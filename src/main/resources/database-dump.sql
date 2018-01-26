CREATE DATABASE  IF NOT EXISTS `abc_banking_group` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `abc_banking_group`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: test
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.29-MariaDB

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
-- Table structure for table `abc_banking_group`.`account`
--

DROP TABLE IF EXISTS `abc_banking_group`.`account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `abc_banking_group`.`account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `transaction_uuid` varchar(50) DEFAULT NULL COMMENT 'UUID to identify the transaction',
  `client_id` int(11) DEFAULT NULL COMMENT 'Foreign key client.id',
  `transaction_type` varchar(100) DEFAULT NULL COMMENT 'withdrawal, deposit',
  `transaction_amount` int(11) DEFAULT NULL,
  `timestamp` timestamp NULL DEFAULT NULL,
  `done_by` varchar(250) DEFAULT NULL COMMENT 'username or card-id',
  `client_type` varchar(45) DEFAULT NULL COMMENT 'atm, online-banking',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=59 DEFAULT CHARSET=utf8 COMMENT='Holds accounts data';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `abc_banking_group`.`account`
--

LOCK TABLES `abc_banking_group`.`account` WRITE;
/*!40000 ALTER TABLE `abc_banking_group`.`account` DISABLE KEYS */;
INSERT INTO `abc_banking_group`.`account` VALUES (1,'f55f4848-db8b-451c-acad-0a627f17c51e',1,'d',500,'2018-01-15 11:56:35','Jone Smith','online-banking'),(2,'2ffd559e-bb21-468e-8b97-752034c85c5c',1,'w',30,'2018-01-12 23:43:49','Salim Wraa','atm'),(3,'d317a4dd-2930-4a96-9ea2-2f017efe2356',1,'d',1200,'2018-01-17 14:11:24','Khan Mimo','online-banking'),(4,'7061d482-3868-4d8e-a18c-2ef152c365a4',1,'d',12000,'2018-01-11 11:45:50','Yuki Shwan','online-banking'),(5,'e299f09b-1239-477a-9bde-f4b87c394f63',1,'w',100,'2018-01-15 06:22:13','Ahmed Al-Adaileh','online-banking'),(6,'839c30e0-92bb-426b-a4cf-3ee8e86832d8',1,'w',100,'2018-01-17 11:16:59','Yuzgat Yuki','atm'),(7,'6eeb34b1-9f2d-4516-9a8e-282e95d8d8eb',1,'w',1200,'2018-01-22 23:08:50','null','online-banking'),(8,'a005ef5a-c6d5-4d5b-aa7d-3d0ecd7a5815',1,'d',1200,'2018-01-22 23:09:12','null','online-banking'),(9,'2179637d-1cfd-4454-9b64-c0f104a8042a',1,'w',150,'2018-01-22 23:21:03','uiooiuzuioppoi','online-banking'),(10,'e4cc6016-ca08-4c06-bf1f-8f355beb53a8',1,'w',120,'2018-01-22 23:24:16','jkj','online-banking'),(11,'87e2bd78-5732-4120-961e-32eeea33f963',1,'w',120,'2018-01-22 23:24:50','jkj','online-banking'),(12,'672bf5cc-cd05-4aab-87e9-565e09cdb1b9',1,'w',120,'2018-01-22 23:26:07','jkj','online-banking'),(13,'daf480d6-7cfb-438e-9c59-7679e6a5d234',1,'w',400,'2018-01-23 13:22:37','Abdullah Korashi','online-banking'),(14,'c17bcf7b-f1b8-43ec-b8c2-4c10624ba00e',1,'w',390,'2018-01-23 13:26:25','Dirk Klaus','online-banking'),(15,'8301a4fe-2fbe-4c5c-9c40-feed10f210b9',1,'d',20,'2018-01-23 17:59:35','Ahmed Al-Adaileh','online-banking'),(16,'0057daa1-067c-40fe-b1a9-753fab2719c0',1,'d',40,'2018-01-23 18:00:03','Ahmed Al-Adaileh','online-banking'),(17,'bf4a8cb9-0bb9-41df-9d38-232d3a057ed3',1,'d',60,'2018-01-23 18:00:12','Ahmed Al-Adaileh','online-banking'),(18,'51859b8c-97a4-41e8-b8d9-7d17c86d9f03',1,'d',950,'2018-01-23 18:00:23','Ahmed Al-Adaileh','online-banking'),(19,'e51395ae-8218-46f5-97a8-a17177bff626',1,'d',20,'2018-01-23 18:04:36','Ahmed Al-Adaileh','atm'),(20,'42616328-4866-48ad-979d-eb9a85bdc7e5',1,'d',40,'2018-01-23 18:04:39','Ahmed Al-Adaileh','atm'),(21,'51f0f5ea-a501-4793-a367-055606a3d48a',1,'d',20,'2018-01-23 18:05:06','Ahmed Al-Adaileh','atm'),(22,'9357e530-121e-4560-890e-91d68fe6608f',1,'d',20,'2018-01-23 18:06:24','Ahmed Al-Adaileh','atm'),(23,'caa75d14-46d7-472b-8af9-a66d424f0093',1,'d',40,'2018-01-23 18:06:38','Ahmed Al-Adaileh','atm'),(24,'15241b6f-a4c7-4293-9365-9ecc749a0dcd',1,'d',520,'2018-01-23 18:06:52','Ahmed Al-Adaileh','atm'),(25,'652d3c15-cb56-41e1-b01b-3bc352b5539b',1,'d',520,'2018-01-23 18:15:45','Ahmed Al-Adaileh','atm'),(26,'a732c710-a7a0-4553-a4cf-cf0344633caa',1,'d',14,'2018-01-23 18:15:53','Ahmed Al-Adaileh','atm'),(27,'22889318-3d40-4288-9424-b0449a70192c',1,'d',14,'2018-01-23 18:21:02','Ahmed Al-Adaileh','atm'),(28,'3feb0b70-48ae-4d70-96b3-94ab1f5dd393',1,'w',20,'2018-01-23 18:22:18','Ahmed Al-Adaileh','atm'),(29,'ca04f191-2c45-4c98-9087-e9a928f2bbf1',1,'w',40,'2018-01-23 18:22:37','Ahmed Al-Adaileh','atm'),(30,'3cae6911-ee83-4301-a37b-11b79ce7119f',1,'w',60,'2018-01-23 18:22:39','Ahmed Al-Adaileh','atm'),(31,'7d253a3d-20fd-42f5-82dc-4c08752293dd',1,'w',25,'2018-01-23 18:22:46','Ahmed Al-Adaileh','atm'),(32,'aa26e0d5-f139-4d59-8b47-9c9f797ef079',1,'w',20,'2018-01-23 21:29:45','Ahmed Al-Adaileh','atm'),(33,'44b50070-f962-4500-bf5f-b7523105d014',1,'w',40,'2018-01-23 21:30:12','Ahmed Al-Adaileh','atm'),(34,'efe8d444-df55-4d3c-9c7b-a0e480f7d115',1,'w',60,'2018-01-23 21:30:14','Ahmed Al-Adaileh','atm'),(35,'9dafee54-fd1c-4c05-b02b-89c02cb8b812',1,'w',80,'2018-01-23 21:30:19','Ahmed Al-Adaileh','atm'),(36,'cca618eb-31ff-4fc0-b20a-c77b38bab6f8',1,'d',20,'2018-01-23 21:30:43','Ahmed Al-Adaileh','atm'),(37,'a066e702-58b1-4032-9dc4-aca551947f5a',1,'w',20,'2018-01-23 21:55:29','Ahmed Al-Adaileh','atm'),(38,'583daece-429d-4ad1-af96-13d8f5fd00d2',1,'d',20,'2018-01-23 21:57:02','Ahmed Al-Adaileh','atm'),(39,'0554a010-e8a5-4063-a9c5-4704d997bfc0',1,'d',60,'2018-01-23 21:57:17','Ahmed Al-Adaileh','atm'),(40,'b21ae63c-e7c1-4221-9530-6c0c52d11b2e',1,'d',60,'2018-01-23 21:57:38','Ahmed Al-Adaileh','atm'),(41,'e69c0269-4a79-4358-99bc-0142392943dd',1,'d',60,'2018-01-23 21:58:37','Ahmed Al-Adaileh','atm'),(42,'b1649fce-8e84-43f6-9dd2-d3e7721cf4f8',1,'d',5,'2018-01-23 21:58:45','Ahmed Al-Adaileh','atm'),(43,'97a1b787-5445-41de-b77d-6552b3b0d357',1,'w',6,'2018-01-23 21:59:06','Ahmed Al-Adaileh','atm'),(44,'57734b09-5ee9-4520-bdec-dde4592eecff',1,'null',1200,'2018-01-23 22:42:54','Yazeed Smith','null'),(45,'07518027-16d7-44af-985c-34d33ce145f0',1,'w',6,'2018-01-23 22:43:55','Ahmed Al-Adaileh','atm'),(46,'1c2d2f81-73c4-486c-836a-1c4c21871bc7',1,'w',6,'2018-01-23 23:20:49','Ahmed Al-Adaileh','atm'),(47,'17e1ef1f-e10a-499b-a048-81d6d10ae37a',1,'w',6,'2018-01-23 23:20:52','Ahmed Al-Adaileh','atm'),(48,'188af7a1-694e-46ac-af68-94ffe5a9c88f',1,'d',20,'2018-01-23 23:24:56','Ahmed Al-Adaileh','atm'),(49,'c625dfa8-7c0d-407e-9ef3-5e7be628b9a6',1,'d',111,'2018-01-23 23:25:07','Ahmed Al-Adaileh','atm'),(50,'5b76ca3e-d0ee-4b8d-8894-3143a89078c5',1,'w',1,'2018-01-23 23:25:46','Ahmed Al-Adaileh','atm'),(51,'980000d5-c91a-4807-9fa2-653357c1fc9c',1,'d',40,'2018-01-24 21:52:55','Ahmed Al-Adaileh','atm'),(52,'901b621c-7bfc-47d7-8b96-24f3e20c139a',1,'d',60,'2018-01-24 21:52:57','Ahmed Al-Adaileh','atm'),(53,'d13e2079-b140-4132-ada7-255344f683a4',1,'d',1,'2018-01-24 21:53:02','Ahmed Al-Adaileh','atm'),(54,'d9b21416-c932-4ced-8478-b363d24501e3',1,'w',60,'2018-01-24 21:53:44','Ahmed Al-Adaileh','atm'),(55,'8375fc23-c5fd-47bd-b15a-caa219930484',1,'w',2000,'2018-01-24 21:53:49','Ahmed Al-Adaileh','atm'),(56,'911d8855-fd7b-4732-97a1-41a14af7255e',1,'d',20000,'2018-01-24 21:54:11','Ahmed Al-Adaileh','atm'),(57,'1e82590c-b2d8-4cb4-a9ae-76c2b754ac6b',1,'w',500,'2018-01-25 20:39:15','uzuiolö','online-banking'),(58,'06569c5a-f741-4274-b880-8852dffd4d04',1,'null',1200,'2018-01-25 21:55:15','Yazeed Smith','null');
/*!40000 ALTER TABLE `abc_banking_group`.`account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `abc_banking_group`.`client`
--

DROP TABLE IF EXISTS `abc_banking_group`.`client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `abc_banking_group`.`client` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) DEFAULT NULL COMMENT 'online-banking, atm',
  `username_card_id` varchar(45) DEFAULT NULL COMMENT 'username for online banking. card_id for ATM',
  `password_pin` varchar(45) DEFAULT NULL COMMENT 'Password for online banking. Pin for ATM',
  `user_name` varchar(200) DEFAULT NULL,
  `user_address` varchar(250) DEFAULT NULL,
  `user_tel` varchar(45) DEFAULT NULL,
  `user_email` varchar(100) DEFAULT NULL,
  `last_logged_in` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='Holds clients data';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `abc_banking_group`.`client`
--

LOCK TABLES `abc_banking_group`.`client` WRITE;
/*!40000 ALTER TABLE `abc_banking_group`.`client` DISABLE KEYS */;
INSERT INTO `abc_banking_group`.`client` VALUES (1,'online-banking','aadaileh','pass','Ahmed Al-Adaileh','Dorstener Str. 113, 45657 Recklinghausen GERMANY','+4917666891078','ahmed.adaileh@gmail.com','2018-01-26 19:53:20'),(11,'atm','12345','123','Ahmed Al-Adaileh','Dorstener Str. 113, 45657 Recklinghausen GERMANY','+4917666891078','ahmed.adaileh@gmail.com','2018-01-26 09:36:52');
/*!40000 ALTER TABLE `abc_banking_group`.`client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `abc_banking_group`.`transfers`
--

DROP TABLE IF EXISTS `abc_banking_group`.`transfers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `abc_banking_group`.`transfers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `timestamp` timestamp NULL DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL,
  `beneficiary_full_name` varchar(250) DEFAULT NULL,
  `beneficiary_address` varchar(250) DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `bank_name` varchar(250) DEFAULT NULL,
  `branch` varchar(100) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `currency_type` varchar(45) DEFAULT NULL,
  `purpose` varchar(250) DEFAULT NULL,
  `transfer_on` timestamp NULL DEFAULT NULL,
  `notes` text,
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `abc_banking_group`.`transfers`
--

LOCK TABLES `abc_banking_group`.`transfers` WRITE;
/*!40000 ALTER TABLE `abc_banking_group`.`transfers` DISABLE KEYS */;
INSERT INTO `abc_banking_group`.`transfers` VALUES (1,'2018-01-22 23:01:22',1,'null','null','uk','LONDON','null','Kingston',1200,'null','null','2018-01-22 23:01:22','Please transfer it asap'),(2,'2018-01-22 23:07:40',1,'null','null','uk','LONDON','null','Kingston',1200,'null','null','2018-01-22 23:07:40','Please transfer it asap'),(3,'2018-01-22 23:08:50',1,'null','null','uk','LONDON','null','Kingston',1200,'null','null','2018-01-22 23:08:50','Please transfer it asap'),(4,'2018-01-22 23:09:12',1,'null','null','uk','LONDON','null','Kingston',1200,'null','null','2018-01-22 23:09:12','Please transfer it asap'),(5,'2018-01-22 23:21:01',1,'uiooiuzuioppoi','uzuiopoiuztzu','iopoiuz','uiopoi','uzuiop','oiuzu',150,'null',',hgfghjklkjhg','2018-01-22 23:21:01','ddhwudhwudhi'),(6,'2018-01-22 23:24:16',1,'jkj','khk','jj','kjj','kj','jhkjljkj',120,'null',',jh','2018-01-22 23:24:16','kljkjkhkj'),(7,'2018-01-22 23:24:50',1,'jkj','khk','jj','kjj','kj','jhkjljkj',120,'null',',jh','2018-01-22 23:24:50','kljkjkhkj'),(8,'2018-01-22 23:26:07',1,'jkj','khk','jj','kjj','kj','jhkjljkj',120,'null',',jh','2018-01-22 23:26:07','kljkjkhkj'),(9,'2018-01-23 13:22:37',1,'Abdullah Korashi','Cairo - Egypt','Egypt','Cairo','Al-Ettihad Bank','Cairo',400,'null','Family support','2018-01-23 13:22:37','Lorem ipsum dolor amet lorem ipsum dolor amet  lorem ipsum dolor amet  lorem ipsum dolor amet  lorem ipsum dolor amet  lorem ipsum dolor amet '),(10,'2018-01-23 13:26:25',1,'Dirk Klaus','Remscheid Germany','Germany','Remscheid','Stadt sparkasse','Remscheid',390,'GBP','Family support','2018-01-23 13:26:25','Lorem ipsum dolor amet lorem ipsum dolor amet  lorem ipsum dolor amet  lorem ipsum dolor amet  lorem ipsum dolor amet  lorem ipsum dolor amet '),(11,'2018-01-23 22:42:54',1,'Yazeed Smith','Hohenhaganer Str. 4, 42855 Remscheid Germany','uk','LONDON','CBC Bank','Kingston',1200,'null','Accomodation rent','2018-01-23 22:42:54','Please transfer it asap'),(12,'2018-01-25 20:39:14',1,'uzuiolö','juzu','iklöljuhz','uilökjh','ul','uzuilooi',500,'GBP','jhujkk','2018-01-25 20:39:14','notes'),(13,'2018-01-25 21:55:15',1,'Yazeed Smith','Hohenhaganer Str. 4, 42855 Remscheid Germany','uk','LONDON','CBC Bank','Kingston',1200,'null','Accomodation rent','2018-01-25 21:55:15','Please transfer it asap');
/*!40000 ALTER TABLE `abc_banking_group`.`transfers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-26 20:58:44
