package etape2;

/**
 * Class contenant le champs des lecteursArbreFichier
 */
public class Champs {
    String motDebut;
    String motFin;
    char charDebut;
    char charCommentaire;
    int positionEtoiles;
    int positionNom;
    int positionType;
    int positionCommentaire;


    public Champs() {
        this.motDebut = "racine";
        this.motFin = "fin";
        this.charDebut = '*';
        this.charCommentaire = '%';
        this.positionCommentaire = 3;
        this.positionEtoiles = 0;
        this.positionNom = 1;
        this.positionType = 2;
    }

    public void setMotDebut(String motDebut) {
        this.motDebut = motDebut;
    }

    public void setMotFin(String motFin) {
        this.motFin = motFin;
    }

    public void setCharDebut(char charDebut) {
        this.charDebut = charDebut;
    }

    public void setCharCommentaire(char charCommentaire) {
        this.charCommentaire = charCommentaire;
    }

    public void setPositionEtoiles(int positionEtoiles) {
        this.positionEtoiles = positionEtoiles;
    }

    public void setPositionNom(int positionNom) {
        this.positionNom = positionNom;
    }

    public void setPositionType(int positionType) {
        this.positionType = positionType;
    }

    public void setPositionCommentaire(int positionCommentaire) {
        this.positionCommentaire = positionCommentaire;
    }

    public String getMotDebut() {
        return motDebut;
    }

    public String getMotFin() {
        return motFin;
    }

    public char getCharDebut() {
        return charDebut;
    }

    public char getCharCommentaire() {
        return charCommentaire;
    }

    public int getPositionEtoiles() {
        return positionEtoiles;
    }

    public int getPositionNom() {
        return positionNom;
    }

    public int getPositionType() {
        return positionType;
    }

    public int getPositionCommentaire() {
        return positionCommentaire;
    }
}

