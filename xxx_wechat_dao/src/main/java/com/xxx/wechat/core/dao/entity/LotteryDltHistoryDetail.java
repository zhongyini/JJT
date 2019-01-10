package com.xxx.wechat.core.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 大乐透历史记录详情表
 * 
 * @author yk
 *
 */
@Table(name = "lottery_dlt_history_detail")
public class LotteryDltHistoryDetail implements Serializable {

	private static final long serialVersionUID = -7492233507028136847L;
	
	/**
	 * 主键自增
	 */
	@Id
	@Column(name = "id")
	private Integer id;
	
	/**
	 * 大乐透历史记录主键
	 */
	@Column(name = "lottery_term")
	private String lotteryTerm;

	/**
	 * 总金额
	 */
	@Column(name = "allmoney")
	private String allmoney;

	/**
	 * 排序
	 */
	@Column(name = "level")
	private String level;

	/**
	 * 金额
	 */
	@Column(name = "money")
	private String money;

	/**
	 * 
	 */
	@Column(name = "num")
	private Integer num;

	/**
	 * 
	 */
	@Column(name = "piece")
	private String piece;

	/**
	 * 赠送奖品
	 */
	@Column(name = "send_prize")
	private String sendPrize;


	/**
	 * 删除标志
	 */
	@Column(name = "delete_flag")
	private Integer deleteFlag;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getLotteryTerm() {
		return lotteryTerm;
	}


	public void setLotteryTerm(String lotteryTerm) {
		this.lotteryTerm = lotteryTerm;
	}


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


	public Integer getNum() {
		return num;
	}


	public void setNum(Integer num) {
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


	public Integer getDeleteFlag() {
		return deleteFlag;
	}


	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

}
