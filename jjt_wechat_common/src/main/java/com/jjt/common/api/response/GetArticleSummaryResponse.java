package com.jjt.common.api.response;

import java.util.List;

import com.jjt.common.api.entity.ArticleSummary;

/**
 */
public class GetArticleSummaryResponse extends BaseResponse {

    private List<ArticleSummary> list;

    public List<ArticleSummary> getList() {
        return list;
    }

    public void setList(List<ArticleSummary> list) {
        this.list = list;
    }
}
