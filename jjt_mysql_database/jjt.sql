
--用户表

--管理员表

--卡券表

--推荐者表

--被推荐者表

--snstoken表

--核销记录表

--

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for jjt_authority
-- ----------------------------
DROP TABLE IF EXISTS `jjt_authority`;
CREATE TABLE `jjt_authority` (
  `ROLE_ID` varchar(20) NOT NULL,
  `AUTHORITY_CODE` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jjt_authority
-- ----------------------------

-- ----------------------------
-- Table structure for jjt_authority_code
-- ----------------------------
DROP TABLE IF EXISTS `jjt_authority_code`;
CREATE TABLE `jjt_authority_code` (
  `CODE` varchar(50) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  PRIMARY KEY (`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jjt_authority_code
-- ----------------------------

-- ----------------------------
-- Table structure for jjt_role
-- ----------------------------
DROP TABLE IF EXISTS `jjt_role`;
CREATE TABLE `jjt_role` (
  `role_id` varchar(20) NOT NULL,
  `role_name` varchar(50) NOT NULL,
  `create_user` varchar(20) NOT NULL,
  `create_date` datetime NOT NULL,
  `modify_user` varchar(20) NOT NULL,
  `modify_date` datetime NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jjt_role
-- ----------------------------

-- ----------------------------
-- Table structure for jjt_user_admin
-- ----------------------------
DROP TABLE IF EXISTS `jjt_user_admin`;
CREATE TABLE `jjt_user_admin` (
  `admin_id` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `password` varchar(128) NOT NULL,
  `role_id` varchar(8) NOT NULL,
  `password_overdue_date` varchar(8) NOT NULL,
  `mail` varchar(200) DEFAULT NULL,
  `portrait` varchar(200) DEFAULT NULL,
  `delete_flag` char(1) NOT NULL,
  `create_user` varchar(20) NOT NULL,
  `create_date` datetime NOT NULL,
  `modify_user` varchar(20) NOT NULL,
  `modify_date` datetime NOT NULL,
  PRIMARY KEY (`admin_id`),
  UNIQUE KEY `admin_id` (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jjt_user_admin
-- ----------------------------

-- ----------------------------
-- Table structure for jjt_wx_token
-- ----------------------------
DROP TABLE IF EXISTS `jjt_wx_token`;
CREATE TABLE `jjt_wx_token` (
  `access_token` varchar(255) NOT NULL,
  `jsapi_ticket` varchar(255) DEFAULT NULL,
  `expires_in` int(6) NOT NULL,
  `errcode` int(8) DEFAULT NULL,
  `errmsg` varchar(256) DEFAULT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`access_token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jjt_wx_token
-- ----------------------------
