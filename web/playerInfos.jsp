<%-- 
    Document   : logginWorks
    Created on : Oct 28, 2016, 6:05:27 PM
    Author     : neiko
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    
    <body>
        
        <p> Bonjour ${prenom} ${nom} vous avez donné ${contrib} € à Alea :) </p>
        <%@ include file="contribJoueurGraph.jsp"%>
        
        
        
        <form method="POST">
            <input type='submit' name='action' value="updateInfo">
            <input type='submit' name='action' value='liste des jeux'>
            <input type='submit' name='action' value='logout'>
        </form>
        <form method="link" action="listPlayer.jsp">
        <input type="submit" value="liste des joueurs">
        </form>
        
        
    </body>
</html>
