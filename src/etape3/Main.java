package etape3;
import etape1.ArbreFichiers;
import etape2.LecteurArbreFichier;
import etape2.exceptions.FichierCorrompuException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        String s = "";
        if (args.length >= 1) {
            for (int i = 0; i < args.length - 1; i++)
                s += args[i] + " ";

            s += args[args.length - 1];
            ProgrammePrincipal(s);
        }else
            ProgrammePrincipal();
            }

    /**
     * Function qui lance la saisie interactive en partant d un fichier texte
     * @param nomFichier est le nom du fichier texte
     */
    private static void ProgrammePrincipal(String nomFichier) {
        try {
        LecteurArbreFichier l = new LecteurArbreFichier(nomFichier);
            saisieInteractive(l.getRacine());
        }catch (FichierCorrompuException e){
            System.out.println(e.getMessage());
        }

    }

    /**
     * Function qui est appelee si on ne passe pas d arguments a programmePrincipal
     */
    private static void ProgrammePrincipal(){
        ArbreFichiers a = new ArbreFichiers();
        saisieInteractive(a);
    }

    /**
     *Fonction qui s'occupe de la saisie dans le temrinal
     * @param a est l'arborescence avec laquelle on commence
     */
    private static void saisieInteractive(ArbreFichiers a){
        ArbreFichiers dossierCourant =a;
        Commande c= new Commande();
        while (true) {
            c.afficherDemande();
            Scanner sc = new Scanner(System.in);
            if (sc.hasNextLine()){
                String s=sc.nextLine();
                String [] tab= s.split(" ");
                try {
                    c.setCommandeEffectuer(tab[0]);
                    dossierCourant=c.effectuerOperation(dossierCourant,tab);
                }catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
