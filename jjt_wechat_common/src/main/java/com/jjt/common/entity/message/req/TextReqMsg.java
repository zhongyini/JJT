package com.jjt.common.entity.message.req;

import com.jjt.common.entity.wechatenum.ReqType;

public final class TextReqMsg extends BaseReqMsg {

    private String content;

    public TextReqMsg(String content) {
        super();
        this.content = content;
        setMsgType(ReqType.TEXT);
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "TextReqMsg [content=" + content + ", toUserName=" + toUserName
                + ", fromUserName=" + fromUserName + ", createTime="
                + createTime + ", msgType=" + msgType + ", msgId=" + msgId
                + "]";
    }

}