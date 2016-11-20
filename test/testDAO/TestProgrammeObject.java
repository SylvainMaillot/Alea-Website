package testDAO;

import dataAccessObject.AccessJeuObject;
import dataAccessObject.AccessProgrammeObject;
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
public class TestProgrammeObject {
    
    private static DataSource myDataSource; // La source de données à utiliser
    private static Connection myConnection ;
    
    private AccessProgrammeObject myDAO;
    
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
        myDAO = new AccessProgrammeObject(myDataSource);
    }
    
    @After
    public void tearDown() {
        myDAO = null;
    }
    
    @Test
    public void testAddJeu2Soiree() throws SQLException {
        assertFalse(myDAO.addJeu2Soiree(1,1));
        assertTrue(myDAO.addJeu2Soiree(1,4));
    }
    
    @Test
    public void testRmJeu2Soiree() throws SQLException {
        assertTrue(myDAO.rmJeu2Soiree(1,3));
    }
    
    @Test
    public void testListJeu2Soiree() throws SQLException {
        assertEquals(2, myDAO.listJeu2Soiree(1).size());
    }
    
    @Test
    public void testListSoiree2Jeu() throws SQLException {
        assertEquals(5, myDAO.listSoiree2Jeu(2).size());
    }
}
