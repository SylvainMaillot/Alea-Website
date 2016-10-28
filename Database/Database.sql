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
				Identifiant VARCHAR(20) NOT NULL,
				MotDePasse VARCHAR(40) NOT NULL,
				JoueurID INT,
				PRIMARY KEY (ID),
				FOREIGN KEY (JoueurID) REFERENCES Joueur(ID) ON DELETE CASCADE);

CREATE TABLE Jeu( ID INT AUTO_INCREMENT,
			Nom VARCHAR(80),
			NbJoueur INT,
			Proprietaire INT,
			PRIMARY KEY (ID),
			FOREIGN KEY (Proprietaire) REFERENCES Joueur(ID) ON DELETE CASCADE);

CREATE TABLE Soiree( ID INT AUTO_INCREMENT,
			Jour DATE,
			Nom VARCHAR(50),
			NbJoueur INT,
			NbAdherant INT,
			PRIMARY KEY (ID));

CREATE TABLE Facture( ID INT AUTO_INCREMENT,
			Moment DATE,
			Montant DECIMAL,
			Motif VARCHAR(100),
			Destinataire VARCHAR(50),
			Emetteur INT,
			PRIMARY KEY (ID),
			FOREIGN KEY (Emetteur) REFERENCES Joueur(ID));

CREATE TABLE Produit( ID INT AUTO_INCREMENT,
			Description VARCHAR(30),
			PRIMARY KEY (ID));

CREATE TABLE Vente( ID INT AUTO_INCREMENT,
			Produit INT NOT NULL,
			Prix DECIMAL,
			Quantite INT,
			PRIMARY KEY (ID),
			FOREIGN KEY (Produit) REFERENCES Produit(ID));

--Contraintes d'intégrités
--La contribution d'un joueur ne peut pas etre négative
ALTER TABLE Joueur ADD CONSTRAINT ContributionPositive CHECK (Contribution >= 0.0);

--Le montant d'une facture ne peut pas etre négatif
ALTER TABLE Facture ADD CONSTRAINT MontantPositif CHECK (Montant >= 0.0);

--Le prix d'un produit ne peut etre négatif
ALTER TABLE Vente ADD CONSTRAINT PrixPositif CHECK (Prix >= 0.0);

--La quantité d'un produit ne peut etre négative
ALTER TABLE Vente ADD CONSTRAINT QuantitePositive CHECK (Quantite >= 0);

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

--Table Jeu
INSERT INTO Jeu VALUES('1','Les contrées de l\'horreur','6','3');

--Table Soiree
INSERT INTO Soiree VALUES('1','2016-10-31','Alea horror party','0','0');

--Table Facture
INSERT INTO Facture VALUES('1','2016-10-14','100.98','Achats de boissons Leclerc','E-Leclerc','3');

--Table Produit
INSERT INTO Produit VALUES('1','Coca 33 cl');
INSERT INTO Produit VALUES('2','Oasis 33 cl');
INSERT INTO Produit VALUES('3','Sprite 33 cl');

--Table Vente
INSERT INTO Vente VALUES('1','1','1.0','7');
INSERT INTO Vente VALUES('2','2','1.0','4');
INSERT INTO Vente VALUES('3','3','1.0','2');
