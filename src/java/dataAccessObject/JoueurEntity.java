package dataAccessObject;

/**
 * Correspond à un enregistrement de la table Joueur
 * @author neiko
 */
public class JoueurEntity {
    
    	private int joueurID;
	private String nom;
	private String prenom;
        private double contribution;

    public JoueurEntity(int joueurID, String nom, String prenom, double contribution) {
        this.joueurID = joueurID;
        this.nom = nom;
        this.prenom = prenom;
        this.contribution = contribution;
    }

    public int getJoueurID() {
        return joueurID;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public double getContribution() {
        return contribution;
    }
    
}
