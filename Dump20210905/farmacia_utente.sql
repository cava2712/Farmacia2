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
  `img` varchar(100) NOT NULL,
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
INSERT INTO `utente` VALUES ('cvzdvd','admin','admin','amministratore','admin','admin','','2000-11-27 00:00:00'),('cvzdvd','alessandro','cavazzuti','cliente','ale','alessandro','default.png','2000-11-27 00:00:00'),('cvzdvd','simone','colombo','cliente','colombo','asdfasdf','default.png','2000-11-27 00:00:00'),('cvzdvd','luca','barbieri','cliente','barbieri','barbieri','default.png','2021-04-18 00:00:00'),('cvzdvd','mario','bianchi','cliente','bianchi','bianchi','','2021-06-22 00:00:00'),('cvzdvd','luigi','bruno','cliente','bruno','bruno','default.png','2000-11-27 00:00:00'),('caio','caio','caio','farmacista','caio','caio','default.png','2021-08-29 00:00:00'),('cvzdvd','davide','cavazzuti','cliente','cavazzuti','cavazzuti','','2000-11-27 00:00:00'),('cvzdvd','utente','utente','cliente','cliente','cliente','default.png','2021-06-23 00:00:00'),('cvzdvd','giovanni','esposito','cliente','esposito','esposito','','2000-11-27 00:00:00'),('cvzdvd','barbara','giordano','cliente','giordano','giordano','','2000-11-27 00:00:00'),('cvzdvd','alessio','greco','cliente','greco','greco','default.png','2000-11-27 00:00:00'),('cvzdvd','claudia','innocenti','cliente','innocenti','innocenti','','2000-11-27 00:00:00'),('cvzdvd','antonio','leone','cliente','leone','leone','default.png','2006-01-04 00:00:00'),('cvzdvd','luca','barbieri','cliente','luca','lucalu','','2000-08-28 00:00:00'),('cvzdvd','marco','marco','cliente','marco','marco','default.png','2021-06-20 00:00:00'),('cvzdvd','alessia','marino','cliente','marino','marino','','2000-11-27 00:00:00'),('cvzdvd','mario','red','cliente','cava','mario','','2000-11-27 00:00:00'),('massimo','massimo','massimo','farmacista','massimo','massimo','default.png','2021-09-28 00:00:00'),('cvzdvd','lucia','mazza','cliente','mazza','mazza','','2000-11-27 00:00:00'),('cvzdvd','francesco','cavazzuti','cliente','davide','mazzama','','2000-11-27 00:00:00'),('cvzdvd','anita','moccia','cliente','moccia','moccia','','2000-11-27 00:00:00'),('cvzdvd','giorgio','moretti','cliente','moretti','moretti','','2000-11-27 00:00:00'),('cvzdvd','cristian','moro','cliente','moro','moro','default.png','2000-11-27 00:00:00'),('cvzdvd','lucio','ricci','cliente','ricci','ricci','','2000-11-27 00:00:00'),('cvzdvd','silvestro','rizzi','cliente','rizzi','rizzi','default.png','2000-11-27 00:00:00'),('cvzdvd','paolo','rossi','cliente','rossi','rossi','','2000-11-27 00:00:00');
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

-- Dump completed on 2021-09-05 14:44:46
