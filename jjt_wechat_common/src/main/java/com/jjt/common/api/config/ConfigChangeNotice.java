package com.jjt.common.api.config;


import java.util.Date;

import com.jjt.common.api.entity.BaseModel;

/**
 * 配置变化通知
 *
 */
public final class ConfigChangeNotice extends BaseModel {

	private static final long serialVersionUID = -1655636646090835710L;

	private Date noticeTime;

    private String appid;

    private ChangeType type;

    private String value;

    public ConfigChangeNotice() {
        this.noticeTime = new Date();
    }

    public ConfigChangeNotice(String appid, ChangeType type, String value) {
        this();
        this.appid = appid;
        this.type = type;
        this.value = value;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public ChangeType getType() {
        return type;
    }

    public void setType(ChangeType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTime(Date noticeTime) {
        this.noticeTime = noticeTime;
    }

    @Override
    public String toString() {
        return "ConfigChangeNotice{" +
                "noticeTime=" + noticeTime +
                ", appid='" + appid + '\'' +
                ", type=" + type +
                ", value='" + value + '\'' +
                '}';
    }
}
