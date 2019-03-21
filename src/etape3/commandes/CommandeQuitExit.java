package etape3.commandes;

import etape1.AbstractArbreFichiers;

public class CommandeQuitExit implements CommandeEffectuer {
    @Override
    public AbstractArbreFichiers effectuerOperation(AbstractArbreFichiers a, String [] s){
        if (s.length==1) {
            System.exit(0);
            return null;
        }else {
            System.out.println("Nombre d'arguments incorrects \n");
            return a;
        }
    }
}
