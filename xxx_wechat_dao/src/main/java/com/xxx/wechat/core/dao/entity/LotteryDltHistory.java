package com.xxx.wechat.core.dao.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 大乐透历史记录表
 * 
 * @author yk
 *
 */
@Table(name = "lottery_dlt_history")
public class LotteryDltHistory implements Serializable {

	private static final long serialVersionUID = 7842099787925999863L;

	/**
	 * 主键彩票号
	 */
	@Id
	@Column(name = "term")
	private Integer term;

	/**
	 * 另一种彩票号
	 */
	@Column(name = "draw_news")
	private String drawNews;

	/**
	 * 按照产生顺序显示
	 */
	@Column(name = "num_sequence")
	private Integer numSequence;

	/**
	 * 从红到蓝、从小到大排序
	 */
	@Column(name = "number")
	private Integer number;

	/**
	 * 一号红色球
	 */
	@Column(name = "red_one")
	private Integer redOne;

	/**
	 * 二号红色球
	 */
	@Column(name = "red_two")
	private Integer redTwo;

	/**
	 * 三号红色球
	 */
	@Column(name = "red_three")
	private Integer redThree;

	/**
	 * 四号红色球
	 */
	@Column(name = "red_four")
	private Integer redFour;

	/**
	 * 五号红色球
	 */
	@Column(name = "red_five")
	private Integer redFive;

	/**
	 * 一号蓝色球
	 */
	@Column(name = "blue_one")
	private Integer blueOne;

	/**
	 * 二号蓝色球
	 */
	@Column(name = "blue_two")
	private Integer blueTwo;

	/**
	 * 开奖日期
	 */
	@Column(name = "lottery_date")
	private Integer lotteryDate;

	/**
	 * 删除标志
	 */
	@Column(name = "delete_flag")
	private String deleteFlag;

	/**
	 * 创建者
	 */
	@Column(name = "update_user")
	private String updateUser;

	/**
	 * 创建时间
	 */
	@Column(name = "update_time")
	private Timestamp updateTime;

	public Integer getTerm() {
		return term;
	}

	public void setTerm(Integer term) {
		this.term = term;
	}

	public String getDrawNews() {
		return drawNews;
	}

	public void setDrawNews(String drawNews) {
		this.drawNews = drawNews;
	}

	public Integer getNumSequence() {
		return numSequence;
	}

	public void setNumSequence(Integer numSequence) {
		this.numSequence = numSequence;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getRedOne() {
		return redOne;
	}

	public void setRedOne(Integer redOne) {
		this.redOne = redOne;
	}

	public Integer getRedTwo() {
		return redTwo;
	}

	public void setRedTwo(Integer redTwo) {
		this.redTwo = redTwo;
	}

	public Integer getRedThree() {
		return redThree;
	}

	public void setRedThree(Integer redThree) {
		this.redThree = redThree;
	}

	public Integer getRedFour() {
		return redFour;
	}

	public void setRedFour(Integer redFour) {
		this.redFour = redFour;
	}

	public Integer getRedFive() {
		return redFive;
	}

	public void setRedFive(Integer redFive) {
		this.redFive = redFive;
	}

	public Integer getBlueOne() {
		return blueOne;
	}

	public void setBlueOne(Integer blueOne) {
		this.blueOne = blueOne;
	}

	public Integer getBlueTwo() {
		return blueTwo;
	}

	public void setBlueTwo(Integer blueTwo) {
		this.blueTwo = blueTwo;
	}

	public Integer getLotteryDate() {
		return lotteryDate;
	}

	public void setLotteryDate(Integer lotteryDate) {
		this.lotteryDate = lotteryDate;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

}
