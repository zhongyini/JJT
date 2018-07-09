package com.xxx.wechat.front.authentication;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xxx.wechat.common.constant.Constant;
import com.xxx.wechat.common.utils.CheckUtils;
import com.xxx.wechat.core.dao.entity.WechatUser;
import com.xxx.wechat.front.exception.ErrorPageException;
import com.xxx.wechat.front.service.IWechatUserService;
import com.xxx.wechat.helper.ConfigHelper;
import com.xxx.wechat.helper.TokenHelper;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TokenHelper tokenHelper;

	@Autowired
	private ConfigHelper configHelper;

	@Autowired
	private IWechatUserService wechatUserService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 用户是否需要处于登陆
		boolean isLand = false;
		String requestUrl = null;
		String contextPath = null;
		try {
			// 判断该接口用户是否需要处于登陆状态
			isLand = checkUserLand(handler);
			if (!isLand) {
				return true;
			}
		} catch (ErrorPageException e) {
			logger.error(e.getMessage());
			// TODO: 跳转到错误页面
			response.sendRedirect(Constant.ControllerUrl.COMMON_ERROR);
			return false;
		}
		// 从cookie获取openid
		String openid = tokenHelper.parseJWT(getCookieValue(request));
		String recommend_openid = request.getParameter("recommend_openid");
		if (CheckUtils.isNullOrEmpty(openid)) {
			logger.info("openid为空,重新获取");
			try {
				contextPath = request.getContextPath();
				requestUrl = request.getRequestURL().toString();
				logger.info("requestUrl : " + requestUrl + "; recommend_openid:" + recommend_openid);
				requestUrl = java.net.URLEncoder.encode(requestUrl, "utf-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(e.getMessage());
				response.sendRedirect(Constant.ControllerUrl.COMMON_ERROR);
				return false;
			}
			// 请求的路径
			String url = contextPath + "/oauth/api?resultUrl=" + requestUrl;
			if(!CheckUtils.isNullOrEmpty(recommend_openid)){
				url = url + "?recommend_openid="+recommend_openid;
			}
			response.sendRedirect(url);
			return false;
		} else {
			logger.info("openid不为空：" + openid);
			// 判断用户信息是否存在
			try {
				WechatUser wechatUser = wechatUserService.findByOpenid(openid);
				if (CheckUtils.isNull(wechatUser)) {
					contextPath = request.getContextPath();
					requestUrl = request.getRequestURL().toString();
					requestUrl = java.net.URLEncoder.encode(requestUrl, "utf-8");
					logger.info("openid：" + openid + "的用户信息不存在或需重新获取信息");
					String url = contextPath + "/oauth/api?resultUrl=" + requestUrl;
					if(!CheckUtils.isNullOrEmpty(recommend_openid)){
						url = url + "?recommend_openid="+recommend_openid;
					}
					response.sendRedirect(url);
					return false;
				}
			} catch (Exception e) {
				logger.error("获取微信用户失败，openid：" + openid + e.getMessage());
				// 进入404画面
				response.sendRedirect(Constant.ControllerUrl.COMMON_ERROR);
				return false;
			}
		}
		return true;
	}

	// 检查用户使用该接口是否需要登陆
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
			throw new ErrorPageException("接口登录判断出错" + e.getMessage());
		}
	}

	private String getCookieValue(HttpServletRequest request) {
		String openid = null;
		try {
			if (request.getCookies() != null) {
				for (Cookie cookie : request.getCookies()) {
					String cookieName = cookie.getName();
					if (configHelper.cookieKey.equals(cookieName)) {
						openid = cookie.getValue();
						break;
					}
				}
			}
		} catch (Exception e) {
			logger.error("获取cookie信息失败：" + e.getMessage());
		}
		return openid;
	}

}
