package org.example;

public class Carte {

    /****************************************************/
    /*************** Attributes/Instances ***************/
    /****************************************************/

    static String[] couleurs = {"pique","trefle","coeur","carreau"};
    private String couleur;
    private int nombre;

    /****************************************************/
    /***********           Constructor        ***********/
    /****************************************************/

    /**
     *
     * @param couleur
     * @param nombre
     */
    public Carte(String couleur, int nombre) {
        this.couleur = couleur;
        this.nombre = nombre;
    }

    /****************************************************/
    /***********            Methods           ***********/
    /****************************************************/

    /**
     * Fonction qui compare la valeur de deux cartes
     * @param c
     * @return
     */
    public int compareA(Carte c) {
        return this.nombre - c.getNombre();
    }

    /**
     * Fonction qui convertie la valeur numérique de la carte en string
     * @return
     */
    private String nomValeur() {
        String nomValeur;
        if(this.nombre == 11) nomValeur="valet";
        else if(this.nombre == 12) nomValeur="reine";
        else if(this.nombre ==13) nomValeur="roi";
        else nomValeur = String.valueOf(this.nombre);
        return nomValeur;
    }

    @Override
    public String toString() {
        return this.nomValeur()+" de "+this.couleur;
    }

    /****************************************************/
    /***********            Getters           ***********/
    /****************************************************/

    public int getNombre() {
        return nombre;
    }

}
