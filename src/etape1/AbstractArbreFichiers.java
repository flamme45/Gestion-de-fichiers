package etape1;

public abstract class AbstractArbreFichiers {
    private ArbreFichiers pere ;
    private ArbreFichiers premierFils; // File le plus a gauche Fichiers et dossier triés par ordre alphabétique
    private ArbreFichiers frereGauche;
    private ArbreFichiers frereDroit;
    private String nom; //sans espace
    private boolean fichier ; //true c'est un fichier, false c'est un dossier
    private String contenuFichier ; //null si c'est un dossier
    private int taille; //Pour un fichier : le nombre de caracteres dans le fichier
    //Pour un dossier : somme de tous les elements qu'il contient

    public ArbreFichiers getPere() {
        return pere;
    }

    public void setPere(ArbreFichiers pere) {
        this.pere = pere;
    }

    public ArbreFichiers getPremierFils() {
        return premierFils;
    }

    public void setPremierFils(ArbreFichiers premierFils) {
        this.premierFils = premierFils;
    }

    public ArbreFichiers getFrereGauche() {
        return frereGauche;
    }

    public void setFrereGauche(ArbreFichiers frereGauche) {
        this.frereGauche = frereGauche;
    }

    public ArbreFichiers getFrereDroit() {
        return frereDroit;
    }

    public void setFrereDroit(ArbreFichiers frereDroit) {
        this.frereDroit = frereDroit;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public boolean isFichier() {
        return fichier;
    }

    public void setFichier(boolean fichier) {
        this.fichier = fichier;
    }

    public String getContenuFichier() {
        return contenuFichier;
    }

    public void setContenuFichier(String contenuFichier) {
        this.contenuFichier = contenuFichier;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }
}
