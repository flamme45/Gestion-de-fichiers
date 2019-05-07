JFLAGS = -g 
JC = javac

.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = etape1/exceptions/AccesImpossibleException.java etape1/exceptions/CreationFilsException.java etape1/exceptions/DossierIntrouvableException.java etape1/exceptions/FilsInexistantException.java etape1/tests/ArbreFichiersTest.java etape1/AbstractArbreFichiers.java etape1/ArbreFichierDossier.java etape1/ArbreFichierFichier.java etape2/exceptions/FichierCorrompuException.java etape2/tests/LecteurArbreFichierTest.java etape2/Champs.java etape2/ILecteurArbreFichier.java etape2/LecteurArbreFichier.java etape2/LecteurArbreFichier1 etape3/commandes/CommandeCd.java etape3/commandes/CommandeEffectuer.java etape3/commandes/CommandeFind.java etape3/commandes/CommandeGrep.java etape3/commandes/CommandeLess.java etape3/commandes/CommandeLs.java etape3/commandes/CommandeMkdir.java etape3/commandes/CommandeMkfile.java etape3/commandes/CommandePwd.java etape3/commandes/CommandeQuitExit.java etape3/commandes/CommandeRm.java etape3/Commande.java etape3/Main.java


default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) etape1/exceptions/*.class etape1/*.class etape2/exceptions/*.class etape2/*.class etape3/commandes/*.class etape3/*.class