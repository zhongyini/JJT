package com.jjt.common.api;

import java.util.UUID;

import com.jjt.common.api.response.GetSignatureResponse;
import com.jjt.common.utils.BeanUtils;

/**
 * 微信js-sdk相关API
 */
public class JsAPI extends BaseAPI {

//    public JsAPI(ApiConfig config) {
//        super(config);
//    }

    public JsAPI(String accessToken){
    	super(accessToken);
    }
    public JsAPI(String accessToken,String jsApiTicket){
    	super(accessToken,jsApiTicket);
    }

    /**
     * 获取js-sdk所需的签名，简化逻辑
     * 不太在意随机数和时间戳的场景，使用更加方便
     * @param url 当前网页的URL，不包含#及其后面部分
     * @return 签名以及相关参数
     */
    public GetSignatureResponse getSignature(String url) {
        BeanUtils.requireNonNull(url, "请传入当前网页的URL，不包含#及其后面部分");
        //当前时间的秒数
        long timestame = System.currentTimeMillis() / 1000;
        //使用UUID来当随机字符串
        String nonceStr = UUID.randomUUID().toString().replaceAll("-", "");
        return getSignature(nonceStr, timestame, url);
    }

    /**
     * 获取js-sdk所需的签名，给调用者最大的自由度，控制生成签名的参数
     * @param nonceStr 随机字符串
     * @param timestame 时间戳
     * @param url 当前网页的URL，不包含#及其后面部分
     * @return 签名以及相关参数
     */
    public GetSignatureResponse getSignature(String nonceStr, long timestame, String url) {
        BeanUtils.requireNonNull(url, "请传入当前网页的URL，不包含#及其后面部分");
        GetSignatureResponse response = new GetSignatureResponse();
//        String jsApiTicket = this.config.getJsApiTicket();
//        String sign;
//        try {
//            sign = JsApiUtil.sign(jsApiTicket, nonceStr, timestame, url);
//        } catch (Exception e) {
//            LOG.error("获取签名异常:", e);
//            response.setErrcode(ResultType.OTHER_ERROR.getCode().toString());
//            response.setErrmsg("获取签名异常");
//            return response;
//        }
//        response.setNoncestr(nonceStr);
//        response.setSignature(sign);
//        response.setTimestamp(timestame);
//        response.setUrl(url);
//        response.setErrcode(ResultType.SUCCESS.getCode().toString());
        return response;
    }
}
