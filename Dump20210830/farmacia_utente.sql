-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: farmacia
-- ------------------------------------------------------
-- Server version	8.0.25

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `utente`
--

DROP TABLE IF EXISTS `utente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utente` (
  `cf` varchar(16) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `cognome` varchar(45) NOT NULL,
  `types` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `img` varchar(100) DEFAULT NULL,
  `dataDiNascita` datetime NOT NULL,
  PRIMARY KEY (`email`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utente`
--

LOCK TABLES `utente` WRITE;
/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
INSERT INTO `utente` VALUES ('a','a','a','cliente','a','a','a.png','2000-11-27 00:00:00'),('aaa','aaa','aaa','cliente','aaa','aaa','default.png','2021-04-18 00:00:00'),('rt','aa','a','a','asdfadsf','aaaaa','','2000-11-27 00:00:00'),('a','a','a','farmacista','ab','ab','default.png','2021-08-14 00:00:00'),('aaa','aaa','aaa','farmacista','a','aba','','2000-08-28 00:00:00'),('sa','sa','sa','amministratore','am','am','null','2000-11-27 00:00:00'),('ASDF','aSD','ASD','cliente','ASD','ASD','','2000-11-27 00:00:00'),('ADS','aSAS','DASDASDSD','cliente','ASDASDA','ASDA','','2000-11-27 00:00:00'),('asdaf','sadf','cavazzuti','cliente','adfasd','asdasadsffa','default.png','2000-11-27 00:00:00'),('asdfasf','asdfas','asdfasd','cliente','asdfasdf','asdfasdf','default.png','2000-11-27 00:00:00'),('cvzdvd','davide','cavazzuti','cliente','cava','cava','cava.png','2000-11-27 00:00:00'),('asdfa','franco','rossi','farmacista','franco','franco','default.png','1980-08-19 00:00:00'),('dfsadfwe','luca','barbieri','cliente','luca','luca','','2000-08-28 00:00:00'),('marco','marco','marco','cliente','marco','marco','default.png','2021-06-20 00:00:00'),('sadfas','paola','rossetto','farmacista','paolo','paolo','paolo.png','2000-11-10 00:00:00'),('prova1','prova1','prova1','cliente','prova1','prova1','','2000-11-27 00:00:00'),('provaaaa','provaaaa','provaaaa','cliente','provaaaa','provaaaa','default.png','2000-11-27 00:00:00'),('provaq','provaq','provaq','farmacista','prova','provaq','provaq.png','2021-06-08 00:00:00'),('sadfasdfasdfsdf','davide','cavazzuti','cliente','davide','sadfadfas','','2000-11-27 00:00:00'),('asdafsd','dat','dat','cliente','aefasf','sdfasffasd','default.png','2006-01-04 00:00:00'),('asd','dsfg','sdfg','cliente','sdf','sdfgsdf','','2000-11-27 00:00:00'),('sdfgsdfgs','sfdgsdfg','gsdfgsdfs','cliente','sdfgsdfs','sdfgsdfg','sdfgsdfg.png','2021-06-22 00:00:00'),('sdfgsdfg','sfgsd','sdfgsd','cliente','sdfgsdfg','sdfgsg','default.png','2000-11-27 00:00:00'),('gsdfgsdg','fgwetdg','sdfgdfg','cliente','sdfgsd','sdfsdgs','','2000-11-27 00:00:00'),('stefano','stef','stefano','farmacista','stefano','stefano','stefano.png','2021-06-23 00:00:00'),('tr','tr','tr','cliente','trov','tr','','2000-11-27 00:00:00'),('ved','ved','ved','cliente','ved','ved','default.png','2000-11-27 00:00:00'),('veda','veda','veda','cliente','veda','veda','veda.png','2000-11-27 00:00:00'),('asadf','a','a','cliente','a','vediamo','null','2000-11-27 00:00:00'),('zucca','z la formica','zorro','cliente','zucca','ziocan','null','2000-11-27 00:00:00');
/*!40000 ALTER TABLE `utente` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-30 10:54:07
