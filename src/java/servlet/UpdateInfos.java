/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import dataAccessObject.AccessUtilisateurObject;
import dataAccessObject.UtilisateurEntity;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 *
 * @author neiko
 */
public class UpdateInfos extends HttpServlet {
    
    private UtilisateurEntity user;
    
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
            throws ServletException, IOException {
        user = (UtilisateurEntity) request.getAttribute("user");
        request.getSession(true);
        
        String action = request.getParameter("action3");
        if (action != null && action.equals("update")) {
            doUpdate(request);
            request.getRequestDispatcher("/UserInterface").include(request, response);
        }
        
        String jspView = "updateInfos.jsp";
        request.setAttribute("prenom", user.getPrenom());
        request.setAttribute("nom", user.getNom());
        request.setAttribute("passwd", user.getMotDePasse());
        request.setAttribute("mail", user.getEmail());
        request.getRequestDispatcher(jspView).forward(request, response);
        
    }
    
    private void doUpdate(HttpServletRequest request) {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String passwd = request.getParameter("passwd");
        String mail = request.getParameter("mail");
        
        try {
            AccessUtilisateurObject dao = new AccessUtilisateurObject(getDataSource());
            dao.updateUtilisateur(nom, prenom, passwd, mail, user.getContribution(),
                    user.getTypeUtilisateur(), user.getUserId());
            user = dao.getUtilisateurByLoggin(user.getIdentifiant(), passwd);
            request.setAttribute("user", user);
        } catch (SQLException ex) {
            Logger.getLogger("UpdateInfos").log(Level.SEVERE, "SQL Exception", ex);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
