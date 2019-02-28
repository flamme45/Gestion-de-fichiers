package etape3.commandes;

import etape1.ArbreFichiers;

import java.util.Scanner;

public class CommandeMkfile implements CommandeEffectuer {
    @Override
    public ArbreFichiers effectuerOperation(ArbreFichiers a,String [] s){
        if (s.length==2) {
            if (a.getArbre(s[1])==null) {
                String contenu = "";
                Scanner sc = new Scanner(System.in);
                System.out.println(" Contenu du fichier ? ");
                if (sc.hasNextLine()) {
                    contenu = sc.nextLine();
                }
                a.ajouterFils(new ArbreFichiers(s[1], true, contenu));
                return a;
            }else
                System.out.println("Un fichier ou dossier possede déjà ce nom \n");
        }else
            System.out.println("Nombre d'arguments incorrects \n");
        return a;
    }
}
