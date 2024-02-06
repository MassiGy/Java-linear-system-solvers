package AlgLin;
import java.util.Scanner;

public class HilbertMatrice extends Matrice {

    public HilbertMatrice(int ordre){
        super(ordre, ordre);
        for (int i = 0 ; i < ordre ; i++){
            for (int j = 0 ; j < ordre ; j++){
                coefficient[i][j] = 1 / (i + j + 1.0); // au lieu de 1 / (i + j - 1) car les coefficients commencent a 0
            }
        }
    }

    private static boolean estEntierPositif(String str) {
        if (str == null) {
            return false;
        }
        try {
            int i = Integer.parseInt(str);
            if (i <= 0) return false;
            return true;
        } catch (NumberFormatException e) {
            // La chaîne n'est pas un entier
            return false;
        }
    }

    public static void main(String[] args) {

        /*
        Scanner sc = new Scanner(System.in);
        String res = "";
        while (res.length() < 1 || !estEntierPositif(res)){
            System.out.println("Entrez l'ordre de la matrice:");
            res = sc.next();
        }
        sc.close();

        int ordre = Integer.parseInt(res);
        HilbertMatrice hm = new HilbertMatrice(ordre);
        HilbertMatrice hmCopie = new HilbertMatrice(ordre);

        System.out.println("Vous avez entre: " + ordre);

        try{

            // calc de l'inverse de hm
            Matrice inverseHm = hm.inverse();

            System.out.println("Inverse de la matrice de Hilbert d'ordre " + ordre + ": \n" + inverseHm);
            System.out.println("Son conditionnement avec L1: " + hmCopie.calcConditionnement((m) -> Matrice.L1_norme(m)));
            System.out.println("Son conditionnement avec Linf: " + hmCopie.calcConditionnement((m) -> Matrice.Linf_norme(m)));

            // calc du produit entre la matrice (copie) et son inverse
            Matrice produitHmEtSonInverse = Matrice.produit(hmCopie, inverseHm);

            Matrice resultatSoustractionId = Matrice.soustraction(produitHmEtSonInverse, Matrice.getIdentite(ordre));

            double L1_norme = resultatSoustractionId.L1_norme();
            double Linf_norme = resultatSoustractionId.Linf_norme();


            if (L1_norme < Matrice.numerical_epsilon) {
                System.out.println("Test réussi, norme_1(AA^-1 - I) est suffisamment petite: " + L1_norme);
            } else {
                System.out.println("Test échoué, norme_1(AA^-1 - I) est trop grande: " + L1_norme);
            }

            if (Linf_norme < Matrice.numerical_epsilon) {
                System.out.println("Test réussi, norme_inf(AA^-1 - I) est suffisamment petite: " + Linf_norme);
            } else {
                System.out.println("Test échoué, norme_inf(AA^-1 - I) est trop grande: " + Linf_norme);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        */


        for (int i = 3; i <= 15; i++) {

            System.out.printf("\n################# Ordre = %d ##############\n",i);

            int ordre = i;
            HilbertMatrice hm = new HilbertMatrice(ordre);
            HilbertMatrice hmCopie = new HilbertMatrice(ordre);


            try{

                // calc de l'inverse de hm
                Matrice inverseHm = hm.inverse();

                System.out.println("Son conditionnement avec L1: " + hmCopie.calcConditionnement((m) -> Matrice.L1_norme(m)));
                System.out.println("Son conditionnement avec Linf: " + hmCopie.calcConditionnement((m) -> Matrice.Linf_norme(m)));

                // calc du produit entre la matrice (copie) et son inverse
                Matrice produitHmEtSonInverse = Matrice.produit(hmCopie, inverseHm);

                Matrice resultatSoustractionId = Matrice.soustraction(produitHmEtSonInverse, Matrice.getIdentite(ordre));

                double L1_norme = resultatSoustractionId.L1_norme();
                double Linf_norme = resultatSoustractionId.Linf_norme();


                if (L1_norme < Matrice.numerical_epsilon) {
                    System.out.println("Test réussi, norme_1(AA^-1 - I) est suffisamment petite: " + L1_norme);
                } else {
                    System.out.println("Test échoué, norme_1(AA^-1 - I) est trop grande: " + L1_norme);
                }

                if (Linf_norme < Matrice.numerical_epsilon) {
                    System.out.println("Test réussi, norme_inf(AA^-1 - I) est suffisamment petite: " + Linf_norme);
                } else {
                    System.out.println("Test échoué, norme_inf(AA^-1 - I) est trop grande: " + Linf_norme);
                }

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

}


