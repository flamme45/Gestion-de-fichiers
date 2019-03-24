package etape3.commandes;

import etape1.AbstractArbreFichiers;
import etape1.ArbreFichierDossier;

public class CommandeFind implements CommandeEffectuer {
    public AbstractArbreFichiers effectuerOperation(AbstractArbreFichiers a, String[] s) {
        return new ArbreFichierDossier("lala");
    }
}
