package com.xxx.wechat.core.dao.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 */
@Table(name = "xxx_wechat_pay_api_log")
public class WechatPayApiLog implements Serializable {

	private static final long serialVersionUID = 5647736262790191398L;

	@Id
    @Column(name = "pay_api_id")
    private Long payApiId;

    @Column(name = "pay_id")
    private Long payId;

    @Column(name = "trade_no")
    private String tradeNo;

    @Column(name = "api_url")
    private String apiUrl;

    @Column(name = "parameters")
    private String parameters;

    @Column(name = "return_code")
    private Integer returnCode;

    @Column(name = "return_json")
    private String returnJson;

    @Column(name = "elapsed")
    private Long elapsed;

    @Column(name = "create_datetime")
    private Date createDatetime;

    public Long getPayApiId() {
        return payApiId;
    }

    public void setPayApiId(Long payApiId) {
        this.payApiId = payApiId;
    }

    public Long getPayId() {
        return payId;
    }

    public void setPayId(Long payId) {
        this.payId = payId;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public Integer getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(Integer returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnJson() {
        return returnJson;
    }

    public void setReturnJson(String returnJson) {
        this.returnJson = returnJson;
    }

    public Long getElapsed() {
        return elapsed;
    }

    public void setElapsed(Long elapsed) {
        this.elapsed = elapsed;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

}
