package etape1;

import java.util.Objects;

public abstract class AbstractArbreFichiers {
    protected AbstractArbreFichiers pere;
    protected AbstractArbreFichiers premierFils; // File le plus a gauche Fichiers et dossier triés par ordre alphabétique
    protected AbstractArbreFichiers frereGauche;
    protected AbstractArbreFichiers frereDroit;
    protected String nom; //sans espace
    private final boolean fichier; //true c'est un fichier, false c'est un dossier
    protected String contenuFichier; //null si c'est un dossier
    protected int taille; //Pour un fichier : le nombre de caracteres dans le fichier
    //Pour un dossier : somme de tous les elements qu'il contient

    /**
     *
     * Constructeur qui crée la racine
     */
    AbstractArbreFichiers() {
        this.pere = null;
        nom = "";
        fichier = false;
        contenuFichier = null;
        taille = 0;
        premierFils = null;
        frereDroit = null;
        frereGauche = null;
    }

    /**
     * Constructeur qui crée un fichier ou dossier
     * @param nom est le nom
     * @param fichier un bool pour savoir si c est un fichier
     * @param contenuFichier le contentu du fichier
     * @param taille la taille du fichier
     */
    AbstractArbreFichiers(String nom, boolean fichier, String contenuFichier, int taille) {
        this.nom = nom;
        this.fichier = fichier;
        pere = null;
        premierFils = null;
        frereDroit = null;
        frereGauche = null;
        this.contenuFichier = contenuFichier;
        this.taille = taille;
    }


    public abstract void ajouterFils(AbstractArbreFichiers noeudAjout);

    public abstract void supprimerFils(AbstractArbreFichiers nouedSuppr);

    public abstract String infoNoeud();

    public abstract String cheminAbsolu();

    public abstract AbstractArbreFichiers seDirigerVers(String s);

    public abstract boolean peutSeDirigerVers(String s);



        /**
     * Methode qui retourne un arbrefichier en focntion du nom de celui-ci
     * @param nom le nom du fichier ou dossier cherche
     * @return le fichier ou dossier du nom de 'nom' et null si il n est pas trouve
     */
    public AbstractArbreFichiers getArbre(String nom){
        AbstractArbreFichiers a=this.premierFils;
        while (a!=null) {
            if (a.nom.equals(nom)) {
                return a;
            }
            a=a.frereDroit;
        }
        return null;
    }

    public abstract String lignesMatch(String s);


    public AbstractArbreFichiers getPere() {
        return pere;
    }

    public void setPere(AbstractArbreFichiers pere) {
        this.pere = pere;
    }

    public AbstractArbreFichiers getPremierFils() {
        return premierFils;
    }

    public void setPremierFils(AbstractArbreFichiers premierFils) {
        this.premierFils = premierFils;
    }

    public AbstractArbreFichiers getFrereGauche() {
        return frereGauche;
    }

    public void setFrereGauche(AbstractArbreFichiers frereGauche) {
        this.frereGauche = frereGauche;
    }

    public AbstractArbreFichiers getFrereDroit() {
        return frereDroit;
    }

    public void setFrereDroit(AbstractArbreFichiers frereDroit) {
        this.frereDroit = frereDroit;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public abstract String trouver(AbstractArbreFichiers a);

    public abstract String trouverUnFichier(AbstractArbreFichiers a,String f);

//    public boolean isFichier() {
//        return fichier;
//    }


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractArbreFichiers that = (AbstractArbreFichiers) o;
        return fichier == that.fichier &&
                taille == that.taille &&
                Objects.equals(pere, that.pere) &&
                Objects.equals(premierFils, that.premierFils) &&
                Objects.equals(frereGauche, that.frereGauche) &&
                Objects.equals(frereDroit, that.frereDroit) &&
                nom.equals(that.nom) &&
                contenuFichier.equals(that.contenuFichier);
    }
}