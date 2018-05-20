package com.jjt.common.api.response;

import com.alibaba.fastjson.annotation.JSONField;

/**
 *  
 */
public class UploadImageMaterialResponse extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JSONField(name = "media_id")
	private String mediaId;
	
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
}
