-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: gortz.org    Database: recruitmentsystem_jobapplication
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application`
--

LOCK TABLES `application` WRITE;
/*!40000 ALTER TABLE `application` DISABLE KEYS */;
INSERT INTO `application` VALUES (1,1,'2017-02-23 13:07:50',0,1),(2,2,'2017-12-23 22:46:39',1,1),(3,3,'2017-02-22 22:44:41',0,1),(4,4,'2017-02-24 22:46:42',2,1),(5,3,'2015-02-23 22:46:42',0,1),(6,2,'2017-02-23 22:46:43',0,1),(7,3,'2016-02-23 22:46:43',1,1),(8,2,'2017-02-23 22:46:44',1,1),(9,1,'2007-02-23 22:46:45',2,1),(10,1,'2007-02-23 22:46:45',1,1),(11,1,'2017-02-23 22:46:46',1,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `competence`
--

LOCK TABLES `competence` WRITE;
/*!40000 ALTER TABLE `competence` DISABLE KEYS */;
INSERT INTO `competence` VALUES (0,'korvgrillning'),(1,'karuselldrift');
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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `competence_profile`
--

LOCK TABLES `competence_profile` WRITE;
/*!40000 ALTER TABLE `competence_profile` DISABLE KEYS */;
INSERT INTO `competence_profile` VALUES (1,1,0,9),(2,1,1,10),(3,2,0,9),(4,2,1,10),(5,3,0,9),(6,3,1,10),(7,4,0,9),(8,4,1,10),(9,5,0,9),(10,5,1,10),(11,6,0,9),(12,6,1,10),(13,7,0,9),(14,7,1,10),(15,8,0,9),(16,8,1,10),(17,9,0,9),(18,9,1,10),(19,10,0,9),(20,10,1,10),(21,11,0,9),(22,11,1,10);
/*!40000 ALTER TABLE `competence_profile` ENABLE KEYS */;
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
INSERT INTO `localized_competence` VALUES (0,1,'korvgrillning'),(0,2,'sausage grilling'),(1,1,'karuselldrift'),(1,2,'carousel operation');
/*!40000 ALTER TABLE `localized_competence` ENABLE KEYS */;
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
INSERT INTO `status` VALUES (0,'PÅGÅENDE'),(1,'AVSLAGEN'),(2,'ACCEPTERAD');
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

-- Dump completed on 2017-03-13 10:21:18
