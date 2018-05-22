SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS jjt_admin_user;
DROP TABLE IF EXISTS jjt_authority;
DROP TABLE IF EXISTS jjt_authority_code;
DROP TABLE IF EXISTS jjt_configuration;
DROP TABLE IF EXISTS jjt_sns_token;
DROP TABLE IF EXISTS jjt_wechat_role;
DROP TABLE IF EXISTS jjt_wechat_token;
DROP TABLE IF EXISTS jjt_wechat_user;




/* Create Tables */

CREATE TABLE jjt_admin_user
(
	admin_id varchar(20) NOT NULL,
	name varchar(20) NOT NULL,
	password varchar(128) NOT NULL,
	role_id varchar(8) NOT NULL,
	password_overdue_date varchar(8) NOT NULL,
	mail varchar(200),
	portrait varchar(200),
	delete_flag char NOT NULL,
	create_user varchar(20) NOT NULL,
	create_date datetime NOT NULL,
	modify_user varchar(20) NOT NULL,
	modify_date datetime NOT NULL,
	PRIMARY KEY (admin_id)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


CREATE TABLE jjt_authority
(
	ROLE_ID varchar(20) NOT NULL,
	AUTHORITY_CODE varchar(50) NOT NULL
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


CREATE TABLE jjt_authority_code
(
	CODE varchar(50) NOT NULL,
	NAME varchar(50) NOT NULL,
	PRIMARY KEY (CODE)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


CREATE TABLE jjt_configuration
(
	ITEM varchar(50) NOT NULL,
	property varchar(600),
	NAME varchar(50) NOT NULL,
	TYPE varchar(1) NOT NULL,
	MEMO varchar(50),
	PRIMARY KEY (ITEM)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


CREATE TABLE jjt_sns_token
(
	openid varchar(30) NOT NULL,
	access_token varchar(255),
	jsapi_ticket varchar(255),
	expires_in int,
	refresh_token varchar(255),
	scope varchar(100),
	unionid varchar(50),
	errcode varchar(8),
	errmsg varchar(256),
	create_date datetime NOT NULL,
	PRIMARY KEY (openid)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


CREATE TABLE jjt_wechat_role
(
	role_id varchar(20) NOT NULL,
	role_name varchar(50) NOT NULL,
	create_user varchar(20) NOT NULL,
	create_date datetime NOT NULL,
	modify_user varchar(20) NOT NULL,
	modify_date datetime NOT NULL,
	PRIMARY KEY (role_id)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


CREATE TABLE jjt_wechat_token
(
	access_token varchar(255) NOT NULL,
	jsapi_ticket varchar(255),
	expires_in int NOT NULL,
	errcode varchar(8),
	errmsg varchar(256),
	create_date datetime NOT NULL,
	PRIMARY KEY (access_token)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


CREATE TABLE jjt_wechat_user
(
	open_id varchar(30) NOT NULL,
	nickname varchar(50),
	sex char,
	province varchar(10),
	city varchar(10),
	country varchar(10),
	headimgurl varchar(255),
	unionid varchar(10),
	updatetime datetime NOT NULL,
	PRIMARY KEY (open_id)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;



