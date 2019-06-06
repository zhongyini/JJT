/*
Navicat MySQL Data Transfer

Source Server         : 腾讯云
Source Server Version : 80016
Source Host           : 118.25.72.245:3306
Source Database       : xxx

Target Server Type    : MYSQL
Target Server Version : 80016
File Encoding         : 65001

Date: 2019-06-06 13:47:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for lottery_dlt_guess
-- ----------------------------
DROP TABLE IF EXISTS `lottery_dlt_guess`;
CREATE TABLE `lottery_dlt_guess` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `term` int(11) DEFAULT NULL COMMENT '大乐透历史纪录主键期号',
  `num_sequence` varchar(50) DEFAULT NULL COMMENT '按照产生顺序显示',
  `number` varchar(50) DEFAULT NULL COMMENT '从红到蓝、从小到大排序',
  `red_one` int(11) DEFAULT NULL COMMENT '一号红色球',
  `red_two` int(11) DEFAULT NULL COMMENT '二号红色球',
  `red_three` int(11) DEFAULT NULL COMMENT '三号红色球',
  `red_four` int(11) DEFAULT NULL COMMENT '四号红色球',
  `red_five` int(11) DEFAULT NULL COMMENT '五号红色球',
  `blue_one` int(11) DEFAULT NULL COMMENT '一号蓝色球',
  `blue_two` int(11) DEFAULT NULL COMMENT '二号蓝色球',
  `several_award` int(11) DEFAULT NULL COMMENT '几等奖=>"0":未中奖;"1":一等奖;"2":二等奖;"3":三等奖',
  `status` int(11) DEFAULT NULL COMMENT '状态=>"0":未过期,"1":已过期',
  `delete_flag` int(11) DEFAULT NULL COMMENT '删除标志=>"0":未删除,"1":已删除',
  `update_user` varchar(20) DEFAULT NULL COMMENT '修改者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of lottery_dlt_guess
-- ----------------------------

-- ----------------------------
-- Table structure for lottery_dlt_history
-- ----------------------------
DROP TABLE IF EXISTS `lottery_dlt_history`;
CREATE TABLE `lottery_dlt_history` (
  `term` int(11) NOT NULL COMMENT '主键彩票号',
  `draw_news` varchar(50) DEFAULT NULL COMMENT '另一种彩票号',
  `num_sequence` varchar(50) DEFAULT NULL COMMENT '按照产生顺序显示',
  `number` varchar(50) DEFAULT NULL COMMENT '从红到蓝、从小到大排序',
  `red_one` int(11) DEFAULT NULL COMMENT '一号红色球',
  `red_two` int(11) DEFAULT NULL COMMENT '二号红色球',
  `red_three` int(11) DEFAULT NULL COMMENT '三号红色球',
  `red_four` int(11) DEFAULT NULL COMMENT '四号红色球',
  `red_five` int(11) DEFAULT NULL COMMENT '五号红色球',
  `blue_one` int(11) DEFAULT NULL COMMENT '一号蓝色球',
  `blue_two` int(11) DEFAULT NULL COMMENT '二号蓝色球',
  `several_award` int(1) DEFAULT NULL COMMENT '几等奖=>"0":未中奖;"1":一等奖;"2":二等奖;"3":三等奖',
  `delete_flag` int(1) DEFAULT '0' COMMENT '删除标志=>"0":未删除,"1":已删除',
  `update_user` varchar(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`term`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of lottery_dlt_history
-- ----------------------------

-- ----------------------------
-- Table structure for lottery_dlt_history_detail
-- ----------------------------
DROP TABLE IF EXISTS `lottery_dlt_history_detail`;
CREATE TABLE `lottery_dlt_history_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `term` int(11) NOT NULL COMMENT '主键彩票号',
  `draw_news` varchar(50) DEFAULT NULL COMMENT '另一种彩票号',
  `level` varchar(50) DEFAULT NULL COMMENT '排序',
  `money` int(11) DEFAULT NULL COMMENT '金额',
  `num` int(11) DEFAULT NULL,
  `piece` varchar(50) DEFAULT NULL COMMENT 'piece',
  `send_prize` varchar(50) DEFAULT NULL COMMENT '赠送奖品',
  `delete_flag` int(1) DEFAULT '0' COMMENT '删除标志=>"0":未删除,"1":已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of lottery_dlt_history_detail
-- ----------------------------

-- ----------------------------
-- Table structure for xxx_admin_authority
-- ----------------------------
DROP TABLE IF EXISTS `xxx_admin_authority`;
CREATE TABLE `xxx_admin_authority` (
  `role_id` varchar(20) NOT NULL,
  `AUTHORITY_CODE` varchar(50) NOT NULL,
  PRIMARY KEY (`role_id`,`AUTHORITY_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of xxx_admin_user
-- ----------------------------
INSERT INTO `xxx_admin_user` VALUES ('admin', '0', '测试账号', 'vdzRRR6z', '2018-06-06', null, 'http://ouq085d8z.bkt.clouddn.com/xiaohuangren1.jpg', '0', 'admin', '2018-05-28 10:24:20', 'admin', '2019-03-01 10:24:26');
INSERT INTO `xxx_admin_user` VALUES ('menzhen', '0', '门诊', 'xxx12345', '2018-06-14', null, null, '0', 'admin', '2018-06-14 14:26:50', 'admin', '2018-03-08 14:26:55');
INSERT INTO `xxx_admin_user` VALUES ('yuanqu', '0', '园区', 'xxx12345', '2018-06-14', null, null, '0', 'admin', '2018-06-14 14:27:33', 'admin', '2018-03-22 14:27:40');

-- ----------------------------
-- Table structure for xxx_baidu_api_access_token
-- ----------------------------
DROP TABLE IF EXISTS `xxx_baidu_api_access_token`;
CREATE TABLE `xxx_baidu_api_access_token` (
  `accessToken` varchar(100) NOT NULL COMMENT '获取到的accessToken',
  `refreshToken` varchar(100) DEFAULT NULL COMMENT '用于刷新的token',
  `expiresIn` smallint(6) DEFAULT NULL COMMENT '凭证有效时间',
  `session_key` varchar(50) DEFAULT NULL,
  `scope` varchar(50) DEFAULT NULL,
  `sessionSecret` varchar(50) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL COMMENT '生成时间',
  PRIMARY KEY (`accessToken`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of xxx_baidu_api_access_token
-- ----------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of xxx_configuration
-- ----------------------------
INSERT INTO `xxx_configuration` VALUES ('appid', 'wxc085afb8ad75c822', 'appid', '0', null);
INSERT INTO `xxx_configuration` VALUES ('appsecret', '624a70c1c3d77e0e97ed775fbdc6a57c', 'appsecret', '0', null);
INSERT INTO `xxx_configuration` VALUES ('get_num_limit', '100', 'get_num_limit', '1', null);
INSERT INTO `xxx_configuration` VALUES ('hostUrl', 'http://www.ykstudy.cn/wechat-front', '域名', '0', null);
INSERT INTO `xxx_configuration` VALUES ('list_page_size', '20', '一页显示数', '1', null);
INSERT INTO `xxx_configuration` VALUES ('money', '10', '红包返点数', '1', null);
INSERT INTO `xxx_configuration` VALUES ('openid_cookie', 'xxx_test_openId', 'cookie', '0', null);
INSERT INTO `xxx_configuration` VALUES ('rec_num_limit', '100', '推荐数上限', '1', null);
INSERT INTO `xxx_configuration` VALUES ('server_config_token', 'testToken', 'wechatToken', '0', null);
INSERT INTO `xxx_configuration` VALUES ('state', '123', 'state', '0', null);
INSERT INTO `xxx_configuration` VALUES ('wechat_tag_id', '100', '微信标签tagid', '1', null);

-- ----------------------------
-- Table structure for xxx_name_config
-- ----------------------------
DROP TABLE IF EXISTS `xxx_name_config`;
CREATE TABLE `xxx_name_config` (
  `id` int(11) NOT NULL,
  `word` varchar(10) DEFAULT NULL,
  `delete_flag` int(1) DEFAULT NULL,
  `update_user` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of xxx_name_config
-- ----------------------------
INSERT INTO `xxx_name_config` VALUES ('1', '一', '0', 'admin', '2018-12-12 14:30:28');
INSERT INTO `xxx_name_config` VALUES ('2', '二', '0', 'admin', '2018-12-12 16:08:20');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of xxx_red_packet
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of xxx_wechat_card
-- ----------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of xxx_wechat_payment
-- ----------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of xxx_wechat_pay_api_log
-- ----------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
  `create_date` timestamp NOT NULL COMMENT '生成时间',
  PRIMARY KEY (`access_token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of xxx_wechat_token
-- ----------------------------
INSERT INTO `xxx_wechat_token` VALUES ('16_YocUADalU3TN6cB2w3Ce6F1s2xieEGfFzCBKfWgycoQX6XBfaluGb2HRhZs5DEDGDIzu6gcYJS1bUVIlEts3OfjlYdtVl61DHNeWCfPIpXpEDz5jQ3vjkKhS4OOQgAGHDo6WvqBHFTqLqaymXYTcAHAPQL', 'kgt8ON7yVITDhtdwci0qefL8blC0fNUBF9inj4zc0B1PHY9ex2N5x5Lp3BhEoWTA2m-95NTcVUtWdaXZftWS1Q', 'IpK_1T69hDhZkLQTlwsAXwt01jbvuH7S-Ap84FsxKjqGt1SXC2HYXoiB7j9YcwpnHOpIFU5EawPEoph3GOWChw', '7200', null, null, '2018-12-12 14:00:00');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of xxx_wechat_user_account
-- ----------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of xxx_wechat_user_info
-- ----------------------------

-- ----------------------------
-- Table structure for xxx_xhs_user
-- ----------------------------
DROP TABLE IF EXISTS `xxx_xhs_user`;
CREATE TABLE `xxx_xhs_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `url` varchar(255) DEFAULT NULL COMMENT '域名url',
  `update_user` varchar(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of xxx_xhs_user
-- ----------------------------
