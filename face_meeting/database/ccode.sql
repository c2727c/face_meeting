/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.24-log : Database - ccode
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ccode` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `ccode`;

/*Table structure for table `group` */

DROP TABLE IF EXISTS `group`;

CREATE TABLE `group` (
  `gId` CHAR(20) NOT NULL,
  `gInfo` VARCHAR(64) DEFAULT NULL,
  PRIMARY KEY (`gId`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `group` */

INSERT  INTO `group`(`gId`,`gInfo`) VALUES ('项目A组','特别特别特比重要的项目');

/*Table structure for table `meeting` */

DROP TABLE IF EXISTS `meeting`;

CREATE TABLE `meeting` (
  `mNo` INT(6) NOT NULL AUTO_INCREMENT,
  `mTitle` VARCHAR(64) NOT NULL,
  `mInfo` VARCHAR(64) NOT NULL DEFAULT '--',
  `mSize` INT(4) NOT NULL DEFAULT '20',
  `mSpan` INT(4) NOT NULL DEFAULT '45',
  `tName` VARCHAR(20) NOT NULL DEFAULT 'default',
  `pId_FQ` CHAR(6) NOT NULL,
  `mState` ENUM('pass','failed','unknown') DEFAULT 'unknown',
  `mCreateTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`mNo`),
  KEY `fk_M_pid1` (`pId_FQ`),
  KEY `fk_M_tName` (`tName`),
  CONSTRAINT `fk_M_pid1` FOREIGN KEY (`pId_FQ`) REFERENCES `person` (`pId`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_M_tName` FOREIGN KEY (`tName`) REFERENCES `room_type` (`tName`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=INNODB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `meeting` */

INSERT  INTO `meeting`(`mNo`,`mTitle`,`mInfo`,`mSize`,`mSpan`,`tName`,`pId_FQ`,`mState`,`mCreateTime`) VALUES (2,'test Meeting7','--',30,60,'default','t15','pass','2019-02-26 22:50:55');

/*Table structure for table `mr_event` */

DROP TABLE IF EXISTS `mr_event`;

CREATE TABLE `mr_event` (
  `mNo` INT(6) NOT NULL,
  `rId` CHAR(6) NOT NULL,
  `startDate` DATE NOT NULL,
  `startTime` TIME NOT NULL,
  `endTime` TIME NOT NULL,
  PRIMARY KEY (`mNo`,`rId`),
  KEY `fk_MR_rid` (`rId`),
  CONSTRAINT `fk_MR_mno` FOREIGN KEY (`mNo`) REFERENCES `meeting` (`mNo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_MR_rid` FOREIGN KEY (`rId`) REFERENCES `room` (`rId`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `mr_event` */

INSERT  INTO `mr_event`(`mNo`,`rId`,`startDate`,`startTime`,`endTime`) VALUES (2,'CR301','2019-01-20','09:00:00','09:45:00');

/*Table structure for table `person` */

DROP TABLE IF EXISTS `person`;

CREATE TABLE `person` (
  `pId` CHAR(6) NOT NULL,
  `pPass` CHAR(16) NOT NULL,
  `pName` VARCHAR(10) DEFAULT NULL,
  `pGender` CHAR(2) DEFAULT NULL,
  `pRole` VARCHAR(10) DEFAULT NULL,
  `pDept` VARCHAR(10) DEFAULT NULL,
  `pPrivilege` TINYINT(4) NOT NULL DEFAULT '0',
  `pFace` BLOB,
  `pIcon` BLOB,
  `pTel` CHAR(16) DEFAULT NULL,
  PRIMARY KEY (`pId`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `person` */

INSERT  INTO `person`(`pId`,`pPass`,`pName`,`pGender`,`pRole`,`pDept`,`pPrivilege`,`pFace`,`pIcon`,`pTel`) VALUES ('t15','123456','测试t15',NULL,NULL,NULL,-1,'testFeature','icon',NULL),('t37','123456','测试t37',NULL,NULL,NULL,-1,'testFeature','icon',NULL),('t38','123456','测试t38',NULL,NULL,'ggb',-1,'testFeature','icon',NULL),('t40','123456','测试t40',NULL,NULL,NULL,-1,'testFeature','icon',NULL),('t57','123456','测试t57',NULL,NULL,NULL,-1,'testFeature','icon',NULL),('t58','123456','测试t58',NULL,NULL,NULL,-1,'testFeature','icon',NULL),('t68','123456','测试t68',NULL,NULL,NULL,-1,'testFeature','icon',NULL),('t69','123456','测试t69',NULL,NULL,NULL,-1,'testFeature','icon',NULL),('t80','123456','测试t80',NULL,NULL,NULL,-1,'testFeature','icon',NULL),('t82','123456','测试t82',NULL,NULL,NULL,-1,'testFeature','icon',NULL),('t83','123456','测试t83',NULL,NULL,'ggb',-1,'testFeature','icon',NULL),('t91','123456','测试t91',NULL,NULL,'ggb',-1,'testFeature','icon',NULL);

/*Table structure for table `pg_join` */

DROP TABLE IF EXISTS `pg_join`;

CREATE TABLE `pg_join` (
  `pId` CHAR(6) NOT NULL,
  `gId` CHAR(20) NOT NULL,
  PRIMARY KEY (`pId`,`gId`),
  KEY `fk_pg_gId` (`gId`),
  CONSTRAINT `fk_pg_gId` FOREIGN KEY (`gId`) REFERENCES `group` (`gId`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_pg_pId` FOREIGN KEY (`pId`) REFERENCES `person` (`pId`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `pg_join` */

INSERT  INTO `pg_join`(`pId`,`gId`) VALUES ('t15','项目A组');

/*Table structure for table `pm_attend` */

DROP TABLE IF EXISTS `pm_attend`;

CREATE TABLE `pm_attend` (
  `pId` CHAR(6) NOT NULL,
  `mNo` INT(6) NOT NULL,
  `state` ENUM('canceled','checkedin','noshow') NOT NULL DEFAULT 'noshow',
  PRIMARY KEY (`pId`,`mNo`),
  KEY `fk_PMa_mno` (`mNo`),
  CONSTRAINT `fk_PMa_mno` FOREIGN KEY (`mNo`) REFERENCES `meeting` (`mNo`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_PMa_pid` FOREIGN KEY (`pId`) REFERENCES `person` (`pId`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `pm_attend` */

INSERT  INTO `pm_attend`(`pId`,`mNo`,`state`) VALUES ('t15',2,'noshow');

/*Table structure for table `pm_inform` */

DROP TABLE IF EXISTS `pm_inform`;

CREATE TABLE `pm_inform` (
  `pId` CHAR(6) NOT NULL,
  `mNo` INT(6) NOT NULL,
  PRIMARY KEY (`pId`,`mNo`),
  KEY `fk_PMi_mno` (`mNo`),
  CONSTRAINT `fk_PMi_mno` FOREIGN KEY (`mNo`) REFERENCES `meeting` (`mNo`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_PMi_pid` FOREIGN KEY (`pId`) REFERENCES `person` (`pId`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `pm_inform` */

/*Table structure for table `room` */

DROP TABLE IF EXISTS `room`;

CREATE TABLE `room` (
  `rId` CHAR(6) NOT NULL,
  `rName` VARCHAR(10) DEFAULT NULL,
  `rInfo` VARCHAR(10) DEFAULT NULL,
  `rSize` INT(4) NOT NULL,
  `rAddr` VARCHAR(10) DEFAULT NULL,
  `tName` VARCHAR(20) NOT NULL DEFAULT 'default',
  PRIMARY KEY (`rId`),
  KEY `fk_R_tName` (`tName`),
  CONSTRAINT `fk_R_tName` FOREIGN KEY (`tName`) REFERENCES `room_type` (`tName`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `room` */

INSERT  INTO `room`(`rId`,`rName`,`rInfo`,`rSize`,`rAddr`,`tName`) VALUES ('A001','UPDATE','TEST',30,'3Floor','default'),('CR301',NULL,NULL,30,'3层','default'),('CR302',NULL,NULL,30,'3层','A'),('CR303',NULL,NULL,40,'3层','default'),('CR304',NULL,NULL,40,'3层','default'),('CR305',NULL,NULL,40,'3层','default'),('CR306',NULL,NULL,20,'3层','A'),('CR401',NULL,NULL,20,'4层','A'),('CR402',NULL,NULL,20,'4层','A'),('CR403',NULL,NULL,15,'4层','A');

/*Table structure for table `room_type` */

DROP TABLE IF EXISTS `room_type`;

CREATE TABLE `room_type` (
  `tName` VARCHAR(20) NOT NULL,
  `tInfo` VARCHAR(64) DEFAULT NULL,
  PRIMARY KEY (`tName`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `room_type` */

INSERT  INTO `room_type`(`tName`,`tInfo`) VALUES ('A','top class'),('default','默认类型');

/*Table structure for table `time_table` */

DROP TABLE IF EXISTS `time_table`;

CREATE TABLE `time_table` (
  `count_point` TIME NOT NULL,
  PRIMARY KEY (`count_point`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `time_table` */

INSERT  INTO `time_table`(`count_point`) VALUES ('14:00:00'),('15:00:00'),('16:00:00'),('17:00:00');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;