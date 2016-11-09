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
}
