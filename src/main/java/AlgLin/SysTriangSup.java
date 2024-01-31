package AlgLin;

import Abstracts.SysLinAbstract;
import Exceptions.IrregularSysLinException;

public class SysTriangSup extends SysLinAbstract {

    public SysTriangSup(Matrice matrice, Vecteur vect) throws IrregularSysLinException {
        super(matrice, vect);
    }

    @Override
    public Vecteur resolution() throws IrregularSysLinException {
        Vecteur res = new Vecteur(this.secondMembre.nbLigne());
        double ithCoefVal = 0;
        double accumulator = 0;
        try {

            for (int i = this.secondMembre.nbLigne()-1; i >= 0; i--) {
                accumulator = 0;
                for (int j = i+1 ; j < this.matriceSystem.nbColonne(); j++) {
                    accumulator += this.matriceSystem.getCoef(i, j) * res.getCoef(j);
                }
                ithCoefVal = (this.secondMembre.getCoef(i) - accumulator) / this.matriceSystem.getCoef(i, i);
                res.remplacecoef(i, ithCoefVal);
            }

            return res;

        } catch (Exception e){
            throw new IrregularSysLinException("On ne peut pas déviser par zéro. Pas de solution pour ce système");
        }
    }

    public static void main(String[] args) {

        try {
            // Création d'une matrice triangulaire supérieure valide
            double[][] coefficients1 = {{3, 4, 5}, {0, 1, 7}, {0, 0, 8}};
            Matrice matriceTriangSup1 = new Matrice(coefficients1);
            Vecteur secondMembre1 = new Vecteur(new double[]{3, 7, 2});

            // Test de la résolution du système
            SysTriangSup sysTriangSup1 = new SysTriangSup(matriceTriangSup1, secondMembre1);
            Vecteur solution1 = sysTriangSup1.resolution();
            System.out.println("Solution du système triangulaire supérieur : \n" + solution1);

            // Calcul de Ax - b
            Matrice Ax1 = Matrice.produit(matriceTriangSup1, (Matrice)solution1);
            Matrice AxMoinsB1 = Matrice.soustraction(Ax1, secondMembre1);

            double L1_norme1 = (new Vecteur(AxMoinsB1)).L1_norme();
            double L2_norme1 = (new Vecteur(AxMoinsB1)).L2_norme();
            double Linf_norme1 = (new Vecteur(AxMoinsB1)).Linf_norme();


            if (L1_norme1 < Matrice.numerical_epsilon) {
                System.out.println("Test réussi, L1_norme de (Ax - b) est suffisamment petite.");
            } else {
                System.out.println("Test échoué, L1_norme de (Ax - b) est trop grande.");
            }

            if (L2_norme1 < Matrice.numerical_epsilon) {
                System.out.println("Test réussi, L2_norme de (Ax - b) est suffisamment petite.");
            } else {
                System.out.println("Test échoué, L2_norme de (Ax - b) est trop grande.");
            }

            if (Linf_norme1 < Matrice.numerical_epsilon) {
                System.out.println("Test réussi, Linf_norme de (Ax - b) est suffisamment petite.");
            } else {
                System.out.println("Test échoué, Linf_norme de (Ax - b) est trop grande.");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
