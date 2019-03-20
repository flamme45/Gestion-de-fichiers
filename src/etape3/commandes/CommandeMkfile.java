package etape3.commandes;

import etape1.ArbreFichiers;

import java.util.Scanner;

public class CommandeMkfile implements CommandeEffectuer {
    @Override
    public ArbreFichiers effectuerOperation(ArbreFichiers a,String [] s){
        if (s.length>=2) {
            for (int i=1;i<s.length;i++) {
                if (a.getArbre(s[i]) == null) {
                    String contenu = "";
                    Scanner sc = new Scanner(System.in);
                    System.out.println(" Contenu du fichier "+s[i]+" ?");
                    if (sc.hasNextLine()) {
                        contenu = sc.nextLine();
                    }
                    a.ajouterFils(new ArbreFichiers(s[i], true, contenu));

                } else
                    System.out.println("Dossier non créé, le nom "+s[i]+" existe déjà\n");
            }
            return a;
        }else
            System.out.println("Nombre d'arguments incorrects \n");
        return a;
    }
}
