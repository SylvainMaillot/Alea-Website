package dataAccessObject;

import java.sql.Connection;
import java.sql.Date;
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
public class AccessSoireeObject {
    
    private final DataSource myDataSource;

   /**
    *
    * @param dataSource la source de données à utiliser
    */
    public AccessSoireeObject(DataSource dataSource) {
	this.myDataSource = dataSource;
    }
    
    public boolean addSoiree(Date jour, String nom, int nbj,
               int nba, String desc) throws SQLException {
               
		String sql = "INSERT INTO Soiree(Jour,Nom,NbJoueur,"
                        + "NbAdherant,Description) VALUES (?,?,"
                        + "?,?,?)";
                    // Ouvrir une connexion
                    Connection connection = myDataSource.getConnection();
                    // On crée un statement pour exécuter une requête
                    PreparedStatement stmt = connection.prepareStatement(sql);
                    
                    stmt.setDate(1,jour);
                    stmt.setString(2,nom);
                    stmt.setInt(3,nbj);
                    stmt.setInt(4,nba);
                    stmt.setString(5,desc);
                
                int rs = stmt.executeUpdate();
                stmt.close();
		connection.close();
		return rs != 0;
	}
    
     public boolean updateSoiree(Date jour, String nom, int nbj,
               int nba, String desc, int id) throws SQLException {
		String sql = "update Soiree set Jour = ?, Nom = ?,"
                        + "NbJoueur = ?, NbAdherant = ?, Description = ?"
                        + "where ID = ?;";
		// Ouvrir une connexion
		Connection connection = myDataSource.getConnection();
		// On crée un statement pour exécuter une requête
		PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setDate(1,jour);
                stmt.setString(2,nom);
                stmt.setInt(3,nbj);
                stmt.setInt(4,nba);
                stmt.setString(5,desc);
                stmt.setInt(6,id);
		// Un ResultSet pour parcourir les enregistrements du résultat
		int rs = stmt.executeUpdate();
		// On ferme tout
		stmt.close();
		connection.close();
                
		return rs != 0;
	}
     
     public boolean rmSoiree(int id) throws SQLException {
           boolean result = true;
           String sql = "delete from Soiree where ID = ?";
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

     public ArrayList<SoireeEntity> listSoiree() throws SQLException {
            ArrayList<SoireeEntity> result = new ArrayList<>();
            String sql = "select * from Soiree";
            Connection connection = myDataSource.getConnection();
            // On crée un statement pour exécuter une requête
            Statement stmt = connection.createStatement();
            // Un ResultSet pour parcourir les enregistrements du résultat
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                int soireeId = rs.getInt("ID");
                Date jour = rs.getDate("Jour");
                String nom = rs.getString("Nom");
                int nbj = rs.getInt("NbJoueur"); 
                int nba = rs.getInt("NbAdherant");
                String desc = rs.getString("Description");
                
                SoireeEntity s = new SoireeEntity(soireeId, jour, nom, nbj, nba, desc);
                result.add(s);
            }
            stmt.close();
            connection.close();
            return result;
       }
     
     public SoireeEntity GetSoireeByID(int ID) throws SQLException {
		SoireeEntity result = null;

		String sql = "SELECT * from Soiree where ID = ?";
		// Ouvrir une connexion
		Connection connection = myDataSource.getConnection();
		// On crée un statement pour exécuter une requête
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setInt(1, ID);
		// Un ResultSet pour parcourir les enregistrements du résultat
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
                    int soireeId = rs.getInt("ID");
                    Date jour = rs.getDate("Jour");
                    String nom = rs.getString("Nom");
                    int nbj = rs.getInt("NbJoueur"); 
                    int nba = rs.getInt("NbAdherant");
                    String desc = rs.getString("Description");
                
                    result = new SoireeEntity(soireeId, jour, nom, nbj, nba, desc);
                }   
		// On ferme tout
		rs.close();
		stmt.close();
		connection.close();

		return result;
	}
    
}
