<%-- 
    Document   : inscription
    Created on : Dec 5, 2016, 9:29:57 AM
    Author     : neiko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <form action="LoginController" method="post">
            <table> <tr>
            <td><label for="pseudo"><strong>Identifiant :</strong></label></td>
            <td><input type="text" name="pseudo" id="pseudo"/></td>
            </tr> <tr>
            <td><label for="passe"><strong>Nom :</strong></label></td>
            <td><input type="text" name="nom" id="passe"/></td>
            </tr> <tr>
            <td><label for="passe"><strong>Prénom :</strong></label></td>
            <td><input type="text" name="prenom" id="passe"/></td>
            </tr> <tr>
            <td><label for="passe"><strong>Mot de passe :</strong></label></td>
            <td><input type="password" name="passe" id="passe"/></td>
            </tr> <tr>
            <td><label for="email"><strong>Adresse e-mail :</strong></label></td>
            <td><input type="text" name="email" id="email"/></td>
            </table>
        <input type="submit" name="action" value="M'inscrire"/>
        </form> <br/>
</html>
