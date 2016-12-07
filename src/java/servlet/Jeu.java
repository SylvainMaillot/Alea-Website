/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import dataAccessObject.AccessJeuObject;
import dataAccessObject.JeuEntity;
import dataAccessObject.UtilisateurEntity;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;
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
 * @author Hp
 */
@WebServlet(name = "Jeu", urlPatterns = {"/Jeu"})
public class Jeu extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        
        int gameID,nbJoueurMin,nbJoueurMax,proprietaireJeu;
        AccessJeuObject ajo;
        String nom = request.getParameter("Nom");
        String descript = request.getParameter("Description");
        ArrayList<JeuEntity> allJeu;
        UtilisateurEntity User = (UtilisateurEntity) findUserInSession(request);
        
        //récupére les résultats de la requette en fonction des cas 
        switch(request.getParameter("action")){
            case "Accueil":
                //retourner a la jsp l'accueil
                request.getRequestDispatcher("LoginController").include(request, response);
                break;
            case "Supprimer": 
                //déclaration des variables utilisées
                gameID = Integer.parseInt(request.getParameter("idJeu"));
                ajo = new AccessJeuObject(getDataSource());
                ajo.rmJeu(gameID);
                // initialisation du ${jeu} utilisé dans la jsp
                request.setAttribute("jeu", ajo.listJeuUtilisateur(User.getUserId()));
                // initialisation du ${allJeu} utilisé dans la jsp
                allJeu = ajo.listJeu();
                request.setAttribute("allJeu", allJeu);
                // rechargement de la jsp listJeux
                request.getRequestDispatcher("listJeux.jsp").forward(request, response);
                break;
            case "Editer":
                //déclaration des variables utilisées
                gameID = Integer.parseInt(request.getParameter("idJeu"));
                nbJoueurMin = Integer.parseInt(request.getParameter("nbJoueurMin"));
                nbJoueurMax = Integer.parseInt(request.getParameter("nbJoueurMax"));
                proprietaireJeu = Integer.parseInt(request.getParameter("proprietaireJeu"));
                ajo = new AccessJeuObject(getDataSource());
                JeuEntity jeu = ajo.GetJeuByID(gameID);
                // initialisation du ${jeu} utilisé dans la jsp
                request.setAttribute("jeu", jeu);
                // chargement de la jsp editeJeu
                request.getRequestDispatcher("editeJeu.jsp").forward(request, response);
                break;
            case "Ajouter":
                //déclaration des variables utilisées
                //on caste en UTF-8 pour eviter les problèmes d'encodage dans la BDD
                String S1 = request.getParameter("Nom");
                nom = new String(S1.getBytes(),Charset.forName("UTF-8"));
                String S2 = request.getParameter("Description");
                descript = new String(S2.getBytes(),Charset.forName("UTF-8"));
                nbJoueurMin = Integer.parseInt(request.getParameter("nbJoueurMin"));
                nbJoueurMax = Integer.parseInt(request.getParameter("nbJoueurMax"));
                ajo = new AccessJeuObject(getDataSource());
                //Creation d'un objet ajo de type updateJeu avec les params précedement ajoutés
                ajo.addJeu(nom, nbJoueurMin, nbJoueurMax, descript, User.getUserId());
                allJeu = ajo.listJeu();
                // initialisation du ${allJeu} utilisé dans la jsp
                request.setAttribute("allJeu", allJeu);
                // initialisation du ${jeu} utilisé dans la jsp
                request.setAttribute("jeu", ajo.listJeuUtilisateur(User.getUserId()));
                // rechargement de la jsp listJeux
                request.getRequestDispatcher("listJeux.jsp").forward(request, response);
                
                break;
            case "Modifier":
                //déclaration des variables utilisées
                //on caste en UTF-8 pour eviter les problèmes d'encodage dans la BDD
                String S3 = request.getParameter("Nom");
                nom = new String(S3.getBytes(),Charset.forName("UTF-8"));
                String S4 = request.getParameter("Description");
                descript = new String(S4.getBytes(),Charset.forName("UTF-8"));
                nbJoueurMin = Integer.parseInt(request.getParameter("nbJoueurMin"));
                nbJoueurMax = Integer.parseInt(request.getParameter("nbJoueurMax"));
                ajo = new AccessJeuObject(getDataSource());
                gameID = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("id",User.getUserId());
                //Creation d'un objet ajo de type updateJeu avec les params précedement ajoutés
                ajo.updateJeu(nom, nbJoueurMin, nbJoueurMax,descript, User.getUserId(),gameID);
                allJeu = ajo.listJeu();
                // initialisation du ${allJeu} utilisé dans la jsp
                request.setAttribute("allJeu", allJeu);
                // initialisation du ${jeu} utilisé dans la jsp
                request.setAttribute("jeu", ajo.listJeuUtilisateur(User.getUserId()));
                // rechargement de la jsp listJeux
                request.getRequestDispatcher("listJeux.jsp").forward(request, response);
                break;
            case "liste des jeux":
                //déclaration des variables utilisées
                AccessJeuObject dao = new AccessJeuObject(getDataSource());
                int id = Integer.parseInt(request.getParameter("id"));
                ArrayList<JeuEntity> jeu3 = dao.listJeuUtilisateur(id);
                allJeu = dao.listJeu();
                // initialisation du ${jeu} utilisé dans la jsp
                request.setAttribute("jeu", jeu3);
                // initialisation du ${allJeu} utilisé dans la jsp
                request.setAttribute("allJeu", allJeu);
                // rechargement de la jsp listJeux
                request.getRequestDispatcher("listJeux.jsp").forward(request, response);
                break;
        }
          
    }

    //Fonction pour retrouver l'utilisateur actuellement connecté
    private Object findUserInSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return (session == null) ? null : session.getAttribute("user");
	}
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Jeu.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Jeu.class.getName()).log(Level.SEVERE, null, ex);
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
