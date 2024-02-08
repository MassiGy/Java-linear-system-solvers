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

        // dim(res) = len(diag(m))
        Vecteur res = new Vecteur(m.coefficient[1].length);

        // on commence par la diagonale
        for (int i = 0; i < res.nbLigne(); i++) {
            res.remplacecoef(
                    i,
                    m.coefficient[1][i] * v.getCoef(i)
            );
        }
        
        // puis la sur diagonale, un coeff de moins à la fin
        for (int i = 0; i < res.nbLigne()-1; i++) {
            res.remplacecoef(i,
                   res.getCoef(i) + m.coefficient[0][i] * v.getCoef(i+1)
                    // on accede à v[i+1] car la sur diagonal viens après la diagonale dans
                    // la somme des produits des coeff
            );
        }

        // puis la sous diagonale, un coeff de mois au debut
        for (int i = 1; i < res.nbLigne(); i++) {
            res.remplacecoef(i,
                    res.getCoef(i) + m.coefficient[2][i] * v.getCoef(i-1)
                    // on accede à v[i-1] car la sous diagonal viens avant la diagonale dans
                    // la somme des produits des coeff
            );
        }

        return res;
    }







}
