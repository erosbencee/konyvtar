CREATE DATABASE  IF NOT EXISTS `rft_konyvtar` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `rft_konyvtar`;
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
  `USER_ID` int(6) NOT NULL,
  `FORENAME` varchar(50) NOT NULL,
  `SURNAME` varchar(50) NOT NULL,
  `DATE_OF_BIRTH` date NOT NULL,
  `LOGIN_NAME` varchar(15) NOT NULL,
  `PASSWORD` varchar(30) NOT NULL,
  `EMAIL_ADDR` varchar(50) NOT NULL,
  `REGISTERED_ON` date DEFAULT NULL,
  `LAST_LOGIN` date DEFAULT NULL,
  UNIQUE KEY `USER_ID` (`USER_ID`),
  UNIQUE KEY `LOGIN_NAME` (`LOGIN_NAME`),
  UNIQUE KEY `EMAIL_ADDR` (`EMAIL_ADDR`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kvt_user`
--

LOCK TABLES `kvt_user` WRITE;
/*!40000 ALTER TABLE `kvt_user` DISABLE KEYS */;
INSERT INTO `kvt_user` VALUES (100000,'Dummy','User','2017-10-16','DumUsr','asd123','dummy@user.rft','2017-10-16','2017-10-16'),(100001,'Dummy2','Dummy2','1978-02-12','dumdum2','dum2pw','dum2@dum2.xd','2017-11-07','4000-01-01'),(100002,'Pista','Jóska','1983-06-16','jospis','joska','joska69@gmail.com','2017-11-08','4000-01-01');
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

-- Dump completed on 2017-11-08 10:45:59
