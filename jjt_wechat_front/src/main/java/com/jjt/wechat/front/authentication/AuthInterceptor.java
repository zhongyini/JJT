package com.jjt.wechat.front.authentication;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jjt.wechat.common.utils.CheckUtils;
import com.jjt.wechat.common.wechat.api.UserAPI;
import com.jjt.wechat.common.wechat.api.response.GetUserInfoResponse;
import com.jjt.wechat.core.config.WechatConfig;
import com.jjt.wechat.core.dao.entity.WechatUserInfo;
import com.jjt.wechat.front.exception.ErrorPageException;
import com.jjt.wechat.front.service.IWechatUserService;
import com.jjt.wechat.helper.ConfigHelper;
import com.jjt.wechat.helper.TokenHelper;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TokenHelper tokenHelper;
	
	@Autowired
	private ConfigHelper configHelper;
	
	@Autowired
	private IWechatUserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//用户是否需要处于登陆
		boolean isLand = false;
		String requestUrl = null;
		String contextPath = null;
		try {
			//判断该接口用户是否需要处于登陆状态
			isLand = checkUserLand(handler);
			if (!isLand) {
				return true;
			}
		} catch (ErrorPageException e) {
			logger.error(e.getMessage());
			// TODO: 跳转到错误页面
			response.sendRedirect("/common/error");
			return false;
		}
		
		String openId = tokenHelper.parseJWT(getCookieValue(request));
		if (CheckUtils.isNullOrEmpty(openId)) {
			logger.info("openid为空或null,重新获取");
			try {
				contextPath = request.getContextPath();
				requestUrl = request.getRequestURL().toString();
				requestUrl = java.net.URLEncoder.encode(requestUrl, "utf-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(e.getMessage());
				response.sendRedirect("/common/error");
				return false;
			}
			// 请求的路径
			response.sendRedirect(contextPath + "/oauth/api?resultUrl=" + requestUrl);
			return false;
		} else {
			logger.info("openid不为空：" + openId);
			// 判断用户信息是否存在
			try {
				WechatUserInfo wechatUser = userService.findByOpenId(openId);
				if (CheckUtils.isNull(wechatUser)) {
					logger.info("openID：" + openId + "的用户不存在");
					response.sendRedirect(contextPath + "/oauth/api?resultUrl=" + requestUrl);
					return false;
				}
			} catch (Exception e) {
				logger.error("获取微信用户失败，参数openID：" + openId);
				logger.error(e.getMessage(), e);
				// 进入404画面
				response.sendRedirect("/common/error");
				return false;
			}
		}
		return true;
	}

	//检查用户使用该接口是否需要登陆
	private boolean checkUserLand(Object handler) throws ErrorPageException {
		// 接口登陆判断
		boolean isLand = false;
		try {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			LoginRequired annotation = method.getAnnotation(LoginRequired.class);
			if (!CheckUtils.isNull(annotation) && annotation.isLand() == true) {
				isLand = true;
			}
			return isLand;
		} catch (Exception e) {
			logger.error("接口登陆判断出错", e.getMessage());
			throw new ErrorPageException("接口登陆判断出错" + e.getMessage());
		}
	}

	private String getCookieValue(HttpServletRequest request) {
		String openId = null;
		try {
			if (request.getCookies() != null) {
				for (Cookie cookie : request.getCookies()) {
					String cookieName = cookie.getName();
					if ("jjt_test_openId".equals(cookieName)) {
						openId = cookie.getValue();
						break;
					}
				}
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return openId;
	}

}
