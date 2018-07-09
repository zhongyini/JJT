SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS xxx_wechat_pay_api_log;
DROP TABLE IF EXISTS xxx_wechat_payment;
DROP TABLE IF EXISTS xxx_wechat_user_account;




/* Create Tables */

CREATE TABLE xxx_wechat_payment
(
	-- 支付主键
	pay_id bigint NOT NULL AUTO_INCREMENT COMMENT '支付主键',
	-- 用户微信openid
	openid varchar(32) NOT NULL COMMENT '用户微信openid',
	-- 支付金额
	amount bigint NOT NULL COMMENT '支付金额',
	-- 贸易编号
	trade_no varchar(32) COMMENT '贸易编号',
	-- 付款状态
	-- 1:未领取
	-- 2:领取成功
	-- 3:领取失败
	-- 4:领取成功(api网络失败)
	-- 5:领取失败(api失败[全部错误])
	status char(1) NOT NULL COMMENT '付款状态
1:未领取
2:领取成功
3:领取失败
4:领取成功(api网络失败)
5:领取失败(api失败[全部错误])',
	-- 创建时间
	create_datetime datetime DEFAULT current_timestamp(3) NOT NULL COMMENT '创建时间',
	-- 更新时间
	update_datetime datetime DEFAULT current_timestamp(3) NOT NULL COMMENT '更新时间',
	PRIMARY KEY (pay_id)
);


CREATE TABLE xxx_wechat_pay_api_log
(
	-- 支付API主键
	pay_api_id bigint NOT NULL AUTO_INCREMENT COMMENT '支付API主键',
	-- 支付主键
	pay_id bigint COMMENT '支付主键',
	-- 贸易编号
	trade_no varchar(32) COMMENT '贸易编号',
	-- 支付接口URL
	api_url varchar(200) NOT NULL COMMENT '支付接口URL',
	-- 请求参数
	parameters text NOT NULL COMMENT '请求参数',
	-- 服务器响应状态
	return_code int NOT NULL COMMENT '服务器响应状态',
	-- 返回json数据
	return_json text NOT NULL COMMENT '返回json数据',
	-- 请求耗时
	elapsed bigint NOT NULL COMMENT '请求耗时',
	-- 创建时间
	create_datetime datetime DEFAULT current_timestamp(3) NOT NULL COMMENT '创建时间',
	PRIMARY KEY (pay_api_id)
);


CREATE TABLE xxx_wechat_user_account
(
	-- 用户微信openid
	openid varchar(32) NOT NULL COMMENT '用户微信openid',
	-- 用户微信unionid
	unionid varchar(32) NOT NULL COMMENT '用户微信unionid',
	-- 账户余额
	-- 单位:分
	balance bigint DEFAULT 0 NOT NULL COMMENT '账户余额
单位:分',
	-- 创建时间
	create_datetime datetime DEFAULT current_timestamp(3) NOT NULL COMMENT '创建时间',
	-- 更新时间
	update_datetime datetime DEFAULT current_timestamp(3) NOT NULL COMMENT '更新时间',
	PRIMARY KEY (openid)
);



/* Create Foreign Keys */

ALTER TABLE xxx_wechat_pay_api_log
	ADD FOREIGN KEY (pay_id)
	REFERENCES xxx_wechat_payment (pay_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE xxx_wechat_payment
	ADD FOREIGN KEY (openid)
	REFERENCES xxx_wechat_user_account (openid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



