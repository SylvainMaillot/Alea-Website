<%-- 
    Document   : updateInfos
    Created on : Nov 21, 2016, 9:47:27 PM
    Author     : neiko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP Page</title>
    </head>
    <body>
        <p>Selectionnez les champs à modifier</p>
        <form  method="POST">
            <label>Prenom : </label><input type="text" name="prenom" value=${user.prenom} /> <br>
            <label>Nom : </label><input type="text" name="nom" value=${user.nom} /> <br>
            <label>Mot de passe : </label><input type="password" name="passwd" value=${user.motDePasse} /> <br>
            <label>Email : </label><input type="text" name="mail" value=${user.email} /> <br>
            <label>Contribution : </label><input type="text" name="contrib" value=${user.contribution} /> <br>
            <label>Type d'utilisateur : </label><input type="text" name="type" value=${user.typeUtilisateur} /> <br>
            <input type='submit' name='action' value='update'>
        </form>
    </body>
</html>
