package com.jjt.common.api.response;

import com.jjt.common.api.entity.Tag;

/**
 */
public class CreateTagResponse extends BaseResponse {

    private Tag tag;

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
