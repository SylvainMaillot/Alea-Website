<%-- 
    Document   : jeuxJoueursGraph
    Created on : 6 déc. 2016, 17:42:24
    Author     : Hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Test</title>
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<!-- On charge l'API Google -->
	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
	<script type="text/javascript">
		google.load("visualization", "1", {packages: ["corechart"]});

		// Après le chargement de la page, on fait l'appel AJAX
		google.setOnLoadCallback(doAjax);
		
		function drawChart(dataArray) {
			var data = google.visualization.arrayToDataTable(dataArray);
			var options = {
				title: 'Nombre de jeux par utilisateur',
				is3D: true
			};
			var chart = new google.visualization.PieChart(document.getElementById('piechart'));
			chart.draw(data, options);
		}

		// Afficher les ventes par client
		function doAjax() {
			$.ajax({
				url: "jeuxParjoueur",
				dataType: "json",
				success: // La fonction qui traite les résultats
					function (result) {
						// On reformate le résultat comme un tableau
						var chartData = [];
						// On met le descriptif des données
						chartData.push(["name", "totalJeu"]);
						for(var user in result) {
							chartData.push([user, result[user]]);
						}
						// On dessine le graphique
						drawChart(chartData);
					},
				error: showError
			});
		}
		
		// Fonction qui traite les erreurs de la requête
		function showError(xhr, status, message) {
			alert("Erreur: " + status + " : " + message);
		}

	</script>
    </head>
    <body>
        <div id="piechart" style="width: 900px; height: 500px;"></div>
    </body>
</html>
