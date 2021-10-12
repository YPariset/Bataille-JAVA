package org.example;

import java.util.ArrayList;

public class Joueur {

    /****************************************************/
    /*************** Attributes/Instances ***************/
    /****************************************************/

    private ArrayList<Carte> cartesEnMain;
    private int score;
    private String nom;
    private boolean estHumain;

    /****************************************************/
    /***********           Constructor        ***********/
    /****************************************************/

    public Joueur() {
        this.score = 0;
        this.cartesEnMain = new ArrayList<Carte>();
        this.estHumain = true;
    }

    public Joueur(String nom) {
        this.nom = nom;
        this.score = 0;
        this.cartesEnMain = new ArrayList<Carte>();
        this.estHumain = true;
    }

    public Joueur(String nom, boolean estHumain) {
        this.nom = nom;
        this.score = 0;
        this.cartesEnMain = new ArrayList<Carte>();
        this.estHumain = estHumain;
    }

    /****************************************************/
    /***********            Methods           ***********/
    /****************************************************/

    public Carte jouerCarte() {
        Carte c = cartesEnMain.get(0);
        cartesEnMain.remove(cartesEnMain.get(0));
        return c;
    }

    public void ajouterCarteEnMain(Carte c) {
        this.cartesEnMain.add(c);
    }

    public void ajouterPlusieursCartesEnMain(ArrayList<Carte> c) {
        this.cartesEnMain.addAll(c);
    }

    public void gagneUnPoint() {
        this.score++;
    }

    public boolean estHumain() {
        return estHumain;
    }

    /****************************************************/
    /***********            Getters           ***********/
    /****************************************************/

    public String getNom() {
        return nom;
    }

    public int getScore() {
        return score;
    }


    /****************************************************/
    /***********            Setters           ***********/
    /****************************************************/

    public void setEstHumain(boolean estHumain) {
        this.estHumain = estHumain;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}