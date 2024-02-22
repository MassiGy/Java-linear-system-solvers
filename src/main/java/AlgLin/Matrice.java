package AlgLin;

import Exceptions.IllegalOperationException;

import java.io.*;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class Matrice {
    /** Définir ici les attributs de la classe **/
    protected double coefficient[][];

    // epsilon numérique pour les test.
    public final static double numerical_epsilon = 0.00001;



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

    public static Matrice getIdentite(int ordre){
        Matrice m = new Matrice(ordre, ordre);
        for (int i = 0 ; i < ordre ; i++)
            m.remplacecoef(i, i, 1);
        return m;
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
            for(int j=0; j< colonne; j++) {
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

    public Matrice inverse() throws Exception {
        if(this.nbColonne() != this.nbLigne()) {
            throw  new IllegalOperationException("la matrice doit être une matrice carré");
        }

        // créer une matrice copie de la courrent
        Matrice matriceCourrante = new Matrice(this.coefficient);


        // créer une matrice qui sera le résultat
        Matrice inverse = new Matrice(this.nbLigne(), this.nbColonne());

        double[] secondMembreTableau;
        Vecteur secondMembre;
        Helder system = null;

        for (int i = 0; i < this.nbLigne(); i++) {

            // créer le tableau pour le second membre
            secondMembreTableau = new double[this.nbLigne()];

            // mettre le bon coeff en 1
            secondMembreTableau[i] = 1;


            // set le second membre
            secondMembre = new Vecteur(secondMembreTableau);

            if (system == null){
                system = new Helder(matriceCourrante, secondMembre);
                system.factorLDR();
            }
            else{
                system.setSecondMembre(secondMembre);
            }

            // résolution du system
            Vecteur iemeColonneDelInverse  = system.resolutionPartielle();

            // copie des valeur de iemeColonneDeLInverse sur la ieme colonne de inverse
            for (int j = 0; j < iemeColonneDelInverse.nbLigne(); j++) {
                inverse.remplacecoef(j,i, iemeColonneDelInverse.getCoef(j));
            }
        }

        return inverse;
    }


    public double L1_norme() {
        double max = 0;
        double sum = 0;
        for (int j = 0; j < nbColonne(); j++) {
            sum = 0;
            for (int i = 0; i < nbLigne(); i++)
                sum += Math.abs(this.getCoef(i, j));

            if (max < sum) {
                max = sum;
            }
        }
        return max;
    }

    public double Linf_norme() {
        double max = 0;
        double sum = 0;
        for (int i = 0; i < nbLigne(); i++) {

            sum = 0;
            for (int j = 0; j < nbColonne(); j++)
                sum += Math.abs(this.getCoef(i, j));

            if (max < sum) {
                max = sum;
            }
        }
        return max;
    }

    public static double L1_norme(Matrice cible) {
        return cible.L1_norme();
    }
    public static double Linf_norme(Matrice cible){
        return cible.Linf_norme();
    }

    public double calcConditionnement(Function<Matrice,Double> fnCalcNorme) throws Exception{
        double normeMatriceCourrante = fnCalcNorme.apply(this);
        double normeMatriceInverse = fnCalcNorme.apply(this.inverse());
        return normeMatriceCourrante * normeMatriceInverse;
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



        // test de l'inverse;
        System.out.println("####################################################");

        // exemple pris de :http://www.jybaudot.fr/Vecteursmatrices/matinverse.html

        double[][] tabMatrice = new double[][]{
                {1,2,1},
                {3,5,0},
                {4,2,6},
        };
        double[][] tabIdentite = new double[][]{
                {1,0,0},
                {0,1,0},
                {0,0,1},
        };



        // création de la matrice identité
        Matrice identite = new Matrice(tabIdentite);


        // création de la matrice et d'une copie
        Matrice A = new Matrice(tabMatrice);
        Matrice ACopie = new Matrice(tabMatrice);


        // verifier que le produit marche
        // System.out.println(Matrice.produit(A, identite));

        // calc de l'inverse
        Matrice inverseDeA = A.inverse();

        // afficher de l'inverse
        // System.out.println(inverseDeA);


        // calc du produit A*A⁻¹
        Matrice produitAetInverse = Matrice.produit(ACopie, inverseDeA);


        // afficher le produit ( doit être proche de l'id )
        // System.out.println(produitAetInverse);


        // calc de la diff entre produitA et son inverse et l'id
        Matrice diff = Matrice.soustraction(produitAetInverse, identite);

        // System.out.println(diff);

        // calcule de la norme de la diff
        double L1_norme = diff.L1_norme();
        double Linf_norme = diff.Linf_norme();

        if (L1_norme < Matrice.numerical_epsilon) {
            System.out.println("Test réussi, L1_norme de (AA⁻¹-ID) est suffisamment petite.");
        } else {
            System.out.println("Test échoué, L1_norme de (AA⁻¹-ID) est trop grande.");
        }

        if (Linf_norme < Matrice.numerical_epsilon) {
            System.out.println("Test réussi, Linf_norme de (AA⁻¹-ID) est suffisamment petite.");
        } else {
            System.out.println("Test échoué, Linf_norme de (AA⁻¹-ID) est trop grande.");
        }

    }

}
