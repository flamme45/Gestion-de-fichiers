package etape3.commandes;

import etape1.ArbreFichiers;

public class CommandeMkdir implements CommandeEffectuer {
    @Override
    public ArbreFichiers effectuerOperation(ArbreFichiers a, String [] s){
        if (s.length>=2)
            for (int i=1;i<s.length;i++) {
                if (a.getArbre(s[i]) == null)
                    a.ajouterFils(new ArbreFichiers(s[i], false, null));
                else
                    System.out.println("Dossier non créé, le nom "+s[i]+" existe déjà\n");
            }
        else
            System.out.println("Nombre d'arguments incorrects \n");
        return a;

    }
}
