package com.jjt.common.api;

import java.io.File;
import java.util.List;

import com.jjt.common.api.enums.ResultType;
import com.jjt.common.api.response.BaseResponse;
import com.jjt.common.utils.BeanUtils;
import com.jjt.common.utils.CollectionUtil;
import com.jjt.common.utils.NetWorkCenter;

/**
 * API基类，提供一些通用方法
 * 包含自动刷新token、通用get post请求等
 *
 * @author peiyu
 * @since 1.2
 */
public abstract class BaseAPI {

    protected static final String BASE_API_URL = "https://api.weixin.qq.com/";

//    protected final ApiConfig config;
    protected String accessToken;
    protected String jsApiTicket;

//    /**
//     * 构造方法，设置apiConfig
//     *
//     * @param config 微信API配置对象
//     */
//    protected BaseAPI(ApiConfig config) {
//        this.config = config;
//    }
     protected BaseAPI(String accessToken){
    	 this.accessToken = accessToken;
     }
     protected BaseAPI(String accessToken,String jsApiTicket){
    	 this.accessToken = accessToken;
    	 this.jsApiTicket = jsApiTicket;
     }


    /**
     * 通用post请求
     *
     * @param url  地址，其中token用#代替
     * @param json 参数，json格式
     * @return 请求结果
     */
    protected BaseResponse executePost(String url, String json) {
        return executePost(url, json, null);
    }

    /**
     * 通用post请求
     *
     * @param url  地址，其中token用#代替
     * @param json 参数，json格式
     * @param file 上传的文件
     * @return 请求结果
     */
    protected BaseResponse executePost(String url, String json, File file) {
        BaseResponse response;
        BeanUtils.requireNonNull(url, "url is null");
        List<File> files = null;
        if (null != file) {
            files = CollectionUtil.newArrayList(file);
        }
        //需要传token
        String postUrl = url.replace("#", this.accessToken);
        response = NetWorkCenter.post(postUrl, json, files);
        return response;
    }


    /**
     * 通用get请求
     *
     * @param url 地址，其中token用#代替
     * @return 请求结果
     */
    protected BaseResponse executeGet(String url) {
        BaseResponse response;
        BeanUtils.requireNonNull(url, "url is null");
        //需要传token
        String getUrl = url.replace("#", this.accessToken);
        response = NetWorkCenter.get(getUrl);
        return response;
    }

    /**
     * 判断本次请求是否成功
     *
     * @param errCode 错误码
     * @return 是否成功
     */
    protected boolean isSuccess(String errCode) {
        return ResultType.SUCCESS.getCode().toString().equals(errCode);
    }
}
