/**
 * Author:  neiko
 * Created: Oct 26, 2016
 */

DROP DATABASE IF EXISTS Alea;
CREATE DATABASE Alea;
use Alea;

DROP TABLE IF EXISTS Joueur;
DROP TABLE IF EXISTS Utilisateur;
DROP TABLE IF EXISTS Jeu;
DROP TABLE IF EXISTS Soiree;
DROP TABLE IF EXISTS Facture;
DROP TABLE IF EXISTS Produit;
DROP TABLE IF EXISTS Vente;

--Creation des tables
CREATE TABLE Joueur( ID INT AUTO_INCREMENT,
			Prenom VARCHAR(20), 
			Nom VARCHAR(30),
			Contribution DECIMAL,
			PRIMARY KEY(ID));
CREATE TABLE Utilisateur( ID INT AUTO_INCREMENT,
				Identifiant VARCHAR(20),
				MotDePasse VARCHAR(30),
				JoueurID INTEGER,
				PRIMARY KEY (ID),
				FOREIGN KEY (JoueurID) REFERENCES Joueur(ID) ON DELETE CASCADE);

--Contraintes d'intégrités
--La contribution d'un joueur ne peut pas etre négative
ALTER TABLE Joueur ADD CONSTRAINT ContributionPositive CHECK (Contribution >= 0.0);

--Insertion dans les tables
--Table Joueur
INSERT INTO Joueur VALUES(1,'Alea',null,0.0);
INSERT INTO Joueur VALUES(2,'Maxime','Moreno',25.0);
INSERT INTO Joueur VALUES(3,'Sylvain','Maillot',3.5);
INSERT INTO Joueur VALUES(4,'Romain','Noto',0.0);

--Table Utilisateur
INSERT INTO Utilisateur VALUES(1,'Alea','Jacta_est',1);
INSERT INTO Utilisateur VALUES(2,'Mamamillaaa','xxx',2);
INSERT INTO Utilisateur VALUES(3,'Neiko','Nyaa',3);
