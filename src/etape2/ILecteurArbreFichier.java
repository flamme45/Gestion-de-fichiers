package etape2;

import etape1.ArbreFichiers;
import etape2.exceptions.FichierCorrompuException;

public interface ILecteurArbreFichier {
    ArbreFichiers lireFichier(String nomFichier)throws FichierCorrompuException;
}
