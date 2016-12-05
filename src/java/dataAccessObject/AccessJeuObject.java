package dataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.sql.DataSource;

/**
 *
 * @author neiko
 */
public class AccessJeuObject {
    
    private final DataSource myDataSource;

   /**
    *
    * @param dataSource la source de données à utiliser
    */
    public AccessJeuObject(DataSource dataSource) {
	this.myDataSource = dataSource;
    }
    
    public int nbJeux() throws SQLException {
		int result = 0;

		String sql = "SELECT COUNT(*) AS NUMBER FROM Jeu";
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
    
    public ArrayList<String> listJeuJoueurMin(int NbJoueursMin, int NbJoueursMax) throws SQLException {
		ArrayList<String> result = new ArrayList<String>();

		String sql = "SELECT * from Jeu where NbJoueurMin >= ? and NbJoueurMax <= ?";
		// Ouvrir une connexion
		Connection connection = myDataSource.getConnection();
		// On crée un statement pour exécuter une requête
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setInt(1, NbJoueursMin);
                stmt.setInt(2, NbJoueursMax);
		// Un ResultSet pour parcourir les enregistrements du résultat
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) { // + de 1 seul enregistrement
			// On récupère le champ NUMBER de l'enregistrement courant
			result.add(rs.getString("Nom"));
		}
		// On ferme tout
		rs.close();
		stmt.close();
		connection.close();

		return result;
	}
    
    public boolean updateJeu(String nom, int njmi, int njma,
               String desc, int prop, int id) throws SQLException {
		String sql = "update Jeu set Nom = ?, NbJoueurMin = ?,NbJoueurMax = ?, Description = ?, Proprietaire = ? where ID = ?";
		// Ouvrir une connexion
		Connection connection = myDataSource.getConnection();
		// On crée un statement pour exécuter une requête
		PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1,nom);
                stmt.setInt(2,njmi);
                stmt.setInt(3,njma);
                stmt.setString(4,desc);
                stmt.setInt(5,prop);
                stmt.setInt(6,id);
		// Un ResultSet pour parcourir les enregistrements du résultat
		int rs = stmt.executeUpdate();
		// On ferme tout
		stmt.close();
		connection.close();
                
		return rs != 0;
	}
    
    public boolean addJeu(String nom, int njmi, int njma,
               String desc, int prop) throws SQLException {
               
		String sql = "INSERT INTO Jeu(Nom,NbJoueurMin,NbJoueurMax,Description,Proprietaire) VALUES (?,?,?,?,?)";
                    // Ouvrir une connexion
                    Connection connection = myDataSource.getConnection();
                    // On crée un statement pour exécuter une requête
                    PreparedStatement stmt = connection.prepareStatement(sql);
                    
                    stmt.setString(1,nom);
                    stmt.setInt(2,njmi);
                    stmt.setInt(3,njma);
                    stmt.setString(4,desc);
                    stmt.setInt(5,prop);
                
                int rs = stmt.executeUpdate();
                stmt.close();
		connection.close();
		return rs != 0;
	}
    
    public boolean rmJeu(int id) throws SQLException {
           boolean result = true;
           String sql = "delete from Jeu where ID = ?";
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
    
    public ArrayList<JeuEntity> listJeu() throws SQLException {
            ArrayList<JeuEntity> result = new ArrayList<>();
            String sql = "select * from Jeu";
            Connection connection = myDataSource.getConnection();
            // On crée un statement pour exécuter une requête
            Statement stmt = connection.createStatement();
            // Un ResultSet pour parcourir les enregistrements du résultat
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                int JeuId = rs.getInt("ID");
                String nom = rs.getString("Nom");
                int njmi = rs.getInt("NbJoueurMin"); 
                int njma = rs.getInt("NbJoueurMax");
                String desc = rs.getString("Description");
                int prop = rs.getInt("Proprietaire");
                
                JeuEntity j = new JeuEntity(JeuId, nom, njmi, njma, desc, prop);
                result.add(j);
            }
            stmt.close();
            connection.close();
            return result;
       }
    
    public ArrayList<JeuEntity> listJeuUtilisateur(int id) throws SQLException {
            ArrayList<JeuEntity> result = new ArrayList<>();
            String sql = "select * from Jeu where Proprietaire = ?";
            Connection connection = myDataSource.getConnection();
            // On crée un statement pour exécuter une requête
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            // Un ResultSet pour parcourir les enregistrements du résultat
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                int JeuId = rs.getInt("ID");
                String nom = rs.getString("Nom");
                int njmi = rs.getInt("NbJoueurMin"); 
                int njma = rs.getInt("NbJoueurMax");
                String desc = rs.getString("Description");
                int prop = rs.getInt("Proprietaire");
                
                JeuEntity j = new JeuEntity(JeuId, nom, njmi, njma, desc, prop);
                result.add(j);
            }
            stmt.close();
            connection.close();
            return result;
       }
}
