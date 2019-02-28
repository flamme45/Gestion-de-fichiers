package etape3.commandes;

import etape1.ArbreFichiers;

public class CommandeLess implements CommandeEffectuer {
    @Override
    public ArbreFichiers effectuerOperation(ArbreFichiers a, String [] s) {
        if (s.length==2) {
            if (a.getArbre(s[1])!=null && a.getArbre(s[1]).isFichier())
                System.out.println(a.getArbre(s[1]).getContenuFichier().replaceAll("___", "\n")+"\n");
            else
                System.out.println(s[1]+" n'est pas un fichier de ce repertoire \n");
        }else
            System.out.println("Nombre d'arguments incorrects \n");
        return a;
    }
}

