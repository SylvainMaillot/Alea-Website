<%-- 
    Document   : logginForm
    Created on : Oct 28, 2016, 5:49:20 PM
    Author     : neiko
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
		Merci de vous identifier:<br>
		<div style="color:red">${errorMessage}</div>
                <form action="${pageContext.request.contextPath}/LoginController" method="POST">
			Pseudonyme : <input name='id'><br>
			Mot de Passe : <input name='pass' type='password'><br>
			<input type='submit' name='action' value='login'>
		</form>