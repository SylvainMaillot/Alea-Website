package dataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

/**
 *
 * @author neiko
 */
public class AccessUtilisateurObject {
    
    	private final DataSource myDataSource;

	/**
	 *
	 * @param dataSource la source de données à utiliser
	 */
	public AccessUtilisateurObject(DataSource dataSource) {
		this.myDataSource = dataSource;
	}
        
        /**
	 *
	 * @return le nombre d'enregistrements dans la table Utilisateur
	 * @throws SQLException
	 */
	public int nbUtilisateurs() throws SQLException {
		int result = 0;

		String sql = "SELECT COUNT(*) AS NUMBER FROM Utilisateur";
		// Ouvrir une connexion
		Connection connection = myDataSource.getConnection();
		// On crée un statement pour exécuter une requête
		Statement stmt = connection.createStatement();
		// Un ResultSet pour parcourir les enregistrements du résultat
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) { // Pas la peine de faire while, il y a 1 seul enregistrement
			// On récupère le champ NUMBER de l'enregistrement courant
			result = rs.getInt("NUMBER");
		}
		// On ferme tout
		rs.close();
		stmt.close();
		connection.close();

		return result;
	}
        
        /**
	 * @param ident identifiant de l'utilisateur
         * @param psswd mot de passe de l'utilisateur
	 * @return Un Utilisateur
	 * @throws SQLException
	 */
	public UtilisateurEntity getUtilisateurByLoggin(String ident, String 
                    psswd) throws SQLException {
		UtilisateurEntity result = null;
		String sql = "select * from Utilisateur\n" +
                "where Identifiant = ? and MotDePasse = ?";
		// Ouvrir une connexion
		Connection connection = myDataSource.getConnection();
		// On crée un statement pour exécuter une requête
		PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, ident);
                stmt.setString(2, psswd);
		// Un ResultSet pour parcourir les enregistrements du résultat
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
                        int UserId = rs.getInt("ID");
                        String Identifiant = rs.getString("Identifiant");
                        String MotDePasse = rs.getString("MotDePasse"); 
                        String Email = rs.getString("Email");
                        String Nom = rs.getString("Nom");
                        String Prenom = rs.getString("Prenom");
                        float Contribution = rs.getFloat("Contribution");
                        int TypeUtilisateur = rs.getInt("TypeUtilisateur");
                        
                        double contribution = rs.getDouble("Contribution");
                        result = new UtilisateurEntity(UserId, Identifiant, MotDePasse,
                                Email, Prenom, Nom, Contribution, TypeUtilisateur);
		}
		// On ferme tout
		rs.close();
		stmt.close();
		connection.close();

		return result;
	}
        
       public boolean updateUtilisateur(String nom, String prenom, String psswd,
               String mail, int id) throws SQLException {
		String sql = "update Utilisateur set Nom = ?, Prenom = ?,"
                        + "MotDePasse = ?, Email = ? where ID = ?;";
		// Ouvrir une connexion
		Connection connection = myDataSource.getConnection();
		// On crée un statement pour exécuter une requête
		PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1,nom);
                stmt.setString(2,prenom);
                stmt.setString(3,psswd);
                stmt.setString(4,mail);
                stmt.setInt(5, id);
		// Un ResultSet pour parcourir les enregistrements du résultat
		int rs = stmt.executeUpdate();
		// On ferme tout
		stmt.close();
		connection.close();
                
		return rs != 0;
	} 
}