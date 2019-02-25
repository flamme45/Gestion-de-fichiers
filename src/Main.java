public class Main {

    public static void main (String [] args){
        ArbreFichiers racine = new ArbreFichiers();
        ArbreFichiers fils1=new ArbreFichiers("aaa",true,"contenu du fichier aaa") ;
        ArbreFichiers fils2=new ArbreFichiers("bbb",true,"contenu du fichier bbbb") ;
        ArbreFichiers fils3=new ArbreFichiers("a",true,"contenu du fichier a") ;
        ArbreFichiers fils4= new ArbreFichiers("dossier a",false,null);

        ArbreFichiers fils5=new ArbreFichiers("aaa",true,"contenu du fichier aaa") ;
        ArbreFichiers fils6=new ArbreFichiers("bbb",true,"contenu du fichier bbbb") ;
        fils4.ajouterFils(fils5);
        fils4.ajouterFils(fils6);

        racine.ajouterFils(fils4);
        racine.ajouterFils(fils2);
        racine.ajouterFils(fils1);
        racine.ajouterFils(fils3);
        System.out.println(fils6);
    }
}
