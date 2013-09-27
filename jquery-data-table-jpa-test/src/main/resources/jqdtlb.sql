-- MySQL dump 10.13  Distrib 5.5.32, for debian-linux-gnu (i686)
--
-- Host: localhost    Database: jqdtlb
-- ------------------------------------------------------
-- Server version	5.5.32-0ubuntu0.13.04.1

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
-- Table structure for table `dados`
--

DROP TABLE IF EXISTS `dados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dados` (
  `engine` varchar(255) DEFAULT NULL,
  `browser` varchar(255) DEFAULT NULL,
  `platform` varchar(255) DEFAULT NULL,
  `version` varchar(255) DEFAULT NULL,
  `grade` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dados`
--

LOCK TABLES `dados` WRITE;
/*!40000 ALTER TABLE `dados` DISABLE KEYS */;
INSERT INTO `dados` VALUES ('Gecko','Firefox 1.0','Win 98+ / OSX.2+','ABCDE','A',1),('Gecko1','Chrome','Windows &','ABC','B',2),('Gecko2','Safari','MacOS','AB','C',3),('Gecko3','Opera','Windows 7','A','D',4),('Gecko','Firefox 1.0','Win 98+ / OSX.2+','ABCDE','A',5),('Gecko1','Chrome','Windows &','ABC','B',6),('Gecko2','Safari','MacOS','AB','C',7),('Gecko3','Opera','Windows 7','A','D',8),('Gecko','Firefox 1.0','Win 98+ / OSX.2+','ABCDE','A',9),('Gecko1','Chrome','Windows &','ABC','B',10),('Gecko2','Safari','MacOS','AB','C',11),('Gecko3','Opera','Windows 7','A','D',12),('Gecko','Firefox 1.0','Win 98+ / OSX.2+','ABCDE','A',13),('Gecko1','Chrome','Windows &','ABC','B',14),('Gecko2','Safari','MacOS','AB','C',15),('Gecko3','Opera','Windows 7','A','D',16),('Gecko','Firefox 1.0','Win 98+ / OSX.2+','ABCDE','A',17),('Gecko1','Chrome','Windows &','ABC','B',18),('Gecko2','Safari','MacOS','AB','C',19),('Gecko3','Opera','Windows 7','A','D',20);
/*!40000 ALTER TABLE `dados` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-09-26 23:14:48
