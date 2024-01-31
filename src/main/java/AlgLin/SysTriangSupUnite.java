package AlgLin;

import Abstracts.SysLinAbstract;
import Exceptions.IrregularSysLinException;

public class SysTriangSupUnite extends SysLinAbstract {
    public SysTriangSupUnite(Matrice matrice, Vecteur vect) throws IrregularSysLinException {
        super(matrice, vect);
    }

    @Override
    public Vecteur resolution() throws IrregularSysLinException {
        Vecteur res = new Vecteur(this.secondMembre.nbLigne());
        double ithCoefVal = 0;
        double accumulator = 0;

        for (int i = this.secondMembre.nbLigne()-1; i >= 0; i--) {
            accumulator = 0;
            for (int j = i+1 ; j < this.matriceSystem.nbColonne(); j++) {
                accumulator += this.matriceSystem.getCoef(i, j) * res.getCoef(j);
            }
            ithCoefVal = (this.secondMembre.getCoef(i) - accumulator); // dévisé par 1
            res.remplacecoef(i, ithCoefVal);
        }
        return res;
    }


    public static void main(String[] args) {

        try {
            // Création d'une matrice triangulaire supérieure unitaire
            double[][] coefficientsSup = {{1, 2, 3}, {0, 1, 4}, {0, 0, 1}};
            Matrice matriceTriangSupUnite = new Matrice(coefficientsSup);
            Vecteur secondMembreSup = new Vecteur(new double[]{9, 8, 7});

            // Test de la résolution du système triangulaire supérieur unitaire
            SysTriangSupUnite sysSupUnite = new SysTriangSupUnite(matriceTriangSupUnite, secondMembreSup);
            Vecteur solutionX = sysSupUnite.resolution();
            System.out.println("Solution du système triangulaire supérieur unitaire : \n" + solutionX);

            // Calcul de Ax - b
            Matrice Ax = Matrice.produit(matriceTriangSupUnite, (Matrice) solutionX);
            Matrice AxMoinsB = Matrice.soustraction(Ax, secondMembreSup);

            double L1_norme = (new Vecteur(AxMoinsB)).L1_norme();
            double L2_norme = (new Vecteur(AxMoinsB)).L2_norme();
            double Linf_norme = (new Vecteur(AxMoinsB)).Linf_norme();


            if (L1_norme < Matrice.numerical_epsilon) {
                System.out.println("Test réussi, L1_norme de (Ax - b) est suffisamment petite.");
            } else {
                System.out.println("Test échoué, L1_norme de (Ax - b) est trop grande.");
            }

            if (L2_norme < Matrice.numerical_epsilon) {
                System.out.println("Test réussi, L2_norme de (Ax - b) est suffisamment petite.");
            } else {
                System.out.println("Test échoué, L2_norme de (Ax - b) est trop grande.");
            }

            if (Linf_norme < Matrice.numerical_epsilon) {
                System.out.println("Test réussi, Linf_norme de (Ax - b) est suffisamment petite.");
            } else {
                System.out.println("Test échoué, Linf_norme de (Ax - b) est trop grande.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
