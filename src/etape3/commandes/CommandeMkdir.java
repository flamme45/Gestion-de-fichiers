package etape3.commandes;

import etape1.AbstractArbreFichiers;
import etape1.ArbreFichierDossier;

public class CommandeMkdir implements CommandeEffectuer {
    @Override
    public AbstractArbreFichiers effectuerOperation(AbstractArbreFichiers a, String [] s){
        if (s.length>=2)
            for (int i=1;i<s.length;i++) {
                if (a.getArbre(s[i]) == null)
                    a.ajouterFils(new ArbreFichierDossier(s[i]));
                else
                    System.out.println("Dossier non créé, le nom "+s[i]+" existe déjà\n");
            }
        else
            System.out.println("Nombre d'arguments incorrects \n");
        return a;

    }
}
