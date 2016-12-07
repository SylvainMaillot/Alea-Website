package servlet;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import dataAccessObject.AccessJeuObject;
import dataAccessObject.AccessUtilisateurObject;
import dataAccessObject.UtilisateurEntity;
import java.io.IOException;
import java.nio.charset.Charset;
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
            throws ServletException, IOException, SQLException {
            request.getSession(true);
            // Quelle action a appelé cette servlet ?
            String action = request.getParameter("action");
            if (null != action) {
                    switch (action) {
                            case "login":
                                //Verifie le login via la fonction de verification et connection
                                checkLogin(request);
                            break;
                            case "logout":
                                //déconnection
                                doLogout(request);
                            break;
                            case "Mettre a jour":
                                //Mise a jour de l'utilisateur
                                doUpdate(request);
                            break;
                            case "Annuler":
                                request.getRequestDispatcher("playerInfos.jsp").include(request, response);
                            break;
                            case "updateInfo":
                                request.setAttribute("user", user);
                                // chargement de la jsp updateInfos
                                request.getRequestDispatcher("updateInfos.jsp").forward(request, response);
                            break;
                            case "liste des joueurs":
                                // chargement de la jsp listPlayer
                                request.getRequestDispatcher("listPlayer.jsp").forward(request, response);
                            break;
                            case "liste des jeux":
                                request.setAttribute("id",user.getUserId());
                                request.getRequestDispatcher("/Jeu").include(request, response);
                            break;
                            case "M'inscrire":
                                //déclaration des variables utilisées
                                //on caste en UTF-8 pour eviter les problèmes d'encodage dans la BDD
                                request.setCharacterEncoding("UTF-8");
                                response.setCharacterEncoding("UTF-8");
                                String S1 = request.getParameter("pseudo");
                                String ID = new String(S1.getBytes(),Charset.forName("UTF-8"));
                                String S2 = request.getParameter("nom");
                                String nom = new String(S2.getBytes(),Charset.forName("UTF-8"));
                                String S3 = request.getParameter("prenom");
                                String prenom = new String(S3.getBytes(),Charset.forName("UTF-8"));
                                String S4 = request.getParameter("passe");
                                String passe = new String(S4.getBytes(),Charset.forName("UTF-8"));
                                String S5 = request.getParameter("email");
                                String mail = new String(S5.getBytes(),Charset.forName("UTF-8"));
                                AccessUtilisateurObject ajo = new AccessUtilisateurObject(getDataSource());
                                //Essay de création de compte
                                try {ajo.newUtilisateur(ID, nom, prenom, passe, mail);} catch (Exception e){
                                    request.setAttribute("errorMessage", "Utilisateur dejà crée !");
                                    request.getRequestDispatcher("inscription.jsp").forward(request, response);}
                            break;    
                    }
            }
            // Est-ce que l'utilisateur est connecté ?
            // On cherche l'attribut customer dans la session
            Object User = findUserInSession(request);
            String jspView = null;
            if (null == User) { // L'utilisateur n'est pas connecté
                    // chargement de la jsp Accueil
                    jspView = "Accueil.jsp";

            } else { // L'utilisateur est connecté
                    // On impose l'encodage
                    request.setCharacterEncoding("UTF-8");
                    response.setCharacterEncoding("UTF-8");
                    // Transmission des données
                    request.setAttribute("prenom", user.getPrenom());
                    request.setAttribute("nom", user.getNom());
                    request.setAttribute("contrib", user.getContribution());
                    request.setAttribute("id", user.getUserId());
                    // On choisit la page d'affichage

                    jspView = "playerInfos.jsp";
                    request.getRequestDispatcher(jspView).forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
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
    //Fonction pour verifier le login entré dans la base de donnée
    private void checkLogin(HttpServletRequest request) {
            //Initialisation des variables
            String S1 = request.getParameter("id");
            String login = new String(S1.getBytes(),Charset.forName("UTF-8"));
            String S2 = request.getParameter("pass");
            String password = new String(S2.getBytes(),Charset.forName("UTF-8"));

            try {
                    AccessUtilisateurObject dao = new AccessUtilisateurObject(getDataSource());
                    try {
                        user = dao.getUtilisateurByLoggin(login, password);
                        if (user != null) { // On a trouvé la combinaison login / password
                            // On stocke l'utilisateur dans la session
                            HttpSession session = request.getSession(true); // démarre la session
                            session.setAttribute("user", user);
                        } else {
                            request.setAttribute("errorMessage", "Login/Password incorrect");
                                }
                    } 
                    catch (Exception e) {
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

    //Fonction pour retrouver l'utilisateur actuellement connecté
    private Object findUserInSession(HttpServletRequest request) {
            HttpSession session = request.getSession(false);
            return (session == null) ? null : session.getAttribute("user");
    }

    //Fonction pour essayer de changer l'utilisateur
    private void doUpdate(HttpServletRequest request) {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String passwd = request.getParameter("passwd");
        String mail = request.getParameter("mail");
        float contrib = Float.parseFloat(request.getParameter("contrib"));
        int typeUser = Integer.parseInt(request.getParameter("type"));

        try {
            AccessUtilisateurObject dao = new AccessUtilisateurObject(getDataSource());
            dao.updateUtilisateur(nom, prenom, passwd, mail, contrib,
                    typeUser, user.getUserId());
            user = dao.getUtilisateurByLoggin(user.getIdentifiant(), passwd);
            request.setAttribute("user", user);
        } catch (SQLException ex) {
            Logger.getLogger("UpdateInfos").log(Level.SEVERE, "SQL Exception", ex);
        }
    }
}
