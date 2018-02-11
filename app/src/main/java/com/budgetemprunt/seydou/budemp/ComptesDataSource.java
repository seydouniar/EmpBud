package com.budgetemprunt.seydou.budemp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niare on 20/05/2016.
 */
public class ComptesDataSource {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_NOM,MySQLiteHelper.COLUMN_PASS,MySQLiteHelper.COLUMN_HEURES };

    public ComptesDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Comptes createCompte(String nom,String pass) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_NOM,nom);
        values.put(MySQLiteHelper.COLUMN_PASS,pass);
        values.put(MySQLiteHelper.COLUMN_HEURES,0);
        long insertId = database.insert(MySQLiteHelper.TABLE_COMPTES, null,
                values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_COMPTES,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Comptes newCompte = cursorToComptes(cursor);
        cursor.close();
        return newCompte;
    }

    public Comptes selectCompte(long id){
        Cursor cursor = database.query(MySQLiteHelper.TABLE_COMPTES,allColumns,MySQLiteHelper.COLUMN_ID+" = ?",new String[] { String.valueOf(id) },null,
                null, null,null);
        if (cursor != null)
            cursor.moveToFirst();
        Comptes newCompte = cursorToComptes(cursor);
        cursor.close();
        return newCompte;
    }
    public void deleteCompte(Comptes comptes) {
        long id = comptes.getId();
        //System.out.println("Comment deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_COMPTES, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public void editCompte(Comptes compte,Long id){

        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_NOM, compte.getNom());
        values.put(MySQLiteHelper.COLUMN_PASS,compte.getPass());
        values.put(MySQLiteHelper.COLUMN_HEURES,compte.getHeures());
        database.update(MySQLiteHelper.TABLE_COMPTES, values,  MySQLiteHelper.COLUMN_ID + "=" +id, null);
    }

    public void editheures(Comptes compte){
        long id = compte.getId();
        double heur = compte.getHeures();
        ContentValues value = new ContentValues();
        value.put(MySQLiteHelper.COLUMN_HEURES,heur);
        database.update(MySQLiteHelper.TABLE_COMPTES,value,MySQLiteHelper.COLUMN_ID  + " = ?", new String[] {String.valueOf(compte.getId())});
    }
    public List<Comptes> getAllComptes() {
        List<Comptes> comptes = new ArrayList<Comptes>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_COMPTES,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Comptes compte = cursorToComptes(cursor);
            comptes.add(compte);
            cursor.moveToNext();
        }
        return comptes;
    }

    private Comptes cursorToComptes(Cursor cursor) {
        Comptes compte = new Comptes();
        compte.setId(cursor.getLong(0));
        compte.setNom(cursor.getString(1));
        compte.setPass(cursor.getString(2));
        compte.setHeures(cursor.getDouble(3));
        return compte;
    }

}

