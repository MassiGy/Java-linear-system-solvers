package Exceptions;

/*
Héritant de Exception, elle mettra en œuvre une méthode toString qui renverra le message
précisant que le système est irrégulier. Cette exception sera déclanchée lorsque l'on détecte un
système irrégulier au cours de sa résolution.
*/

public class IrregularSysLinException extends  Exception{
    private String msg;
    public IrregularSysLinException(String msg){
        super(msg);
    }
    public String toString(){
        return "Système irrégulier\n";
    }
}
