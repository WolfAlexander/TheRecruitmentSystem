-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: gortz.org    Database: recruitmentsystem
-- ------------------------------------------------------
-- Server version	5.7.17

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
-- Table structure for table `application`
--

DROP TABLE IF EXISTS `application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `person_id` int(11) NOT NULL,
  `date_of_registration` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status_id` int(11) NOT NULL DEFAULT '0',
  `availability_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `status_idx` (`status_id`),
  KEY `availability_idx` (`availability_id`),
  CONSTRAINT `existing_availability` FOREIGN KEY (`availability_id`) REFERENCES `availability` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=200 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application`
--

LOCK TABLES `application` WRITE;
/*!40000 ALTER TABLE `application` DISABLE KEYS */;
INSERT INTO `application` VALUES (167,1,'2017-02-17 21:54:29',0,1),(168,1,'2017-02-17 21:54:32',0,1),(169,1,'2017-02-17 21:54:32',0,1),(170,1,'2017-02-17 21:54:33',1,1),(171,1,'2017-02-17 21:54:49',0,1),(172,1,'2017-02-18 08:55:33',0,1),(173,1,'2017-02-18 09:28:33',0,1),(174,0,'2017-02-20 10:00:13',0,1),(175,0,'2017-02-20 10:10:08',0,1),(176,1,'2017-02-20 17:50:49',0,1),(177,1,'2017-02-21 22:45:52',0,1),(178,1,'2017-02-22 09:51:33',0,1),(179,1,'2017-02-22 09:54:49',0,1),(180,1,'2017-02-22 13:34:33',0,1),(181,1,'2017-02-22 13:34:49',0,1),(182,1,'2017-02-22 13:36:22',0,1),(183,0,'2017-02-22 13:36:48',0,1),(184,0,'2017-02-22 13:37:03',0,1),(185,0,'2017-02-22 13:37:08',0,1),(186,-1,'2017-02-22 13:45:22',0,1),(187,-1,'2017-02-22 13:48:06',0,1),(188,-1,'2017-02-22 13:50:12',0,1),(189,-1,'2017-02-22 13:51:46',0,1),(190,-1,'2017-02-22 13:59:18',0,1),(191,-1,'2017-02-22 14:00:18',0,1),(192,0,'2017-02-22 14:11:01',0,1),(193,-1,'2017-02-22 14:19:18',0,1),(194,0,'2017-02-22 14:19:24',0,1),(195,0,'2017-02-22 14:22:30',0,1),(196,0,'2017-02-22 14:22:46',0,1),(197,0,'2017-02-22 14:38:50',0,1),(198,0,'2017-02-22 14:39:22',0,1),(199,-1,'2017-02-22 14:39:44',0,1);
/*!40000 ALTER TABLE `application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `availability`
--

DROP TABLE IF EXISTS `availability`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `availability` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `from_date` date NOT NULL,
  `to_date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `availability`
--

LOCK TABLES `availability` WRITE;
/*!40000 ALTER TABLE `availability` DISABLE KEYS */;
INSERT INTO `availability` VALUES (1,'2015-03-14','2016-05-12');
/*!40000 ALTER TABLE `availability` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `competence`
--

DROP TABLE IF EXISTS `competence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `competence` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `competence`
--

LOCK TABLES `competence` WRITE;
/*!40000 ALTER TABLE `competence` DISABLE KEYS */;
INSERT INTO `competence` VALUES (13,'sausage grilling'),(14,'carousel operation');
/*!40000 ALTER TABLE `competence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `competence_profile`
--

DROP TABLE IF EXISTS `competence_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `competence_profile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `application_id` int(11) NOT NULL,
  `competence_id` int(11) NOT NULL,
  `years_of_experience` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  KEY `competence_idx` (`competence_id`),
  KEY `competence_id` (`competence_id`),
  CONSTRAINT `competence` FOREIGN KEY (`competence_id`) REFERENCES `competence` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `existing_application` FOREIGN KEY (`application_id`) REFERENCES `application` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `competence_profile`
--

LOCK TABLES `competence_profile` WRITE;
/*!40000 ALTER TABLE `competence_profile` DISABLE KEYS */;
INSERT INTO `competence_profile` VALUES (65,167,13,9),(66,167,14,10),(67,168,13,9),(68,168,14,10),(69,169,13,9),(70,169,14,10),(71,170,13,9),(72,170,14,10),(73,171,13,9),(74,171,14,10),(75,175,13,9),(76,176,13,9),(77,176,14,10),(78,177,13,9),(79,177,14,10),(80,178,13,9),(81,178,14,10),(82,179,13,9),(83,179,14,10),(84,180,13,9),(85,180,14,10),(86,182,13,9),(87,183,13,9),(88,186,13,9),(89,186,14,10),(90,187,13,9),(91,187,14,10),(92,188,13,9),(93,188,14,10),(94,189,13,9),(95,189,14,10),(96,190,13,9),(97,190,14,10),(98,191,13,9),(99,191,14,10),(100,192,13,9),(101,192,14,10),(102,193,13,9),(103,193,14,10),(104,194,13,9),(105,194,14,10),(106,195,13,9),(107,195,14,10),(108,196,13,9),(109,196,14,10),(110,197,13,9),(111,197,14,10),(112,198,13,9),(113,198,14,10),(114,199,13,9),(115,199,14,10);
/*!40000 ALTER TABLE `competence_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `credential`
--

DROP TABLE IF EXISTS `credential`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `credential` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `person_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `person_idx` (`person_id`),
  CONSTRAINT `person` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credential`
--

LOCK TABLES `credential` WRITE;
/*!40000 ALTER TABLE `credential` DISABLE KEYS */;
INSERT INTO `credential` VALUES (1,'adadada','assssssssss',NULL),(2,'adadada','assssssssss',NULL),(3,'adadada','assssssssss',NULL),(4,'adadada','assssssssss',27),(5,'adadada','assssssssss',28),(6,'allbiin','albin123',29),(7,'test','password123',30),(8,'test','password123',31),(9,'test','password123',32),(10,'test','password123',33),(11,'kdfso','kdfoskdfosdfkp',34),(12,'adddrian','adrian1995',35),(13,'testuser','password',36),(14,'testuser1','password',37);
/*!40000 ALTER TABLE `credential` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `languages`
--

DROP TABLE IF EXISTS `languages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `languages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `languages`
--

LOCK TABLES `languages` WRITE;
/*!40000 ALTER TABLE `languages` DISABLE KEYS */;
INSERT INTO `languages` VALUES (1,'sv'),(2,'en');
/*!40000 ALTER TABLE `languages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `localized_competence`
--

DROP TABLE IF EXISTS `localized_competence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `localized_competence` (
  `competence_id` int(11) NOT NULL,
  `language_id` int(11) NOT NULL,
  `translation` varchar(45) NOT NULL,
  PRIMARY KEY (`competence_id`,`language_id`),
  KEY `language_idx` (`language_id`),
  KEY `language_id` (`language_id`),
  KEY `language_ids` (`language_id`),
  CONSTRAINT `competence_foreign_key` FOREIGN KEY (`competence_id`) REFERENCES `competence` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `language_foreign_key` FOREIGN KEY (`language_id`) REFERENCES `languages` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `localized_competence`
--

LOCK TABLES `localized_competence` WRITE;
/*!40000 ALTER TABLE `localized_competence` DISABLE KEYS */;
INSERT INTO `localized_competence` VALUES (13,1,'korvgrillning'),(13,2,'sausage grilling'),(14,1,'karuselldrift'),(14,2,'carousel operation');
/*!40000 ALTER TABLE `localized_competence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `localized_role`
--

DROP TABLE IF EXISTS `localized_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `localized_role` (
  `role_id` int(11) NOT NULL,
  `language_id` int(11) NOT NULL,
  `translation` varchar(45) NOT NULL,
  PRIMARY KEY (`role_id`,`language_id`),
  KEY `role_id_idx` (`role_id`),
  KEY `language_id_idx` (`language_id`),
  KEY `role_id` (`role_id`),
  KEY `language_foreign_idx` (`language_id`),
  CONSTRAINT `language_to_translate_to` FOREIGN KEY (`language_id`) REFERENCES `languages` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `role_to_translate_from` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `localized_role`
--

LOCK TABLES `localized_role` WRITE;
/*!40000 ALTER TABLE `localized_role` DISABLE KEYS */;
INSERT INTO `localized_role` VALUES (1,1,'Rekryterare'),(1,2,'Recruiter'),(2,1,'Sökande'),(2,2,'Applicant'),(3,1,'Administratör'),(3,2,'Admin');
/*!40000 ALTER TABLE `localized_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `localized_status`
--

DROP TABLE IF EXISTS `localized_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `localized_status` (
  `status_id` int(11) NOT NULL,
  `language_id` int(11) NOT NULL,
  `translation` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`status_id`,`language_id`),
  KEY `language_foreign_key_idx` (`language_id`),
  KEY `language_foreign_key_id` (`language_id`),
  CONSTRAINT `language_id` FOREIGN KEY (`language_id`) REFERENCES `languages` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `status_id` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `localized_status`
--

LOCK TABLES `localized_status` WRITE;
/*!40000 ALTER TABLE `localized_status` DISABLE KEYS */;
INSERT INTO `localized_status` VALUES (0,1,'PÅGÅENDE'),(0,2,'PENDING'),(1,1,'AVSLAGEN'),(1,2,'REJECTED'),(2,1,'ACCEPTERAD'),(2,2,'ACCEPTED');
/*!40000 ALTER TABLE `localized_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45) DEFAULT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `role_id` int(11) DEFAULT '2',
  PRIMARY KEY (`id`),
  KEY `role_foreign_key_idx` (`role_id`),
  CONSTRAINT `role_foreign_key` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (1,'adde','boksak','1997-03-12','adde@hotmail.com',2),(2,'Adrian','Gortzak','1995-03-19','addegor95@gmail.com',1),(3,'Nils','Eriksson','1965-05-12','nills@gmail.com',1),(4,'Peter','Henriksson','1954-06-13','peter@hotmail.com',1),(5,'Sven','Svensson','1943-03-12','sven@hotmail.com',1),(27,'adde','boksak','1997-03-12','adde@hotmail.com',2),(28,'adde','boksak','1997-03-12','adde@hotmail.com',2),(29,'Albin','Friedner','1994-03-20','albin@example.com',2),(30,'Test','Testsson','3894-03-19','test@example.com',2),(31,'Test','Testsson','3894-03-19','test@example.com',2),(32,'Test','Testsson','3894-03-19','test@example.com',2),(33,'Test','Testsson','3894-03-19','test@example.com',2),(34,'test','test','1230-12-12','dsd@se.se',2),(35,'adrian','Gortzak','1995-02-12','addegor95@gmail.com',2),(36,'Test','Testson','1291-12-12','sdfs@test.se',2),(37,'Test','Testson','1291-12-12','test@test.se',1);
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_RECRUITER'),(2,'ROLE_APPLICANT'),(3,'Admin');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (0,'PENDING'),(1,'REJECTED'),(2,'ACCEPTED');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-13 10:20:32
