package com.xxx.wechat.admin.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.shiro.codec.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xxx.wechat.common.wechat.api.entity.UserInfo;
import com.xxx.wechat.core.dao.entity.AdminUser;

@Component
public class JwtConfig {

	private static final Logger logger = LoggerFactory.getLogger(JwtConfig.class);

	private static final String JWT_SECRET = "7786df7fc3a34e26a61c034d5ec8245d";

	// one month
	private static final long ACTIVETIME = 1000l * 60l * 60l * 24l * 30l;

	private static String appTokenIssuer = "abc";

	/**
	 * 
	 * @param userInfo
	 * @param loginDate
	 * @return
	 */
	public String createJWT(AdminUser userInfo, Date loginDate) {

		if (userInfo == null) {
			return null;
		}
		// The JWT signature algorithm we will be using to sign the token
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		JwtBuilder builder = Jwts.builder().setId(appTokenIssuer).setIssuedAt(loginDate)// 发行时间
				.setSubject(generalSubject(userInfo))// 抽象主题
				.setIssuer(appTokenIssuer)// 发行人
				.setAudience(String.valueOf(userInfo.getRoleId()))// 角色
				.signWith(signatureAlgorithm, generalKey());

		if (ACTIVETIME >= 0) {
			Date exp = new Date(System.currentTimeMillis() + ACTIVETIME);
			builder.setExpiration(exp);
		}

		return builder.compact();
	}

	/**
	 * 
	 * @param userInfo
	 * @return
	 */
	public String createJWT(AdminUser userInfo) {

		if (userInfo == null) {
			return null;
		}
		// The JWT signature algorithm we will be using to sign the token
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		JwtBuilder builder = Jwts.builder().setId(appTokenIssuer).setIssuedAt(new Date())// 发行时间
				.setSubject(generalSubject(userInfo))// 抽象主题
				.setIssuer(appTokenIssuer)// 发行人
				.setAudience(String.valueOf(userInfo.getRoleId()))// 角色
				.signWith(signatureAlgorithm, generalKey());

		if (ACTIVETIME >= 0) {
			Date exp = new Date(System.currentTimeMillis() + ACTIVETIME);
			builder.setExpiration(exp);
		}

		return builder.compact();
	}

	public AdminUser parseJWT(String jwt) throws Exception {
		Claims claims = null;
		try {
			claims = Jwts.parser().setSigningKey(generalKey()).parseClaimsJws(jwt).getBody();
		} catch (ExpiredJwtException e) {
			logger.error("登录超时");
			return null;
		} catch (SignatureException | MalformedJwtException | UnsupportedJwtException e) {
			logger.error(e.getMessage());
			logger.error("解析失败", e);
			throw new Exception(e);
		} catch (IllegalArgumentException e) {
			logger.error(e.getMessage());
			logger.error("参数异常", e);
			throw new Exception(e);
		}
		return JSON.parseObject(claims.getSubject(), AdminUser.class);

	}

	/**
	 * 由字符串生成加密key
	 * 
	 * @return
	 */
	private SecretKey generalKey() {
		byte[] encodedKey = Base64.decode(appTokenIssuer + JWT_SECRET);
		SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
		return key;
	}

	/**
	 * 生成subject信息
	 * 
	 * @param userInfo
	 * @return
	 */
	public static String generalSubject(AdminUser userInfo) {
		JSONObject jo = new JSONObject();
		jo.put("id", userInfo.getAdminId());
		jo.put("name", userInfo.getName());
		jo.put("roleId", userInfo.getRoleId());
		jo.put("portrait", userInfo.getPortrait());
		return jo.toJSONString();
	}
}
