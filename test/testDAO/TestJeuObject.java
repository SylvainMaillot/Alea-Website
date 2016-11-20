package testDAO;

import dataAccessObject.AccessJeuObject;
import dataAccessObject.AccessUtilisateurObject;
import dataAccessObject.JeuEntity;
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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author neiko
 */
public class TestJeuObject {
    
    private static DataSource myDataSource; // La source de données à utiliser
    private static Connection myConnection ;
    
    private AccessJeuObject myDAO;
    
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
        myDAO = new AccessJeuObject(myDataSource);
    }
    
    @After
    public void tearDown() {
        myDAO = null;
    }

    @Test
    public void testCountJeu() throws SQLException {
        int nb = myDAO.nbJeux();
        assertEquals(1, nb);
    }
    
    @Test
    public void testUpdateJeu() throws SQLException {
        String nom = "abc"; 
        int nbjmi = 2;
        int nbjma = 4; 
        String desc = "Lord";
        int idp = 1;
        int id = 1;
        assertTrue(myDAO.updateJeu(nom, nbjmi, nbjma, desc, idp,id));
    }
    
    @Test
    public void testAddJeu() throws SQLException {
        String nom = "abc"; 
        int nbjmi = 2;
        int nbjma = 4; 
        String desc = "Lord";
        int idp = 2;
        assertTrue(myDAO.addJeu(nom, nbjmi, nbjma, desc, idp));
    }
    
    @Test
    public void testRmJeu() throws SQLException {
        assertTrue(myDAO.rmJeu(2));
    }
    
    @Test
    public void testListAllJeu() throws SQLException {
        assertEquals(1,myDAO.listJeu().size());
    }
    
    @Test
    public void testListJeuUtilisateur() throws SQLException {
        assertEquals(0,myDAO.listJeuUtilisateur(2).size());
    }
}
