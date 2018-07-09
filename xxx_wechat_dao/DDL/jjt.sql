/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50516
Source Host           : localhost:3306
Source Database       : xxx

Target Server Type    : MYSQL
Target Server Version : 50516
File Encoding         : 65001

Date: 2018-07-09 13:11:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for xxx_admin_authority
-- ----------------------------
DROP TABLE IF EXISTS `xxx_admin_authority`;
CREATE TABLE `xxx_admin_authority` (
  `role_id` varchar(20) NOT NULL,
  `AUTHORITY_CODE` varchar(50) NOT NULL,
  PRIMARY KEY (`role_id`,`AUTHORITY_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xxx_admin_authority
-- ----------------------------
INSERT INTO `xxx_admin_authority` VALUES ('0', '/coupon/cancel');
INSERT INTO `xxx_admin_authority` VALUES ('0', '/coupon/cardDetails');
INSERT INTO `xxx_admin_authority` VALUES ('0', '/logout');
INSERT INTO `xxx_admin_authority` VALUES ('0', 'authority.adminManager.list');
INSERT INTO `xxx_admin_authority` VALUES ('0', 'wx');
INSERT INTO `xxx_admin_authority` VALUES ('0', 'wx.coupon.list');
INSERT INTO `xxx_admin_authority` VALUES ('0', 'wx.share.list');

-- ----------------------------
-- Table structure for xxx_admin_authority_code
-- ----------------------------
DROP TABLE IF EXISTS `xxx_admin_authority_code`;
CREATE TABLE `xxx_admin_authority_code` (
  `AUTHORITY_CODE` varchar(50) NOT NULL,
  `NAME` varchar(50) NOT NULL COMMENT '配置名',
  PRIMARY KEY (`AUTHORITY_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xxx_admin_authority_code
-- ----------------------------
INSERT INTO `xxx_admin_authority_code` VALUES ('authority', 'aut');
INSERT INTO `xxx_admin_authority_code` VALUES ('authority.adminManager.list', '管理员管理');
INSERT INTO `xxx_admin_authority_code` VALUES ('wx', 'weix');
INSERT INTO `xxx_admin_authority_code` VALUES ('wx.coupon.list', '卡券核销');
INSERT INTO `xxx_admin_authority_code` VALUES ('wx.share.list', '用户分享数一览');

-- ----------------------------
-- Table structure for xxx_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `xxx_admin_role`;
CREATE TABLE `xxx_admin_role` (
  `role_id` varchar(20) NOT NULL,
  `role_name` varchar(50) NOT NULL,
  `create_user` varchar(20) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_user` varchar(20) DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xxx_admin_role
-- ----------------------------
INSERT INTO `xxx_admin_role` VALUES ('0', '超级管理员', 'admin', '2018-05-28 19:06:39', 'admin', '2018-05-28 14:01:14');

-- ----------------------------
-- Table structure for xxx_admin_user
-- ----------------------------
DROP TABLE IF EXISTS `xxx_admin_user`;
CREATE TABLE `xxx_admin_user` (
  `admin_id` varchar(20) NOT NULL,
  `role_id` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `password_overdue_date` date DEFAULT NULL COMMENT '密码过期日期',
  `mail` varchar(50) DEFAULT NULL,
  `headImgUrl` varchar(200) DEFAULT NULL COMMENT '头像',
  `delete_flag` char(1) DEFAULT NULL,
  `create_user` varchar(20) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL COMMENT '生成时间',
  `modify_user` varchar(20) DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xxx_admin_user
-- ----------------------------
INSERT INTO `xxx_admin_user` VALUES ('admin', '0', '测试账号', 'vdzRRR6z', '2018-06-06', null, 'http://ouq085d8z.bkt.clouddn.com/xiaohuangren1.jpg', '0', 'admin', '2018-05-28 10:24:20', 'admin', '2018-05-28 10:24:26');
INSERT INTO `xxx_admin_user` VALUES ('menzhen', '0', '门诊', 'xxx12345', '2018-06-14', null, null, '0', 'admin', '2018-06-14 14:26:50', 'admin', '2018-06-14 14:26:55');
INSERT INTO `xxx_admin_user` VALUES ('yuanqu', '0', '园区', 'xxx12345', '2018-06-14', null, null, '0', 'admin', '2018-06-14 14:27:33', 'admin', '2018-06-14 14:27:40');

-- ----------------------------
-- Table structure for xxx_configuration
-- ----------------------------
DROP TABLE IF EXISTS `xxx_configuration`;
CREATE TABLE `xxx_configuration` (
  `ITEM` varchar(50) NOT NULL COMMENT '配置码',
  `property` varchar(600) NOT NULL COMMENT '配置属性',
  `NAME` varchar(50) DEFAULT NULL COMMENT '配置名',
  `TYPE` varchar(1) DEFAULT NULL COMMENT '配置类型(0系统配置;1后台配置;2业务配置)',
  `MEMO` varchar(50) DEFAULT NULL COMMENT '注释',
  PRIMARY KEY (`ITEM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xxx_configuration
-- ----------------------------
INSERT INTO `xxx_configuration` VALUES ('appid', 'wxc085afb8ad75c822', 'appid', '0', null);
INSERT INTO `xxx_configuration` VALUES ('appsecret', '624a70c1c3d77e0e97ed775fbdc6a57c', 'appsecret', '0', null);
INSERT INTO `xxx_configuration` VALUES ('get_num_limit', '100', 'get_num_limit', '1', null);
INSERT INTO `xxx_configuration` VALUES ('list_page_size', '20', '一页显示数', '1', null);
INSERT INTO `xxx_configuration` VALUES ('money', '10', '红包返点数', '1', null);
INSERT INTO `xxx_configuration` VALUES ('rec_num_limit', '100', '推荐数上限', '1', null);

-- ----------------------------
-- Table structure for xxx_red_packet
-- ----------------------------
DROP TABLE IF EXISTS `xxx_red_packet`;
CREATE TABLE `xxx_red_packet` (
  `packet_id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(20) DEFAULT NULL COMMENT '卡券code',
  `card_id` varchar(30) DEFAULT NULL,
  `open_id` varchar(30) DEFAULT NULL COMMENT '领取红包用户',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`packet_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xxx_red_packet
-- ----------------------------

-- ----------------------------
-- Table structure for xxx_sns_token
-- ----------------------------
DROP TABLE IF EXISTS `xxx_sns_token`;
CREATE TABLE `xxx_sns_token` (
  `openid` varchar(30) NOT NULL,
  `access_token` varchar(255) DEFAULT NULL COMMENT '微信凭证access_token',
  `jsapi_ticket` varchar(255) DEFAULT NULL COMMENT 'JS接口的临时票据',
  `expires_in` int(11) DEFAULT NULL COMMENT '凭证有效时间，单位：秒',
  `refresh_token` varchar(255) DEFAULT NULL,
  `scope` varchar(100) DEFAULT NULL,
  `unionid` varchar(50) DEFAULT NULL,
  `errcode` varchar(8) DEFAULT NULL COMMENT '错误码',
  `errmsg` varchar(256) DEFAULT NULL COMMENT '错误信息',
  `create_date` datetime NOT NULL COMMENT '生成时间',
  PRIMARY KEY (`openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xxx_sns_token
-- ----------------------------

-- ----------------------------
-- Table structure for xxx_wechat_card
-- ----------------------------
DROP TABLE IF EXISTS `xxx_wechat_card`;
CREATE TABLE `xxx_wechat_card` (
  `card_id` varchar(30) NOT NULL COMMENT '卡券ID。一个卡券ID对应一类卡券，包含了相应库存数量的Code码。',
  `card_type` varchar(24) DEFAULT NULL COMMENT '卡券类型(GROUPON团购券;CASH代金券;DISCOUNT折扣券;GIFT兑换券;GENERAL_COUPON优惠券)',
  `least_cost` int(11) DEFAULT NULL COMMENT '代金券专用，表示起用金额（单位为分）,如果无起用门槛则填0',
  `reduce_cost` int(11) DEFAULT NULL COMMENT '代金券专用，表示减免金额。（单位为分）',
  `deal_detail` text COMMENT '团购券专用，团购详情。',
  `logo_url` varchar(150) DEFAULT NULL COMMENT '卡券的商户logo，建议像素为300*300。',
  `code_type` varchar(16) DEFAULT NULL COMMENT '码型： "CODE_TYPE_TEXT"文 本 ； "CODE_TYPE_BARCODE"一维码 "CODE_TYPE_QRCODE"二维码 "CODE_TYPE_ONLY_QRCODE",二维码无code显示； "CODE_TYPE_ONLY_BARCODE",一维码无code显示；CODE_TYPE_NONE， 不显示code和条形码类型',
  `brand_name` varchar(36) DEFAULT NULL COMMENT '商户名字,字数上限为12个汉字。',
  `title` varchar(27) DEFAULT NULL COMMENT '卡券名，字数上限为9个汉字。(建议涵盖卡券属性、服务及金额)。',
  `color` varchar(16) DEFAULT NULL COMMENT '卡券背景颜色',
  `notice` varchar(48) DEFAULT NULL COMMENT '卡券使用提醒，字数上限为16个汉字。',
  `description` text COMMENT '卡券使用说明，字数上限为1024个汉字。',
  `quantity` int(11) DEFAULT NULL COMMENT '卡券库存的数量，上限为100000000。',
  `type` varchar(20) DEFAULT NULL COMMENT '使用时间的类型，旧文档采用的1和2依然生效。DATE_TYPE_FIX _TIME_RANGE 表示固定日期区间，DATETYPE FIX_TERM 表示固定时长 (自领取后按天算)。',
  `begin_timestamp` varchar(20) DEFAULT NULL COMMENT 'type为DATE_TYPE_FIX_TIME_RANGE时专用，表示起用时间。从1970年1月1日00:00:00至起用时间的秒数，最终需转换为字符串形态传入。（东八区时间,UTC+8，单位为秒）',
  `end_timestamp` varchar(30) DEFAULT NULL COMMENT '表示结束时间 ， 建议设置为截止日期的23:59:59过期 。 （ 东八区时间,UTC+8，单位为秒 ）',
  `fixed_term` int(11) DEFAULT NULL COMMENT 'type为DATE_TYPE_FIX_TERM时专用，表示自领取后多少天内有效，不支持填写0。',
  `fixed_begin_term` int(11) DEFAULT '0' COMMENT 'type为DATE_TYPE_FIX_TERM时专用，表示自领取后多少天开始生效，领取后当天生效填写0。（单位为天）',
  `use_limit` int(11) DEFAULT NULL,
  `get_limit` int(255) DEFAULT NULL,
  `use_custom_code` char(1) DEFAULT '0',
  `bind_openid` char(1) DEFAULT '0',
  `can_share` char(1) DEFAULT '0',
  `can_give_friend` char(1) DEFAULT '0',
  PRIMARY KEY (`card_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xxx_wechat_card
-- ----------------------------
INSERT INTO `xxx_wechat_card` VALUES ('pKv1dwonNT-qAAzhnUX92jBcxpGA', 'GIFT', null, null, null, 'http://mmbiz.qpic.cn/mmbiz_jpg/2pv5kKsEocokTaRGdeicuybW1ta1qvGRYz60MfcWQ5uPcDH0H2N3cDXiakO6w8ic5G3KicQJ40bm901hricjPsZ8hlg/0', 'CODE_TYPE_TEXT', '商户名字', '卡券名', 'Color030', '卡券使用提醒', '卡券使用说明，字数上限为1024个汉字', '100', 'DATE_TYPE_FIX_TERM', null, '1529942399', '30', '0', '5', '5', '0', '0', '0', '0');

-- ----------------------------
-- Table structure for xxx_wechat_card_code
-- ----------------------------
DROP TABLE IF EXISTS `xxx_wechat_card_code`;
CREATE TABLE `xxx_wechat_card_code` (
  `code_id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(50) DEFAULT NULL COMMENT '核销Code号',
  `card_id` varchar(30) NOT NULL COMMENT '核销卡券号',
  `rec_ed_openid` varchar(30) NOT NULL COMMENT '核销对象',
  `rec_openid` varchar(30) DEFAULT NULL,
  `money` int(11) DEFAULT '0',
  `code_status` int(11) DEFAULT NULL COMMENT '卡券状态(0未核销;1已核销)',
  `red_packet_status` int(11) DEFAULT '0' COMMENT '红包生成状态(0未领取;1已领取)',
  `updatetime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`code_id`)
) ENGINE=InnoDB AUTO_INCREMENT=249 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xxx_wechat_card_code
-- ----------------------------

-- ----------------------------
-- Table structure for xxx_wechat_card_consume
-- ----------------------------
DROP TABLE IF EXISTS `xxx_wechat_card_consume`;
CREATE TABLE `xxx_wechat_card_consume` (
  `consume_id` int(11) NOT NULL AUTO_INCREMENT,
  `code_id` int(11) DEFAULT NULL COMMENT '核销Code号',
  `admin_id` varchar(30) DEFAULT NULL COMMENT '核销者',
  `money` double DEFAULT '0',
  `updatetime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`consume_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xxx_wechat_card_consume
-- ----------------------------

-- ----------------------------
-- Table structure for xxx_wechat_payment
-- ----------------------------
DROP TABLE IF EXISTS `xxx_wechat_payment`;
CREATE TABLE `xxx_wechat_payment` (
  `pay_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '支付主键',
  `openid` varchar(32) NOT NULL COMMENT '用户微信openid',
  `amount` bigint(20) NOT NULL COMMENT '支付金额',
  `redpack_url` varchar(255) DEFAULT NULL COMMENT '红包Url',
  `trade_no` varchar(32) DEFAULT NULL COMMENT '贸易编号',
  `status` char(1) NOT NULL COMMENT '付款状态\r\n0:系统操作异常\r\n1:发放中\r\n2:支付API发放成功\r\n3:支付API发放失败',
  `create_datetime` datetime NOT NULL DEFAULT '2018-06-01 18:51:14' COMMENT '创建时间',
  `update_datetime` datetime NOT NULL DEFAULT '2018-06-01 18:51:14' COMMENT '更新时间',
  PRIMARY KEY (`pay_id`),
  KEY `openid` (`openid`),
  CONSTRAINT `xxx_wechat_payment_ibfk_1` FOREIGN KEY (`openid`) REFERENCES `xxx_wechat_user_account` (`openid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xxx_wechat_payment
-- ----------------------------
INSERT INTO `xxx_wechat_payment` VALUES ('1', 'oKv1dwgLxu96p53D0nYS6KP7SQbA', '40', 'https://www.jufenyun.com/wechat/batches?key=f13bfordtnY2OLNse0Xe8irVUdzsZcC4XRb9BXMWuBOTZeHafRwgXayZqPzLHEVqfEr9Q3vyROiu1hnD0SmppVnj', '20180601778239261498674688', '2', '2018-06-01 18:51:14', '2018-06-01 18:53:19');
INSERT INTO `xxx_wechat_payment` VALUES ('2', 'oKv1dwgLxu96p53D0nYS6KP7SQbA', '40', 'https://www.jufenyun.com/wechat/batches?key=dba2XWjaMZW6yajytYFCVcq6wCK920H07evMDAhoapKzCq45ceawjsa4blaIehzaeQrj4dynK99OApFneIAOwWuu', '20180601305764875840395680', '2', '2018-06-01 18:51:14', '2018-06-01 18:54:14');
INSERT INTO `xxx_wechat_payment` VALUES ('3', 'oKv1dwu4PeoH_clpd-Lz4h4sorEM', '40', 'https://www.jufenyun.com/wechat/batches?key=f8c9tkWQFMC84YZuDsRkYDcAhfrANWiJ5xYKroSjStjxO6Sfb4%2BOYTLQPOjupn5bHgjWhBeEO%2FKgYncTRbCIYtH4', '20180601500804287241771840', '2', '2018-06-01 18:51:14', '2018-06-01 18:54:25');
INSERT INTO `xxx_wechat_payment` VALUES ('4', 'oKv1dwgLxu96p53D0nYS6KP7SQbA', '50', 'https://www.jufenyun.com/wechat/batches?key=5baeA920QXxh8uvAJ%2FYXjKgek4cowsZZbiNMpNXzJ0Bi3nl1Lm9aciekEHkVY7ooN1wZF0tSqNaXKh%2BA5l35UxFI', '20180601864832301251590272', '2', '2018-06-01 18:51:14', '2018-06-01 19:08:05');
INSERT INTO `xxx_wechat_payment` VALUES ('5', 'oKv1dwgLxu96p53D0nYS6KP7SQbA', '50', 'https://www.jufenyun.com/wechat/batches?key=d221BC9lFD011dJVTJaFB9MJwvH91nN44tIsQEtKH4oo8HCrO8xvp491wFVEt1hj2cUTqz0NplDrfGEsmdy9y1%2BL', '20180601861086712451651712', '2', '2018-06-01 18:51:14', '2018-06-01 19:57:50');
INSERT INTO `xxx_wechat_payment` VALUES ('6', 'oKv1dwgLxu96p53D0nYS6KP7SQbA', '50', 'https://www.jufenyun.com/wechat/batches?key=5637y%2B%2FlzQOZ2FVOxj9%2BJiZH1woVox%2Fx6TmwBZ0pYWOFBxVygpIdKCQDs0ixIVlWgTpDuIh3HE7WXE2T8oJmWXm1', '20180601674383746646344640', '2', '2018-06-01 18:51:14', '2018-06-01 19:58:27');
INSERT INTO `xxx_wechat_payment` VALUES ('7', 'oKv1dwu4PeoH_clpd-Lz4h4sorEM', '50', 'https://www.jufenyun.com/wechat/batches?key=338bAUPxJ8fPQ0%2F48ka9zDPoMJ0cMRjOgstKfPqXacXVeNrz432X3%2FxPykRq1WoO6wmp2dcYUOuImxGocXryAI3f', '20180601955381707847118336', '2', '2018-06-01 18:51:14', '2018-06-01 20:01:05');
INSERT INTO `xxx_wechat_payment` VALUES ('8', 'oKv1dwu4PeoH_clpd-Lz4h4sorEM', '100', 'https://www.jufenyun.com/wechat/batches?key=0b88%2FXMS%2BANnc%2FYM14jfIISmpThLafnh28H0Gh%2BcdJRJNIATDzx3lJ1GAxB2nyQIS6GjLi6GTFq7HUg2XjPzOJAO', '20180601695956044225022208', '2', '2018-06-01 18:51:14', '2018-06-01 20:25:04');
INSERT INTO `xxx_wechat_payment` VALUES ('9', 'oKv1dwgLxu96p53D0nYS6KP7SQbA', '50', 'https://www.jufenyun.com/wechat/batches?key=2deaIDHKQQ5tcPJlCiIpe20yrncDI7NNjCGCcSVvVqhRlP%2FWSiRozB8nsi20LoGyOXRpx1QkVF5fxCV1oxqWJ3mP', '20180601365108685335144416', '2', '2018-06-01 18:51:14', '2018-06-01 20:25:51');

-- ----------------------------
-- Table structure for xxx_wechat_pay_api_log
-- ----------------------------
DROP TABLE IF EXISTS `xxx_wechat_pay_api_log`;
CREATE TABLE `xxx_wechat_pay_api_log` (
  `pay_api_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '支付API主键',
  `pay_id` bigint(20) DEFAULT NULL COMMENT '支付主键',
  `trade_no` varchar(32) DEFAULT NULL COMMENT '贸易编号',
  `api_url` varchar(200) NOT NULL COMMENT '支付接口URL',
  `parameters` text NOT NULL COMMENT '请求参数',
  `return_code` int(11) NOT NULL COMMENT '服务器响应状态\r\n0:支付API发放成功\r\n1:支付API发放失败\r\n2:系统异常',
  `return_json` text NOT NULL COMMENT '返回json数据',
  `elapsed` bigint(20) NOT NULL COMMENT '请求耗时',
  `create_datetime` datetime NOT NULL DEFAULT '2018-06-01 18:51:14' COMMENT '创建时间',
  PRIMARY KEY (`pay_api_id`),
  KEY `pay_id` (`pay_id`),
  CONSTRAINT `xxx_wechat_pay_api_log_ibfk_1` FOREIGN KEY (`pay_id`) REFERENCES `xxx_wechat_payment` (`pay_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xxx_wechat_pay_api_log
-- ----------------------------
INSERT INTO `xxx_wechat_pay_api_log` VALUES ('1', '1', null, 'https://www.jufenyun.com/openapi/gateway', '{\"method\":\"jfy.redpacks.send\",\"money\":40,\"openid\":\"oKv1dwgLxu96p53D0nYS6KP7SQbA\",\"appkey\":\"a5eb4360-b515-4cc1-9a7a-58700a744bfc\"}', '0', '{\"code\":\"0\",\"message\":null,\"redpackSn\":\"20180601778239261498674688\",\"redpackUrl\":\"https://www.jufenyun.com/wechat/batches?key=f13bfordtnY2OLNse0Xe8irVUdzsZcC4XRb9BXMWuBOTZeHafRwgXayZqPzLHEVqfEr9Q3vyROiu1hnD0SmppVnj\",\"money\":null,\"openid\":null,\"nickname\":null,\"consumeSn\":null,\"createdTime\":null,\"usedTime\":null,\"status\":null,\"netStatus\":\"200\"}', '616', '2018-06-01 18:51:14');
INSERT INTO `xxx_wechat_pay_api_log` VALUES ('2', '2', null, 'https://www.jufenyun.com/openapi/gateway', '{\"method\":\"jfy.redpacks.send\",\"money\":40,\"openid\":\"oKv1dwgLxu96p53D0nYS6KP7SQbA\",\"appkey\":\"a5eb4360-b515-4cc1-9a7a-58700a744bfc\"}', '0', '{\"code\":\"0\",\"message\":null,\"redpackSn\":\"20180601305764875840395680\",\"redpackUrl\":\"https://www.jufenyun.com/wechat/batches?key=dba2XWjaMZW6yajytYFCVcq6wCK920H07evMDAhoapKzCq45ceawjsa4blaIehzaeQrj4dynK99OApFneIAOwWuu\",\"money\":null,\"openid\":null,\"nickname\":null,\"consumeSn\":null,\"createdTime\":null,\"usedTime\":null,\"status\":null,\"netStatus\":\"200\"}', '396', '2018-06-01 18:51:14');
INSERT INTO `xxx_wechat_pay_api_log` VALUES ('3', '3', null, 'https://www.jufenyun.com/openapi/gateway', '{\"method\":\"jfy.redpacks.send\",\"money\":40,\"openid\":\"oKv1dwu4PeoH_clpd-Lz4h4sorEM\",\"appkey\":\"a5eb4360-b515-4cc1-9a7a-58700a744bfc\"}', '0', '{\"code\":\"0\",\"message\":null,\"redpackSn\":\"20180601500804287241771840\",\"redpackUrl\":\"https://www.jufenyun.com/wechat/batches?key=f8c9tkWQFMC84YZuDsRkYDcAhfrANWiJ5xYKroSjStjxO6Sfb4%2BOYTLQPOjupn5bHgjWhBeEO%2FKgYncTRbCIYtH4\",\"money\":null,\"openid\":null,\"nickname\":null,\"consumeSn\":null,\"createdTime\":null,\"usedTime\":null,\"status\":null,\"netStatus\":\"200\"}', '427', '2018-06-01 18:51:14');
INSERT INTO `xxx_wechat_pay_api_log` VALUES ('4', '4', null, 'https://www.jufenyun.com/openapi/gateway', '{\"method\":\"jfy.redpacks.send\",\"money\":50,\"openid\":\"oKv1dwgLxu96p53D0nYS6KP7SQbA\",\"appkey\":\"a5eb4360-b515-4cc1-9a7a-58700a744bfc\"}', '0', '{\"code\":\"0\",\"message\":null,\"redpackSn\":\"20180601864832301251590272\",\"redpackUrl\":\"https://www.jufenyun.com/wechat/batches?key=5baeA920QXxh8uvAJ%2FYXjKgek4cowsZZbiNMpNXzJ0Bi3nl1Lm9aciekEHkVY7ooN1wZF0tSqNaXKh%2BA5l35UxFI\",\"money\":null,\"openid\":null,\"nickname\":null,\"consumeSn\":null,\"createdTime\":null,\"usedTime\":null,\"status\":null,\"netStatus\":\"200\"}', '529', '2018-06-01 18:51:14');
INSERT INTO `xxx_wechat_pay_api_log` VALUES ('5', '5', null, 'https://www.jufenyun.com/openapi/gateway', '{\"method\":\"jfy.redpacks.send\",\"money\":50,\"openid\":\"oKv1dwgLxu96p53D0nYS6KP7SQbA\",\"appkey\":\"a5eb4360-b515-4cc1-9a7a-58700a744bfc\"}', '0', '{\"code\":\"0\",\"message\":null,\"redpackSn\":\"20180601861086712451651712\",\"redpackUrl\":\"https://www.jufenyun.com/wechat/batches?key=d221BC9lFD011dJVTJaFB9MJwvH91nN44tIsQEtKH4oo8HCrO8xvp491wFVEt1hj2cUTqz0NplDrfGEsmdy9y1%2BL\",\"money\":null,\"openid\":null,\"nickname\":null,\"consumeSn\":null,\"createdTime\":null,\"usedTime\":null,\"status\":null,\"netStatus\":\"200\"}', '442', '2018-06-01 18:51:14');
INSERT INTO `xxx_wechat_pay_api_log` VALUES ('6', '6', null, 'https://www.jufenyun.com/openapi/gateway', '{\"method\":\"jfy.redpacks.send\",\"money\":50,\"openid\":\"oKv1dwgLxu96p53D0nYS6KP7SQbA\",\"appkey\":\"a5eb4360-b515-4cc1-9a7a-58700a744bfc\"}', '0', '{\"code\":\"0\",\"message\":null,\"redpackSn\":\"20180601674383746646344640\",\"redpackUrl\":\"https://www.jufenyun.com/wechat/batches?key=5637y%2B%2FlzQOZ2FVOxj9%2BJiZH1woVox%2Fx6TmwBZ0pYWOFBxVygpIdKCQDs0ixIVlWgTpDuIh3HE7WXE2T8oJmWXm1\",\"money\":null,\"openid\":null,\"nickname\":null,\"consumeSn\":null,\"createdTime\":null,\"usedTime\":null,\"status\":null,\"netStatus\":\"200\"}', '366', '2018-06-01 18:51:14');
INSERT INTO `xxx_wechat_pay_api_log` VALUES ('7', '7', null, 'https://www.jufenyun.com/openapi/gateway', '{\"method\":\"jfy.redpacks.send\",\"money\":50,\"openid\":\"oKv1dwu4PeoH_clpd-Lz4h4sorEM\",\"appkey\":\"a5eb4360-b515-4cc1-9a7a-58700a744bfc\"}', '0', '{\"code\":\"0\",\"message\":null,\"redpackSn\":\"20180601955381707847118336\",\"redpackUrl\":\"https://www.jufenyun.com/wechat/batches?key=338bAUPxJ8fPQ0%2F48ka9zDPoMJ0cMRjOgstKfPqXacXVeNrz432X3%2FxPykRq1WoO6wmp2dcYUOuImxGocXryAI3f\",\"money\":null,\"openid\":null,\"nickname\":null,\"consumeSn\":null,\"createdTime\":null,\"usedTime\":null,\"status\":null,\"netStatus\":\"200\"}', '391', '2018-06-01 18:51:14');
INSERT INTO `xxx_wechat_pay_api_log` VALUES ('8', '8', null, 'https://www.jufenyun.com/openapi/gateway', '{\"method\":\"jfy.redpacks.send\",\"money\":100,\"openid\":\"oKv1dwu4PeoH_clpd-Lz4h4sorEM\",\"appkey\":\"a5eb4360-b515-4cc1-9a7a-58700a744bfc\"}', '0', '{\"code\":\"0\",\"message\":null,\"redpackSn\":\"20180601695956044225022208\",\"redpackUrl\":\"https://www.jufenyun.com/wechat/batches?key=0b88%2FXMS%2BANnc%2FYM14jfIISmpThLafnh28H0Gh%2BcdJRJNIATDzx3lJ1GAxB2nyQIS6GjLi6GTFq7HUg2XjPzOJAO\",\"money\":null,\"openid\":null,\"nickname\":null,\"consumeSn\":null,\"createdTime\":null,\"usedTime\":null,\"status\":null,\"netStatus\":\"200\"}', '1198', '2018-06-01 18:51:14');
INSERT INTO `xxx_wechat_pay_api_log` VALUES ('9', '9', null, 'https://www.jufenyun.com/openapi/gateway', '{\"method\":\"jfy.redpacks.send\",\"money\":50,\"openid\":\"oKv1dwgLxu96p53D0nYS6KP7SQbA\",\"appkey\":\"a5eb4360-b515-4cc1-9a7a-58700a744bfc\"}', '0', '{\"code\":\"0\",\"message\":null,\"redpackSn\":\"20180601365108685335144416\",\"redpackUrl\":\"https://www.jufenyun.com/wechat/batches?key=2deaIDHKQQ5tcPJlCiIpe20yrncDI7NNjCGCcSVvVqhRlP%2FWSiRozB8nsi20LoGyOXRpx1QkVF5fxCV1oxqWJ3mP\",\"money\":null,\"openid\":null,\"nickname\":null,\"consumeSn\":null,\"createdTime\":null,\"usedTime\":null,\"status\":null,\"netStatus\":\"200\"}', '381', '2018-06-01 18:51:14');

-- ----------------------------
-- Table structure for xxx_wechat_qrcode
-- ----------------------------
DROP TABLE IF EXISTS `xxx_wechat_qrcode`;
CREATE TABLE `xxx_wechat_qrcode` (
  `qrcode_id` int(11) NOT NULL AUTO_INCREMENT,
  `openid` varchar(30) DEFAULT NULL,
  `ticket` varchar(200) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `qrcode_type` int(11) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`qrcode_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xxx_wechat_qrcode
-- ----------------------------

-- ----------------------------
-- Table structure for xxx_wechat_qrcode_type
-- ----------------------------
DROP TABLE IF EXISTS `xxx_wechat_qrcode_type`;
CREATE TABLE `xxx_wechat_qrcode_type` (
  `qrcode_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `scene` varchar(50) DEFAULT NULL,
  `create_user` varchar(30) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`qrcode_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xxx_wechat_qrcode_type
-- ----------------------------

-- ----------------------------
-- Table structure for xxx_wechat_recommend
-- ----------------------------
DROP TABLE IF EXISTS `xxx_wechat_recommend`;
CREATE TABLE `xxx_wechat_recommend` (
  `recommend_id` int(11) NOT NULL AUTO_INCREMENT,
  `rec_ed_openid` varchar(30) DEFAULT NULL COMMENT '推荐者的openid',
  `rec_openid` varchar(30) DEFAULT NULL COMMENT '被推荐者的openid',
  `updatetime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`recommend_id`)
) ENGINE=InnoDB AUTO_INCREMENT=534 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xxx_wechat_recommend
-- ----------------------------

-- ----------------------------
-- Table structure for xxx_wechat_token
-- ----------------------------
DROP TABLE IF EXISTS `xxx_wechat_token`;
CREATE TABLE `xxx_wechat_token` (
  `access_token` varchar(255) NOT NULL COMMENT '微信凭证access_token',
  `jsapi_ticket` varchar(255) DEFAULT NULL COMMENT 'JS接口的临时票据',
  `api_ticket` varchar(255) DEFAULT NULL,
  `expires_in` int(11) DEFAULT NULL COMMENT '凭证有效时间，单位：秒',
  `errcode` varchar(8) DEFAULT NULL COMMENT '错误码',
  `errmsg` varchar(256) DEFAULT NULL COMMENT '错误信息',
  `create_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '生成时间',
  PRIMARY KEY (`access_token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xxx_wechat_token
-- ----------------------------

-- ----------------------------
-- Table structure for xxx_wechat_user
-- ----------------------------
DROP TABLE IF EXISTS `xxx_wechat_user`;
CREATE TABLE `xxx_wechat_user` (
  `openid` varchar(30) NOT NULL COMMENT '微信openid',
  `unionid` varchar(30) DEFAULT NULL COMMENT '微信unionid',
  `phone` varchar(20) DEFAULT NULL,
  `share_number` int(11) DEFAULT '0',
  `verify_phone` varchar(20) DEFAULT NULL,
  `verify_code` varchar(10) DEFAULT '',
  `verify_code_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xxx_wechat_user
-- ----------------------------

-- ----------------------------
-- Table structure for xxx_wechat_user_account
-- ----------------------------
DROP TABLE IF EXISTS `xxx_wechat_user_account`;
CREATE TABLE `xxx_wechat_user_account` (
  `openid` varchar(32) NOT NULL COMMENT '用户微信openid',
  `unionid` varchar(32) NOT NULL COMMENT '用户微信unionid',
  `balance` bigint(20) NOT NULL DEFAULT '0' COMMENT '账户余额\r\n单位:分',
  `create_datetime` datetime NOT NULL COMMENT '创建时间',
  `update_datetime` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xxx_wechat_user_account
-- ----------------------------
INSERT INTO `xxx_wechat_user_account` VALUES ('oKv1dwgLxu96p53D0nYS6KP7SQbA', 'abcd', '0', '2018-06-01 18:19:30', '2018-06-01 20:25:51');
INSERT INTO `xxx_wechat_user_account` VALUES ('oKv1dwu4PeoH_clpd-Lz4h4sorEM', 'defg', '100', '2018-06-01 18:19:53', '2018-06-01 20:25:04');

-- ----------------------------
-- Table structure for xxx_wechat_user_info
-- ----------------------------
DROP TABLE IF EXISTS `xxx_wechat_user_info`;
CREATE TABLE `xxx_wechat_user_info` (
  `openid` varchar(30) NOT NULL COMMENT '微信openid',
  `nickname` varchar(200) DEFAULT NULL COMMENT '微信昵称',
  `sex` char(1) DEFAULT NULL COMMENT '性别',
  `province` varchar(10) DEFAULT NULL COMMENT '省份',
  `city` varchar(10) DEFAULT NULL COMMENT '城市',
  `country` varchar(10) DEFAULT NULL COMMENT '国家',
  `headimgurl` varchar(255) DEFAULT NULL COMMENT '头像url',
  `unionid` varchar(10) DEFAULT NULL COMMENT '微信unionid',
  `subscribe` int(11) DEFAULT NULL COMMENT '用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。',
  `language` varchar(20) DEFAULT NULL COMMENT '用户的语言，简体中文为zh_CN',
  `subscribe_time` varchar(20) NOT NULL DEFAULT '' COMMENT '用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间',
  `remark` varchar(30) DEFAULT NULL COMMENT '公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注',
  `groupid` varchar(10) DEFAULT NULL COMMENT '用户所在的分组ID（兼容旧的用户分组接口）',
  `subscribe_scene` varchar(30) DEFAULT NULL COMMENT '返回用户关注的渠道来源',
  `qr_scene` int(11) DEFAULT NULL COMMENT '二维码扫码场景（开发者自定义）',
  `qr_scene_str` varchar(50) DEFAULT NULL COMMENT '二维码扫码场景描述（开发者自定义）',
  `updatetime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xxx_wechat_user_info
-- ----------------------------
INSERT INTO `xxx_wechat_user_info` VALUES ('oKv1dwgLxu96p53D0nYS6KP7SQbA', 'Youn', '1', '', '', '泽西岛', 'http://thirdwx.qlogo.cn/mmopen/pBRRaGic9IthfNy0RDXd1IPv7X8d5bpiavBeiazVpbqM4phgAd6jnWTwuYne5WDEibBlPyTia3KJ3YtGL5JCEk522vhKAyw9N2Pyv/132', null, '1', 'zh_CN', '1527790366000', '', '0', 'ADD_SCENE_QR_CODE', '0', '', '2018-06-01 02:13:08');
INSERT INTO `xxx_wechat_user_info` VALUES ('oKv1dwoJOhPYdN4J9EAr4tJTHfMc', '%E8%B0%A2%E8%BE%89', '1', '上海', '浦东新区', '中国', 'http://thirdwx.qlogo.cn/mmopen/LhK9Vic2X5nhgnLkfKsMu9FVUC29X7ImfdialOerdUUC7sibGszu8PZlBTqtLP12FzktpaYxflf1tTFwbKPSRAibeicTtJwMh9LAv/132', null, '1', 'zh_CN', '1527838152000', '', '0', 'ADD_SCENE_QR_CODE', '0', 'share_one_oKv1dwgLxu96p53D0nYS6KP7SQbA', '2018-06-01 15:29:43');
INSERT INTO `xxx_wechat_user_info` VALUES ('oKv1dws2m6ao9JhU0zbiwfvZGg-8', '%E5%AF%B9%E6%97%A5%E6%9C%88%E6%88%90%E5%9B%9B%E5%BD%B1', '1', '', '', '阿尔巴尼亚', 'http://thirdwx.qlogo.cn/mmopen/pBRRaGic9IthfNy0RDXd1IGSBGS4OyicbFUuFm1YZrKnf7aropBjDqUEm9uzdOengdw5yfNl6VasaO2LicmV89VI4KSe56kdJ2w/132', null, '1', 'zh_CN', '1527840305000', '', '0', 'ADD_SCENE_QR_CODE', '0', 'share_one_oKv1dwoJOhPYdN4J9EAr4tJTHfMc', '2018-06-01 16:06:17');
INSERT INTO `xxx_wechat_user_info` VALUES ('oKv1dwu4PeoH_clpd-Lz4h4sorEM', 'teamLab', '0', '', '', '', 'http://thirdwx.qlogo.cn/mmopen/LhK9Vic2X5njDUaUbNibIqCONVC1gQorpHibmMPxxx0bIpV2T7rOWFsTFaVRjDibWxqNOorth3QnvbLtTc0ya22sk1FpVbmugUIM/132', null, '1', 'zh_CN', '1527847353000', '', '0', 'ADD_SCENE_QR_CODE', '0', 'share_one_oKv1dwoJOhPYdN4J9EAr4tJTHfMc', '2018-06-01 18:02:42');
