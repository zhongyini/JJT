package com.jjt.wechat.common.utils;

import static com.jjt.wechat.common.utils.CheckUtils.requireNonNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Json工具类
 *
 */
public class JsonUtils {
	/**
	 * 将String转为Object
	 * @param str
	 * @param clazz
	 * @return
	 */
	public static <T> T parseObject(String str, Class<T> clazz){
		return JSONObject.parseObject(str).toJavaObject(clazz);
	}
	
	/**
	 * 将Object转为String
	 * @param object
	 * @return
	 */
	public static String toJsonString(Object object){
		return JSONObject.toJSONString(object);
	}
	
    /**
     * 从json获取指定key的字符串
     *
     * @param json json字符串
     * @param key  字符串的key
     * @return 指定key的值
     */
    public static Object getStringFromJSONObject(final String json, final String key) {
        requireNonNull(json, "json is null");
        return JSON.parseObject(json).getString(key);
    }
}
