package com.xxx.wechat.common.lottery.api.response;

import java.io.Serializable;
import java.util.List;

/**
 * 大乐透期号返回数据
 * 
 * @author Administrator
 *
 */
public class LotteryDltHistoryTermResponse implements Serializable {

	private static final long serialVersionUID = -4368722664398971403L;

	private List<TremList> tremList;

	private String ctrem;

	public List<TremList> getTremList() {
		return tremList;
	}

	public void setTremList(List<TremList> tremList) {
		this.tremList = tremList;
	}

	public String getCtrem() {
		return ctrem;
	}

	public void setCtrem(String ctrem) {
		this.ctrem = ctrem;
	}

	public static class TremList {
		private String drawNews;
		private String term;

		public String getDrawNews() {
			return drawNews;
		}

		public void setDrawNews(String drawNews) {
			this.drawNews = drawNews;
		}

		public String getTerm() {
			return term;
		}

		public void setTerm(String term) {
			this.term = term;
		}

	}
}
