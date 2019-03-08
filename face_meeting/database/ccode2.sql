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
  `gId` char(20) NOT NULL,
  `gInfo` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`gId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `group` */

insert  into `group`(`gId`,`gInfo`) values ('é¡¹ç›®Aç»„','ç‰¹åˆ«ç‰¹åˆ«ç‰¹æ¯”é‡è¦çš„é¡¹ç›®');

/*Table structure for table `meeting` */

DROP TABLE IF EXISTS `meeting`;

CREATE TABLE `meeting` (
  `mNo` int(6) NOT NULL AUTO_INCREMENT,
  `mTitle` varchar(64) NOT NULL,
  `mInfo` varchar(64) NOT NULL DEFAULT '--',
  `mSize` int(4) NOT NULL DEFAULT '20',
  `mSpan` int(4) NOT NULL DEFAULT '45',
  `tName` varchar(20) DEFAULT 'default',
  `pId_FQ` char(6) DEFAULT NULL,
  `mState` enum('pass','failed','unknown') DEFAULT 'unknown',
  `mCreateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`mNo`),
  KEY `fk_M_pid1` (`pId_FQ`),
  KEY `fk_M_tName` (`tName`),
  CONSTRAINT `fk_M_pid1` FOREIGN KEY (`pId_FQ`) REFERENCES `person` (`pId`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_M_tName` FOREIGN KEY (`tName`) REFERENCES `room_type` (`tName`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `meeting` */

insert  into `meeting`(`mNo`,`mTitle`,`mInfo`,`mSize`,`mSpan`,`tName`,`pId_FQ`,`mState`,`mCreateTime`) values (2,'test Meeting7','å•Šå•Šå•Šå•Šå•Šå•Šå•Šå•Šå•Šå•Šå•Šå•Šå•Šå•Šå•Š',30,60,'default','t15','pass','2019-02-26 22:50:55'),(3,'test Meeting8','--',30,60,'default','t15','pass','2019-02-26 22:50:55'),(4,'qqq','qqqqqqqqqqqqqqqqqqq',20,45,'default','t37','pass','2019-03-05 12:38:10');

/*Table structure for table `message` */

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `messageId` char(36) NOT NULL,
  `owner` char(6) NOT NULL,
  `sender` char(6) NOT NULL,
  `receiver` char(6) DEFAULT NULL,
  `mNo` int(20) DEFAULT NULL,
  `title` char(20) NOT NULL DEFAULT '--',
  `content` varchar(128) NOT NULL DEFAULT '--',
  `sendTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `type` char(10) NOT NULL DEFAULT 'private',
  `isRead` tinyint(1) NOT NULL DEFAULT '0',
  `state` char(20) DEFAULT 'none',
  PRIMARY KEY (`messageId`,`owner`),
  KEY `fk_mNo` (`mNo`),
  KEY `fk_owner` (`owner`),
  KEY `fk_sender` (`sender`),
  KEY `fk_receiver` (`receiver`),
  CONSTRAINT `fk_mNo` FOREIGN KEY (`mNo`) REFERENCES `meeting` (`mNo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_owner` FOREIGN KEY (`owner`) REFERENCES `person` (`pId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_receiver` FOREIGN KEY (`receiver`) REFERENCES `person` (`pId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_sender` FOREIGN KEY (`sender`) REFERENCES `person` (`pId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `message` */

insert  into `message`(`messageId`,`owner`,`sender`,`receiver`,`mNo`,`title`,`content`,`sendTime`,`type`,`isRead`,`state`) values ('0debe937-8858-418c-b78b-2fcf19033780','t15','t15',NULL,2,'ä¼šè®®å…¬å‘Š','xxxxxxxxxxx','2019-03-03 23:55:24','meeting',0,'none'),('0debe937-8858-418c-b78b-2fcf19033780','t37','t15','t37',2,'ä¼šè®®å…¬å‘Š','xxxxxxxxxxx','2019-03-03 23:55:24','meeting',0,'none'),('0debe937-8858-418c-b78b-2fcf19033780','t38','t15','t38',2,'ä¼šè®®å…¬å‘Š','xxxxxxxxxxx','2019-03-03 23:55:24','meeting',0,'none'),('0debe937-8858-418c-b78b-2fcf19033780','t40','t15','t40',2,'ä¼šè®®å…¬å‘Š','xxxxxxxxxxx','2019-03-03 23:55:24','meeting',0,'none'),('1d8a2f0b-e421-49cc-94bd-da49b06386d5','t15','t15',NULL,2,'ä¼šè®®å…¬å‘Š','xxxxxxxxxxx','2019-03-03 23:37:04','meeting',0,'none'),('1d8a2f0b-e421-49cc-94bd-da49b06386d5','t37','t15','t37',2,'ä¼šè®®å…¬å‘Š','xxxxxxxxxxx','2019-03-03 23:37:04','meeting',0,'none'),('1d8a2f0b-e421-49cc-94bd-da49b06386d5','t38','t15','t38',2,'ä¼šè®®å…¬å‘Š','xxxxxxxxxxx','2019-03-03 23:37:04','meeting',0,'none'),('1d8a2f0b-e421-49cc-94bd-da49b06386d5','t40','t15','t40',2,'ä¼šè®®å…¬å‘Š','xxxxxxxxxxx','2019-03-03 23:37:04','meeting',0,'none'),('26171435-3163-4864-a890-ba3c3ef984c5','t15','t15',NULL,2,'ä¼šè®®å…¬å‘Š','xxxxxxxxxxx','2019-03-03 23:46:14','meeting',0,'none'),('26171435-3163-4864-a890-ba3c3ef984c5','t37','t15','t37',2,'ä¼šè®®å…¬å‘Š','xxxxxxxxxxx','2019-03-03 23:46:14','meeting',0,'none'),('26171435-3163-4864-a890-ba3c3ef984c5','t38','t15','t38',2,'ä¼šè®®å…¬å‘Š','xxxxxxxxxxx','2019-03-03 23:46:14','meeting',0,'none'),('26171435-3163-4864-a890-ba3c3ef984c5','t40','t15','t40',2,'ä¼šè®®å…¬å‘Š','xxxxxxxxxxx','2019-03-03 23:46:14','meeting',0,'none'),('27e5dc9f-8f90-45ba-b8ca-828e04f7a72e','t15','t15',NULL,2,'ä¼šè®®å…¬å‘Š','xxxxxxxxxxx','2019-03-03 23:37:05','meeting',0,'none'),('27e5dc9f-8f90-45ba-b8ca-828e04f7a72e','t37','t15','t37',2,'ä¼šè®®å…¬å‘Š','xxxxxxxxxxx','2019-03-03 23:37:05','meeting',0,'none'),('27e5dc9f-8f90-45ba-b8ca-828e04f7a72e','t38','t15','t38',2,'ä¼šè®®å…¬å‘Š','xxxxxxxxxxx','2019-03-03 23:37:05','meeting',0,'none'),('27e5dc9f-8f90-45ba-b8ca-828e04f7a72e','t40','t15','t40',2,'ä¼šè®®å…¬å‘Š','xxxxxxxxxxx','2019-03-03 23:37:05','meeting',0,'none'),('4dd364ef-ec19-4719-b144-2bc6b85f42e2','t15','t15',NULL,2,'ä¼šè®®å…¬å‘Š','xxxxxxxxxxx','2019-03-03 23:40:05','meeting',0,'none'),('4dd364ef-ec19-4719-b144-2bc6b85f42e2','t37','t15','t37',2,'ä¼šè®®å…¬å‘Š','xxxxxxxxxxx','2019-03-03 23:40:05','meeting',0,'none'),('4dd364ef-ec19-4719-b144-2bc6b85f42e2','t38','t15','t38',2,'ä¼šè®®å…¬å‘Š','xxxxxxxxxxx','2019-03-03 23:40:05','meeting',0,'none'),('4dd364ef-ec19-4719-b144-2bc6b85f42e2','t40','t15','t40',2,'ä¼šè®®å…¬å‘Š','xxxxxxxxxxx','2019-03-03 23:40:05','meeting',0,'none'),('59abc101-234c-40f2-8b7c-4a080bdaa618','t15','t15',NULL,2,'ä¼šè®®å…¬å‘Š','xxxxxxxxxxx','2019-03-03 23:43:16','meeting',0,'none'),('59abc101-234c-40f2-8b7c-4a080bdaa618','t37','t15','t37',2,'ä¼šè®®å…¬å‘Š','xxxxxxxxxxx','2019-03-03 23:43:16','meeting',0,'none'),('59abc101-234c-40f2-8b7c-4a080bdaa618','t38','t15','t38',2,'ä¼šè®®å…¬å‘Š','xxxxxxxxxxx','2019-03-03 23:43:16','meeting',0,'none'),('59abc101-234c-40f2-8b7c-4a080bdaa618','t40','t15','t40',2,'ä¼šè®®å…¬å‘Š','xxxxxxxxxxx','2019-03-03 23:43:16','meeting',0,'none'),('68679270-fac1-41e9-b390-f5625087d315','t15','t15',NULL,2,'ä¼šè®®å…¬å‘Š','xxxxxxxxxxx','2019-03-03 23:54:26','meeting',0,'none'),('68679270-fac1-41e9-b390-f5625087d315','t37','t15','t37',2,'ä¼šè®®å…¬å‘Š','xxxxxxxxxxx','2019-03-03 23:54:26','meeting',0,'none'),('68679270-fac1-41e9-b390-f5625087d315','t38','t15','t38',2,'ä¼šè®®å…¬å‘Š','xxxxxxxxxxx','2019-03-03 23:54:26','meeting',0,'none'),('68679270-fac1-41e9-b390-f5625087d315','t40','t15','t40',2,'ä¼šè®®å…¬å‘Š','xxxxxxxxxxx','2019-03-03 23:54:26','meeting',0,'none'),('6dab644a-f7e9-462f-8feb-ff1328400504','t15','t15',NULL,2,'ä¼šè®®å…¬å‘Š','xxxxxxxxxxx','2019-03-03 23:54:06','meeting',0,'none'),('6dab644a-f7e9-462f-8feb-ff1328400504','t37','t15','t37',2,'ä¼šè®®å…¬å‘Š','xxxxxxxxxxx','2019-03-03 23:54:06','meeting',0,'none'),('6dab644a-f7e9-462f-8feb-ff1328400504','t38','t15','t38',2,'ä¼šè®®å…¬å‘Š','xxxxxxxxxxx','2019-03-03 23:54:06','meeting',0,'none'),('6dab644a-f7e9-462f-8feb-ff1328400504','t40','t15','t40',2,'ä¼šè®®å…¬å‘Š','xxxxxxxxxxx','2019-03-03 23:54:06','meeting',0,'none'),('6e7221ea-b59d-424e-ad14-b6bb354a8aaa','t15','t15',NULL,2,'ä¼šè®®å…¬å‘Š','xxxxxxxxxxx','2019-03-03 23:45:13','meeting',0,'none'),('6e7221ea-b59d-424e-ad14-b6bb354a8aaa','t37','t15','t37',2,'ä¼šè®®å…¬å‘Š','xxxxxxxxxxx','2019-03-03 23:45:13','meeting',0,'none'),('6e7221ea-b59d-424e-ad14-b6bb354a8aaa','t38','t15','t38',2,'ä¼šè®®å…¬å‘Š','xxxxxxxxxxx','2019-03-03 23:45:13','meeting',0,'none'),('6e7221ea-b59d-424e-ad14-b6bb354a8aaa','t40','t15','t40',2,'ä¼šè®®å…¬å‘Š','xxxxxxxxxxx','2019-03-03 23:45:13','meeting',0,'none'),('95cdeb9a-d819-4920-97df-f9fc9c54bc22','t15','t15',NULL,2,'ä¼šè®®å…¬å‘Š','xxxxxxxxxxx','2019-03-03 23:59:54','meeting',0,'none'),('95cdeb9a-d819-4920-97df-f9fc9c54bc22','t37','t15','t37',2,'ä¼šè®®å…¬å‘Š','xxxxxxxxxxx','2019-03-03 23:59:54','meeting',0,'none'),('95cdeb9a-d819-4920-97df-f9fc9c54bc22','t38','t15','t38',2,'ä¼šè®®å…¬å‘Š','xxxxxxxxxxx','2019-03-03 23:59:54','meeting',0,'none'),('95cdeb9a-d819-4920-97df-f9fc9c54bc22','t40','t15','t40',2,'ä¼šè®®å…¬å‘Š','xxxxxxxxxxx','2019-03-03 23:59:54','meeting',0,'none'),('9b1dca73-2533-42c2-810f-cb3e02524d22','t15','t15',NULL,2,'ä¼šè®®å…¬å‘Š','xxxxxxxxxxx','2019-03-03 23:42:05','meeting',0,'none'),('9b1dca73-2533-42c2-810f-cb3e02524d22','t37','t15','t37',2,'ä¼šè®®å…¬å‘Š','xxxxxxxxxxx','2019-03-03 23:42:05','meeting',0,'none'),('9b1dca73-2533-42c2-810f-cb3e02524d22','t38','t15','t38',2,'ä¼šè®®å…¬å‘Š','xxxxxxxxxxx','2019-03-03 23:42:05','meeting',0,'none'),('9b1dca73-2533-42c2-810f-cb3e02524d22','t40','t15','t40',2,'ä¼šè®®å…¬å‘Š','xxxxxxxxxxx','2019-03-03 23:42:05','meeting',0,'none');

/*Table structure for table `mr_event` */

DROP TABLE IF EXISTS `mr_event`;

CREATE TABLE `mr_event` (
  `mNo` int(6) NOT NULL,
  `rId` char(6) NOT NULL,
  `startDate` date NOT NULL,
  `startTime` time NOT NULL,
  `endTime` time NOT NULL,
  PRIMARY KEY (`mNo`,`rId`),
  KEY `fk_MR_rid` (`rId`),
  CONSTRAINT `fk_MR_mno` FOREIGN KEY (`mNo`) REFERENCES `meeting` (`mNo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_MR_rid` FOREIGN KEY (`rId`) REFERENCES `room` (`rId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `mr_event` */

/*Table structure for table `person` */

DROP TABLE IF EXISTS `person`;

CREATE TABLE `person` (
  `pId` char(6) NOT NULL,
  `pPass` char(16) NOT NULL,
  `pName` varchar(10) DEFAULT NULL,
  `pGender` char(2) DEFAULT NULL,
  `pRole` varchar(10) DEFAULT NULL,
  `pDept` varchar(10) DEFAULT NULL,
  `pPrivilege` tinyint(4) NOT NULL DEFAULT '0',
  `pFace` blob,
  `pIcon` blob,
  `pTel` char(16) DEFAULT NULL,
  PRIMARY KEY (`pId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `person` */

insert  into `person`(`pId`,`pPass`,`pName`,`pGender`,`pRole`,`pDept`,`pPrivilege`,`pFace`,`pIcon`,`pTel`) values ('111','123456','111111',NULL,NULL,'ggb',0,NULL,NULL,NULL),('735524','123456',NULL,NULL,NULL,NULL,-1,'','','13717735524'),('t15','123456','æµ‹è¯•t15',NULL,NULL,NULL,-1,'¯.ƒÔÃ§1Ã‹¤ÓkèÐ}m–8BB°÷ä½ÂTœ9zLÄ\"Ö½zÝ§DËRÀÀÜ0“î¡YZlA\r˜ÕÅO\'•ÑGe\n‹l™”©MØ.úˆ²ÚZO±00Š ´‹ÂV•ÛTe‰oëL\n+ôì*£& ¤ûuÒe·Ê\\øWv<Øo_ÌµÍQÞ–®‹ ¢d’Û·ž×ŠFë§=ÍWq>Vbù‰ØôOÅ:I(0,#èóy-Û=e˜];¼ØO-ÖÖm÷™Òž\rëM£%·ŽWÞâc‹afEÅCt#\\…\r§Ÿu¡ý~†ÇuÑ3lÄÙ†¶”pÄWä¹j©õ³`sqmásv6…f¨lYÍá‘ƒoÑ\"ÙÖ¶±¦ýüÉ)ªÔy³26s’&Wì${·I!Ll¢CÕÖe’gúÊÿ\nÞFÝZ•¸bÐ*¾i_Ú«aƒ•³kZžR7î¢üK²tJÂ%U“ž(¸\ZE±ðbò<=	v²Ù\r¿¹îËÍ2þ\\Þ$#K€w$&GÜÍaÿBs†bìX[ñ\rk˜½­+o“›¦„°2Ó¢ÏÏ8Ü>ýÁ˜¸OÈÿ	11µQ“XÅ!«A(õu‘DSÌ%ž/0 iƒÕût\r} ]û#A…H¿ÎtÓ.=;ˆ&ßQjÙ¬½d\0SDŠJ7Â\'z‚y›ôÎ®ÆÝÇøt\"_æ6­•¾ïÂJoÑõ)—®Éf·2«Ö\nUãû†Æ\0ã÷CüH³‚c®ÉâèöfÆ»?ÍÀ\"ÞI®ÎÙSj‡•]G;ç“HÿÃÛò’DÒ\0Aðž>A½:(ŸpÍU¢{o»F,;wðŸÉï6ýE)ÎbƒÍV\"Ìz¹–1Ç¶ü¯ b9L¾žÍ2@\ZÄaZó ð{»d¿\\¼¤­\nË›¥õå‘£Xhr}MÇäìö…;¥ã2å¡ûðm$*.ú8<\rMu´,ÀôÆÃ_57å¤Ù¼Ïè×¬æž’ùª»¨øžÊ7÷[®ÎÀL÷Û+hD7 ¶–É~ÈU)A÷FÙƒÁm÷Âx%É8e]©7[Ö5øKw½·ú Õï^Dù·ƒC0Á‚kƒ³ùïÛzèþê?’!Áëiö{…nÄ@Ê[ÔhÎ¨Zß<1®äÙE4ŽG¡_Ñùþl¸­ë7:Moªu2ß§`ã0Ê““½Œbt¿Ñ’÷ºÝXKs+À©~…ÙDw&ŸÄ;ˆºB¬Î½ÕÿÛžø\r@ÄHm¨1%3}\'õ8Wæq¦{¢ç¥ïÞ|pÍ».}mA×âLÊ´ÀCHmUýtö•lB½á´\'†ÈT9K¬»X™É“És|jõ3øË3àc[ö¦õíjÆôà‰=\rÑq¯ŒKpÈcÑz\ZhÔ?œ³wŠÝ†»ˆ]p½ŒL}†1üåVú2óÅÄ<ú9†2„²ò4ô/¤ 7_Öx=Í‹”SœÁ·\n§t‚^dÎtËÔŸlÝ²ÖKG<©ƒ\n6²¾Ž¥\'¿ÁH\0ƒ&1•Â	ºúÁc[ã>+5:\n	\rúôr#oWã%ÞWº@$\0›õlÚ÷´‹Œ…÷É@öå÷/\"A¼ãc¡ýáÝ“O5]Î»\Z~tsøxr£ÌÍ¨Æ[Ï¨—^[3‚tW\rô«uîQÈs|!¹=àš¥Ê/«X)yØ`µÙs1õ,Ó¸âÞvº^6vWxKiÝ´\Zm*—@K‹¾”¿ßtkµP=âÊò¤´çŠÐ\"üM–R6\r‡Yêœ–5yš\\ð?LÙ¦¶çŽÇ†\rû{LTÞ@·¸PœC°PE’ÐI:AEù—ÒpYJgÕ¯c£¨=Óà\\N–§åˆ¹v1¤X³ÞB¢@lŽ]‹}Ô´ü«ÚLÐe9J¤°×»/¥xêÏ Hv\0Î3€±±=©;÷uÞÏæÈjAºÂøôÔÛôZ—+”ÏU{Øâ‰‚|ogØŸusó.ÄVŒ¨ip“Ã‹\\û>ZðI¨ˆ¦•ÓÜ€Öí|}É²ª³ík qdÆõ³w0Ñ‚~Ø»˜Bl˜à5ê€ST¨ÜŠÅxÓÎs€aÏ›nÇÆ¶DÆ\\L 4Ëf_9 ×=å‚ùyæ%3Z_ïËÎÕ±Aµ$†~ùqÃìä6ñ¯¯°!Ô	vâG¶X(gjƒ	’_8y%|µl®’žgäÙÚƒÊMiïöe\n(·ƒBÒbàœÑˆñ=‰ÈêØKë\\E¾ù?ÓÑ$$í<`0àÏ-,ýgtY]AWj.o	„·I&(þ‰‚§äwà*à7Å¿&ÎÕ™€P}‘@¥XÜ3ü\n¥+~74!IüÎöÙ1K¾ájÂì5	¿hîç~¦\\O‡‘òåC0Y¬Vžñ°—©›ïäÅZÁ%Ø@úOmµ£:Å²ý1¹^I?dâÇ-CPƒIpž³µ&°°ˆò˜Ù€æ¦kÓ<6û%I¾°>H¯iK\nÂêÆ{ŠéŽ?P\\ŸðÕ3´P³MÙr/Ùû¼ü˜½œ˜&°âZ€)s+jW‹‡ Zº’Í“u/¤‡n÷-}÷û‚ýî.ýÉaäÛÇîƒÇêTHò¹.Éá3J`ü¹8b]c¶&+\'¼†4«¦Ü˜`\\†(Ÿô ²”Í„9ÀKíþîJy®’óåÆžê”ssÖ?Q ¬Rf¡!ŒÙnkiÛ «Fu¼Ï–»kp±æç@POA‡1…,ØþˆÅˆyŒ/¬e*q QnZ»Û9›\'xÓˆýV µASÅ^, ‹w?ŒJ|Ž…^ ¾çäÖf•ï¢,f*PÿŸÈ£/dG6ê’÷kÛV}õ¤ß1gE·!ŸóP@\0YÊ8ÿ0_‰Öf™rs6Ø…ç8vLOÙ}Ôã¸3èq¿ŒÎs]ÄûQM¬yeÆØáà_XµA_LäfÝ¥¬ß1~cÇDæ¯ñT™5j¬ÆkÅÿ%MÀ¤b‹œŸ\\Õc9«qÔ§§Ç	5’.í1êÐ»u„öyi&üš·\\â°Â=fL˜2frÃ7šÁP×7Éëu\0³oÎªHÙO¬ƒ:±©`<©¼çNâP%«Ðèg Yþ$ K–ÀU‚˜`ªÆðÓqoÊ>· )$â–…}ß•Cèf¯â ï}§`ž	™ðZ’‰>¤\"êÊN\'&`W—–	gólw®àcþÛÞ;®Ås|nâ[ HÓ‰¯P—Öá÷&ÐxàèbrQÖ’,AüÓšQ=‹ÜoÌ(+{\\43p9/òþÔ-NX híë¡ÕÆ6ßüÓ¸Î^(AÝè¿Åa,µhî@­!`OŸ!Ý\r¬‹¶»(?KÖcw9\0P**œØ‘)-ßìpŽºˆÄ×OE»æÖ5ìž(©¿§tÓ`ìÈÆ›Î®*:ÝÖqx™.“åKÊÖëùOôÞMdÜW&(eê¿gí¨ ƒïŒÙ<ÁíþkVœÖ”ÎÖ¬mÌ“¢äFÓt~Dc¥0]….QŠÑ8&æëŠ\0^ª»=†Æü\n ©ÈXuif6Ðä&^“³2VUÄJâg©E\\2º¥›Û%;CŽ«pÑ©3®õ4¡8[jóëzÕ+FˆÔÂùû·kµÄbåå&¶á>hìp3(ÙV›|i×Lúp=Ê½ÚqÝòà)J/(,È´¼Ã¬œ¤™E:=RßpWRßu$¶	º¨ÞÕtùâüS=Uƒ^ðØy«ó›å)i\nàÑýåJÃnl5mÀ;¨—M¤Ve•u¦\Z\'·Îƒ.Ùå–‡æLÑ‡‹À¢@V¨Xd0,õžô1)¥Î÷DÄVÆrgkb½„ÿXô®ö}Á¯†§-pTžN6*á•ù	\nB„zg•Á‚[<\0o¾\nt]S$Ùysžrpò9‹cl5KaojgœØ¹HÉ˜ä1@EŽT{ãd<%òAáÒ’‚\ZÈ^­N9›#=füiÕü¬Röø\nÝ0`‚ÐÔ]Ö>YòV<ñÞå*Zæ«Ýì_„uàÚ°CDuˆ@¹ßµ+Rv¹ŒûV^|1&Î¯¦•“ÀÂ=oà¥³9Ž{qµj$\\-·j5ëÃÜ­¶ÓÚè KkÏMH›Ë|\rSÑ	£þéÐÁë!¾r“…TþçÜû“\"Ò•”È·Õ]#ñ‰Ï+z6]ÝBY”Aüáüt`b JÃ†bY2­™žÓª+V\0¬\\3L…m™¾ž	ðÍõÛÀ6Úýy,-éM½›Pÿ|2gl\0nEØ,Æ[äóñ[3MîGå]4±È„àÚæŠ÷KK^1ÈÐ†Ý>Ê÷\'ÀZë¨ôzè]Àx›†¯«­¿q^‡ŒÁ²€®8k«L8,	ŽÓH¯í4$“¹éÐqê\\ÂärÍÇ3Lh6‰Š¶uúÈÔ2Ý[˜èÝ%^ñ¢‘EC¡âýÒ½wÓ²†“¨DòJtn‚WV‡{ÅÙÒÁË€ÒÒ’¢ÏE÷é} \n/~ñäß=\nO\r¹ M?j1Ü?,Mèåw§n »ó[°˜*²¾€OÔ¹ßÌ³“ù\Z²u®¢i)9CçÚk/£å`#¬-DÊtJNÌKÛ€ð|Eò9Aú0ß÷·;v:¿2¢RÎ!	ŠýPÇ4šé#hŸõ¨¬’}{ÒÁûBƒ†-Œ?¾­--èëpÐVc‡d÷´u¤Z“áÉ\r•‰¡Ã ð\0÷êð¶žÅÀ8w:á|v]e]vò²œ½Ûà¡™æö»(‹žÃzÙ§·ûig£¥˜º<›uÄNÐª† ŽÁKIl|U–ócÑÚÍµâ_r³@›˜Ç»ºuû¼ëÄø_0L¾F¼âY<‡†hYuaÖ«H½Çaº‘!°mU¬æàdZµ®« @\0³»Ô\r*ð?!»•·ž1]—À¹Aš• 6Ì¹íÎ3˜ß\n”†\Z†‘dœ·˜ÒÀâ½]yƒŽ‹äBó%UŽ°ë*°:ÍÙÿ¨®c\rYØ¹ß¾[í€†¯c2wí¥ïœjboIíLó¸æ•³Ñ *ª®øWïÝåÖ!›â#@ÐŽà‘ªŽZõXž¬h3Nº=¤Û…Xl§YEù…ÍÔ¡dçòùm¼Ô˜¨6w^³vÜS@q*’S‚OÓ#æk+™¹èUÒ¨Ì¼ˆŒLCl¦¦˜ 1úY8ÉµÄøØòÑ2Š×û;Ì}Z°¨ß: {n¨«#É	ˆäÏ¤Æ=e‰oWËz\\\0ä¨Ì‡±³p#R2Îhð_ÀÒ°ž¬7ä—ÆÏ£ÇjØæo!¯Y#Æncl\\1×ÃLŽVuæVðbÃ¸—\'XC²ì U¾ÿVVÑ/)NˆHó†¡Ï½ü.rÊ‰wÅ¢…ÒF*¤þ¸—î+3^¸¢)Kù*…@ŒdRlÛÐÀo=ë$žA\\vk89ÀÌ -µzðßÜ/»Ž	‡¨Ëðº—Û¾ËÀH²Â?¤àb@¯ï~Ë8çfåÀ%´çd(=˜v4=¶ÇÓX4­‘7ìd!¢èÁ áô4¨Š])§ƒjMÛiÕ¿µ\'1Vñ\0øž\rQ9¾ó õ÷ÐtªÚkýÃpù:l.ç¶¯|±«:cH4ÑÏ¡G·Æ¢–3=Bd€dZ¢`\" i8i—¨Zm:k–‘yøæÜ{/}£bbRKµ*45L\"É	€n×­AA’°ì¾)@…ý=!üÀ)š[†EH1Î’#ß³ýõ‰7x¯ÇëŒÕ§•Š”JÝpŸù¦IÀ¡‰ÌÑG[·H¤úÄ&ÌåÂÄ?­LµUIÝõ÷Z9#†ç ‹1Pèl¼Ÿ ëj»ÝÇ¸§KK«Ú”;ž‚ð ¬v; ­™š‘4{E—D¼‚ûQ7B •ÔGÀZŒ÷›­ˆú6h6ÍÖFSH¿ÊˆVà8sV”^S‡3ºóP|f5ÅŒ†`ô$Ký‰>µÀ\":£·†_¥©T¦ÓÚ}é´|§Éö`²¤,TØëÐª”‚ÝkGÕ‡7€Ú‘†lÀZDÝêáÜÅªSÞZHm°rHßÛ€û:Êh¥»†!¤–­3Ëên9ÙÞÕƒQZÖ}ÈÀq­¶ƒÒ6^î\0Ô{ÒË>Ž·(ØŠ@[Ù$æ‹V´\0	ä-Áž­M1>^dT9?J¿>0A_¤ÙˆiiÝ™.ï{4BAÝs:ÐÆòcê£=l»CZ™)¥¯ä$µ¹AGF2Qf“yª…ÜcOÝ7¸~ÔÞgë\'Ž]€ù¼·µò	C\rGk=47š•Ø¿s\ZˆÖQYÿß’@ˆ2[4¦róW¥@oióÚ¯9®»e~¿$V†Dæ?.ÃN•XA\\¤õ|Cnè—Ç]Kn¢®jqÑ™î¡F‘p–JlfE,[RÏQ(C6Ã¨µ()9Î´VçI±.Ê7Y¸…îKJ§î*‰¹éÁÆ<ú\'óŒµÏ7–£GõLÚ\'´–z?Š´Ô* ­)btLÞ»¸<øðV\0ÏëðI7\në˜¬\Zî<Ë„‹i’’!B\r–Õ­Õ\r\\c§†’Y‚}8MÆÉãZ\Z”Ó\'gÒzN=t³Z]ö¨*Þ\'dËŠà¥`üŸ•¤ô&dG´\0í\'#xDk<#âNÍÇê¢³¬ïÎTP\"Ö\0’¦v·zs0ŽýÉ­qZ\n±%¬¦>ÔN“rZ”{Õ,?Ïö÷ãd\raÚ7î‚Û—¦\rªÐ	oÐ§„ñ‹ÍXeµ¾#ÓëºÉÉƒA0tnw7ÇþXá¦R1¤Hë:cžz­íéwxêÅŽÞîT¿/~Ž\'+\\œd*,6¨Ø$0BO¥oS”bÉw-÷”òt›ïàAså¿š[šTžÇ¢âÑÞYæ8Î2‘B–ð]á\nïä’Ëÿ‰N[ÚŠîÇ™@	\\Yë®!,a‘íÅÕ¨.sªÌôËæ€jùgQËÀ´Z4b@·d¦ŠšÄ	&¹^\r`fYjqˆ¡ÐÁ­ŽÑä\rÊžnª<ˆÎj{sÎ†2,c÷\'qß.ÄeÐÓ#©•ÖðCY¯A³µ·êâm’™°\Z,!¨ØÚÛ`g]MíÿëíÁrŠQ²ª©“P.ñ]…3;þè\nvËÑýü‡b÷Ë®Ô½@÷>ï¿àÅ”]·ñî˜÷2öD>®ë\0àc÷‹RTŸøÇ…¼N/À\\©$=ç/»Š„´±œ9)8UúÉSÀ[GZ\Z÷gz\r·W`„î=Ÿ™hÀÐfK0`ž—ÏCço __‚³Z5wÓwK+çb,á*eS+.‹ñwXQa‹Œnp„1y ƒŸ#ƒW+?û3jV|À©5\"iÆ!	P.q@â°pìpÂÇXÏþž¥¸,Mã‚4:º6XK×p<_Öwô™Ñqb’¹ÎªuÐ”ÿØM¡XÃ»Q‡Á×ˆb1gÛE;xüÂ{1êñ¨g‹w”H‚˜­–°q•øx7ˆ‰Z´WQŒ—oðæ‡=…LA #\ZØNßû¦,»\Z‡Å¬$vhgt]gJá¦\0ë™\"—?€åöH¿ÌˆÞÁàc~F\Z}Yÿ>yÈ·÷Ãä¡)l¾)I¯	ëö¤-|‰âå ñb^ö“pÌØ¬Lð£Ø»£e0\r§Å­IíRDÐ†¼c¾&…Ëì98Aç&E™¶:/ç¬AéHã‹f\nmi¥jÆèô9ôpIFžg#—ÍóÿžiØÛeçÿN¹ëüC\Z¥ÃóÆ‚Ÿü• ßCÛù;Ø¦HNÜ‰¼“e¹c|@ÊJƒ±•‰ôÁ]¯ÉðéÙ€¾ƒ\"Kƒùîã…iîÚîrØXÍÆ–EsQºÐŽcÜÃØo$°ŽKGBF:¨Ó*CZÇ³ƒð]*S˜ã:Xî¢düï¾.W«kj*ª\n’³¿Uê<ÆÎ\Z±1_E±ûÖ·Ë£#¡¼mÑ!Ïc\"pÿúâö½\"QÇËœ?ö†n¸e«ÞpŠüvfž´âT£Á¥?Ð\\ŸÀƒU†ïNR,]l\n	;K0”7hÿ~7Bj*Š¼`#¤è`öÛµâß©ºsRòMÇ§”½£q‹iuþF‡H6h\"ŠéèãŠï† ;TÀo~ª	’‚Ñ‘’±ËÅ‰]š<})ÇÓ#0ùÁšÇ¤Ú«d±>Ë	,sAMo_ÏÅÑ\"æ)ë¾ûáF¼`¾jjPÕZ&diÍ2¡0Ü-ì¶ÐmUÀs`šC]Â)3ùó¯ÓrBp^\n¶\nQ$Ô@¢ÅÄÑiLBå&ÈÜ<Ç_5—¹šÈ•£ššãêh\r­\Z3¤Í¼“«õ¼íYõ©ø¡(zw:Dþ†UC,µ\n g$hÖSXmŸ$9pýh¼6¤0*¢IÏ©/\Zrå$¢ú³,”a*”öA\'ÏÎ^õ4eúA`ˆÓÌRØÛ«#4š?„ìŸ2H)äo’E…‚À¶IVá•Û†K\nž--)˜ƒxjœõððØut,{`ÅZä\n^L1Km_Š“!´Yª]TŽRB¿.¬ž®Ðò½€¼Æ$ÖæË¾«\r6äå¸ÆVß¬µå8êà\r\'€à€Nº}R%$‘Ë^sWS€°=n¿HfÈL˜Ý|¡×688é9¥õ¯ÈÉ“ŽpZÖßwpõú—G\Z³Hn!UßÕà0Y«Ña\']Ã\".´\\QÐˆCo\Z°x˜ÒµNòþ<qÒÁw÷+\"´y^[ÓÈÑ…Œ‚ÂJìxŠÇZž?ý²‘±1Ì²Df9¹“‚À¢%òž]æxq¤\r&Y=oZ¾Å›\'/mlß)]õ+ë¹kJ\ZnDPÎ·›†+Ž\rÐZ§<5ó€k\\ò`ÆÂXÅ_\nØ³¨kðŸkËí`‚Ø$s—ª{²ŸÑÿwˆEéèmñîúŒð3\'~›}žb´tBŠKpÆÔ¼[å;DèIƒK³Ü|6¡8†c6È©LÌajnÌ®ÔûkgGÀpg\\‰‘É1‘PÊÔØ,»£M|IÆžéùÒlN<€­{5dÉÙ®mIËb4Lð&jGdÂÐ	c±Ç¼ugxñÎ‡Åâ@=afBËíÛð©rïGöE§‡\'Â#h/•/\n ×Å7\Z†‘¾à°y¾ç	xý²ÝE—ÝckVXa<œTët¿•\\œ<¦JŠàÁv×e¹`GÎBO—¸Šß~\nÞz¨½\'7Â+ÞÜûHô9°F)?Ï™®<¢´}ó\\ð€Œ-’ôöï Íy\0áÙ	~4ÕÓÈG¦n†˜Ü\0$ÿ‹»¢eêÀ/s	ÒiŸ¾?Íð±›ÄQÑŒÚžÀ’¬P%^8<v¦£æÊ ÀT:]2õ¾¹§¨“ø»—©-´\'Ž&åIÌ„\"ËhÙ®²ðe|¦Ø—×íQ˜Š_¹\\Ï¦ydVg\"•çGjAŸ€´8}± A4L¬xi.Eåz0?\nØ4æÚèJ„¨º0X4N(?yÝDÉµ5ð;µàú[O®\ZvŸê–žÏwC>Å\0›8ÃCšX+§ìŽEE‹âúóRÙ$N`iåïŸÈ‰&9=ªFë©œq}†!\r‹u›H¥©×Õv=A^þÝ]önŒã\0Fòx8	‡Ã²ˆÅ‹síZšZqùÄ­»yµ]Ÿ2†çhË÷™ð’?l²5¦SµS<Z†R¯ap·“\\‰ur%“Ó8ÞøæŒb;²­ò=9Ü¯0Ç©½ÚE˜P9±Ó[`\ZúÓ?t¸kæÉóM`\"ŠŒ·pÑHI„ê#ÚŽiÇ¬ÐV•:¶ò­‰,`Œ2Ø4¹‹¹!?š”¿—+‚ Ô½r¡<°Ñ-úÎáÊòdá±Þ‰Ñ\n*ÆxuAÁP9Ç9¼[î7Ë03u>l\\†EwóhI\'¹ðžÐ>í’N:¤ÊÛ¥ñðù¤os9¦¯<Ìt®ÎÊ§ƒO?LhÛb†Ñ%r8K0ãˆ*Å%ÁJhšøÅ´­ªÙ6 3{ki Û]a>duêÛ’x:Ýv(oÊî¶lqQ™Ñò¢~s£@ër¯ÖO[é9º	Ñti»×õ`!l±‘šÜ9äÚ¨=ªXÊüB\Z%9[·\\[#—yDÎwês,µ†öx+ÑBR8ç-ç<}ÃÊ:q»6BD:–Xˆtà1ƒG6šÈÒÖî¨Ìhº[>ÍL@\0ti_ñ˜W¼d\Z0-ÿ†B ëÉÖWá[PR$Ïy(ÂP(L1m¡ïþ»¸G™ÁCËUÿn¬CVÌ€©–ÜÂ=Ä–åO‡ÌáDcÑWeµ×±²ðË[ÇŽ)YyA’á†QN8g?+/$9^ú#Âmí¸ÓzâÐNxôá––\n)^A…šØŽ{bñ€‡6DtÁ8UER%›ô÷9?Ù±\rÍrµ¨C·7Ù/ô¦*žÓ_<kRÞÞVH(ìïÏµ®ÞF÷«\\C Å¤\0¼UÜLO”±µ%¥†Wùß+²Ü|qâ•äç´Mk\r8nQÉÎ™‘]‹ˆþÖÇDv×“>%ß’o&0ÃLt;CÇÉ‰™I®­õ^âÞËâ6`Àí¹Ô­“/àÉ¥Å6fC3×%ûãÑ^uÙŠÛ-ÁOg”¡?sçhÜÔ4dæ>ÛôÖÓë5C“oÝ\rsZ(ª§øNŸeÜi`›¼Ä¢p­?( ²4Ï;s°ŽÊ¤È¨À‘žiÈÎ\r£­ þŒ·óÔŒV*Sj!bªÚð´’Æ7À£áuÙ§5£­ÍãI‰4{Ü¿ÎË}jøø<7ý6w£“1ë—°Í<¼]± ¥ÓóÒ}’¹µ·…ÂØ¼£M/y±?ƒ‘ÚÇ¶UI—)oúZz°²i÷VÏ/Ø¸mÃÎOûÑc*÷ÏÀ:òe4ÂžT‘WÌ†¦Ë‹l£Ù‹ë·8WbXµksñÉM×¨š˜H.È:%æ¾jË™IÊ:yo›Y“–¨ñÄ}ÇŽrÖ©ÁÂì…Å£AÇcda£\\g“s=JØ&w}K©&eßlêÕºQ’..Ù½÷9ã©y3–„|ó5A“º4_ê– À9ûI¸p˜#Cœà5DvÝÿ#–H =gh Ÿ5P[¥ó¦C¢%?²àB	\n×ú!…¢NˆG†\\Œñ\0Û‡ßqt\"j‘è0Fº6Û4Âžñ/ÿÇwŸ;¼Î|ÃKÃ!´”‹Ú5ÛQe¢7Ó¡àWdƒ\ZÝœ«JZEÆ¶8ÛÅV›¢i¢=wlEŠy‡¿aúTÂÊ…z¯\"}ÔÇ&ÀÃ¥î\ZWQh½aÀ\'`h{_7ÌòËžr)íôþmQ¦©·09·<ÍbÃŒ&«]	N-Þ vÇöÿ_nÊKˆóYO%ÂO\0`Ì¦Å²(F*>ô\rÁ”ý[ú»)\\l«Ïß™ì´Í)æð‰vÔÕ<™ƒ\'³ugÂŠmDa½µ÷Hk‘Á9Ú)Eüha»õ\\þEmoÈj¦Ê™xr½#†åƒc8ØmÕÁd”‚ÕÇ\n˜¼;ê“1÷ÜÎÁ¬à0š­úw–^}ÞõÖ_Ð‰,j–|o§*Îvûƒ¿¬L¶±ÐQR—Ûïè©Â×“{fòT+ãdÐ2I6ž	yëÃŽÒi»Oôlµ9NC2\\È×zQ}g6ŽŽ×O†ÍsÀ‚ÝkÉIÊÆ¥ŽŽGø@¤T”åé)‹ÈÒœÖûsÌðV\0¡¢ë„:g\0ªQÔkHþ0wq™ ”r–eÜ+/Öµ¢Ÿ¨‘nÚâü¹jÀÇ$utÕ¿ÆK¬äìJˆ§BÙU+ðaË³üQ2f±“:¸ŠHó`k?Mgê²H²œã‘–ÅÈ$”ÇTùyp±yßW#üÍ-aÉõðèÎ7Ê¸lá	À\rË¿œ\rŠ&mÕ…•‚DCä}Ró\'å ŒüXá\0»¬î0Æ®O%‚ÜýËßFò])æC¶´Ü˜¤´\"F%ˆ\0ÌÁÅ”V„¿Kr¡[ÔpÎ×¥ÿýÁéëU6ªl~‘6ù”ðD¿{EU¥w5ó‡€ëÂD¿)Üñg[Œ•Ó¯Ì‚’.Y}¦Oq¢;†\\­5R>‘àD„Œzò.EZvÿ\"b\0æ9„idäVcûÝÈ3~·ª ^JÓÞr|‹êR|Q2Ä5°\ZMôÄ3J¥¨Z‘GÕ#‚!Ad÷–žËl¨¡ðã,Ãí¦´Vý>ÎŠ•Ô#Ã½èiv„\\P¶M?ØÃuìO,Sbö¼œÇäüÆxÿÓ*™åýé\nÑ[Ì=LL¶*9bþ]Ë´T³ñs5KïuEàó›\'u%ä’þ~Þl’Û7 H@†KŽÈí4$vÛÓ©‚œe±M]£T\"3[Ùxx•7çïMÉºµ÷‰¤·.ßz#œÝ½R’T¢q_ ‘ã’)\0öÀøp{‹,tÿŽss\052È®úAd¸(¹ŒÔ&’Û­O²²ž@Àhl¶Û]>opHŸ†üÓë1TL“J>z÷8É½&•h‚HÓÊÌ\Z^_hÝÌ‘ÏˆÌ‹<LÛ8OÜVµ’ãáBã´cÊ»äÆ]?ÕH(lÎ÷¯NxüÎÓ| ½÷v¶cu±þ\0Lß˜›/M8#œUÁPªâÜ¼Û±XØ\"fÕÝ9žsôÊ#ä÷x?;žÒ`Á}\"†ÔïÏ!\'àæ}BòtÑ¸Î+íÿn®‡qÒaBJÜ\'özHŠÁµÅì![Ípëjí]CêÐ1Æ\r£ƒµÖ§\\S3<}SQî\rS‘‘ó\\Tø^\Zi™’Uý_¬çcÍ¹ÀÅèÝg8¯\Zâ×x¦š§ä’²K«\\R½ñ,T“8Ä\0^4UCxv•©=÷³¼qž\"Â´|5¥(É~5ÇÃ`„ÕC–_\'˜/~m.sâs‹ìfDÁ ümwDÛøí/KW¹Ï!öñºªhk=ñ\\çý{{Ôn¦ðx·JÛâñ!\'Y‡7`ChE¯’{û­a˜</Zà~B\n.¢±BTÓG{Ñ‡+CD&¿-’¾¸0>JÎ‹=|»žª\"áZ·Ì°~Œq=¾KwÊŸÅù£JË^<•Õ_*Ø¿L²›æ\nuó1L;”ù&mUÓt†ZíÃæ“«›ÕP,ÁõÕ«u«í¯’°ájÃtŒŠ÷â‹‡K`ê½µ÷Á8²=¿D\nï7r±‰^\'ÂáçýgÃKÓïcå™Ë]:e)a\0mÍ´‘:V¸ùö¼þ0_‹„AË“\\˜ü‹Jiú-]%üÊ£èónh´j;Í™°*ñS$ÈÝô·5sÞÄìP\0[=Ék=kRª,Ï–Ké£wõa¦ÆùaµLh2$ïOF=d¸/óßvü]\'ë†÷˜€E’U=^üa}	Øn|^!:yU~[´¼(dÔ,ŸbB³vNñ”Ê=º€4êzÖ	›LjÐÄåü¿ÆÊLyÿì×õ ÏôÌ\0AS×9ÇÑÐ;™‹h£\"ŸQ½¬Ãi_\ZN<93§«x p™£–ÎQ„ dLÆË>¡ÔÐÉÂ\rÉ\\²úùÕo)-Ø†HÞ\r¼žñ/õAäˆÇ¾<·Ûk5¦üMú²z¤ê8báp¿GbtÙ&8JÇ¨#áb1X¥\nHÊH³_Ö;\rI}xÊÞHì•–2Vnð¯w+	Uß!NñÔŸ^?Ð`üìˆ²–á–!É2ë§Œ¾¾|a3@ó3m/WšÒ†(‹AV‡?µ±ë—Ó¸ã÷‡ä˜z#Jq>ë¡Ÿ™rúáwL ¨¢¦Kêº7ãK\"e\'ÕÈÑºsW¶[´Ø4„+Å‹U3uiBÌ§XÖ,ëNàæ”[ŽáÿµÚfoñà‡¶chQòÞ Åv–V‰™_	Sù[Œ\\zí^+”¬‡% 1K¤ÓIíbÌØåºYòô£5e[@ÁëâÐMe&åÖÐ÷!U¨Ä*¿ws*ãœ¯‘gE”rÅÓ–¾ZqÎaé\'øF}¯nA¬ö;M*KQr)+ž¤ ÞÞÂídí‹ÿèÝ^†+±¼ÔYXtEÌD-\0`}îeýÎ9ÔJÍR[ò¶Å%ØiÛŽ÷„*¦U?©{±¸‡a¤×ÁÒr¨Q¶q‰¥Õ%f)LÆ§€y_–alTç#væã!™A‚Ÿ0Ò[Ã|Ð\\t|ÀúYâNSJD¤®$mER,nýO³åÀzîÿdOn;.Sª—E=W¶R4ë5<Zõ+¬ðQbXèS‡–„Ð\";ýï8reÉ$Íã¿õ×pïº×I>€ÅÏtC|çµá#C%,urN›ôLŸlQvÜ’.ªÝ&yíDìÊ¼¬ò¿²ýÜ\nz’^PvzŸ]ÐN\0\0ú³­/¤=Å´Ðß²”>ÌÙ¸ÆŽD‚ºšp­daiêFŸ{­r¤²4Wõ‡ ³J&BbŒË§ƒïØç¼CÍ¨Ñ!óì8æ£ãT:’ƒ‚ÿÃÁs-`sfÄFLÉ0þSŠÍÀ‘üó¹E\"Þõí–ß„\r‡içCedrÿÇû} pÁÃæD^VA$^|/q®pî× þ†5•{€ÊQ·) È8bôÝ£ã_þ=C:h|NR ¦Äë§š…UùÃŸÜûIo$¾rjím<”ùJ¦ö1tUÉ¸ }iÕBƒ±„ï0æl+¬WgMŸ¢˜>î8ù/Ž»z¡+A]/Ì/É>Ä_ÞªÈ‚˜§½|9ˆÏ­‘yxÒ!8€ãÄœóWtO7 GËÊmYröÛOÕk“¿º$¡èLF\06CÑ‚ÂÅÆV\'<ýÏØKm zQÚZÒ>””œ`+NÈs\\¡Ñ6£h!¸bbeVÂ+]ÕÕÂµ)s\Z«vµ[öôÞJÛT’ðŸ3}¹ûFÈ(D[Ih‘»«ºÄqÂ`nÏüìõtW{ŸD«×¡¨@ÀÚGn-¥&É£¦¢«x¤ö×DÖ¹ÿ˜Èí‹éî=|¸·pûêŽw\ZûÚtg¢Á…~pÑ\ZWÂÏ_6ÂÀ7E,èëíæÉ ‰R¬Äi:Á8onËj^å^äeê\r\rÑcÎíç®š&Psÿ\rÁI(2¹SŸVyçK\rÉ¦ \\&‚?˜yuªë°4hµÄžª“Í‹Æµ$–ÉØ=¡Wêyˆ\'V@1ó¥½nÈÍŒ›dA&Ü~Æþ“1Ñ±Š^Cÿ’h‰ön¦Ql¥ìˆdÄÇ ³°z iÔaÅ®¿­°Ì%øÈÏ¼ƒoÏ\ZÝ6_Ä4%‚PÖæNúÓ$j\nÚ/\"á_Äešà²ûŒ¶þqÙYÆÖùðƒì7O\'c´°ý4,ÑÄˆ`a„î½\"¶˜»´Öè½Ë\n-Ã=Ù\'ñ_ö38b+1Âé‚¬è¼•òt‡ŽBÃ,‘Ü¡!Q#ã\nÖÐËg\Zí¨)ñ¢‘æ±áU¾!¶ 	/üOS%R/ ŽR1¨µaás­}|€Qšê§ù?‰¿ýX£©‰¬‰+¶Y¦ÒYTžRFÔ/â½ÓcP×¥ùÉþhõ¡¥=™‘µšåÌÙqO³U	&½,vÜ	K+›ŸÓl1nÿˆîž”…¶íÀNÏ\Zé±LwãQˆç3r¸bWº˜žPüà­Àê—Ü”)FÛÊoCV=³6Ò/ãK«g$+QÅh¿@ùù½Ç‡3ªéÅÖÑØ=Qœ7S?\0Ì·Æcs^uáí±ä7ž\'p¬/XÑRoèèá[E¬mmŠ\'[†à¸«0<W‡@c\\€\r‡ÑƒpNJaAð,òÿËŽÈËAµ=Ç.¦vÆ€n*ºêŒ6>tÆœ‡m eûdê9~$6ÍŒÊ[ö>±Rïkhmíª¯Ü:uÜª¾|ú…êå*è¦Ä§x«ù6<Þ°­ªÓz€è¾Ê[\\èØ;­-+v-¤•ÞhXo-ö…~.{\0&fwyÒ¤…°ùÔƒ!IÉsªàÇ\0`å\rÒiæ%+=lúB2,ÀÕ—¤ñv|d ýI8øú_˜ðÍ\0¥ß¾U£tx@UÅ½¡_hçø,ö7.\ZCRR°SC&GF9Ž\'j¼É›2Ïðs*œØÍÀ`QhrIhëf÷¢uåÛµZÔ¦ÛQ|3Pw(­ûäù¥5nuÒ#B)é´gËaÂ=†Hý™HË³ÄÕï–)¦MN›f§ñUü¦^2´pRm®ç÷0”`Ä«_„Á‘F¹×°©’„½‡É7JU˜>\\‘°~°O¸í2À3e£Qƒ«^ã}Nòøs¦ö~5vC¶k‹M›Û%dì_¾Ÿ:µ{º[îkÃ¼OnÏ„qpcB¢L˜Û­îÙ°—Ä\\[{ó¤â–DyºhöàôO³¼\Z>}˜+øÅþQ³›#-Ë´äJï^”QÆÑ\n¯=/1W\"îw&öwçÎæÄJeÝm:²Úe5ûíª0‡×VŠþPq08öZ†n`¬®v>Ö;ùÀ¡¾óO—\\½ðTbdýBŽ4ð«Š…1^§˜½&#/±1þ¨Ê²ìÌ9ôµIéÖg—8_²¦ýpo€€\"Ð«Øö\\{\Z¥{aÂ îÃWd‚yÌT6±¹Efâžû<·ûÒ…°’d’ƒ³ÊçQ~Ó„4À&àFÞÔ)a¥E¿iÑ_Î}\'c@¸`‚5Rd_ÆL‹wüR WiÔ˜`/î€éQ„?Ô¦#T#}FƒÆ·o¦Tåôrð†í)U]6ºÕ¼l\'ïOüª¢Êêè\\ÐDYŸòÉþ!iÿVÔß¥áªÛûòÇK¿ïä\Z7on2…‚ž+§]p¶ds:1\'uÃµølÕxtÐÊÉe|Ç¯t-´ Pôuñ­\nßõö—IŒNCzòaårñm­ŒÿMÁí•îm5^E1;Eopüô\\JK‡…¤ÙfÁã–ôô ¬qW½%Ý™èX…¦yÿàe1ø-ìˆ!R¬ÎqTøþxùèdeÏ!LºåIvÂ[o>	ô³Oƒ{öûÞ¹ÊÈüÎ”X¾]ñµ|þð¿Æ!^Œ\nFƒ¹…\0ñ^;Ù&Ãô¥ªÎQ¾ÕÒ\0ÿö‡\rÃ-ŸPhB©õá’œÔÅ#”2ÄòÄÿ—wîÊcjÔ±¤cËóËö«ZæLËà[G>&síÛƒ\rŽK¼Ö”hÜ+‘­1!qšŸ—xÜ|B$k&~$\rÐ`Ò²x&Ù‘ƒ‚,6{—<i§TduJçjüöV\"O€./BY)tIƒ%f$Ûm—è\Z¹¼‡RôvÐúQ±§“×=°$’ªLùíÁÍQû‘|ƒîò1ÏÍ/IGë×8KÈŸ:*°„ÇŒãñðRæÝ×Î½Fî~mº\"K\n«älhäÎvùŸˆ<V\'ÊðS­aý‚­âPÉLSmªi†Q@Ý¾|.©6á‚ —s\n÷ý¦@±ÿÑ¢íH^ˆ§EŸØø_kzœ\rðÝÒK7°®§Ðt9*(tªM€¢€g…ê¬ƒ	|5Çˆ³EÏ÷¹êP%õNl’\"\0±aPzÈl·Ì«´àuÎO$Ååß~¬¶’å8×	äUÌX2nÛrˆÒeÃ¹gW	åÊ—¯±a„ÐÄ’)¸<‘ÉfZ¬tÛòü?Q+L“ÛR¨óÅ¹I©^*áþ{Å’0Y-ð.w}—q€þûiÌ€œBBàÍtË\\D’&þº	{Ñ1QuÂÇ\"4\rì•Šœ+¨ÑÐzŒ0^\n¥–‡Îeô)~á8ÄD6f.i¬]Lò4ÒRGÿýG®”´aXŠöo~¶/<:ºK/)ˆò^õÀtŸ“\næ™ŠñÔ÷^©L§q™KEÆþ:RÖ×¯O.Î1eë•‡²*òaï °3úÜåÈ\\“—]e \'Iˆ HŸö¡:ãç„~-ÉyëÑ–¥3DŠ\\M`,—èÌàj±Æs×Ue.‰ñâsÓ_î°ô ÏÍ=£l3]ž.VùØ‡®¡q‹qñê\Z`<ò¡2ÁŠw	!.š¦z•»Yû2þä´ù÷iP”‹,éú×ã=çxïçïŠéÕºÞÜiv¡ØC}‚1ýdZ-ƒ\\ÏœÈ‘:Ø(ñ­õ‚šYþä‘ÏÿåÉNð\'\Z¡´\r¬îD„à³õaUJê´N*¤…9Î\\?á5äQ¢º|óÔêå+Ò\\a¾õj­ÞÛ	ºuN%uŽ  ‚³]ûË&†<\\äˆËKçLïÝàà‰¢±Çæø—´éÏÖŽß`-ÑNFlÚÁùêû”¦ðás“ÝßW¯±\ZxŽï’<æ7ŸQ=:Þ	äù!¼ >1‹Ì\'=û¡\rûÜ0Ái@q[Ãt—Ý¯FKÑ²ßFàaV>Úì.V²2tØuÞvrHŸÙ6²}ÙØ\Zj}Æ«y¬M²vEd•Š1G†ÊÀ7m\Z¨\"AÐE³$ÉIe¢†™ÞWúUSœ\Z½õðT¼*¡T–õÐÞc\nÐü½$.†bD˜³r„kš(Y…ü†Gv›k“ˆd^šÅ³CÄCÀùùŠñ¿ÿš™…FûBï“tÑÑ	‡ZAžtÌæõø!²Xlß]Ó‡œ”ÛŒWÿ“>ÍÃ‰(ŽDK†øÅw6†~‚5ÞùÒ¾þ9PÇ®ê8ã`®”Ë-¿8{d!H0\0ù¦ ƒ]˜Ì‚<öt=V\r°JRï2Þ9KV_iÆÙÑe5Yy 9xo&\n®1$˜ä÷èUÛ]~÷Øƒþ n·´\'¼ðgRz¸xVFó×ˆÒ‰Ç|ÌÞ×­³—P ´½+ýU.é¯¹œÈiâ¡ZbsW*g‡ù>€TˆFr´Cyáqä§Oj*4	\n:€ê+  yQÂð9×»oaó¿ìo×²Tâ¾_`{]¦Tžþ†±üé×Mí<ô„Xãâ:&(èW„ãpØóÔy\Z§íçEÜ7ûTœ+Vûg˜7õ®Ë•llŒ¼\nòÁ*ãkŠ¢f‘Ú²cÞâ¶žûû—4“ØR¶ ŠøïªXÂö+¼áfv_Êš£:\"HO}—*dJ`\nò3—ï·½7ðEÄ[ªËêe¾8\"§CIk¡Ã¼Z)‘ií­ç¾(Øuµ»öÂ¦Wtw€ñÎÎ×\r¥:,œ¼¢ÑVà \Z%¼Éç®˜Â”•­¹+ï\ZÜ¹z/W¥ƒlŒ·Š­maÏ~jÂ;íQºû!DKKiÅ?ŒødÚùhŠ°ñu²ƒ{/Fëýß¦!Y÷tÜ3¶¤õ³ûS©¯D¸ËÛÆ@ÑògsB“º®{‡OMz€¾`Ú+?©Úê;Y+ìD>ë3	î	Üg–hÜxvžËe&}-»NxäØ„ÞbÉ¾ëvÐ/™|Vend¥W¿cxÏ£3O…[øz4Ã®N%£xzI¸èáç ~s‹h›ƒ°Ñ{\Z‰Ân36mþŽ1æ[a8QôÁ…l	Áì6k]#Œ?ììÙóšM\"8óif/Ú@Û­LØ^ÂOô¸+|i‡yÎŒ•z¿à’œ#yäÃ–+ö³¹AúK\r’:¥ë=5Xº¬;k<cÃÌŠ/BNíGßØðÛ¯>>û\\ srèhf2~w-]Íí:îR|cwÇ4F&,¬ÏÏ`+ˆ˜Láð”dÿk÷˜í…oÛÑÞzG³¿Fˆ…Ñg ã5ÐÑøoéì.Jô›C(L­#Ó]ì¬$#\'o.áíP;Š–XkR}ùôÜ:†Ý(‘göý—µ›0Ëë¢SãBâ~ŒDú¶ÿ÷µÛ	ŒEI;<Ì9’ØÒ‡¤ªÓ¨V$1@ëÌªTNóÆÕiòWˆ#\'Ê­.j©}~¨•‰MSž×hâ1H™K\\[w ±<mž;B©Úäu\rB ÌÞÚ\rmë+82;²r×­\0tŠÿÆvãNÕ0 <;ûtÝv½\"v:ãÎQ_7ioÜ¹z,¸°÷€¾c½r½æFnª|–øOà¶Î[Z«8–k@j\0Ó§¶ËíPŒæ`Þ½5L!W°Im|g/\rÅÔäìjK§žåAÚ<@©\Zd*06äð¥ì»cÔþðZÿÎÏ@ýØ	JäzG®ÞôŽ½ô)S¼e¬4åéÑd!‚6—g;ôÙ*9ÓwU$V³ä¶ƒ.àç¹JHF9Q:ª¿¥õ§ðœ}MÑâÝ\nré3ù:Bte‹™MÔÉŽÉâ›UïÀ‘À#EÒ…eÔj\r‡b+*{²xÊé\n×aV3~Ã±Îk<”æR%š†Ð$Bm<….),‚ö1\"R&—ú8æXúÐ²Ýò‘¦(	_†€Ï½êä®è†Þv¯mÖ­å0ÿóÝK¾”ÝžÐwê5VÉK¸ƒw73tÔ	ÇQU±²óœb–ŸÜ#\Z7†ž‘Q¥öyPn:o¨òeÒöHÇ 1šËéÉ&Ë{íö‹]	Â‘¯eéãMgñÊ~zÎüØ¥¥ºµ{‹	Ÿ©Zh–P(ó¯e|±Vºž×ÑÕ„¼c8*‹§ádÊ`7\rOJ!G)ÉÂ)­djX•§S×ckÃð{`sÏ–åJóä©Ûƒý3ò‰ª¤$4öÑÇ’lH@Á½Ÿ4¤<‚{‰Ue²¶ìjÛÃœ‡Æ§UóÕÆÕËZµšˆH¤q¤*V94)†X	Õ\n¹ŒÛ ¼‘5‡ý}Âu †\rRl‰ÇÈÏóÔ!!{‘PZO¾•U‰¶œ¢´=Å½9U\\eÍ%gÿ§‡¼?Š·r`°Iü4TvwÙÆø§kX,{˜\0nMÄeÝo®ŠXÑ\\üT+$dôÒ0l:nsµœÚï­Ä¯¥-4W”×k^EÊM®wÇî\n{œ °PA^`&Ãí%l\nÁ¶¹9cÇµ«™ @AiPZž°,ëlRÜOdK´«ï¶¬gƒe6€Ùš#îÞwÕ#ßàß®°ÔÇ*¦%\\6°69ö½yÇ9¤åCYg’ƒëP‡vvƒÀqËŸFT*)3Àk&\\Ÿý>ãkÑ¸a´žúiø~ìµtGýœ¿ ƒ‡b\n—÷ßzÏòd=xgËOžÂuÒ*ž!Ìy€7zÛÎ\n.W0ù6sYFIòÝØ1ÄòéRýÄDX&bt\'1È¯õÕÓ;Æl¤€x´\Z‡S›uÀìMÙW`¿˜¢OpŽæöx7”ÜªœÙÝ8)ú G–Ã×¾\"Ü¡U‡ãuÍ<§œÊå6}žÿ\"0¿†ºÚåýíØ1s½\0nu¨­´Èm1oB÷Bî\\‰€Om [ô§ÅBPæ$¤Úô©¿=>J7 ³Û„9¶ñß+Óóß}äƒ_%ìñÒ¬1ìÜO«jäV=€YsØãƒžxM|YÒü˜½YÍí‡^[A$Êè)Ñ!X§÷èlyê“LtÑQn)¶ÊúýºŸÙÜÅ³W:‚±ÀóY…‹4Î\0#`Ù·_GjÚêß•u²œ—šœö\\=n°¨¦\'í˜\Z»Íøë0¹><F(¼¿˜)4å¾‰=ù\\^fï\'´ìþ-}î¨ä³_HÇùZ÷j³\nP>+ßŒ’r¡Ù%kÅ+þ:Dœ.~uD—,8¥²áÕÜd\Z¿´rú‚Tmt%Žï¾§¯J×,\"Óœ©Ôä2`OzÙÄÃÿœ%6T©Åo\0vÚˆ\'+tëÐv\"\\#äÀnjUuæ×:±ÛõˆwJ¢eð=ÓÜ³E‰ ¼«†jW¼ì ýn…¨¢¡zJˆö1Ó«#eqõ¾WÔªc3®] [6ü9]\Zã;/Úæ‡‰Àóƒ2à¦<B”G@¶9º7:Rä7‡>\r¶-¹šïc•Y•ïJÃºBÌXŽhÑX\nÞ\0œÎ?„°\\™ö\n|A:¼”çœý/ÊåŸ•U¾FKZºGbw\0ª7Þ\0!°©ZÞ=×‡-<2x	j3ÙŒïö’©È¯‡\'÷®®ªqžáLœÆ*zq‰ÈØ.PåV2•Vß‡Aöóô4vëwºZÛ#äüûÐa›Zó–\ZåÏGZÿ‰Tµå®[wê€Ò0Ä<SgeZÎÓA+g²*U)öÈ€Ì]a…—ž¨ã4’vwgMÓ¶s¸Í«†!6à©8¦`’±ƒÓúb4Üû•×ž6ÆZìàÌýspÓ0ñj×•H]Š…»”ì9-×l¬ÈeÑ˜§ª„Ý”tmP·’~\ZøÏŽ„’˜aIZ³[×BÕ½¬;!\0d§óE2põãsûsyñüïçmëàå™å”ˆ²“Øæ@!MZ®ÚßÍ…\'4A\ZA5æòRÍ—m$P¬\\k\n”½8§²}#—+€@•vßÂ@ìZ@ñ_ÔŸv4ßÇoBÞµß£ÿ4Á\"ë/}»ƒH_v«ÆîÜ4ÎfìË\"Éí‰ kØ¿7ÍKœécóØ5úû°¬el0ÿ¿®eo{ä¸–±ÿË°ð¸ÐíÄªeÄb÷°I—¸­¦£q®vBXFpmˆ´ø`wŠF¢5ã›[»Š1\rÒ¹Ï²q°ÑôˆøÙLÀÒvFTÝùÅCÁÈÛž¿5½²óKþNY±ÎPÞŒ²¤6@WÓü5äÜäÝF>53­6÷’‚›p«ƒjúlè_q¥u}0>ÝkîºëgÊ±u è‹èé«ˆ©(×SbèÊ±Âzš«ŠÖ’ÛÒØ\'Ásœw7\"÷®g<\\ö!˜¾wlÌ¢+ENZØQ¼þ‰vŽ4ÃJ4\"õ\Zâ˜¸×/‚Ã‚ 5!5/ž»ÿ{5ªx¿°~w4WjnÈ4ÏÅ¦,¥Ä}ýùµ\nqNûfä,ãdËÌÍÑÍXÊ¸RûáÕEêäÇ3¬õAœ±ŸÄ£òñ­`l´×ÈGËkh½£¼Í™kµ)Žm°Ò šf¡òŸ_AG¹tóáÃâÉç-»Œª•v‰\0/×íÕè¥£90÷Á)É\Z>.±ó’ê¯JmÃ÷&‚øó#ÔËé¿Ú P#n‘%Z›¢Û¾Hw`„(~’nû¿ëÝF…ôöäI”pŒŸÄ“ÔO7Ä‹ì wÂÏžÀ—¨®­Ã]To£ÃFÃõÂR©¦?g°Ðæî­¹`–FgV\\lœÈë.Ô¤6¶7|kcê„Ï±½ î0g ˜zÆnYú¿Ïz®\\:|Å\ZhúIXhÜ4yÃ†2k´ÙŽÇ!Qûá¸ÉÌŠI5?è4¼Ú3Í‡sƒ	Ó“bÙæ¯úm%üà™ÖÜÌŸA¾oº<]¡ÉsÏ\ZíDn½hø‚ªÏ»¥œ¸hÎT„,†‰62ÔdBã¹lŒ%;‡Sâ\nÔ}×§ö™¾m\Z±˜§ë½_¯þæº%ÕÂ\nËE)R¥8p+’”ååhÓÁÈ¨‡TŽeV@¢ÅØd¿	Ã|¡váóÜlèx®mžBü 5â·4âàˆÉ½ˆtÓ®™JŸèÁÚÅMþáš5ž6ÿnAÚ‘.· _\næTWp3£ÓTúûV€“—Â“a çŠL%I¡X`U”î’ÇËîãíñjëFH¼6Qz\Z»Ì­’bI&Ð#Þnià	àå/{bŠp	ÊœIO¯šƒò-E\\±Bô‘?ŒáÃŽ]r0™ÐŠž=Òo-«•ä¾Q‚4ˆ¬•-®$‡Á•p¯gHy#_`ï¥qË0ÂÈí„ºæÉ:bë\\Õ\'v$þ+c£5ISû+R9\Z?ä+tÈçL-«7ñÑ³ÃõO:+ª›–«ëâ/ø|_°„ 	®õ-6Ï0k¥SM°ƒQ@\ZÇìf\\Zð^\"ÊÓW“‚_‰ýŠ\\nZÜÎÅ‹EÏYDx¤zÂ§§„„A>d]<À¶\rÑÛ÷Õ‰äv4Ø¸EƒÌg\'†ÕÛùž&Ïó^úcS.x í’]‡t/ÁŠw%.Ttj¶H×¤ßÝ‡Hë„¥2Žÿwò¿AGX2,Û/BªTv\ròŠ&AR“®4‹>€1Oƒ´Fo\n‘û‡„½[¾Q(yÊ[s@•²u}ý¢SZGþ	3^;LÌàóð’¶«{P´ˆ-Œ3Å£l>%H—Ø¨=Ô\n¸…ž	Á—Ñ,	y\'šôÛC–}ðéLÛùA†Êì›Ù#fákÚŸzåÐN‚¾cÏW½æA‰M¸ÑBdI‘ë ÔùáûQÐˆìTž¨4Ü¢´ø£-µ—lâÇ:Ru´Ñ¡U,FñìÆòùSëYÔ>]07p_»Ðì‚6—­=_Ó\'ß‡\n_úJu?¦^;|t³‹s$\"è»éÍš)ò¨ÛÚBŸêþ+¢Fç{5Bíêadó|±!/ÐZA­v³#cÔÒŽhmw9N´uã[DØÄ0¤ÚüY[×\'°ªV¾OÁ(ã0ñÍcOž/Éºá*À?Umwkm²€	¥o™êOýŠ:®ëÜŠ¿’^Å§>öÇk ât¤mn*ä×9ü\n…Pg„	Fùñìâ‚¨~²–6h:NZPÍ•P8«¾Òvd”çáò>æ©Ó‰žçY>Š±}3¡Üò,>Æ sÊcJ\\-é´ÔªAdMf.¥Á\'•6ÃÔ·P.DŽÒŒ²Ä/.!ö]¤n‘‹0äpîŠ¢8ÏÐ¤E¿ðdQ,ÅÃŒ(sÏûw^\\?²ë\0œÑícÉ+ÜÕXn#ëo“Kró)õt&Õ5ê(1wý[ÏÏ!¤ÚáT~¾°)³×ÖU®~» ÜÑ×ü;yaÞö`uV‘Ž6Êc*ð aóvŸ	¡ˆ:ÅYtåP‹ÔX©\nß°BA‚¤÷±Ü*æ’ß±™m^a[18]\'êCÅ½DY±‚L.PIà	äô¹Ãª92iÓ1Wôáã\Z–WTe²™kP(}HøÞëi²â!©÷h¡§½ø5ä½ùrÓs{ìÈ•˜¿!žÌO4ù±ÞÓì! ª\\6“èÏZ:¨ô–üFGýìæ8|H\0äX)ÔñÙáÿ×n[Ðˆ4ô\\vÜÃ:èw=N‚ðú8V¦êé›YºÆ	šHË^xðFQPÁOuteÖxøÎ¢u ˆ–/åp¥D²»Ý76CSOÚHþåËÊº¥xãó\'í„^ZÀ¤à„Ó¸K÷sHªÀðÐ¾LÒAXÞIM–\rÜÓkzˆi9çs‹aHåŸ¯^²t¤)ƒÈjÜF\"èÇÔ0	ÓyåˆJ•½Z’âÃ†º ivÞI1\n×¾O§\\-x~„\ZÕF¤MKµ“h@·òOq¨Ê»¯ÌKõ°K4öªIsgS·>pkèÏLý-\nS½Âê<V…Z–ú»½ÏþìMƒ!«5ºÑßók¹™î}©Bbõ›«\néK)‘ª8qâÅË&ªwÙôäà1CYë<\Z”ÿ^}ïÉ°pù‚âÒ2½à‹Ešyãœô©Î`ZMgíí¼¹ýsÐÜƒw™rûË’\r´²0}7^âÃ\0LÃÖ,Í2îWè…qÊÝ-Z?-^Ëèfp(\rÝ÷‡#¯‰ŠÔü—£ûîèxwî)©\"\ZEÂ™Sûpû$ÑQ8ÞO¢r6%û&·_-¨¦>{9¬WP\0\ZÂpÿ…MÂþ7Oý$D‰ÙÂikŽåv÷È$ÈÑÇÀNë_€KŽ@Æwþ„{x†ýz­øš¿Ôýiü:ø¤ó¡ªý×ÏÀz;	f69	4Š[D}ÐÍ€üÙ ÿu/›å”5ëD—CÿXIÆG©HŠ½»kCYÒ\ZòÏt©ÌïÞÙ€M¶µ¡Ž@’‚CJê±/HF²àÃ„‹À›€\r8ôÉO0a°‹7òµBIBhâi”³ì\'îÖø\'à©8¿›ÌÅóy0þÁöµ«Ý»ˆ·!s@*Ž!ën÷Bj9+6ûd‹ä¬®ZªÎ´WšÄÄ8…ä‰Ž8ÜL™Ó6©€Òîˆv :&)¾%ØX`›r¹ñ?Áx1¸:XÉ ƒÊ¢£–@û Ûçü[ÐDWM©ðFg;?\r \ZŒR5¤è´×çó—TpÅÅö=Ýêü+…3­ÜóÍV ™·®’(ãø³É@·O½obrVKcÐÃª~\n=t\0^Õ64Zïî\'¡y™7mi}š7ô\rj&äd9˜6J›YDFž©özmšÐ†A,+–çöÞÆËE^ù\0%-ŠLùà©°¡_J(ø0ô½Õ§u%Ur¸ßÅoö$ÅØö=ü¸¾SŸQÞ^€Šq¼Í¯×„‘¥¿¸O,ÌfD*xÃ:S¶È%Þ \0È%}ÄÃp@¨ûª¬ÆrÌýcÑ\"Ì·”~2Ì¾/dÁP„¶„æ=Àš«Q§öHá`· ç\0\rm+±ÔØÆÊ+\Z8›\'fôþíoLæ!¶l6£Êý~¶`¹FÆÇj<üsó\"üÛú:Ü„ö¥=Ð‘-öÆ5£Í.ˆjëžjÉùúç©+vÉ=-ÅW\"ªÒ®²Æï¦=;|ßÑ‹tê,“CGß{©€\Z‡hnàHk6”d‰²ixœ&JRåA\"`ã\ZÄõµ%jB÷ûÑ`ÇFÀI=VïÚ£f™wÛ\n¦VÔáHCqü˜ÕœDP¶K–ð¿Åúœ+žnƒ“éžñÈ3õÒTÕ\'7ÉbÂÐØØ“[V5P\':ÎÆøj^ö¡¿q~šc !Ýçóoãår=Dµ¿Ð}—½Nç\rvÀæ|é‹õÖHËy¡ÉÃÙÑ?¸‰\r™Rð4*ÙÁß9¦?àÄ§>RlYgã‰¹®\02\"‹zDÉ„3\r_@:¢Î„væNÜäÂ8w›º\'´ÊÀ}’V´‘”ð¿„‘ë?ín6|	&Â->FÑCJ*5ÏC©P\'ÕÊù0èõÛc2ÄQ›Ü4„QÅf¸2\'¼¶•mçþ?‰Å9¦ô+Î*évõN’ÿ\"+ØkõÀí¯Zïùñ‘½\Z–%šÿSpí-}ÄÜ]éDR‰¡CÔ¥œv¤¸hîuì.¤vGÂóÇµL.ËÐò~ü§D8èë Ê­@ gògÉ%ýÊ æM‡»´ºŠ‘ª(Ê¢Ô)yþÆ‡°ouí„¯½b—Qôr§\0÷7¢s§´DKW½%±rAtÔ<ëK?)ôŸ“µŽñ V‡£º`¿Rî9ß\\)èÐ-¦I›â\n÷EV=œoÂ•õ>œÿë7y‚¥vŽÕ\0_Øí¦~Ôs6ªcŠ‹§íy×2¥ô‡_Ðp~BÛìæ§à\\Zs_¹â®(qóÕÏYçšìgAŠ^¨J51Ào|ªÀ…å‹Y·ûI)•@TôêïôV[üN4ÎùPdôhäî6«º»Vß\n&Ý>Ë©”¸3a\'{i	È™¬ÔwË_-ŒR‹‡XË°+%(t¿ßÜ\'^•EZ:g’±c8ª1RNùhqøL—HokÐŽˆøeÖ¦	3Ù¼Ûvû	ÏOÀáÚ¦ÕaEh%ÒÙ§JFNÊ¿¨Ñÿm³‘‡ŽB6Î!w$¸§„ª_Ò®…ø_xvCÝ„yãöpÊK9®Nìéã.Y\\îÎ6¡›BåéËRdXÏmrE€é‹‹Ÿ¸X8h¦‰Ç¸IÏ+“ž˜ªäƒ_JAå0L°¹íÑ²ôÓÅwÆ­¢Z+F –¬žbãXžhz{Bèæ[“ Èð«ý«hTÃÊÕ·1:NðJtÛ…„Û\r%J‹­–ñí|ó€ÛÊ¨×•½,mà—²†ÞpãzxyØŠ\\Ç£§ìôXÝ…dáôáÄu¢½é2ê|\\ù¨°J›‰°“0º²çÞì(¿#ÐVmuhŒly‡šJŸˆPf¶%!»€{cr²ÂNfKnÝH]‹ÔE[ã`]ão#*,/â&0`HÓFÿ±FPÅŸBœV2J”]Nc^â7Dp†ÔºuŠ½Â\0óŸK\n¡ïŽÝ}™¬øvý?^ë[çü‚7¯³é ³3~Ù7hgÖlÓ™žôõÞ[q-/ÛÑ	Õûˆ¯Ò‹I0ïjôL7YÕÖèÎ\rR^ùç~ÎO%Çœ¾†iÑ?PEšÅâJ!\\æQ¾«PÞ\04°Ç‰ý²VpûÒYãºáÁ1”gö¥<“éŠö­®Z©nÁZV•&¿JŽË‹‚Î¢]IZFQy‚Õ(‘\"K40\',0aX+µ¯+v¢T|“\\çZ(¨×´4dSœÂ;ö¢7E|j8€šÄ1tÚl?óŽ¨+uº?vŠ‡†Ÿ9KOÊ(/ì˜ÊÀ´ÏÇ´_\"Œ©¢ÚûªZª*¶C–óŠ›†Éº;»¬¯=à|v±\0Š’Ñ@Ó~ª‡í´êþm±lÄŒýêæM†MáPcZ‘ ¢ö{\r­ÎD€ýV‰IW0ÉžZj­“Î,ïÞ\\ªõ¢:µ_Ã×†sàù¶]\r\"Ú­‡¼i9<ÏÊ¦ž1Ñ{U-‹åÌaÿxDû$ëÙKë\":è3Q>AÏUBÌ‚òæ^í¢&hÑ\0Q6ÈPA4ÜRãðˆýMÇDÍÑ…X[Ž£¿§ËËÅ\rfmÌy\')#fJ•w’â°)Äg	˜=ÄÕÈlûÇDÒ+~¤4¿¨#É&¼ßO·3ºQ)nC:«âˆþýöËxÄ¯=Aµ¿qÌƒMo€0nä*µº=Uè¸¼ R\\ nf)ÉÙnZœHqpŠjb4 T†Ø5åÂ2Òf¦B±õ¨y\r‚zDixe¬\nputƒÕ#«(iéf«\Z<ºÖgâ½0°ÂUïv\'ŽI;ævb‡z‹±fy)¬EAÃÈÅ4,K¤u&0=kØÝÔ-û+vÏMQ	ÏƒÚ0+°WhvYM2×Ø3q‘¼½3Ä#nz}åé€3_?ƒ/2ZAlxßáüÐ]„xš¶{T3»6¬±JìÉ\Z75·ãÏôVQ)wŒÿ—¨×÷Öj „ýà¡ið|ÍDÿXm÷˜/Ó:ˆæƒõêd@g*}\\ÖØî7-¡²\n0ÃiÜ]Ðô¤ét½o7·ÂrPüÏÜõ ×X¡(ôK¼2èrÁò3¦æšõÖ£–¢‚MmªŸæQêŒ±Öæ¤¢êÏÞ8(Í1VÏêÝ}núlñ}\rŠ˜˜ÏâÂ’¨h©ÆLÞàH²E|½ ôX¸ÆcÉøbvínTfLWž5Æ§Á^ŠœeµÝYäŸG/º ‚CYHÒªêGÚú‰ÖšvŠ½–0NŠùÇÒâ\\T]¨zªU7','ÿØÿà\0JFIF\0\0\0\0\0\0ÿÛ\0C\0	\n\n			\n\n		\r\r\nÿÛ\0C	ÿÀ\0\0d\0d\"\0ÿÄ\0\0\0\0\0\0\0\0\0\0\0\0\0\0	ÿÄ\0I\0\n	\0\0\0\0!1A\"QaqÑ‘#2BR‚’±ÁÒ$3rsƒ“Ââ4áðCDETUb¢²ñÿÄ\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÄ\02\0\0\0\0\0\0\0\0!1AQaq\"#2‘¡ÑÁ3Bb±ñÿÚ\0\0\0?\0\Zý2¹AÈ_Ø?=kúuXÎPîFùéÍ­ïÿ\0™©û“ùuÏ%¼©?Ó\'øÔÿ\0\r&6`SÞì.#p©¬_ýµŒ—Z1¼üµÔÖðñý(¾Zæ–€÷ïç|õ ”sX1­/w˜|:uÉ?Ë®i®¬èTÄ¸#[ýºíú¦¥©ÅOŽÂnA)ù{g÷kS[ôkœ\0ûÁêÖÂVkíÃº&&0±ë\nÖ-zR›¸§q§Õ²—êµP7Ãÿ\0ÝxÖIzúÑýúÜLÞ«íÓÇ$È«6N1ä~zØÈI~õ?ÇN«g™@‹öÈþ\Zô[fÏEˆÿ\0‰þÝdÌ9‘M<œØõfø`|õ‹@B’gû½<u@íGõ¿å¬‚§Õ/Ù¬¶aÕg`¦fŒG„çü#òÒÓ©¢ªÏH3öæÒÖûàµØ(‡ô:Doóç­QÑ“$ãüWùéõhiÛ?Šƒ©ý!ó×<öÌR±ýî?Ž—DŠÖ‡TÍôZ,cé3¯¿Äcûõ­¨`šdŠ\Z™ÙÝ‚ªŽ¥‰ì\0ÇS§ª×þÙœõjcÃ±¸½ÚZwšu”RQÄXÈL¤u*	=p@Ï[X– ÆÒî|•šJQS(a8Ièãgìë\\û%-û²*e¨¦R:?¸˜eqÎ{9A8éÌXùë¼Ùí0b†ÕA\0FŒ2qŽŸõ×RKnÞ£Ž¦¶\rÑz¨¤z8c•©íôÞ;r@L0È#\n\0$\0ÄêOmáEÜë¡¹^î1˜â‰„\"Hÿ\0ì	åÏ&òòuíœã¦5EÖê¹ÆòML”d^­ô™Š,“×\0ÿ\0Ô1ŸoØîq¼W=²¥C²S‹€HõXdÓ¸8ÔBëÁ}¯=Â\nÊ(Þš˜95«–W\\É$‚§8éœc åï£-ã‡·ŠKªÓXnÕÒŒDí[…a;+:§:~JÕ|×Û¨åt”MCr¤–’¡LR’= Œ†ðHÕ9\"«¡vØÈiûa¦©·ÝY»È.°sÙW\rõµ©,›šªÝhA\"€Ðý\"T’~ÝîØ$Œœj:ms“ÿ\0uÿ\0$ÿ\0>ŽüU±GxÛoW\Z…©·~9—\'Ãÿ\0´uýQ É³Ôçþ%>Ôù\Z£¬ßE“Å-Ý(¼œå€äBàÊŸdX÷!Äë·Õ¯ä•iù:}]Z 4\'ûç­/I\\:„þ«ã«—<ÐÜ&ÓM\\;ûÿ\0Ù¥®™a®F#éïqütµ yê¾Â$%e³e¥Or5…D¶×¬Ô¤žÞ²üõ.¦·šÚˆéiQ§žg	Q\';ÈÇ°P:“£~ÂôeJ´ŠéÄBcC†[dåâÈ½¿²‡õ»Q¡ š½ØŒi×’¥S[}þ=Y’¦Û\ZøJ!™ÿ\0B5ç?n;}¸Óío7þÛ2Šl4tõ¥¢YG<ÕbIX!3,Q ·®udøëÀ­‹O²êwÕ¶QX+í±©E§ŒG\rY$(GQùä÷Éë‘Ùƒ„Ô´4[pC$(ihïÃ!q• 0öÀœôÆNŒTÐSÛå…¹ÚqÔç†4Cíµ—\ZÑTùZºÖœ’rN}04î«ÆÃâíïw[·ëfï‹ÕVæÚ”féAjs+y¦çš^IÔ£ª1æ1¯+’ùN jeÂßK=ó»í7Mÿ\0ºx}u–ßk1-Æ¶ÇEâRÈU0í/;f\"©ÊÞ®G·\0èÄ=v}5uÙ­”óXh¯¨a¹‹MkÒ,ñ’¼ŠyW=‰P2	‰Ì±ø³¬œ¾pfÄ¶KUÖ‚¢ŸÄbe),¨A™Ù3ŸÉÎOe\0cG&–\n‘‚Ì*>Zj7e²z æìô…Ûö~ÑÞ·æÅ½[¡ºUø´WjIÕËU©W8ÁGU<0Ã˜aq×S=ŸtÛ¼cáð©¶î(nµ9h®Q®\Z9D˜*@ Æ2¤„>ãªÝÄŽñ>>[8A5…wEâÑu–jÃVÂ‘ÐR·/40—å•‘ÏRŽ9TŽ„ôÁ£Ñ¯ƒ–Î­]ŒVÍW{’†›ë™bôÑTi9PvY#P{·#ƒ ¡v‚ÂL~˜ì¯[_[ÁÒ´ŒëŸÒf¹ÀóPÖRVÇÈü’Á4dç•€*ËöâZÓª¨ê ç‘zôøh½%ÒUî­$vúU]]Ty”xO#*XaŒg2tyâ\'£·w=n0ÉØ¸G\nÿ\0J§lëc)øŽV÷,Yí©ßnÊBmñ5Ñ”âÒ˜þ$•«TŒTËöÄ‡ý:ÓâVƒ6zùÂŸ-I7~Ñ¸íÄ¶z×i™rb¨¤¬ñàçFàõ²ÏP5äœ¸éÀü3ü5#ât.,xÁT3G;CãÔ¢HêÉñcF–·<2szÒÖgû+òÒÖ¹î¥Ïetø³6.ÆÛ–þ#n:»MÎûF“ÓIQ<q­5<ŠQI8.À‚Ä|A’cµßlwØÚkâ†á\Zœ3RÔ$ yRq¯˜ÛRá_[µ¬µõSÏ0·ÀŠÓHÎÊ*‚Äœ€`;jO··Eók]a½X.sÐÖÀAYb|d~‹Ì§ÍNA×Ve¥”ìÝÄtÎ³{F«-š/w8\':ý°­o¤mþw¯³mXd+\n£Üj\0?”AäŒvKŸŠ)÷©£Û×ý©n–SS;øÇ’u‘ZDH\"Rq’äuãí¤úâ~ƒ[DT,œ„Ä*©pñ“Ü0Q€{1ÁÏs1àÔµ¦ýI¶,ÉK%-ú›èÕ©4&P`äv2VR9Æs×˜÷8Ò%M<“]œù8\0>€®ñAr¥w‡¢òÇ;~þ~¤	Ò£}ñ‹fØ*l=³%]¾V–G­¦y¤Z1žY²8Ue ?qŸXã\ZÕ¶¸ñÃ=½müß6}Ýmª…c…¡•*Q*£S“™‚ƒÎFTŒ<¿\röÕÓo\\7L7K¼µæJøÞ2ÒHØOÏˆXç/CP{ôßáÿ\0·ÝUá7¦†àµêR¥$E1O\n€y½;0#\'#F¼‹ÙÌNÉïûAsŠz€éÙ€xìþŽŠ/¤_\r¼4ŠÑ´w\rÑâPˆÆ•\0\0væg~o·XUn¤»Û,bŠ÷¾j‹QÐÓ·4$Ê¶@a\n$à::êCuôj¡¬¡HìÛÞà\ZIPHÓEÆaÏ® \")$®TzÃÏ–Nô¦Ý|)±Y.?[SË~ªYi¯ÁIM4 )ðáæP«Ìz³ÂŒ\0 hI¢ªÙ.«Àh×DfJú-¦²ƒiÎÿ\0.\n!·ì\\;©²KMºwÍ]-Áež•¡¶D&†\0¬Qzˆœç\0¤| |Q‚ótŽ¿zKÄ*\rßi·8ê¢«DzRN<3JÌmÐú¨@\'ÛŒ¬³}\Z®áFï„•+Ó¯)^Sñë[]6¯DŽ(½4—‰¬váU[+U¼RÕÇâ©rH_5gýº±á‰~<¬kC\Z1õõú%¿iÔo¨¶Ó»™ïÚÐ;8üc<ª¶Š*°$\0G:Ç0_YËÚ<ô¨)ªjcj¡YÕ‘\n«þh÷§ÝÉµ·ÑºÉeÜ–¹¨+\"\0´RÕOfV«)Áê	õ–*•»ªÓaS$;Ž½R~Î‹øš‰“Óy¦qo>Ë˜û3½U[®†ÏU×ç\0çG\r#øNÐU†ÇÒ)û{ù´µË<7$“—èñvò—þZZçÛ=×¡2ÕœÇMtÐ¨Xâ@ˆ£É@À\ZuÛûzó¹î°Yl6Ù««j2R(†HP2ÌO’ÜŸ‡rf ®¥¹ÓÇYG0’)\0 Üuoý¶-Ô¸ïI¢V«¹Ôµ$.GU§‹\0ñ“Ÿ>ÞUök³Ï>î-¦ó^>ðõ”Ýî>^|€2]×N_t,°z0[·$–ËÆìÜê,ÔÂ:ÊÃ$´Ñ·šF“MÈÄç»ò\0À<Ç*KÝ¼Lá¯¢¶ÔmJD¤Žj¨)g¡¥¦d§¬¦ëÈ9]ÑW£©êp¬O‘Å6–ÝKŸÖËlŒÔó™$”IåH¨O*¹ó`>g©×eÂÙm»Ò½ÞßM[M\'G†¢‘â¬:\\t.|¦gW ­í§¶S²Ž6|&òÊTOm®µ~íÝÛ%=u,uHãIà¨B£‘‚0æç#\n°ÉÀ ê¶¥\r—šó»©ïußZÔ‡yin+A4­…IAxãêJ¨eÀÎ‰×Ä[ƒƒ±ÇPûy)mt•>%–ÙlI!§«¨u\'ñ˜cr0‘ÈäïÈÀÕfâ·Z¢Å}«Ž†wVs¼4èåG(>¶[$‘ðÄPÏ;¾€ 7oÛld2§%Îä0HO\0®öÇ¢Ú´¦hlS\\b©ŒQI_4ÂxóÐŽCœ&Æ: ÿ\0¤®á†³vZöÌN¤Û©ZWÕŒÓ°\n€w-Ëp:úã@â·V\nJvâ\réÅ~\r;ø¨²¢`¾*¨vÈ<Ìr@=ÆuîÔâ>íÚÛÁwµ%ÞzË£\0“É_#T\ZˆûxlÎKc\0‚òöjj›\\µqAi}¤[¢¨i»N4ïÍ8%èÿ\0~¾«~o„z{{ˆZ†Ù(ÄŽè¯\"þjäÊz’:€2\ZËò‘ÑGM0l=çlâÖ Ý6¿R:¸ÿ\09he3ï#>cÏOã›¾©ALÊVì´cªz–¹Õø™ÎÈÆ0uÑý\'v=.åáåFáJu\r¼>•ƒ¹ƒ J„û1ë|ST6¯uPÐîû}™åˆ®fvlrdQ«ýé!¾-ûO„›¥$•M]MªxcLTÈ¼›ían¾lðúß%Î÷6â¯ª’C>2Äç¼Ÿ´j½Sa tnþí*;šñ7\rÜ“ß€ü\"UMÎÖe$\\é±6=-sÔ­#LOAö\r-sÍZÂißÛVïÃü—KDFk%có˜ÔTÏqîÿ\0£×®.ÿ\0¢ñ°î>[é-õH*hêj£žÞ¸f™¤U×¶w+\\Wj)(+^’Xd*Ô­÷_¡ÔggÚ·_®S×í	£¹ZªdÏ@3ˆãóãn 6>ÿ\0gžXüNb–´è8ÚH«ðœPW:åF0ç8uç‘Ý}	É¶¼#ÏUÃfzOÛë™(*î‘EV0¦–èžÁ½º?tU£âÕ¦eºß<ÛîÓ”e³7j\"ì©?,8xÁBŸL›¤‰K¶lÁ¿+TÕ:ûY*Ÿ¹ÛUiŽN^˜¿oWK¶nÖ:Â‰SNåâaÊä£(íæ}Ú­Ÿ…ûsÿ\0SOØo–ŒÒ82 \nâ4Šinïpi#dñ¯Tàê?6øÛ±V©ä#Éc?Ç\Zd¹ñ.(Z\ZP3Ù¦=OÁGsöêWÔ1š“¢\\‚ÙURðÈØIW»ÐúäÉ´75UB­=5Á%Rí€…â\0wäöèƒ¼ø·k³ROõuLJ±!2ÖJÁbˆ0OÝñÕ\'Ø¼fgì˜­Ú*ëíîá+VTC\n˜ ŠF\0*¶pXªªƒ€zç¯žœ/Ü7½ªŽ¿{™“Ã<–ÚV\nøÍÍâ9—©ê¾ÐN•«.Ô°¸àOL¯DØ­pÐÃ ‚\Z\\ÜTß·>3TI·¬³ÏgV×Ê„µ[)Ê€ü@ ¤ã8¶ƒoÅk¥ŽŠŽ¡Dq(h“ï\'\'R*j»Ñí´´–øAŠÄ`,Ž\0ƒÌÙ$v<Ã=þ>]é-öœMp©­§i£q-—Èå?«ú¸L÷êÃ=ˆÒEuTÕÒåï‚¢¤°åŒ$s8Qùíóx‡ñ°¿=-j¼]­tÕJ–Ê›df0Í#RÉ\'+ƒOLu÷éj®æAÍZ%ZßL	ÀaúÇZ†2\ZNÿ\0¥¥¥ \ngpMw=»hº§‡q¤IÇ`\\@÷ãQ;ý²¯fP\Z½³¸ïT:¬	VL#·æF––Š[æ’7‡èU\Z¨Øñï\0Pïzo½Û»¬­`ÜƒWN$IQ<+\"8óy>èv,éœ}6§ÿ\0‡òéiiÚ:‰‹uyû”›[EM$ ¾6ŸPu»nÐÔNy*yÝöþÁÚðD*ZÜ%l~{Ÿ.	ÒÒÐ;”ÒœåÇîŽ[©`‹úlÐ\0§tŽß†‚ÙIN˜í\ZŸ^ºê’vd\'•GÃ?=--.ç\'TÀ@DÕRKµ\nH¥$\n®/CAé“çç¬o·\ZÈè­ôm/ˆ“ÝÒfTTf\0°œœiikvŠ-.–×ã¡ÿ\0arË|çÔ_Ù\ZZZZ˜º¿ÿÙ','12345678901'),('t37','123456','æµ‹è¯•t37',NULL,NULL,NULL,-1,'testFeature','icon',NULL),('t38','123456','æµ‹è¯•t38',NULL,NULL,'ggb',-1,'testFeature','icon',NULL),('t40','123456','æµ‹è¯•t40',NULL,NULL,NULL,-1,'testFeature','icon',NULL),('t57','123456','æµ‹è¯•t57',NULL,NULL,NULL,-1,'testFeature','icon',NULL),('t58','123456','æµ‹è¯•t58',NULL,NULL,NULL,-1,'testFeature','icon',NULL),('t68','123456','æµ‹è¯•t68',NULL,NULL,NULL,-1,'testFeature','icon',NULL),('t69','123456','æµ‹è¯•t69',NULL,NULL,NULL,-1,'testFeature','icon',NULL),('t80','123456','æµ‹è¯•t80',NULL,NULL,NULL,-1,'testFeature','icon',NULL),('t82','123456','æµ‹è¯•t82',NULL,NULL,NULL,-1,'testFeature','icon',NULL),('t83','123456','æµ‹è¯•t83',NULL,NULL,'ggb',-1,'testFeature','icon',NULL),('t91','123456','æµ‹è¯•t91',NULL,NULL,'ggb',-1,'testFeature','icon',NULL),('user01','123456','111',NULL,NULL,NULL,0,NULL,NULL,NULL);

/*Table structure for table `pg_join` */

DROP TABLE IF EXISTS `pg_join`;

CREATE TABLE `pg_join` (
  `pId` char(6) NOT NULL,
  `gId` char(20) NOT NULL,
  PRIMARY KEY (`pId`,`gId`),
  KEY `fk_pg_gId` (`gId`),
  CONSTRAINT `fk_pg_gId` FOREIGN KEY (`gId`) REFERENCES `group` (`gId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_pg_pId` FOREIGN KEY (`pId`) REFERENCES `person` (`pId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `pg_join` */

insert  into `pg_join`(`pId`,`gId`) values ('t15','é¡¹ç›®Aç»„'),('t38','é¡¹ç›®Aç»„');

/*Table structure for table `pm_attend` */

DROP TABLE IF EXISTS `pm_attend`;

CREATE TABLE `pm_attend` (
  `pId` char(6) NOT NULL,
  `mNo` int(6) NOT NULL,
  `state` enum('canceled','checkedin','noshow') NOT NULL DEFAULT 'noshow',
  PRIMARY KEY (`pId`,`mNo`),
  KEY `fk_PMa_mno` (`mNo`),
  CONSTRAINT `fk_PMa_mno` FOREIGN KEY (`mNo`) REFERENCES `meeting` (`mNo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_PMa_pid` FOREIGN KEY (`pId`) REFERENCES `person` (`pId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `pm_attend` */

insert  into `pm_attend`(`pId`,`mNo`,`state`) values ('t15',2,'noshow'),('t15',3,'noshow'),('t15',4,'noshow'),('t37',2,'noshow'),('t38',2,'noshow'),('t40',2,'noshow');

/*Table structure for table `pm_inform` */

DROP TABLE IF EXISTS `pm_inform`;

CREATE TABLE `pm_inform` (
  `pId` char(6) NOT NULL,
  `mNo` int(6) NOT NULL,
  PRIMARY KEY (`pId`,`mNo`),
  KEY `fk_PMi_mno` (`mNo`),
  CONSTRAINT `fk_PMi_mno` FOREIGN KEY (`mNo`) REFERENCES `meeting` (`mNo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_PMi_pid` FOREIGN KEY (`pId`) REFERENCES `person` (`pId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `pm_inform` */

/*Table structure for table `room` */

DROP TABLE IF EXISTS `room`;

CREATE TABLE `room` (
  `rId` char(6) NOT NULL,
  `rName` varchar(10) DEFAULT NULL,
  `rInfo` varchar(10) DEFAULT NULL,
  `rSize` int(4) NOT NULL,
  `rAddr` varchar(10) DEFAULT NULL,
  `tName` varchar(20) DEFAULT 'default',
  PRIMARY KEY (`rId`),
  KEY `fk_R_tName` (`tName`),
  CONSTRAINT `fk_R_tName` FOREIGN KEY (`tName`) REFERENCES `room_type` (`tName`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `room` */

insert  into `room`(`rId`,`rName`,`rInfo`,`rSize`,`rAddr`,`tName`) values ('A001','UPDATE','TEST',30,'3Floor','default'),('CR301',NULL,NULL,31,'3å±‚','default'),('CR302',NULL,NULL,30,'3å±‚','A'),('CR303',NULL,NULL,40,'3å±‚','default'),('CR304',NULL,NULL,40,'3å±‚','default'),('CR305',NULL,NULL,40,'3å±‚','default'),('CR306',NULL,NULL,20,'3å±‚','A'),('CR401',NULL,NULL,20,'4å±‚','A'),('CR402',NULL,NULL,20,'4å±‚','A'),('CR403',NULL,NULL,15,'4å±‚','A');

/*Table structure for table `room_type` */

DROP TABLE IF EXISTS `room_type`;

CREATE TABLE `room_type` (
  `tName` varchar(20) NOT NULL,
  `tInfo` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`tName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `room_type` */

insert  into `room_type`(`tName`,`tInfo`) values ('A','top class'),('default','é»˜è®¤ç±»åž‹');

/*Table structure for table `time_table` */

DROP TABLE IF EXISTS `time_table`;

CREATE TABLE `time_table` (
  `count_point` time NOT NULL,
  PRIMARY KEY (`count_point`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `time_table` */

insert  into `time_table`(`count_point`) values ('14:00:00'),('15:00:00'),('16:00:00'),('17:00:00');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
