<%-- 
    Document   : listPlayer
    Created on : Nov 22, 2016, 7:35:55 PM
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
        <c:forEach var="ue" varStatus="status" items="${ue}">
            <c:if test="${status.first}">
            <%-- On met l'en-tête de la table --%>
		<table border='1'>
                    <tr><th>ID</th><th>Identifiant</th><th>Email</th><th>Prenom</th>
                        <th>Nom</th><th>Contribution</th><th>Type Utilisateur</th>
                        <th>Modifider</th>
                    </tr>
            </c:if>
                    <%-- On met une ligne dans la table --%>
                    <%-- Les noms de propriétés correspondent aux "propriétés" java exportées par CustomerEntity (ex: getName() ) --%>
                    <tr><td>${ue.userId}</td><td>${ue.identifiant}</td><td>${ue.email}</td>
                        <td>${ue.prenom}</td><td>${ue.nom}</td><td>${ue.contribution}</td><td>${ue.typeUtilisateur}</td>
                        <td><input type='submit' name='udpdateUser' value="${ue}"> </td>
                    </tr>
                        <c:if test="${status.last}"> <!-- Si on est sur le dernier élément de la liste -->
			<%-- On ferme la table --%>
                            </table>
                    </c:if>
        </c:forEach>	
    </body>
</html>
