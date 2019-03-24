package etape3.commandes;

import etape1.AbstractArbreFichiers;


public interface CommandeEffectuer {
    AbstractArbreFichiers effectuerOperation(AbstractArbreFichiers a, String [] s);
}
