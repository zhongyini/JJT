package com.xxx.wechat.front.dto;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import java.io.IOException;

import static com.xxx.wechat.common.utils.HttpClientUtil.ResponseResult;

public class PaymentResultDto {

    /**
     * 错误代码
     */
    private String code;
    /**
     * 错误消息
     */
    private String message;

    /* ************************************************* pay result *************************************************/
    /**
     * 红包编号。此编号为唯一存在。发放通知结果也返回此编号
     */
    private String redpackSn;
    /**
     * 红包的链接，您可以用微信对话消息封装
     */
    private String redpackUrl;

    /* ************************************************* get result *************************************************/
    /**
     * 单位为分
     */
    private String money;
    /**
     * 您公众号的粉丝openid
     */
    private String openid;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 支付流水号
     */
    private String consumeSn;
    /**
     * 创建时间
     */
    private String createdTime;
    /**
     * 领取时间
     */
    private String usedTime;
    /**
     * 0:等待发放；1：已发放；2：等待退回；3：已退回；4：发放成功
     */
    private String status;

    // ext
    /**
     * 网络状态
     */
    private String netStatus;

    /**
     * json to bean
     */
    @SuppressWarnings("deprecation")
	public static PaymentResultDto jsonToBean(ResponseResult responseResult) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        PaymentResultDto paymentResultDto = objectMapper.readValue(responseResult.getResult(), PaymentResultDto.class);
        paymentResultDto.setNetStatus(responseResult.getResponseStatus().toString());
        return paymentResultDto;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRedpackSn() {
        return redpackSn;
    }

    public void setRedpackSn(String redpackSn) {
        this.redpackSn = redpackSn;
    }

    public String getRedpackUrl() {
        return redpackUrl;
    }

    public void setRedpackUrl(String redpackUrl) {
        this.redpackUrl = redpackUrl;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getConsumeSn() {
        return consumeSn;
    }

    public void setConsumeSn(String consumeSn) {
        this.consumeSn = consumeSn;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(String usedTime) {
        this.usedTime = usedTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNetStatus() {
        return netStatus;
    }

    public void setNetStatus(String netStatus) {
        this.netStatus = netStatus;
    }

}
