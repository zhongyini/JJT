package com.xxx.wechat.front.config;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常处理
 * 
 * @author yangk
 * @date 2018-11-06
 * @description 全局异常处理: 使用 @RestControllerAdvice + @ExceptionHandler
 *              可以处理大部分开发中用到的自自定义业务异常处理了，再也不用在 Controller层进行 try-catch了
 *              注解方式实现全局异常处理
 * 
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	private final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

	/**
	 * 申明捕获哪个异常类
	 * 
	 * @param request
	 * @param e
	 * @return 返回json字符串
	 */
	@ExceptionHandler(value = Exception.class)
	public String ExceptionHandler(HttpServletRequest request, Exception e) {
		// 错误日志处理
		logger.error(e.getMessage());
		// 返回的消息处理
		return "ERRORRRR";
	}

}
