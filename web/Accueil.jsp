<%-- 
    Document   : Accueil
    Created on : Dec 4, 2016, 6:56:45 PM
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
        <h1>Bienvenue sur le site d'Alea</h1>
        <%@ include file="logginForm.jsp" %>
        <h2>Pas encore inscrit ?</h2>
        <form method="link" action="inscription.jsp">
            <input type="submit" value="s'inscrire">
        </form>
    </body>
</html>
