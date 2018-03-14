/*
SQLyog Ultimate v8.32 
MySQL - 5.1.73-community : Database - jeesite_t
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

/*Data for the table `sys_data_auth_rule_option` */

insert  into `sys_data_auth_rule_option`(`id`,`business_type_id`,`data_auth_rule_option_code`,`data_auth_rule_option_describe`,`remarks`,`create_by`,`create_date`,`update_by`,`update_date`,`del_flag`,`data_auth_rule_option_level`) values (7,3,'ALL_DATE','所有的数据',NULL,NULL,NULL,NULL,NULL,NULL,3);
insert  into `sys_data_auth_rule_option`(`id`,`business_type_id`,`data_auth_rule_option_code`,`data_auth_rule_option_describe`,`remarks`,`create_by`,`create_date`,`update_by`,`update_date`,`del_flag`,`data_auth_rule_option_level`) values (8,3,'CUR_AND_SUB_OFFICE','所属订单的设计师为本机构及子机构下人员的数据',NULL,NULL,NULL,NULL,NULL,NULL,2);
insert  into `sys_data_auth_rule_option`(`id`,`business_type_id`,`data_auth_rule_option_code`,`data_auth_rule_option_describe`,`remarks`,`create_by`,`create_date`,`update_by`,`update_date`,`del_flag`,`data_auth_rule_option_level`) values (9,3,'MYSELF','所属订单的设计师为当前登录人的数据',NULL,NULL,NULL,NULL,NULL,NULL,1);
insert  into `biz_business_type`(`id`,`business_code`,`business_name`,`remarks`,`create_by`,`create_date`,`update_by`,`update_date`,`del_flag`) values (3,'CSFC','复尺单（厂商）',NULL,NULL,NULL,NULL,NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
