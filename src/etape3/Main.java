package etape3;
import etape1.AbstractArbreFichiers;
import etape1.ArbreFichierDossier;
import etape2.ILecteurArbreFichier;
import etape2.LecteurArbreFichier1;
import etape2.exceptions.FichierCorrompuException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        if (args.length ==1) {
            ProgrammePrincipal(args[0]);
        }else if (args.length==0)
            ProgrammePrincipal();
        else
            System.out.println("Nombre d'arguments incorrects");
            }

    /**
     * Function qui lance la saisie interactive en partant d un fichier texte
     * @param nomFichier est le nom du fichier texte
     */
    private static void ProgrammePrincipal(String nomFichier) {
        try {
        ILecteurArbreFichier l = new LecteurArbreFichier1();
            saisieInteractive(l.lireFichier(nomFichier));
        }catch (FichierCorrompuException e){
            System.out.println(e.getMessage());
        }

    }

    /**
     * Function qui est appelee si on ne passe pas d arguments a programmePrincipal
     */
    private static void ProgrammePrincipal(){
        AbstractArbreFichiers a = new ArbreFichierDossier();
        saisieInteractive(a);
    }

    /**
     *Fonction qui s'occupe de la saisie dans le temrinal
     * @param a est l'arborescence avec laquelle on commence
     */
    private static void saisieInteractive(AbstractArbreFichiers a){
        AbstractArbreFichiers dossierCourant =a;
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
