package etape2;

import etape1.ArbreFichiers;
import etape2.exceptions.FichierCorrompuException;

public class LecteurArbreFichier1 extends LecteurArbreFichier {

    public LecteurArbreFichier1(){
        super();
        Champs x = super.getChamps();
        x.setMotDebut("racined");
        x.setMotFin("fin");
        x.setCharCommentaire('%');
        x.setCharDebut('*');
        x.setPositionCommentaire(3);
        x.setPositionEtoiles(0);
        x.setPositionNom(1);
        x.setPositionType(2);
    }

    @Override
    public ArbreFichiers lireFichier(String nomFichier) throws FichierCorrompuException {
        return  super.lireFichier(nomFichier);
    }
}
