package testDAO;

import dataAccessObject.AccessUtilisateurObject;
import dataAccessObject.UtilisateurEntity;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
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
        assertEquals(4, nb);
    }
    
    
    //Petit soucis avec ce test
    @Test
    public void testLoggin() throws SQLException {
        UtilisateurEntity utilisateur = myDAO.getUtilisateurByLoggin("Neiko", "Nyaa");
        assertEquals(utilisateur.getPrenom(), "Prenom");
        utilisateur = myDAO.getUtilisateurByLoggin("Neiko", "nyaa");
        assertEquals(utilisateur, null);
    }
    
    @Test
    public void testUpdateNom() throws SQLException {
        UtilisateurEntity utilisateur = myDAO.getUtilisateurByLoggin("Neiko", "Nyaa");
        assertTrue(myDAO.updateUtilisateurLastName("Nom", utilisateur.getUserId()));
        utilisateur = myDAO.getUtilisateurByLoggin("Neiko", "Nyaa");
        assertEquals(utilisateur.getNom(), "Nom");
        assertFalse(myDAO.updateUtilisateurLastName("Chiwa", 7));
    }
   
    @Test
    public void testUpdatePrenom() throws SQLException {
        UtilisateurEntity utilisateur = myDAO.getUtilisateurByLoggin("Neiko", "Nyaa");
        assertTrue(myDAO.updateUtilisateurFristName("Prenom", utilisateur.getUserId()));
        utilisateur = myDAO.getUtilisateurByLoggin("Neiko", "Nyaa");
        assertEquals(utilisateur.getPrenom(), "Prenom");
        assertFalse(myDAO.updateUtilisateurFristName("Blabla", 7));
    }
    
    @Test
    public void testUpdateMail() throws SQLException {
        UtilisateurEntity utilisateur = myDAO.getUtilisateurByLoggin("Neiko", "Nyaa");
        assertTrue(myDAO.updateUtilisateurMail("test@hotmail.fr", utilisateur.getUserId()));
        utilisateur = myDAO.getUtilisateurByLoggin("Neiko", "Nyaa");
        assertEquals(utilisateur.getEmail(), "test@hotmail.fr");
        assertFalse(myDAO.updateUtilisateurMail("test@hotmail.fr", 7));
    }
    
    @Test
    public void testUpdateContribution() throws SQLException {
        float newContrib = 5.95f;
        
        UtilisateurEntity utilisateur = myDAO.getUtilisateurByLoggin("Neiko", "Nyaa");
        assertTrue(myDAO.updateUtilisateurContribution(newContrib, utilisateur.getUserId()));
        utilisateur = myDAO.getUtilisateurByLoggin("Neiko", "Nyaa");
        System.out.println(utilisateur.getContribution());
        assertFalse(myDAO.updateUtilisateurContribution(newContrib, 7));
    }
    
}
