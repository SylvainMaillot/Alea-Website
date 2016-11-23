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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <p>Bonjour ${prenom} ${nom} vous avez donné ${contrib} € à Alea :) </p>
        <form action="${pageContext.request.contextPath}/UpdateInfos" method="POST">
            <input type='submit' name='id' value=${user.userId}>
        </form>
            <form method="POST"> 
                <input type='submit' name='action' value='logout'>
                <c:if test="${type == 3}"> 
                <input type='submit' name='action' value='liste des joueurs'> 
            </c:if>
            </form>
            
    </body>
</html>
