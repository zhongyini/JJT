package com.jjt.wechat.common.utils;

import java.security.MessageDigest;

/**
 * 加密工具
 *
 */
public class EncryptUtils {

	/**
	 * 加MD5加密算法
	 * @param source
	 * @return
	 */
	public static String getMD5(String source) {
		String s = null;
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(source.getBytes("UTF-8"));
			byte tmp[] = md5.digest();
			char str[] = new char[16 * 2];
			int k = 0;
			for (int i = 0; i < 16; i++) {
				byte byte0 = tmp[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			s = new String(str);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return s;
	}
}
