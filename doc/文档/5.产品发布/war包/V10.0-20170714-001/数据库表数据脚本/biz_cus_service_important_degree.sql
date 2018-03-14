/*
Navicat MySQL Data Transfer

Source Server         : 131测试
Source Server Version : 50173
Source Host           : 192.168.1.131:3306
Source Database       : jeesite_p

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-07-14 21:03:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for biz_cus_service_important_degree
-- ----------------------------
DROP TABLE IF EXISTS `biz_cus_service_important_degree`;
CREATE TABLE `biz_cus_service_important_degree` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id -- ''',
  `important_degree_code` varchar(20) DEFAULT NULL COMMENT '??Ҫ?̶ȱ??? -- ''',
  `father_important_degree_code` varchar(20) DEFAULT NULL COMMENT '????Ҫ?̶ȱ??? -- ''',
  `important_degree_level` int(11) DEFAULT NULL COMMENT '??Ҫ?̶ȼ??? -- ''',
  `important_degree_name` varchar(100) DEFAULT NULL COMMENT '??Ҫ?̶????? -- ''',
  `remarks` varchar(255) DEFAULT NULL COMMENT '??ע -- ''',
  `create_by` varchar(64) DEFAULT NULL COMMENT '??????Ա??id -- ''',
  `create_date` datetime DEFAULT NULL COMMENT '????????ʱ?? -- ''',
  `update_by` varchar(64) DEFAULT NULL COMMENT '??????Ա??id -- ''',
  `update_date` datetime DEFAULT NULL COMMENT '????????ʱ?? -- ''',
  `del_flag` char(1) DEFAULT NULL COMMENT '?Ƿ?ɾ?? -- ''',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of biz_cus_service_important_degree
-- ----------------------------
INSERT INTO `biz_cus_service_important_degree` VALUES ('31', '89', '0', '1', '咨询', null, null, null, null, null, null);
INSERT INTO `biz_cus_service_important_degree` VALUES ('32', '90', '0', '1', '投诉', null, null, null, null, null, null);
INSERT INTO `biz_cus_service_important_degree` VALUES ('33', '91', '0', '1', '回访', null, null, null, null, null, null);
INSERT INTO `biz_cus_service_important_degree` VALUES ('34', '92', '91', '2', '回访咨询', null, null, null, null, null, null);
INSERT INTO `biz_cus_service_important_degree` VALUES ('35', '93', '91', '2', '回访投诉', null, null, null, null, null, null);
INSERT INTO `biz_cus_service_important_degree` VALUES ('36', '94', '89', '2', '来电咨询', null, null, null, null, null, null);
INSERT INTO `biz_cus_service_important_degree` VALUES ('37', '95', '89', '2', '上门咨询', null, null, null, null, null, null);
INSERT INTO `biz_cus_service_important_degree` VALUES ('38', '96', '90', '2', '来电投诉', null, null, null, null, null, null);
INSERT INTO `biz_cus_service_important_degree` VALUES ('39', '97', '90', '2', '多次来电', null, null, null, null, null, null);
INSERT INTO `biz_cus_service_important_degree` VALUES ('40', '98', '90', '2', '上门投诉', null, null, null, null, null, null);
INSERT INTO `biz_cus_service_important_degree` VALUES ('41', '99', '90', '2', '涉外投诉', null, null, null, null, null, null);
INSERT INTO `biz_cus_service_important_degree` VALUES ('42', '100', '90', '2', '媒体曝光', null, null, null, null, null, null);
INSERT INTO `biz_cus_service_important_degree` VALUES ('43', '101', '90', '2', '到期回访未执行1', null, null, null, null, null, null);
INSERT INTO `biz_cus_service_important_degree` VALUES ('44', '102', '90', '2', '到期回访未执行2', null, null, null, null, null, null);
INSERT INTO `biz_cus_service_important_degree` VALUES ('45', '103', '90', '2', '其他', null, null, null, null, null, null);
