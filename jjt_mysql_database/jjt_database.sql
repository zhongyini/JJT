SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS jjt_admin_authority;
DROP TABLE IF EXISTS jjt_admin_authority_code;
DROP TABLE IF EXISTS jjt_admin_role;
DROP TABLE IF EXISTS jjt_admin_user;
DROP TABLE IF EXISTS jjt_configuration;
DROP TABLE IF EXISTS jjt_sns_token;
DROP TABLE IF EXISTS jjt_wechat_card;
DROP TABLE IF EXISTS jjt_wechat_qrcode;
DROP TABLE IF EXISTS jjt_wechat_qrcode_type;
DROP TABLE IF EXISTS jjt_wechat_recommend;
DROP TABLE IF EXISTS jjt_wechat_token;
DROP TABLE IF EXISTS jjt_wechat_user;
DROP TABLE IF EXISTS t_wechat_card_consume;




/* Create Tables */

CREATE TABLE jjt_admin_authority
(
	role_id varchar(20) NOT NULL,
	code varchar(50) NOT NULL,
	PRIMARY KEY (role_id, code)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


CREATE TABLE jjt_admin_authority_code
(
	CODE varchar(50) NOT NULL,
	-- 配置名
	NAME varchar(50) NOT NULL COMMENT '配置名',
	PRIMARY KEY (CODE)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


CREATE TABLE jjt_admin_role
(
	role_id varchar(20) NOT NULL,
	role_name varchar(50) NOT NULL,
	modify_user varchar(20) NOT NULL,
	modify_date datetime NOT NULL,
	PRIMARY KEY (role_id)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


CREATE TABLE jjt_admin_user
(
	admin_id varchar(20) NOT NULL,
	role_id varchar(20),
	name varchar(20) NOT NULL,
	password varchar(128) NOT NULL,
	password_overdue_date varchar(8) NOT NULL,
	mail varchar(200),
	portrait varchar(200),
	delete_flag char NOT NULL,
	create_user varchar(20) NOT NULL,
	-- 生成时间
	create_date datetime NOT NULL COMMENT '生成时间',
	modify_user varchar(20) NOT NULL,
	modify_date datetime NOT NULL,
	PRIMARY KEY (admin_id)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


CREATE TABLE jjt_configuration
(
	-- 配置码
	ITEM varchar(50) NOT NULL COMMENT '配置码',
	-- 配置属性
	property varchar(600) NOT NULL COMMENT '配置属性',
	-- 配置名
	NAME varchar(50) COMMENT '配置名',
	-- 配置类型(0系统配置;1后台配置;2业务配置)
	TYPE varchar(1) COMMENT '配置类型(0系统配置;1后台配置;2业务配置)',
	-- 注释
	MEMO varchar(50) COMMENT '注释',
	PRIMARY KEY (ITEM)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


CREATE TABLE jjt_sns_token
(
	openid varchar(30) NOT NULL,
	-- 微信凭证access_token
	access_token varchar(255) COMMENT '微信凭证access_token',
	-- JS接口的临时票据
	jsapi_ticket varchar(255) COMMENT 'JS接口的临时票据',
	-- 凭证有效时间，单位：秒
	expires_in int COMMENT '凭证有效时间，单位：秒',
	refresh_token varchar(255),
	scope varchar(100),
	unionid varchar(50),
	-- 错误码
	errcode varchar(8) COMMENT '错误码',
	-- 错误信息
	errmsg varchar(256) COMMENT '错误信息',
	-- 生成时间
	create_date datetime NOT NULL COMMENT '生成时间',
	PRIMARY KEY (openid)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


CREATE TABLE jjt_wechat_card
(
	-- 卡券ID。一个卡券ID对应一类卡券，包含了相应库存数量的Code码。
	card_id varchar(30) NOT NULL COMMENT '卡券ID。一个卡券ID对应一类卡券，包含了相应库存数量的Code码。',
	-- 卡券类型(GROUPON团购券;CASH代金券;DISCOUNT折扣券;GIFT兑换券;GENERAL_COUPON优惠券)
	card_type varchar(24) COMMENT '卡券类型(GROUPON团购券;CASH代金券;DISCOUNT折扣券;GIFT兑换券;GENERAL_COUPON优惠券)',
	-- 代金券专用，表示起用金额（单位为分）,如果无起用门槛则填0
	least_cost int COMMENT '代金券专用，表示起用金额（单位为分）,如果无起用门槛则填0',
	-- 代金券专用，表示减免金额。（单位为分）
	reduce_cost int COMMENT '代金券专用，表示减免金额。（单位为分）',
	-- 团购券专用，团购详情。
	deal_detail text COMMENT '团购券专用，团购详情。',
	-- 卡券的商户logo，建议像素为300*300。
	logo_url varchar(150) COMMENT '卡券的商户logo，建议像素为300*300。',
	-- 码型： "CODE_TYPE_TEXT"文 本 ； "CODE_TYPE_BARCODE"一维码 "CODE_TYPE_QRCODE"二维码 "CODE_TYPE_ONLY_QRCODE",二维码无code显示； "CODE_TYPE_ONLY_BARCODE",一维码无code显示；CODE_TYPE_NONE， 不显示code和条形码类型
	code_type varchar(16) COMMENT '码型： "CODE_TYPE_TEXT"文 本 ； "CODE_TYPE_BARCODE"一维码 "CODE_TYPE_QRCODE"二维码 "CODE_TYPE_ONLY_QRCODE",二维码无code显示； "CODE_TYPE_ONLY_BARCODE",一维码无code显示；CODE_TYPE_NONE， 不显示code和条形码类型',
	-- 商户名字,字数上限为12个汉字。
	brand_name varchar(36) COMMENT '商户名字,字数上限为12个汉字。',
	-- 卡券名，字数上限为9个汉字。(建议涵盖卡券属性、服务及金额)。
	title varchar(27) COMMENT '卡券名，字数上限为9个汉字。(建议涵盖卡券属性、服务及金额)。',
	-- 卡券背景颜色
	color varchar(16) COMMENT '卡券背景颜色',
	-- 卡券使用提醒，字数上限为16个汉字。
	notice varchar(48) COMMENT '卡券使用提醒，字数上限为16个汉字。',
	-- 卡券使用说明，字数上限为1024个汉字。
	description text COMMENT '卡券使用说明，字数上限为1024个汉字。',
	-- 卡券库存的数量，上限为100000000。
	quantity int COMMENT '卡券库存的数量，上限为100000000。',
	-- 使用时间的类型，旧文档采用的1和2依然生效。DATE_TYPE_FIX _TIME_RANGE 表示固定日期区间，DATETYPE FIX_TERM 表示固定时长 (自领取后按天算)。
	type varchar(30) COMMENT '使用时间的类型，旧文档采用的1和2依然生效。DATE_TYPE_FIX _TIME_RANGE 表示固定日期区间，DATETYPE FIX_TERM 表示固定时长 (自领取后按天算)。',
	-- type为DATE_TYPE_FIX_TIME_RANGE时专用，表示起用时间。从1970年1月1日00:00:00至起用时间的秒数，最终需转换为字符串形态传入。（东八区时间,UTC+8，单位为秒）
	begin_timestamp varchar(20) COMMENT 'type为DATE_TYPE_FIX_TIME_RANGE时专用，表示起用时间。从1970年1月1日00:00:00至起用时间的秒数，最终需转换为字符串形态传入。（东八区时间,UTC+8，单位为秒）',
	-- 表示结束时间 ， 建议设置为截止日期的23:59:59过期 。 （ 东八区时间,UTC+8，单位为秒 ）
	end_timestamp varchar(30) COMMENT '表示结束时间 ， 建议设置为截止日期的23:59:59过期 。 （ 东八区时间,UTC+8，单位为秒 ）',
	-- type为DATE_TYPE_FIX_TERM时专用，表示自领取后多少天内有效，不支持填写0。
	fixed_term int COMMENT 'type为DATE_TYPE_FIX_TERM时专用，表示自领取后多少天内有效，不支持填写0。',
	-- type为DATE_TYPE_FIX_TERM时专用，表示自领取后多少天开始生效，领取后当天生效填写0。（单位为天）
	fixed_begin_term int DEFAULT 0 COMMENT 'type为DATE_TYPE_FIX_TERM时专用，表示自领取后多少天开始生效，领取后当天生效填写0。（单位为天）',
	PRIMARY KEY (card_id)
);


CREATE TABLE jjt_wechat_qrcode
(
	id int NOT NULL AUTO_INCREMENT,
	open_id varchar(30),
	ticket varchar(200),
	qrcode_type int,
	create_date datetime,
	PRIMARY KEY (id)
);


CREATE TABLE jjt_wechat_qrcode_type
(
	id int NOT NULL AUTO_INCREMENT,
	scene varchar(50),
	qrcode_name varchar(50),
	modify_admin varchar(30),
	modify_time datetime,
	PRIMARY KEY (id)
);


CREATE TABLE jjt_wechat_recommend
(
	id int NOT NULL AUTO_INCREMENT,
	open_id varchar(30),
	qrcode_id int,
	status int DEFAULT 0,
	updatetime timestamp,
	PRIMARY KEY (id)
);


CREATE TABLE jjt_wechat_token
(
	-- 微信凭证access_token
	access_token varchar(255) NOT NULL COMMENT '微信凭证access_token',
	-- JS接口的临时票据
	jsapi_ticket varchar(255) COMMENT 'JS接口的临时票据',
	-- 凭证有效时间，单位：秒
	expires_in int COMMENT '凭证有效时间，单位：秒',
	-- 错误码
	errcode varchar(8) COMMENT '错误码',
	-- 错误信息
	errmsg varchar(256) COMMENT '错误信息',
	-- 生成时间
	create_date datetime NOT NULL COMMENT '生成时间',
	PRIMARY KEY (access_token)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


CREATE TABLE jjt_wechat_user
(
	-- 微信openid
	openid varchar(30) NOT NULL COMMENT '微信openid',
	-- 微信昵称
	nickname varchar(50) COMMENT '微信昵称',
	-- 性别
	sex char COMMENT '性别',
	-- 省份
	province varchar(10) COMMENT '省份',
	-- 城市
	city varchar(10) COMMENT '城市',
	-- 国家
	country varchar(10) COMMENT '国家',
	-- 头像url
	headimgurl varchar(255) COMMENT '头像url',
	-- 微信unionid
	unionid varchar(10) COMMENT '微信unionid',
	-- 更新时间
	updatetime datetime NOT NULL COMMENT '更新时间',
	PRIMARY KEY (openid)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


CREATE TABLE t_wechat_card_consume
(
	id int NOT NULL AUTO_INCREMENT,
	-- 核销对象
	open_id varchar(30) NOT NULL COMMENT '核销对象',
	-- 核销者
	admin_id varchar(30) COMMENT '核销者',
	-- 核销卡券号
	card_id varchar(30) NOT NULL COMMENT '核销卡券号',
	-- 核销Code号
	code varchar(20) COMMENT '核销Code号',
	-- 状态(0未核销;1已核销)
	status int COMMENT '状态(0未核销;1已核销)',
	updatetime datetime,
	PRIMARY KEY (id)
);



