/*
SQLyog Ultimate v11.27 (32 bit)
MySQL - 5.5.29 : Database - health99
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`health99` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `health99`;

/*Table structure for table `hospital` */

DROP TABLE IF EXISTS `hospital`;

CREATE TABLE `hospital` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(2000) DEFAULT NULL COMMENT '名称',
  `Alias` varchar(2000) DEFAULT NULL COMMENT '别名',
  `CityId` varchar(128) DEFAULT NULL,
  `City` varchar(512) DEFAULT NULL COMMENT '城市',
  `District` varchar(512) DEFAULT NULL COMMENT '城区',
  `Property` varchar(2000) DEFAULT NULL COMMENT '性质',
  `Remark` text COMMENT '简介',
  `Level` varchar(2000) DEFAULT NULL COMMENT '医院等级',
  `Telephone` varchar(512) DEFAULT NULL COMMENT '联系电话',
  `Address` varchar(2000) DEFAULT NULL COMMENT '地址',
  `OfficeList` text COMMENT '科室',
  `Website` varchar(500) DEFAULT NULL COMMENT '网址',
  `PostCode` varchar(128) DEFAULT NULL COMMENT '邮编',
  `Photo` mediumblob COMMENT '医院图片',
  `BusInfo` text COMMENT '乘车路线',
  `Medicare` varchar(128) DEFAULT NULL COMMENT '是否医保',
  PRIMARY KEY (`Id`),
  KEY `name_index` (`Name`(255))
) ENGINE=InnoDB AUTO_INCREMENT=24282 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
