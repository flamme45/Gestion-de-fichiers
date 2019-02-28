package etape3.commandes;

import etape1.ArbreFichiers;

public class CommandeCd implements CommandeEffectuer {
    @Override
    public ArbreFichiers effectuerOperation(ArbreFichiers a, String [] s){
        if (s.length==2) {
            if (a.peutSeDirigerVers(s[1]))
                return a.seDirigerVers(s[1]);
            else {
                System.out.println("Impossible de se diriger vers " + s[1]);
                return a;
            }
        }else
            System.out.println("Nombre d'arguments incorrects \n");
        return a;
    }

}
