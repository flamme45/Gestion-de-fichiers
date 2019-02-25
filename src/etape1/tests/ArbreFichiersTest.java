package etape1.tests;

import etape1.ArbreFichiers;
import org.junit.jupiter.api.Test;


class ArbreFichiersTest {

    @Test
    void ajouterFils() {
        ArbreFichiers racine = new ArbreFichiers();
        ArbreFichiers fils1 = new ArbreFichiers("aaa", true, "contenu du fichier aaa");
        ArbreFichiers fils2 = new ArbreFichiers("bbb", true, "contenu du fichier bbbb");
        ArbreFichiers fils3 = new ArbreFichiers("a", true, "contenu du fichier a");
        ArbreFichiers fils4 = new ArbreFichiers("dossier a", false, null);
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
    }


    @Test
    void supprimerFils() {
        ArbreFichiers racine = new ArbreFichiers();
        ArbreFichiers fils1 = new ArbreFichiers("aaa", true, "88888888");
        ArbreFichiers fils2 = new ArbreFichiers("ee", true, "22");
        ArbreFichiers fils3 = new ArbreFichiers("a", true, "1010101010");
        ArbreFichiers fils4 = new ArbreFichiers("dossier aa", false, null);
        racine.ajouterFils(fils4);
        racine.ajouterFils(fils2);
        racine.ajouterFils(fils1);
        racine.ajouterFils(fils3);
        fils4.ajouterFils(new ArbreFichiers("test", true, "55555"));
        fils4.ajouterFils(new ArbreFichiers("test2", true, "1"));
        racine.supprimerFils(fils4);
        assert racine.getTaille() == fils1.getTaille() + fils2.getTaille() + fils3.getTaille() + fils4.getTaille() - fils4.getTaille() : "Erreur taille";
        assert fils1.getFrereDroit() == fils2 : "Erreur frere droit";
        assert fils2.getFrereGauche() == fils1 : "Erreur frere gauche";
    }

    @Test
    void cheminAbsolu() {
        ArbreFichiers racine = new ArbreFichiers();
        assert racine.cheminAbsolu().equals("/");
        ArbreFichiers dossier1 = new ArbreFichiers("dossier1", false, null);
        racine.ajouterFils(dossier1);
        assert dossier1.cheminAbsolu().equals("/dossier1");
        ArbreFichiers fichier1 = new ArbreFichiers("fichier1", true, "contenu fichier 1");
        racine.ajouterFils(fichier1);
        assert fichier1.cheminAbsolu().equals("/");
        ArbreFichiers dossier2 = new ArbreFichiers("dossier2", false, null);
        dossier1.ajouterFils(dossier2);
        assert dossier2.cheminAbsolu().equals("/dossier1/dossier2");

    }

    @Test
    void seDirigerVers() {
        ArbreFichiers racine = new ArbreFichiers();
        ArbreFichiers d1 = new ArbreFichiers("dossier1", false, null);
        racine.ajouterFils(d1);
        ArbreFichiers d2 = new ArbreFichiers("dossier2", false, null);
        d1.ajouterFils(d2);
        assert racine.seDirigerVers("dossier1") == d1 : "Deplacement vers le premier dossier, erreur";
        assert racine.seDirigerVers("dossier1").seDirigerVers("dossier2") == d2 : "Deplacement vers le dossier dans le dossier, erreur";
    }

    @Test
    void peutSeDirigerVers() {
        ArbreFichiers racine = new ArbreFichiers();
        ArbreFichiers d1 = new ArbreFichiers("doss1", false, null);
        racine.ajouterFils(d1);
        ArbreFichiers d2 = new ArbreFichiers("doss2", false, null);
        d1.ajouterFils(d2);
        ArbreFichiers f1 = new ArbreFichiers("fich1", true, "contenu f1");
        racine.ajouterFils(f1);
        assert racine.peutSeDirigerVers("doss1"):"Erreur possibilite deplacement";
        assert d1.peutSeDirigerVers("doss2");
        assert !racine.peutSeDirigerVers("fich1"):"Erreur, on peut se diriger vers un fichier";
        assert d1.peutSeDirigerVers(".."):"Erreur, deplacement vers l arriere";
        assert !racine.peutSeDirigerVers(".."):"Erreur deplacement en arriere depuis racine impossible";
    }
}