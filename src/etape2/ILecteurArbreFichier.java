package etape2;

import etape1.AbstractArbreFichiers;
import etape2.exceptions.FichierCorrompuException;

public interface ILecteurArbreFichier {

    AbstractArbreFichiers lireFichier(String s) throws FichierCorrompuException;
}
