package dataAccessObject;

/**
 * Correspond à un enregistrement de la table Utilisateur
 * @author neiko
 */
public class UtilisateurEntity {
    
    	private int UserId;
        private String Identifiant;
        private String MotDePasse; 
        private String Email;
        private String Prenom; 
        private String Nom;
        private float Contribution;
        private int TypeUtilisateur;

    public UtilisateurEntity(int UserId, String Identifiant, String MotDePasse, 
                String Email, String Prenom, String Nom, float Contribution, 
                int TypeUtilisateur) {
        this.UserId = UserId;
        this.Identifiant = Identifiant;
        this.MotDePasse = MotDePasse;
        this.Email = Email;
        this.Prenom = Prenom;
        this.Nom = Nom;
        this.Contribution = Contribution;
        this.TypeUtilisateur = TypeUtilisateur;
    }

    public int getUserId() {
        return UserId;
    }

    public String getIdentifiant() {
        return Identifiant;
    }

    public String getMotDePasse() {
        return MotDePasse;
    }

    public String getEmail() {
        return Email;
    }

    public String getPrenom() {
        return Prenom;
    }

    public String getNom() {
        return Nom;
    }

    public float getContribution() {
        return Contribution;
    }

    public int getTypeUtilisateur() {
        return TypeUtilisateur;
    }
    
    
    
}
