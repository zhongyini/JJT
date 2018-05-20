package com.jjt.common.api.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 图文群发每日数据
 *
 */
public class ArticleSummary extends BaseDataCube {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String  msgid;//这里的msgid实际上是由msgid（图文消息id，这也就是群发接口调用后返回的msg_data_id）和index（消息次序索引）组成， 例如12003_3， 其中12003是msgid，即一次群发的消息的id； 3为index，假设该次群发的图文消息共5个文章（因为可能为多图文），3表示5个中的第3个
    private String  title;//图文消息的标题
    @JSONField(name = "int_page_read_user")
    private Integer intPageReadUser;//图文页（点击群发图文卡片进入的页面）的阅读人数
    @JSONField(name = "int_page_read_count")
    private Integer intPageReadCount;//图文页的阅读次数
    @JSONField(name = "ori_page_read_user")
    private Integer oriPageReadUser;//原文页（点击图文页“阅读原文”进入的页面）的阅读人数，无原文页时此处数据为0
    @JSONField(name = "ori_page_read_count")
    private Integer oriPageReadCount;//原文页的阅读次数
    @JSONField(name = "share_user")
    private Integer shareUser;//分享的人数
    @JSONField(name = "shareCount")
    private Integer share_count;//分享的次数
    @JSONField(name = "add_to_fav_user")
    private Integer addToFavUser;//收藏的人数
    @JSONField(name = "add_to_fav_count")
    private Integer addToFavCount;//收藏的次数

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getIntPageReadUser() {
        return intPageReadUser;
    }

    public void setIntPageReadUser(Integer intPageReadUser) {
        this.intPageReadUser = intPageReadUser;
    }

    public Integer getIntPageReadCount() {
        return intPageReadCount;
    }

    public void setIntPageReadCount(Integer intPageReadCount) {
        this.intPageReadCount = intPageReadCount;
    }

    public Integer getOriPageReadUser() {
        return oriPageReadUser;
    }

    public void setOriPageReadUser(Integer oriPageReadUser) {
        this.oriPageReadUser = oriPageReadUser;
    }

    public Integer getOriPageReadCount() {
        return oriPageReadCount;
    }

    public void setOriPageReadCount(Integer oriPageReadCount) {
        this.oriPageReadCount = oriPageReadCount;
    }

    public Integer getShareUser() {
        return shareUser;
    }

    public void setShareUser(Integer shareUser) {
        this.shareUser = shareUser;
    }

    public Integer getShare_count() {
        return share_count;
    }

    public void setShare_count(Integer share_count) {
        this.share_count = share_count;
    }

    public Integer getAddToFavUser() {
        return addToFavUser;
    }

    public void setAddToFavUser(Integer addToFavUser) {
        this.addToFavUser = addToFavUser;
    }

    public Integer getAddToFavCount() {
        return addToFavCount;
    }

    public void setAddToFavCount(Integer addToFavCount) {
        this.addToFavCount = addToFavCount;
    }
}
