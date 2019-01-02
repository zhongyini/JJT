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


}
