package dataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        
        public UtilisateurEntity getUtilisateurByID(int ID) throws SQLException {
       		UtilisateurEntity result = null;
		String sql = "select * from Utilisateur\n" +
                "where ID= ?";
		// Ouvrir une connexion
		Connection connection = myDataSource.getConnection();
		// On crée un statement pour exécuter une requête
		PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setInt(1, ID);
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
               String mail, float contrib, int type, int id) throws SQLException {
		String sql = "update Utilisateur set Nom = ?, Prenom = ?,"
                        + "MotDePasse = ?, Email = ?, Contribution = ?,"
                        + "TypeUtilisateur = ? where ID = ?";
		// Ouvrir une connexion
		Connection connection = myDataSource.getConnection();
		// On crée un statement pour exécuter une requête
		PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1,nom);
                stmt.setString(2,prenom);
                stmt.setString(3,psswd);
                stmt.setString(4,mail);
                stmt.setFloat(5,contrib);
                stmt.setInt(6,type);
                stmt.setInt(7, id);
		// Un ResultSet pour parcourir les enregistrements du résultat
		int rs = stmt.executeUpdate();
		// On ferme tout
		stmt.close();
		connection.close();
                
		return rs != 0;
	}
       
       public boolean newUtilisateur(String ident, String nom, String prenom, 
               String psswd,String mail) throws SQLException {
               
		String sql = "INSERT INTO Utilisateur(Identifiant,MotDePasse,Email,Prenom,Nom,Contribution,TypeUtilisateur) VALUES (?,?,?,?,?,?,?)";
                    // Ouvrir une connexion
                    Connection connection = myDataSource.getConnection();
                    // On crée un statement pour exécuter une requête
                    PreparedStatement stmt = connection.prepareStatement(sql);

                    float contrib = 0.0f;
                    int type = 0;
                    stmt.setString(1,ident);
                    stmt.setString(2,psswd);
                    stmt.setString(3,mail);
                    stmt.setString(4,prenom);
                    stmt.setString(5,nom);
                    stmt.setFloat(6,contrib);
                    stmt.setInt(7,type);
                
                int rs = stmt.executeUpdate();
                stmt.close();
		connection.close();
		return rs != 0;
	}
       
       public ArrayList<UtilisateurEntity> listUtilisateur() throws SQLException {
            ArrayList<UtilisateurEntity> result = new ArrayList<>();
            String sql = "select * from Utilisateur";
            Connection connection = myDataSource.getConnection();
            // On crée un statement pour exécuter une requête
            Statement stmt = connection.createStatement();
            // Un ResultSet pour parcourir les enregistrements du résultat
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                int UserId = rs.getInt("ID");
                String Identifiant = rs.getString("Identifiant");
                String MotDePasse = rs.getString("MotDePasse"); 
                String Email = rs.getString("Email");
                String Nom = rs.getString("Nom");
                String Prenom = rs.getString("Prenom");
                float Contribution = rs.getFloat("Contribution");
                int TypeUtilisateur = rs.getInt("TypeUtilisateur");
                double contribution = rs.getDouble("Contribution");
                UtilisateurEntity u = new UtilisateurEntity(UserId, Identifiant, 
                    MotDePasse,Email, Prenom, Nom, Contribution, TypeUtilisateur);
                result.add(u);
            }
            stmt.close();
            connection.close();
            return result;
       }
       
       public boolean suprUtilisateur(int id) throws SQLException {
           boolean result = true;
           String sql = "delete from Utilisateur where id = ?";
           Connection connection = myDataSource.getConnection();
           PreparedStatement stmt = connection.prepareStatement(sql);
           stmt.setInt(1, id);
           try {
                stmt.execute();
           } catch (SQLException e) {
                result = false;
           }
           stmt.close();
           connection.close();
           return result;
       }
       
       public Map<String, Integer> gameByUser() throws SQLException {
		Map<String, Integer> result = new HashMap<>();
		String sql = "SELECT Identifiant,count(jeu.id) AS total FROM utilisateur\n" +
                                "LEFT JOIN jeu ON (jeu.`Proprietaire`=utilisateur.`ID`)\n" +
                                "GROUP BY `Identifiant`;";
		try (Connection connection = myDataSource.getConnection(); 
		     Statement stmt = connection.createStatement(); 
		     ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				// On récupère les champs nécessaires de l'enregistrement courant
				String name = rs.getString("Identifiant");
				int totalJeu = rs.getInt("total");
				// On l'ajoute à la liste des résultats
				result.put(name, totalJeu);
			}
		}
		return result;
	}
       
       public Map<String, Float> bestDonator() throws SQLException {
		Map<String, Float> result = new HashMap<>();
		String sql = "SELECT Identifiant,Contribution FROM utilisateur;";
		try (Connection connection = myDataSource.getConnection(); 
		     Statement stmt = connection.createStatement(); 
		     ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				// On récupère les champs nécessaires de l'enregistrement courant
				String name = rs.getString("Identifiant");
				float contrib = rs.getFloat("Contribution");
				// On l'ajoute à la liste des résultats
				result.put(name, contrib);
			}
		}
		return result;
	}
}