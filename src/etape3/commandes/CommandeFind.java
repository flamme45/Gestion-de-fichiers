package etape3.commandes;

import etape1.AbstractArbreFichiers;


public class CommandeFind implements CommandeEffectuer {
    public AbstractArbreFichiers effectuerOperation(AbstractArbreFichiers a, String[] s) {
        if (s.length==1 || s.length==2){
            if (s.length==1)
                System.out.println(a.trouver(a));
            else {
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
