/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testDAO;

import dataAccessObject.AccessJeuObject;
import dataAccessObject.AccessSoireeObject;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.hsqldb.cmdline.SqlFile;
import org.hsqldb.cmdline.SqlToolError;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author neiko
 */
public class TestSoireeeObject {
    
    private static DataSource myDataSource; // La source de données à utiliser
    private static Connection myConnection ;
    
    private AccessSoireeObject myDAO;
    
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
        myDAO = new AccessSoireeObject(myDataSource);
    }
    
    @After
    public void tearDown() {
        myDAO = null;
    }

    @Test
    public void testCountSoiree() throws SQLException {
        int nb = myDAO.listSoiree().size();
        assertEquals(2, nb);
    }
    
    @Test
    public void testUpdateSoiree() throws SQLException {
        Date j = new Date(2016-07-19);
        String nom = "Soiree 1"; 
        int nbj = 5;
        int nba = 8; 
        String desc = "HAHAHA";
        int id = 1;
        assertTrue(myDAO.updateSoiree(j,nom, nbj, nba, desc,id));
    }
    
    @Test
    public void testAddSoiree() throws SQLException {
        Date j = new Date(2016-07-20);
        String nom = "Soiree 2"; 
        int nbj = 5;
        int nba = 8; 
        String desc = "HAHOHAHA";
        assertTrue(myDAO.addSoiree(j, nom, nbj, nba, desc));
    }
    
    @Test
    public void testRmSoiree() throws SQLException {
        assertTrue(myDAO.rmSoiree(2));
    }
    
    @Test
    public void testListJeu() throws SQLException {
        assertEquals(1,myDAO.listSoiree().size());
    }
    
}

