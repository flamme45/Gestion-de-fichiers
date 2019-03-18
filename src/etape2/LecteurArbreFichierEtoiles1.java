package etape2;
import etape1.*;
import etape2.exceptions.FichierCorrompuException;


public class LecteurArbreFichierEtoiles1 extends  LecteurArbreFichierEtoiles {


    public LecteurArbreFichierEtoiles1()  {
        motDebut="racine";
        motFin="fin";
        charDebut='*';
        charCommentaire='%';
        positionEtoiles=0;
        positionNom=1;
        positionType=2;
        positionCommentaire=3;
    }

    public ArbreFichiers lireFichier(String nomFichier) throws FichierCorrompuException {
        return super.lireFichier(nomFichier);
    }



}
