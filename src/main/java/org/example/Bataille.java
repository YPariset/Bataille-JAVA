package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Bataille {

/****************************************************/
/*************** Attributes/Instances ***************/
/****************************************************/

    private static ArrayList<Carte> pioche;
    private int scoreLimite;
    private Scanner choixJeu;
    private Joueur j1;
    private Joueur j2;
    private int nbTour;
    private Carte c1,c2;
    private Prompt prompt;
    private ArrayList<String> pseudos;

/****************************************************/
/***********           Constructor        ***********/
/****************************************************/
    /**
     * Sans param
     */
    public Bataille() {
        this.scoreLimite = 20;

        this.choixJeu = new Scanner(System.in);
        this.j1 = new Joueur();
        this.j2 = new Joueur();
        this.nbTour = 1;
        this.prompt = new Prompt();
    }

    /**
     *
     * @param scoreLimite
     */
    public Bataille(int scoreLimite) {
        this.scoreLimite = scoreLimite;
        this.prompt = new Prompt();
        this.choixJeu = new Scanner(System.in);
        this.j1 = new Joueur();
        this.j2 = new Joueur();
        this.nbTour = 1;
    }

/****************************************************/
/***********            Methods           ***********/
/****************************************************/

    /**
     * Fonction principale qui génère la partie
     */
    public void jouer() {
        System.out.println("      O                                                 O\n" +
                "{o)xxx|===============-  Bataille JAVA  -===============|xxx(o}\n" +
                "      O                                                 O");
        this.pseudos = prompt.lirePseudo();

        this.j1.setNom(pseudos.get(0));
        this.choixMultijoueur();
        this.creerJeuDeCarte();
        this.distributionDesCartes();

        //La Partie
        boolean partieFini=false;
        while(!joueurAGagnerLaPartie(j1) && !joueurAGagnerLaPartie(j2) && !partieFini) {
            partieFini = this.UnTour();
        }
        this.affichageVainqueurDeLaPartie();
    }

    /**
     * Fonction qui génère un tour de la partie
     */
    public boolean UnTour() {
        System.out.println(" ============== Tour "+this.nbTour+" ==============");

        //TOUR JOUEUR 1
        this.c1 = tourJoueur(this.j1);
        if(this.c1 == null) return true;

        //TOUR JOUEUR 2
        this.c2 = tourJoueur(this.j2);
        if(this.c2 == null) return true;

        if(this.c1.compareA(this.c2) > 0) { //Si la carte du joueur 1 est meilleur que celle du joueur 2 alors joueur 1 gagne la manche
            gagneTour(this.j1);
        }
        else if(this.c1.compareA(this.c2) < 0) { //Si la carte du joueur 2 est meilleur que celle du joueur 1 alors joueur 2 gagne la manche
            gagneTour(this.j2);
        } else {
            this.grandeBataille();
        }
        this.affichageDuScore();
        this.nbTour++;
        return false;
    }

    /**
     * Fonction qui définie le vainqueur du tour
     * @param vainqueur
     */
    public void gagneTour(Joueur vainqueur) {
        vainqueur.ajouterCarteEnMain(this.c1);
        vainqueur.ajouterCarteEnMain(this.c2);
        System.out.println("-----------------------------------------");
        System.out.println("\t\t\t" + vainqueur.getNom()+" remporte le tour");
        System.out.println("-----------------------------------------");
        vainqueur.gagneUnPoint();
    }

    /**
     * Fonction qui affiche les options de jeu à chaques tours
     * @param j
     * @return
     */
    public Carte tourJoueur(Joueur j) {
        Carte c = null;
        if(j.estHumain()) {
            System.out.println("\n\t" + j.getNom()+", à votre tour : \n \t(1 ou autre) pour jouer , (2) pour quitter.");
            String choixJoueur = choixJeu.nextLine();
            if(choixJoueur.equals("2")) {
                return c;
            }
        }
        c = j.jouerCarte();
        System.out.println("\t\t" + j.getNom()+" joue la carte : \n\t\t\t"+c.toString());
        return c;
    }

    /**
     * Fonction qui distribue les cartes aux différents joueurs
     */
    public void distributionDesCartes() {
        System.out.println("Distribution des cartes...");
        while(pioche.size()!=0) {
            carteAleatoire(this.j1);
            carteAleatoire(this.j2);
        }
    }

    /**
     * Fonction qui génère un main de cartes aléatoires
     * @param j
     */
    public void carteAleatoire (Joueur j) {
        int pos1 = (int) (Math.random() *(pioche.size()));
        j.ajouterCarteEnMain(pioche.get(pos1));
        pioche.remove(pos1);
    }

    /**
     * Fonction qui génère un jeu de carte
     */
    public void creerJeuDeCarte() {
        this.pioche = new ArrayList<Carte>();
        for(int i=0 ; i<4 ; i++) { //Coeur , Carreau , Trefle , Pique : 4
            for(int j=1 ; j<=13 ; j++) { //1 , 2 , 3 ... Valet , Reine , Roi : 13
                pioche.add(new Carte(Carte.couleurs[i],j));
            }
        }
    }

    /**
     * Fonction qui affecte le pseudo au joueur
     */
    public void choixMultijoueur() {
        if (pseudos.get(1).toString().equals("IA")) {
            this.j2.setNom("IA");
            this.j2.setEstHumain(false);
        }
        else {
            this.j2.setNom(pseudos.get(1));
        }
    }

    /**
     * Fonction qui génère la bataille en cas d'égalité
     */
    public void grandeBataille() {
        ArrayList<Carte> tas = new ArrayList<Carte>();
        tas.addAll(Arrays.asList(this.c1,this.c2));
        System.out.println("\t\t\t BATAILLE !!!\n");
        do {
            //Joueur 1
            this.c1 = this.tourJoueur(j1);
            tas.add(this.c1);
            //Joueur 2
            this.c2 = this.tourJoueur(j2);
            tas.add(this.c2);

            if (this.c1.compareA(this.c2) > 0) {
                gagneBataille(this.j1, tas);
            }
            else if (this.c1.compareA(this.c2) < 0) {
                gagneBataille(this.j2, tas);
            }
        } while(this.c1.compareA(this.c2) == 0);
    }

    /**
     * Fonction qui défini le vainqueur de la bataille en cas d'égalité
     * @param gagnant
     * @param tas
     */
    public void gagneBataille(Joueur gagnant, ArrayList tas) {
        gagnant.ajouterPlusieursCartesEnMain(tas);
        System.out.println("-------------------------------------------");
        System.out.println("\t\t\t" + gagnant.getNom()+" remporte la bataille");
        System.out.println("-------------------------------------------\n");
        gagnant.gagneUnPoint();
    }

    /**
     * Fonction qui défini le vainqueur de la partie
     * @param j
     * @return
     */
    public boolean joueurAGagnerLaPartie(Joueur j) {
        return j.getScore() >= this.scoreLimite;
    }

    /**
     * Fonction qui affiche le score des deux joueurs
     */
    public void affichageDuScore() {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("\t\t\tScore de "+j1.getNom()+" : "+j1.getScore());
        System.out.println("\t\t\tScore de "+j2.getNom()+" : "+j2.getScore());
        System.out.println("++++++++++++++++++++++++++++++++++++++++++\n");
    }

    /**
     * Fonction qui affiche le vainqueur de la partie
     */
    public void affichageVainqueurDeLaPartie() {
        System.out.println("----------------------------------------------------");
        System.out.println("|                                                   |");
        System.out.println("|                                                   |");
        if(this.joueurAGagnerLaPartie(j1))
            afficheGagnant(j1);
        else {
            if (j2.getNom().equals("IA")) {
                afficheGagnant(j2);
            }
            else {
                afficheGagnant(j2);
            }
        }
        System.out.println("|                                                   |");
        System.out.println("|                                                   |");
        System.out.println("----------------------------------------------------");
    }

    /**
     * Fonction qui aide a afficher le vainqueur de la partie
     * @param gagnant
     */
    public void afficheGagnant(Joueur gagnant) {
        System.out.println("|\t\t\t\t" + gagnant.getNom()+" a gagné la partie !    \t\t\t|");
    }
}