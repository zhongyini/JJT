package com.jjt.common.api.entity;

import com.jjt.common.utils.JSONUtil;

/**
 * 抽象实体类
 *
 */
public abstract class BaseModel implements Model {
    /**
	 * 
	 */
	private static final long serialVersionUID = 8031230506035063110L;

	@Override
    public String toJsonString() {
        return JSONUtil.toJson(this);
    }
}
