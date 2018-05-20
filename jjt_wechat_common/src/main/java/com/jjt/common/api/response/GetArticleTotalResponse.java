package com.jjt.common.api.response;

import java.util.List;

import com.jjt.common.api.entity.ArticleTotal;

/**
 */
public class GetArticleTotalResponse extends BaseResponse {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3595446677052418904L;
	private List<ArticleTotal> list;

    public List<ArticleTotal> getList() {
        return list;
    }

    public void setList(List<ArticleTotal> list) {
        this.list = list;
    }
}
