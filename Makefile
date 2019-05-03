JFLAGS = -g 
JC = javac

.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = src/etape1/exceptions/AccesImpossibleException.java src/etape1/exceptions/CreationFilsException.java src/etape1/exceptions/DossierIntrouvableException.java src/etape1/exceptions/FilsInexistantException.java src/etape1/tests/ArbreFichiersTest.java src/etape1/AbstractArbreFichiers.java src/etape1/ArbreFichierDossier.java src/etape1/ArbreFichierFichier.java src/etape2/exceptions/FichierCorrompuException.java src/etape2/tests/LecteurArbreFichierTest.java src/etape2/Champs.java src/etape2/ILecteurArbreFichier.java src/etape2/LecteurArbreFichier.java src/etape2/LecteurArbreFichier1 src/etape3/commandes/CommandeCd.java src/etape3/commandes/CommandeEffectuer.java src/etape3/commandes/CommandeFind.java src/etape3/commandes/CommandeGrep.java src/etape3/commandes/CommandeLess.java src/etape3/commandes/CommandeLs.java src/etape3/commandes/CommandeMkdir.java src/etape3/commandes/CommandeMkfile.java src/etape3/commandes/CommandePwd.java src/etape3/commandes/CommandeQuitExit.java src/etape3/commandes/CommandeRm.java src/etape3/Commande.java src/etape3/Main.java


default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) src/etape1/exceptions/*.class src/etape1/tests/*.class src/etape1/*.class src/etape2/exceptions/*.class src/etape2/tests/*.class src/etape2/*.class src/etape3/commandes/*.class src/etape3/*.class