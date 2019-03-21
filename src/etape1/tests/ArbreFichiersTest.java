package etape1.tests;

import etape1.AbstractArbreFichiers;
import etape1.ArbreFichierDossier;
import etape1.ArbreFichierFichier;
import etape1.exceptions.FilsInexistantException;
import org.junit.jupiter.api.Test;


class ArbreFichiersTest {

    @Test
    void ajouterFils() {
        AbstractArbreFichiers racine = new ArbreFichierDossier();
        AbstractArbreFichiers fils1 = new ArbreFichierFichier("aaa","contenu du fichier aaa");
        AbstractArbreFichiers fils2 = new ArbreFichierFichier("bbb", "contenu du fichier bbbb");
        AbstractArbreFichiers fils3 = new ArbreFichierFichier("a","contenu du fichier a");
        AbstractArbreFichiers fils4 = new ArbreFichierDossier("dossier a");
        racine.ajouterFils(fils4);
        racine.ajouterFils(fils2);
        racine.ajouterFils(fils1);
        racine.ajouterFils(fils3);
        assert (racine.getTaille() == fils1.getTaille() + fils2.getTaille() + fils3.getTaille() + fils4.getTaille()) : "Erreur de taille";
        assert racine.getPremierFils() == fils3 : "Erreur premier fils incorect";
        assert fils3.getFrereGauche() == null : "Erreur frere gauche incorrect";
        assert fils3.getFrereDroit() == fils1 : "Erreur frere droit incorrect";
        assert fils1.getFrereGauche() == fils3 : "Erreur frere gauche incorrect";
        assert fils1.getFrereDroit() == fils2 : "Erreur frere droit incorrect";
        assert fils2.getFrereDroit() == fils4 : "Erreur frere droit incorrect";
        assert fils2.getFrereGauche() == fils1 : "Erreur frere gauche incorrect";
        assert fils4.getFrereGauche() == fils2 : "Erreur frere gauche incorrect";
        assert fils4.getFrereDroit() == null : "Erreur frere droit incorrect";
        assert fils1.getPere() == racine : "Erreur pere incorrect";
        boolean erreur =false;
        try {
            fils3.ajouterFils(new ArbreFichierDossier("aaaaaaaaa"));
        }catch (IllegalCallerException e){
            erreur=true;
        }
        assert erreur:"Un fils a pû être créé alors que le fichier courant un un fichier";
    }


    @Test
    void supprimerFils() {
        AbstractArbreFichiers racine = new ArbreFichierDossier();
        AbstractArbreFichiers fils1 = new ArbreFichierFichier("aaa", "88888888");
        AbstractArbreFichiers fils2 = new ArbreFichierFichier("ee", "22");
        AbstractArbreFichiers fils3 = new ArbreFichierFichier("a","1010101010");
        AbstractArbreFichiers fils4 = new ArbreFichierDossier("dossier aa");
        racine.ajouterFils(fils4);
        racine.ajouterFils(fils2);
        racine.ajouterFils(fils1);
        racine.ajouterFils(fils3);
        fils4.ajouterFils(new ArbreFichierFichier("test", "55555"));
        fils4.ajouterFils(new ArbreFichierFichier("test2", "1"));
        racine.supprimerFils(fils4);
        AbstractArbreFichiers f = new ArbreFichierDossier("testbjbj");
        boolean erreur =false;
        try {
            racine.supprimerFils(f);
        }catch (FilsInexistantException e){
            erreur=true;
        }
        assert erreur:"Un fils inéxistant n'a pas levé d'expcetions";
        assert racine.getTaille() == fils1.getTaille() + fils2.getTaille() + fils3.getTaille() + fils4.getTaille() - fils4.getTaille() : "Erreur taille";
        assert fils1.getFrereDroit() == fils2 : "Erreur frere droit";
        assert fils2.getFrereGauche() == fils1 : "Erreur frere gauche";
    }

    @Test
    void cheminAbsolu() {
        AbstractArbreFichiers racine = new ArbreFichierDossier();
        assert racine.cheminAbsolu().equals("/");
        AbstractArbreFichiers dossier1 = new ArbreFichierDossier("dossier1");
        racine.ajouterFils(dossier1);
        assert dossier1.cheminAbsolu().equals("/dossier1");
        AbstractArbreFichiers fichier1 = new ArbreFichierFichier("fichier1", "contenu fichier 1");
        racine.ajouterFils(fichier1);
        boolean b=false;
        try {
            assert fichier1.cheminAbsolu().equals("/");
        }catch (IllegalCallerException e) {
            b = true;
        }
        assert b : "Chemin absolu depuis un fichier effectué";

        AbstractArbreFichiers dossier2 = new ArbreFichierDossier("dossier2");
        dossier1.ajouterFils(dossier2);
        assert dossier2.cheminAbsolu().equals("/dossier1/dossier2");

    }

    @Test
    void seDirigerVers() {
        AbstractArbreFichiers racine = new ArbreFichierDossier();
        AbstractArbreFichiers d1 = new ArbreFichierDossier("dossier1");
        racine.ajouterFils(d1);
        AbstractArbreFichiers d2 = new ArbreFichierDossier("dossier2");
        d1.ajouterFils(d2);
        assert racine.seDirigerVers("dossier1") == d1 : "Deplacement vers le premier dossier, erreur";
        assert racine.seDirigerVers("dossier1").seDirigerVers("dossier2") == d2 : "Deplacement vers le dossier dans le dossier, erreur";
    }

    @Test
    void peutSeDirigerVers() {
        AbstractArbreFichiers racine = new ArbreFichierDossier();
        AbstractArbreFichiers d1 = new ArbreFichierDossier("doss1");
        racine.ajouterFils(d1);
        AbstractArbreFichiers d2 = new ArbreFichierDossier("doss2");
        d1.ajouterFils(d2);
        AbstractArbreFichiers f1 = new ArbreFichierFichier("fich1","contenu f1");
        racine.ajouterFils(f1);
        assert racine.peutSeDirigerVers("doss1"):"Erreur possibilite deplacement";
        assert d1.peutSeDirigerVers("doss2");
        assert !racine.peutSeDirigerVers("fich1"):"Erreur, on peut se diriger vers un fichier";
        assert d1.peutSeDirigerVers(".."):"Erreur, deplacement vers l arriere";
        assert !racine.peutSeDirigerVers(".."):"Erreur deplacement en arriere depuis racine impossible";
    }
}