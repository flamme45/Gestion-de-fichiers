package etape3.commandes;

import etape1.AbstractArbreFichiers;

public class CommandePwd implements CommandeEffectuer {
    @Override
    public AbstractArbreFichiers effectuerOperation(AbstractArbreFichiers a, String [] s){
        if (s.length==1) {
            System.out.println(a.cheminAbsolu() + "\n");
        }else
            System.out.println("Nombre d'arguments incorrects \n");
        return a;
    }
}
