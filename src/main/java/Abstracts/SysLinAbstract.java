package Abstracts;

/*
On souhaite écrire une classe abstraite permettant de définir un système linéaire en vue de sa
résolution. Cette classe contient :
• int private ordre : correspond à la taille du système (c'est à dire le nombre de lignes et/ou de
colonnes de la matrice et du second membre) ;
• private protected Matrice matriceSystem : correspond à la matrice du système ;
• private protected Vecteur secondMembre : correspond au second membre du système ;
• un constructeur possédant obligatoirement 2 paramètres qui correspondent à la matrice et
au second membre du système que l'on définit. Ce constructeur doit vérifier que la matrice
est carrée et de même taille que le second membre. Si cette vérification est mise en défaut,
une exception que l’on aura définit devra être levée ;
• public int getOrdre() est un accesseur en lecture de l'attribut ordre ;
• public Matrice getMatriceSystem() est un accesseur en lecture de l'attribut matriceSystem ;
• public Vecteur getSecondMembre() est un accesseur en lecture de l'attribut secondMembre ;
• Abstract public Vecteur resolution() : renvoie la résolution du système et est susceptible de
lever l'exception IrregularSysLinException. Cette méthode devra être définie dans toute
classe dérivée correspondant à des systèmes particuliers.


*/

import AlgLin.Matrice;
import AlgLin.Vecteur;
import Exceptions.IrregularSysLinException;

public abstract class SysLinAbstract {
    private int ordre;
    protected Matrice matriceSystem;
    protected Vecteur secondMembre;

    /*
    • un constructeur possédant obligatoirement 2 paramètres qui correspondent à la matrice et
    au second membre du système que l'on définit. Ce constructeur doit vérifier que la matrice
    est carrée et de même taille que le second membre. Si cette vérification est mise en défaut,
    une exception que l’on aura définit devra être levée ;
     */
    public SysLinAbstract(Matrice matrice, Vecteur vect) throws IrregularSysLinException {
        if (matrice.nbLigne() == matrice.nbColonne() && matrice.nbColonne() == vect.nbLigne()) {
            this.matriceSystem = matrice;
            this.secondMembre = vect;
            this.ordre = matrice.nbLigne();
        } else {
            throw new IrregularSysLinException("La matrice doit être carrée et de même taille que le second membre.");
        }
    }
    public int getOrdre(){
        return this.ordre;
    }
    public Matrice getMatriceSystem() {
        return this.matriceSystem;
    }
    public Vecteur getSecondMembre() {
        return this.secondMembre;
    }
    public abstract Vecteur resolution() throws IrregularSysLinException;
}
