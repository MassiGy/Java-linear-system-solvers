package AlgLin;

import Abstracts.SysLinAbstract;
import Exceptions.IrregularSysLinException;

public class SysDiagonal extends SysLinAbstract {

    public SysDiagonal(Matrice matrice, Vecteur vect) throws IrregularSysLinException {
        super(matrice, vect);
    }

    @Override
    public Vecteur resolution() throws IrregularSysLinException {
        Vecteur res = new Vecteur(this.secondMembre.nbLigne());
        double ithCoefVal;

        for (int i = 0; i < this.secondMembre.nbLigne(); i++) {
            ithCoefVal = this.secondMembre.getCoef(i) / this.matriceSystem.getCoef(i,i);
            res.remplacecoef(i, ithCoefVal);
        }

        return res;
    }



    public static void main(String[] args) {

        try {
            double[][] systeme1 = {
                    {3, 0, 0, 0},
                    {0, 7, 0, 0},
                    {0, 0, -2, 0},
                    {0, 0, 0, -5}
            };
            double[] vect = {
                    1,
                    7,
                    -6,
                    8
            };

            Matrice matrice1 = new Matrice(systeme1);
            Vecteur secondMembre = new Vecteur(vect);

            assert matrice1 != null;
            assert secondMembre != null;
            System.out.println(matrice1);
            System.out.println(secondMembre);

            SysDiagonal sysDiagonal1 = new SysDiagonal(matrice1, secondMembre);
            Vecteur solution1 = sysDiagonal1.resolution();

            System.out.println("Solution du système diagonal :\n" + solution1);

            // Calcul de Ax - b
            Matrice Ax = Matrice.produit(matrice1, (Matrice) solution1);
            Matrice AxMoinsB = Matrice.soustraction(Ax, secondMembre);

            double L1_norme = new Vecteur(AxMoinsB).L1_norme();
            double L2_norme = new Vecteur(AxMoinsB).L2_norme();
            double Linf_norme =new Vecteur(AxMoinsB).Linf_norme();

            // Vérification si les normes sont inférieures à l'epsilon numérique

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

        } catch (Exception e) { // IrregularSysLinException serait ok aussi mais moins général
            e.printStackTrace();
        }
    }
}
