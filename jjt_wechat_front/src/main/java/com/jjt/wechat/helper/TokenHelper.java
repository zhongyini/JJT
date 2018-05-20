package com.jjt.wechat.helper;

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

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TokenHelper {

	private static final Logger logger = LoggerFactory
			.getLogger(TokenHelper.class);

	private static final String JWT_SECRET = "7786df7fc3a34e26a61c034d5ec8245d";

	private static final String ISSUER = "qiaohu";

	private static final int ACTIVETIME = 1000 * 60 * 90;

	public String createJWT(String openId) {
		if (openId == null) {
			return null;
		}
		// The JWT signature algorithm we will be using to sign the token
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		JwtBuilder builder = Jwts.builder().setId(openId)// 针对当前token的唯一标识
				.setIssuedAt(now)// 发行时间
				.setSubject(openId)// 抽象主题
				.setIssuer(ISSUER)// 发行人
				.signWith(signatureAlgorithm, generalKey());

		// 设置token失效时间
		Date exp = new Date(nowMillis + ACTIVETIME);
		builder.setExpiration(exp);

		return builder.compact();
	}

	public String parseJWT(String jwt) {
		
		if (jwt == null) {
			return null;
		}
		Claims claims = null;
		try {
			claims = Jwts.parser().setSigningKey(generalKey())
					.parseClaimsJws(jwt).getBody();
		} catch (ExpiredJwtException e) {
			logger.info("登录超时");
			return null;
		} catch (SignatureException | MalformedJwtException
				| UnsupportedJwtException e) {
			logger.error(e.getMessage());
			logger.error("解析失败", e);
			return null;
		} catch (IllegalArgumentException e) {
			logger.error(e.getMessage());
			logger.error("参数异常", e);
			return null;
		}
		return (String) claims.getSubject();

	}

	/**
	 * 由字符串生成加密key
	 * 
	 * @return
	 */
	private SecretKey generalKey() {
		byte[] encodedKey = Base64.decodeBase64(ISSUER + JWT_SECRET);
		SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length,
				"AES");
		return key;
	}

}
