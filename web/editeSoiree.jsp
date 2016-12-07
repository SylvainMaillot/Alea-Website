<%-- 
    Document   : editeSoiree
    Created on : Dec 7, 2016, 2:12:24 PM
    Author     : neiko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edition d'une soiree</title>
    </head>
    <body>
         <form action="Soiree" method="POST">
            <table border='1'>
                <tr>
                    <th>Nom</th>
                    <th>Date</th>
                    <th>Description</th>
                    <th>Action</th>
                </tr>
                    <td>
                        <input type='text' name='nom' value="${soiree.nom}">
                    </td>
                    <td>
                        <input type='date' name='date' value=${soiree.jour}>
                    </td>
                    <td>
                        <input type='text' name='description' value="${soiree.description}">
                    </td>
                    <td>
                        <input type='hidden' name='soireeID' value=${soiree.soireeId}>
                        <input type='submit' name='action' value='Modifier'>
                    </td>      
                </tr>
            </table>
        </form>   
    </body>
</html>
