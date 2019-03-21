package etape2;

import etape1.AbstractArbreFichiers;
import etape2.exceptions.FichierCorrompuException;

public class LecteurArbreFichier1 extends LecteurArbreFichier {

    public LecteurArbreFichier1(){
        super();
        Champs x = super.getChamps();
        x.setMotDebut("racine");
        x.setMotFin("fin");
        x.setCharCommentaire('%');
        x.setCharDebut('*');
        x.setPositionCommentaire(3);
        x.setPositionEtoiles(0);
        x.setPositionNom(1);
        x.setPositionType(2);
    }

    @Override
    public AbstractArbreFichiers lireFichier(String nomFichier) throws FichierCorrompuException {
        return  super.lireFichier(nomFichier);
    }
}
