/*
SQLyog Ultimate v8.32 
MySQL - 5.1.73-community 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;
insert into `biz_business_type` (`id`, `business_code`, `business_name`, `remarks`, `create_by`, `create_date`, `update_by`, `update_date`, `del_flag`) values('5','SJB','审计部',NULL,NULL,NULL,NULL,NULL,NULL);
insert into `sys_data_auth_rule_option` (`id`, `business_type_id`, `data_auth_rule_option_code`, `data_auth_rule_option_describe`, `remarks`, `create_by`, `create_date`, `update_by`, `update_date`, `del_flag`, `data_auth_rule_option_level`) values('13','5','ALL_DATE','所有的数据',NULL,NULL,NULL,NULL,NULL,NULL,'3');
insert into `sys_data_auth_rule_option` (`id`, `business_type_id`, `data_auth_rule_option_code`, `data_auth_rule_option_describe`, `remarks`, `create_by`, `create_date`, `update_by`, `update_date`, `del_flag`, `data_auth_rule_option_level`) values('14','5','CUR_AND_SUB_OFFICE','所属订单的审计师为本机构及子机构下人员的数据',NULL,NULL,NULL,NULL,NULL,NULL,'2');
insert into `sys_data_auth_rule_option` (`id`, `business_type_id`, `data_auth_rule_option_code`, `data_auth_rule_option_describe`, `remarks`, `create_by`, `create_date`, `update_by`, `update_date`, `del_flag`, `data_auth_rule_option_level`) values('15','5','MYSELF','所属订单的审计师为当前登录人的数据',NULL,NULL,NULL,NULL,NULL,NULL,'1');
