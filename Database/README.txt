POUR POUVOIR UTILISER LA BASE DE DONNEES : 

1 - Installer MySql ou MariaDB (Personellement j'utilise MariaDB)
2 - Lancer mysql à l'aide de la commande : mysql --host=localhost --user=root --password=root (Le mdp que tu as défini)
3 - Lancer la base de données à l'aide de la commande : source Database.sql (Il faut etre dans le dossier)

    Sous netBeans !
4 - Aller dans l'onglet Services -> DataBase -> (Clic droit) New Connection
5 - Driver : MySQL / Driver File : Normalement t'as pas besoin d'y toucher
6 -	DriverName : Pas touche
	Host: localhost   Port: 3306
	Database: Alea
	User Name: root
	Password: root
7 - Finish

Et voilà, normalement tu peux utiliser la base de donnée pour faire ce que tu veux.

Après m'etre renseigné un peu, j'ai pus voir que cryper des données dans la base de données n'est pas forcement nécessaire
A reverifier
