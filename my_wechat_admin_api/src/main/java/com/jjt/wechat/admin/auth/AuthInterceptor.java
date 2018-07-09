package com.jjt.wechat.admin.auth;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jjt.wechat.admin.dto.LoginReq;
import com.jjt.wechat.constants.Constants;
import com.jjt.wechat.core.exception.AppException;
import com.jjt.wechat.helper.AuthorityHelper;
import com.jjt.wechat.helper.TokenHelper;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	private static Logger logger = LoggerFactory
			.getLogger(AuthInterceptor.class);
	@Autowired
	private TokenHelper tokenHelper;

	@Autowired
	private AuthorityHelper authorityHelper;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (!handler.getClass().isAssignableFrom(HandlerMethod.class)) {
			return true;
		}

		// 判断是否需要权限认证
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		AuthRequired annotation = method.getAnnotation(AuthRequired.class);
		if (annotation != null) {

			// 验证token
			String token = request.getHeader("x-access-token");
			// 客户端请求没有携带发行的token，返回服务器拒绝请求
			if (token == null) {
				logger.error("客户端token不存在");
				response.sendError(Constants.STATUS_NO_TOKEN);
				return false;
			} else {
				LoginReq admin = null;
				try {
					logger.info(token);
					admin = tokenHelper.parseJWT(token);
				} catch (AppException e) {
					logger.error(e.getMessage());
					response.sendError(Constants.STATUS_TOO_LONG_LOGIN);
					return false;
				}
				// tonken过期，重新登录
				if (admin == null) {
					response.sendError(Constants.STATUS_TOO_LONG_LOGIN);
					return false;
				}

				// 判断用户是否已经登录.从全局中取出判断token是否相同，不相同 就重新登录
				HttpSession session = request.getSession();

				LoginReq appAdmin = (LoginReq) session.getServletContext()
						.getAttribute(Constants.USER);

				// tonken过期，重新登录
				if (appAdmin == null) {
					response.sendError(Constants.STATUS_TOO_LONG_LOGIN);
					return false;
				}

				// 更新token
				response.addHeader("x-access-token",
						tokenHelper.createJWT(admin));
				request.getSession().setAttribute(Constants.USER, admin);
				// 没有权限
				if (!authorityHelper.hasPermission(admin.getRoleId(),
						annotation.permission())) {
					response.sendError(Constants.STATUS_NO_PERMISSION);
					return false;
				}

			}

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

}
