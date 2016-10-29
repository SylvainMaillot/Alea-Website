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
CREATE TABLE Joueur(ID INTEGER IDENTITY, 
			Prenom VARCHAR(20),
			Nom VARCHAR(30),
			Contribution DECIMAL);

CREATE TABLE Utilisateur(ID INTEGER IDENTITY,
                            Identifiant VARCHAR(20),
                            MotDePasse VARCHAR(40),
                            JoueurID INTEGER,
                            FOREIGN KEY (JoueurID) REFERENCES Joueur(ID) ON DELETE CASCADE);

CREATE TABLE Jeu(ID INTEGER IDENTITY,
			Nom VARCHAR(80),
			NbJoueur INT,
			Proprietaire INT,
			FOREIGN KEY (Proprietaire) REFERENCES Joueur(ID) ON DELETE CASCADE);

CREATE TABLE Soiree(ID INTEGER IDENTITY,
			Jour DATE,
			Nom VARCHAR(50),
			NbJoueur INT,
			NbAdherant INT);

CREATE TABLE Facture(ID INTEGER IDENTITY,
			Moment DATE,
			Montant DECIMAL,
			Motif VARCHAR(100),
			Destinataire VARCHAR(50),
			Emetteur INT,
			FOREIGN KEY (Emetteur) REFERENCES Joueur(ID));

CREATE TABLE Produit(ID INTEGER IDENTITY,
			Description VARCHAR(30));

CREATE TABLE Vente(ID INTEGER IDENTITY,
			Produit INT NOT NULL,
			Prix DECIMAL,
			Quantite INT,
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
INSERT INTO Jeu VALUES('1','Les contrées de l horreur','6','3');

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

COMMIT;