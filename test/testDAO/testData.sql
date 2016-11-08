/**
 * Author:  neiko
 * Created: Oct 26, 2016
 */

DROP TABLE Utilisateur IF EXISTS;
DROP TABLE Jeu IF EXISTS;
DROP TABLE Soiree IF EXISTS;
DROP TABLE Programme IF EXISTS;

/*Creation des tables*/

CREATE TABLE Utilisateur(ID INTEGER IDENTITY,
                            Identifiant VARCHAR(20),
                            MotDePasse VARCHAR(40),
                            Email VARCHAR(80),
                            Prenom VARCHAR(20) NOT NULL, 
                            Nom VARCHAR(30) NOT NULL,
                            Contribution DECIMAL,
                            TypeUtilisateur INT);

CREATE TABLE Jeu(ID INTEGER IDENTITY,
			Nom VARCHAR(80),
			NbJoueurMin INT,
			NbJoueurMax INT,
                        Description VARCHAR(150),
			Proprietaire INT,
			FOREIGN KEY (Proprietaire) REFERENCES Utilisateur(ID) ON DELETE CASCADE);

CREATE TABLE Soiree(ID INTEGER IDENTITY,
			Jour DATE,
			Nom VARCHAR(50),
			NbJoueur INT,
			NbAdherant INT,
			Description VARCHAR(400));

CREATE TABLE Programme( SoireeID INT,
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
INSERT INTO Jeu VALUES('1','Les contrées de lhorreur','1','6','Blabla','3');

/*Table Soiree*/
INSERT INTO Soiree VALUES('1','2016-10-31','Alea horror party','0','0','');

COMMIT;
