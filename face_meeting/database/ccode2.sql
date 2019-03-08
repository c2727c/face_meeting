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

insert  into `group`(`gId`,`gInfo`) values ('项目A组','特别特别特比重要的项目');

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

insert  into `meeting`(`mNo`,`mTitle`,`mInfo`,`mSize`,`mSpan`,`tName`,`pId_FQ`,`mState`,`mCreateTime`) values (2,'test Meeting7','啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊',30,60,'default','t15','pass','2019-02-26 22:50:55'),(3,'test Meeting8','--',30,60,'default','t15','pass','2019-02-26 22:50:55'),(4,'qqq','qqqqqqqqqqqqqqqqqqq',20,45,'default','t37','pass','2019-03-05 12:38:10');

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

insert  into `message`(`messageId`,`owner`,`sender`,`receiver`,`mNo`,`title`,`content`,`sendTime`,`type`,`isRead`,`state`) values ('0debe937-8858-418c-b78b-2fcf19033780','t15','t15',NULL,2,'会议公告','xxxxxxxxxxx','2019-03-03 23:55:24','meeting',0,'none'),('0debe937-8858-418c-b78b-2fcf19033780','t37','t15','t37',2,'会议公告','xxxxxxxxxxx','2019-03-03 23:55:24','meeting',0,'none'),('0debe937-8858-418c-b78b-2fcf19033780','t38','t15','t38',2,'会议公告','xxxxxxxxxxx','2019-03-03 23:55:24','meeting',0,'none'),('0debe937-8858-418c-b78b-2fcf19033780','t40','t15','t40',2,'会议公告','xxxxxxxxxxx','2019-03-03 23:55:24','meeting',0,'none'),('1d8a2f0b-e421-49cc-94bd-da49b06386d5','t15','t15',NULL,2,'会议公告','xxxxxxxxxxx','2019-03-03 23:37:04','meeting',0,'none'),('1d8a2f0b-e421-49cc-94bd-da49b06386d5','t37','t15','t37',2,'会议公告','xxxxxxxxxxx','2019-03-03 23:37:04','meeting',0,'none'),('1d8a2f0b-e421-49cc-94bd-da49b06386d5','t38','t15','t38',2,'会议公告','xxxxxxxxxxx','2019-03-03 23:37:04','meeting',0,'none'),('1d8a2f0b-e421-49cc-94bd-da49b06386d5','t40','t15','t40',2,'会议公告','xxxxxxxxxxx','2019-03-03 23:37:04','meeting',0,'none'),('26171435-3163-4864-a890-ba3c3ef984c5','t15','t15',NULL,2,'会议公告','xxxxxxxxxxx','2019-03-03 23:46:14','meeting',0,'none'),('26171435-3163-4864-a890-ba3c3ef984c5','t37','t15','t37',2,'会议公告','xxxxxxxxxxx','2019-03-03 23:46:14','meeting',0,'none'),('26171435-3163-4864-a890-ba3c3ef984c5','t38','t15','t38',2,'会议公告','xxxxxxxxxxx','2019-03-03 23:46:14','meeting',0,'none'),('26171435-3163-4864-a890-ba3c3ef984c5','t40','t15','t40',2,'会议公告','xxxxxxxxxxx','2019-03-03 23:46:14','meeting',0,'none'),('27e5dc9f-8f90-45ba-b8ca-828e04f7a72e','t15','t15',NULL,2,'会议公告','xxxxxxxxxxx','2019-03-03 23:37:05','meeting',0,'none'),('27e5dc9f-8f90-45ba-b8ca-828e04f7a72e','t37','t15','t37',2,'会议公告','xxxxxxxxxxx','2019-03-03 23:37:05','meeting',0,'none'),('27e5dc9f-8f90-45ba-b8ca-828e04f7a72e','t38','t15','t38',2,'会议公告','xxxxxxxxxxx','2019-03-03 23:37:05','meeting',0,'none'),('27e5dc9f-8f90-45ba-b8ca-828e04f7a72e','t40','t15','t40',2,'会议公告','xxxxxxxxxxx','2019-03-03 23:37:05','meeting',0,'none'),('4dd364ef-ec19-4719-b144-2bc6b85f42e2','t15','t15',NULL,2,'会议公告','xxxxxxxxxxx','2019-03-03 23:40:05','meeting',0,'none'),('4dd364ef-ec19-4719-b144-2bc6b85f42e2','t37','t15','t37',2,'会议公告','xxxxxxxxxxx','2019-03-03 23:40:05','meeting',0,'none'),('4dd364ef-ec19-4719-b144-2bc6b85f42e2','t38','t15','t38',2,'会议公告','xxxxxxxxxxx','2019-03-03 23:40:05','meeting',0,'none'),('4dd364ef-ec19-4719-b144-2bc6b85f42e2','t40','t15','t40',2,'会议公告','xxxxxxxxxxx','2019-03-03 23:40:05','meeting',0,'none'),('59abc101-234c-40f2-8b7c-4a080bdaa618','t15','t15',NULL,2,'会议公告','xxxxxxxxxxx','2019-03-03 23:43:16','meeting',0,'none'),('59abc101-234c-40f2-8b7c-4a080bdaa618','t37','t15','t37',2,'会议公告','xxxxxxxxxxx','2019-03-03 23:43:16','meeting',0,'none'),('59abc101-234c-40f2-8b7c-4a080bdaa618','t38','t15','t38',2,'会议公告','xxxxxxxxxxx','2019-03-03 23:43:16','meeting',0,'none'),('59abc101-234c-40f2-8b7c-4a080bdaa618','t40','t15','t40',2,'会议公告','xxxxxxxxxxx','2019-03-03 23:43:16','meeting',0,'none'),('68679270-fac1-41e9-b390-f5625087d315','t15','t15',NULL,2,'会议公告','xxxxxxxxxxx','2019-03-03 23:54:26','meeting',0,'none'),('68679270-fac1-41e9-b390-f5625087d315','t37','t15','t37',2,'会议公告','xxxxxxxxxxx','2019-03-03 23:54:26','meeting',0,'none'),('68679270-fac1-41e9-b390-f5625087d315','t38','t15','t38',2,'会议公告','xxxxxxxxxxx','2019-03-03 23:54:26','meeting',0,'none'),('68679270-fac1-41e9-b390-f5625087d315','t40','t15','t40',2,'会议公告','xxxxxxxxxxx','2019-03-03 23:54:26','meeting',0,'none'),('6dab644a-f7e9-462f-8feb-ff1328400504','t15','t15',NULL,2,'会议公告','xxxxxxxxxxx','2019-03-03 23:54:06','meeting',0,'none'),('6dab644a-f7e9-462f-8feb-ff1328400504','t37','t15','t37',2,'会议公告','xxxxxxxxxxx','2019-03-03 23:54:06','meeting',0,'none'),('6dab644a-f7e9-462f-8feb-ff1328400504','t38','t15','t38',2,'会议公告','xxxxxxxxxxx','2019-03-03 23:54:06','meeting',0,'none'),('6dab644a-f7e9-462f-8feb-ff1328400504','t40','t15','t40',2,'会议公告','xxxxxxxxxxx','2019-03-03 23:54:06','meeting',0,'none'),('6e7221ea-b59d-424e-ad14-b6bb354a8aaa','t15','t15',NULL,2,'会议公告','xxxxxxxxxxx','2019-03-03 23:45:13','meeting',0,'none'),('6e7221ea-b59d-424e-ad14-b6bb354a8aaa','t37','t15','t37',2,'会议公告','xxxxxxxxxxx','2019-03-03 23:45:13','meeting',0,'none'),('6e7221ea-b59d-424e-ad14-b6bb354a8aaa','t38','t15','t38',2,'会议公告','xxxxxxxxxxx','2019-03-03 23:45:13','meeting',0,'none'),('6e7221ea-b59d-424e-ad14-b6bb354a8aaa','t40','t15','t40',2,'会议公告','xxxxxxxxxxx','2019-03-03 23:45:13','meeting',0,'none'),('95cdeb9a-d819-4920-97df-f9fc9c54bc22','t15','t15',NULL,2,'会议公告','xxxxxxxxxxx','2019-03-03 23:59:54','meeting',0,'none'),('95cdeb9a-d819-4920-97df-f9fc9c54bc22','t37','t15','t37',2,'会议公告','xxxxxxxxxxx','2019-03-03 23:59:54','meeting',0,'none'),('95cdeb9a-d819-4920-97df-f9fc9c54bc22','t38','t15','t38',2,'会议公告','xxxxxxxxxxx','2019-03-03 23:59:54','meeting',0,'none'),('95cdeb9a-d819-4920-97df-f9fc9c54bc22','t40','t15','t40',2,'会议公告','xxxxxxxxxxx','2019-03-03 23:59:54','meeting',0,'none'),('9b1dca73-2533-42c2-810f-cb3e02524d22','t15','t15',NULL,2,'会议公告','xxxxxxxxxxx','2019-03-03 23:42:05','meeting',0,'none'),('9b1dca73-2533-42c2-810f-cb3e02524d22','t37','t15','t37',2,'会议公告','xxxxxxxxxxx','2019-03-03 23:42:05','meeting',0,'none'),('9b1dca73-2533-42c2-810f-cb3e02524d22','t38','t15','t38',2,'会议公告','xxxxxxxxxxx','2019-03-03 23:42:05','meeting',0,'none'),('9b1dca73-2533-42c2-810f-cb3e02524d22','t40','t15','t40',2,'会议公告','xxxxxxxxxxx','2019-03-03 23:42:05','meeting',0,'none');

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

