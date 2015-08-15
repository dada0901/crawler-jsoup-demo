/*
SQLyog Ultimate v11.27 (32 bit)
MySQL - 5.5.29 : Database - ypk39
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ypk39` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `ypk39`;

/*Table structure for table `medication` */

DROP TABLE IF EXISTS `medication`;

CREATE TABLE `medication` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Category` varchar(2000) DEFAULT NULL COMMENT '分类',
  `Origin` varchar(128) DEFAULT NULL COMMENT '产地',
  `Name` varchar(512) DEFAULT NULL COMMENT '药品名称',
  `FullName` varchar(2000) DEFAULT NULL COMMENT '全名',
  `Prescription` varchar(128) DEFAULT NULL COMMENT '处方',
  `Composition` text COMMENT '成分',
  `Syz` text COMMENT '适应症',
  `Yfyl` text COMMENT '用法用量',
  `Blfy` text COMMENT '不良反应',
  `Ban` text COMMENT '禁忌',
  `Zysx` text COMMENT '注意事项',
  `Ywhxzy` text COMMENT '药物互相作用',
  `ValidTime` varchar(512) DEFAULT NULL COMMENT '有效期',
  `Ylzy` text COMMENT '药理作用',
  `Storage` text COMMENT '贮藏',
  `ApproveNumber` varchar(2000) DEFAULT NULL COMMENT '批准文号',
  `Manufacturer` text COMMENT '生产企业',
  `Formulation` varchar(512) DEFAULT NULL COMMENT '剂型',
  `Specification` text COMMENT '规格',
  `Barcode` varchar(128) DEFAULT NULL COMMENT '条码',
  `Photo` mediumblob COMMENT '药品图片',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=151 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
