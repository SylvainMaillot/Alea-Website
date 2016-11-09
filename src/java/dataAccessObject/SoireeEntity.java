package dataAccessObject;

import java.util.Date;

/**
 *
 * @author neiko
 */
public class SoireeEntity {
    
    private int SoireeId;
    private Date Jour;
    private String Nom;
    private int NbJoueur;
    private int NbAdherant;
    private String Description;

    public SoireeEntity(int SoireeId, Date Jour, String Nom, int NbJoueur, 
                        int NbAdherant, String Description) {
        this.SoireeId = SoireeId;
        this.Jour = Jour;
        this.Nom = Nom;
        this.NbJoueur = NbJoueur;
        this.NbAdherant = NbAdherant;
        this.Description = Description;
    }

    public int getSoireeId() {
        return SoireeId;
    }

    public Date getJour() {
        return Jour;
    }

    public String getNom() {
        return Nom;
    }

    public int getNbJoueur() {
        return NbJoueur;
    }

    public int getNbAdherant() {
        return NbAdherant;
    }

    public String getDescription() {
        return Description;
    }
    
}
