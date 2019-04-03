package etape3.commandes;

import etape1.AbstractArbreFichiers;


public class CommandeFind implements CommandeEffectuer {
    public AbstractArbreFichiers effectuerOperation(AbstractArbreFichiers a, String[] s) {
        if (s.length==1 || s.length==2 || s.length==3){
            if (s.length==1)
                System.out.println(a.trouver(a));
            else if (s.length==3){
                    if (s[1].equals("-name")) {
                    System.out.println(a.trouverUnFichier(a, s[2]));
                }else
                    System.out.println("Arguments incorrects");
            }else {
                AbstractArbreFichiers b = a.getArbre(s[1]);
                if (s[1].equals(".")) {
                    System.out.println(a.trouver(a));
                } else {
                    if (b != null)
                        System.out.println(a.trouver(b));
                    else
                        System.out.println(s[1] + " n'est pas un dossier de ce repertoire \n");
                }
            }
        }else
            System.out.println("Nombre d'arguments incorrects \n");
        return  a;
    }
}
