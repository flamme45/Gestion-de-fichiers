package etape3.commandes;

import etape1.ArbreFichiers;

public class CommandeMkdir implements CommandeEffectuer {
    @Override
    public ArbreFichiers effectuerOperation(ArbreFichiers a, String [] s){
        if (s.length==2)
            if (a.getArbre(s[1])==null)
                a.ajouterFils(new ArbreFichiers(s[1],false,null));
            else
                System.out.println("Un fichier ou dossier possede déjà ce nom \n");
        else
            System.out.println("Nombre d'arguments incorrects \n");
        return a;

    }
}
