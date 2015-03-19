--Amélioration de la base de données


--Changement du nom de la colonne nb_heures de la table module par nb_heures_annuel

ALTER TABLE `lagarenne2015`.`module` 
CHANGE COLUMN `nb_heures` `nb_heures_annuel` INT(11) NULL DEFAULT NULL ;

--Création de la table qui met en relation la session et le module et qui indique le nombre d'heures disponibles dans ce cas là

CREATE TABLE `lagarenne2015`.`heures_session_matiere` (
  `id_session` INT NOT NULL,
  `id_module` INT NOT NULL,
  `nbre_heures_disponibles` INT NULL,
  PRIMARY KEY (`id_session`, `id_module`),
  INDEX `id_module_fk_idx` (`id_module` ASC),
  CONSTRAINT `id_session_fk`
    FOREIGN KEY (`id_session`)
    REFERENCES `lagarenne2015`.`session` (`id_session`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `id_module_fk`
    FOREIGN KEY (`id_module`)
    REFERENCES `lagarenne2015`.`module` (`id_module`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

--Remplissage de la table heures_session_matiere

INSERT INTO `lagarenne2015`.`heures_session_matiere`(`id_session`,`id_module`,`nbre_heures_disponibles`)VALUES(1,1,30),(1,2,50),(1,3,50), (2,1,30), (2,2,50), (2,3,50), (3,1,30), (3,2,50), (3,3,50);

--Creation de la table journee

CREATE TABLE `lagarenne2015`.`jour` (
  `id_jour` INT NOT NULL,
  `nom` VARCHAR(45) NULL,
  PRIMARY KEY (`id_jour`),
  UNIQUE INDEX `nom_UNIQUE` (`nom` ASC));


--Remplissage de la table

INSERT INTO `lagarenne2015`.`jour` (`id_jour`,`nom`) 
VALUES (1,'Lundi'),(2,'Mardi'),(3,'Mercredi'), (4,'Jeudi'),(5,'Vendredi');

--Creation de la table creneau

CREATE TABLE `lagarenne2015`.`creneau` (
  `id_creneau` INT NOT NULL,
  `nom` VARCHAR(45) NULL,
  PRIMARY KEY (`id_creneau`),
  UNIQUE INDEX `nom_UNIQUE` (`nom` ASC));

--Remplissage de la table créneau

INSERT INTO `lagarenne2015`.`creneau` (`id_creneau`, `nom`) VALUES (1, 'Matin')(2, 'Après-midi');


--Creation table de contrainte

CREATE TABLE `lagarenne2015`.`contrainte` (
  `id_contrainte` INT NOT NULL,
  `id_journee` INT NOT NULL,
  PRIMARY KEY (`id_contrainte`),
  UNIQUE INDEX `id_jour_UNIQUE` (`id_journee` ASC),
  CONSTRAINT `id_journee`
    FOREIGN KEY (`id_contrainte`)
    REFERENCES `lagarenne2015`.`journee` (`id_journee`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

--Remplissage de la table contrainte

INSERT INTO `lagarenne2015`.`contrainte` (`id_contrainte`, `id_journee`) VALUES (1, 1),(2, 2),(3, 3),(4, 4),(5, 5),(6, 6);

--Création de la table jour_creneau

CREATE TABLE `lagarenne2015`.`jour_creneau` (
  `id_jour` INT NOT NULL,
  `id_creneau` INT NOT NULL,
  PRIMARY KEY (`id_jour`, `id_creneau`),
  INDEX `id_creneau_idx` (`id_creneau` ASC),
  CONSTRAINT `id_jour`
    FOREIGN KEY (`id_jour`)
    REFERENCES `lagarenne2015`.`jour` (`id_jour`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `id_creneau`
    FOREIGN KEY (`id_creneau`)
    REFERENCES `lagarenne2015`.`creneau` (`id_creneau`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

ALTER TABLE `lagarenne2015`.`jour_creneau` 
ADD COLUMN `id_jour_creneau` INT NULL AFTER `id_creneau`,
ADD UNIQUE INDEX `id_jour_creneau_UNIQUE` (`id_jour_creneau` ASC);

--Remplissage de la table jour_creneau

INSERT INTO `lagarenne2015`.`jour_creneau` (`id_jour`, `id_creneau`) VALUES (1, 1),VALUES (1, 2),(2, 1),(2, 2),(3, 1),(3, 2);

--Création de la table contrainte_formateur

CREATE TABLE `lagarenne2015`.`contrainte_formateur` (
  `id_formateur` INT NOT NULL,
  `id_jour_creneau` INT NOT NULL,
  PRIMARY KEY (`id_formateur`, `id_jour_creneau`),
  INDEX `id_jour_creneau_idx` (`id_jour_creneau` ASC),
  CONSTRAINT `id_formateur`
    FOREIGN KEY (`id_formateur`)
    REFERENCES `lagarenne2015`.`formateur` (`id_personne`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `id_jour_creneau`
    FOREIGN KEY (`id_jour_creneau`)
    REFERENCES `lagarenne2015`.`jour_creneau` (`id_jour_creneau`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

--Remplissage de la table contrainte_formateur_formateur

INSERT INTO `lagarenne2015`.`contrainte_formateur` (`id_formateur`, `id_jour_creneau`) VALUES (4, 1),(5, 3),(6,5);

--MAJ de la table formateur

ALTER TABLE `lagarenne2015`.`formateur` 
ADD COLUMN `id_module` INT NULL AFTER `date_entree`,
ADD INDEX `fk_id_module_idx` (`id_module` ASC);
ALTER TABLE `lagarenne2015`.`formateur` 
ADD CONSTRAINT `fk_id_module`
  FOREIGN KEY (`id_module`)
  REFERENCES `lagarenne2015`.`module` (`id_module`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `lagarenne2015`.`formateur` 
ADD UNIQUE INDEX `id_module_UNIQUE` (`id_module` ASC);

UPDATE `lagarenne2015`.`formateur` SET `id_module`='1' WHERE `id_personne`='4';
UPDATE `lagarenne2015`.`formateur` SET `id_module`='2' WHERE `id_personne`='5';
UPDATE `lagarenne2015`.`formateur` SET `id_module`='3' WHERE `id_personne`='6';
