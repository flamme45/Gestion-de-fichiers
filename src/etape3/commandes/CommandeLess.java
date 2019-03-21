package etape3.commandes;

import etape1.AbstractArbreFichiers;
import etape1.ArbreFichierFichier;

public class CommandeLess implements CommandeEffectuer {
    @Override
    public AbstractArbreFichiers effectuerOperation(AbstractArbreFichiers a, String [] s) {
        if (s.length==2) {
            if (a.getArbre(s[1])!=null && a.getArbre(s[1]) instanceof ArbreFichierFichier)
                System.out.println(a.getArbre(s[1]).getContenuFichier()+"\n");
            else
                System.out.println(s[1]+" n'est pas un fichier de ce repertoire \n");
        }else
            System.out.println("Nombre d'arguments incorrects \n");
        return a;
    }
}

