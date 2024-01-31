package AlgLin;

import Abstracts.SysLinAbstract;
import Exceptions.IrregularSysLinException;

public class SysTriangInf extends SysLinAbstract {

    public SysTriangInf(Matrice matrice, Vecteur vect) throws IrregularSysLinException {
        super(matrice, vect);
    }

    @Override
    public Vecteur resolution() throws IrregularSysLinException {
        Vecteur res = new Vecteur(this.secondMembre.nbLigne());
        double ithCoefVal = 0;
        double accumulator = 0;
        try {

            for (int i = 0; i < this.secondMembre.nbLigne(); i++) {
                accumulator = 0;
                for (int j = 0; j < i; j++) {
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
            // Création d'une matrice triangulaire inférieure valide
            double[][] coefficients1 = {{3, 0, 0}, {7, 2, 0}, {5, 10, 3}};
            Matrice matriceTriangInf1 = new Matrice(coefficients1);
            Vecteur secondMembre1 = new Vecteur(new double[]{1, 3, 2});

            // Test de la résolution du système
            SysTriangInf sysTriangInf1 = new SysTriangInf(matriceTriangInf1, secondMembre1);
            Vecteur solution1 = sysTriangInf1.resolution();
            System.out.println("Solution du système triangulaire inférieur :\n" + solution1);

            // Calcul de Ax - b
            Matrice Ax1 = Matrice.produit(matriceTriangInf1, (Matrice)solution1);
            Matrice AxMoinsB1 = Matrice.soustraction(Ax1, secondMembre1);

            double L1_norme1 = (new Vecteur(AxMoinsB1)).L1_norme();
            double L2_norme1 = (new Vecteur(AxMoinsB1)).L2_norme();
            double Linf_norme1 = (new Vecteur(AxMoinsB1)).Linf_norme();

            // Vérification si les normes sont inférieures à l'epsilon numérique

            if (L1_norme1 < Matrice.numerical_epsilon) {
                System.out.println("Test réussi, L1_norme de (Ax - b) est suffisamment petite.");
            } else {
                System.out.println("L1_norme1: "+ L1_norme1);
                System.out.println("Test échoué, L1_norme de (Ax - b) est trop grande.");
            }

            if (L2_norme1 < Matrice.numerical_epsilon) {
                System.out.println("Test réussi, L2_norme de (Ax - b) est suffisamment petite.");
            } else {
                System.out.println("L2_norme1: "+ L2_norme1);
                System.out.println("Test échoué, L2_norme de (Ax - b) est trop grande.");
            }

            if (Linf_norme1 < Matrice.numerical_epsilon) {
                System.out.println("Test réussi, Linf_norme de (Ax - b) est suffisamment petite.");
            } else {
                System.out.println("Linf_norme1: "+ Linf_norme1);
                System.out.println("Test échoué, Linf_norme de (Ax - b) est trop grande.");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
