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
public class AccessObject {
    
    	private final DataSource myDataSource;

	/**
	 *
	 * @param dataSource la source de données à utiliser
	 */
	public AccessObject(DataSource dataSource) {
		this.myDataSource = dataSource;
	}
        
        /**
	 *
	 * @return le nombre d'enregistrements dans la table JOUEUR
	 * @throws SQLException
	 */
	public int nbJoueurs() throws SQLException {
		int result = 0;

		String sql = "SELECT COUNT(*) AS NUMBER FROM Joueur";
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
	 *
	 * @return 
	 * @throws SQLException
	 */
	public JoueurEntity getJoueurByLoggin(String ident, String psswd) throws SQLException {
		JoueurEntity result = null;
		String sql = "select * from Joueur\n" +
                "join Utilisateur on Joueur.ID = Utilisateur.JoueurID\n" +
                "where Utilisateur.Identifiant = ? and Utilisateur.MotDePasse = ?";
		// Ouvrir une connexion
		Connection connection = myDataSource.getConnection();
		// On crée un statement pour exécuter une requête
		PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, ident);
                stmt.setString(2, psswd);
		// Un ResultSet pour parcourir les enregistrements du résultat
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
                        int id = rs.getInt("ID");
                        String nom = rs.getString("Nom");
                        String prenom = rs.getString("Prenom");
                        double contribution = rs.getDouble("Contribution");
                        result = new JoueurEntity(id, nom, prenom, contribution);
		}
		// On ferme tout
		rs.close();
		stmt.close();
		connection.close();

		return result;
	}
    
}