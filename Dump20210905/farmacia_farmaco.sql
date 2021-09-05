-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: farmacia
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `farmaco`
--

DROP TABLE IF EXISTS `farmaco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `farmaco` (
  `codice` int NOT NULL AUTO_INCREMENT,
  `quantità` int NOT NULL,
  `nome` varchar(45) NOT NULL,
  `marca` varchar(45) NOT NULL,
  `categoria` varchar(45) NOT NULL,
  `percorsoImg` varchar(100) NOT NULL,
  `prezzo` float(6,3) NOT NULL,
  `ricetta` varchar(45) NOT NULL DEFAULT 'false',
  PRIMARY KEY (`codice`),
  UNIQUE KEY `nome_UNIQUE` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `farmaco`
--

LOCK TABLES `farmaco` WRITE;
/*!40000 ALTER TABLE `farmaco` DISABLE KEYS */;
INSERT INTO `farmaco` VALUES (0,93,'xanax','VIATRIS PHARMA Srl','antidepressivo','xanax.jpg',15.900,'false'),(1,100,'tachipirina','ANGELINI SpA','emicrania','tachipirina.jpg',9.560,'true'),(2,100,'moment','ANGELINI SpA','anti-dolorifico','moment.jpg',45.980,'false'),(3,100,'oki','DOMPE` FARMACEUTICI SpA','anti-dolorifico','oki.jpg',34.000,'true'),(4,95,'norvasc','PFIZER ITALIA Srl','anti-dolorifico','',1.000,'false'),(5,100,'emoxil','PFIZER ITALIA Srl','anti-dolorifico','emoxil.jpg',2.000,'false'),(6,100,'augmentin','ANGELINI SpA','anti-dolorifico','augmentin.jpg',3.000,'false'),(7,100,'iopidine','ANGELINI SpA','emicrania','iopidine.jpg',4.000,'true'),(9,100,'nuvigil','DOMPE` FARMACEUTICI SpA','emicrania','nuvigil.jpg',6.000,'false'),(10,100,'ternomil','Budpak Inc.','antivirale','',82.000,'true'),(11,86,'strattera','Apotheca Company','antivirale','strattera.jpg',97.000,'true'),(12,98,'imuran','PFIZER ITALIA Srl','anti-infiammatorio','',9.000,'false'),(13,100,'zitromax','ANGELINI SpA','anti-infiammatorio','',0.000,'false'),(14,100,'beconase','PFIZER ITALIA Srl','anti-infiammatorio','',1.000,'false'),(15,97,'tessalon','ALLERGAN SpA','antivirale','',2.000,'false'),(16,93,'celebrex','DOMPE` FARMACEUTICI SpA','anti-infiammatorio','',3.000,'false'),(17,100,'diabinese','ALLERGAN SpA','diabete','',4.000,'false'),(18,100,'prevalite','PFIZER ITALIA Srl','antivirale','',17.000,'true'),(19,95,'colestid','PFIZER ITALIA Srl','antidepressivo','',6.000,'false'),(20,100,'seromicina','DOMPE` FARMACEUTICI SpA','antidepressivo','',7.000,'false'),(21,86,'cosmegen','PFIZER ITALIA Srl','antipertensivi','',8.000,'true'),(22,50,'aromasin','DOMPE` FARMACEUTICI SpA','antipertensivi','',9.000,'true'),(23,100,'tambocor','DOMPE` FARMACEUTICI SpA','anticoagulanti','tambocor.jpg',1.000,'false'),(24,97,'vanos','DOMPE` FARMACEUTICI SpA','anticoagulanti','',34.000,'false'),(25,100,'ocufen','ALLERGAN SpA','colirio','ocufen.jpg',12.000,'false');
/*!40000 ALTER TABLE `farmaco` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-09-05 14:44:46
