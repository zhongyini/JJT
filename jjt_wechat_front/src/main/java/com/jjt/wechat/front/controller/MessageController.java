package com.jjt.wechat.front.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jjt.wechat.common.constant.Constant;
import com.jjt.wechat.common.utils.CheckUtils;
import com.jjt.wechat.front.authentication.LoginRequired;
import com.jjt.wechat.front.controller.logic.MessageConfigLogic;
import com.jjt.wechat.front.controller.logic.MessageProcessLogic;
import com.jjt.wechat.front.controller.logic.MessageXmlLogic;

@RestController
@RequestMapping("/message")
public class MessageController extends BaseController{
	@Autowired
	MessageConfigLogic messageConfigLogic;
	
	@Autowired
	MessageXmlLogic messageXmlLogic;
	
	@Autowired
	MessageProcessLogic messageProcessLogic;
	
	
	/**
	 * 配置微信接口
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	@LoginRequired(isLand = false)
	public String bind(HttpServletRequest request) {
		if(messageConfigLogic.isLegal(request)){
			logger.info("Wechat 接口配置成功.");
			return request.getParameter(Constant.WechatParams.MSG_PARAM_ECHOSTR);
		}
		logger.info("Wechat 接口配置失败.");
		return null;
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	@LoginRequired(isLand = false)
	public final void process(HttpServletRequest request,HttpServletResponse response){
		//解析微信消息
		Map<String, Object> reqMap = messageXmlLogic.parseXml(request);
		//若没有消息，则返回空消息给微信
		if(CheckUtils.isNull(reqMap)){
			logger.error("Wechat message is null");
			messageProcessLogic.responseResult(response, Constant.EMPTY);
			return;
		}
		
		String msgType = (String) reqMap.get(Constant.WechatParams.MSGTYPE);
		String event = (String)reqMap.get(Constant.WechatParams.EVENT);
		String eventKey = (String) reqMap.get(Constant.WechatParams.EVENTKEY);
		
		//TODO 处理返回消息
		messageProcessLogic.responseResult(response, Constant.EMPTY);
		logger.info("获取微信消息：MsgType is {}. Event is {}. EventKey is {}", msgType, event, eventKey);
		
	}
	

	
	

}
