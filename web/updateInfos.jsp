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
        <h1>Page de Modification</h1>
        <form  method="POST">
            
            <table> <tr>
            <td><label for="prenom"><strong>Prenom :</strong></label></td>
            <td><input type="text" name="prenom" value=${user.prenom} /></td>
            </tr> <tr>
            <td><label for="nom"><strong>Nom :</strong></label></td>
            <td><input type="text" name="nom" value=${user.nom} /></td>
            </tr> <tr>
            <td><label for="passwd"><strong>Mot de passe :</strong></label></td>
            <td><input type="password" name="passwd" value=${user.motDePasse} /></td>
            </tr> <tr>
            <td><label for="mail"><strong>Adresse e-mail :</strong></label></td>
            <td><input type="email" name="mail" value=${user.email} /></td>
            </tr> <tr>
            <td><label for="contrib"><strong>Contribution :</strong></label></td>
            <td><input type="text" name="contrib" value=${user.contribution} /></td>
            <input type="hidden" name="type" value=${user.typeUtilisateur} />
            </table>
            
            <input type='submit' name='action' value='Mettre à jour'>
            <input type='submit' name='action' value='Annuler'>
        </form>
    </body>
</html>
