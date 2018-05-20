package com.jjt.wechat.auth;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jjt.common.utils.CheckUtils;
import com.jjt.wechat.constants.WechatConstants;
import com.jjt.wechat.helper.ConfigHelper;
import com.jjt.wechat.helper.TokenHelper;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ConfigHelper configHelper;

	@Autowired
	private TokenHelper tokenHelper;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		LoginRequired annotation = null;
		String requestUrl = null;
		String contextPath = null;
		try {
			if (!handler.getClass().isAssignableFrom(HandlerMethod.class)) {
				return true;
			}
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			// 先判断是否有注解
			annotation = method.getAnnotation(LoginRequired.class);
			// 不需要绑定或一键登录
			if (CheckUtils.isNull(annotation)) {
				return true;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			response.sendRedirect(WechatConstants.View.ERROR_ACTION);
			return false;
		}

		String openId = getCookieValue(request);

		if (CheckUtils.isNullOrEmpty(openId)) {
			logger.info("openid为空或null,重新获取");
			try {
				contextPath = request.getContextPath();
				requestUrl = request.getRequestURL().toString();
				requestUrl = java.net.URLEncoder.encode(requestUrl, "utf-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(e.getMessage());
				response.sendRedirect(WechatConstants.View.ERROR_ACTION);
				return false;
			}
			request.getSession().setAttribute(
					WechatConstants.QiaoHuAPI.SERVLET_PATH, requestUrl);
			// 请求的路径
			response.sendRedirect(contextPath + "/oauth/api?resultUrl="
					+ requestUrl);
			return false;
		} else {
			logger.info("openid不为空：" + openId);
		}
		return true;
	}

	/**
	 * 在业务处理器处理请求执行完成后,生成视图之前执行的动作
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	/**
	 * 在DispatcherServlet完全处理完请求后被调用
	 * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

	private String getCookieValue(HttpServletRequest request) {
		String openId = null;
		try {
			if (request.getCookies() != null) {
				for (Cookie cookie : request.getCookies()) {
					String cookieName = cookie.getName();
					if (configHelper.cookieKey.equals(cookieName)) {
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

	/**
	 * check用户单点登录状态
	 * 
	 * @param request
	 *            客户端请求
	 * @param userCookie
	 *            用户数据库存储的cookie
	 * @return
	 */
	private boolean checkSingleSignOnStatus(HttpServletRequest request,
			String userCookie) {

		String qiaohuC = null;
		String cookieL = null;
		String cookieT = null;

		HttpClient client = new HttpClient();
		// 数据库cookie优先
		if (!CheckUtils.isNullOrEmpty(userCookie)) {
			qiaohuC = userCookie;
		} else {
			if (request.getCookies() != null) {
				for (Cookie cookie : request.getCookies()) {
					String cookieName = cookie.getName();
					if (WechatConstants.QiaoHuAPI.COOKIE_TGC.equals(cookieName)) {
						qiaohuC = cookie.getValue();
					}
					if (WechatConstants.QiaoHuAPI.COOKIE_LOGIN
							.equals(cookieName)) {
						cookieL = cookie.getValue();
					}
					if (WechatConstants.QiaoHuAPI.COOKIE_TIMEOUT
							.equals(cookieName)) {
						cookieT = cookie.getValue();
					}
				}
			}
		}
		if (CheckUtils.isNullOrEmpty(qiaohuC)) {
			return false;
		}
		logger.info("qiaohuC:" + qiaohuC + "\n cookieL:" + cookieL
				+ "\n cookieT:" + cookieT);

		client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,
				"UTF-8");
		client.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
		client.getParams().setParameter(HttpMethodParams.SINGLE_COOKIE_HEADER,
				true);

		GetMethod getMethod = new GetMethod(configHelper.singleSignOnUrl);
		String auth = configHelper.singleSignOnUser + ":"
				+ configHelper.singleSignOnPwd;
		byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset
				.forName("US-ASCII")));
		String authHeader = "Basic " + new String(encodedAuth);

		getMethod.addRequestHeader("Authorization", authHeader);
		getMethod.addRequestHeader("Cookie", "_qiaohu_c=" + qiaohuC);
		int statusCode;
		try {
			statusCode = client.executeMethod(getMethod);
			// 判断网络连接状态码是否正常(0--200都数正常)
			String responseBody = null;
			logger.info("单点登录请求返回状态码statusCode:" + statusCode);
			if (statusCode == HttpStatus.SC_OK) {
				responseBody = getMethod.getResponseBodyAsString();
				Document doc = Jsoup.parse(responseBody);

				String ok = doc.body().text();
				String title = doc.title();
				logger.info("单点登录请求返回内容:title:" + title + ";ok:" + ok);
				if ("ok".equalsIgnoreCase(ok)) {
					return true;
				}
				return false;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
		return false;
	}

}
