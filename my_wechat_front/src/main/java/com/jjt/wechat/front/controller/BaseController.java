package com.jjt.wechat.front.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.jjt.wechat.common.utils.CheckUtils;
import com.jjt.wechat.helper.ConfigHelper;
import com.jjt.wechat.helper.TokenHelper;

public abstract class BaseController {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	protected HttpServletRequest request;
	
	@Autowired
	protected HttpServletResponse response;
	
	@Autowired
	protected ConfigHelper configHelper;
	
	@Autowired
	protected TokenHelper tokenHelper;
	
	/**
	 * cookie
	 * 一年
	 */
	private static int USER_INFO_TIMEOUT = 365 * 24 * 60 * 60;

	
	protected String getOpenId() {
		return tokenHelper
				.parseJWT(getCookieValue(configHelper.cookieKey));
	}
	
	protected void setResponseCookie(String key, String value) {
		Cookie cookie = new Cookie(key, value);
		cookie.setMaxAge(USER_INFO_TIMEOUT);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
	
	protected String getCookieValue(String key) {
		if (CheckUtils.isNullOrEmpty(key)) {
			return null;
		}
		String openId = null;
		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
				String cookieName = cookie.getName();
				if (key.equals(cookieName)) {
					openId = cookie.getValue();
					break;
				}
			}
		}
		return openId;
	}

}
