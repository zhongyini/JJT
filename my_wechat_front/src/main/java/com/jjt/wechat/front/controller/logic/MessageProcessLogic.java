package com.jjt.wechat.front.controller.logic;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.jjt.wechat.common.constant.Constant;
import com.jjt.wechat.common.utils.CheckUtils;

@Component
public class MessageProcessLogic extends BaseLogic {

	private final String WECHAT_CONTENT_TYPE = "text/xml;charset=UTF-8";

	/**
	 * 将消息返回给微信
	 * 
	 * @param response
	 * @param result
	 */
	public void responseResult(HttpServletResponse response, String result) {
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
