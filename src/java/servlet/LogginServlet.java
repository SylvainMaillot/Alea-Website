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
import javax.sql.DataSource;

/**
 *
 * @author neiko
 */
public class LogginServlet extends HttpServlet {
    
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
            throws ServletException, IOException, SQLException {
        
        String identifiant = request.getParameter("id");
        String mdp = request.getParameter("pass");
        AccessUtilisateurObject ao = new AccessUtilisateurObject(getDataSource());
        
        if (identifiant == null && mdp == null) {
            request.getRequestDispatcher("logginForm.jsp").forward(request, response);
        } else {
            UtilisateurEntity je = ao.getUtilisateurByLoggin(identifiant, mdp);
            if (je != null) {
                request.setAttribute("nom", je.getNom());
                request.setAttribute("prenom", je.getPrenom());
                request.setAttribute("contrib", je.getContribution());
                request.getRequestDispatcher("playerInfos.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("logginFails.jsp").forward(request, response);
            }
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
                    Logger.getLogger(LogginServlet.class.getName()).log(Level.SEVERE, null, ex);
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
                    Logger.getLogger(LogginServlet.class.getName()).log(Level.SEVERE, null, ex);
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
