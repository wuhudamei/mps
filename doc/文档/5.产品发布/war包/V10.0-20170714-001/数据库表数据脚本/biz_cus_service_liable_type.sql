/*
Navicat MySQL Data Transfer

Source Server         : 131测试
Source Server Version : 50173
Source Host           : 192.168.1.131:3306
Source Database       : jeesite_p

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-07-14 21:03:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for biz_cus_service_liable_type
-- ----------------------------
DROP TABLE IF EXISTS `biz_cus_service_liable_type`;
CREATE TABLE `biz_cus_service_liable_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id -- ''',
  `liable_type_code` varchar(20) DEFAULT NULL COMMENT '???????????? -- ''',
  `father_liable_type_code` varchar(20) DEFAULT NULL COMMENT '?????????????? -- ''',
  `liable_type_level` int(11) DEFAULT NULL COMMENT '????????? -- ''',
  `liable_type_name` varchar(100) DEFAULT NULL COMMENT '???????????? -- ''',
  `remarks` varchar(255) DEFAULT NULL COMMENT '??ע -- ''',
  `create_by` varchar(64) DEFAULT NULL COMMENT '??????Ա??id -- ''',
  `create_date` datetime DEFAULT NULL COMMENT '????????ʱ?? -- ''',
  `update_by` varchar(64) DEFAULT NULL COMMENT '??????Ա??id -- ''',
  `update_date` datetime DEFAULT NULL COMMENT '????????ʱ?? -- ''',
  `del_flag` char(1) DEFAULT NULL COMMENT '?Ƿ?ɾ?? -- ''',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of biz_cus_service_liable_type
-- ----------------------------
INSERT INTO `biz_cus_service_liable_type` VALUES ('17', '96', null, '1', '工程售后部', null, null, null, null, null, null);
INSERT INTO `biz_cus_service_liable_type` VALUES ('18', '104', '0', '2', '工长', null, null, null, null, null, null);
INSERT INTO `biz_cus_service_liable_type` VALUES ('19', '116', '0', '2', '质检', null, null, null, null, null, null);
INSERT INTO `biz_cus_service_liable_type` VALUES ('20', '117', '104', '3', '安装', null, null, null, null, null, null);
INSERT INTO `biz_cus_service_liable_type` VALUES ('21', '118', '104', '3', '维修', null, null, null, null, null, null);
INSERT INTO `biz_cus_service_liable_type` VALUES ('22', '119', '104', '3', '沟通能力差', null, null, null, null, null, null);
INSERT INTO `biz_cus_service_liable_type` VALUES ('23', '120', '104', '3', '专业水平低', null, null, null, null, null, null);
INSERT INTO `biz_cus_service_liable_type` VALUES ('24', '121', '104', '3', '服务态度差', null, null, null, null, null, null);
INSERT INTO `biz_cus_service_liable_type` VALUES ('25', '122', '104', '3', '私自介绍业务', null, null, null, null, null, null);
INSERT INTO `biz_cus_service_liable_type` VALUES ('26', '123', '104', '3', '施工进度慢', null, null, null, null, null, null);
INSERT INTO `biz_cus_service_liable_type` VALUES ('27', '124', '104', '3', '其它', null, null, null, null, null, null);
INSERT INTO `biz_cus_service_liable_type` VALUES ('28', '185', '116', '3', '服务态度差', null, null, null, null, null, null);
INSERT INTO `biz_cus_service_liable_type` VALUES ('29', '186', '116', '3', '不去现场', null, null, null, null, null, null);
INSERT INTO `biz_cus_service_liable_type` VALUES ('30', '187', '116', '3', '其它', null, null, null, null, null, null);
INSERT INTO `biz_cus_service_liable_type` VALUES ('31', '92', null, '1', '工程部', null, null, null, null, null, null);
INSERT INTO `biz_cus_service_liable_type` VALUES ('32', '95', null, '1', '质检部', null, null, null, null, null, null);
INSERT INTO `biz_cus_service_liable_type` VALUES ('33', '96', null, '1', '工程售后部', null, null, null, null, null, null);
INSERT INTO `biz_cus_service_liable_type` VALUES ('34', '104', '0', '2', '工长', null, null, null, null, null, null);
INSERT INTO `biz_cus_service_liable_type` VALUES ('35', '116', '0', '2', '质检', null, null, null, null, null, null);
INSERT INTO `biz_cus_service_liable_type` VALUES ('36', '117', '104', '3', '安装', null, null, null, null, null, null);
INSERT INTO `biz_cus_service_liable_type` VALUES ('37', '118', '104', '3', '维修', null, null, null, null, null, null);
INSERT INTO `biz_cus_service_liable_type` VALUES ('38', '119', '104', '3', '沟通能力差', null, null, null, null, null, null);
INSERT INTO `biz_cus_service_liable_type` VALUES ('39', '120', '104', '3', '专业水平低', null, null, null, null, null, null);
INSERT INTO `biz_cus_service_liable_type` VALUES ('40', '121', '104', '3', '服务态度差', null, null, null, null, null, null);
INSERT INTO `biz_cus_service_liable_type` VALUES ('41', '122', '104', '3', '私自介绍业务', null, null, null, null, null, null);
INSERT INTO `biz_cus_service_liable_type` VALUES ('42', '123', '104', '3', '施工进度慢', null, null, null, null, null, null);
INSERT INTO `biz_cus_service_liable_type` VALUES ('43', '124', '104', '3', '其它', null, null, null, null, null, null);
INSERT INTO `biz_cus_service_liable_type` VALUES ('44', '185', '116', '3', '服务态度差', null, null, null, null, null, null);
INSERT INTO `biz_cus_service_liable_type` VALUES ('45', '186', '116', '3', '不去现场', null, null, null, null, null, null);
INSERT INTO `biz_cus_service_liable_type` VALUES ('46', '187', '116', '3', '其它', null, null, null, null, null, null);
INSERT INTO `biz_cus_service_liable_type` VALUES ('47', '92', null, '1', '工程部', null, null, null, null, null, null);
INSERT INTO `biz_cus_service_liable_type` VALUES ('48', '95', null, '1', '质检部', null, null, null, null, null, null);
