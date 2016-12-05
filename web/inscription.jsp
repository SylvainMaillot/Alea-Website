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
    <form action="inscription.java" method="post">
            <table> <tr>
            <td><label for="pseudo"><strong>Identifiant :</strong></label></td>
            <td><input type="text" name="pseudo" id="pseudo"/></td>
            </tr> <tr>
            <td><label for="passe"><strong>Mot de passe :</strong></label></td>
            <td><input type="password" name="passe" id="passe"/></td>
            </tr> <tr>
            <td><label for="passe2"><strong>Confirmez le mot de passe :</strong></label></td>
            <td><input type="password" name="passe2" id="passe2"/></td>
            </tr> <tr>
            <td><label for="email"><strong>Adresse e-mail :</strong></label></td>
            <td><input type="text" name="email" id="email"/></td>
            </table>
        <input type="submit" name="inscrition" value="M'inscrire"/>
        </form> <br/>
</html>
