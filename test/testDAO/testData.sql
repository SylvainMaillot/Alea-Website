/**
 * Author:  neiko
 * Created: Oct 26, 2016
 */

DROP TABLE Joueur IF EXISTS;
DROP TABLE Utilisateur IF EXISTS;
DROP TABLE Jeu IF EXISTS;
DROP TABLE Soiree IF EXISTS;
DROP TABLE Facture IF EXISTS;
DROP TABLE Produit IF EXISTS;
DROP TABLE Vente IF EXISTS;

--Creation des tables
CREATE TABLE Joueur(ID INTEGER IDENTITY, Prenom VARCHAR(20), Nom VARCHAR(30), Contribution DECIMAL);
CREATE TABLE Utilisateur(ID INTEGER IDENTITY, Identifiant VARCHAR(20), Password VARCHAR(30), JoueurID INTEGER, FOREIGN KEY (JoueurID) REFERENCES Joueur(ID) ON DELETE CASCADE);

--Contraintes d'intégrités
--La contribution d'un joueur ne peut pas etre négative
ALTER TABLE Joueur ADD CONSTRAINT ContributionPositive CHECK (Contribution >= 0.0);

--Insertion dans les tables
--Table Joueur
INSERT INTO Joueur VALUES(0,'Alea',null,0.0);
INSERT INTO Joueur VALUES(1,'Maxime','Moreno',25.0);
INSERT INTO Joueur VALUES(2,'Sylvain','Maillot',3.5);
INSERT INTO Joueur VALUES(3,'Romain','Noto',0.0);

--Table Utilisateur
INSERT INTO Utilisateur VALUES(0,'Alea','Jacta_est',0);
INSERT INTO Utilisateur VALUES(1,'Mamamillaaa','xxx',1);
INSERT INTO Utilisateur VALUES(2,'Neiko','Nyaa',2);

COMMIT;