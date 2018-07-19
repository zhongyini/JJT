package com.xxx.wechat.common.constant;

public enum ConfigurationEnum {
	/**
     * 微信appid
     */
	APPID("appid"),
	/**
     * 微信appsecret
     */
	APPSECRET("appsecret"),
	/**
     * 域名
     */
	HOST_URL("hostUrl"),
	/**
     * 一页显示数
     */
	LIST_PAGE_SIZE("list_page_size"),
	/**
     * state
     */
	STATE("state"),
	/**
     * 推荐数上限
     */
	REC_NUM_LIMIT("rec_num_limit"),
	/**
     * 领取数上限
     */
	GET_NUM_LIMIT("get_num_limit"),
	/**
     * 红包返点数
     */
	MONEY("money"),
	/**
     * 微信配置Token
     */
	SERVER_CONFIG_TOKEN("server_config_token"),
	/**
	 * 微信个性化动态菜单标签id
	 */
	WECHAT_TAG_ID("wechat_tag_id"),
	/**
	 * cookie,存放openid
	 */
	OPENID_COOKIE("openid_cookie");
	
	String value;
	
	ConfigurationEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
