import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArbreFichiersTest {

    @Test
    void ajouterFils() {
        ArbreFichiers racine = new ArbreFichiers();
        ArbreFichiers fils1=new ArbreFichiers("aaa",true,"contenu du fichier aaa") ;
        ArbreFichiers fils2=new ArbreFichiers("bbb",true,"contenu du fichier bbbb") ;
        ArbreFichiers fils3=new ArbreFichiers("a",true,"contenu du fichier a") ;
        ArbreFichiers fils4= new ArbreFichiers("dossier a",false,null);
        racine.ajouterFils(fils4);
        racine.ajouterFils(fils2);
        racine.ajouterFils(fils1);
        racine.ajouterFils(fils3);
        assert (racine.getTaille()==fils1.getTaille()+fils2.getTaille()+fils3.getTaille()+fils4.getTaille()):"Erreur de taille";
        assert racine.getPremierFils()==fils3:"Erreur premier fils incorect";
        assert fils3.getFrereGauche()==null :"Erreur frere gauche incorrect";
        assert fils3.getFrereDroit()==fils1 :"Erreur frere droit incorrect";
        assert fils1.getFrereGauche()==fils3 : "Erreur frere gauche incorrect";
        assert fils1.getFrereDroit()==fils2 :"Erreur frere droit incorrect";
        assert fils2.getFrereDroit()==fils4:"Erreur frere droit incorrect";
        assert fils2.getFrereGauche()==fils1: "Erreur frere gauche incorrect";
        assert fils4.getFrereGauche()==fils2 : "Erreur frere gauche incorrect";
        assert fils4.getFrereDroit()==null :"Erreur frere droit incorrect";
        assert fils1.getPere()==racine :"Erreur pere incorrect";
    }
}