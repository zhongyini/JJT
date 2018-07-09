package com.jjt.wechat.front.controller.logic;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.jjt.wechat.common.constant.Constant;
import com.jjt.wechat.common.utils.CheckUtils;
import com.jjt.wechat.core.config.SystemConfig;

/**
 * 微信配置逻辑层
 *
 */
@Component
public class MessageConfigLogic extends BaseLogic {

	private static final char[] digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
			'F' };
	private static Map<String, Integer> messages = new HashMap<String, Integer>();
	private final String SHA_1 = "SHA-1";
	private final String UTF8 = "UTF-8";

	public boolean isLegal(HttpServletRequest request) {
		String signature = request.getParameter(Constant.WechatParams.MSG_PARAM_SIGNATURE);
		String timestamp = request.getParameter(Constant.WechatParams.MSG_PARAM_TIMESTAMP);
		String nonce = request.getParameter(Constant.WechatParams.MSG_PARAM_NONCE);
		String echostr = request.getParameter(Constant.WechatParams.MSG_PARAM_ECHOSTR);

		logger.info("\n接收到来自微信服务器的认证消息：[{}, {}, {}, {}]", signature, timestamp, nonce, echostr);
		String token = SystemConfig.getInstance().getProperty(Constant.WechatParams.SERVER_CONFIG_TOKEN);
		boolean ret = checkSignature(token, signature, timestamp, nonce);

		if (ret) {
			ret = isNotContains(signature);
		}
		return ret;
	}

	public boolean isNotContains(String key) {
		if (messages.containsKey(key)) {
			int times = messages.get(key);
			if (times > 2) {
				messages.remove(key);
				return false;
			}
			messages.remove(key);
			messages.put(key, times + 1);
			return false;
		} else {
			messages.put(key, 1);
		}
		return true;
	}

	private boolean checkSignature(String token, String signature, String timestamp, String nonce) {
		if (CheckUtils.hasNull(token, signature, timestamp, nonce)) {
			return false;
		}
		String[] arr = new String[] { token, timestamp, nonce };
		Arrays.sort(arr);
		StringBuilder content = new StringBuilder();
		for (String anArr : arr) {
			content.append(anArr);
		}
		MessageDigest md;
		String tmpStr = null;

		try {
			md = MessageDigest.getInstance(SHA_1);
			byte[] digest = md.digest(content.toString().getBytes(UTF8));
			tmpStr = byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			logger.error("加密方式异常", e);
		} catch (UnsupportedEncodingException e) {
			logger.error("编码格式不支持", e);
		}
		return tmpStr != null && tmpStr.equalsIgnoreCase(signature);
	}

	/**
	 * 将byte数组变为16进制对应的字符串
	 *
	 * @param byteArray
	 *            byte数组
	 * @return 转换结果
	 */
	private String byteToStr(byte[] byteArray) {
		int len = byteArray.length;
		StringBuilder strDigest = new StringBuilder(len * 2);
		for (byte aByteArray : byteArray) {
			strDigest.append(byteToHexStr(aByteArray));
		}
		return strDigest.toString();
	}

	private static String byteToHexStr(byte mByte) {
		char[] tempArr = new char[2];
		tempArr[0] = digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = digit[mByte & 0X0F];
		return new String(tempArr);
	}

}
