/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.131(大美装饰管理平台开发库)
Source Server Version : 50717
Source Host           : 192.168.1.131:3306
Source Database       : jeesite_d

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-10-19 13:43:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for biz_order_disclose
-- ----------------------------
DROP TABLE IF EXISTS `biz_order_demolition_build`;
CREATE TABLE `biz_order_demolition_build` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id -- ''',
  `order_id` int(11) DEFAULT NULL COMMENT '订单id -- ''',
  `demolition_build_date` date DEFAULT NULL COMMENT '拆改时间 -- ''',
  `delay_days` int(11) DEFAULT NULL COMMENT '延期天数 -- ''',
  `demolition_build_employee_id` int(11) DEFAULT NULL COMMENT '拆改员工id -- ''',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注-- ''',
  `create_by` varchar(64) DEFAULT NULL COMMENT 'create_by -- ''',
  `create_date` datetime DEFAULT NULL COMMENT 'create_date -- ''',
  `update_by` varchar(64) DEFAULT NULL COMMENT 'update_by -- ''',
  `update_date` datetime DEFAULT NULL COMMENT 'update_date -- ''',
  `del_flag` char(1) DEFAULT NULL COMMENT 'del_flag -- ''',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
