package com.jjt.common.api.response;

import java.util.List;

import com.jjt.common.api.entity.Tag;

/**
 */
public class GetAllTagsResponse extends BaseResponse {

    private List<Tag> tags;

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
