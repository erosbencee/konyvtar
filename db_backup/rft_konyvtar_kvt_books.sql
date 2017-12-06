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
-- Table structure for table `kvt_books`
--

DROP TABLE IF EXISTS `kvt_books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kvt_books` (
  `ISBN_ID` varchar(14) NOT NULL,
  `TITLE` varchar(50) NOT NULL,
  `SUBTITLE` varchar(50) DEFAULT NULL,
  `AUTHOR` varchar(50) NOT NULL,
  `PUBLISHER` varchar(50) NOT NULL,
  `GENRE` varchar(50) NOT NULL,
  `ONHAND_QTY` int(3) NOT NULL,
  PRIMARY KEY (`ISBN_ID`),
  UNIQUE KEY `ISBN_ID` (`ISBN_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kvt_books`
--

LOCK TABLES `kvt_books` WRITE;
/*!40000 ALTER TABLE `kvt_books` DISABLE KEYS */;
INSERT INTO `kvt_books` VALUES ('9789630674799','Ízek bűvöletében',NULL,'Gordon Ramsay','T. Bálint Kiadó','gasztronómia',9),('9789631265330','Schon verstehe! - Már értem!','Magyar-német villanyszerelői szakszótár','Szesztai Csaba Károly','Magánkiadás','lexikon, enciklopédia',3),('9789632799452','India - A múltból a jövő felé',NULL,'Gáthy Vera','Typotex Elektronikus Kiadó Kft.','történelem',3),('9789634058045','Az',NULL,'Stephen King','Viking','horror, thriller',12),('9789634058397','A Gyűrűk Ura I-III.',NULL,'J. R. R. Tolkien','Allen & Unwin','fantasy',10),('9789634156123','Tüskevár',NULL,'Fekete István','Móra Ferenc Ifjusági Könyvkiadó Kft.','ifjúsági irodalom, szépirodalom',9),('9789638985750','E-mail-marketing',NULL,'Benyó Dániel','Kreatív Kontroll Kft.','reklám, marketing',2),('9789639301498','Tanuljuk meg a MySQL használatát 24 óra alatt',NULL,'Julie C. Meloni','Kiskapu Kiadó','számítástechnika',6),('9789639863354','Szoftverfejlesztés Java SE platformon',NULL,'Kövesdán Gábor','Szak Kiadó Kft.','számítástechnika, internet',4),('9789639868762','Az Ember a Fellegvárban',NULL,'Philip K. Dick','Agave Könyvek Kiadó Kft.','sci-fi',5);
/*!40000 ALTER TABLE `kvt_books` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-06 10:57:29
