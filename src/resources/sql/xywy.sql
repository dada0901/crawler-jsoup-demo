/*
SQLyog Ultimate v11.27 (32 bit)
MySQL - 5.5.29 : Database - xywy
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`xywy` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `xywy`;

/*Table structure for table `bodypart` */

DROP TABLE IF EXISTS `bodypart`;

CREATE TABLE `bodypart` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(256) DEFAULT NULL,
  `HotIllnessRef` text COMMENT '热门疾病',
  `IllnessRef` text COMMENT '所有疾病',
  `HotSymptomRef` text COMMENT '热门症状',
  `SymptomRef` text COMMENT '所有症状',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Table structure for table `checkup` */

DROP TABLE IF EXISTS `checkup`;

CREATE TABLE `checkup` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `ParentId` int(11) DEFAULT NULL,
  `Name` varchar(2000) DEFAULT NULL,
  `Spell` varchar(2000) DEFAULT NULL,
  `TreePath` varchar(2000) DEFAULT NULL,
  `Remark` text COMMENT '检查介绍',
  `NormalRange` text COMMENT '正常值',
  `Lcyy` text COMMENT '临床意义',
  `Zysx` text COMMENT '注意事项',
  `Jcgc` text COMMENT '检察过程',
  `CheckupRef` text COMMENT '检查相关项',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3667 DEFAULT CHARSET=utf8;

/*Table structure for table `doctor` */

DROP TABLE IF EXISTS `doctor`;

CREATE TABLE `doctor` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(512) DEFAULT NULL COMMENT '医生姓名',
  `JobTitle` varchar(512) DEFAULT NULL COMMENT '职称',
  `HospitalName` varchar(512) DEFAULT NULL COMMENT '所在医院',
  `OfficeName` varchar(512) DEFAULT NULL COMMENT '所在科室',
  `GoodAt` text COMMENT '擅长',
  `CureIllness` text COMMENT '治疗疾病',
  PRIMARY KEY (`Id`),
  KEY `name_index` (`Name`(255),`HospitalName`(255),`OfficeName`(255))
) ENGINE=InnoDB AUTO_INCREMENT=106957 DEFAULT CHARSET=utf8;

/*Table structure for table `hospital` */

DROP TABLE IF EXISTS `hospital`;

CREATE TABLE `hospital` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(2000) DEFAULT NULL COMMENT '医院名称',
  `AreaInfo` varchar(512) DEFAULT NULL,
  `Remark` text COMMENT '医院概述',
  `Address` text COMMENT '地址',
  `AddressWay` text COMMENT '乘车路线',
  `Telephone` varchar(2000) DEFAULT NULL COMMENT '电话号码',
  `OfficeInfo` text COMMENT '科室信息',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `illness` */

DROP TABLE IF EXISTS `illness`;

CREATE TABLE `illness` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `IllCategoryId` int(11) DEFAULT NULL,
  `Name` varchar(2000) DEFAULT NULL,
  `Spell` varchar(512) DEFAULT NULL,
  `Alias` varchar(2000) DEFAULT NULL COMMENT '别名',
  `Remark` text,
  `Photo` mediumblob,
  `Reason` text,
  `Zzgs` text COMMENT '症状概述',
  `Food` text COMMENT '饮食',
  `Precaution` text COMMENT '预防',
  `Zlfa` text COMMENT '治疗方案',
  `CheckupRemark` text COMMENT '检查方案',
  `Zdjb` text COMMENT '诊断鉴别',
  `Bfz` text COMMENT '并发症',
  `CheckupRef` text,
  PRIMARY KEY (`Id`),
  KEY `name_index` (`Name`(255))
) ENGINE=InnoDB AUTO_INCREMENT=13823 DEFAULT CHARSET=utf8;

/*Table structure for table `medication` */

DROP TABLE IF EXISTS `medication`;

