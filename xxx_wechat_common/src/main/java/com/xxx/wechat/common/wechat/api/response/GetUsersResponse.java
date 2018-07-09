package com.xxx.wechat.common.wechat.api.response;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class GetUsersResponse extends BaseResponse {

	private static final long serialVersionUID = 3551370984958430118L;
	
	private long   total;
    private int    count;
    private Openid data;
    @JSONField(name = "next_openid")
    private String nextOpenid;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Openid getData() {
        return data;
    }

    public void setData(Openid data) {
        this.data = data;
    }

    public String getNextOpenid() {
        return nextOpenid;
    }

    public void setNextOpenid(String nextOpenid) {
        this.nextOpenid = nextOpenid;
    }

    public class Openid implements Serializable {
    	
		private static final long serialVersionUID = -2931139153015391303L;
		
		private String[] openid;

        public String[] getOpenid() {
            return openid;
        }

        public void setOpenid(String[] openid) {
            this.openid = openid;
        }
    }
}
