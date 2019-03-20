package etape1;

public class ArbreFichierFichier extends  AbstractArbreFichiers {

    public ArbreFichierFichier(){
        throw new IllegalCallerException("Impossible de creer un fichier sans parametres");
    }

    public ArbreFichierFichier(String nom, boolean fichier,String contenuFichier){
        super(nom,true,contenuFichier.replaceAll("___","\n"));
    }
        this.nom =nom;
        this.fichier=fichier;
        pere=null;
        premierFils=null;
        frereDroit=null;
        frereGauche=null;
        if (fichier){
            this.contenuFichier=contenuFichier.replaceAll("___","\n");
            taille=contenuFichier.length();
        }else {
            this.contenuFichier = null;
            taille = 0;
        }
}
