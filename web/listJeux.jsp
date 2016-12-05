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
        <form action="Jeu" method="POST">
            <c:forEach var="jeu" varStatus="status" items="${jeu}">
                <c:if test="${status.first}">
                <%-- On met l'en-tête de la table --%>
                    <table border='1'>
                        <tr>
                            <th>ID</th>
                            <th>Nom</th>
                            <th>Nombre de joueurs min</th>
                            <th>Nombre de joueurs max</th>
                            <th>proprietaire du jeu</th>
                            <th>Action</th>
                        </tr>
                </c:if>
                        <tr>
                            <td>
                                ${jeu.gameId}
                                <input type='hidden' name='idJeu' value=${jeu.gameId}>
                            </td>
                            <td>
                                ${jeu.nom}
                                <input type='hidden' name='Nom' value=${jeu.nom}>
                            </td>
                            <td>
                                ${jeu.nbJoueurMin}
                                <input type='hidden' name='nbJoueurMin' value=${jeu.nbJoueurMin}>
                            </td>
                            <td>
                                ${jeu.nbJoueurMax}
                                <input type='hidden' name='nbJoueurMax' value=${jeu.nbJoueurMax}>
                            </td>
                            <td>
                                ${jeu.proprietaireID}
                                <input type='hidden' name='proprietaireJeu' value=${jeu.proprietaireID}>
                            </td>
                            <td>
                                <input type='submit' name='action' value='Editer'>
                                <input type='submit' name='action' value='Supprimer'>
                            </td>
                            
                        </tr>
                            <c:if test="${status.last}">
                            </table>
                        </c:if>
            </c:forEach>
        </form>
        <form action="Jeu" method="POST">
            <table border='1'>
                <tr>
                    <th>Nom</th>
                    <th>Nombre de joueurs min</th>
                    <th>Nombre de joueurs max</th>
                    <th>Description</th>
                    <th>proprietaire du jeu</th>
                    <th>Action</th>
                </tr>
                <tr>
                    <td>
                        <input type='text' name='Nom'>
                    </td>
                    <td>
                        <input type='number' name='nbJoueurMin' min=1 max=100>
                    </td>
                    <td>
                        <input type='number' name='nbJoueurMax' min=0 max=100>
                    </td>
                    <td>
                        <input type='text' name='Description'>
                    </td>
                    <td>
                        <input type='number' name='proprietaireJeu' min=0>
                    </td>
                    <td>
                        <input type='submit' name='action' value='Ajouter'>
                    </td>      
                </tr>
            </table>
        </form>     
    </body>
</html>
