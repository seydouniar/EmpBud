package com.budgetemprunt.seydou.budemp;

/**
 * Created by Niare on 20/05/2016.
 */
public class Comptes {
    private long id;
    private String nom;
    private String pass;
    private double heures;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPass(){
        return pass;
    }

    public void setPass(String pass){
        this.pass = pass;
    }
    public double getHeures(){
        return heures;
    }

    public void setHeures(double heures){
        this.heures = heures;
    }
    @Override
    public String toString() {
        return nom;
    }
}
