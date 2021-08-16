-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: paddystorage
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `admin_id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'admin','admin','admin@gmail.com','admin1234','2021-07-25 21:06:52');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paddy_storage`
--

DROP TABLE IF EXISTS `paddy_storage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paddy_storage` (
  `paddy_storage_id` int NOT NULL AUTO_INCREMENT,
  `paddy_type_id` int NOT NULL,
  `total_amount` decimal(2,0) DEFAULT NULL,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`paddy_storage_id`),
  KEY `paddy_storage_paddy_type_id_fk_idx` (`paddy_type_id`),
  CONSTRAINT `paddy_storage_paddy_type_id_fk` FOREIGN KEY (`paddy_type_id`) REFERENCES `paddy_type` (`paddy_type_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paddy_storage`
--

LOCK TABLES `paddy_storage` WRITE;
/*!40000 ALTER TABLE `paddy_storage` DISABLE KEYS */;
/*!40000 ALTER TABLE `paddy_storage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paddy_type`
--

DROP TABLE IF EXISTS `paddy_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paddy_type` (
  `paddy_type_id` int NOT NULL AUTO_INCREMENT,
  `paddy_type_name` varchar(45) NOT NULL,
  `paddy_type_price` decimal(2,0) NOT NULL,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`paddy_type_id`),
  UNIQUE KEY `paddy_type_name_UNIQUE` (`paddy_type_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paddy_type`
--

LOCK TABLES `paddy_type` WRITE;
/*!40000 ALTER TABLE `paddy_type` DISABLE KEYS */;
INSERT INTO `paddy_type` VALUES (1,'Nadu',70,'2021-07-27 13:03:39'),(2,'Rathu Kakulu',65,'2021-07-27 12:22:44'),(3,'Sudu Kakulu',63,'2021-07-27 12:23:06');
/*!40000 ALTER TABLE `paddy_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_first_name` varchar(45) NOT NULL,
  `user_last_name` varchar(45) DEFAULT NULL,
  `user_address` varchar(150) NOT NULL,
  `user_contact_no` int DEFAULT NULL,
  `user_created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `user_NIC` varchar(12) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_NIC_UNIQUE` (`user_NIC`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Nilantha ',' Kulatunga','No.107, Galle Road,Galle',712563458,'2021-07-26 12:23:49','753625142V'),(2,'Lasika ',' Amarashinghe','No.36, Station Road,Anuradhapura',778541236,'2021-07-26 12:23:49','774172589V'),(3,'Rizhara ',' Dissanayake','122 Kandy Road,Kandy',784512547,'2021-07-26 12:23:49','801245789V'),(4,'Charitha  ','Attygale','70 Colombo Road, New Town Monaragala',769853241,'2021-07-26 12:23:49','821457455V'),(5,'Saman','Gunarathna','Anuradhapura',785236985,'2021-07-27 09:11:33','852031425V'),(7,'p','p','p',2,'2021-07-27 09:19:21','p'),(8,'Saman','Gamage','Galle Street ,Galle',784512369,'2021-07-27 12:24:23','856324127V'),(9,'s','s','s',2,'2021-07-27 12:26:51','s'),(10,'Nuwan','Gunasekara','Temple Street, Anuradhapura',785236985,'2021-07-27 13:04:42','658745623V');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_paddy_storage`
--

DROP TABLE IF EXISTS `user_paddy_storage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_paddy_storage` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `paddy_type_id` int NOT NULL,
  `amount` decimal(2,0) DEFAULT NULL,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`,`paddy_type_id`),
  KEY `user_paddy_storage_paddy_type_id_idx` (`paddy_type_id`),
  CONSTRAINT `user_paddy_storage_paddy_type_id` FOREIGN KEY (`paddy_type_id`) REFERENCES `paddy_type` (`paddy_type_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_paddy_storage_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_paddy_storage`
--

LOCK TABLES `user_paddy_storage` WRITE;
/*!40000 ALTER TABLE `user_paddy_storage` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_paddy_storage` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-27 13:33:59
