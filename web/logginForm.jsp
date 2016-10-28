<%-- 
    Document   : logginForm
    Created on : Oct 28, 2016, 5:49:20 PM
    Author     : neiko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Loggin</title>
    </head>
    <body>
        <form>
            Identifiant : <input type="text" name="id" value="${id}" />
            Mot de passe : <input type="password" name="pass" value="${pass}" />
            <input type="submit"/>
        </form>
    </body>
</html>
