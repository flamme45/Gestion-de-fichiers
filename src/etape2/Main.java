package etape2;

import etape1.ArbreFichiers;
import etape2.exceptions.FichierCorrompuException;

public class Main {
    public static void main(String[] args) throws FichierCorrompuException {
        LecteurArbreFichier l= new LecteurArbreFichier("toto.txt");
        ArbreFichiers racine = l.getRacine();
        //System.out.println(racine.toString());
    }
}
