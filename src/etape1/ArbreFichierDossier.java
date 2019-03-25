package etape1;

import etape1.exceptions.AccesImpossibleException;
import etape1.exceptions.CreationFilsException;
import etape1.exceptions.DossierIntrouvableException;
import etape1.exceptions.FilsInexistantException;

public class ArbreFichierDossier extends  AbstractArbreFichiers {

    public ArbreFichierDossier(){
        super();
    }

    public ArbreFichierDossier(String nom){
        super(nom,false,null,0);
    }

    /**
     * Methode qui ajoute un fils noeudAjout à l'arbre et mets la taille,le fils et le freres  à jour
     * @param noeudAjout est le fichier, dossier ou bout d'arborescence à ajouter
     * @throws NullPointerException si noeudAjout est null
     * @throws CreationFilsException si on essaye d'ajouter un fils a un fichier
     */
    public void ajouterFils(AbstractArbreFichiers noeudAjout){ //METHODE 1
        noeudAjout.pere=this;
//        if (this.fichier){
//            throw new CreationFilsException("Impossible de creer un fils dans un fichier");
//        }
        if (this.premierFils==null){ //si il n y a pas de fils alors noeudAjout devient le premierFils
            this.premierFils=noeudAjout;
        }else {
            AbstractArbreFichiers n4=this.premierFils; //n4 est le fichier ou dossier comparé, il commence au premier et termine au dernier
            boolean rester=true;
            AbstractArbreFichiers n3; //n3 est le frere gauche de n4 s'il existe
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
        AbstractArbreFichiers a=this;

        while (a.pere!=null){
            a.taille=(noeudAjout.taille+a.taille); //Augmentation de la taille du noeud
            a=a.pere;
        }
        a.taille=(noeudAjout.taille+a.taille); //augmentation de la taille de la racine
    }

    /**
     //     * Methode qui supprime un noeud
     //     * @param noeudSuppr est le fichier ou dossier à supprimer
     //     * @throws NullPointerException si noeudSuppr est null
     //     * @throws FilsInexistantException si noeudSuppr n'est pas un fils de l'objet appele
     //     */
    public void supprimerFils(AbstractArbreFichiers noeudSuppr){ //methode 2 équivalent du rm
        AbstractArbreFichiers b=this.premierFils;
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
        AbstractArbreFichiers n3=noeudSuppr.frereDroit;
        AbstractArbreFichiers n4 =noeudSuppr.frereGauche;
        if (n4!=null)
            n4.frereDroit=n3;
        if (n3!=null)
            n3.frereGauche=n4;

        AbstractArbreFichiers a=noeudSuppr.pere;
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
        AbstractArbreFichiers a=this.premierFils;
        if (a==null){
            return "";
        }else{
            while (a!=null){
                if (a instanceof ArbreFichierFichier)
                    s+="f";
                else if (a instanceof  ArbreFichierDossier)
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
        String s;
        s=this.nom;
        AbstractArbreFichiers a =this.pere;
        while (a!=null){
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
    public AbstractArbreFichiers seDirigerVers(String s){
        if (s.equals("..")){
            if (this.nom.equals("")){
                throw new AccesImpossibleException("Chemin en arriere depuis la racine impossible");
            }else
                return this.pere;
        }else {
            AbstractArbreFichiers a=this.premierFils;
            while (a!=null){
                if (a.nom.equals(s)&& a instanceof ArbreFichierDossier)
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
            AbstractArbreFichiers a = this.premierFils;
            while (a != null) {
                if (a.nom.equals(s) && a instanceof ArbreFichierDossier)
                    return true;
                a = a.frereDroit;
            }
        }
        return false ;
    }

    public String lignesMatch(String s){
        throw new IllegalCallerException("Impossible de match le contenu d'un dossier");
    }

    public String trouver(AbstractArbreFichiers a){
        String total="";
        if (a instanceof ArbreFichierFichier) {
            AbstractArbreFichiers b =a;
            String s=b.nom;
            b=b.pere;
            while (!b.equals(this)){
                s=b.nom+"/"+s;
                b=b.pere;
            }
            if (s.equals(""))
                s="/";
            return (this.nom+"/"+s+"\n");


        }else {
            AbstractArbreFichiers c =a;
            String s="";
            while (c!=(null) && !c.equals(this)){
                s=c.nom+"/"+s;
                c=c.pere;
            }
            if (s.equals(""))
                s="/";
            s=this.nom+"/"+s;
            if (s.length()>0)
                s=s.substring(0,s.length()-1);
            total+=s+"\n";



            AbstractArbreFichiers b = a.premierFils;
            while (b != null) {
                total+=trouver(b);
                b = b.frereDroit;
            }
        }
        return total;
    }


    public String chemin(AbstractArbreFichiers cheminpere){
        AbstractArbreFichiers dc=this;
        String s=this.nom;
        while (!dc.equals(cheminpere)){
            s=dc.nom+"/"+s;
            dc=dc.pere;
        }
        return s+this.nom+"lalala\n";

    }

}
