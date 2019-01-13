package com.xxx.wechat.common.lottery.api.response;

import java.io.Serializable;
import java.util.List;

/**
 * 大乐透返回数据
 * 
 * @author Administrator
 *
 */
public class LotteryDltHistoryResponse implements Serializable {

	private static final long serialVersionUID = 1428311970775875985L;

	private Lottery lottery;
	private List<Details> details;
	private String[] codeNumber;
	private String[] eventName;

	public Lottery getLottery() {
		return lottery;
	}

	public void setLottery(Lottery lottery) {
		this.lottery = lottery;
	}

	public List<Details> getDetails() {
		return details;
	}

	public void setDetails(List<Details> details) {
		this.details = details;
	}

	public String[] getCodeNumber() {
		return codeNumber;
	}

	public void setCodeNumber(String[] codeNumber) {
		this.codeNumber = codeNumber;
	}

	public String[] getEventName() {
		return eventName;
	}

	public void setEventName(String[] eventName) {
		this.eventName = eventName;
	}

	public static class Lottery {
		private String drawNews;
		private String fTime;
		private String isAP;
		private String ispool;
		private String lType;
		private String numSequence;
		private String numSequence_pool;
		private String number;
		private String number_pool;
		private OpenTime openTime;
		private String openTime_fmt;
		private String openTime_fmt1;
		private String pool;
		private String sTime;
		private String status;
		private String term;
		private String totalSales;
		private String totalSales2;
		private String totlSaleNews;
		private String verify;

		public String getDrawNews() {
			return drawNews;
		}

		public void setDrawNews(String drawNews) {
			this.drawNews = drawNews;
		}

		public String getfTime() {
			return fTime;
		}

		public void setfTime(String fTime) {
			this.fTime = fTime;
		}

		public String getIsAP() {
			return isAP;
		}

		public void setIsAP(String isAP) {
			this.isAP = isAP;
		}

		public String getIspool() {
			return ispool;
		}

		public void setIspool(String ispool) {
			this.ispool = ispool;
		}

		public String getlType() {
			return lType;
		}

		public void setlType(String lType) {
			this.lType = lType;
		}

		public String getNumSequence() {
			return numSequence;
		}

		public void setNumSequence(String numSequence) {
			this.numSequence = numSequence;
		}

		public String getNumSequence_pool() {
			return numSequence_pool;
		}

		public void setNumSequence_pool(String numSequence_pool) {
			this.numSequence_pool = numSequence_pool;
		}

		public String getNumber() {
			return number;
		}

		public void setNumber(String number) {
			this.number = number;
		}

		public String getNumber_pool() {
			return number_pool;
		}

		public void setNumber_pool(String number_pool) {
			this.number_pool = number_pool;
		}

		public OpenTime getOpenTime() {
			return openTime;
		}

		public void setOpenTime(OpenTime openTime) {
			this.openTime = openTime;
		}

		public String getOpenTime_fmt() {
			return openTime_fmt;
		}

		public void setOpenTime_fmt(String openTime_fmt) {
			this.openTime_fmt = openTime_fmt;
		}

		public String getOpenTime_fmt1() {
			return openTime_fmt1;
		}

		public void setOpenTime_fmt1(String openTime_fmt1) {
			this.openTime_fmt1 = openTime_fmt1;
		}

		public String getPool() {
			return pool;
		}

		public void setPool(String pool) {
			this.pool = pool;
		}

		public String getsTime() {
			return sTime;
		}

		public void setsTime(String sTime) {
			this.sTime = sTime;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getTerm() {
			return term;
		}

		public void setTerm(String term) {
			this.term = term;
		}

		public String getTotalSales() {
			return totalSales;
		}

		public void setTotalSales(String totalSales) {
			this.totalSales = totalSales;
		}

		public String getTotalSales2() {
			return totalSales2;
		}

		public void setTotalSales2(String totalSales2) {
			this.totalSales2 = totalSales2;
		}

		public String getTotlSaleNews() {
			return totlSaleNews;
		}

		public void setTotlSaleNews(String totlSaleNews) {
			this.totlSaleNews = totlSaleNews;
		}

		public String getVerify() {
			return verify;
		}

		public void setVerify(String verify) {
			this.verify = verify;
		}

		public static class OpenTime {
			private String date;
			private String day;
			private String hours;
			private String minutes;
			private String month;
			private String nanos;
			private String seconds;
			private String time;
			private String timezoneOffset;
			private String year;

			public String getDate() {
				return date;
			}

			public void setDate(String date) {
				this.date = date;
			}

			public String getDay() {
				return day;
			}

			public void setDay(String day) {
				this.day = day;
			}

			public String getHours() {
				return hours;
			}

			public void setHours(String hours) {
				this.hours = hours;
			}

			public String getMinutes() {
				return minutes;
			}

			public void setMinutes(String minutes) {
				this.minutes = minutes;
			}

			public String getMonth() {
				return month;
			}

			public void setMonth(String month) {
				this.month = month;
			}

			public String getNanos() {
				return nanos;
			}

			public void setNanos(String nanos) {
				this.nanos = nanos;
			}

			public String getSeconds() {
				return seconds;
			}

			public void setSeconds(String seconds) {
				this.seconds = seconds;
			}

			public String getTime() {
				return time;
			}

			public void setTime(String time) {
				this.time = time;
			}

			public String getTimezoneOffset() {
				return timezoneOffset;
			}

			public void setTimezoneOffset(String timezoneOffset) {
				this.timezoneOffset = timezoneOffset;
			}

			public String getYear() {
				return year;
			}

			public void setYear(String year) {
				this.year = year;
			}
		}
	}

	public static class Details {
		private String allmoney;
		private String level;
		private String money;
		private String num;
		private String piece;
		private String sendPrize;

		public String getAllmoney() {
			return allmoney;
		}

		public void setAllmoney(String allmoney) {
			this.allmoney = allmoney;
		}

		public String getLevel() {
			return level;
		}

		public void setLevel(String level) {
			this.level = level;
		}

		public String getMoney() {
			return money;
		}

		public void setMoney(String money) {
			this.money = money;
		}

		public String getNum() {
			return num;
		}

		public void setNum(String num) {
			this.num = num;
		}

		public String getPiece() {
			return piece;
		}

		public void setPiece(String piece) {
			this.piece = piece;
		}

		public String getSendPrize() {
			return sendPrize;
		}

		public void setSendPrize(String sendPrize) {
			this.sendPrize = sendPrize;
		}
	}

}
