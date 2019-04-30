package com.xxx.wechat.core.dao.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 大乐透预测表
 * 
 * @author yk
 *
 */
@Table(name = "lottery_dlt_guess")
public class LotteryDltGuess implements Serializable {

	private static final long serialVersionUID = -6400751621352112908L;

	/**
	 * 主键id
	 */
	@Id
	@Column(name = "id")
	private Integer id;

	/**
	 * 大乐透历史纪录主键期号
	 */
	@Column(name = "term")
	private Integer term;

	/**
	 * 按照产生顺序显示
	 */
	@Column(name = "num_sequence")
	private String numSequence;

	/**
	 * 从红到蓝、从小到大排序
	 */
	@Column(name = "number")
	private String number;

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
	 * 几等奖=>"0":未中奖;"1":一等奖;"2":二等奖;"3":三等奖
	 */
	@Column(name = "several_award")
	private Integer severalAward;

	/**
	 * 状态=>"0":未过期,"1":已过期
	 */
	@Column(name = "status")
	private Integer status;

	/**
	 * 删除标志=>"0":未删除,"1":已删除
	 */
	@Column(name = "delete_flag")
	private Integer deleteFlag;

	/**
	 * 修改者
	 */
	@Column(name = "update_user")
	private String updateUser;

	/**
	 * 修改时间
	 */
	@Column(name = "update_time")
	private Timestamp updateTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getTerm() {
		return term;
	}

	public void setTerm(Integer term) {
		this.term = term;
	}

	public String getNumSequence() {
		return numSequence;
	}

	public void setNumSequence(String numSequence) {
		this.numSequence = numSequence;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
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

	public Integer getSeveralAward() {
		return severalAward;
	}

	public void setSeveralAward(Integer severalAward) {
		this.severalAward = severalAward;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
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