CREATE TABLE `medication` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(2000) DEFAULT NULL COMMENT '药品名',
  `Alias` varchar(2000) DEFAULT NULL COMMENT '通用名',
  `Spell` varchar(128) DEFAULT NULL COMMENT '通用名首字母',
  `Cffcf` varchar(128) DEFAULT NULL COMMENT '处方药非处方药',
  `Zyxy` varchar(128) DEFAULT NULL COMMENT '中药西药',
  `Ckjg` varchar(128) DEFAULT NULL COMMENT '参考价格',
  `Sccj` varchar(2000) DEFAULT NULL COMMENT '生产厂家',
  `Gnzz` text COMMENT '功能主治',
  `Zycf` text COMMENT '主要成分',
  `Bzgg` text COMMENT '包装规格',
  `Yfyl` text COMMENT '用法用量',
  `Pzwh` text COMMENT '批准文号',
  `Photo` mediumblob,
  `Zzjb` text COMMENT '主治疾病',
  PRIMARY KEY (`Id`),
  KEY `Id` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=216723 DEFAULT CHARSET=utf8;

/*Table structure for table `medicine` */

DROP TABLE IF EXISTS `medicine`;

CREATE TABLE `medicine` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(2000) DEFAULT NULL COMMENT '药品名',
  `alias` varchar(2000) DEFAULT NULL COMMENT '通用名',
  `spell` varchar(128) DEFAULT NULL COMMENT '通用名首字母',
  `cffcf` varchar(128) DEFAULT NULL COMMENT '处方药非处方药',
  `zyxy` varchar(128) DEFAULT NULL COMMENT '中药西药',
  `ckjg` varchar(128) DEFAULT NULL COMMENT '参考价格',
  `sccj` varchar(2000) DEFAULT NULL COMMENT '生产厂家',
  `gnzz` text COMMENT '功能主治',
  `zycf` text COMMENT '主要成分',
  `bzgg` text COMMENT '包装规格',
  `yfyl` text COMMENT '用法用量',
  `pzwh` text COMMENT '批准文号',
  `photo` mediumblob,
  `zzjb` text COMMENT '主治疾病',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=216723 DEFAULT CHARSET=utf8;

/*Table structure for table `people` */

DROP TABLE IF EXISTS `people`;

CREATE TABLE `people` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(128) DEFAULT NULL,
  `IllnessRef` text,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

/*Table structure for table `specialty` */

DROP TABLE IF EXISTS `specialty`;

CREATE TABLE `specialty` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `ParentId` int(11) DEFAULT NULL COMMENT '父节点id',
  `Name` varchar(512) DEFAULT NULL COMMENT '专科名',
  `TreePath` varchar(512) DEFAULT NULL,
  `HotIllnessRef` text COMMENT '热门疾病',
  `IllnessRef` text COMMENT '专科关联疾病',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=133 DEFAULT CHARSET=utf8;

/*Table structure for table `symptom` */

DROP TABLE IF EXISTS `symptom`;

CREATE TABLE `symptom` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(2000) DEFAULT NULL COMMENT '名称',
  `Spell` varchar(2000) DEFAULT NULL COMMENT '拼音首字母',
  `Remark` text COMMENT '症状概述',
  `Reason` text COMMENT '原因',
  `CheckupRemark` text COMMENT '检查概述',
  `Jbzd` text COMMENT '鉴别诊断概述',
  `Hjff` text COMMENT '缓解方法',
  `BodyRef` varchar(2000) DEFAULT NULL COMMENT '患病部位',
  `SpecialtyRef` varchar(2000) DEFAULT NULL COMMENT '所属科室',
  `IllnessRef` text COMMENT '相关疾病',
  `CheckupRef` text COMMENT '相关检查',
  `SymptomRef` text COMMENT '相关症状',
  `Photo` mediumblob,
  PRIMARY KEY (`Id`),
  KEY `name_index` (`Name`(255))
) ENGINE=InnoDB AUTO_INCREMENT=6320 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
