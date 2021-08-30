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
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `farmaco`
--

LOCK TABLES `farmaco` WRITE;
/*!40000 ALTER TABLE `farmaco` DISABLE KEYS */;
INSERT INTO `farmaco` VALUES (0,99972,'oki','oki task','anti-infiammatorio','oki.jpg',15.900,'false'),(1,98970,'tachipirina','tachipirina','mal di testa','tachipirina.jpg',9.560,'true'),(2,99971,'moment','moment','anti-dolorifico','moment.jpg',45.980,'false'),(3,20,'asfdags','sssss','anti-dolorificoo','',34.000,'true'),(4,18,'c','a','anti-dolorifico','',1.000,'false'),(5,18,'d','a','anti-dolorifico','',2.000,'false'),(6,8,'e','a','anti-dolorifico','',3.000,'false'),(7,18,'f','a','malditesta','',4.000,'true'),(8,2,'g','a','malditesta','',5.000,'false'),(9,1,'h','a','malditesta','',6.000,'false'),(12,8,'m','a','anti-infiammatorio','',9.000,'false'),(13,1,'n','a','anti-infiammatorio','',0.000,'false'),(14,3,'o','a','anti-infiammatorio','',1.000,'false'),(15,3,'p','a','a','',2.000,'false'),(16,3,'q','a','a','',3.000,'false'),(17,3,'r','a','a','',4.000,'false'),(19,3,'t','a','a','',6.000,'false'),(20,3,'u','a','a','',7.000,'false'),(21,70,'v','a','a','',8.000,'true'),(22,13,'z','a','a','',9.000,'false'),(23,21,'ss','a','a','',1.000,'false'),(24,34,'asfad','asdfa','ddddd','',34.000,'false'),(25,12,'prova','din','don','prova.png',12.000,'false'),(28,22,'prosciutto','parma','affettati','',36.080,'false'),(32,2,'propro','pro','pro','',3.000,'true');
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

-- Dump completed on 2021-08-30 10:54:07
