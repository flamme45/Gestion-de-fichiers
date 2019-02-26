package etape2;
import etape1.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LecteurArbreFichier {
    ArbreFichiers dossierCourant ;
    Scanner lecteur;

    public LecteurArbreFichier(String nomFichier){
        dossierCourant=new ArbreFichiers();
        try {
            lecteur = new Scanner(new File(nomFichier));


        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    private boolean estTexteCorrecte(){
        String ligne=lecteur.nextLine();
        int nombreEtoiles=0;
        if (!ligne.equals("racine"))
            return false;
        return true;

    }
}
