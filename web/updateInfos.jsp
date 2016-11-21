<%-- 
    Document   : updateInfos
    Created on : Nov 21, 2016, 9:47:27 PM
    Author     : neiko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <p>Selectionnez les champs à modifier</p>
        <form method="POST">
            <label>Prenom : </label><input type="text" name="prenom" value=${prenom} /> <br>
            <label>Nom : </label><input type="text" name="nom" value=${nom} /> <br>
            <label>Mot de passe : </label><input type="password" name="passwd" value=${passwd} /> <br>
            <label>Email : </label><input type="text" name="mail" value=${mail} />
            <input type='submit' name='action' value='update'>
        </form>
    </body>
</html>
