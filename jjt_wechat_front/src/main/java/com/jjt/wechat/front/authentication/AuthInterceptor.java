package com.jjt.wechat.front.authentication;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jjt.wechat.common.utils.CheckUtils;
import com.jjt.wechat.front.exception.ErrorPageException;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//用户是否需要处于登陆
		boolean isLand = false;

		try {
			//判断该接口用户是否需要处于登陆状态
			isLand = checkUserLand(handler);
			if (isLand) {
				
			}
		} catch (ErrorPageException e) {
			// TODO: 跳转到错误页面
			return false;
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

}
