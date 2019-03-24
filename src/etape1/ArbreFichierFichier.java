package etape1;

public class ArbreFichierFichier extends  AbstractArbreFichiers {

    public ArbreFichierFichier() {
        throw new IllegalCallerException("Impossible de creer un fichier sans parametres");
    }

    public ArbreFichierFichier(String nom, String contenuFichier) {

        super(nom, true, contenuFichier.replaceAll("___", "\n"), contenuFichier.length());
    }

    public void ajouterFils(AbstractArbreFichiers noeudAjout) {
        throw new IllegalCallerException("Impossible d'ajouter un fils a un fichier");
    }

    public void supprimerFils(AbstractArbreFichiers noeudSuppr) {
        throw new IllegalCallerException("Impossible de supprimer un fils d'un fichier");
    }

    public String infoNoeud() {
        throw new IllegalCallerException("Un fichier n'est pas un noueud");
    }

    public String cheminAbsolu() {
        throw new IllegalCallerException("Pas de chemin absolu sur un fichier");
    }

    public AbstractArbreFichiers seDirigerVers(String s) {
        throw new IllegalCallerException("Pas possible de se diriger depuis un fichier");
    }

    public boolean peutSeDirigerVers(String s) {
        throw new IllegalCallerException("Impossible d'essayer de se diriger depuis un fichier");
    }
}
