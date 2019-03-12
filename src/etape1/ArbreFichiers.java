package etape1;

import etape1.exceptions.*;

/**
 * Methode qui gere les noeuds d une arborescence de fichiers et de dossiers
 */
public class ArbreFichiers {
    private ArbreFichiers pere ;
    private ArbreFichiers premierFils; // File le plus a gauche Fichiers et dossier triés par ordre alphabétique
    private ArbreFichiers frereGauche;
    private ArbreFichiers frereDroit;
    private String nom; //sans espace
    private boolean fichier ; //true c'est un fichier, false c'est un dossier
    private String contenuFichier ; //null si c'est un dossier
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

    public ArbreFichiers(String nom, boolean fichier,String contenuFichier){
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

    /**
     * Methode qui ajoute un fils noeudAjout à l'arbre et mets la taille,le fils et le freres  à jour
     * @param noeudAjout est le fichier, dossier ou bout d'arborescence à ajouter
     * @throws NullPointerException si noeudAjout est null
     * @throws CreationFilsException si on essaye d'ajouter un fils a un fichier
     */
    public void ajouterFils(ArbreFichiers noeudAjout){ //METHODE 1
        noeudAjout.pere=this;
        if (this.fichier){
            throw new CreationFilsException("Impossible de creer un fils dans un fichier");
        }
        if (this.premierFils==null){ //si il n y a pas de fils alors noeudAjout devient le premierFils
            this.premierFils=noeudAjout;
        }else {
            ArbreFichiers n4=this.premierFils; //n4 est le fichier ou dossier comparé, il commence au premier et termine au dernier
            boolean rester=true;
            ArbreFichiers n3; //n3 est le frere gauche de n4 s'il existe
            while (rester){
                if (n4.nom.compareToIgnoreCase(noeudAjout.nom)>0){   //Si le nom de n4 est plus grand que celui de noeudAjout alors
                    n3=n4.frereGauche; // n3 est le frere gauche de n4
                    if (n3!=null) // s'il n'existe pas alors on ne le met pas à jour, celle signidie que n4 est le premier fils donc noeudAjout devient le premier fils
                        n3.frereDroit=noeudAjout;
                    noeudAjout.frereDroit=n4;
                    noeudAjout.frereGauche=n3;
                    n4.frereGauche=noeudAjout;
                    if (this.premierFils==n4){ // Si n4 est le premier fils alors c'est noeudAjout qui le devient
                        this.premierFils=noeudAjout;
                    }
                    rester=false; // on a fini de placer n4
                }
                if (rester && n4.frereDroit==null){ //Si on a parcouru tous les fils et qu'on a toujours pas placés noeudAjout, alors noeudAjout est le dernier fils
                    n4.frereDroit=noeudAjout; // le frere droit de n4 est noeudAjout
                    noeudAjout.frereGauche=n4; // le frere gauche de noeudAjout est n4
                    noeudAjout.frereDroit=null; // noeudAjout n'a pas de frere droit vu qu'il est le dernier fils
                    rester=false; //on a fini de placer n4
                }else
                n4=n4.frereDroit; // Si on n'a pas placé noeudAjout alors n4 devient le frere de n4
            }
        }
        ArbreFichiers a=this;

        while (a.pere!=null){
            a.taille=(noeudAjout.taille+a.taille); //Augmentation de la taille du noeud
            a=a.pere;
        }
        a.taille=(noeudAjout.taille+a.taille); //augmentation de la taille de la racine
    }

    /**
     * Methode qui supprime un noeud
     * @param noeudSuppr est le fichier ou dossier à supprimer
     * @throws NullPointerException si noeudSuppr est null
     * @throws FilsInexistantException si noeudSuppr n'est pas un fils de l'objet appele
     */
    public void supprimerFils(ArbreFichiers noeudSuppr){ //methode 2 équivalent du rm
        ArbreFichiers b=this.premierFils;
        boolean bool=false;
        while(b!=null){
            if (b.equals(noeudSuppr)){
                bool=true;
                break;
            }
            b=b.frereDroit;
        }
        if (!bool){
            throw new FilsInexistantException(noeudSuppr.nom+" n'est pas un fils de l'objet appelé ");
        }
        if (this.premierFils.equals(noeudSuppr))
            premierFils=noeudSuppr.frereDroit;
        ArbreFichiers n3=noeudSuppr.frereDroit;
        ArbreFichiers n4 =noeudSuppr.frereGauche;
        if (n4!=null)
            n4.frereDroit=n3;
        if (n3!=null)
            n3.frereGauche=n4;

        ArbreFichiers a=noeudSuppr.pere;
        while (a.pere!=null){
            a.taille=(a.taille-noeudSuppr.taille);
            a=a.pere;
        }
        a.taille=(a.taille-noeudSuppr.taille);
    }

    /**
     * Methode qui retourne un String contenant toutes les informations du noeud
     * @return les information du noeud actuel
     */
    public String infoNoeud(){ //COMMANDE ls
        String s="";
        ArbreFichiers a=this.premierFils;
        if (a==null){
            return "";
        }else{
            while (a!=null){
                if (a.fichier)
                    s+="f";
                else
                    s+="d";
                s+=" "+a.nom +" "+a.taille+ "\n";
                a=a.frereDroit;
            }
        }
        return s;
    }

    /**
     * Methode qui retourne le chemin absulu de ce noeud vers la racine
     * @return un string contantn le chemin absolu entre la racile et le noeud actuel
     */
    public String cheminAbsolu(){ //commande pwd
        String s="";
        boolean f =true;
        if (!this.fichier) {
            s = this.nom;
            f=false;
        }
        ArbreFichiers a =this.pere;
        while (a!=null){
            if (f){
                s=a.nom;
                f=false;
            }else
                s=a.nom+"/"+s;
            a=a.pere;
        }
        if (s.equals(""))
            s="/";
        return s;
    }

    /**
     * Methode qui se dirige vers le chemin passe en parametre, utiliser peutSeDirigerVers() avant d'appeler cette méthode
     * @param s est la destination souhaitee, '..' pour revenir en arriere
     * @return le noeud de la destination souhaitee
     * @throws AccesImpossibleException si on fait '..' à la racine
     * @throws DossierIntrouvableException si le nom passe en paramètre ne correspond a aucun dossier du dossier actuel
     */
    public ArbreFichiers seDirigerVers(String s){
        if (s.equals("..")){
            if (this.nom.equals("")){
                throw new AccesImpossibleException("Chemin en arriere depuis la racine impossible");
            }else
                return this.pere;
        }else {
            ArbreFichiers a=this.premierFils;
            while (a!=null){
                if (a.nom.equals(s)&& !a.fichier)
                    return a;
                a=a.frereDroit;
            }
            throw new DossierIntrouvableException("Dossier non trouvé");
        }
    }

    /**
     * Methode a utiliser avant d utiliser seDirigerVers(String s)
     * @param s est le chemin que l'on souhaite se diriger
     * @return true si on peut s y diriger,false sinon
     */
    public boolean peutSeDirigerVers(String s){
        if (s.equals("..")) {
            return !this.nom.equals("");
        }else {
            ArbreFichiers a = this.premierFils;
            while (a != null) {
                if (a.nom.equals(s) && !a.fichier)
                    return true;
                a = a.frereDroit;
            }
        }
        return false ;
    }

    /**
     * Methode qui retourne un arbrefichier en focntion du nom de celui-ci
     * @param nom le nom du fichier ou dossier cherche
     * @return le fichier ou dossier du nom de 'nom' et null si il n est pas trouve
     */
    public ArbreFichiers getArbre(String nom){
        ArbreFichiers a=this.getPremierFils();
        while (a!=null) {
            if (a.nom.equals(nom)) {
                return a;
            }
            a=a.getFrereDroit();
        }
        return null;
    }

    @Override
    public String toString(){
        String s ="";
        if (this.fichier){
            s+="Fichier : "+this.nom+"\n";
        }else {
            if (this.nom.equals(""))
                s+="Dossier : root\n";
            else
                s += "Dossier : " + this.nom + "\n";
        }if (this.pere==null){
            s+="Pere : "+null +"\n";
        }else {
            if (this.pere.nom.equals(""))
                s += "Pere : root \n";
            else
                s += "Pere : " + this.pere.nom + " \n";
        }
        if (frereDroit==null){
            s+="Frere droit : "+null+"\n";
        }else
            s+="Frere droit : " + this.frereDroit.nom+"\n";
        if (this.frereGauche==null){
            s+="Frere gauche : " +null+"\n";
        }else
            s+="Frere gauche : " +this.frereGauche.nom +"\n";
        s+="Taille : " + this.taille +"\n";
        if (this.fichier){
            s+="Contenu : \""+ this.contenuFichier +"\"\n";
        }else {
            if (this.premierFils==null){
                s+="Dossier vide \n";
            }else {
                s += "Premier fils : " + this.premierFils.nom+ "\n";
                ArbreFichiers n2 = this.premierFils;
                int numeroFils = 1;
                s += "-----------------------------------------\n";
                while (n2 != null) {
                    if (n2.fichier)
                        s += "Fils n°" + numeroFils + " |fichier| : " + n2.nom + "\n";
                    else
                        s += "Fils n°" + numeroFils + " |dossier| : " + n2.nom + "\n";
                    n2 = n2.frereDroit;
                    numeroFils++;
                }
                s += "-----------------------------------------\n";
            }
        }
        return s;
    }


    public ArbreFichiers getPremierFils() {
        return premierFils;
    }

    public ArbreFichiers getFrereGauche() {
        return frereGauche;
    }

    public ArbreFichiers getFrereDroit() {
        return frereDroit;
    }

    public String getNom() {
        return nom;
    }


    public ArbreFichiers getPere() {
        return pere;
    }

    public boolean isFichier() {
        return fichier;
    }

    public String getContenuFichier() {
        return contenuFichier;
    }

    public int getTaille() {
        return taille;
    }

    public void setPere(ArbreFichiers pere) {
        this.pere = pere;
    }

    public void setFrereDroit(ArbreFichiers frereDroit) {
        this.frereDroit = frereDroit;
    }

    public void setFrereGauche(ArbreFichiers frereGauche) {
        this.frereGauche = frereGauche;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

}
