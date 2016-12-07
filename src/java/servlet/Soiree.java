/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import dataAccessObject.AccessJeuObject;
import dataAccessObject.AccessSoireeObject;
import dataAccessObject.JeuEntity;
import dataAccessObject.SoireeEntity;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author neiko
 */
@WebServlet(name = "Soiree", urlPatterns = {"/Soiree"})
public class Soiree extends HttpServlet {
    
    public DataSource getDataSource() throws SQLException {
		com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new MysqlConnectionPoolDataSource();
                ds.setDatabaseName("Alea");
		ds.setUser("root");
		ds.setPassword("root");
		// The host on which Network Server is running
		ds.setServerName("localhost");
		// port on which Network Server is listening
		ds.setPortNumber(3306);
		return ds;
	}

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        
        AccessSoireeObject dao = new AccessSoireeObject(getDataSource());
        ArrayList<SoireeEntity> listSoiree;
        SoireeEntity soiree;
        int soireeID;
        String nom, desc;
        java.util.Date jour;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        
        switch(request.getParameter("action")){
            case "Accueil":
                request.getRequestDispatcher("playerInfos.jsp").forward(request, response);
                break;
            case "Supprimer": 
                soireeID = Integer.parseInt(request.getParameter("soireeID"));
                dao.rmSoiree(soireeID);
                listSoiree = dao.listSoiree();
                request.setAttribute("soirees", listSoiree);
                request.getRequestDispatcher("listSoiree.jsp").forward(request, response);
                break;
            case "Editer":
                soireeID = Integer.parseInt(request.getParameter("soireeID"));
                soiree = dao.GetSoireeByID(soireeID);
                request.setAttribute("soiree", soiree);
                request.getRequestDispatcher("editeSoiree.jsp").forward(request, response);
                break;
            case "Ajouter":                
                jour = format.parse(request.getParameter("date"));
                String S1 = request.getParameter("nom");
                nom = new String(S1.getBytes(),Charset.forName("UTF-8"));
                String S2 = request.getParameter("description");
                desc = new String(S2.getBytes(),Charset.forName("UTF-8"));
                dao.addSoiree(new java.sql.Date(jour.getTime()), nom, 0, 0, desc);
                listSoiree = dao.listSoiree();
                request.setAttribute("soirees", listSoiree);
                request.getRequestDispatcher("listSoiree.jsp").forward(request, response);                
                break;
            case "Modifier":
                jour = format.parse(request.getParameter("date"));
                String S3 = request.getParameter("nom");
                nom = new String(S3.getBytes(),Charset.forName("UTF-8"));
                String S4 = request.getParameter("description");
                desc = new String(S4.getBytes(),Charset.forName("UTF-8"));
                soireeID = Integer.parseInt(request.getParameter("soireeID"));
                soiree = dao.GetSoireeByID(soireeID);
                dao.updateSoiree(new java.sql.Date(jour.getTime()), nom, soiree.getNbJoueur(),soiree.getNbJoueur(), desc,soireeID);
                listSoiree = dao.listSoiree();
                request.setAttribute("soirees", listSoiree);
                request.getRequestDispatcher("listSoiree.jsp").forward(request, response);
                break;
                
            case "Voir les soirees":
                
                listSoiree = dao.listSoiree();
                request.setAttribute("soirees", listSoiree);
                request.getRequestDispatcher("listSoiree.jsp").forward(request, response);
                break;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Soiree.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Soiree.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Soiree.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Soiree.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
