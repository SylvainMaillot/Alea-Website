<%-- 
    Document   : contribJoueurGraph
    Created on : 6 déc. 2016, 18:04:28
    Author     : Hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<!-- On charge l'API Google -->
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript" src="https://www.google.com/jsapi"></script>
	<script type="text/javascript">
		google.charts.load('current', {'packages':['corechart']});
                google.load("visualization", "1", {packages: ["corechart"]});

		// Après le chargement de la page, on fait l'appel AJAX
		
		
		   function drawChart(dataArray) {
                            var data = google.visualization.arrayToDataTable(dataArray);

                            var options = {
                              title: 'Contrib par joueur',
                              curveType: 'function',
                              legend: { position: 'bottom' }
                            };

                            var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

                            chart.draw(data, options);
                          }
                 function drawChart2(dataArray) {
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
				url: "contribParJoueur",
				dataType: "json",
				success: // La fonction qui traite les résultats
					function (result) {
						// On reformate le résultat comme un tableau
						var chartData = [];
						// On met le descriptif des données
						chartData.push(["Identifiant", "Contribution"]);
						for(var user in result) {
							chartData.push([user, result[user]]);
						}
						// On dessine le graphique
						drawChart(chartData);
					}
			});
                        $.ajax({
				url: "jeuxParjoueur",
				dataType: "json",
				success: // La fonction qui traite les résultats
					function (result) {
						// On reformate le résultat comme un tableau
						var chartData2 = [];
						// On met le descriptif des données
						chartData2.push(["name", "totalJeu"]);
						for(var user in result) {
							chartData2.push([user, result[user]]);
						}
						// On dessine le graphique
						drawChart2(chartData2);
					}
			});
		}
                
      google.setOnLoadCallback(doAjax);

	
		// Fonction qui traite les erreurs de la requête
		function showError(xhr, status, message) {
			alert("Erreur: " + status + " : " + message);
		}

	</script>
        <div id="curve_chart" style="width: 900px; height: 500px;"></div>
        <div id="piechart" style="width: 900px; height: 500px;"></div>

