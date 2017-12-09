-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: rft_konyvtar
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Table structure for table `kvt_user`
--

DROP TABLE IF EXISTS `kvt_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kvt_user` (
  `USER_ID` int(6) NOT NULL AUTO_INCREMENT,
  `FORENAME` varchar(50) NOT NULL,
  `SURNAME` varchar(50) NOT NULL,
  `DATE_OF_BIRTH` date NOT NULL,
  `LOGIN_NAME` varchar(15) NOT NULL,
  `PASSWORD` varchar(30) NOT NULL,
  `EMAIL_ADDR` varchar(50) NOT NULL,
  `REGISTERED_ON` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `LAST_LOGIN` timestamp NULL DEFAULT NULL,
  `END_DATE` date DEFAULT NULL,
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `USER_ID` (`USER_ID`),
  UNIQUE KEY `LOGIN_NAME` (`LOGIN_NAME`),
  UNIQUE KEY `EMAIL_ADDR` (`EMAIL_ADDR`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kvt_user`
--

LOCK TABLES `kvt_user` WRITE;
/*!40000 ALTER TABLE `kvt_user` DISABLE KEYS */;
INSERT INTO `kvt_user` VALUES (1,'Sándor','Szabó','1981-02-12','sanyika81','sanyika','szabo.sandor81@asd.com','2017-11-26 22:21:27','2017-12-09 18:16:02',NULL),(8,'Géza','Kelemen','1972-09-02','gezu72','kelemen','kgezu72@asd.com','2017-11-28 03:07:16','2021-12-31 23:00:00',NULL),(9,'Péter','Jónás','1968-11-09','jonaspeti68','petike','jonas.peter68@asd.com','2017-11-28 19:13:00','2021-12-31 23:00:00',NULL),(13,'Takács','Dániel','1996-03-13','dtakacs','asdf1234','takacs.f.daniel@gmail.com','2017-12-06 21:47:06','2017-12-08 18:26:17',NULL),(14,'Antal','Trab','1995-06-15','trabant95','trabi','trabi95@asd.com','2017-12-09 17:43:48','2017-12-09 18:47:01',NULL);
/*!40000 ALTER TABLE `kvt_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-09 20:00:56
