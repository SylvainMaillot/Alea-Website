package dataAccessObject;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
    
    public boolean addUser2Soiree(int soireeID, int userID) throws SQLException {
		boolean result = true;
                String sql = "INSERT INTO Programme VALUES (?,?)";
                    // Ouvrir une connexion
                    Connection connection = myDataSource.getConnection();
                    // On crée un statement pour exécuter une requête
                    PreparedStatement stmt = connection.prepareStatement(sql);
                    stmt.setInt(1,soireeID);
                    stmt.setInt(2,userID);
                try {
                    int rs = stmt.executeUpdate();
                } catch (SQLException s) {
                    result = false;
                }
                stmt.close();
		connection.close();
		return result;
	}
     
     public boolean rmUser2Soiree(int soireeID, int userID) throws SQLException {
           boolean result = true;
           String sql = "delete from Programme where SoireeID = ? and UtilisateurID = ?";
           Connection connection = myDataSource.getConnection();
           PreparedStatement stmt = connection.prepareStatement(sql);
           stmt.setInt(1, soireeID);
           stmt.setInt(2, userID);
           try {
                stmt.execute();
           } catch (SQLException e) {
                result = false;
           }
           stmt.close();
           connection.close();
           return result;
       }

     public Map<Integer,Integer> nbUser2Soiree() throws SQLException {
	Map<Integer,Integer> result = new HashMap<>();
	String sql = "select soireeID,count(UtilisateurID) as total from Programme group by soireeID;";
	try(Connection connection = myDataSource.getConnection();
	Statement stmt = connection.createStatement();
	ResultSet rs = stmt.executeQuery(sql)) {
            while(rs.next()) {
                    int soiree = rs.getInt("soireeID");
                    int nb = rs.getInt("total");
                    result.put(soiree,nb);
                }
            }
	return result;	
	}

//TODO

    /**
     *
     * @param soireeID
     * @return
     * @throws SQLException
     */
     public Map<Integer,ArrayList<Integer>> listIdUser2Soiree() throws SQLException {
            Map<Integer,ArrayList<Integer>> result = new HashMap<>();
            ArrayList<Integer> tmp = new ArrayList<>();
            String sql = "select * from Programme;";
            Connection connection = myDataSource.getConnection();
            // On crée un statement pour exécuter une requête
            Statement stmt = connection.createStatement();
            // Un ResultSet pour parcourir les enregistrements du résultat
            ResultSet rs = stmt.executeQuery(sql);
            int s=0;
            while(rs.next()) {
                int soiree = rs.getInt("SoireeID");
                int userID = rs.getInt("UtilisateurID");
                if(soiree != s)
                    tmp = new ArrayList<>();
                s=soiree;
                tmp.add(userID);
                result.put(soiree,tmp);
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
