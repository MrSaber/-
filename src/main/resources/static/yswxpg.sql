-- MySQL dump 10.13  Distrib 5.7.18, for Linux (x86_64)
--
-- Host: localhost    Database: yswxpg
-- ------------------------------------------------------
-- Server version	5.7.18-1

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
-- Table structure for table `wx_bid`
--

DROP TABLE IF EXISTS `wx_bid`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wx_bid` (
  `bid_id` varchar(45) NOT NULL,
  `bid_flow_id` varchar(45) DEFAULT NULL,
  `bid_user_id` int(11) DEFAULT NULL,
  `bid_timeprice` double DEFAULT NULL,
  `bid_total` double DEFAULT NULL,
  `bid_status` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`bid_id`),
  KEY `fk_wx_bid_1_idx` (`bid_user_id`),
  KEY `fk_wx_bid_2_idx` (`bid_flow_id`),
  CONSTRAINT `fk_wx_bid_1` FOREIGN KEY (`bid_user_id`) REFERENCES `wx_user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_wx_bid_2` FOREIGN KEY (`bid_flow_id`) REFERENCES `wx_flow` (`flow_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wx_bid`
--

LOCK TABLES `wx_bid` WRITE;
/*!40000 ALTER TABLE `wx_bid` DISABLE KEYS */;
INSERT INTO `wx_bid` VALUES ('1','123456',1,100,200,0),('BID201802041517729395','123456',1,100,100,0),('BID201802041517731746','WX201802041517731402',1,1500,100,0),('BID201802041517736528','WX201802041517736352',1,1600,100,0),('BID201802041517738126','WX201802041517736352',2,1600,100,1),('BID201802041517738353','WX201802041517736352',3,1700,100,0);
/*!40000 ALTER TABLE `wx_bid` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wx_fault`
--

DROP TABLE IF EXISTS `wx_fault`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wx_fault` (
  `fault_id` varchar(25) NOT NULL,
  `fault_info` varchar(45) DEFAULT NULL,
  `fault_context` varchar(255) DEFAULT NULL,
  `fault_flow_id` varchar(45) NOT NULL,
  PRIMARY KEY (`fault_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wx_fault`
--

LOCK TABLES `wx_fault` WRITE;
/*!40000 ALTER TABLE `wx_fault` DISABLE KEYS */;
INSERT INTO `wx_fault` VALUES ('TASK201801311517395091','器材损坏','器材损坏具体信息',''),('TASK201801311517396098','guzhang','guzhanginfo',''),('TASK201802021517553492','12311','12311','WX201802021517553490'),('TASK201802021517581041','1033_1','1033-1','WX201802021517581033'),('TASK201802021517581300','1295_1','123456789','WX201802021517581295'),('TASK201802021517581320','1295-2','123456789','WX201802021517581295'),('TASK201802041517731046','器材损坏','桌椅板凳损坏','WX201802041517731043'),('TASK201802041517731086','电力故障','电力维修故障','WX201802041517731043'),('TASK201802041517731218','器材损坏','桌椅板凳损坏','WX201802041517731213'),('TASK201802041517731425','故障','桌椅损坏','WX201802041517731402'),('TASK201802041517736361','故障1','info','WX201802041517736352'),('TASK201802051517814823','故障1','椅子损坏','WX201802051517814818'),('TASK201802051517814875','故障2','哈哈哈','WX201802051517814818'),('TASK201802161518761823','166401','食堂卫生','WX201802161518761664'),('TASK201802161518761846','16602','食堂器材','WX201802161518761664');
/*!40000 ALTER TABLE `wx_fault` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wx_flow`
--

