package com.xxx.wechat.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xxx.wechat.common.constant.Constant;
import com.xxx.wechat.common.ecxception.WeixinException;
import com.xxx.wechat.common.wechat.api.entity.WxCardApiSignature;

/**
 * 绑定服务器工具类
 *
 * @author
 */
public final class SignUtil {

	private static final Logger logger = LoggerFactory.getLogger(SignUtil.class);
	private static final char[] digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
			'F' };

	/**
	 * 此类不需要实例化
	 */
	private SignUtil() {
	}

	/**
	 * 认证微信，可以参见微信开发者文档
	 *
	 * @param token
	 *            我们自己设定的token值
	 * @param signature
	 *            微信传来的变量
	 * @param timestamp
	 *            微信传来的变量
	 * @param nonce
	 *            微信传来的变量
	 * @return 是否合法
	 */
	public static boolean checkSignature(String token, String signature, String timestamp, String nonce) {
		if (StrUtil.hasBlank(token, signature, timestamp, nonce)) {
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
			md = MessageDigest.getInstance("SHA-1");
			byte[] digest = md.digest(content.toString().getBytes("UTF-8"));
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
	private static String byteToStr(byte[] byteArray) {
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

	public static Map<String, String> jsApiTicketSign(String jsapi_ticket) {
		return jsApiTicketSign(jsapi_ticket, Constant.EMPTY);
	}

	public static Map<String, String> jsApiTicketSign(String jsapi_ticket, String url) {
		Map<String, String> ret = new HashMap<String, String>();
		String nonce_str = create_nonce_str();
		String timestamp = create_timestamp();
		String string1;
		String signature = Constant.EMPTY;
		// 注意这里参数名必须全部小写，且必须有序
		string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url=" + url;
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
			logger.info(signature);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			logger.error("jsApiTicketSign 配置失败："+e.getMessage());
		}
		ret.put("url", url);
		ret.put("jsapi_ticket", jsapi_ticket);
		ret.put("nonceStr", nonce_str);
		ret.put("timestamp", timestamp);
		ret.put("signature", signature);

		return ret;
	}

	/**
	 * 创建微信卡券JSAPI签名
	 * 
	 * @param apiSignature
	 * @return
	 */
	public static WxCardApiSignature createWxCardJsApiSignature(String apiTicket,
			WxCardApiSignature apiSignature) {
		String timestamp = create_timestamp();
		String nonce_str = create_nonce_str();
		String str = Constant.EMPTY;
		String signature = Constant.EMPTY;
		List<String> parameters = new ArrayList<>();
		if (apiSignature.isChooseCard()) {
			// parameters.add(wxClient.getClientId());
		}

		parameters.add(apiTicket);
		parameters.add(apiSignature.getCardId());
		parameters.add(nonce_str);
		parameters.add(timestamp);

		if (!CheckUtils.isNullOrEmpty(apiSignature.getCardType())) {
			parameters.add(apiSignature.getCardType());
		}
		if (!CheckUtils.isNullOrEmpty(apiSignature.getCode())) {
			parameters.add(apiSignature.getCode());
		}
		if (!CheckUtils.isNullOrEmpty(apiSignature.getBalance())) {
			parameters.add(apiSignature.getBalance());
		}
		if (!CheckUtils.isNullOrEmpty(apiSignature.getOpenId())) {
			parameters.add(apiSignature.getOpenId());
		}
		if (!CheckUtils.isNullOrEmpty(apiSignature.getLocationId())) {
			parameters.add(apiSignature.getLocationId());
		}
		Collections.sort(parameters);
		for (int i = 0; i < parameters.size(); i++) {
			str += parameters.get(i);
		}
		try {
			MessageDigest md = null;
			try {
				md = MessageDigest.getInstance("SHA-1");
				md.reset();
				md.update(str.getBytes());
				signature = byteToHex(md.digest());
			} catch (Exception e) {
				e.printStackTrace();
			}
			apiSignature.setNonce(nonce_str);
			apiSignature.setTimestamp(timestamp);
			apiSignature.setSignature(signature);
			//logger.info(apiSignature.getSignature());
			return apiSignature;
		} catch (Exception e) {
			logger.error("createWxCardJsAPISignature failed", e);
			throw new WeixinException(e.getMessage());
		}
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	private static String create_nonce_str() {
		return UUID.randomUUID().toString();
	}

	private static String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}
}
