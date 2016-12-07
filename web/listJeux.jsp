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
        <form method="POST">
            <input type='submit' name='action' value='Accueil'>
        </form>
            <c:forEach var="jeu" varStatus="status" items="${jeu}">
                <c:if test="${status.first}">
                <%-- On met l'en-tête de la table --%>
                    <table border='1'>
                        <tr>
                            <th>Nom</th>
                            <th>Nombre de joueurs min</th>
                            <th>Nombre de joueurs max</th>
                            <th>Description</th>
                            <th>Action</th>
                        </tr>
                </c:if>
                        <form action="Jeu" method="POST">
                        <tr>
                            <c:set var="id" scope="session" value="${jeu.proprietaireID}"/>
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
                                ${jeu.description}
                                <input type='hidden' name='description' value=${jeu.description}>
                            </td>
                            <td>
                                <input type='submit' name='action' value='Editer'>
                                <input type='hidden' name='idJeu' value=${jeu.gameId}> 
                                <input type='submit' name='action' value='Supprimer'>
                            </td>
                            
                        </tr>
                                </form>

                            <c:if test="${status.last}">
                            </table>
                        </c:if>
            </c:forEach>
        <br>
        <form action="Jeu" method="POST">
            <table border='1'>
                <tr>
                    <th>Nom</th>
                    <th>Nombre de joueurs min</th>
                    <th>Nombre de joueurs max</th>
                    <th>Description</th>
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
                        <input type='submit' name='action' value='Ajouter'>
                    </td>      
                </tr>
            </table>
        </form> 
        <br>
        <c:forEach var="allJeu" varStatus="status" items="${allJeu}">
                <c:if test="${status.first}">
                <%-- On met l'en-tête de la table --%>
                    <table border='1'>
                        <tr>
                            <th>Nom</th>
                            <th>Nombre de joueurs min</th>
                            <th>Nombre de joueurs max</th>
                            <th>Description</th>
                            <th>proprietaire du jeu</th>
                        </tr>
                </c:if>
                <c:if test="${allJeu.proprietaireID != id}">
                        <tr>
                            <td>
                                ${allJeu.nom}
                                <input type='hidden' name='Nom' value=${allJeu.nom}>
                            </td>
                            <td>
                                ${allJeu.nbJoueurMin}
                                <input type='hidden' name='nbJoueurMin' value=${allJeu.nbJoueurMin}>
                            </td>
                            <td>
                                ${allJeu.nbJoueurMax}
                                <input type='hidden' name='nbJoueurMax' value=${allJeu.nbJoueurMax}>
                            </td>
                            <td>
                                ${allJeu.description}
                                <input type='hidden' name='description' value=${allJeu.description}>
                            </td>
                            <td>
                                ${allJeu.proprietaireID}
                                <input type='hidden' name='proprietaireJeu' value=${allJeu.proprietaireID}>
                            </td>      
                        </tr>
                </c:if>
                            <c:if test="${status.last}">
                            </table>
                        </c:if>
            </c:forEach>
    </body>
</html>
