/*
Navicat MySQL Data Transfer

Source Server         : 1231
Source Server Version : 80023
Source Host           : localhost:3306
Source Database       : sport

Target Server Type    : MYSQL
Target Server Version : 80023
File Encoding         : 65001

Date: 2021-04-15 15:25:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for calories
-- ----------------------------
DROP TABLE IF EXISTS `calories`;
CREATE TABLE `calories` (
  `name` varchar(255) NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `calories` int NOT NULL,
  `type` varchar(20) NOT NULL,
  `unit` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of calories
-- ----------------------------
INSERT INTO `calories` VALUES ('芹菜', '2', '10', '蔬菜', '克');
INSERT INTO `calories` VALUES ('南瓜', '3', '100', '水果', '个');
INSERT INTO `calories` VALUES ('鸡蛋', '6', '50', '其他', '个');
INSERT INTO `calories` VALUES ('牛肉', '7', '200', '肉类', '克');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `cid` int NOT NULL AUTO_INCREMENT,
  `cname` varchar(20) NOT NULL,
  `num` int NOT NULL,
  `remake` varchar(20) DEFAULT NULL,
  `uid` int NOT NULL,
  `weekday` varchar(30) NOT NULL,
  `address` varchar(30) NOT NULL,
  `time` varchar(30) NOT NULL,
  `lesson` int NOT NULL,
  `status` tinyint DEFAULT NULL,
  PRIMARY KEY (`cid`),
  KEY `uid` (`uid`),
  CONSTRAINT `uid` FOREIGN KEY (`uid`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', '减肥训练', '43', '减肥', '1', '星期一', '训练室1', '10:00-11:30', '20', '1');
INSERT INTO `course` VALUES ('4', '测试', '0', '1', '1', '星期二', '训练室2', '10:00-11:30', '1', '1');
INSERT INTO `course` VALUES ('8', '123', '23', '1', '2', '星期一', '训练室1', '14:00-15:30', '1', '1');
INSERT INTO `course` VALUES ('9', '康复训练', '13', '12', '1', '星期四', '训练室3', '14:00-15:30', '20', '1');
INSERT INTO `course` VALUES ('10', '塑性健身', '18', '', '1', '星期四', '训练室2', '10:00-11:30', '12', '1');
INSERT INTO `course` VALUES ('11', '瑜伽训练', '49', '123', '1', '星期三', '训练室3', '16:00-17:30', '20', '1');
INSERT INTO `course` VALUES ('12', '游泳', '19', '7', '1', '星期六', '训练室1', '08:00-09:30', '7', '1');
INSERT INTO `course` VALUES ('13', '瑜伽', '19', '7', '1', '星期六', '训练室1', '16:00-17:30', '20', '1');
INSERT INTO `course` VALUES ('14', '瑜伽', '19', '7', '1', '星期六', '训练室1', '10:00-11:30', '20', '1');
INSERT INTO `course` VALUES ('15', 'java', '59', '123', '4', '星期一', '训练室1', '08:00-09:30', '2', '1');
INSERT INTO `course` VALUES ('16', 'java', '20', '123', '1', '星期天', '训练室2', '14:00-15:30', '1', '0');

-- ----------------------------
-- Table structure for foodplanmain
-- ----------------------------
DROP TABLE IF EXISTS `foodplanmain`;
CREATE TABLE `foodplanmain` (
  `id` int NOT NULL AUTO_INCREMENT,
  `remark` varchar(255) DEFAULT NULL,
  `meals` varchar(255) CHARACTER SET gbk COLLATE gbk_chinese_ci DEFAULT NULL,
  `userName` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of foodplanmain
-- ----------------------------
INSERT INTO `foodplanmain` VALUES ('18', '123', '午餐', 'coach');
INSERT INTO `foodplanmain` VALUES ('22', '智能生成的饮食计划', '午餐', 'coach');

-- ----------------------------
-- Table structure for kidmenu
-- ----------------------------
DROP TABLE IF EXISTS `kidmenu`;
CREATE TABLE `kidmenu` (
  `id` int NOT NULL,
  `title` varchar(11) DEFAULT NULL,
  `path` varchar(20) DEFAULT NULL,
  `mid` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `mid` (`mid`),
  CONSTRAINT `kidmenu_ibfk_1` FOREIGN KEY (`mid`) REFERENCES `menu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kidmenu
-- ----------------------------
INSERT INTO `kidmenu` VALUES ('101', '用户列表', '/user', '100');
INSERT INTO `kidmenu` VALUES ('102', '角色管理', '/rights', '100');
INSERT INTO `kidmenu` VALUES ('103', '运动计划', '/sport', '200');
INSERT INTO `kidmenu` VALUES ('104', '卡路里', '/goods', '300');
INSERT INTO `kidmenu` VALUES ('108', '课程管理', '/course', '200');
INSERT INTO `kidmenu` VALUES ('109', '课程审核', '/verify', '200');
INSERT INTO `kidmenu` VALUES ('110', '课程选择', '/source', '200');
INSERT INTO `kidmenu` VALUES ('111', '我的选课', '/mycourse', '200');
INSERT INTO `kidmenu` VALUES ('112', '课表查看', '/mysourse', '200');
INSERT INTO `kidmenu` VALUES ('114', '个人信息', '/info', '100');
INSERT INTO `kidmenu` VALUES ('115', '饮食计划', '/plan', '300');

-- ----------------------------
-- Table structure for loginrecord
-- ----------------------------
DROP TABLE IF EXISTS `loginrecord`;
CREATE TABLE `loginrecord` (
  `username` varchar(255) NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `time` varchar(30) NOT NULL,
  `ip` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `name` (`username`),
  CONSTRAINT `name` FOREIGN KEY (`username`) REFERENCES `sys_user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of loginrecord
-- ----------------------------
INSERT INTO `loginrecord` VALUES ('admin', '1', '2020-10-26', '192.168.1.135');
INSERT INTO `loginrecord` VALUES ('admin', '4', '2020-10-27', '192.168.1.135');
INSERT INTO `loginrecord` VALUES ('admin', '5', '2020-10-27', '192.168.1.135');
INSERT INTO `loginrecord` VALUES ('admin', '6', '2020-10-27', '192.168.1.135');
INSERT INTO `loginrecord` VALUES ('coach', '7', '2020-10-27', '192.168.1.135');
INSERT INTO `loginrecord` VALUES ('coach', '8', '2020-10-27', '192.168.1.135');
INSERT INTO `loginrecord` VALUES ('fangling', '9', '2020-10-27', '192.168.1.135');
INSERT INTO `loginrecord` VALUES ('admin', '10', '2020-10-27', '192.168.1.135');
INSERT INTO `loginrecord` VALUES ('coach', '11', '2020-10-27', '192.168.1.135');
INSERT INTO `loginrecord` VALUES ('admin', '12', '2020-10-27', '192.168.1.135');
INSERT INTO `loginrecord` VALUES ('admin', '13', '2020-10-27', '192.168.1.135');
INSERT INTO `loginrecord` VALUES ('admin', '14', '2020-10-27', '192.168.1.135');
INSERT INTO `loginrecord` VALUES ('admin', '15', '2020-10-27', '192.168.1.135');
INSERT INTO `loginrecord` VALUES ('admin', '19', '2020-10-27', '192.168.1.135');
INSERT INTO `loginrecord` VALUES ('fangling', '20', '2020-10-27', '192.168.1.135');
INSERT INTO `loginrecord` VALUES ('admin', '21', '2020-10-27', '192.168.1.135');
INSERT INTO `loginrecord` VALUES ('fangling', '22', '2020-10-27', '192.168.1.135');
INSERT INTO `loginrecord` VALUES ('coach', '23', '2020-10-27', '192.168.1.135');
INSERT INTO `loginrecord` VALUES ('admin', '24', '2020-10-27', '192.168.1.135');
INSERT INTO `loginrecord` VALUES ('admin', '25', '2020-10-28', '192.168.1.135');
INSERT INTO `loginrecord` VALUES ('fangling', '26', '2020-10-28', '192.168.1.135');
INSERT INTO `loginrecord` VALUES ('fangling', '27', '2020-10-28', '192.168.1.135');
INSERT INTO `loginrecord` VALUES ('fangling', '28', '2020-10-28', '192.168.1.135');
INSERT INTO `loginrecord` VALUES ('coach', '29', '2020-10-28', '192.168.1.135');
INSERT INTO `loginrecord` VALUES ('coach', '30', '2020-10-28', '192.168.1.135');
INSERT INTO `loginrecord` VALUES ('admin', '31', '2020-10-28', '192.168.1.135');
INSERT INTO `loginrecord` VALUES ('fangling', '32', '2020-10-28', '192.168.1.135');
INSERT INTO `loginrecord` VALUES ('admin', '33', '2020-10-28', '192.168.1.135');
INSERT INTO `loginrecord` VALUES ('fangling', '34', '2020-10-28', '192.168.1.135');
INSERT INTO `loginrecord` VALUES ('system', '35', '2021-04-06', '172.16.1.134');
INSERT INTO `loginrecord` VALUES ('admin', '36', '2021-04-06', '172.16.1.134');
INSERT INTO `loginrecord` VALUES ('admin', '37', '2021-04-07', '172.16.1.134');
INSERT INTO `loginrecord` VALUES ('test2', '38', '2021-04-07', '172.16.1.134');
INSERT INTO `loginrecord` VALUES ('admin', '39', '2021-04-07', '172.16.1.134');
INSERT INTO `loginrecord` VALUES ('system', '40', '2021-04-07', '172.16.1.134');
INSERT INTO `loginrecord` VALUES ('test2', '41', '2021-04-07', '172.16.1.134');
INSERT INTO `loginrecord` VALUES ('coach', '42', '2021-04-07', '172.16.1.134');
INSERT INTO `loginrecord` VALUES ('coach', '43', '2021-04-07', '172.16.1.134');
INSERT INTO `loginrecord` VALUES ('admin', '44', '2021-04-07', '172.16.1.134');
INSERT INTO `loginrecord` VALUES ('admin', '45', '2021-04-07', '172.16.1.134');
INSERT INTO `loginrecord` VALUES ('admin', '46', '2021-04-07', '172.16.1.134');
INSERT INTO `loginrecord` VALUES ('admin', '47', '2021-04-07', '172.16.1.134');
INSERT INTO `loginrecord` VALUES ('admin', '48', '2021-04-07', '172.16.1.134');
INSERT INTO `loginrecord` VALUES ('admin', '49', '2021-04-07', '172.16.1.134');
INSERT INTO `loginrecord` VALUES ('admin', '50', '2021-04-08', '172.16.1.134');
INSERT INTO `loginrecord` VALUES ('coach', '51', '2021-04-08', '172.16.1.134');
INSERT INTO `loginrecord` VALUES ('admin', '52', '2021-04-08', '172.16.1.134');
INSERT INTO `loginrecord` VALUES ('test2', '53', '2021-04-08', '172.16.1.134');
INSERT INTO `loginrecord` VALUES ('coach', '54', '2021-04-08', '172.16.1.134');
INSERT INTO `loginrecord` VALUES ('coach', '55', '2021-04-12', '172.16.1.64');
INSERT INTO `loginrecord` VALUES ('admin', '56', '2021-04-12', '172.16.1.64');
INSERT INTO `loginrecord` VALUES ('admin', '57', '2021-04-13', '172.16.1.64');
INSERT INTO `loginrecord` VALUES ('admin', '58', '2021-04-13', '172.16.1.64');
INSERT INTO `loginrecord` VALUES ('admin', '59', '2021-04-14', '172.16.1.64');
INSERT INTO `loginrecord` VALUES ('coach', '60', '2021-04-14', '172.16.1.64');
INSERT INTO `loginrecord` VALUES ('admin', '61', '2021-04-14', '172.16.1.64');
INSERT INTO `loginrecord` VALUES ('coach', '62', '2021-04-14', '172.16.1.64');
INSERT INTO `loginrecord` VALUES ('coach', '63', '2021-04-15', '172.16.1.64');
INSERT INTO `loginrecord` VALUES ('admin', '64', '2021-04-15', '172.16.1.64');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int NOT NULL,
  `title` varchar(11) DEFAULT NULL,
  `path` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('100', '权限管理', '/admin');
INSERT INTO `menu` VALUES ('200', '学习平台', '/use');
INSERT INTO `menu` VALUES ('300', '饮食管理', '/calories');

-- ----------------------------
-- Table structure for plandetail
-- ----------------------------
DROP TABLE IF EXISTS `plandetail`;
CREATE TABLE `plandetail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `mainId` int NOT NULL,
  `calories` int NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of plandetail
-- ----------------------------
INSERT INTO `plandetail` VALUES ('29', '18', '2', '1');
INSERT INTO `plandetail` VALUES ('30', '18', '3', '1');
INSERT INTO `plandetail` VALUES ('31', '18', '6', '1');
INSERT INTO `plandetail` VALUES ('32', '18', '7', '1');
INSERT INTO `plandetail` VALUES ('45', '22', '2', '4');
INSERT INTO `plandetail` VALUES ('46', '22', '6', '4');
INSERT INTO `plandetail` VALUES ('47', '22', '3', '4');
INSERT INTO `plandetail` VALUES ('48', '22', '7', '4');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `roleId` int NOT NULL,
  `roleName` varchar(12) DEFAULT NULL,
  `sort` int DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '管理员', '1', '1');
INSERT INTO `role` VALUES ('2', '普通用户', '3', '1');
INSERT INTO `role` VALUES ('3', '教练', '2', '1');

-- ----------------------------
-- Table structure for rolemenu
-- ----------------------------
DROP TABLE IF EXISTS `rolemenu`;
CREATE TABLE `rolemenu` (
  `id` int NOT NULL AUTO_INCREMENT,
  `roleId` int DEFAULT NULL,
  `menuid` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `roleId` (`roleId`),
  KEY `menuid` (`menuid`),
  CONSTRAINT `rolemenu_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `role` (`roleId`),
  CONSTRAINT `rolemenu_ibfk_2` FOREIGN KEY (`menuid`) REFERENCES `kidmenu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=337 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rolemenu
-- ----------------------------
INSERT INTO `rolemenu` VALUES ('319', '1', '101');
INSERT INTO `rolemenu` VALUES ('320', '1', '102');
INSERT INTO `rolemenu` VALUES ('321', '1', '114');
INSERT INTO `rolemenu` VALUES ('322', '1', '103');
INSERT INTO `rolemenu` VALUES ('323', '1', '109');
INSERT INTO `rolemenu` VALUES ('324', '1', '104');
INSERT INTO `rolemenu` VALUES ('325', '1', '115');
INSERT INTO `rolemenu` VALUES ('326', '3', '114');
INSERT INTO `rolemenu` VALUES ('327', '3', '103');
INSERT INTO `rolemenu` VALUES ('328', '3', '108');
INSERT INTO `rolemenu` VALUES ('329', '3', '104');
INSERT INTO `rolemenu` VALUES ('330', '3', '115');
INSERT INTO `rolemenu` VALUES ('331', '2', '114');
INSERT INTO `rolemenu` VALUES ('332', '2', '103');
INSERT INTO `rolemenu` VALUES ('333', '2', '110');
INSERT INTO `rolemenu` VALUES ('334', '2', '111');
INSERT INTO `rolemenu` VALUES ('335', '2', '112');
INSERT INTO `rolemenu` VALUES ('336', '2', '115');

-- ----------------------------
-- Table structure for source
-- ----------------------------
DROP TABLE IF EXISTS `source`;
CREATE TABLE `source` (
  `sid` int NOT NULL AUTO_INCREMENT,
  `cid` int NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`sid`),
  KEY `cid` (`cid`) USING BTREE,
  KEY `username` (`username`),
  CONSTRAINT `cid` FOREIGN KEY (`cid`) REFERENCES `course` (`cid`),
  CONSTRAINT `username` FOREIGN KEY (`username`) REFERENCES `sys_user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of source
-- ----------------------------
INSERT INTO `source` VALUES ('11', '1', 'test2');
INSERT INTO `source` VALUES ('14', '8', 'fangling');
INSERT INTO `source` VALUES ('15', '10', 'fangling');
INSERT INTO `source` VALUES ('17', '1', 'test4');
INSERT INTO `source` VALUES ('18', '1', 'test5');
INSERT INTO `source` VALUES ('19', '1', 'test6');
INSERT INTO `source` VALUES ('20', '1', 'test7');
INSERT INTO `source` VALUES ('21', '1', 'test8');
INSERT INTO `source` VALUES ('27', '11', 'fangling');
INSERT INTO `source` VALUES ('28', '9', 'fangling');
INSERT INTO `source` VALUES ('30', '1', 'fangling');
INSERT INTO `source` VALUES ('31', '14', 'fangling');
INSERT INTO `source` VALUES ('38', '4', 'fangling');
INSERT INTO `source` VALUES ('41', '10', 'test2');
INSERT INTO `source` VALUES ('42', '15', 'test2');
INSERT INTO `source` VALUES ('43', '8', 'test2');
INSERT INTO `source` VALUES ('44', '12', 'test2');
INSERT INTO `source` VALUES ('45', '13', 'test2');
INSERT INTO `source` VALUES ('46', '9', 'test2');

-- ----------------------------
-- Table structure for sportplay
-- ----------------------------
DROP TABLE IF EXISTS `sportplay`;
CREATE TABLE `sportplay` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(12) DEFAULT NULL,
  `site` varchar(20) DEFAULT NULL,
  `time` date DEFAULT NULL,
  `remake` varchar(50) DEFAULT NULL,
  `userId` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  KEY `id` (`id`),
  CONSTRAINT `userId` FOREIGN KEY (`userId`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sportplay
-- ----------------------------
INSERT INTO `sportplay` VALUES ('1', '羽毛球', '安大体育馆', '2020-09-23', '123', '1');
INSERT INTO `sportplay` VALUES ('2', '篮球', '安大体育馆', '2020-09-07', '123', '1');
INSERT INTO `sportplay` VALUES ('3', 'aaaaaaa', '1aaa', '2020-09-18', '2', '1');
INSERT INTO `sportplay` VALUES ('4', '游泳', '体育馆', '2020-09-16', '', '1');
INSERT INTO `sportplay` VALUES ('5', '123', '123', '2020-09-01', '1', '1');
INSERT INTO `sportplay` VALUES ('14', '跑步', '体育馆', '2020-09-16', '', '1');
INSERT INTO `sportplay` VALUES ('15', '篮球', '123', '2020-10-23', '123', '108');
INSERT INTO `sportplay` VALUES ('16', '123', '123', '2021-04-06', '1', '10');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `roleId` int DEFAULT NULL,
  `state` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_2` (`username`),
  KEY `roleId` (`roleId`),
  KEY `id` (`id`) USING BTREE,
  KEY `username` (`username`),
  CONSTRAINT `sys_user_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `role` (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'coach', '123456', '111', '3', '1');
INSERT INTO `sys_user` VALUES ('2', 'system', '123456', '1111', '1', '1');
INSERT INTO `sys_user` VALUES ('4', 'admin', '123456', 'aaaas', '1', '1');
INSERT INTO `sys_user` VALUES ('8', 'test0', '111111', '222', '3', '1');
INSERT INTO `sys_user` VALUES ('9', 'test1', '123456', '1887575541qq.com', '3', '1');
INSERT INTO `sys_user` VALUES ('10', 'test2', '111111', '1887575542qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('11', 'test3', '123456', '1887575543qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('12', 'test4', '123456', '1887575544qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('13', 'test5', '123456', '1887575545qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('14', 'test6', '123456', '1887575546qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('15', 'test7', '123456', '1887575547qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('16', 'test8', '123456', '1887575548qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('17', 'test9', '123456', '1887575549qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('18', 'test10', '123456', '18875755410qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('19', 'test11', '123456', '18875755411qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('20', 'test12', '123456', '18875755412qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('21', 'test13', '123456', '18875755413qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('22', 'test14', '123456', '18875755414qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('23', 'test15', '123456', '18875755415qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('24', 'test16', '123456', '18875755416qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('25', 'test17', '123456', '18875755417qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('26', 'test18', '123456', '18875755418qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('27', 'test19', '123456', '18875755419qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('28', 'test20', '123456', '18875755420qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('29', 'test21', '123456', '18875755421qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('30', 'test22', '123456', '18875755422qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('31', 'test23', '123456', '18875755423qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('32', 'test24', '123456', '18875755424qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('33', 'test25', '123456', '18875755425qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('34', 'test26', '123456', '18875755426qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('35', 'test27', '123456', '18875755427qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('36', 'test28', '123456', '18875755428qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('37', 'test29', '123456', '18875755429qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('38', 'test30', '123456', '18875755430qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('39', 'test31', '123456', '18875755431qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('40', 'test32', '123456', '18875755432qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('41', 'test33', '123456', '18875755433qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('42', 'test34', '123456', '18875755434qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('43', 'test35', '123456', '18875755435qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('44', 'test36', '123456', '18875755436qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('45', 'test37', '123456', '18875755437qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('46', 'test38', '123456', '18875755438qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('47', 'test39', '123456', '18875755439qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('48', 'test40', '123456', '18875755440qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('49', 'test41', '123456', '18875755441qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('50', 'test42', '123456', '18875755442qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('51', 'test43', '123456', '18875755443qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('52', 'test44', '123456', '18875755444qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('53', 'test45', '123456', '18875755445qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('54', 'test46', '123456', '18875755446qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('55', 'test47', '123456', '18875755447qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('56', 'test48', '123456', '18875755448qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('57', 'test49', '123456', '18875755449qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('58', 'test50', '123456', '18875755450qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('59', 'test51', '123456', '18875755451qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('60', 'test52', '123456', '18875755452qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('61', 'test53', '123456', '18875755453qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('62', 'test54', '123456', '18875755454qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('63', 'test55', '123456', '18875755455qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('64', 'test56', '123456', '18875755456qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('65', 'test57', '123456', '18875755457qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('66', 'test58', '123456', '18875755458qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('67', 'test59', '123456', '18875755459qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('68', 'test60', '123456', '18875755460qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('69', 'test61', '123456', '18875755461qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('70', 'test62', '123456', '18875755462qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('71', 'test63', '123456', '18875755463qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('72', 'test64', '123456', '18875755464qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('73', 'test65', '123456', '18875755465qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('74', 'test66', '123456', '18875755466qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('75', 'test67', '123456', '18875755467qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('76', 'test68', '123456', '18875755468qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('77', 'test69', '123456', '18875755469qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('78', 'test70', '123456', '18875755470qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('79', 'test71', '123456', '18875755471qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('80', 'test72', '123456', '18875755472qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('81', 'test73', '123456', '18875755473qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('82', 'test74', '123456', '18875755474qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('83', 'test75', '123456', '18875755475qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('84', 'test76', '123456', '18875755476qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('85', 'test77', '123456', '18875755477qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('86', 'test78', '123456', '18875755478qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('87', 'test79', '123456', '18875755479qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('88', 'test80', '123456', '18875755480qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('89', 'test81', '123456', '18875755481qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('90', 'test82', '123456', '18875755482qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('91', 'test83', '123456', '18875755483qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('92', 'test84', '123456', '18875755484qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('93', 'test85', '123456', '18875755485qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('94', 'test86', '123456', '18875755486qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('95', 'test87', '123456', '18875755487qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('96', 'test88', '123456', '18875755488qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('97', 'test89', '123456', '18875755489qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('98', 'test90', '123456', '18875755490qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('99', 'test91', '123456', '18875755491qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('100', 'test92', '123456', '18875755492qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('101', 'test93', '123456', '18875755493qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('102', 'test94', '123456', '18875755494qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('103', 'test95', '123456', '18875755495qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('104', 'test96', '123456', '18875755496qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('105', 'test97', '123456', '18875755497qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('106', 'test98', '123456', '18875755498qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('107', 'test99', '123456', '18875755499qq.com', '2', '1');
INSERT INTO `sys_user` VALUES ('108', 'fangling', '123456', '1uuhjhj', '2', '1');
INSERT INTO `sys_user` VALUES ('109', '123', '111111', '123', '2', '1');
INSERT INTO `sys_user` VALUES ('110', 'test100', '123456', '18875755418qq.com', '2', '1');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `trueName` varchar(100) NOT NULL,
  `username` varchar(255) NOT NULL,
  `sex` varchar(10) NOT NULL,
  `birth` varchar(100) NOT NULL,
  `address` varchar(100) NOT NULL,
  `phone` varchar(30) NOT NULL,
  `idcard` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `uname` (`username`),
  CONSTRAINT `uname` FOREIGN KEY (`username`) REFERENCES `sys_user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', '张三', 'admin', '男', '1996-08-07', '安徽安庆', '18895681451', '340828199608071018');
INSERT INTO `user_info` VALUES ('2', '彭于晏', 'fangling', '男', '2000-10-10T16:00:00.000Z', '安徽合肥', '18856547451', '340585199602041512');
INSERT INTO `user_info` VALUES ('3', '李四', 'coach', '男', '1990-06-04T15:00:00.000Z', '安徽合肥', '18895681264', '3408294124645541');
INSERT INTO `user_info` VALUES ('4', '张三', 'test2', '女', '2021-04-12T16:00:00.000Z', '安徽合肥', '1990877122', '1234221111');
