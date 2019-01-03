package com.xxx.wechat.common.baidu.api;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xxx.wechat.common.utils.BeanUtils;
import com.xxx.wechat.common.utils.CheckUtils;
import com.xxx.wechat.common.utils.CollectionUtil;
import com.xxx.wechat.common.utils.NetWorkCenter;
import com.xxx.wechat.common.wechat.api.enums.ResultType;
import com.xxx.wechat.common.wechat.api.response.BaseResponse;

public class BaseApi {
	
	// 获取百度AIP开放平台Access Token
	protected static final String BASE_API_URL = "https://aip.baidubce.com/oauth/2.0/token?grant_type=%s&client_id=%s&client_secret=%s";
	protected static final String GRANT_TYPE = "client_credentials";
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	protected String accessToken;
	protected String jsApiTicket;

	protected BaseApi() {
	}
	
	protected BaseApi(String accessToken) {
		this.accessToken = accessToken;
	}

	protected BaseApi(String accessToken, String jsApiTicket) {
		this.accessToken = accessToken;
		this.jsApiTicket = jsApiTicket;
	}

	/**
	 * 通用post请求
	 *
	 * @param url
	 *            地址，其中token用#代替
	 * @param json
	 *            参数，json格式
	 * @return 请求结果
	 */
	protected BaseResponse executePost(String url, String json) {
		return executePost(url, json, null);
	}

	/**
	 * 通用post请求
	 *
	 * @param url
	 *            地址，其中token用#代替
	 * @param json
	 *            参数，json格式
	 * @param file
	 *            上传的文件
	 * @return 请求结果
	 */
	protected BaseResponse executePost(String url, String json, File file) {
		BaseResponse response;
		BeanUtils.requireNonNull(url, "url is null");
		List<File> files = null;
		if (null != file) {
			files = CollectionUtil.newArrayList(file);
		}
		// 需要传token
		String postUrl = url.replace("#", this.accessToken);
		response = NetWorkCenter.post(postUrl, json, files);
		return response;
	}

	/**
	 * 通用get请求
	 *
	 * @param url
	 *            地址，其中token用#代替
	 * @return 请求结果
	 */
	protected BaseResponse executeGet(String url) {
		BaseResponse response;
		BeanUtils.requireNonNull(url, "url is null");
		// 需要传token
		String getUrl = url.replace("#", this.accessToken);
		response = NetWorkCenter.get(getUrl);
		return response;
	}

	/**
	 * 判断本次请求是否成功
	 *
	 * @param errCode
	 *            错误码
	 * @return 是否成功
	 */
	protected boolean isSuccess(String errCode) {
		if (CheckUtils.isNullOrEmpty(errCode)) {
			return true;
		}
		return ResultType.SUCCESS.getCode().toString().equals(errCode);
	}

	protected void logInfoParam(String method, String param) {
		logger.info("Method is " + method + " , Param is " + param);
	}

	protected void logInfoResult(String method, String result) {
		logger.info("Method is " + method + " , result is " + result);
	}
}
