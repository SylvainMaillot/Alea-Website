<%-- 
    Document   : logginWorks
    Created on : Oct 28, 2016, 6:05:27 PM
    Author     : neiko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <META http-equiv="Content-Type" content="text/html" charset="UTF-8">
        <title>JSP Page</title>
    </head>
    
    <body>
        <p> Bonjour ${prenom} ${nom} alias numéro ${id} vous avez donné ${contrib} Euro à Alea! Merci mon ami </p>
        <%@ include file="contribJoueurGraph.jsp"%>
        
        
        
        <form action="LoginController" method="POST">
            <input type='submit' name='action' value="updateInfo">
            <input type='submit' name='action' value='logout'>
        </form>
        <form action="Jeu" method="POST">
            <input type='hidden' name='id' value=${id}>
            <input type='submit' name='action' value='liste des jeux'>
        </form>
        <form action="Soiree" method="POST">
            <input type='submit' name='action' value='Voir les soirees'>
        </form>
        <form method="link" action="listPlayer.jsp">
            <input type="submit" value="liste des joueurs">
        </form>
        
        
    </body>
</html>
