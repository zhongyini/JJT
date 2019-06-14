package com.xxx.wechat.front.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.xxx.wechat.common.constant.Constant;
import com.xxx.wechat.common.utils.BeanUtils;
import com.xxx.wechat.common.utils.CheckUtils;
import com.xxx.wechat.common.wechat.api.BaseApi;
import com.xxx.wechat.common.wechat.api.enums.EventType;
import com.xxx.wechat.common.wechat.api.enums.WechatReqType;
import com.xxx.wechat.common.wechat.api.response.BaseResponse;
import com.xxx.wechat.core.dao.entity.WechatRecommend;
import com.xxx.wechat.front.controller.logic.MessageConfigLogic;
import com.xxx.wechat.front.controller.logic.MessageProcessLogic;
import com.xxx.wechat.front.controller.logic.MessageXmlLogic;

/**
 * 用来接收微信消息和事件的接口
 * @author yangk
 *
 */
@RestController
@RequestMapping("/message")
public class MessageController extends BaseController{
	
	protected static final Logger logger = LoggerFactory.getLogger(MessageController.class);
	
	/**
	 * 配置微信接口
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public String bind(HttpServletRequest request) {
		if(MessageConfigLogic.isLegal(request)){
			logger.info("Wechat 接口配置成功.");
			return request.getParameter(Constant.WechatParams.MSG_PARAM_ECHOSTR);
		}
		logger.info("Wechat 接口配置失败.");
		return null;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public final void process(HttpServletRequest request,HttpServletResponse response){
		//解析微信消息
		Map<String, Object> reqMap = MessageXmlLogic.parseXml(request);
		//若没有消息，则返回空消息给微信
		if(CheckUtils.isNull(reqMap)){
			logger.error("Wechat message is null");
			MessageProcessLogic.responseResult(response, Constant.EMPTY);
			return;
		}
		
		String msgType = (String)reqMap.get(Constant.WechatParams.MSGTYPE);
		String event = (String)reqMap.get(Constant.WechatParams.EVENT);
		String eventKey = (String)reqMap.get(Constant.WechatParams.EVENTKEY);
		
		logger.info("获取微信消息：MsgType is {}. Event is {}. EventKey is {}", msgType, event, eventKey);
		if (MessageConfigLogic.isLegal(request)) {
			//TODO 处理微信消息
			switch(msgType) {
				// 文本消息
				case WechatReqType.TEXT:MessageProcessLogic.responseResult(response, Constant.EMPTY);processTextRequest(reqMap);break;
				// 事件消息
				case WechatReqType.EVENT:MessageProcessLogic.responseResult(response, Constant.EMPTY);processEventRequest(reqMap);break;
				// 地理位置消息
				case WechatReqType.LOCATION:MessageProcessLogic.responseResult(response, Constant.EMPTY);break;
				// 图片消息
				case WechatReqType.IMAGE:MessageProcessLogic.responseResult(response, Constant.EMPTY);break;
				// 语音消息
				case WechatReqType.VOICE:MessageProcessLogic.responseResult(response, Constant.EMPTY);break;
				// 视频消息
				case WechatReqType.VIDEO:MessageProcessLogic.responseResult(response, Constant.EMPTY);break;
				// 小视频消息
				case WechatReqType.SHORT_VIDEO:MessageProcessLogic.responseResult(response, Constant.EMPTY);break;
				// 链接消息
				case WechatReqType.LINK:MessageProcessLogic.responseResult(response, Constant.EMPTY);break;
				// 其它
				default:MessageProcessLogic.responseResult(response, Constant.EMPTY);break;
			}
		}
		
	}
	
	/**
	 * 处理文件消息内容
	 * @param reqMap
	 * @return
	 */
	private void processTextRequest(Map<String, Object> reqMap){
		try {
			//获取文本消息内容
			String fromUserName = (String) reqMap.get(Constant.WechatParams.FROMUSERNAME);
			String toUserName = (String) reqMap.get(Constant.WechatParams.TOUSERNAME);
			String content = (String) reqMap.get(Constant.WechatParams.CONTENT);
			String msgId = (String) reqMap.get(Constant.WechatParams.MSGID);
			logger.debug("Get wechat text message:fromUserName is " + fromUserName + ". toUsername is " + toUserName + ". content is " + content + ". Msgid is " + msgId + ".");
		} catch (Exception e) {
			logger.error("Process text message error.");
			logger.error(e.getMessage());
		}
		
	}
	
	/**
	 * 处理微信事件消息
	 * 
	 * @param reqMap
	 */
	private void processEventRequest(Map<String, Object> reqMap) {
		String fromUserName = (String) reqMap.get(Constant.WechatParams.FROMUSERNAME);
		String toUserName = (String) reqMap.get(Constant.WechatParams.TOUSERNAME);
		//事件类型
		String event = (String)reqMap.get(Constant.WechatParams.EVENT);
		logger.debug("Wechat event is " + event + "." + "FromUserName is " + fromUserName + ".ToUserName is " + toUserName);
		// 关注事件
		if (EventType.SUBSCRIBE.equalsIgnoreCase(event)) {
			logger.info("处理关注消息");
			//String ticket = (String) reqMap.get(Constant.WechatParams.TICKET);
			String eventKey = (String) reqMap.get(Constant.WechatParams.EVENTKEY);
			if (!CheckUtils.isNullOrEmpty(eventKey) && eventKey.startsWith(Constant.WechatParams.QRSCENE+Constant.Str.SHARE_ONE)) {
				try {
				String recommendOpenid = eventKey.substring(Constant.Num.INT_EIGHTEEN, eventKey.length());
				WechatRecommend wechatRecommend = new WechatRecommend();
				wechatRecommend.setRecEdOpenid(fromUserName);
				wechatRecommend.setRecOpenid(recommendOpenid);
				createWechatRecommend(wechatRecommend);
				} catch(Exception e) {
					logger.error("处理关注事件异常："+e.getMessage());
				}
			}
		}
	}

}
