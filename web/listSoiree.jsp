<%-- 
    Document   : listSoiree
    Created on : Dec 7, 2016, 1:56:09 PM
    Author     : neiko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
        <title>Liste des soirees</title>
    </head>
    <body>
        <form method="POST">
            <input type='submit' name='action' value='Accueil'>
        </form>
        <c:forEach var="soiree" varStatus="status" items="${soirees}">
                <c:if test="${status.first}">
                <%-- On met l'en-tête de la table --%>
                    <table border='1'>
                        <tr>
                            <th>Nom</th>
                            <th>Date</th>
                            <th>Description</th>
                            <th>Nombre de joueurs</th>
                            <th>Action</th>
                        </tr>
                </c:if>
                <form action="Soiree" method="POST">
                        <tr>
                            <td>
                                ${soiree.nom}
                                <input type='hidden' name='Nom' value=${soiree.nom}>
                            </td>
                            <td>
                                ${soiree.jour}
                                <input type='hidden' name='nbJoueurMin' value=${soiree.jour}>
                            </td>
                            <td>
                                ${soiree.description}
                                <input type='hidden' name='nbJoueurMax' value=${soiree.description}>
                            </td>
                            <td>
                                ${soiree.nbJoueur}
                                <input type='hidden' name='description' value=${soiree.nbJoueur}>
                            </td>
                            <td>
                                <input type='hidden' name='soireeID' value=${soiree.soireeId}> 
                                <input type='submit' name='action' value='Editer'>
                                <input type='submit' name='action' value='Supprimer'>
                            </td>
                        </tr>
                    </form>
                <c:if test="${status.last}">
                            </table>
                        </c:if>
            </c:forEach>
        
        <form action="Soiree" method="POST">
            <table border='1'>
                <tr>
                    <th>Nom</th>
                    <th>Date (aaaa-MM-jj)</th>
                    <th>Description</th>
                    <th>Action</th>
                </tr>
                <tr>
                    <td>
                        <input type='text' name='nom'>
                    </td>
                    <td>
                        <input type='date' name='date'>
                    </td>
                    <td>
                        <input type='text' name='description'>
                    </td>
                    <td>
                        <input type='submit' name='action' value='Ajouter'>
                    </td>      
                </tr>
            </table>
        
    </body>
</html>
