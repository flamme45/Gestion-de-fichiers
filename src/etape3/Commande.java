package etape3;

import etape1.AbstractArbreFichiers;
import etape3.commandes.*;

public class Commande {
    private CommandeEffectuer commandeEffectuer;

    /**
     * Pour s assurer que le programme ne plante pas,  on instancie par defaut a premier commande a ls
     */
    public Commande(){
        commandeEffectuer=new CommandeLs();
    }

    /**
     * Methode qui modifie la commande a effectuer en fontion de ce qu on a taper
     * @param s est la commande
     * @throws  IllegalArgumentException si s n est pas une commande valide
     */
    public void setCommandeEffectuer(String s) throws IllegalArgumentException{
        switch (s){

            case "ls":
                commandeEffectuer= new CommandeLs();
                break;
            case "cd":
                commandeEffectuer=new CommandeCd();
                break;
            case "less":
                commandeEffectuer=new CommandeLess();
                break;
            case "pwd":
                commandeEffectuer=new CommandePwd();
                break;
            case "mkdir":
                commandeEffectuer=new CommandeMkdir();
                break;
            case "mkfile":
                commandeEffectuer=new CommandeMkfile();
                break;
            case "quit" :
                commandeEffectuer=new CommandeQuitExit();
                break;
            case "exit" :
                commandeEffectuer=new CommandeQuitExit();
                break;
            case "rm":
                commandeEffectuer=new CommandeRm();
                break;
            default:
                throw new IllegalArgumentException("Commande '" +s+"' inconnue \n");
        }
    }

    /**
     * Methode qui demande a commandeEffectuer d effectuer une operation
     * @param a est le dossier courant
     * @param s est les parametes en plus
     * @return le dossier courant apres modifications
     */
    public AbstractArbreFichiers effectuerOperation(AbstractArbreFichiers a, String ... s){
        return commandeEffectuer.effectuerOperation(a,s);
    }

    /**
     * Methode qui affiche > pour signifier qu on attend que l utilisateur entre une commande
     */
    public void afficherDemande(){
        System.out.print(">");
    }


}
