package AlgLin;

import java.io.*;
import java.util.*;

public class Matrice {
    /** Définir ici les attributs de la classe **/
    protected double coefficient[][];

    // epsilon numérique pour les test.
    public final static double numerical_epsilon = 0.000001;



    /** Définir ici les constructeur de la classe **/
    Matrice() {}


    Matrice (int nbligne, int nbcolonne){
        this.coefficient = new double[nbligne][nbcolonne];
    }




    Matrice(double[][] tableau){

        // tableau est supposé être une matrice carré


        coefficient = new double[tableau.length][];
        for (int i = 0; i < tableau.length; i++) {
            coefficient[i] = new double[tableau[i].length];

            for (int j = 0; j < coefficient[i].length; j++) {
                coefficient[i][j] = tableau[i][j];
            }
        }
    }
    Matrice(BufferedReader bufferedReader){
        try {
            Scanner sc  = new Scanner(bufferedReader);
            int ligne = sc.nextInt();
            int colonne = sc.nextInt();
            this.coefficient = new double[ligne][colonne];
            for(int i=0; i<ligne;i++)
                for(int j=0; j< colonne; j++)
                    this.coefficient[i][j]=sc.nextDouble();
            sc.close();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    Matrice(String fichier){
        try {
            Scanner sc = new Scanner(new File(fichier));
            int ligne = sc.nextInt();
            int colonne = sc.nextInt();
            this.coefficient = new double[ligne][colonne];
            for(int i=0; i<ligne;i++)
                for(int j=0; j< colonne; j++)
                    this.coefficient[i][j]=sc.nextDouble();
            sc.close();

        }
        catch(FileNotFoundException e) {
            System.out.println("Fichier absent");
        }
    }

    /** Definir ici les autres methodes */
    public void recopie(Matrice modele){
        int ligne, colonne;
        ligne = modele.nbLigne(); colonne = modele.nbColonne();
        this.coefficient = new double[ligne][colonne];
        for(int i=0; i<ligne; i++)
            for (int j=0;j<colonne;j++)
                this.coefficient[i][j]= modele.coefficient[i][j];
    }

    public int nbLigne(){
        return this.coefficient.length;
    }

    public int nbColonne(){
        return this.coefficient[0].length;
    }

    public double getCoef(int ligne, int colonne){
        return this.coefficient[ligne][colonne];
    }

    public void remplacecoef(int ligne, int colonne, double value){
        this.coefficient[ligne][colonne]=value;
    }

    public String toString(){
        int ligne = this.nbLigne();
        int colonne = this.nbColonne();
        String matr = "";
        for(int i = 0; i<ligne;i++){
            for(int j =0; j< colonne;j++){
                if(j == 0)
                {
                    matr += this.getCoef(i, j);
                }
                else{
                    matr += " " + this.getCoef(i, j);
                }
            }
            matr += "\n";
        }
        return matr;
    }

    public Matrice produit(double scalaire){
        int ligne = this.nbLigne();
        int colonne = this.nbColonne();
        for(int i=0; i<ligne;i++)
            for(int j=0; j< colonne; j++)
                this.coefficient[i][j]*=scalaire;
        return this;
    }

    static Matrice addition(Matrice a, Matrice b){
        int ligne = a.nbLigne();
        int colonne = a.nbColonne();
        Matrice mat = new Matrice(ligne, colonne);
        for(int i=0; i<ligne;i++)
            for(int j=0; j< colonne; j++)
                mat.coefficient[i][j]=a.coefficient[i][j] + b.coefficient[i][j];
        return mat;
    }
    static Matrice soustraction(Matrice a, Matrice b){
        int ligne = a.nbLigne();
        int colonne = a.nbColonne();
        Matrice mat = new Matrice(ligne, colonne);
        for(int i=0; i<ligne;i++)
            for(int j=0; j< colonne; j++)
                mat.coefficient[i][j]=a.coefficient[i][j] - b.coefficient[i][j];
        return mat;
    }
    static boolean checkEquality(Matrice A, Matrice B, double sd){
        // sd -> standard deviation, pour nous c'est l'epsilon numérique

        // on suppose que A et B sont des matrice carré avec la même dimension.
        for (int i = 0; i < A.nbLigne(); i++) {
            for (int j = 0; j < A.nbColonne(); j++) {
                if(A.getCoef(i,j) - B.getCoef(i,j) > sd)
                    return false;
            }
        }
        return true;
    }


    static Matrice verif_addition(Matrice a, Matrice b) throws Exception{
        if((a.nbLigne() == b.nbLigne()) && (a.nbColonne() == b.nbColonne()))
        {
            int ligne = a.nbLigne();
            int colonne = a.nbColonne();
            Matrice mat = new Matrice(ligne, colonne);
            for(int i=0; i<ligne;i++)
                for(int j=0; j< colonne; j++)
                    mat.coefficient[i][j]=a.coefficient[i][j] + b.coefficient[i][j];
            return mat;
        }
        else {
            throw new Exception("Les deux matrices n'ont pas les mêmes dimensions !!!");
        }
    }

    static Matrice produit(Matrice a, Matrice b){
        int ligne, colonne;
        ligne = a.nbLigne();
        colonne = b.nbColonne();
        Matrice mat = new Matrice(ligne, colonne);
        for(int i=0; i<ligne;i++)
            for(int j=0; j< colonne; j++)
            {
                mat.coefficient[i][j]=0;
                for(int k=0; k <a.nbColonne();k++)
                    mat.coefficient[i][j] += a.coefficient[i][k] * b.coefficient[k][j];
            }
        return mat;
    }

    static Matrice verif_produit(Matrice a, Matrice b) throws Exception{
        int ligne = 0;
        int colonne = 0;
        if(a.nbColonne()==b.nbLigne())
        {
            ligne = a.nbLigne();
            colonne = b.nbColonne();
        }
        else{
            throw new Exception("Dimensions des matrices à multiplier incorrectes");
        }

        Matrice mat = new Matrice(ligne, colonne);
        for(int i=0; i<ligne;i++)
            for(int j=0; j< colonne; j++)
            {
                mat.coefficient[i][j]=0;
                for(int k=0; k <a.nbColonne();k++)
                    mat.coefficient[i][j] += a.coefficient[i][k] * b.coefficient[k][j];
            }
        return mat;
    }

    public static void main(String[] args) throws Exception {
        double mat[][]= {{2,1},{0,1}};
        Matrice a = new Matrice(mat);
        System.out.println("construction d'une matrice par affectation d'un tableau :\n"+a);

        ClassLoader classLoader = Helder.class.getClassLoader();
        // lire le fichier de la matrice
        File configFile= new File(classLoader.getResource("matrice.txt").getFile());
        FileInputStream inputStream = new FileInputStream(configFile);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        Matrice b = new Matrice(reader);

        System.out.println("Construction d'une matrice par lecture d'un fichier :\n"+b);
        Matrice c = new Matrice(2,2);
        c.recopie(b);
        System.out.println("Recopie de la matrice b :\n"+c);
        System.out.println("Nombre de lignes et colonnes de la matrice c : "+c.nbLigne()+
                ", "+c.nbColonne());
        System.out.println("Coefficient (2,2) de la matrice b : "+b.getCoef(1, 1));
        System.out.println("Nouvelle valeur de ce coefficient : 8");
        b.remplacecoef(1, 1, 8);
        System.out.println("Vérification de la modification du coefficient");
        System.out.println("Coefficient (2,2) de la matrice b : "+b.getCoef(1, 1));
        System.out.println("Addition de 2 matrices : affichage des 2 matrices "+
                "puis de leur addition");
        System.out.println("matrice 1 :\n"+a+"matrice 2 :\n"+b+"somme :\n"+
                Matrice.addition(a,b));
        System.out.println("Produit de 2 matrices : affichage des 2 matrices "+
                "puis de leur produit");
        System.out.println("matrice 1 :\n"+a+"matrice 2 :\n"+b+"produit :\n"+
                produit(a,b));
    }

}
