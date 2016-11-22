package servlet;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import dataAccessObject.AccessUtilisateurObject;
import dataAccessObject.UtilisateurEntity;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 *
 * @author rbastide
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {
    
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
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		request.getSession(true);
		// Quelle action a appelé cette servlet ?
		String action = request.getParameter("action");
		if (null != action) {
			switch (action) {
				case "login":
					checkLogin(request);
					break;
				case "logout":
					doLogout(request);
					break;
                                case "update":
                                        doUpdate(request);
                                        break;
			}
		}

		// Est-ce que l'utilisateur est connecté ?
		// On cherche l'attribut customer dans la session
		Object User = findUserInSession(request);
		String jspView = null;
		if (null == User) { // L'utilisateur n'est pas connecté
			// On choisit la page de login
			jspView = "logginForm.jsp";

		} else { // L'utilisateur est connecté
			// On choisit la page d'affichage
                        request.setAttribute("user", user);
			request.getRequestDispatcher("/UserInterface").include(request, response);
		}
		// On va vers la page choisie
		request.getRequestDispatcher(jspView).forward(request, response);

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

	private void checkLogin(HttpServletRequest request) {
		String login = request.getParameter("id");
		String password = request.getParameter("pass");

		try {
			AccessUtilisateurObject dao = new AccessUtilisateurObject(getDataSource());
			user = dao.getUtilisateurByLoggin(login, password);
			if (user != null) { // On a trouvé la combinaison login / password
				// On stocke l'utilisateur dans la session
				HttpSession session = request.getSession(true); // démarre la session
				session.setAttribute("user", user);
				
			} else { // On positionne un message d'erreur pour l'afficher dans la JSP
				request.setAttribute("errorMessage", "Login/Password incorrect");
			}

		} catch (SQLException ex) {
			Logger.getLogger("loginMVC").log(Level.SEVERE, "SQL Exception", ex);
		}
	}

	private void doLogout(HttpServletRequest request) {
		// On termine la session
		request.getSession(false).invalidate();
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
	
	private Object findUserInSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return (session == null) ? null : session.getAttribute("user");
	}

}
