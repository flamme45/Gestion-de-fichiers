package etape2.tests;

import etape1.AbstractArbreFichiers;
import etape2.ILecteurArbreFichier;
import etape2.LecteurArbreFichier;
import etape2.LecteurArbreFichier1;
import etape2.exceptions.FichierCorrompuException;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class LecteurArbreFichierTest {

    @Test
    void getRacine() {
        try {
            ILecteurArbreFichier l = new LecteurArbreFichier1();
            assert l.lireFichier("toto.txt").getNom().equals(""):"La methode ne retourne pas la racine";
        }catch (FichierCorrompuException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    void LecteurArbreFichierConstructeur() {
        try {
            ILecteurArbreFichier l = new LecteurArbreFichier1();
            AbstractArbreFichiers racine = l.lireFichier("toto.txt");
            AbstractArbreFichiers sd1 = racine.getPremierFils();
            AbstractArbreFichiers sd3= sd1.getFrereDroit();
            AbstractArbreFichiers un_fichier = sd3.getFrereDroit();
            assert sd1.getNom().equals("sd1");
            assert sd3.getNom().equals("sd3");
            assert un_fichier.getNom().equals("un_fichier");
            AbstractArbreFichiers sd2 = sd1.getPremierFils();
            AbstractArbreFichiers un_autre_fichier=sd2.getFrereDroit();
            AbstractArbreFichiers un_dernier_fichier= un_autre_fichier.getFrereDroit();
            assert sd2.getNom().equals("sd2");
            assert un_autre_fichier.getNom().equals("un_autre_fichier");
            assert un_dernier_fichier.getNom().equals("un_dernier_fichier");
            AbstractArbreFichiers un_3e_fichier = sd2.getPremierFils();
            assert un_3e_fichier.getNom().equals("un_3e_fichier");

            assert un_3e_fichier.getPremierFils()==null :"Erreur positionemment ";
            assert un_autre_fichier.getPremierFils()==null:"Erreur positionemment ";
            assert un_dernier_fichier.getPremierFils()==null:"Erreur positionemment ";
            assert sd3.getPremierFils()==null:"Erreur positionemment ";
            assert un_fichier.getPremierFils()==null:"Erreur positionemment ";

            assert un_fichier.getFrereDroit()==null:"Erreur positionemment ";
            assert un_dernier_fichier.getFrereDroit()==null:"Erreur positionemment ";
            assert un_3e_fichier.getFrereDroit()==null:"Erreur positionemment ";

            assert sd1.getFrereGauche()==null:"Erreur positionemment ";
            assert sd2.getFrereGauche()==null:"Erreur positionemment ";
            assert un_3e_fichier.getFrereGauche()==null:"Erreur positionemment ";

        } catch (FichierCorrompuException e) {
            System.out.println(e.getMessage());
        }
    }
}