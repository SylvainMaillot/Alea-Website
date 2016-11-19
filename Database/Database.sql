/**
 * Author:  neiko
 * Created: Oct 26, 2016
 */

DROP DATABASE IF EXISTS Alea;
CREATE DATABASE Alea;
use Alea;

DROP TABLE IF EXISTS Utilisateur;
DROP TABLE IF EXISTS Jeu;
DROP TABLE IF EXISTS Soiree;
DROP TABLE IF EXISTS Programme;

/*Creation des tables*/

/*type : 0 -> non enregistre | 1 -> enregistre | 2 -> adherant | 3 -> admin*/
CREATE TABLE Utilisateur( ID INT AUTO_INCREMENT,
				Identifiant VARCHAR(20),
				MotDePasse VARCHAR(40),
				Email VARCHAR(80),
				Prenom VARCHAR(20) NOT NULL, 
				Nom VARCHAR(30) NOT NULL,
				Contribution DECIMAL,
				TypeUtilisateur INT,
				PRIMARY KEY (ID));

CREATE TABLE Jeu( ID INT AUTO_INCREMENT,
			Nom VARCHAR(80),
			NbJoueurMin INT,
			NbJoueurMax INT,
			Description VARCHAR(150),
			Proprietaire INT,
			PRIMARY KEY (ID),
			FOREIGN KEY (Proprietaire) REFERENCES Utilisateur(ID) ON DELETE CASCADE);

CREATE TABLE Soiree( ID INT AUTO_INCREMENT,
			Jour DATE,
			Nom VARCHAR(50),
			NbJoueur INT,
			NbAdherant INT,
			Description VARCHAR(400),
			PRIMARY KEY (ID));

CREATE TABLE Programme(	SoireeID INT,
			JeuID INT,
			FOREIGN KEY (SoireeID) REFERENCES Soiree(ID) ON DELETE CASCADE,
			FOREIGN KEY (JeuID) REFERENCES Jeu(ID) ON DELETE CASCADE
			);

/*Contraintes d'intégrités
La contribution d'un joueur ne peut pas etre négative*/
ALTER TABLE Utilisateur ADD CONSTRAINT ContributionPositive CHECK (Contribution >= 0.0);

/*Insertion dans les tables
Table Utilisateur*/
INSERT INTO Utilisateur VALUES(1,'Alea','Jacta_est','AleaJacta_est@gmail.com','Alea','Asso',0.0,3);
INSERT INTO Utilisateur VALUES(2,'Mamamillaaa','xxx','Mamamilla@gmail.com','Maxime','Moreno',25.0,3);
INSERT INTO Utilisateur VALUES(3,'Neiko','Nyaa','sylvain.maillot1@gmail.com','Sylvain','Maillot',3.5,2);
INSERT INTO Utilisateur VALUES(4,'NotoPD','AZERTY','hahacaca@gmail.com','Romain','Noto',0.0,1);

/*Table Jeu*/
INSERT INTO Jeu VALUES('1','Les contrées de l horreur','1','6','Blabla','3');

/*Table Soiree*/
INSERT INTO Soiree VALUES('1','2016-10-31','Alea horror party','0','0','');