package etape3.commandes;

import etape1.ArbreFichiers;

public class CommandeQuitExit implements CommandeEffectuer {
    @Override
    public ArbreFichiers effectuerOperation(ArbreFichiers a, String [] s){
        if (s.length==1) {
            System.exit(0);
            return null;
        }else {
            System.out.println("Nombre d'arguments incorrects \n");
            return a;
        }
    }
}
