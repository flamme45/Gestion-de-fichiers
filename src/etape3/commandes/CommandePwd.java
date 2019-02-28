package etape3.commandes;

import etape1.ArbreFichiers;

public class CommandePwd implements CommandeEffectuer {
    @Override
    public ArbreFichiers effectuerOperation(ArbreFichiers a, String [] s){
        if (s.length==1) {
            System.out.println(a.cheminAbsolu() + "\n");
        }else
            System.out.println("Nombre d'arguments incorrects \n");
        return a;
    }
}
