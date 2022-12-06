-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: dealership
-- ------------------------------------------------------
-- Server version	8.0.20

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
-- Table structure for table `audi_models`
--

DROP TABLE IF EXISTS `audi_models`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `audi_models` (
  `model` varchar(20) NOT NULL,
  `price` int NOT NULL,
  PRIMARY KEY (`model`),
  UNIQUE KEY `audi_models_model_uindex` (`model`),
  KEY `model` (`model`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `audi_models`
--

LOCK TABLES `audi_models` WRITE;
/*!40000 ALTER TABLE `audi_models` DISABLE KEYS */;
INSERT INTO `audi_models` VALUES ('A6',50000),('Q7',75000),('Q8',100000),('RS6 Avant',150000);
/*!40000 ALTER TABLE `audi_models` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bmw_models`
--

DROP TABLE IF EXISTS `bmw_models`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bmw_models` (
  `model` varchar(20) NOT NULL,
  `price` int NOT NULL,
  PRIMARY KEY (`model`),
  UNIQUE KEY `bmw_models_model_uindex` (`model`),
  KEY `model` (`model`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bmw_models`
--

LOCK TABLES `bmw_models` WRITE;
/*!40000 ALTER TABLE `bmw_models` DISABLE KEYS */;
INSERT INTO `bmw_models` VALUES ('840d xDrive',120000),('M5 xDrive',115000),('X5 xDrive30d',90000),('X7 M50d AT',95000);
/*!40000 ALTER TABLE `bmw_models` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clients` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `surname` varchar(20) NOT NULL,
  `login` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `password` (`password`),
  KEY `FK_clients_users` (`login`),
  KEY `name` (`name`),
  KEY `surname` (`surname`),
  CONSTRAINT `FK_clients_users` FOREIGN KEY (`id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES (1,'Sergey','Lazarev','user1','user1'),(2,'Vera','Brezhneva','user2','user2'),(3,'Nastya','Ivleeva','user3','user3'),(4,'Ilya','Prusikin','user4','user4');
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `insurance`
--

DROP TABLE IF EXISTS `insurance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `insurance` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(20) NOT NULL,
  `price` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `insurance`
--

LOCK TABLES `insurance` WRITE;
/*!40000 ALTER TABLE `insurance` DISABLE KEYS */;
INSERT INTO `insurance` VALUES (1,'standard 15 day',30),(2,'casco 1 year',1000),(3,'standard 30 days',50),(4,'osago 3 month',500);
/*!40000 ALTER TABLE `insurance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `make`
--

DROP TABLE IF EXISTS `make`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `make` (
  `make` varchar(20) NOT NULL,
  PRIMARY KEY (`make`),
  UNIQUE KEY `make` (`make`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `make`
--

LOCK TABLES `make` WRITE;
/*!40000 ALTER TABLE `make` DISABLE KEYS */;
INSERT INTO `make` VALUES ('Audi'),('BMW'),('Mercedes');
/*!40000 ALTER TABLE `make` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `managers`
--

DROP TABLE IF EXISTS `managers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `managers` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `surname` varchar(20) NOT NULL,
  `login` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `login` (`login`),
  KEY `password` (`password`),
  CONSTRAINT `FK_managers_users` FOREIGN KEY (`id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK_managers_users_2` FOREIGN KEY (`login`) REFERENCES `users` (`login`),
  CONSTRAINT `FK_managers_users_3` FOREIGN KEY (`password`) REFERENCES `users` (`password`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `managers`
--

LOCK TABLES `managers` WRITE;
/*!40000 ALTER TABLE `managers` DISABLE KEYS */;
INSERT INTO `managers` VALUES (1,'Aleksey','Kuznetsov','man1','man1'),(2,'Eugene','Vaneev','man2','man2');
/*!40000 ALTER TABLE `managers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mercedes_models`
--

DROP TABLE IF EXISTS `mercedes_models`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mercedes_models` (
  `model` varchar(20) NOT NULL,
  `price` int NOT NULL,
  PRIMARY KEY (`model`),
  UNIQUE KEY `mercedes_models_model_uindex` (`model`),
  KEY `model` (`model`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mercedes_models`
--

LOCK TABLES `mercedes_models` WRITE;
/*!40000 ALTER TABLE `mercedes_models` DISABLE KEYS */;
INSERT INTO `mercedes_models` VALUES ('E220 d 4MATIC',60000),('GLE 400 d 4MATIC',80000),('GLS 580 4MATIC',120000);
/*!40000 ALTER TABLE `mercedes_models` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `number` int unsigned NOT NULL AUTO_INCREMENT,
  `order_accepted` tinyint unsigned NOT NULL DEFAULT '0',
  `insurance` varchar(20) NOT NULL DEFAULT '0',
  `country` varchar(20) NOT NULL DEFAULT '0',
  `color` varchar(20) NOT NULL DEFAULT '0',
  `make` varchar(20) NOT NULL,
  `year` year NOT NULL,
  `order_completed` tinyint unsigned NOT NULL DEFAULT '0',
  `model` varchar(20) NOT NULL,
  `price` int unsigned NOT NULL,
  `name` varchar(20) NOT NULL,
  `surname` varchar(20) NOT NULL,
  PRIMARY KEY (`number`),
  KEY `FK_order_make` (`make`),
  KEY `model` (`model`),
  KEY `name` (`name`),
  KEY `surname` (`surname`),
  KEY `insurance` (`insurance`),
  CONSTRAINT `FK_order_clients` FOREIGN KEY (`name`) REFERENCES `clients` (`name`),
  CONSTRAINT `FK_order_clients_2` FOREIGN KEY (`surname`) REFERENCES `clients` (`surname`),
  CONSTRAINT `FK_order_insurance` FOREIGN KEY (`insurance`) REFERENCES `insurance` (`type`) ON UPDATE CASCADE,
  CONSTRAINT `FK_order_make` FOREIGN KEY (`make`) REFERENCES `make` (`make`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (4,0,'standard 15 day','Germany','WHITE','BMW',2017,0,'X7 M50d AT',95000,'Sergey','Lazarev'),(6,1,'osago 3 month','Russia','RED','Audi',2016,0,'RS6 Avant',150000,'Ilya','Prusikin'),(7,0,'casco 1 year','USA','BLUE','Mercedes',2018,0,'E220 d 4MATIC',80000,'Vera','Brezhneva'),(10,1,'standart 30 day','Russia','YELLOW','BMW',2019,0,'840d xDrive',120000,'Nastya','Ivleeva');
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier` (
  `id` int NOT NULL AUTO_INCREMENT,
  `country` varchar(20) NOT NULL DEFAULT '0',
  `make` varchar(20) NOT NULL DEFAULT '0',
  `name` varchar(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `make` (`make`),
  KEY `name` (`name`),
  CONSTRAINT `FK_supplier_make` FOREIGN KEY (`make`) REFERENCES `make` (`make`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (1,'Russia','BMW','BMWRussia'),(2,'Germany','BMW','BMWGermany'),(3,'USA','Audi','AudiUSA'),(4,'Russia','Audi','AudiRussia'),(5,'Germany','Mercedes','MercedesDE'),(6,'USA','Mercedes','MercedesUS');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier_order`
--

DROP TABLE IF EXISTS `supplier_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier_order` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `make` varchar(20) NOT NULL DEFAULT '0',
  `supplier` varchar(20) NOT NULL DEFAULT '0',
  `model` varchar(20) NOT NULL DEFAULT '0',
  `quantity` int unsigned NOT NULL DEFAULT '0',
  `color` varchar(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_supplier_order_make` (`make`),
  KEY `FK_supplier_order_supplier` (`supplier`),
  CONSTRAINT `FK_supplier_order_make` FOREIGN KEY (`make`) REFERENCES `make` (`make`),
  CONSTRAINT `FK_supplier_order_supplier` FOREIGN KEY (`supplier`) REFERENCES `supplier` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier_order`
--

LOCK TABLES `supplier_order` WRITE;
/*!40000 ALTER TABLE `supplier_order` DISABLE KEYS */;
INSERT INTO `supplier_order` VALUES (4,'BMW','BMWGermany','X7 M50d AT',3,'black');
/*!40000 ALTER TABLE `supplier_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `account_type` varchar(10) NOT NULL,
  `login` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login` (`login`),
  KEY `password` (`password`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','admin','admin'),(2,'manager','man1','man1'),(3,'manager','man2','man2'),(4,'client','user1','user1'),(5,'client','user2','user2'),(6,'client','user3','user3'),(7,'client','user4','user4');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-22  3:08:11
