package com.xxx.wechat.core.dao.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 支付
 */
@Table(name = "xxx_wechat_payment")
public class WechatPayment implements Serializable {

    @Id
    @Column(name = "pay_id")
    private Long payId;

    @Column(name = "openid")
    private String openid;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "trade_no")
    private String tradeNo;

    @Column(name = "redpack_url")
    private String redpackUrl;

    @Column(name = "status")
    private String status;

    @Column(name = "create_datetime")
    private Date createDatetime;

    @Column(name = "update_datetime")
    private Date updateDatetime;

    // ext
    /**
     * 0未超过24小时 1超过
     */
    private Integer timeout;

    public Long getPayId() {
        return payId;
    }

    public void setPayId(Long payId) {
        this.payId = payId;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getRedpackUrl() {
        return redpackUrl;
    }

    public void setRedpackUrl(String redpackUrl) {
        this.redpackUrl = redpackUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

}
