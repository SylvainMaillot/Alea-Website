package dataAccessObject;

/**
 *
 * @author neiko
 */
public class JeuEntity {
    
    private int GameId;
    private String Nom;
    private int NbJoueurMin;
    private int NbJoueurMax;
    private String Description;
    private int ProprietaireID;

    public JeuEntity(int GameId, String Nom, int NbJoueurMin, int NbJoueurMax, 
                       String Description, int ProprietaireID) {
        this.GameId = GameId;
        this.Nom = Nom;
        this.NbJoueurMin = NbJoueurMin;
        this.NbJoueurMax = NbJoueurMax;
        this.Description = Description;
        this.ProprietaireID = ProprietaireID;
    }

    public int getGameId() {
        return GameId;
    }

    public String getNom() {
        return Nom; 
    }

    public int getNbJoueurMin() {
        return NbJoueurMin;
    }

    public int getNbJoueurMax() {
        return NbJoueurMax;
    }

    public String getDescription() {
        return Description;
    }

    public int getProprietaireID() {
        return ProprietaireID;
    }
    
}
