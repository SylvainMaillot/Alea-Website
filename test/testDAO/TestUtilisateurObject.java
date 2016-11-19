package testDAO;

import dataAccessObject.AccessUtilisateurObject;
import dataAccessObject.UtilisateurEntity;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.hsqldb.cmdline.SqlFile;
import org.hsqldb.cmdline.SqlToolError;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author neiko
 */
public class TestUtilisateurObject {
    
    private static DataSource myDataSource; // La source de données à utiliser
    private static Connection myConnection ;
    
    private AccessUtilisateurObject myDAO;
    
    public static DataSource getDataSource() throws SQLException {
	org.hsqldb.jdbc.JDBCDataSource ds = new org.hsqldb.jdbc.JDBCDataSource();
	ds.setDatabase("jdbc:hsqldb:mem:testcase;shutdown=true");
	ds.setUser("sa");
	ds.setPassword("sa");
	return ds;
    }
    
    @BeforeClass
    public static void setUpClass() throws SQLException, IOException, SqlToolError {
        // On crée la connection vers la base de test "in memory"
		myDataSource = getDataSource();
		myConnection = myDataSource.getConnection();
		// On initialise la base avec le contenu d'un fichier de test
		String sqlFilePath = TestUtilisateurObject.class.getResource("testData.sql").getFile();
		SqlFile sqlFile = new SqlFile(new File(sqlFilePath));

		sqlFile.setConnection(myConnection);
		sqlFile.execute();
		sqlFile.closeReader();
    }
    
    @AfterClass
    public static void tearDownClass() throws SQLException {
        myConnection.close();
    }
    
    @Before
    public void setUp() {
        myDAO = new AccessUtilisateurObject(myDataSource);
    }
    
    @After
    public void tearDown() {
        myDAO = null;
    }

    @Test
    public void testCountPlayers() throws SQLException {
        int nb = myDAO.nbUtilisateurs();
        assertEquals(5, nb);
    }
    
    @Test
    public void testLoggin() throws SQLException {
        UtilisateurEntity utilisateur = myDAO.getUtilisateurByLoggin("Neiko", "Nyaa");
        assertEquals(utilisateur.getPrenom(), "Sylvain");
        utilisateur = myDAO.getUtilisateurByLoggin("Neiko", "nyaa");
        assertEquals(utilisateur, null);
    }
    
    @Test
    public void testUpdate() throws SQLException {
        String MotDePasse = "abc"; 
        String Email = "ca@ca";
        String Prenom = "Chips"; 
        String Nom = "Lord";
        float contrib = 3.5f;
        int type = 1;
        
        UtilisateurEntity utilisateur = myDAO.getUtilisateurByLoggin("Neiko", "Nyaa");
        assertTrue(myDAO.updateUtilisateur(Nom, Prenom, MotDePasse, Email, 
                contrib, type,utilisateur.getUserId()));
        
        UtilisateurEntity utilisateur2 = myDAO.getUtilisateurByLoggin("Neiko", "abc");
        assertEquals(utilisateur2.getEmail(), Email);
        assertEquals(utilisateur2.getMotDePasse(), MotDePasse);
        assertEquals(utilisateur2.getPrenom(), Prenom);
        assertEquals(utilisateur2.getNom(), Nom);
        assertEquals(utilisateur2.getTypeUtilisateur(), type);
    }
    
    @Test
    public void testNewUser() throws SQLException {
        String ident = "Neiw";
        String MotDePasse = "mdp"; 
        String Email = "ca@ca.fr";
        String Prenom = "Haku"; 
        String Nom = "Byako";
        assertTrue(myDAO.newUtilisateur(ident, Nom, Prenom, MotDePasse, Email));
        UtilisateurEntity us1 = myDAO.getUtilisateurByLoggin(ident, MotDePasse);
        assertEquals(Prenom, us1.getPrenom());
    }
    
    @Test
    public void testListUser() throws SQLException {
        ArrayList<UtilisateurEntity> userList = myDAO.listUtilisateur();
        assertEquals(userList.size(), myDAO.nbUtilisateurs());
    }
    
}
