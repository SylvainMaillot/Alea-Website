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
public class AccessProgrammeObject {
    
    private final DataSource myDataSource;

   /**
    *
    * @param dataSource la source de données à utiliser
    */
    public AccessProgrammeObject(DataSource dataSource) {
	this.myDataSource = dataSource;
    }
    
    public boolean addJeu2Soiree(int soireeID, int jeuID) throws SQLException {
		boolean result = true;
                String sql = "INSERT INTO Programme VALUES (?,?)";
                    // Ouvrir une connexion
                    Connection connection = myDataSource.getConnection();
                    // On crée un statement pour exécuter une requête
                    PreparedStatement stmt = connection.prepareStatement(sql);
                    stmt.setInt(1,soireeID);
                    stmt.setInt(2,jeuID);
                try {
                    int rs = stmt.executeUpdate();
                } catch (SQLException s) {
                    result = false;
                }
                stmt.close();
		connection.close();
		return result;
	}
     
     public boolean rmJeu2Soiree(int soireeID, int jeuID) throws SQLException {
           boolean result = true;
           String sql = "delete from Programme where SoireeID = ? and JeuID = ?";
           Connection connection = myDataSource.getConnection();
           PreparedStatement stmt = connection.prepareStatement(sql);
           stmt.setInt(1, soireeID);
           stmt.setInt(2, jeuID);
           try {
                stmt.execute();
           } catch (SQLException e) {
                result = false;
           }
           stmt.close();
           connection.close();
           return result;
       }

     public ArrayList<SoireeEntity> listJeu2Soiree(int jeuID) throws SQLException {
            ArrayList<SoireeEntity> result = new ArrayList<>();
            String sql = "select * from Programme right join Soiree s on Programme.SoireeID"
                    + " = s.ID where Programme.JeuID = ?;";
            Connection connection = myDataSource.getConnection();
            // On crée un statement pour exécuter une requête
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, jeuID);
            // Un ResultSet pour parcourir les enregistrements du résultat
            ResultSet rs = stmt.executeQuery();
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
    
     public ArrayList<JeuEntity> listSoiree2Jeu(int soireeID) throws SQLException {
            ArrayList<JeuEntity> result = new ArrayList<>();
            String sql = "select * from Programme right join Jeu j on Programme.JeuID"
                    + " = j.ID where Programme.SoireeID = ?;";
            Connection connection = myDataSource.getConnection();
            // On crée un statement pour exécuter une requête
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, soireeID);
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