insert  into `person`(`pId`,`pPass`,`pName`,`pGender`,`pRole`,`pDept`,`pPrivilege`,`pFace`,`pIcon`,`pTel`) values ('111','123456','111111',NULL,NULL,'ggb',0,NULL,NULL,NULL),('735524','123456',NULL,NULL,NULL,NULL,-1,'','','13717735524'),('t15','123456','测试t15',NULL,NULL,NULL,-1,'�.����1����k��}m��8B�B����T�9zL�\"ֽzݧD�R���0��YZlA\r���O\'��Ge\n�l���M؏.����ZO�00� ���V��Te�o�L\n+��*�&���uҁe��\\�Wv<�o_̵�Q������d�۷�׊F�=�Wq>Vb����O�:I(0,#��y-�=e�];��O-���m��Ҟ\r�M�%��W��c�afE�Ct#\\�\r��u��~��u�3l�ن��p�W�j���`sqm�sv6�f�lY�ᑃo�\"�ֶ�����)��y��26s�&W�${�I!Ll�C��e�g���\n�F�Z��b�*�i_ګa���kZ�R7��K�tJ�%U��(�\ZE��b�<=	v��\r�����2�\\�$#K�w$&G��a�Bs�b�X[�\rk���+o�����2Ӣ��8�>����O��	11�Q�X�!�A(�u�DS�%�/0�i���t�\r}�]�#A�H��t�.=;�&�Qj٬�d\0SD�J7�\'z�y��ή����t\"_�6�����Jo��)���f�2��\nU����\0��C�H��c�����fƻ?��\"�I���Sj��]G;�H�����D�\0A�>A�:(�p�U�{o�F,;w���6�E)�b��V\"�z��1Ƕ�� b9L���2@\Z�aZ� �{�d�\\���\n������Xhr}MǏ����;��2���m$*.�8<\rMu�,����_57�ټ��׬����������7�[���L��+hD7 ���~�U)A�F���m��x%�8e]�7[�5�Kw������^D�����C0��k�����z���?�!��i�{�n�@�[�h��Z�<1���E4�G�_���l���7:Mo�u2ߧ`�0�����bt�ђ���XKs+��~��Dw&��;��B�ν��۞�\r@ĐHm�1%3}\'�8W�q�{�����|p��.}mA��Lʴ�CHmU�t��lB���\'��T9K��X�ɓ�s|j�3��3�c[����j�����=\r�q��Kp�c�z\Zh�?��w��݆��]p��L}�1��V�2���<�9�2���4�/��7_�x=͋�S���\n�t�^d�t�ԟl���KG<��\n6����\'��H\0�&1��	���c[�>+�5:\n	\r��r#oW�%�W�@$\0��l��������@���/\"A��c���ݓO5]λ\Z~ts�xr�����[Ϩ�^[3�tW\r��u�Q�s|!�=�����/�X)y��`��s1��,Ӹ��v�^6vWxKiݴ\Zm*�@K�����tk�P=�����\"�M�R6\r�Y꜖5y�\\�?L٦��ǆ\r�{LT�@��P�C��PE��I:�AE���pYJgկc��=��\\N��刹v1�X��B�@l�]�}Դ���L�e9J��׻/�x�ϠHv\0�3���=�;�u����jA�������Z�+��U{�≂|og؟us�.�V��ip�Ë\\�>Z�I�����܀��|}�����k�qd���w0т~ػ�Bl��5�ST�܊�x��s�aϛn��ƶD�\\L 4�f_9��=����y�%3Z_���ձA�$�~�q���6񯯰!�	�v�G�X(gj�	�_8y%|�l���g��ڃʐMi��e\n(��B�b��ш�=����K�\\E��?��$$�<`0��-,�gtY]AWj.o	��I&(�����w�*�7ſ&�ՙ�P}�@�X�3�\n�+~�74!I����1K��j��5	�h��~�\\O����C0Y�V�񰗩����Z�%�@���Om��:���1�^I?d��-CP�Ip���&�����ـ�k�<�6�%I��>H�iK�\n���{��?P\\���3�P�M�r/��������&��Z�)s+jW���Z��͓u/��n�-}�����.��a������TH�.��3J`��8b]c�&+\'��4��ܘ`\\�(�� ��̈́9�K���Jy����ƞ�ss�?Q��Rf�!��nki۠�Fu�ϖ�kp���@POA�1��,���ň�y�/�e*q��QnZ��9�\'x����V���AS�^, �w?�J|��^ ����f��,f*P��ȣ/dG6��k�V}���1gE�!��P@\0Y�8�0_��f��rs6؅�8vLO�}��3�q���s]��QM�ye����_X�A_L�fݥ��1~c�D��T�5j�Ɛk��%M��b���\\�c9�q����	5�.�1�лu���yi&���\\��=fL��2fr�7��P�7��u\0�oΪH�O��:��`<���N�P%���g Y�$ K��U��`����qo�>��)$▅}ߕ�C�f����}�`��	��Z��>�\"��N\'&`W��	g�lw��c���;��s|n�[�HӉ�P����&�x��brQ֒,A�ӚQ=��o�(+{\\43p9/���-NX�h����6��Ӹ�^(A���a,�h�@��!`O�!�\r����(?K�cw9\0P**�ؑ)-��p����םOE���5�(���t�`��ƛ��*:��qx�.��K����O��Md�W&(e�g� ���<����kV����֬m̓��F�t~D�c�0]�.Q��8&��\0^��=���\n ��Xuif6��&^��2VU�J�g�E\\2�����%;C��pѩ3��4�8[j��z�+F������k��b��&��>h�p3(�V�|i�L�p=ʽ�q���)J/(,ȴ�ì���E�:=R�pWR�u$�	����t���S=U�^��y���)i�\n����J�nl5m��;��M�Ve�u�\Z\'�΃.�喇�Lч���@V�Xd0,���1)���D�V�rgkb���X���}����-pT�N6*��	\nB�zg����[<\0o�\nt]S$�ys�rp�9�cl5Kaojg�عHɘ�1@E�T{�d<%�A�Ғ�\Z�^�N9�#=f�i���R��\n�0`����]�>Y�V<���*Z����_�u�ڰCDu�@���+Rv���V^|1&ί�����=oॳ9�{q�j$\\-�j5�������� Kk�MH��|\rS�	�������!�r��T��܏��\"ҕ�ȷ�]#���+z6]��BY�A���t`b��JÆbY2���Ӫ+V\0�\\3L�m���	�����6��y,-�M��P�|2gl\0nE��,�[���[3M�G�]4�Ȅ����KK^1�І�>��\'�Z���z�]�x������q^������8k�L8,	��H��4$����q�\\��r͝�3Lh6���u���2�[���%^�EC���ҽwӲ���D�J�tn�WV�{����ˀ�Ғ��E��}�� \n/~���=\nO\r� M?j1�?,M��w�n���[��*���OԹ�̳��\Z�u��i)9C��k/��`#�-D�tJN�K���|E�9A�0���;v:�2�R�!	��P�4��#h������}{���B��-�?��--��p�Vc�d��u�Z���\r���à�\0������8w:�|v]e]v򲜽�࡙���(���z�٧��i�g����<��u��NЪ� ���KIl|U��c��͵�_r�@��ǻ�u�����_0L�F��Y<��hYua��H��a��!�mU���dZ��� @\0���\r*�?!����1]���A���6̹��3��\n��\Z��d�������]y����B�%U���*�:�����c\rYع߾[퀆�c2w��jboI�L����� *���W����!��#@Ў�����Z�X���h3N�=�ۅXl�YE���Ԑ�d���m�Ԙ�6w^�v�S@q*�S�O�#�k+���U�Ҩ̼��LCl����1�Y8ɵ������2���;�}Z���:�{n��#�	�����=e�oW�z\\\0�̇��p#R2�h�_������7������j��o!�Y#�ncl\\1��L�Vu��V�b���\'XC�� U��VV�/)N�H�Ͻ�.rʉwŢ��F*�����+3^���)K�*�@�dRl���o=��$�A\\vk89�� -�z���/��	����۾��H��?��b@��~��8�f��%���d(�=�v4=���X4��7�d!��� ��4��])��jM�iտ�\'1V�\0��\rQ9�����t��k��p�:l.綯|��:cH4�ϡG�Ƣ�3=Bd�dZ�`\" i8i��Z�m:k��y���{/}�bbRK�*45L\"�	��n׭AA���)@��=!��)�[�EH1Β#߳���7x���է���J�p���I�����G[�H���&����?��L�UI���Z9#�砋1P�l�� �j��Ǹ�KK�ڔ;��� �v; �����4{E�D����Q7B ��G�Z������6h6��FSH�ʈ�V�8sV�^S�3��P|f5���`�$K��>��\":���_��T���}�|���`��,T��Ъ���kGՇ7�ڑ�l�ZD����ŪS�ZHm�rH�ۀ�:�h���!���3��n9��ՃQZ�}��q�����6^�\0�{��>��(��@[�$��V�\0	�-����M1>^dT9?J�>0A_�وiiݙ�.�{4BA�s:���c�=l�CZ�)���$��AGF2Qf�y���cOݏ7�~��g�\'�]������	C\rGk=4�7����s\Z��QY��ߒ@�2[4�r�W�@oi�گ9��e~�$V�D�?.�N�XA\\���|Cn��]�Kn��jqљ�F�p�JlfE,[R�Q(C6è�()9δV�I�.�7Y���KJ��*�����<�\'󌵝�7��G�L�\'��z?���* �)btL���<��V�\0���I7\n똏�\Z�<���i��!B\r�խ�\r\\c���Y�}8M���Z\Z��\'g�zN=t�Z]��*�\'dˊ��`�����&dG�\0�\'#xDk<#�N��ꢳ���TP\"�\0��v�zs0��ɭqZ\n�%��>�N�rZ��{�,?����d\ra�7�ۗ��\r���	oЧ����Xe��#��ɐɃ�A0tnw7��X�R1�H�:c��z����wx�����T�/~�\'+\\�d*,6��$0BO�oS��b�w-���t���As忚[��T�Ǣ���Y�8�2�B��]�\n�����N[ڊ�Ǚ@	\\Y�!,a���ը.s�����j�gQ���Z4b@�d����	&�^\r`fYjq��������\rʞn�<��j{sΆ2,c�\'q�.�e��#����CY�A�����m���\Z,!����`g]M�����r�Q����P.�]��3;��\nv�����b�ˮԽ@�>��Ŕ]����2�D>��\0�c��RT��ǅ�N/�\\�$=�/������9)8U��S�[GZ\Z�gz\r�W`��=��h��fK0`���C�o __��Z5w�wK+�b,�*eS+.��wXQa��np�1y���#��W+?�3jV|��5\"i�!	P.q@��p�p��X�����,M�4:�6XK��p<_�w����qb��ΪuД��M�XÁ�Q��׈b1g�E;x��{1���g�w�H�����q��x7���Z�WQ���o��=��LA #\Z�N���,�\Z��Ŭ$vhgt]gJ��\0�\"�?����H������c~F\Z}Y�>yȷ���)l�)I�	���-|��� �b^��p�جL��ػ�e0\r�ŭI�RDІ�c�&���98A�&E��:/�A�H�f\nmi�j����9�pIF�g#�����i�ېe��N���C\Z���Ƃ��� �C��;ئHN܉��e�c|@��J������]�����ـ��\"K����i���r�X�ƖEsQ�Ўc���o$��KGBF:��*CZǳ��]*S��:�X�d���.W�kj*�\n���U�<��\Z�1_E��ַˣ#��m�!�c\"p�����\"Q�˜?��n�e��p��vf���T���?�\\���U��NR,]l\n	;K0�7h�~7Bj*��`#��`�����ߩ�sR�Mǧ���q�iu�F�H6h\"����;T�o~�	��ё���ŉ]�<})��#�0���Ǥګd�>�	,sAMo_���\"�)���F�`�jjP�Z&di��2�0�-��mU�s`�C]�)3���rBp^\n�\nQ$�@����iLB�&��<�_5���ȕ�����h\r�\Z3�ͼ�����Y����(zw:D��UC,�\n g$h�SXm�$9p�h�6��0*�Iϩ/\Zr�$���,�a*��A\'��^�4e�A`���R���#4�?��2H)�o�E����IV�ۆK\n��--)��xj�����ut,{`�Z�\n^L1Km_���!�Y�]T�RB�.����򽀼Ɛ$��˾�\r6���V߬��8��\r\'���N�}R%$��^sWS��=n�Hf�L��|��688�9�������p�Z��wp���G\Z�Hn!U���0Y��a\']�\".�\\QЈCo\Z�x�ҵN��<q��w�+\"�y^[��х���J�x��Z�?����1��Df9�����%�]�xq�\r&Y=oZ�ś\'/ml�)]�+�kJ\ZnDPη��+�\r�Z�<5��k\\�`��X�_\nس�k�k��`��$s���{�����w�E��m�����3\'~�}�b�tB�Kp�Լ[�;D�I�K��|6�8�c6��L�ajn̮��kgG�pg\\���1�P���,��M|I�����lN<��{5d�ٮmI�b4L�&jGd��	c�Ǽug�x�·��@=afB����r�G�E��\'��#�h/�/\n ���7\Z����y��	x���E��ckVXa<�T�t���\\�<�J���v�e�`G�BO����~\n�z��\'�7�+����H�9�F)?ϙ�<��}�\\���-�������y\0��	~4���G�n���\0$����e��/s	�i��?���Qьڞ���P%^8<v��恐� �T:]2����������-�\'�&�Ī\"�hٮ��e|�ؗ��Q��_�\\��ydVg\"��GjA���8}���A4L�xi.E�z0?\n�4���J���0X4N(?y�D��5�;����[O�\Zv�����wC>�\0�8�C�X�+��EE����R�$N`i��ȉ&9=�F멜q}�!\r�u�H����v=A^��]��n��\0F�x8	�ò���s�Z�Zq�ĭ�y�]�2��h����?l�5�S�S<�Z�R�ap��\\�ur%��8���b;���=9ܯ0ǩ��E�P9��[`\Z��?t�k���M`\"���p�HI��#��iǬ�V�:��,`�2�4���!?�����+� Խr�<��-�����d�މ�\n*�xuA�P9�9�[�7�03u>l\\�Ew�hI\'����>��N:��ۥ����os9��<̏t��ʧ�O?Lh�b��%r8K0�*�%�Jh��Ŵ���6�3{ki �]a>du�ےx:�v(�o��lqQ���~s�@�r��O[�9�	�ti���`!l����9�ڨ=�X��B\Z%9[�\\[#�yD�w�s,���x+�BR8�-��<�}��:q�6BD:�X�t�1�G6������h�[>�L@\0ti_�W�d\Z0-��B������W�[PR$�y(P(L1m�����G��C�U�n�CV������=Ė�O���Dc�We�ױ���[ǎ)YyA��QN8g?+/$9^�#�m��z��Nx�ᖖ\n)^A��؎{b�6Dt�8UER�%���9?ٱ\r�r��C�7�/��*��_<kR��VH(������F��\\C�Ť\0�U�LO���%��W��+��|q����Mk\r8nQ�Ι�]�����Dvד>%ߒo&0�Lt;C�ɉ�I���^����6`��ԭ�/�ɥ�6fC3�%���^u���-�Og��?s�h��4d�>�����5C��o�\rsZ(���N�e�i`��Ģp�?( �4�;s��ʤȨ���i��\r�������ԌV*Sj!b����7���u٧5����I�4{ܿ��}j��<7�6w��1뗰�<�]������}������ؼ�M/y��?�����UI�)o�Zz��i�V�/ظm��O��c*���:�e4T�W̆�ˋl����8WbX�ks��Mר��H.�:%�j˙I�:yo�Y�����}ǎr֩���ţA�cda�\\g�s=J�&w}K�&e�l�պQ�..ٽ�9�y3��|�5A��4_� �9�I�p�#C���5Dv��#�H =gh �5P[��C�%?��B	\n��!��N�G�\\��\0ۇ�qt\"j��0F�6�4�/��w�;��|�K�!���ڏ5�Qe�7ӡ�Wd�\Zݜ��JZEƶ8��V���i�=�wlE�y��a�T�ʅz�\"}��&�å�\ZWQh�a�\'`h�{_7��˞r)���mQ���09�<�bÌ&�]	N-� v���_n�K��YO%�O\0`̦Ų(F*>�\r���[���)\\l������)���v��<��\'�ugmDa���Hk��9�)E�ha��\\�EmoȐj�ʙxr�#��c8�m��d��Ս�\n���;�1�܏����0���w�^}����_�Љ,j�|o�*�v����L���QR�����ד{f�T+�d�2I6�	y�Î�i�O�l�9NC2\\��zQ}g6���O��s���k�I�ƥ��G�@�T���)��Ҝ��s��V\0���:g\0�Q��kH�0wq���r�e�+/ֵ����n����j��$ut���K���J��B�U+�a˳�Q2f��:��H�`k?Mg�H��㑖��$��T�yp�y�W#��-a�����7ʐ�l�	�\r˿�\r�&mՅ��DC�}R�\'堌�X�\0���0ƮO%�����F��])�C��ܘ��\"F%�\0��ŔV��Kr�[�p�ץ�����U6�l~�6���D�{EU�w5���D�)��g[��ӯ̂�.Y}�Oq�;�\\�5R>��D��z�.EZv�\"b\0�9�id�Vc���3~�� ^J��r|��R|Q2�5��\ZM��3J��Z��G�#�!Ad����l�����,��V�>Ί��#ý�iv�\\P�M?��u�O,Sb�������x��*����\n�[�=LL�*9b�]��T���s5K�uE��\'u%���~�l��7�H@��K���4$v�ө���e�M]�T\"3[�xx�7��Mɺ�����.�z#���R�T�q_ ��)\0���p{�,t��ss\052Ȯ��Ad�(���&�ۭO���@�hl��]>opH������1TL�J>z�8ɽ&�h�H���\Z^_h�̑ψ�̋<L�8O�V����B�cʻ��]?�H(�l���Nx���| ���v�cu��\0Lߘ�/M8#�U�P��ܼ۱X�\"f��9�s��#��x?;��`�}\"����!\'��}B�tѸ�+��n��q�aBJ�\'�zH�����![�p�j�]C�Ѝ1�\r���֧\\S3<}SQ�\rS���\\T�^\Zi���U��_��c͹����g8�\Z��x�����K�\\R��,T�8�\0^4UCxv��=���q�\"´|5�(�~5��`��C�_\'�/~m.s��s��fD���mwD���/KW���!��hk=�\\��{{�n��x�J���!\'Y�7`ChE��{��a�</Z�~B\n.��BT�G{ч+CD&�-���0>J΋=|����\"�Z�̰~�q=�Kwʟ���J�^<��_*ؿL���\nu�1L;���&m�U�t�Z��擫��P,���իu����j�t����⋇K`꽵��8�=�D\n�7r��^\'����g�K��c���]:e)a\0mʹ�:V�����0_��A˓\\���Ji�-]%������nh�j�;͙�*�S$����5s���P\0[=�k=kR�,ϖK��w�a���a�Lh2$�OF=d�/��v�]\'����E�U=^�a}	�n|^!:yU~[��(d�,�bB�vN��=���4�z��	�Lj�������Ly���� ���\0AS�9����;��h�\"�Q���i_\ZN<93��x�p����Q��dL��>�����\r�\\����o)-؆H�\r���/�A��Ǿ<��k5��M��z��8b�p�Gbt�&8JǨ#�b1X�\nH�H�_�;\rI}x��H앖2Vn�w+	U�!N�ԟ^?�`��숲���!�2�����|a3@�3m/W�҆(�AV�?��띗Ӹ������z#Jq>롟�r��wL����K�7��K\"e\'��ѺsW�[��4�+ŋU3uiB̧X�,�N���[�����fo����chQ�� �v�V��_	S�[��\\z�^+���%�1K��I�b���Y��5e[@����Me&����!U��*�ws*㜯�gE�r����Zq�a�\'�F}�nA��;M*KQr)+�������d����^�+���YXtE�D-�\0`}�e��9�J�R[��%�iێ��*�U?�{����a�����r�Q�q���%f)LƧ�y_�alT�#�v��!�A��0�[�|�\\t|��Y�NSJD��$mER,n�O���z��dOn;.S��E=W�R4�5<Z�+��QbX�S����\";��8re�$�㝿��p��I>���tC|��#C%,urN��L�lQvܒ.��&y�D�ʼ����\nz�^Pvz�]�N\0\0���/�=Ŵ�߲�>�ٸƎD���p�dai�F�{�r���4W�� �J&Bb�˧�����Cͨ�!��8��T:������s-`sf�FL�0�S������E\"���߄\r�i�Cedr���}�p���D^VA$�^|/q�p�נ��5�{��Q�)��8b����_�=C:h|NR�������U�ßܝ�Io$�rj�m<��J��1tU�� }i�B����0�l+�WgM���>�8�/��z�+A]/�/�>�_ުȂ���|9�ϭ�yx�!8��Ĝ�WtO7 G��mYr��O�k���$��LF\06Cт���V\'�<���Km zQ�Z�>���`�+N�s\\��6�h!�bbeV�+]��µ)s\Z�v�[���J�T��3}��F�(D[Ih�����q�`n����tW{�D�ס�@��Gn-�&ɣ���x���Dֹ������=|��p����w�\Z��tg���~p�\ZW��_6��7E,����ɠ�R��i:�8on�j^�^�e�\r\r�c��箐�&Ps�\r�I(2�S�Vy�K\rɦ \\&�?�yu��4h�Ğ��͋Ƶ$���=�W�y�\'V@1�n�͌�dA&�~���1ѱ�^C��h��n�Ql��d�Ǡ��z i�a������%��ϼ�o�\Z�6_�4%�P��N�Ӑ$j\n�/\"�_�e������q�Y������7O\'c���4,���`a��\"�������\n-�=�\'�_�38b+1�邬輕�t��B�,�ܡ!Q#�\n�Н��g\Z��)���U�!��	/�OS%R/���R1��a�s�}|�Q�꧍�?���X�����+�Y��YT��RF�/��cPץ���h���=��������qO�U	&�,v�܁	K+���l1n������N�\Z�Lw�Q��3�r�bW���P����ܔ)F��oCV=�6�/�K�g$+Q�h�@���Ǉ3������=Q�7S?\0̷�cs^u���7�\'p�/�X�Ro���[E�mm�\'[�ห0<W�@c\\�\r�уpNJaA�,��ˎ��A�=�.�vƀn*��6�>tƜ�m�e�d�9~$6͌�[�>�R�khm��:u�ܪ�|����*�ħx���6<ް���z���[\\��;�-+v-���hXo-��~.{\0&fwyҤ���ԃ!Iɏs���\0`�\r��i�%+=l�B2,�՝���v|d �I8��_���\0��߾U�tx@UŽ�_h��,�7.\ZCRR�SC&GF9�\'j�ɛ2��s*����`QhrIh�f��u�۵ZԦ�Q|3Pw(�����5nu�#B)�g�a�=�H��H�˳���)�MN�f��U��^2�pRm���0�`ī_���F�װ������7JU�>\\��~�O��2�3e�Q��^�}N��s��~5vC�k�M��%d�_��:�{�[�küOnτqpcB�L�ۭ�ٰ��\\[{���Dy�h���O��\Z>}�+���Q���#-˴�J�^�Q��\n�=/1W\"�w&�w����Je�m:��e5��0��V��Pq08�Z�n`���v>�;�����O�\\��Tbd�B�4𫊅1^���&#/�1��ʲ��9��I��g�8_���po��\"Ы��\\{\Z�{a���Wd�y�T6��Ef��<��҅���d����ʝ�Q~��4�&�F��)a�E�i�_�}\'c@�`�5Rd_�L�w�R WiԘ`/��Q�?Ԧ�#T#}F���o�T��r���)U]6�ռ�l�\'�O������\\�DY����!i�V�ߥ�����K���\Z7on2���+�]p�ds:1\'uõ�l�xt���e|ǯt-� P�u��\n����I�NCz��a�r�m���M����m5^E1;Eop��\\JK����f�������qW�%ݙ�X��y��e1�-��!R�΁qT��x��de��!L��I�v�[o>	��O��{��޹���ΔX�]�|���!^�\nF���\0�^;�&�����Q���\0���\r�-�PhB��ᒜ��#�2�����w��cjԱ�c������Z�L��[G>&s��ۃ\r�K�֔h�+��1!q���x�|B$k&~$\r�`Ҳx&ّ��,6{�<i�TduJ�j��V\"O�./�BY)tI�%f$�m��\Z���R�v��Q����=�$��L����Q���|���1��/IG��8Kȟ:*��ǌ���R���νF�~m�\"K\n��lh��v���<V\'��S�a����P�LSm�i�Q@ݾ|.�6����s\n���@��Ѣ�H^��E���_kz�\r���K7����t9*(t�M���g����	|5ǈ��E����P%�Nl�\"\0�aPz�l�̫��u��O$���~����8�	�U�X2n�r��e�ùgW	�����a��Ē)�<��fZ�t���?Q+L��R��ŹI�^*��{Œ0Y-�.w}�q���ì��BB��t�\\D��&��	{�1Qu��\"4\r앝��+���z�0^\n����e�)~�8�D6f.i�]L�4�RG��G���aX��o~�/<:�K/)��^��t��\n晊����^�L�q�KE��:R�ׯO.�1e땇��*�a�3����\\��]e�\'I� H����:��~-�y�і�3D�\\M`,�����j��s�Ue.���s�_�� ��=�l3]�.V�؇��q�q��\Z`<�2��w	!.���z��Y�2����iP��,����=�x����պ��iv��C}�1�dZ-�\\Ϝȑ:�(����Y������N��\'\Z��\r��D����aUJ�N�*��9�\\�?�5�Q��|����+�\\a��j���	�uN%u�� ��]��&�<\\���K�L��������������֎�`-�NFl���������s���W��\Zx��<�7�Q=:�	��!����>1��\'=��\r��0�i@q[�t�ݯFKѲ�F�aV>��.V�2t�u�vrH��6�}ُ�\Zj}ƫy�M�vEd��1G���7m\Z�\"A�E�$�Ie����W�US�\Z���T�*�T����c\n���$.�bD��r�k���(Y���Gv�k��d^�ųC�C�����������F�B��t��	�ZA�t����!�Xl�]����یW��>�É(�DK��Łw6�~�5��Ҿ�9PǮ�8�`����-�8{d!H0\0����]�̂<�t=V\r�JR�2�9KV_i���e5Yy�9xo&\n�1$����U�]~�����n��\'��gRz�xVF�׈҉�|��׭��P ��+�U.鯹��i�ZbsW*g��>�T�Fr�Cy�q�Oj*4	\n:��+  yQ��9׻oa��oײT�_`{]�T�������M�<�X��:&(�W��p���y\Z���E�7�T�+V�g�7��˕ll��\n��*�k��f�ڲc�⶞���4��R�����X��+��fv_ʚ�:\"HO}�*dJ`\n�3�﷽7�E�[���e�8\"�CIk���Z)�i��(�u���¦Wtw�����\r�:,���яV� \Z%��箘���+�\Zܹz/W��l�����ma�~j�;�Q��!DKKi�?��d��h���u��{/F����!Y�t��3������S��D�����@��gsB����{�OMz��`�+?����;Y+�D>�3	�	�g�h�xv���e&}-�Nx�؄�b���v�/�|Vend�W�cx��3O�[�z4îN%�x�zI����~s�h����{\Z��n36m��1�[a��8Q���l	��6k]#�?����M\"8�if/�@ۭL�^�O��+|i��yΌ�z����#y�Ö+����A�K\r�:��=5X��;k<c�̊�/BN�G���ۯ>>�\\�sr�hf2~w-]��:�R|cw�4F&,���`+��L��d�k���o���zG��F���g��5����o��.J��C(L�#�]�$#\'o.��P;��XkR}���:��(�g�����0��S�B�~�D������	�E�I;<�9��҇��ӨV$1@�̪TN���i�W�#\'ʭ.j�}~����MS��h�1H�K\\[w��<m�;B���u\rB����\rm�+82;�r��\0t���v�N�0�<;�t�v�\"v:��Q_7ioܹz,�����c�r��Fn��|���O��[Z�8�k@j\0ӧ���P��`޽5L!W�Im|�g/\r����jK���A�<@�\Zd*06���c���Z���@��	J�zG�����)S�e�4���d!�6�g;��*9�wU$V�䶃.���J�HF9Q�:��������}M���\nr�3�:Bte��M�Ɏ��U����#E��e�j\r�b+*{�x��\n�aV3~ñ�k<��R%���$Bm<�.),��1\"R&��8�X�в��(	_��Ͻ����v�m֭�0���K�����w�5V�K���w73t�	�QU���b���#\Z7���Q��yPn:o��e��H� 1����&�{���]	�e��Mg��~z��إ���{�	��Zh�P(�e|�V����Մ�c8*���d�`7\rOJ!�G)��)�djX��S�ck��{`sϖ�J���ۃ�3򉪤$4��ǒlH@���4�<�{�Ue���j�����ƧU�����Z���H�q�*V94)�X	�\n��� ��5��}u��\rRl������!!{�PZO��U�����=Ž9U\\e�%g����?��r`�I�4Tvw����kX,{�\0nM�e�o��X�\\�T+$d���0l:ns�����į�-4W��k^E�M�w��\n{� �PA^`�&��%l\n���9cǵ���@AiPZ��,�lR�Od�K��ﶬg��e6�ٚ#��w�#��߮���*�%\\6�69��y�9��CYg���P�vv��q˟FT*)3�k&\\��>�kѸa���i�~�tG��� ��b\n���z��d=xg�O��u�*�!�y�7z��\n.W0�6sYFI���1���R��DX&bt\'1ȯ���;�l��x�\Z�S�u��M�W`���Op���x7�ܪ���8)� G���׾\"ܝ�U��u�<����6}��\"0��������1s�\0nu�����m1oB�B�\\��Om [���BP�$�����=>J7��ۄ9���+���}�_%��Ҭ1��O�j��V=�Ys��マxM|Y����Y��^[A$��)�!X���ly�Lt�Qn)��������ųW:�����Y��4�\0#`ٷ_Gj��ߕu�������\\=n���\'�\Z����0�><F(����)���4徉=�\\^f�\'���-}���_H��Z�j�\nP>+���r��%k�+�:D�.~uD�,8���Ր�d\Z��r��Tmt%����J�,\"Ӝ���2`Oz�����%6T��o\0v��\'+t��v\"\\#��njUu��:����wJ��e�=�ܳE�����jW�� �n����zJ���1ӫ#eq��WԪc3�] [6�9]\Z�;/�應��2�<B�G@�9�7:R�7��>\r�-���c�Y��JúB�X�h�X\n�\0��?���\\��\n|A:�����/��埕U�FKZ��Gbw\0�7�\0!��Z�=ׇ-<2x	j3ٌ����ȯ�\'����q���L��*zq���.�P�V2�V߇A���4v�w�Z�#���Нa�Z�\Z��GZ��T��[w��0�<SgeZ��A+g�*U)�Ȁ�]a�����4�vwgMӶs�ͫ�!6�8�`�����b4���מ6�Z����sp�0�j׍�H]�����9-�l��eј������tmP��~\Z�ώ���aIZ�[�Bս�;!\0d��E2p��s�sy����m����������@!MZ���ͅ\'4A\ZA5��R͗�m$P�\\k\n���8��}#�+�@�v��@�Z@�_ԟv4��oB��ߣ�4�\"�/}��H_v����4�f��\"�� k؏�7�K��c��5����el0���eo{丏���˰������e�b��I�����q�vBXFpm���`w�F�5�[��1\rҹϲq������L��vFT���C��۞�5���K�NY��Pތ��6@W��5���ݏF>53�6����p��j�l�_q�u}0>�k����gʱu���髈�(�Sb�ʱ�z���֒���\'�s�w7\"��g<\\�!��wl�̢+ENZ�Q���v�4�J4\"�\Z☸�/�Â 5!5/����{5�x��~w4Wjnȝ4�Ŧ,��}���\nqN�f�,�d�����XʸR���E���3��A���ģ��`l���G�kh���͙k�)�m�� �f��_AG�t������-����v�\0/���襣90��)�\Z>.���Jm��&���#���� P#n�%Z��۾Hw`�(~�n����F����I�p��ēԏO7ċ� w�Ϟ������]To��FÏ��R��?g���`�FgV\\l���.ԝ�6�7|kc�ϱ���0g��z�nY���z�\\:|�\Zh�IXh�4yÆ2�k�ف��!Q����̊I5?�4��3��s�	ӓb���m%����܏��A�o�<]��s�\Z�Dn�h���ϻ����h�T�,��62�dB�l�%;��S�\n�}ק���m\Z����_���%��\n�E)R�8p+����h��Ȩ�T�eV@���d�	�|�v���l�x�m�B��5��4����ɽ�tӮ�J������M��5�6�nAڑ.� _\n�TWp3��T��V���a��L%I�X`U��������j�FH�6Qz\Z�̭�bI&�#�ni�	��/{b�p	ʜIO����-E\\��B��?��Î]r0�Њ�=�o-���Q�4���-�$�����p�gHy#_`�q�0������:b�\\�\'v�$�+�c��5IS�+R9\Z?�+t��L-�7�ѳ��O:�+������/�|_���	��-6�0k�SM��Q@\Z��f\\Z�^\"��W���_���\\nZ��ŋE�YDx�z§���A>d]<��\r���Չ�v4��E��g\'�����&��^��cS.x��]�t/��w%.�Ttj��Hׁ��݇H넥2��w�AGX2,�/B�Tv\r��&AR��4�>�1O��Fo\n�����[�Q(y�[s@��u}��SZG�	3^;L���𒶫{P��-�3�ţl>%H�ب=�\n���	����,	y\'���C�}��L��A������#f�kڟ�z��N��c�W��A�M��BdI�� ����QЈ�T��4ܢ���-���l��:Ru��ѡU,F�����S�Y�>]07p_����6��=�_�\'߇\n_�Ju?�^;|t��s$\"��͚)���B���+�F�{5B��ad�|�!/�ZA�v�#cԍ��hmw9N�u�[D��0���Y[�\'��V�O�(�0��cO�/ɺ�*�?Umwkm��	�o��O��:��܊��^ŧ>��k �t�mn*��9�\n�Pg�	F����₨~��6h:NZ�P͕P8���vd����>��Ӊ��Y>��}3���,>� s�cJ\\-�ԪAdMf.��\'�6�ԷP.D�Ҍ��/.!�]�n��0�p���8�ФE��dQ,�Ì(s��w^\\?��\0���c��+��Xn#�o�Kr�)�t&�5�(1w�[��!���T~��)���U�~������;ya��`uV��6�c*�a�v�	��:�Yt�P��X�\n߰BA�����*�߱�m^a[18]\'�CŽD�Y��L.PI�	���ê92i�1W���\Z�WTe��kP(}H���i��!��h����5��r�s{�ȕ��!��O4�����!��\\6��ρZ:����FG���8|H\0�X)������n[Ј4�\\v��:�w=N���8V���Y��	�H�^x�FQP�Oute�x�΢u ��/�p�D���76CSO�H���ʺ�x��\'�^Z����ӸK��sH���оLҁAX�IM�\r��kz�i9�s�aH��^�t�)��j�F\"���0	�y�J��Z��Æ� i�v��I1\n׾O�\\-x~�\Z�F�MK��h@��Oq�ʻ��K��K4���IsgS�>pk��L�-\nS���<V�Z�������M�!�5�����k���}�Bb���\n�K)��8q���&�wٍ���1CY�<\Z��^�}�ɰp����2����E��y������`ZMg���s�܃w�r�˒\r��0}7^��\0L��,�2�W�q��-Z?-^��fp(\r���#����������xw�)�\"\ZES�p�$�Q8�O��r6%�&�_-��>�{9�W�P\0\Z��p��M���7O�$D���ik��v��$����N�_�K�@�w��{x��z������i�:��������z;	f69	4��[D}�̀�٠�u�/��5�D�C�XI�G�H���kCY�\Z��t����ـM����@��CJ�/�HF��Ä����\r8��O0a��7�BIBh�i���\'���\'�8�����y0�����ݻ��!s@*��!�n�Bj9+6�d�䬮Z�δW���8�䉎8�L��6����v�:&)�%�X`�r��?�x1�:Xɠ�ʢ��@�����[�DWM��Fg;?\r�\Z�R5�����Tp���=���+�3����V ����(����@�O�obrVKc�ê~\n=t\0^�64Z���\'�y�7�mi}�7�\rj&�d9�6J�YDF���zm�ІA,+������E^�\0%-�L�ੰ�_J(�0��էu%Ur���o�$���=���S�Q�^��q�ͯׄ����O,�fD*x�:S��%� \0�%}��p@�����r��c�\"̷�~2̾/d�P����=���Q��H�`���\0\rm+�����+\Z8�\'f���oL�!�l6���~�`�F��j<�s�\"���:܄��=Б-��5��.�j�j����+v�=-�W\"�Ү����=;|�ыt�,�CG�{��\Z�hn�Hk6�d��ix�&JR�A\"`�\Z���%jB���`�F��I�=V�ڣf�w�\n�V��HCq��Տ�DP�K�����+�n�����3��T�\'7��b���ؓ[�V5P\':���j^���q~�c !���o��r=D���}��N�\rv��|���H�y�����?��\r�R�4*���9�?�ħ>RlYg㉹�\02\"�zDɄ3\r_@:�΄v�N���8w��\'���}�V����𿄑�?�n6|	&�->F�CJ*5�C�P\'���0���c2�Q��4�Q�f�2\'���m��?��9��+�*�v�N��\"+�k���Z���\Z�%��Sp�-}��]�DR��Cԥ�v��h�u�.�vG��ǵL.���~��D8�� ʭ@�g��g�%�� �M�������(ʢ�)y�Ƈ�ou턯�b�Q�r�\0�7�s��DKW�%�rAt�<��K?)������V���`�R�9�\\)��-�I��\n�EV=�o�>���7y��v��\0_��~�s6�c����y�2��_�p~B����\\Zs�_��(q���Y��gA�^�J51�o|�����Y��I)�@T����V[��N4��Pd�h��6���V�\n&�>˩��3a\'{i	ș��w�_-�R��X˰+%(t���\'^�EZ:g��c8�1RN�hq�L�HokЎ��e֦	3ټ�v�	�O��ڦ�aE�h%�٧JFNʿ���m����B6�!w$����_Ү��_xvC݄y��p�K9�N���.Y\\��6��B���RdX�mrE�����X8h��ǸI�+�����_JA�0L���Ѳ���wƭ�Z+F ���b�X�hz{�B��[������hT��շ1:N�Jtۅ��\r%J�����|���ʨו�,m�����p�zxy��\\ǣ���X݅d����u���2�|\\����J����0�����(�#�Vmuh�ly��J��Pf�%!��{cr��NfKn�H]��E[�`]�o#*,/�&0`�HӐF��FP��B��V2J�]Nc^�7Dp�Ժu���\0�K\n����}���v�?^�[���7��頳3~�7hg�lә����[q-/��	՝���ҋI0�j�L7Y�����\rR^��~�O%ǜ��i�?PE���J!\\�Q��P�\04�ǉ���Vp��Y���1��g����<������Z�n�ZV�&�J�ˋ���]IZFQy��(�\"K40\',0aX+��+v�T|�\\�Z(�״4dS��;��7E|j8����1t�l?�+u�?v����9KO�(/�������_\"������Z�*�C�󊛆ɺ;���=�|v�\0���@�~�����m�lČ���M�M�PcZ����{\r��D��V�IW0ɞZj���,��\\���:�_�׆s���]\r\"ڭ��i9<�ʦ�1��{U-���a�xD�$��K�\":�3Q>A�UB̂��^�&h�\0Q6ȁPA4�R����M�D�хX[�������\rfm�y\')#fJ�w��)��g	�=���l��D�+~�4��#�&��O�3�Q)nC:������xį=A��q̃Mo�0n�*��=U踼�R\\�nf)��nZ�Hqp�jb4�T��5��2�f�B���y\r�zDixe�\nput��#�(�i�f�\Z<��g�0��U�v\'�I;�vb�z��fy)�EA���4,K�u&0=k���-�+v�MQ	���0+�WhvYM2��3q���3�#nz}��3_?�/2ZAlx����]�x���{T3�6��J��\Z75����VQ)w�������j����i�|�D�Xm��/�:�����d@g�*}�\\���7-��\n0�i�]����t�o7��rP�������X�(�K��2�r��3���֣���Mm���Qꌱ�椢���8(�1V���}n�l�}\r��������h��L��H�E|� �X��c��bv�nTfLW�5Ƨ�^��e��Y�G/���CYHҪ�G���֚v���0N�����\\T�]�z�U7','����\0JFIF\0\0\0\0\0\0��\0C\0	\n\n			\n\n		\r\r\n��\0C	��\0\0d\0d\"\0��\0\0\0\0\0\0\0\0\0\0\0\0\0\0	��\0I\0\n	\0\0\0\0!1A\"Qaq���#2BR�����$3rs����4��CDETUb�����\0\0\0\0\0\0\0\0\0\0\0\0\0��\02\0\0\0\0\0\0\0\0!1AQaq\"#2�����3Bb����\0\0\0?\0\Z�2�A�_�?=k�uX�P�F��ͭ��\0�����u�%��?�\'���\0\r&6`S��.#p��_����Z�1��������(�Z斀���|� �sX1�/w�|:u�?ˮi���Tĸ#[��������O��nA)��{g�kS[��k�\0�����Vk�ú&&0��\n�-zR���q�ղ��P7��\0�x�Iz�����Lޫ���$ȫ6N1�~z��I~�?�N�g�@����\Z�[f�E��\0���d�9�M<���f�`|��@B�g��<u@�G������/٬�a�g`�f�G���#��ө���H3�������(��:Do��Q��$��W���hi�?����!��<��R���?��D�ևT��Z,c�3���c����`�d�\Z��݂�����\0�S�����ٜ��jc�����Zw�u�RQ�X�L�u*	=p@�[X�����|��JQS(a8I��g��\\�%-��*e���R:?��eq�{9A8��X����0b��A\0F���2q����RKnޣ���\r�z��z8c�����;r@L0�#\n\0$\0��Om�E�롹^�1�≄\"H�\0�	��&��u��5E����ML�d^����,��\0�\0�1�o��q�W=��C�S��H�XdӸ8�B��}�=�\n�(ޚ�95��W\\�$��8�c���-ㇷ�K��Xn�ҌD�[�a;+:�:~J�|�ۨ�t�MCr����LR��=����H�9\"��v��i�a����Y��.�s�W\r���,����hA\"���\"T�~���$��j:ms��\0u�\0$�\0>��U�Gx�oW\Z���~9�\'��\0�u�Q�ɳ���%>��\Z���E��-�(����B�ʟdX�!���կ�i�:}]Z��4\'��/I\\:���㫍�<��&�M\\;��\0٥��a�F#��q�t� y��$%e�e�Or5�D���Ԥ�޲��.���ڈ�iQ��g	Q\';�ǰP:��~��eJ����BcC�[d��Ƚ������Q����،iג�S[}�=Y���\Z�J!��\0B5�?n;}���o7��2�l4t���YG<�bIX!3,Q���ud�����O��wնQX+�E��G\rY$(GQ�����ك�Դ4[pC$(ih��!q� �0�����N�T�S�兹�q��4C�\Z�T�Z����rN}04������w[��f��V�ڔf�Ajs+y��^Iԣ�1�1�+��N�je��K=��7M�\0�x}u��k1-ƶ�E�R�U0�/;f\"��ޮG�\0��=v}5u٭��Xh��a��Mk�,����yW=�P2	�̱�����p�fĶKUւ���be),�A�ُ3���Oe\0cG&�\n���*>Zj7e�z �����~�޷�Ž[��U��WjI��U�W8�GU<0Øaq�S=�tۼc���(n�9h�Q�\Z9�D�*@ �2��>��Ď�>>[8A5�wE��u�j�V�R�/40�啑�R�9T�����ѯ����]�V�W{����b��Ti9PvY#P{�#���v���L~��[_[�Ҵ���f���P�RV�����4d畀*����Z�����z��h�%��U�$v�U]]Ty��xO#*Xa�g2ty�\'��w=�n0�ظG\n�\0J�l�c)��V��,Y폩�n��Bm�5є�ҏ��$��T�T��ć�:��V�6z�-I7~Ѹ�Ķz�i�rb�������F����P5䜸���3�5#�t.,x�T3G;C���H���cF��<2sz��g�+��ֹ��et��6.�ۖ�#n:�M��F��IQ<q�5<�QI8.���|A�c��lw��k��\Z�3R�$�yRq���R�_[����S�0����H�ʁ*�Ĝ�`;jO��E�k]a�X.s���AYb|d~�̧�NA�Ve�����tγ{F�-�/w8\':���o�m�w��mXd+\n��j\0?�A�vK���)�������n�SS;�ǒu�ZDH\"Rq��u����~�[DT,���*�p��0Q�{1��s1�Ե��I�,�K%-���թ4&P`�v2VR9�sט�8�%M<�]��8\0>���Ar�w����;~�~�	ң}�f�*l=�%]�V�G��y��Z1�Y�8Ue ?q�X�\Zն���=�m��6}�m��c���*Q*�S�����FT�<�\r���o\\7L7K���J��2�H�OψX�/C�P{����\0��U�7����R�$E�1O\n�y��;0#\'#F����N���As�z��ـx����/�_\r�4�Ѵw\r��P�ƕ\0\0v�g~o�XUn���,b���j�Q�ӷ4�$��@a\n$�:�:�Cu�j���H����\ZIPH�E�aϮ�\")$�Tz�ϖN���|)�Y.?[S�~�Yi��IM4 )���P��z�\0�hI���.��h�DfJ�-���i��\0.\n!��\\;��KM�w�]-�e����D&�\0�Qz���\0�|�|Q��t��zK�*\r�i�8�ꢫDzRN<3J�m���@\'ی��}\Z��F��+ӯ)^S��[]6�D�(�4���v�U[+U�R���rH_5g����~<�kC\Z1���%�i�o��ӻ����;8�c<����*�$\0G:�0_Y��<��)�jcj�YՑ\n��h���ɵ�Ѻ�eܖ��+\"\0�R��OfV�)��	��*����aS$;����R~΋�����y�qo>˘�3�U[���U���\0�G\r#�N�U���)�{����<7$����v��ZZ��=ס2՜�MtШX�@���@�\Zu��z��Yl6٫�j2R(�HP2�O��ܟ�rf������YG0�)\0 ��uo���-Ը�I�V��Ե$.GU��\0��>�U�k��>�-��^>�����>^|�2]�N_t,�z0[�$������,��:��$�ѷ�F�M����\0�<�*KݼL����mJD��j�)g���d����ȝ9]�W���p�O��6��K���l���$�I�H�O*��`>g��e��m�ҽ��M[M\'G����:\\t.|�gW���S��6|&�ʍTOm��~���%=u,uH�I�B���0��#\n��� �궥\r����u�Zԇyin+A4��IAx��J�e�����[�����P�y)mt�>%��lI!���u\'�cr0�������f��Z��}���wVs�4��G(>�[$���P�;�� 7o�ld2�%��0HO\0��Ǣڴ�hlS\\b��QI_4�x���C�&�:��\0��ᆳvZ��N�۩ZWՌӰ\n�w-�p:��@�V\nJv�\r��~\r;����`�*�v�<�r@=�u���>����w�%�zˣ\0��_#T\Z��xl�Kc\0���jj�\\�qAi}�[��i�N4��8%��\0~��~o�z{{�Z��(Ď��\"�j��z�:�2\Z���GM0l=�l�֠�6�R:��\09he3�#>c�O㛾�AL�V�c�z�������Ɲ0u��\'v=.���F�Ju\r�>���� J��1�|ST6�uP���}���fvlrdQ���!�-�O���$�M]M�xcL�Tȼ���a�n�l���%��6���C>2�����j�Sa�tn��*;��7\rܓ߀�\"UM��e$\\鱏6=-sԭ#LOA�\r-s�Z�i��V����KDFk%c��T�q��\0�׮.�\0���>[�-�H*h�j��޸f��U׶�w+\\Wj)(+^�Xd*ԭ��_��ggڷ_�S��	��Z�d�@3����n�6>�\0g��X�Nb���8�H��PW:�F0�8u��}	���#�U�fzO��(*�EV0�������?tU��զe��<��Ӕe�7j\"�?,8x�B�L���K�l��+T�:�Y*���Ui�N�^��oWK�n��:SN��a��(��}ڭ���s�\0SO�o���82 \n�4�in�pi#d�T��?6�۱V��#�c?�\Zd��.(�Z\ZP3٦=O�Gs��W�1���\\��UR���IW����ɴ75UB�=5�%R퀅�\0�w��胼��k�RO�uLJ�!2�J�b�0O���\'ؼf�g옭�*����+VTC\n���F\0*�pX����z篞�/�7����{���<��V\n��́�9�����N��.԰���OL�Dحp����\Z\\�T߷>3TI�����gV�ʄ�[)ʀ��@ ��8��o�k�����Dq(h��\'\'R*j������A��`,�\0���$v<�=�>]�-��Mp���i�q-���?���L���=��EuT���������$s8Q���x����=-j�]�t�J�ʛ�df0�#R�\'+�OLu��j��A�Z%Z�L	�a��Z�2\ZN�\0����\ngpMw=�h���q�I�`\\@��Q;���fP\Z����T:�	VL#��F���[�7���U\Z����\0P�zo�ۻ��`��WN$IQ�<+\"8�y>�v,�}6��\0���ii�:��uy���[EM$��6�Pu�n��Ny*y�ݍ�����D*Z�%l~{��.	���;�Ҝ���[�`��l�\0�t�����IN��\Z��^��vd\'�G�?=--.�\'T�@D�RK�\nH��$\n�/C�A���o�\Z���m/����fTTf\0���iikv��-.����\0ar�|��_�\ZZZZ�����','12345678901'),('t37','123456','测试t37',NULL,NULL,NULL,-1,'testFeature','icon',NULL),('t38','123456','测试t38',NULL,NULL,'ggb',-1,'testFeature','icon',NULL),('t40','123456','测试t40',NULL,NULL,NULL,-1,'testFeature','icon',NULL),('t57','123456','测试t57',NULL,NULL,NULL,-1,'testFeature','icon',NULL),('t58','123456','测试t58',NULL,NULL,NULL,-1,'testFeature','icon',NULL),('t68','123456','测试t68',NULL,NULL,NULL,-1,'testFeature','icon',NULL),('t69','123456','测试t69',NULL,NULL,NULL,-1,'testFeature','icon',NULL),('t80','123456','测试t80',NULL,NULL,NULL,-1,'testFeature','icon',NULL),('t82','123456','测试t82',NULL,NULL,NULL,-1,'testFeature','icon',NULL),('t83','123456','测试t83',NULL,NULL,'ggb',-1,'testFeature','icon',NULL),('t91','123456','测试t91',NULL,NULL,'ggb',-1,'testFeature','icon',NULL),('user01','123456','111',NULL,NULL,NULL,0,NULL,NULL,NULL);

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

insert  into `pg_join`(`pId`,`gId`) values ('t15','项目A组'),('t38','项目A组');

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

insert  into `room`(`rId`,`rName`,`rInfo`,`rSize`,`rAddr`,`tName`) values ('A001','UPDATE','TEST',30,'3Floor','default'),('CR301',NULL,NULL,31,'3层','default'),('CR302',NULL,NULL,30,'3层','A'),('CR303',NULL,NULL,40,'3层','default'),('CR304',NULL,NULL,40,'3层','default'),('CR305',NULL,NULL,40,'3层','default'),('CR306',NULL,NULL,20,'3层','A'),('CR401',NULL,NULL,20,'4层','A'),('CR402',NULL,NULL,20,'4层','A'),('CR403',NULL,NULL,15,'4层','A');

/*Table structure for table `room_type` */

DROP TABLE IF EXISTS `room_type`;

CREATE TABLE `room_type` (
  `tName` varchar(20) NOT NULL,
  `tInfo` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`tName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `room_type` */

insert  into `room_type`(`tName`,`tInfo`) values ('A','top class'),('default','默认类型');

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
