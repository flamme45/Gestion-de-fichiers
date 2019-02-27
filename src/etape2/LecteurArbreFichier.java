package etape2;
import etape1.*;
import etape2.exceptions.FichierCorrompuException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LecteurArbreFichier {
    private ArbreFichiers dossierCourant;
    private int numeroLigne;
    private final String motDebut = "racine";
    private final String motFin = "fin";
    private final char charDebut = '*';
    private final char charCommentaire = '%';
    private final int positionEtoiles=0;
    private final int positionNom=1;
    private final int positionType =2;
    private final int positionCommentaire=3;
    private int nbetoiles;

    public LecteurArbreFichier(String nomFichier) throws FichierCorrompuException {
        numeroLigne=0;
        dossierCourant = new ArbreFichiers();
        ArbreFichiers nouvelArbre ;
        nbetoiles=1;
        Scanner lecteur=null;
        try {
            lecteur = new Scanner(new File(nomFichier));
            if (!lecteur.hasNextLine()) //S'il n'y a pas de premiere ligne
                throw new FichierCorrompuException("Le fichier est vide");
            String ligne = lecteur.nextLine();
            numeroLigne++; //Pour afficher les messages d'erreur on calcule le numero de ligne
            if (!ligne.equals(motDebut)) //On verifie que le fichier commence bien par le mot de debut
                throw new FichierCorrompuException("Le fichier ne commence pas par le mot " + motDebut);
            while (lecteur.hasNextLine()) {
                ligne = lecteur.nextLine();
                numeroLigne++;
                String[] tMots = ligne.split(" "); //tableau qui sépare les mots de la ligne
                if (tMots.length == 1) { //S'il n'y a qu'un seul mot
                    if (tMots[0].equals("")) //Si le premier élement c'est une chaine vide, cela signifie que la ligne est vide
                        throw new FichierCorrompuException("Une ligne est vide"+ "(ligne " + numeroLigne + ")");
                    if (!tMots[0].equals(motFin)) { //Si ce dernier mot n'est pas le mot fin
                        if (!lecteur.hasNextLine()) // et que c'estle dernier mot du fichier
                            throw new FichierCorrompuException("Le fichier ne se termine pas par le mot '" + motFin + "'(ligne " + numeroLigne + ")");
                        else // s'il y a un mot tout seul <=> erreur
                            throw new FichierCorrompuException("Une ligne contient un seul mot et le fichier n'a pas fini d'être lû (ligne " + numeroLigne + ")");
                    } else {//si c'est le mot 'fin'
                        if (lecteur.hasNextLine()) //s'il reste des lignes après le mot fin
                            throw new FichierCorrompuException("Le mot '"+motFin+"' a été trouvé mais il reste des lignes dans le fichier (ligne " + numeroLigne + ")");
                        break; // sinon on a términé la lecture
                    }
                } else { // s'il y a plusieurs mots
                    if (tMots[positionNom].equals(motFin)) { // et que le deuxieme mot et le mot fin
                        verifierEtoiles(tMots[positionEtoiles],nbetoiles-1); //on verifie qu'il y ait le bon nobmre d'étoiles
                        remonterDePere(tMots); // et on remonte d'un pere
                        if (tMots.length>2) // si la ligne contient let mot 'fin' en deuxieme mot et qu'il y a des mots derriere
                            verifierCommentraire(tMots[positionType]); // on verifie que ce sont bien des commentaires
                    } else { // si le deuxieme mot ce n'est pas le mot de fin
                        if (tMots.length > 3) { // et si il y a plus de 3 mots
                            String commentaire = tMots[positionCommentaire];
                            verifierCommentraire(commentaire); // on verifie que c'est bien un commentaire
                        }if (tMots.length==2){
                            throw new FichierCorrompuException("il n'y a que 2 mots et la ligne ne correspond pas à l'indication '"+motFin+"' (ligne "+numeroLigne+")"); // si la taille est de deux, c'est forcement une erreur
                        }

                        String nom = tMots[positionNom];
                        String type = tMots[positionType];
                        String etoiles = tMots[positionEtoiles];
                        verifierLigne(type, etoiles); //On verifie que le type existe et que les etoiles sont bien des etoiles et qu'il y ait bien le bon nombre
                        if (type.equals("d")) { // si c'est un dossier
                            nouvelArbre = new ArbreFichiers(nom, false, null); // on crée un nouveau dossier
                            dossierCourant.ajouterFils(nouvelArbre);
                            dossierCourant = nouvelArbre; // on rentre dans ce dossier
                            nbetoiles++; //on augmente le nombre d'étoiles
                        } else if (lecteur.hasNextLine()) {
                            String contenu = lecteur.nextLine(); //On prend le contenu de ce fichier
                            numeroLigne++;
                            nouvelArbre = new ArbreFichiers(nom, true, contenu); // on cree le fichier
                            dossierCourant.ajouterFils(nouvelArbre);// et on l'ajoute
                        } else
                            throw new FichierCorrompuException("Le fichier ne se ferme pas correctement (ligne " + numeroLigne + ")");
                    }
                }
            }
            if (!ligne.equals(motFin)) // Si on est dans le cas ou un fichier a été ajouté en dernier et qu'il n'y ait pas le mot fin apres le contenu
                throw new FichierCorrompuException("Le fichier ne se termine pas par le mot '"+motFin+"'");
            lecteur.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            lecteur.close();
        }
    }

    /**
     * Methode qui verifie que le type et qui demande a la focntion verifierEtoiles si les etoiles sotn correctes
     * @param type soit un dossier soit un fichier, repsectivement 'd' ou 'f'
     * @param etoiles les etoiles du début de fichier
     * @throws FichierCorrompuException s'il un des deux arguments n'est pas correct
     */
    private void verifierLigne(String type, String etoiles) throws FichierCorrompuException{
        if (!type.equals("f")&&!type.equals("d"))
            throw new FichierCorrompuException("Le type de fichier ne peut pas être lû, il n'est ni un 'f' ni un 'd' (ligne "+numeroLigne+")");
        verifierEtoiles(etoiles,nbetoiles);

    }

    /**
     * Methode qui verifie le nombre d'étoiles
     * @param etoiles sont les etoiles de la ligne
     * @param nbretoiles est le nombre d etoiles que la ligne est censee avoir
     * @throws FichierCorrompuException s il y a un probleme avec les etoiles
     */
    private void verifierEtoiles(String etoiles,int nbretoiles)throws FichierCorrompuException{
        for (int i =0;i<etoiles.length();i++){
            if (etoiles.charAt(i)!=(charDebut))
                throw new FichierCorrompuException("Chaque ligne doit commencer par des "+charDebut+" si ce n'est pas le contenu d'un fichier (ligne "+numeroLigne+")");
        }
        if (etoiles.length()!=nbretoiles)
            throw new FichierCorrompuException("Le nombre d'etoiles dans une ligne est incorect (ligne "+numeroLigne+")");
    }

    /**
     * Methode qui verifie que le string en parametre est bien un commentaire
     * @param commentaire est la chaine qui est censee commencer par let motif du commentaire
     * @throws FichierCorrompuException si ca ne commence pas par le motif du commentaire
     */
    private void verifierCommentraire(String commentaire) throws  FichierCorrompuException{
        if (commentaire.charAt(0)!=charCommentaire)
            throw new FichierCorrompuException("Une ligne contient plus de 4 mots  et les mots en trop ne sont pas des commentaires"+"(ligne "+numeroLigne+")");
    }

    /**
     * Methode qui remonte de dossier si la ligne est correcte
     * @param tab est le tableau contenant tous les mots de la ligne
     * @throws FichierCorrompuException si la ligne est fausse
     */
    private void remonterDePere(String [] tab ) throws FichierCorrompuException{
        if (tab[1].equals(motFin) && tab[0].length() == nbetoiles - 1) {
            this.dossierCourant = dossierCourant.getPere();
            nbetoiles--;
        }else
            throw new FichierCorrompuException("Erreur dans le nombre d'étoiles dans le fichier"+"(ligne "+numeroLigne+")");
    }

    /**
     * Methode qui retourne le dossier courant, qui est forcement le dossier racine quand on l appel a l exterieur de cette classe
     * @return le dossiier courant
     */
    public ArbreFichiers getRacine(){
        return dossierCourant;
    }

}
