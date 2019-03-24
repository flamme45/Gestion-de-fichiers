package etape3.commandes;

import etape1.AbstractArbreFichiers;
import etape1.ArbreFichierFichier;

public class CommandeGrep implements  CommandeEffectuer {
    public AbstractArbreFichiers effectuerOperation(AbstractArbreFichiers a, String[] s) {

        if (s.length < 3) {
            System.out.println("Nombre d'arguments incorrects \n");
        } else {
            String pat = "";
            for (int i = 1; i < s.length - 1; i++) {
                pat += s[i];
            }
            AbstractArbreFichiers fichier = a.getArbre(s[s.length - 1]);
            if (fichier instanceof ArbreFichierFichier) {
                if (veriferRegex(pat)) {
                    pat = pat.substring(1, pat.length() - 1);
                    System.out.println(fichier.lignesMatch(pat));
                } else
                    System.out.println("Erreur expression regulière\n");

            } else
                System.out.println(s[s.length - 1] + " n'est pas un fichier de ce repertoire \n");
        }
        return a;
    }

    private boolean veriferRegex(String pat) {
        return doubleGuillements(pat)||simpleGuillements(pat);
    }

    private boolean doubleGuillements(String pat){
        if (pat.charAt(0)!='"'&& pat.charAt(pat.length()-1)!='"')
            return false;
        pat = pat.substring(1, pat.length() - 1);
        char [] tabChar= pat.toCharArray();
        boolean b=true;
        for (int i =0; i<tabChar.length;i++){
            if (tabChar[i]=='"' && (i==0||tabChar[i-1]!='\\')) {
                b = false;
                break;
            }
        }
        return b;
    }

    private boolean simpleGuillements(String pat){
        if (pat.charAt(0)!='\''&& pat.charAt(pat.length()-1)!='\'')
            return false;
        pat = pat.substring(1, pat.length() - 1);
        char [] tabChar= pat.toCharArray();
        boolean b=true;
        for (int i =0; i<tabChar.length;i++){
            if (tabChar[i]=='\'' && (i==0||tabChar[i-1]!='\\')) {
                b = false;
                break;
            }
        }
        return b;
    }

    }


