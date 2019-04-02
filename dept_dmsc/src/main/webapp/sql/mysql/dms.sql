/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : last

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2018-12-21 17:08:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `senderid` int(11) DEFAULT NULL,
  `sendername` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `receiverid` int(11) NOT NULL,
  `type` varchar(10) DEFAULT NULL,
  `titile` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `content` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sendtime` datetime DEFAULT NULL,
  `status` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for queryconfig
-- ----------------------------
DROP TABLE IF EXISTS `queryconfig`;
CREATE TABLE `queryconfig` (
  `transtype` varchar(10) NOT NULL,
  `datasource` varchar(30) DEFAULT NULL,
  `sqlcontext` varchar(255) DEFAULT NULL,
  `callback` varchar(50) DEFAULT NULL,
  `recode` varchar(10) DEFAULT NULL,
  `redesc` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for sys_authority
-- ----------------------------
DROP TABLE IF EXISTS `sys_authority`;
CREATE TABLE `sys_authority` (
  `roleid` int(11) NOT NULL,
  `authorityid` int(11) NOT NULL AUTO_INCREMENT,
  `authorityname` varchar(60) DEFAULT NULL,
  `fatherid` int(11) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `icon` varchar(60) DEFAULT NULL,
  `children` varchar(60) DEFAULT NULL,
  `createid` int(11) DEFAULT NULL,
  `createname` varchar(60) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `modiftytime` datetime DEFAULT NULL,
  `enable` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`authorityid`)
) ENGINE=InnoDB AUTO_INCREMENT=2018012708 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `roleid` int(11) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(60) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `auths` varchar(1000) DEFAULT NULL,
  `isadmin` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `createid` int(11) DEFAULT NULL,
  `createname` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `modiftytime` datetime DEFAULT NULL,
  `enable` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`roleid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `roleid` int(11) DEFAULT NULL,
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `phonenumber` int(11) DEFAULT NULL,
  `email` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `lockednum` int(11) DEFAULT NULL,
  `unlocked` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `faultrole` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `registertime` datetime DEFAULT NULL,
  `lastmodifytime` datetime DEFAULT NULL,
  `enable` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=239 DEFAULT CHARSET=utf8;
