<%-- 
    Document   : listPlayer
    Created on : Nov 22, 2016, 7:35:55 PM
    Author     : neiko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Visualisation Google</title>
	<!-- On charge JQuery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<!-- On charge l'API Google -->
	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
        <script type="text/javascript" 	src="javascript/jquery.jsontotable.min.js"></script>
	<script>
		// Après le chargement de la page, on fait l'appel AJAX
            $(document).ready(	
                function () {
                    doAjax();
                }
            );
                
                function doAjax() {
			$.ajax({
				url: "listJoueurs",
				dataType: "json",
				success: // La fonction qui traite les résultats
					function (result) {
						$("#joueurs").empty();
                                                // On convertit les enregistrements en table HTML
						$.jsontotable(result, {id: "#joueurs"});
					},
				error: showError
			});
		}
		
		// Fonction qui traite les erreurs de la requête
		function showError(xhr, status, message) {
			alert("Erreur: " + status + " : " + message);
		}

	</script>         
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Liste des joueurs</h1>
	<!-- Le graphique apparaît ici -->
	<div id="joueurs"></div>
    </body>
</html>
