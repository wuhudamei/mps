/*
SQLyog Ultimate v8.32 
MySQL - 5.1.73 : Database - jeesite
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`jeesite` /*!40100 DEFAULT CHARACTER SET utf8 */;

/*Data for the table `sys_data_auth_rule_option` */

update sys_data_auth_rule_option set data_auth_rule_option_level = 1 where id = 1;
UPDATE sys_data_auth_rule_option SET data_auth_rule_option_level = 2 WHERE id = 2;

insert  into `biz_business_type`(`id`,`business_code`,`business_name`,`remarks`,`create_by`,`create_date`,`update_by`,`update_date`,`del_flag`) values (2,'DD','订单',NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `sys_data_auth_rule_option`(`id`,`business_type_id`,`data_auth_rule_option_code`,`data_auth_rule_option_describe`,`remarks`,`create_by`,`create_date`,`update_by`,`update_date`,`del_flag`,`data_auth_rule_option_level`) values (3,1,'ALL_DATE','所有的数据',NULL,NULL,now(),NULL,now(),'0',3);
insert  into `sys_data_auth_rule_option`(`id`,`business_type_id`,`data_auth_rule_option_code`,`data_auth_rule_option_describe`,`remarks`,`create_by`,`create_date`,`update_by`,`update_date`,`del_flag`,`data_auth_rule_option_level`) values (4,2,'MYSELF','所属订单的设计师为当前登录人的数据',NULL,NULL,now(),NULL,now(),'0',1);
insert  into `sys_data_auth_rule_option`(`id`,`business_type_id`,`data_auth_rule_option_code`,`data_auth_rule_option_describe`,`remarks`,`create_by`,`create_date`,`update_by`,`update_date`,`del_flag`,`data_auth_rule_option_level`) values (5,2,'CUR_AND_SUB_OFFICE','所属订单的设计师为本机构及子机构下人员的数据',NULL,NULL,now(),NULL,now(),'0',2);
insert  into `sys_data_auth_rule_option`(`id`,`business_type_id`,`data_auth_rule_option_code`,`data_auth_rule_option_describe`,`remarks`,`create_by`,`create_date`,`update_by`,`update_date`,`del_flag`,`data_auth_rule_option_level`) values (6,2,'ALL_DATE','所有的数据',NULL,NULL,now(),NULL,now(),'0',3);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
