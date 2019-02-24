public class ArbreFichiers {
    private ArbreFichiers pere ;
    private ArbreFichiers premierFils; // File le plus a gauche Fichiers et dossier triés par ordre alphabétique
    private ArbreFichiers frereGauche;
    private ArbreFichiers frereDroit;
    private String nom; //sans espace
    private boolean fichier ; //true c'est un fichier, false c'est un dossier
    private String contenuFichier ; //Null si c'est un dossier
    private int taille; //Pour un fichier : le nombre de caracteres dans le fichier
                        //Pour un dossier : somme de tous les elements qu'il contient

    public ArbreFichiers(){
        pere=null;
        nom="";
        fichier=false;
        contenuFichier=null;
        taille=0;
        premierFils=null;
        frereDroit=null;
        frereGauche=null;
    }

    /**
     * Methode qui ajoute un fils n2 à l'arbre et mets la taille,le fils et le freres  à jour
     * @param n2 est le fichier, dossier ou bout d'arborescence à ajouter
     * @throws NullPointerException si n2 est null
     */
    public void ajouterFils(ArbreFichiers n2){ //METHODE 1
        n2.setPere(this);
        if (this.premierFils==null){ //si il n y a pas de fils alors n2 devient le premierFils
            this.premierFils=n2;
        }else {
            ArbreFichiers n4=this.premierFils; //n4 est le fichie ou dossier comparé, il commence au premier et termine au dernier
            boolean rester=true;
            ArbreFichiers n3; //n3 est le frere gauche de n4 s'il existe
            while (rester){
                if (n4.getNom().compareToIgnoreCase(n2.getNom())>0){   //Si le nom de n4 est plus grand que celui de n2 alors
                    n3=n4.getFrereGauche(); // n3 est le frere gauche de n4
                    if (n3!=null) // s'il n'existe pas alors on ne le met pas à jour, celle signidie que n4 est le premier fils donc n2 devient le premier fils
                        n3.setFrereDroit(n2);
                    n4.setFrereGauche(n2); // le frere gauche de n4 devient n2
                    n2.setFrereDroit(n4); // et le frere droit de n2 devient n4
                    n2.setFrereGauche(n3); // le frere gauche de n2 devient n3, si n3 n'existe pas, alors
                    if (this.premierFils==n4){ // Si n4 est le premier fils alors c'est n2 qui le devient
                        this.premierFils=n2;
                    }
                    rester=false; // on a fini de placer n4
                }
                if (n4.getFrereDroit()==null){ //Si on a parcouru tous les fils et qu'on a toujours pas placés n2, alors n2 est le dernier fils
                    n4.setFrereDroit(n2); // le frere droit de n4 est n2
                    n2.setFrereGauche(n4); // le frere gauche de n2 est n4
                    n2.setFrereDroit(null); // n2 n'a pas de frere droit vu qu'il est le dernier fils
                    rester=false; //on a fini de placer n4
                }else
                n4=n4.getFrereDroit(); // Si on n'a pas placé n2 alors n4 devient le frere de n4
            }
        }
        ArbreFichiers a=this;

        while (a.getPere()!=null){
            a.setTaille(n2.getTaille()); //Augmentation de la taille du noeud
            a=a.getPere();
        }
        a.setTaille(n2.getTaille()); //augmentation de la taille de la racine

    }

    private ArbreFichiers getFrereGauche() {
        return frereGauche;
    }

    private ArbreFichiers getFrereDroit() {
        return frereDroit;
    }

    private String getNom() {
        return nom;
    }


    private ArbreFichiers getPere() {
        return pere;
    }

    public boolean isFichier() {
        return fichier;
    }

    public String getContenuFichier() {
        return contenuFichier;
    }

    private int getTaille() {
        return taille;
    }

    private void setPere(ArbreFichiers pere) {
        this.pere = pere;
    }

    private void setFrereDroit(ArbreFichiers frereDroit) {
        this.frereDroit = frereDroit;
    }

    private void setFrereGauche(ArbreFichiers frereGauche) {
        this.frereGauche = frereGauche;
    }

    private void setTaille(int taille) {
        this.taille = taille;
    }
}
