package AlgLin;

import Abstracts.SysLinAbstract;
import Exceptions.IrregularSysLinException;

public class Helder extends SysLinAbstract {

    public Helder(Matrice matrice, Vecteur vect) throws IrregularSysLinException {
        super(matrice, vect);
    }

    /*
        • public void factorLDR() qui remplace les coefficients de la matrice d'origine du système par
        les coefficients non nuls et non égaux à 1 des trois matrices L, D et R.
         */
    public void factorLDR(){
        // on suppose que la matrice est bien une matrice carrée
        // source : http://fmdkdd.free.fr/files/MIS6_Analyse_Num%C3%A9rique.pdf

        int n  = this.matriceSystem.nbLigne();
        for (int i = 0; i < n; i++) {

            // pour L
            double Lval = 0;
            for (int j = 0; j < i ; j++) {
                Lval = 0;

                for (int k = 0; k < j ; k++) {
                    Lval += this.matriceSystem.getCoef(i,k) *
                            this.matriceSystem.getCoef(k,k) *
                            this.matriceSystem.getCoef(k,j);
                }
                Lval = this.matriceSystem.getCoef(i,j) - Lval;

                Lval = Lval / this.matriceSystem.getCoef(j,j);

                this.matriceSystem.remplacecoef(i,j, Lval);
            }

            // pour D
            double Dval = 0;
            for (int k = 0; k < i ;k++) {
                Dval += this.matriceSystem.getCoef(i,k) *
                        this.matriceSystem.getCoef(k,k) *
                        this.matriceSystem.getCoef(k,i);            // dans un doc c'était k,j !
            }
            Dval = this.matriceSystem.getCoef(i,i) - Dval;
            this.matriceSystem.remplacecoef(i,i, Dval);


            // pour R
            double Rval = 0;
            for (int j = i +1; j < n; j++) {
                Rval = 0;
                for (int k = 0; k < i; k++) {
                    Rval += this.matriceSystem.getCoef(i, k) *
                            this.matriceSystem.getCoef(k,k) *
                            this.matriceSystem.getCoef(k,j);
                }

                Rval = this.matriceSystem.getCoef(i, j) - Rval;
                Rval = Rval / this.matriceSystem.getCoef(i, i);
                this.matriceSystem.remplacecoef(i,j, Rval);
            }

        }

    }

    /*
    • public Vecteur resolution() qui commence par factoriser la matrice et résout les systèmes
    élémentaires décrits par les matrices L, D et R ;
     */
    public Vecteur resolution(){
        // on factorise puis on trouve la solution.
        factorLDR();
        return resolutionPartielle();
    }
    /*
    • public Vecteur resolutionPartielle() qui suppose que la matrice du système est déjà sous
    forme factorisée et n'effectue que la résolution des systèmes élémentaires.
    */
    public Vecteur resolutionPartielle() {
        // resoudre en utlisant SysTirangSup, SysTriangInf et SysDiagonal

        // on a Ax=b tq; A = LDR
        // donc le systeme devient: LDRx=b
        // on commence par résoudre:
        // * D'abord,   Lz = b
        // * puis,      Dy = z
        // * enfin,     Rx = y


        // pour Lz = b
        Vecteur z;
        try {
            SysTriangInfUnite L = new SysTriangInfUnite(this.matriceSystem, this.secondMembre);
            z = L.resolution();
        } catch (IrregularSysLinException e) {
            throw new RuntimeException(e);
        }

        // pour Dy = z
        Vecteur y;
        try {
            SysDiagonal D = new SysDiagonal(this.matriceSystem, z);
            y = D.resolution();
        }  catch (IrregularSysLinException e) {
            throw new RuntimeException(e);
        }

        // pour Rx = y
        Vecteur x;
        try {
            SysTriangSupUnite R = new SysTriangSupUnite(this.matriceSystem, y);
            x = R.resolution();
        } catch (IrregularSysLinException e) {
            throw new RuntimeException(e);
        }

        return x;       // x est la solution de notre système
    }

    /*
    • public void setSecondMembre(Vecteur newSecondMembre) va affecter au second membre
    du système, le vecteur donné en paramètre.
     */
    public void setSecondMembre(Vecteur newSecondMembre){
        this.secondMembre = newSecondMembre;
    }


    private Matrice multiplieLDR(Matrice LDR) {

        Matrice L   = new Matrice(this.getOrdre(), this.getOrdre());
        Matrice D   = new Matrice(this.getOrdre(), this.getOrdre());
        Matrice R   = new Matrice(this.getOrdre(), this.getOrdre());

        for (int i = 0 ; i < getOrdre() ; i++){
            for (int j = 0 ; j < i ; j++)
                L.remplacecoef(i, j, LDR.getCoef(i, j));

            for (int j = i + 1 ; j < getOrdre() ; j++)
                R.remplacecoef(i, j, LDR.getCoef(i, j));

            L.remplacecoef(i, i, 1);
            D.remplacecoef(i, i, LDR.getCoef(i, i));
            R.remplacecoef(i, i, 1);
        }

        System.out.println("L\n"  +  L);
        System.out.println("D\n"  +  D);
        System.out.println("R\n"  +  R);


        Matrice _LD  = Matrice.produit(L, D);
        Matrice _LDR = Matrice.produit(_LD, R);

        // renvoyer _LDR qui est le résultat des produits de L*D*R
        // l'appelant doit verifier que _LDR == LDR
        return _LDR;
    }







    public static void main(String[] args) {

        // test: ex 1 - td 1
        try{

            System.out.println("\n\n");

            double[][] tab = {
                    { 1, 1,  -2},
                    { 4, -2,  1},
                    { 3, -1,  3},
            };

            Vecteur sMembre = new Vecteur(new double[]{3, 5, 8});

            Matrice matrice = new Matrice(tab);

            Helder system = new Helder(matrice, sMembre);

            system.factorLDR();

            // afficher la factorisation de la matrice en LDR
            System.out.println("Matrice après factorisation");
            System.out.println(system.matriceSystem);

            // afficher le resultat du produit L*D*R, verifier == matrice ?
            System.out.println("Résultat du produit L*D*R");
            Matrice produitLDR = system.multiplieLDR(system.matriceSystem);
            System.out.println(produitLDR);

            // comparer les deux
            System.out.print("L*D*R ==? system.matrice: ");
            System.out.println(Matrice.checkEquality(new Matrice(tab), produitLDR, Matrice.numerical_epsilon));

        }catch(Exception e){
            e.printStackTrace();
        }


        try{

            System.out.println("\n\n");

            double[][] tab = {
                    { 1, 2,  3,  4},
                    {-2, 3,  6, -1},
                    { 3, 4, -6,  2},
                    {-5, 2,  3, -1}
            };

            Vecteur sMembre = new Vecteur(new double[]{3, 7, 2, -1});

            Matrice matrice = new Matrice(tab);
            Helder system = new Helder(matrice, sMembre);

            system.factorLDR();

            // afficher la factorisation de la matrice en LDR
            System.out.println("Matrice après factorisation");
            System.out.println(system.matriceSystem);

            // afficher le resultat du produit L*D*R, verifier == matrice ?

            System.out.println("Résultat du produit L*D*R");
            Matrice produitLDR = system.multiplieLDR(system.matriceSystem);
            System.out.println(produitLDR);

            // comparer les deux
            System.out.print("L*D*R ==? system.matrice: ");
            System.out.println(Matrice.checkEquality(new Matrice(tab), produitLDR, Matrice.numerical_epsilon));


        }catch(Exception e){
            e.printStackTrace();
        }

        // faut tester avec A²x = b

    }
}
