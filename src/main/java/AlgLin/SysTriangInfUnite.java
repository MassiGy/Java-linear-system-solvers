package AlgLin;

import Abstracts.SysLinAbstract;
import Exceptions.IrregularSysLinException;

public class SysTriangInfUnite extends SysLinAbstract {
    public SysTriangInfUnite(Matrice matrice, Vecteur vect) throws IrregularSysLinException {
        super(matrice, vect);
    }

    @Override
    public Vecteur resolution() throws IrregularSysLinException {
        Vecteur res = new Vecteur(this.secondMembre.nbLigne());
        double ithCoefVal = 0;
        double accumulator = 0;

        for (int i = 0; i < this.secondMembre.nbLigne(); i++) {
            accumulator = 0;
            for (int j = 0; j < i; j++) {
                accumulator += this.matriceSystem.getCoef(i, j) * res.getCoef(j);
            }
            ithCoefVal = (this.secondMembre.getCoef(i) - accumulator); // dévisé par 1
            res.remplacecoef(i, ithCoefVal);
        }

        return res;
    }



    public static void main(String[] args) {

        try {
            double[][] coefficients = {{1, 0, 0}, {2, 1, 0}, {4, 5, 1}};
            Matrice matriceA = new Matrice(coefficients);
            Vecteur secondMembre = new Vecteur(new double[]{3, 5, 10});

            SysTriangInfUnite systeme = new SysTriangInfUnite(matriceA, secondMembre);
            Vecteur solutionX = systeme.resolution();
            System.out.println("Solution du système triangulaire supérieur unitaire : \n" + solutionX);


            // Calcul de Ax - b
            Matrice Ax = Matrice.produit(matriceA, (Matrice)solutionX);
            Matrice AxMoinsB = Matrice.soustraction(Ax, secondMembre);


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
