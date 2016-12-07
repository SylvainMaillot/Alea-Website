<%-- 
    Document   : editeJeu
    Created on : 6 déc. 2016, 09:14:32
    Author     : Hp
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
                        <input type='text' name='Nom' value="${jeu.nom}">
                    </td>
                    <td>
                        <input type='number' name='nbJoueurMin' min=1 max=100 value=${jeu.nbJoueurMin}>
                    </td>
                    <td>
                        <input type='number' name='nbJoueurMax' min=0 max=100 value=${jeu.nbJoueurMax}>
                    </td>
                    <td>
                        <input type='text' name='Description' value="${jeu.description}">
                    </td>
                    <td>
                        <input type='hidden' name='id' value=${jeu.gameId}>
                        <input type='submit' name='action' value='Modifier'>
                    </td>      
                </tr>
            </table>
        </form>     
    </body>
</html>