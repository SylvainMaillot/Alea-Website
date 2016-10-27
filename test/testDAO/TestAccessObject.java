package testDAO;

import dataAccessObject.AccessObject;
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
public class TestAccessObject {
    
    private static DataSource myDataSource; // La source de données à utiliser
    private static Connection myConnection ;
    
    private AccessObject myDAO;
    
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
		String sqlFilePath = TestAccessObject.class.getResource("testData.sql").getFile();
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
        myDAO = new AccessObject(myDataSource);
    }
    
    @After
    public void tearDown() {
        myDAO = null;
    }

    @Test
    public void testCountPlayers() throws SQLException {
        int nb = myDAO.nbJoueurs();
        assertEquals(4, nb);
    }
}
