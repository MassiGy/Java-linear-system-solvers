package AlgLin;

import java.io.BufferedReader;

public class Vecteur extends Matrice{


    public Vecteur(int nbligne) {

        // un vecteur a qu'une seule colonne
        super(nbligne, 1);
    }

    public Vecteur(Matrice m){
        super(matriceEnVecteur(m));
    }

    private static double[][] matriceEnVecteur(Matrice m) {
        double[][] tableau = new double[m.nbLigne()][m.nbColonne()];
        for (int i = 0; i < m.nbLigne(); i++)
            for (int j = 0 ; j < m.nbColonne(); j++)
                tableau[i][j] = m.getCoef(i, j);

        return tableau;
    }

    /*
    Un constructeur avec un paramètre tableau à une dimension composé de coefficients de
    type double. Le tableau « coefficient » possède alors une seule colonne qui contient les
    valeurs du tableau donné en paramètre
     */
    public Vecteur(double[] coefficients){

        this(coefficients.length);
        for (int i = 0; i < coefficients.length; i++) {
            this.coefficient[i][0] = coefficients[i];
        }
    }

    /*
    Un constructeur avec un paramètre correspondant à un nom de fichier qui contiendra la
    taille et les coefficients du Vecteur qui seront rangés dans l’attribut « coefficient » qui ne
    contient qu’une seule colonne; Ce constructeur sera capable de détecter grâce à une
    exception que le fichier nommé n’est pas présent.
     */
    public Vecteur(String fichier){
        super(fichier);
    }

    public Vecteur(BufferedReader reader){
        super(reader);
    }

    /*
        Une méthode qui renvoie la taille du Vecteur
     */

    public int nbLigne() {
        return this.coefficient.length;
    }

    public int nbColonne() {
        return 1;
    }

    public double getCoef(int ligne) {
        return this.coefficient[ligne][0];
    }

    public void remplacecoef(int ligne, double value) {
        this.coefficient[ligne][0] = value;
    }


    public String toString() {
        return super.toString();
    }



    /* produit scalaire */
    public static double produit_scalaire(Vecteur a, Vecteur b ) {
        if(a.nbLigne() != b.nbLigne()) {
            throw new IllegalArgumentException("les deux vecteurs en entré n'ont pas le même nombre de lignes.");
        }
        return Vecteur.produit_scalaire_sans_verification(a,b);
    }


    public static double produit_scalaire_sans_verification(Vecteur a, Vecteur b){
        double sum = 0;
        for (int i = 0; i < a.nbLigne(); i++) {
            sum += a.getCoef(i) * b.getCoef(i);
        }
        return sum;
    }

    public double L1_norme() {
       double sum = 0;

        for (int i = 0; i < this.nbLigne(); i++) {
            sum += Math.abs(this.getCoef(i));
        }

        return sum;
    }

    public double L2_norme() {
        double sum  = 0;
        for (int i = 0; i < this.nbLigne(); i++) {
            sum += Math.abs(this.getCoef(i)) * Math.abs(this.getCoef(i));
        }

        return Math.sqrt(sum);
    }

    public double Linf_norme() {
        double max = this.getCoef(0);

        for (int i = 1; i < this.nbLigne(); i++) {
            double ithElem = Math.abs(this.getCoef(i));
            if(ithElem > max) {
                max = ithElem;
            }

        }
        return max;
    }



    public static void main(String[] args) throws Exception {

        double[] coeffs =  {2,1,3};
        Vecteur a = new Vecteur(coeffs);
        System.out.println("construction d'un vecteur par affectation d'un tableau :\n"+a);


        Vecteur b = new Vecteur("/home/massigy/Documents/mesCours/scientific-programming/assets/vecteur.txt");
        System.out.println("Construction d'un Vecteur par lecture d'un fichier :\n"+b);

        System.out.println("Coefficient (2,1) de la matrice b : "+b.getCoef(1));

        System.out.println("Nouvelle valeur de ce coefficient : 8");
        b.remplacecoef(1, 8);
        System.out.println("Vérification de la modification du coefficient");
        System.out.println("Coefficient (2,1) de la vecteur b : "+b.getCoef(1));

        System.out.println("Addition de 2 vecteur : affichage des 2 vectuer "+
                "puis de leur addition");
        System.out.println("vectuer 1 :\n"+a+"vectuer 2 :\n"+b+"somme :\n"+
                Matrice.addition(a,b));
        System.out.println("Produit de 2 vectuer : affichage des 2 vectuer "+
                "puis de leur produit");
        System.out.println("vectuer 1 :\n"+a+"vectuer 2 :\n"+b+"produit :\n"+
                Vecteur.produit_scalaire(a,b));
    }
}
