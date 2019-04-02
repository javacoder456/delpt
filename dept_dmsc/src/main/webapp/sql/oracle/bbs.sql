/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : bbs

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2018-09-30 18:05:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for carport
-- ----------------------------
DROP TABLE IF EXISTS `carport`;
CREATE TABLE `carport` (
  `carportid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `enable` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`carportid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of carport
-- ----------------------------

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `messageid` int(11) DEFAULT NULL,
  `id` int(11) DEFAULT NULL,
  `sender` varchar(60) DEFAULT NULL,
  `titile` varchar(1000) DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `sendtime` datetime DEFAULT NULL,
  `status` varchar(2) DEFAULT NULL,
  `enable` varchar(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice
-- ----------------------------

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `orderid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `dcid` int(11) DEFAULT NULL,
  `createid` int(11) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  `costtime` datetime DEFAULT NULL,
  `count` varchar(4) DEFAULT NULL,
  `discount` varchar(60) DEFAULT NULL,
  `enable` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`orderid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for price
-- ----------------------------
DROP TABLE IF EXISTS `price`;
CREATE TABLE `price` (
  `priceid` int(11) NOT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  `cartype` varchar(6) DEFAULT NULL,
  `createid` int(11) DEFAULT NULL,
  `createname` varchar(60) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `enable` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`priceid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of price
-- ----------------------------

-- ----------------------------
-- Table structure for role_authority
-- ----------------------------
DROP TABLE IF EXISTS `role_authority`;
CREATE TABLE `role_authority` (
  `connectionid` int(11) NOT NULL,
  `roleid` int(11) NOT NULL,
  `authorityid` int(11) NOT NULL,
  `createid` int(11) NOT NULL,
  `createname` varchar(80) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `enable` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`connectionid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_authority
-- ----------------------------
INSERT INTO `role_authority` VALUES ('1', '1', '1', '1', 'ADMIN', null, '1');

-- ----------------------------
-- Table structure for sequence
-- ----------------------------
DROP TABLE IF EXISTS `sequence`;
CREATE TABLE `sequence` (
  `seq_name` varchar(50) NOT NULL,
  `current_val` int(11) NOT NULL,
  `increment_val` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`seq_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sequence
-- ----------------------------
INSERT INTO `sequence` VALUES ('seq_authorid', '20180524', '2');
INSERT INTO `sequence` VALUES ('seq_fatherid', '20180531', '1');

-- ----------------------------
-- Table structure for sms
-- ----------------------------
DROP TABLE IF EXISTS `sms`;
CREATE TABLE `sms` (
  `id` int(11) DEFAULT NULL,
  `username` varchar(60) DEFAULT NULL,
  `sender` varchar(60) DEFAULT NULL,
  `read` varchar(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sms
-- ----------------------------

-- ----------------------------
-- Table structure for sys_authority
-- ----------------------------
DROP TABLE IF EXISTS `sys_authority`;
CREATE TABLE `sys_authority` (
  `authorityid` int(11) NOT NULL AUTO_INCREMENT,
  `authorityname` varchar(60) DEFAULT NULL,
  `fatherid` int(11) NOT NULL,
  `url` varchar(100) DEFAULT NULL,
  `icon` varchar(60) DEFAULT NULL,
  `children` varchar(60) DEFAULT NULL,
  `createid` int(11) NOT NULL,
  `createname` varchar(60) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `modiftytime` datetime DEFAULT NULL,
  `enable` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`authorityid`)
) ENGINE=InnoDB AUTO_INCREMENT=2018012639 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_authority
-- ----------------------------
INSERT INTO `sys_authority` VALUES ('2018012624', 'tets', '20180529', null, null, '0', '1', '', '2018-05-24 18:02:33', null, '1');
INSERT INTO `sys_authority` VALUES ('2018012626', '系统权限管理', '3', null, 'elements', '1', '1', '', '2018-05-25 11:53:02', null, '1');
INSERT INTO `sys_authority` VALUES ('2018012627', '用户注册', '2018012626', 'userRegister.do', null, '2', '1', '', '2018-05-25 11:54:06', null, '1');
INSERT INTO `sys_authority` VALUES ('2018012628', '角色注册', '2018012626', 'systemSetup.do', null, '2', '1', '', '2018-05-25 14:02:52', null, '1');
INSERT INTO `sys_authority` VALUES ('2018012629', '资源管理', '2018012626', 'systemSetup!setUpAuths.do', null, '2', '1', '', '2018-05-25 14:03:32', null, '1');
INSERT INTO `sys_authority` VALUES ('2018012631', '系统消息', '3', 'message!getmessages.do', null, '1', '1', '', '2018-05-25 14:06:36', null, '1');
INSERT INTO `sys_authority` VALUES ('2018012634', '文件上传', '20180531', '', 'trash', '1', '1', '', '2018-05-25 14:15:39', null, '1');
INSERT INTO `sys_authority` VALUES ('2018012635', '测试文件上传', '2018012634', 'systemFileAction.do', null, '2', '1', '', '2018-05-25 14:17:13', null, '1');
INSERT INTO `sys_authority` VALUES ('2018012636', '车位管理', '1', null, 'support', '1', '1', '', '2018-05-25 14:20:42', null, '1');
INSERT INTO `sys_authority` VALUES ('2018012637', '车位预约', '2018012636', 'parkingAction.do', null, '2', '1', '', '2018-05-25 14:22:32', null, '1');
INSERT INTO `sys_authority` VALUES ('2018012638', 'ActiveMQ测试', '2018012631', 'jmsAction.do', null, '2', '1', '', '2018-09-10 10:35:28', null, '1');

-- ----------------------------
-- Table structure for sys_fatherauthority
-- ----------------------------
DROP TABLE IF EXISTS `sys_fatherauthority`;
CREATE TABLE `sys_fatherauthority` (
  `fatherid` int(11) NOT NULL,
  `childrenid` int(11) DEFAULT NULL,
  `fathername` varchar(100) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `icon` varchar(60) DEFAULT NULL,
  `createid` int(11) DEFAULT NULL,
  `createname` varchar(60) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `modiftytime` datetime DEFAULT NULL,
  `enable` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`fatherid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_fatherauthority
-- ----------------------------
INSERT INTO `sys_fatherauthority` VALUES ('1', '1', '首页', '/manageblog.html', 'icon-flatscreen', '1', 'ADMIN', null, null, '1');
INSERT INTO `sys_fatherauthority` VALUES ('2', '1', '消息管理', '/manageblog.html', 'icon-message', '1', 'ADMIN', null, null, '1');
INSERT INTO `sys_fatherauthority` VALUES ('3', '1', '系统管理', '/manageblog.html', 'icon-pencil', '1', 'ADMIN', null, null, '1');
INSERT INTO `sys_fatherauthority` VALUES ('20180531', null, 'Test', null, 'icon-message', '1', '', '2018-05-25 14:13:24', null, '1');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `roleid` int(11) NOT NULL,
  `rolename` varchar(60) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `admin` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `createid` int(11) DEFAULT NULL,
  `createname` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `modiftytime` datetime DEFAULT NULL,
  `enable` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`roleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'ADMIN', null, '1', '1', 'ADMIN', null, null, '1');
INSERT INTO `sys_role` VALUES ('2', 'USER', null, null, '1', 'ADMIN', null, null, '1');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `userid` int(11) NOT NULL,
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'ADMIN', 'ADMIN', '123456789', '599204540@qq.com', 'DA11', '1', '255', '0', '0', 'ADMIN', null, null, '1');
INSERT INTO `sys_user` VALUES ('2', 'USER', 'USER', '123456789', '2132131', 'D111', '1', '11', '0', null, null, null, null, '1');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `connectionid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `roleid` int(11) NOT NULL,
  `createid` int(11) DEFAULT NULL,
  `createname` varchar(60) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `enable` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`connectionid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '1', '1', 'ADMIN', null, '1');
INSERT INTO `user_role` VALUES ('2', '2', '2', '1', 'ADMIN', null, '1');

-- ----------------------------
-- Function structure for currval
-- ----------------------------
DROP FUNCTION IF EXISTS `currval`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `currval`(v_seq_name VARCHAR(50)) RETURNS int(11)
begin  
    declare value integer;  
    set value = 0;
    select current_val into value  from sequence where seq_name = v_seq_name;  
   return value;  
end
;;
DELIMITER ;

-- ----------------------------
-- Function structure for nextval
-- ----------------------------
DROP FUNCTION IF EXISTS `nextval`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `nextval`(v_seq_name VARCHAR(50)) RETURNS int(11)
begin  
    update sequence set current_val = current_val + increment_val  where seq_name = v_seq_name;  
    return currval(v_seq_name);  
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `tri_fatherid`;
DELIMITER ;;
CREATE TRIGGER `tri_fatherid` BEFORE INSERT ON `sys_fatherauthority` FOR EACH ROW BEGIN  
set NEW.fatherid = nextval('seq_fatherid');  
END
;;
DELIMITER ;
