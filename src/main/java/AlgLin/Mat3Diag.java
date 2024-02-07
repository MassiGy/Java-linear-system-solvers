package AlgLin;

import Exceptions.IllegalTridiagDimensionException;

public class Mat3Diag extends Matrice {

    public Mat3Diag(int dim1, int dim2) throws Exception{
        if(dim1 != 3) {
            throw  new IllegalTridiagDimensionException("Dimension différente de 3");
        }
        this.coefficient = new double[dim1][dim2];
    }

    public Mat3Diag(double tableau[][]) throws Exception{
        if(tableau.length != 3) {
            throw  new IllegalTridiagDimensionException("Dimension différente de 3");
        }
        this.coefficient = tableau;
    }

    public Mat3Diag(int dim) {
        this.coefficient = new double[3][dim];
    }


    public static Vecteur produit(Mat3Diag m, Vecteur v) {

        // le resulat sera une vecteur de 3 ligne
        Vecteur res = new Vecteur(3);


        // étant donne que on a que 3 ligne à prendre
        // en compte, on peut décomposer le calcul en
        // 3 etape


        // ligne diagonale
        // la ligne du tableau qui nous interesse est la deuxieme ligne
        for (int i = 0; i < m.nbColonne(); i++) {
            res.remplacecoef(
                    i,
                    // 1 pour la deuxième ligne du tab coeffs
                    m.getCoef(1,i) * v.getCoef(i)
            );
        }
        
        
        

        // ligne  sous diagonale
        for (int i = 0; i < m.nbColonne()-1; i++) {
            res.remplacecoef(
                    i,

                    // 2 pour la troisieme lignes du tabl coeffs
                    res.getCoef(i) + m.getCoef(2,i) * v.getCoef(i)
            );
        }
        
        

        // ligne sur diagonal
        for (int i = 0; i < m.nbColonne()-1; i++) {
            res.remplacecoef(
                    i,

                    // 0 pour la premier ligne du tab coeffs
                    res.getCoef(i) + m.getCoef(0,i+1) * v.getCoef(i)
            );
        }


        return res;

    }







}
