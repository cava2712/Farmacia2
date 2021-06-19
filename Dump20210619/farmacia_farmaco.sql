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
  `quantità` int DEFAULT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `marca` varchar(45) DEFAULT NULL,
  `categoria` varchar(45) DEFAULT NULL,
  `percorsoImg` varchar(45) DEFAULT NULL,
  `prezzo` float NOT NULL,
  PRIMARY KEY (`codice`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `farmaco`
--

LOCK TABLES `farmaco` WRITE;
/*!40000 ALTER TABLE `farmaco` DISABLE KEYS */;
INSERT INTO `farmaco` VALUES (0,5,'oki','oki','antiinfiammatorio',NULL,0),(1,5,'attacchipirina','moment','malditesta',NULL,0),(2,2,'a','a','a','a',1),(3,1,'b','a','a','a',1),(4,1,'c','a','a','a',1),(5,1,'d','a','a','a',2),(6,1,'e','a','a','a',3),(7,1,'f','a','a','a',4),(8,1,'g','a','a','a',5),(9,1,'h','a','a','a',6),(10,1,'i','a','a','a',7),(11,1,'l','a','a','a',8),(12,1,'m','a','a','a',9),(13,1,'n','a','a','a',0),(14,1,'o','a','a','a',1),(15,1,'p','a','a','a',2),(16,1,'q','a','a','a',3),(17,1,'r','a','a','a',4),(18,1,'s','a','a','a',5),(19,1,'t','a','a','a',6),(20,1,'u','a','a','a',7),(21,1,'v','a','a','a',8),(22,1,'z','a','a','a',9);
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

-- Dump completed on 2021-06-19 17:26:51
