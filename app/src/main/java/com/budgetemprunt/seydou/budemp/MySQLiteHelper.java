package com.budgetemprunt.seydou.budemp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Niare on 20/05/2016.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_COMPTES = "comptes";
    public static final String TABLE_HIST = "historique";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NOM = "nom";
    public static final String COLUMN_PASS = "password";
    public static final String COLUMN_HEURES = "heures";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_IDH = "_id";
    public static final String COLUMN_HEURE = "heure";
    public static final String COLUMN_NBH = "nombreh";
    private static final String DATABASE_NAME = "comptes.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_COMPTES + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_NOM
            + " text not null,"+COLUMN_PASS+" text not null, "+COLUMN_HEURES+" DECIMAL(10,2));";
    private static final String DATABASE_DEUX = "create table "
            + TABLE_HIST + "(" + COLUMN_IDH + " integer primary key autoincrement," +COLUMN_NOM+" text," + COLUMN_DATE
            + " text not null,"+COLUMN_NBH+" DECIMAL(10,2),"+COLUMN_HEURE+" text not null);";


    private static MySQLiteHelper instance;

    public static synchronized MySQLiteHelper getHelper(Context context) {
        if (instance == null)
            instance = new MySQLiteHelper(context);
        return instance;
    }
    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPTES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HIST);
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
        db.execSQL(DATABASE_DEUX);
    }
}
