package com.xxx.wechat.common.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密工具
 *
 */
public class EncryptUtils {

	/**
	 * 用MD5算法进行加密
	 * 
	 * @param str
	 *            需要加密的字符串
	 * @return MD5加密后的结果
	 */
	public static String encodeMD5(String str) {
		return encode(str, "MD5");
	}

	/**
	 * 用SHA算法进行加密
	 * 
	 * @param str
	 *            需要加密的字符串
	 * @return SHA加密后的结果
	 */
	public static String encodeSHAS(String str) {
		return encode(str, "SHA");
	}

	/**
	 * MD5加密时会缺0
	 * @param str
	 * @param method
	 * @return
	 */
	private static String encode(String str, String method) {
		MessageDigest md = null;
		String dstr = null;
		try {
			md = MessageDigest.getInstance(method);

			md.update(str.getBytes("UTF-8"));
			// md.update(str.getBytes());
			dstr = new BigInteger(1, md.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dstr;
	}
	

	/**
	 * 加密时不会缺0
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