DROP TABLE IF EXISTS `wx_flow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wx_flow` (
  `flow_id` varchar(25) NOT NULL,
  `flow_date` date NOT NULL,
  `flow_status` tinyint(4) NOT NULL,
  `flow_whyfail` varchar(255) DEFAULT NULL,
  `flow_log` text,
  `flow_remark` varchar(255) DEFAULT NULL,
  `flow_score` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`flow_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wx_flow`
--

LOCK TABLES `wx_flow` WRITE;
/*!40000 ALTER TABLE `wx_flow` DISABLE KEYS */;
INSERT INTO `wx_flow` VALUES ('123456','2018-01-31',12,'不合理',NULL,NULL,NULL),('WX201802021517581295','2018-02-02',3,NULL,NULL,NULL,NULL),('WX201802041517731213','2018-02-04',1,NULL,NULL,NULL,NULL),('WX201802041517731402','2018-02-04',3,NULL,NULL,NULL,NULL),('WX201802041517736352','2018-02-04',5,NULL,NULL,NULL,NULL),('WX201802041517736392','2018-02-04',1,NULL,NULL,NULL,NULL),('WX201802051517814818','2018-02-05',3,NULL,NULL,NULL,NULL),('WX201802051517814904','2018-02-05',1,NULL,NULL,NULL,NULL),('WX201802051517814909','2018-02-05',1,NULL,NULL,NULL,NULL),('WX201802161518761664','2018-02-16',14,NULL,NULL,'任务完成的非常好！',5);
/*!40000 ALTER TABLE `wx_flow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wx_image`
--

DROP TABLE IF EXISTS `wx_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wx_image` (
  `image_id` int(11) NOT NULL AUTO_INCREMENT,
  `image_path` varchar(255) NOT NULL,
  `image_fault_id` varchar(25) NOT NULL,
  PRIMARY KEY (`image_id`),
  KEY `fk_wx_image_1_idx` (`image_fault_id`),
  CONSTRAINT `fk_wx_image_1` FOREIGN KEY (`image_fault_id`) REFERENCES `wx_fault` (`fault_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wx_image`
--

LOCK TABLES `wx_image` WRITE;
/*!40000 ALTER TABLE `wx_image` DISABLE KEYS */;
INSERT INTO `wx_image` VALUES (32,'/home/mrsaber/tmp/TASK201801311517395091118643.jpg','TASK201801311517395091'),(33,'/home/mrsaber/tmp/TASK201801311517395091118643.jpg','TASK201801311517395091'),(34,'/home/mrsaber/tmp/TASK201801311517396098144350.jpg','TASK201801311517396098'),(37,'/home/mrsaber/tmp/TASK201802021517553492511207.jpg','TASK201802021517553492'),(38,'/home/mrsaber/tmp/TASK201802021517553492511215.jpg','TASK201802021517553492'),(39,'/home/mrsaber/tmp/TASK201802021517581300313644.jpg','TASK201802021517581300'),(40,'/home/mrsaber/tmp/TASK201802021517581320333026.jpg','TASK201802021517581320'),(41,'/home/mrsaber/tmp/TASK201802041517731046080979.jpg','TASK201802041517731046'),(42,'/home/mrsaber/tmp/TASK201802041517731086107245.jpg','TASK201802041517731086'),(43,'/home/mrsaber/tmp/TASK201802041517731218237972.jpg','TASK201802041517731218'),(44,'/home/mrsaber/tmp/TASK201802041517731425437380.jpg','TASK201802041517731425'),(45,'/home/mrsaber/tmp/TASK201802041517736361386770.jpg','TASK201802041517736361'),(46,'/home/mrsaber/tmp/TASK201802051517814823872403.jpg','TASK201802051517814823'),(47,'/home/mrsaber/tmp/TASK201802051517814875892040.jpg','TASK201802051517814875'),(48,'/home/mrsaber/tmp/TASK201802161518761823841529.jpg','TASK201802161518761823');
/*!40000 ALTER TABLE `wx_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wx_material`
--

DROP TABLE IF EXISTS `wx_material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wx_material` (
  `cl_id` int(11) NOT NULL AUTO_INCREMENT,
  `cl_offer_id` int(11) DEFAULT NULL,
  `cl_type` varchar(45) DEFAULT NULL,
  `cl_number` int(11) DEFAULT NULL,
  `ci_price` double DEFAULT NULL,
  PRIMARY KEY (`cl_id`),
  KEY `fk_wx_material_1_idx` (`cl_offer_id`),
  CONSTRAINT `fk_wx_material_1` FOREIGN KEY (`cl_offer_id`) REFERENCES `wx_offer` (`offer_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wx_material`
--

LOCK TABLES `wx_material` WRITE;
/*!40000 ALTER TABLE `wx_material` DISABLE KEYS */;
/*!40000 ALTER TABLE `wx_material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wx_offer`
--

DROP TABLE IF EXISTS `wx_offer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wx_offer` (
  `offer_id` int(11) NOT NULL,
  `offer_time` date DEFAULT NULL,
  `offer_user_id` int(11) DEFAULT NULL,
  `offer_flow_id` varchar(45) DEFAULT NULL,
  `offer_status` tinyint(4) DEFAULT NULL,
  `offer_timeprice` double DEFAULT NULL,
  `offer_other` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`offer_id`),
  KEY `fk_wx_offer_1_idx` (`offer_flow_id`),
  KEY `fk_wx_offer_2_idx` (`offer_user_id`),
  CONSTRAINT `fk_wx_offer_1` FOREIGN KEY (`offer_flow_id`) REFERENCES `wx_flow` (`flow_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_wx_offer_2` FOREIGN KEY (`offer_user_id`) REFERENCES `wx_user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wx_offer`
--

LOCK TABLES `wx_offer` WRITE;
/*!40000 ALTER TABLE `wx_offer` DISABLE KEYS */;
/*!40000 ALTER TABLE `wx_offer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wx_stuff`
--

DROP TABLE IF EXISTS `wx_stuff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wx_stuff` (
  `stuff_id` int(11) NOT NULL AUTO_INCREMENT,
  `stuff_bid_id` varchar(45) NOT NULL,
  `stuff_name` varchar(45) DEFAULT NULL,
  `stuff_number` int(11) DEFAULT NULL,
  `stuff_price` double DEFAULT NULL,
  PRIMARY KEY (`stuff_id`),
  KEY `fk_wx_stuff_1_idx` (`stuff_bid_id`),
  CONSTRAINT `fk_wx_stuff_1` FOREIGN KEY (`stuff_bid_id`) REFERENCES `wx_bid` (`bid_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wx_stuff`
--

LOCK TABLES `wx_stuff` WRITE;
/*!40000 ALTER TABLE `wx_stuff` DISABLE KEYS */;
INSERT INTO `wx_stuff` VALUES (1,'BID201802041517729395','石料1',10,100),(2,'BID201802041517731746','板凳',5,1000),(3,'BID201802041517731746','石料',1,1000),(4,'BID201802041517736528','木块',1,100),(5,'BID201802041517736528','石料',1,100),(6,'BID201802041517738126','椅子',10,150),(7,'BID201802041517738353','椅子',1,100);
/*!40000 ALTER TABLE `wx_stuff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wx_user`
--

DROP TABLE IF EXISTS `wx_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wx_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) NOT NULL,
  `user_password` varchar(45) NOT NULL,
  `user_type` int(11) NOT NULL DEFAULT '1',
  `user_realname` varchar(45) DEFAULT NULL,
  `user_office` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wx_user`
--

LOCK TABLES `wx_user` WRITE;
/*!40000 ALTER TABLE `wx_user` DISABLE KEYS */;
INSERT INTO `wx_user` VALUES (1,'stgly','123456',3,'张三','南苑'),(2,'wxdw1','123456',10,'王某','维修单位1'),(3,'wxdw2','123456',10,'张某','维修单位2'),(4,'fgld','123456',5,'分管领导','部门'),(5,'zr','123456',7,'主任','处长'),(6,'gzry','123456',1,'张三','工作人员');
/*!40000 ALTER TABLE `wx_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-17  9:49:58
