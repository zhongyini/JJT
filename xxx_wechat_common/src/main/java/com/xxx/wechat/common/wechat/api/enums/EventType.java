package com.xxx.wechat.common.wechat.api.enums;

public final class EventType {

	// 关注
    public static final String SUBSCRIBE       = "subscribe";
    // 取消关注
    public static final String UNSUBSCRIBE     = "unsubscribe";
    // 自定义菜单点击
    public static final String CLICK           = "CLICK";
    // 自定义菜单跳转
    public static final String VIEW            = "VIEW";
    // 上报地理位置
    public static final String LOCATION        = "LOCATION";
    // 已关注
    public static final String SCAN            = "SCAN";
    public static final String SCANCODEPUSH    = "scancode_push";
    public static final String SCANCODEWAITMSG = "scancode_waitmsg";
    public static final String PICSYSPHOTO     = "pic_sysphoto";
    public static final String PICPHOTOORALBUM = "pic_photo_or_album";
    public static final String PICWEIXIN       = "pic_weixin";
    public static final String LOCATIONSELECT  = "location_select";
    // 模板消息发送成功
    public static final String TEMPLATESENDJOBFINISH  = "TEMPLATESENDJOBFINISH";
    // 群发结果
    public static final String MASSSENDJOBFINISH="MASSSENDJOBFINISH";
    
    public static final String KFCLOSE = "kf_close_session";
    
    public static final String KFCREATE = "kf_create_session";

    private EventType() {
    }

}
