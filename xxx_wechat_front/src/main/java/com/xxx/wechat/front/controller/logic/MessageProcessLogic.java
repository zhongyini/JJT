package com.xxx.wechat.front.controller.logic;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xxx.wechat.common.constant.Constant;
import com.xxx.wechat.common.utils.CheckUtils;

public class MessageProcessLogic extends BaseLogic {

	protected final static Logger logger = LoggerFactory.getLogger(MessageProcessLogic.class);
	
	private final static String WECHAT_CONTENT_TYPE = "text/xml;charset=UTF-8";

	/**
	 * 将消息返回给微信
	 * 
	 * @param response
	 * @param result
	 */
	public static void responseResult(HttpServletResponse response, String result) {
		if (CheckUtils.isNullOrEmpty(result)) {
			result = Constant.EMPTY;
		}
		PrintWriter writer = null;
		try {
			response.setContentType(WECHAT_CONTENT_TYPE);
			writer = response.getWriter();
			writer.write(result);
		} catch (Exception e) {
			logger.error("Response wechat message error.");
			logger.error(e.getMessage());
		} finally {
			writer.close();
		}

	}

}
