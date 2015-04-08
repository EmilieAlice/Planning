--  Amélioration de la base de données


CREATE TABLE heures_session_module (
    id_session INT NOT NULL,
    id_module INT NOT NULL,
    nbre_heures_disponibles INT NULL,
    PRIMARY KEY (id_session , id_module),
    INDEX id_module_fk_idx (id_module ASC),
    CONSTRAINT id_session_fk FOREIGN KEY (id_session)
        REFERENCES lagarenne2015.session (id_session)
        ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT id_module_fk FOREIGN KEY (id_module)
        REFERENCES lagarenne2015.module (id_module)
        ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- Remplissage de la table heures_session_matiere

INSERT INTO heures_session_module
(id_session,id_module,nbre_heures_disponibles)VALUES
(1,1,30),(1,2,50),(1,3,50), (2,1,30), (2,2,50), (2,3,50), (3,1,30), (3,2,50), (3,3,50);

-- Creation de la table jour

CREATE TABLE jour (
    id_jour INT NOT NULL,
    nom_jour VARCHAR(45) NULL,
    PRIMARY KEY (id_jour),
    UNIQUE INDEX nom_jour_UNIQUE (nom_jour ASC)
);


-- Remplissage de la table

INSERT INTO jour (id_jour,nom_jour) 
VALUES (1,'Lundi'),(2,'Mardi'),(3,'Mercredi'), (4,'Jeudi'),(5,'Vendredi');

-- Creation de la table creneau

CREATE TABLE creneau (
    id_creneau INT NOT NULL,
    nom_creneau VARCHAR(45) NULL,
    PRIMARY KEY (id_creneau),
    UNIQUE INDEX nom_creneau_UNIQUE (nom_creneau ASC)
);

-- Remplissage de la table créneau


INSERT INTO creneau (id_creneau, nom_creneau) VALUES (1,'MATIN'),(2,'APRES_MIDI');


-- Creation table de contrainte

CREATE TABLE contrainte_formateur (
    id_formateur INT NOT NULL,
    id_creneau INT NOT NULL,
    id_jour INT NOT NULL,
    PRIMARY KEY (id_formateur , id_creneau , id_jour),
    INDEX id_creneau_idx (id_creneau ASC),
    INDEX id_jour_idx (id_jour ASC),
    CONSTRAINT id_formateur FOREIGN KEY (id_formateur)
        REFERENCES formateur (id_formateur)
        ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT id_creneau FOREIGN KEY (id_creneau)
        REFERENCES creneau (id_creneau)
        ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT id_jour FOREIGN KEY (id_jour)
        REFERENCES jour (id_jour)
        ON DELETE NO ACTION ON UPDATE NO ACTION
)  ENGINE=INNODB DEFAULT CHARSET=UTF8;


-- Remplissage de la table contrainte

INSERT INTO contrainte_formateur VALUES (4,1,1),(5,2,2),(6,1,3);


-- MAJ de la table formateur

ALTER TABLE formateur 
ADD COLUMN id_module INT NULL AFTER date_entree,
ADD INDEX fk_id_module_idx (id_module ASC);
ALTER TABLE lagarenne2015.formateur 
ADD CONSTRAINT fk_id_module
  FOREIGN KEY (id_module)
  REFERENCES lagarenne2015.module (id_module)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE formateur 
ADD UNIQUE INDEX id_module_UNIQUE (id_module ASC);

UPDATE formateur 
SET 
    id_module = '1'
WHERE
    id_formateur = '4';
UPDATE formateur 
SET 
    id_module = '2'
WHERE
    id_formateur = '5';
UPDATE formateur 
SET 
    id_module = '3'
WHERE
    id_formateur = '6';




-- MAJ de la table seance

ALTER TABLE seance 
ADD COLUMN id_creneau INT NOT NULL AFTER fin,
DROP PRIMARY KEY,
ADD PRIMARY KEY (id_module, id_session, id_formateur, debut, fin, id_salle, id_creneau),
ADD INDEX fk_seance_creneau1_idx (id_creneau ASC);

  
UPDATE seance 
SET 
    id_creneau = '1'
WHERE
    id_module = '2' AND id_session = '2'
        AND id_formateur = '5'
        AND debut = '2015-07-02 13:00:00'
        AND fin = '2015-07-02 16:00:00'
        AND id_salle = '1'
        AND id_creneau = '0';
UPDATE seance 
SET 
    id_creneau = '1'
WHERE
    id_module = '3' AND id_session = '3'
        AND id_formateur = '6'
        AND debut = '2015-09-12 13:00:00'
        AND fin = '2015-09-12 17:00:00'
        AND id_salle = '1'
        AND id_creneau = '0';

-- Creation d'une table admin

CREATE TABLE `lagarenne2015`.`administrateur` (
  `id_admin` INT NOT NULL,
  `nom_admin` VARCHAR(45) NOT NULL,
  `mot_passe_admin` VARCHAR(45) NOT NULL,
  `email_admin` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_admin`),
  UNIQUE INDEX `nom_admin_UNIQUE` (`nom_admin` ASC),
  UNIQUE INDEX `email_admin_UNIQUE` (`email_admin` ASC));

