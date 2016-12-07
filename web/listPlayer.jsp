<%-- 
    Document   : listPlayer
    Created on : Nov 22, 2016, 7:35:55 PM
    Author     : neiko
--%>

<%@page contentType="text/html"  pageEncoding="UTF-8"%>
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
                    CreateHtmlTable();
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
						$.jsontotable(result, {id: "#joueurs", header: true});
					},
				error: showError
			});
		}
                
                function CreateHtmlTable() {

            //Clear result div
            $("#joueurs").html("");

            //Crate table html tag
            var table = $("<table id=DynamicTable border=1></table>").appendTo("#joueurs");

            //Create table header row
            var rowHeader = $("<tr></tr>").appendTo(table);

            $("<td></td>").text("Name").appendTo(rowHeader);
            $("<td></td>").text("Prenom").appendTo(rowHeader);
            $("<td></td").text("ID").appendTo(rowHeader);
            $("<td></td>").text("Mail").appendTo(rowHeader);
            $("<td></td>").text("Contribution").appendTo(rowHeader);

            //Get JSON data by calling action method in controller
            $.get('/Alea-Website/listJoueurs', function (data) {
                $.each(data, function (i, value) {

                    //Create new row for each record
                    var row = $("<tr></tr>").appendTo(table);
                    $("<td></td>").text(value.Nom).appendTo(row);
                    $("<td></td>").text(value.Prenom).appendTo(row);
                    $("<td></td>").text(value.Identifiant).appendTo(row);
                    $("<td></td>").text(value.Email).appendTo(row);
                    $("<td></td>").text(value.Contribution).appendTo(row);
                });
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
