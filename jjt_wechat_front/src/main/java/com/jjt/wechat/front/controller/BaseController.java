package com.jjt.wechat.front.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.jjt.common.utils.CheckUtils;
import com.jjt.wechat.constants.WechatConstants;
import com.jjt.wechat.helper.ConfigHelper;
import com.jjt.wechat.helper.TokenHelper;

public abstract class BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	protected TokenHelper tokenHelper;
	
	@Autowired
	protected ConfigHelper configHelper;
	
	@Autowired
	protected HttpServletRequest request;

	@Autowired
	protected HttpServletResponse response;
	
	protected String getOpenId() {
		return getCookieValue(configHelper.cookieKey);
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
	
	protected void setResponseCookie(String key, String value) {
		Cookie cookie = new Cookie(key, value);
		cookie.setDomain(WechatConstants.QiaoHuAPI.COOKIE_DOMAIN);
		cookie.setMaxAge(WechatConstants.QiaoHuAPI.USER_INFO_TIMEOUT);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
}
