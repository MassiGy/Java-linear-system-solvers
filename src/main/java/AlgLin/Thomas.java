package AlgLin;

import Abstracts.SysLinAbstract;
import Exceptions.IrregularSysLinException;

public class Thomas extends SysLinAbstract {

    public Thomas(Matrice matrice, Vecteur vect) throws IrregularSysLinException {
        super(matrice, vect);

        // s'assurer que la matrice carrée passé est bien un matrice tridiagonal;
        if(!Thomas.assertMatrice3Diag(matrice)){
            throw new IrregularSysLinException("Matrice passée en parametres est non triDiag");
        }
    }

    protected static boolean assertMatrice3Diag(Matrice cible) {
        for (int i = 0; i < cible.nbLigne(); i++) {
            for (int j = 0; j < cible.nbColonne(); j++) {
                // verifier que les coeff de la sous diag sont à 0
                if(i > j + 1 && cible.getCoef(i,j) != 0) return false;

                // verifier que les coeff de la sur diag sont à 0
                if(i < j - 1 && cible.getCoef(i,j) != 0) return false;
            }
        }
        return true;
    }


    @Override
    public Vecteur resolution() throws IrregularSysLinException {
        // en utilisant la méthode vu dans le td 2

        // on a p1 et q1
        /*
            p1 = −c1/b1
            q1 = d1/b1
         */
        // on a la formule de p(k+1) et q(k+1)
        /*
            pk+1 = − ck+1 / (ak+1 * pk + bk+1)
            qk+1 = dk+1 − ak+1 * qk / ( ak+1 * pk + bk+1 )
         */

        // on a la formule de x(k+1) en fonction de p(k), x(k+1) et q(k)




        // pour faire la resolution, on va créer un tableau des q et des p
        // on les calcule partant de q1 et p1, jusqu'a qn-1 et pn-1
        // une fois que cela est fait, on utilisera qn-1 et pn-1 pour trouver xn
        // une fois que xn est calculé, on utilisera la formule de la méthode de
        // thomas pour remonter et résoudre xn-1, xn-2,....,x0
        /*
            la formule est : xk = pk * xk+1 + qk
         */

        // et on placera les xk dans le vecteur resultat



        // init le vect solution
        Vecteur solution = new Vecteur(this.secondMembre.nbLigne());


        // calculer q1 et p1
        // p1 = −c1/b1
        double b1 = matriceSystem.getCoef(0,0);
        double c1 = matriceSystem.getCoef(0,1);
        if(b1 == 0)
            throw new IrregularSysLinException("Division par zero");

        double p1 = -1 * c1/ b1;


        // q1 = d1/b1
        double d1 = secondMembre.getCoef(0);
        double q1 = d1 / b1;


        // setup les tableau pour les qn et les pn
        double[] Qs = new double[matriceSystem.nbColonne()-1];
        double[] Ps = new double[matriceSystem.nbColonne()-1];

        // set la premiere valeurs pour ces tableau
        Qs[0] = q1;
        Ps[0] = p1;

        // pour la suite, on va extraire de la matrice triDiag le tab
        // des Cs et des Bs et des As
        double[] Cs = matriceSystem.coefficient[0];      // sur diagonale
        double[] Bs = matriceSystem.coefficient[1];      // diagonale
        double[] As = matriceSystem.coefficient[2];      // sous diagonale
        double[] Ds = secondMembre.coefficient[0];       // juste pour suivre la notation de la formule

        double denum = 1;
        double numerateur =1;


        // calculer qk+1, et pk+1 à partir de p1 et q1
        for (int i = 1; i < matriceSystem.nbColonne()-1; i++) {
        /*
            pk+1 = − ck+1 / (ak+1 * pk + bk+1)
            qk+1 = dk+1 − ak+1 * qk / ( ak+1 * pk + bk+1 )
         */

            numerateur = -1 * Cs[i];
            denum = As[i] * Ps[i-1] + Bs[i];

            if (denum == 0 )
                throw new IrregularSysLinException("Division par 0");

            Ps[i] = numerateur/denum;


            numerateur = Ds[i] - As[i] * Qs[i-1];
            denum = As[i] * Ps[i-1] + Bs[i];


            if (denum == 0 )
                throw new IrregularSysLinException("Division par 0");


            Qs[i] = numerateur / denum ;


        }

        // maintenant qu'on a tout les Qn et Pn, calculant xn
        /*
            xn = dn − an * qn−1 /( an * pn−1 + bn )
            si an * pn−1 + bn != 0
         */

        int n = Bs.length;      // n est la dim de la triDiag ( donc la long de la diag)

        if (As[n - 1] * Ps[n - 1 - 1] + Bs[n - 1] == 0)
            throw new IrregularSysLinException("Division par 0");

        // set le tableau pour les Xn
        double[] Xs = solution.coefficient[0];      // les xn,xn-1...,x0 sont les coeff du vect solution

        numerateur = Ds[n-1] - As[n-1] * Qs[n-1-1];
        denum = As[n-1] * Ps[n-1-1] + Bs[n-1];


        Xs[n-1] = numerateur /denum;


        // parcourir le tableau de la fin vers le debut et calculer
        for (int i = n-1-1; i >= 0; i++) {
            /*
                xk = pk * xk+1 + qk , 1 ≤ k ≤ n − 1
             */
            Xs[i] = Ps[i] * Xs[i+1] + Qs[i];
        }


        // maintenat que les coeff du vect solution sont calculés
        // retourner le vecteur solution
        return solution;
    }


    public static void main(String[] args) {
        // @Todo: add tests

    }






}
