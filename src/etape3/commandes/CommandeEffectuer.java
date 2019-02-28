package etape3.commandes;

import etape1.ArbreFichiers;

public interface CommandeEffectuer {
    ArbreFichiers effectuerOperation(ArbreFichiers a,String [] s);
}
