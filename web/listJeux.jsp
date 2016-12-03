<%-- 
    Document   : listPlayer
    Created on : Nov 22, 2016, 7:35:55 PM
    Author     : neiko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form>
            <c:forEach var="jeu" varStatus="status" items="${jeu}">
                <c:if test="${status.first}">
                <%-- On met l'en-tête de la table --%>
                    <table border='1'>
                        <tr><th>ID</th><th>Nom</th><th>Nombre de joueurs min</th><th>Nombre de joueurs max</th>
                            <th>proprietaire du jeu</th>
                        </tr>
                </c:if>
                        <%-- On met une ligne dans la table --%>
                        <%-- Les noms de propriétés correspondent aux "propriétés" java exportées par CustomerEntity (ex: getName() ) --%>
                        <tr><td>${jeu.gameId}</td>
                            <td>${jeu.nom}</td>
                            <td>${jeu.nbJoueurMin}</td>
                            <td>${jeu.nbJoueurMax}</td>
                            <td>${jeu.proprietaireID}</td>  
                            
                        </tr>
                            <c:if test="${status.last}"> <!-- Si on est sur le dernier élément de la liste -->
                            <%-- On ferme la table --%>
                                </table>
                        </c:if>
            </c:forEach>
        </form>
    </body>
</html>
