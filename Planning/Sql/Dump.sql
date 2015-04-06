-- MySQL dump 10.13  Distrib 5.6.19, for osx10.7 (i386)
--
-- Host: localhost    Database: lagarenne2015
-- ------------------------------------------------------
-- Server version	5.6.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `candidature`
--

LOCK TABLES `candidature` WRITE;
/*!40000 ALTER TABLE `candidature` DISABLE KEYS */;
INSERT INTO `candidature` VALUES (1,3,'A','2015-08-10 14:00:00','Je suis extrement motivé pour accéder à cette formation.'),(2,1,'R','2015-08-10 14:00:00','Je suis très motivé et passioné et souhaite donc accéder à cette formation.'),(3,2,'V','2015-03-15 10:00:00','Je suis extrement motivé pour accéder à cette formation d\'informatique, un domaine qui me passionne.');
/*!40000 ALTER TABLE `candidature` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `contrainte_formateur`
--

LOCK TABLES `contrainte_formateur` WRITE;
/*!40000 ALTER TABLE `contrainte_formateur` DISABLE KEYS */;
INSERT INTO `contrainte_formateur` VALUES (4,1),(5,3),(6,5);
/*!40000 ALTER TABLE `contrainte_formateur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `creneau`
--

LOCK TABLES `creneau` WRITE;
/*!40000 ALTER TABLE `creneau` DISABLE KEYS */;
INSERT INTO `creneau` VALUES (2,'Après-midi'),(1,'Matin');
/*!40000 ALTER TABLE `creneau` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `etat_candidature`
--

LOCK TABLES `etat_candidature` WRITE;
/*!40000 ALTER TABLE `etat_candidature` DISABLE KEYS */;
INSERT INTO `etat_candidature` VALUES ('A','Admis'),('R','Refusée'),('V','Validée');
/*!40000 ALTER TABLE `etat_candidature` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `evaluation`
--

LOCK TABLES `evaluation` WRITE;
/*!40000 ALTER TABLE `evaluation` DISABLE KEYS */;
INSERT INTO `evaluation` VALUES (1,1,1,4),(2,2,2,5),(3,3,3,6);
/*!40000 ALTER TABLE `evaluation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `formateur`
--

LOCK TABLES `formateur` WRITE;
/*!40000 ALTER TABLE `formateur` DISABLE KEYS */;
INSERT INTO `formateur` VALUES (4,'2008-01-09',1),(5,'2009-09-15',2),(6,'2012-05-02',3);
/*!40000 ALTER TABLE `formateur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `formation`
--

LOCK TABLES `formation` WRITE;
/*!40000 ALTER TABLE `formation` DISABLE KEYS */;
INSERT INTO `formation` VALUES (1,'BTS SIO','Formation pour obtenie le BTS Services Informatiques aux Organisations options SISR ou SLAM'),(2,'BTS CG','Formation pour obtenir le BTS Comptabilité et Gestion'),(3,'BTS Audiovisuel','Formation pour obtenir le BTS Audiovisuel option Son, Image ou Montage');
/*!40000 ALTER TABLE `formation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `heures_session_module`
--

LOCK TABLES `heures_session_module` WRITE;
/*!40000 ALTER TABLE `heures_session_module` DISABLE KEYS */;
INSERT INTO `heures_session_module` VALUES (1,1,30),(1,2,35),(1,3,50),(2,1,30),(2,2,50),(2,3,50),(3,1,30),(3,2,50),(3,3,50);
/*!40000 ALTER TABLE `heures_session_module` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `jour`
--

LOCK TABLES `jour` WRITE;
/*!40000 ALTER TABLE `jour` DISABLE KEYS */;
INSERT INTO `jour` VALUES (4,'Jeudi'),(1,'Lundi'),(2,'Mardi'),(3,'Mercredi'),(5,'Vendredi');
/*!40000 ALTER TABLE `jour` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `jour_creneau`
--

LOCK TABLES `jour_creneau` WRITE;
/*!40000 ALTER TABLE `jour_creneau` DISABLE KEYS */;
INSERT INTO `jour_creneau` VALUES (1,1,1),(1,2,2),(2,1,3),(2,2,4),(3,1,5),(3,2,6);
/*!40000 ALTER TABLE `jour_creneau` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `module`
--

LOCK TABLES `module` WRITE;
/*!40000 ALTER TABLE `module` DISABLE KEYS */;
INSERT INTO `module` VALUES (1,'SI2','Enseigner aux élèves les bases sur le fonctionnement du réseau internet','Des TP et des cours',30,'Les prérequis sont le module SI1 et le binaire'),(2,'Maths','Enseigner aux élèves les notions de mathématiques nécessaires en informatique','Des cours théoriques et du python',50,'Les prérequis sont le BAC'),(3,'Anglais','Enseigner la compréhension orale et de texte','Des cours et beaucoup de pratique orale',50,'Les prérequis sont le BAC et un peu d\'attention');
/*!40000 ALTER TABLE `module` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `module_formation`
--

LOCK TABLES `module_formation` WRITE;
/*!40000 ALTER TABLE `module_formation` DISABLE KEYS */;
INSERT INTO `module_formation` VALUES (1,1),(2,2),(3,3);
/*!40000 ALTER TABLE `module_formation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `module_theme`
--

LOCK TABLES `module_theme` WRITE;
/*!40000 ALTER TABLE `module_theme` DISABLE KEYS */;
INSERT INTO `module_theme` VALUES (1,1),(2,2),(3,3);
/*!40000 ALTER TABLE `module_theme` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `note`
--

LOCK TABLES `note` WRITE;
/*!40000 ALTER TABLE `note` DISABLE KEYS */;
INSERT INTO `note` VALUES (1,1,12.0),(2,2,14.0),(3,3,9.0);
/*!40000 ALTER TABLE `note` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `personne`
--

LOCK TABLES `personne` WRITE;
/*!40000 ALTER TABLE `personne` DISABLE KEYS */;
INSERT INTO `personne` VALUES (1,'M.','Jérome','LE BARON','33 Chemin du fossé de laumone','92600','Asnières','0951466417',NULL,'lebaronjerome@free.fr','dopler','2015-01-19 11:29:18',0),(2,'Mle','Emilie','WAILLE','25 Avenue de la gare','92000','NANTERRE','0956789101',NULL,'waille@hotmail.fr','walle','2014-12-17 11:29:18',0),(3,'M.','Saad','Hassaini','123 Rue de la mairie','95230','POISSY','0532198734',NULL,'saadh@gmail.com','loljeu','2015-01-20 11:29:18',0),(4,'Mme','Brigitte','GROLEAS','12 Rue du temple','95000','ARGENTEUIL','0125897456',NULL,'brigitte@groleas.fr','java8','2015-01-21 11:29:18',0),(5,'M.','Michel','PLASSE','5 Rue des martyrs','78560','MELUN','0244896531',NULL,'m.plasse@voila.fr','tintin','2015-01-22 11:29:18',0),(6,'Mme','Sylvie','JOUANNE','52 Avenue de la république','75012','PARIS','0137548652',NULL,'jsylvie.@orange.fr','proust','2015-01-23 11:29:18',0),(7,'M.','Jer','JER','','','','','','jerome@digiplace.fr','12','2015-02-01 16:23:59',1);
/*!40000 ALTER TABLE `personne` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER personne_before_insert_trigger BEFORE INSERT ON personne
FOR EACH ROW
BEGIN
    SET NEW.date_inscription = NOW();
    SET NEW.prenom = trim(initcap(NEW.prenom));
    SET NEW.nom = trim(UPPER(NEW.nom));
    SET NEW.adresse = trim(NEW.adresse);
    SET NEW.ville = trim(initcap(NEW.ville));
    SET NEW.telephone = trim(NEW.telephone);
    SET NEW.telephone2 = trim(NEW.telephone2);
    If NEW.prenom REGEXP '^ *$' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT='Prenom vide', MYSQL_ERRNO=3000;
    END If;
    If NEW.nom REGEXP '^ *$' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT='Nom vide', MYSQL_ERRNO=3001;
    END If;
    If NEW.email REGEXP '^ *$' THEN
        SIGNAL SQLSTATE '45002'
        SET MESSAGE_TEXT='Email vide', MYSQL_ERRNO=3002;
    END If;
    If NEW.mot_passe REGEXP '^ *$' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT='Mot de passe vide', MYSQL_ERRNO=3003;
    END If;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Dumping data for table `salle`
--

LOCK TABLES `salle` WRITE;
/*!40000 ALTER TABLE `salle` DISABLE KEYS */;
INSERT INTO `salle` VALUES (1,'Salle 306'),(2,'Salle 201'),(3,'Salle 114');
/*!40000 ALTER TABLE `salle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `seance`
--

LOCK TABLES `seance` WRITE;
/*!40000 ALTER TABLE `seance` DISABLE KEYS */;
INSERT INTO `seance` VALUES (1,1,4,'2015-06-02 09:00:00','2015-06-02 13:00:00',1,'Première séance de SI2 avec découverte du fonctionnement d\'internet'),(2,2,5,'2015-07-02 13:00:00','2015-07-02 16:00:00',1,'Suite du cours sur les matrices'),(3,3,6,'2015-09-12 13:00:00','2015-09-12 17:00:00',1,'Révision des propositions relatives');
/*!40000 ALTER TABLE `seance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `session`
--

LOCK TABLES `session` WRITE;
/*!40000 ALTER TABLE `session` DISABLE KEYS */;
INSERT INTO `session` VALUES (1,'BTS SIO 2016','2015-05-07','2016-05-12','4ème session pour le BTS SIO',1,'2015-03-07 09:00:00','2015-04-07 18:00:00'),(2,'BTS CG 2016','2015-09-25','2016-09-15','2ème session pour le BTS CG',2,'2015-07-25 09:00:00','2015-08-25 18:00:00'),(3,'BTS Audio 2016','2015-11-15','2016-11-05','1ère session pour le BTS Audiovisuel option son',2,'2015-09-15 09:00:00','2015-10-25 18:00:00');
/*!40000 ALTER TABLE `session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `theme`
--

LOCK TABLES `theme` WRITE;
/*!40000 ALTER TABLE `theme` DISABLE KEYS */;
INSERT INTO `theme` VALUES (2,'culture'),(1,'informatique'),(3,'langue');
/*!40000 ALTER TABLE `theme` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'lagarenne2015'
--

--
-- Dumping routines for database 'lagarenne2015'
--
/*!50003 DROP FUNCTION IF EXISTS `initcap` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `initcap`(chaine text) RETURNS text CHARSET utf8
    DETERMINISTIC
BEGIN
    DECLARE gauche, droite text; 
    SET gauche='';
    SET droite ='';
        WHILE chaine REGEXP ' ' DO
            SELECT SUBSTRING_INDEX(chaine, ' ', 1) INTO gauche;
            SELECT SUBSTRING(chaine, LOCATE(' ', chaine) + 1) INTO chaine; 
            SELECT CONCAT(droite, ' ', CONCAT(UPPER(SUBSTRING(gauche, 1, 1)), LOWER(SUBSTRING(gauche, 2)))) INTO droite; 
        END WHILE;
    RETURN LTRIM(CONCAT(droite, ' ', CONCAT(UPPER(SUBSTRING(chaine,1,1)), LOWER(SUBSTRING(chaine, 2))))); 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `inserer_personne` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `inserer_personne`(
    OUT p_id INT,
    IN  p_civilite VARCHAR(3),
    IN  p_prenom varchar(20),
    IN  p_nom varchar(30),
    IN  p_adresse varchar(45),
    IN  p_code_postal varchar(5),
    IN  p_ville varchar(30),
    IN  p_telephone varchar(15),
    IN  p_telephone2 varchar(15),
    IN  p_email varchar(30),
    IN  p_mot_passe varchar(45),
    OUT p_date_inscription TIMESTAMP)
BEGIN
    START TRANSACTION;
    SELECT NOW() INTO p_date_inscription;
    INSERT INTO personne (civilite, prenom, nom, adresse, code_postal, ville,
        telephone, telephone2, email, mot_passe, date_inscription)
    VALUES(p_civilite, p_prenom, p_nom, p_adresse, p_code_postal, p_ville,
        p_telephone, p_telephone2, p_email, p_mot_passe, p_date_inscription);
    SELECT MAX(id_personne) FROM personne INTO p_id;
    COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `refresh_base` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `refresh_base`()
BEGIN
  -- Lever temporairement les contraintes d'intégrité
  SET FOREIGN_KEY_CHECKS=0;
  TRUNCATE seance;
  TRUNCATE note;
  TRUNCATE evaluation;
  TRUNCATE candidature;
  TRUNCATE etat_candidature;
  TRUNCATE salle;
  TRUNCATE session;
  TRUNCATE module_formation;
  TRUNCATE formation;
  TRUNCATE module_theme;
  TRUNCATE theme;
  TRUNCATE module;
  TRUNCATE formateur;
  TRUNCATE personne;
  SET FOREIGN_KEY_CHECKS=1;

  ALTER TABLE evaluation AUTO_INCREMENT=1;
  ALTER TABLE salle AUTO_INCREMENT=1;
  ALTER TABLE session AUTO_INCREMENT=1;
  ALTER TABLE formation AUTO_INCREMENT=1;
  ALTER TABLE theme AUTO_INCREMENT=1;
  ALTER TABLE module AUTO_INCREMENT=1;
  ALTER TABLE personne AUTO_INCREMENT=1;

  START TRANSACTION;
  INSERT INTO personne
  (id_personne,civilite,prenom, nom,adresse,code_postal,ville,telephone,telephone2,email,mot_passe,date_inscription,est_inscrite) VALUES
  (1, 'M.', 'Jérome', 'LE BARON', '33 Chemin du fossé de laumone', '92600', 'Asnières', '0951466417', null, 'lebaronjerome@free.fr', 'dopler', '2015-01-19 11:29:18', 0),
  (2, 'Mle', 'Emilie', 'WAILLE', '25 Avenue de la gare', '92000', 'NANTERRE', '0956789101', null, 'waille@hotmail.fr', 'walle', '2014-12-17 11:29:18', 0),
  (3, 'M.', 'Saad', 'Hassaini', '123 Rue de la mairie', '95230', 'POISSY', '0532198734', null, 'saadh@gmail.com', 'loljeu', '2015-01-20 11:29:18', 0),
  (4, 'Mme', 'Brigitte', 'GROLEAS', '12 Rue du temple', '95000', 'ARGENTEUIL', '0125897456', null, 'brigitte@groleas.fr', 'java8', '2015-01-21 11:29:18', 0),
  (5, 'M.', 'Michel', 'PLASSE', '5 Rue des martyrs', '78560', 'MELUN', '0244896531', null, 'm.plasse@voila.fr', 'tintin', '2015-01-22 11:29:18', 0),
  (6, 'Mme', 'Sylvie', 'JOUANNE', '52 Avenue de la république', '75012', 'PARIS', '0137548652', null, 'jsylvie.@orange.fr', 'proust', '2015-01-23 11:29:18', 0);

  INSERT INTO formateur
  (id_formateur, date_entree) VALUES
  (4, '2008-01-09'),
  (5, '2009-09-15'),
  (6, '2012-05-02');

  INSERT INTO module
  (id_module, nom_module, objectif, contenu, nb_heures, prerequis) VALUES
  (1, 'SI2', 'Enseigner aux élèves les bases sur le fonctionnement du réseau internet', 'Des TP et des cours', 30, 'Les prérequis sont le module SI1 et le binaire'),
  (2, 'Maths', 'Enseigner aux élèves les notions de mathématiques nécessaires en informatique', 'Des cours théoriques et du python', 50, 'Les prérequis sont le BAC'),
  (3, 'Anglais', 'Enseigner la compréhension orale et de texte', 'Des cours et beaucoup de pratique orale', 50, 'Les prérequis sont le BAC et un peu d''attention');

  INSERT INTO theme
  (id_theme, libelle) VALUES
  (1, 'informatique'),
  (2, 'culture'),
  (3, 'langue');

  INSERT INTO module_theme
  (id_module, id_theme) VALUES
  (1, 1),
  (2, 2),
  (3, 3);

  INSERT INTO formation
  (id_formation, nom_formation, description_formation) VALUES
  (1, 'BTS SIO', 'Formation pour obtenie le BTS Services Informatiques aux Organisations options SISR ou SLAM'),
  (2, 'BTS CG', 'Formation pour obtenir le BTS Comptabilité et Gestion'),
  (3, 'BTS Audiovisuel', 'Formation pour obtenir le BTS Audiovisuel option Son, Image ou Montage');

  INSERT INTO module_formation
  (id_module, id_formation) VALUES
  (1, 1),
  (2, 2),
  (3, 3);

  INSERT INTO session
  (id_session, nom_session, date_debut, date_fin, description_session, id_formation, date_debut_inscription, date_fin_inscription) VALUES
  (1, 'BTS SIO 2016', '2015-05-07', '2016-05-12', '4ème session pour le BTS SIO', 1, '2015-03-07 09:00:00', '2015-04-07 18:00:00'),
  (2, 'BTS CG 2016', '2015-09-25', '2016-09-15', '2ème session pour le BTS CG', 2, '2015-07-25 09:00:00', '2015-08-25 18:00:00'),
  (3, 'BTS Audio 2016', '2015-11-15', '2016-11-05', '1ère session pour le BTS Audiovisuel option son', 2, '2015-09-15 09:00:00', '2015-10-25 18:00:00');

  INSERT INTO salle
  (id_salle, nom_salle) VALUES
  (1, 'Salle 306'),
  (2, 'Salle 201'),
  (3, 'Salle 114');

  INSERT INTO etat_candidature
  (id_etat_candidature, libelle) VALUES
  ('A', 'Admis'),
  ('V', 'Validée'),
  ('R', 'Refusée');

  INSERT INTO candidature
  (id_session, id_personne, id_etat_candidature, date_effet, motivation) VALUES
  (3, 2, 'V', '2015-03-15 10:00:00', 'Je suis extrement motivé pour accéder à cette formation d''informatique, un domaine qui me passionne.'),
  (1, 3, 'A', '2015-08-10 14:00:00', 'Je suis extrement motivé pour accéder à cette formation.'),
  (2, 1, 'R', '2015-08-10 14:00:00', 'Je suis très motivé et passioné et souhaite donc accéder à cette formation.');

  INSERT INTO evaluation
  (id_evaluation, id_module, id_session, id_formateur) VALUES
  (1, 1, 1, 4),
  (2, 2, 2, 5),
  (3, 3, 3, 6);

  INSERT INTO note
  (id_evaluation, id_personne, note) VALUES
  (1, 1, 12),
  (2, 2, 14),
  (3, 3, 9);

  INSERT INTO seance
  (id_module, id_session, id_personne, debut, fin, id_salle, contenu) VALUES
  (1, 1, 4, '2015-06-02 09:00:00', '2015-06-02 13:00:00', 1, 'Première séance de SI2 avec découverte du fonctionnement d''internet'),
  (2, 2, 5, '2015-07-02 13:00:00', '2015-07-02 16:00:00', 1, 'Suite du cours sur les matrices'),
  (3, 3, 6, '2015-09-12 13:00:00', '2015-09-12 17:00:00', 1, 'Révision des propositions relatives');

  COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-03-19 15:48:55
