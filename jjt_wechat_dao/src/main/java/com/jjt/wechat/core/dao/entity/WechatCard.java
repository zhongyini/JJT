package com.jjt.wechat.core.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 微信卡券表.
 * 
 */
@Table(name = "jjt_wechat_card")
public class WechatCard implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** card_id. */
	@Id
	@Column(name = "card_id")
	private String cardId;

	/** card_type. */
	@Column(name = "card_type")
	private String cardType;

	/** least_cost. */
	@Column(name = "least_cost")
	private Integer leastCost;

	/** reduce_costreduce_cost. */
	@Column(name = "reduce_cost")
	private Integer reduceCost;

	/** deal_detail. */
	@Column(name = "deal_detail")
	private String dealDetail;

	/** logo_url. */
	@Column(name = "logo_url")
	private String logoUrl;

	/** code_type. */
	@Column(name = "code_type")
	private String codeType;

	/** brand_name. */
	@Column(name = "brand_name")
	private String brandName;

	/** title. */
	@Column(name = "title")
	private String title;

	/** color. */
	@Column(name = "color")
	private String color;

	/** notice. */
	@Column(name = "notice")
	private String notice;

	/** description. */
	@Column(name = "description")
	private String description;

	/** quantity. */
	@Column(name = "quantity")
	private Integer quantity;

	/** type. */
	@Column(name = "type")
	private String type;

	/** begin_timestamp. */
	@Column(name = "begin_timestamp")
	private String beginTimestamp;

	/** end_timestamp. */
	@Column(name = "end_timestamp")
	private String endTimestamp;

	/** fixed_term. */
	@Column(name = "fixed_term")
	private Integer fixedTerm;

	/** fixed_begin_term. */
	@Column(name = "fixed_begin_term")
	private Integer fixedBeginTerm;

	/**
	 * Constructor.
	 */
	public WechatCard() {
	}

	/**
	 * Set the card_id.
	 * 
	 * @param cardId
	 *            card_id
	 */
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	/**
	 * Get the card_id.
	 * 
	 * @return card_id
	 */
	public String getCardId() {
		return this.cardId;
	}

	/**
	 * Set the card_type.
	 * 
	 * @param cardType
	 *            card_type
	 */
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	/**
	 * Get the card_type.
	 * 
	 * @return card_type
	 */
	public String getCardType() {
		return this.cardType;
	}

	/**
	 * Set the least_cost.
	 * 
	 * @param leastCost
	 *            least_cost
	 */
	public void setLeastCost(Integer leastCost) {
		this.leastCost = leastCost;
	}

	/**
	 * Get the least_cost.
	 * 
	 * @return least_cost
	 */
	public Integer getLeastCost() {
		return this.leastCost;
	}

	/**
	 * Set the reduce_costreduce_cost.
	 * 
	 * @param reduceCost
	 *            reduce_costreduce_cost
	 */
	public void setReduceCost(Integer reduceCost) {
		this.reduceCost = reduceCost;
	}

	/**
	 * Get the reduce_costreduce_cost.
	 * 
	 * @return reduce_costreduce_cost
	 */
	public Integer getReduceCost() {
		return this.reduceCost;
	}

	/**
	 * Set the deal_detail.
	 * 
	 * @param dealDetail
	 *            deal_detail
	 */
	public void setDealDetail(String dealDetail) {
		this.dealDetail = dealDetail;
	}

	/**
	 * Get the deal_detail.
	 * 
	 * @return deal_detail
	 */
	public String getDealDetail() {
		return this.dealDetail;
	}

	/**
	 * Set the logo_url.
	 * 
	 * @param logoUrl
	 *            logo_url
	 */
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	/**
	 * Get the logo_url.
	 * 
	 * @return logo_url
	 */
	public String getLogoUrl() {
		return this.logoUrl;
	}

	/**
	 * Set the code_type.
	 * 
	 * @param codeType
	 *            code_type
	 */
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	/**
	 * Get the code_type.
	 * 
	 * @return code_type
	 */
	public String getCodeType() {
		return this.codeType;
	}

	/**
	 * Set the brand_name.
	 * 
	 * @param brandName
	 *            brand_name
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	/**
	 * Get the brand_name.
	 * 
	 * @return brand_name
	 */
	public String getBrandName() {
		return this.brandName;
	}

	/**
	 * Set the title.
	 * 
	 * @param title
	 *            title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Get the title.
	 * 
	 * @return title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Set the color.
	 * 
	 * @param color
	 *            color
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * Get the color.
	 * 
	 * @return color
	 */
	public String getColor() {
		return this.color;
	}

	/**
	 * Set the notice.
	 * 
	 * @param notice
	 *            notice
	 */
	public void setNotice(String notice) {
		this.notice = notice;
	}

	/**
	 * Get the notice.
	 * 
	 * @return notice
	 */
	public String getNotice() {
		return this.notice;
	}

	/**
	 * Set the description.
	 * 
	 * @param description
	 *            description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Get the description.
	 * 
	 * @return description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Set the quantity.
	 * 
	 * @param quantity
	 *            quantity
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * Get the quantity.
	 * 
	 * @return quantity
	 */
	public Integer getQuantity() {
		return this.quantity;
	}

	/**
	 * Set the type.
	 * 
	 * @param type
	 *            type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Get the type.
	 * 
	 * @return type
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Set the begin_timestamp.
	 * 
	 * @param beginTimestamp
	 *            begin_timestamp
	 */
	public void setBeginTimestamp(String beginTimestamp) {
		this.beginTimestamp = beginTimestamp;
	}

	/**
	 * Get the begin_timestamp.
	 * 
	 * @return begin_timestamp
	 */
	public String getBeginTimestamp() {
		return this.beginTimestamp;
	}

	/**
	 * Set the end_timestamp.
	 * 
	 * @param endTimestamp
	 *            end_timestamp
	 */
	public void setEndTimestamp(String endTimestamp) {
		this.endTimestamp = endTimestamp;
	}

	/**
	 * Get the end_timestamp.
	 * 
	 * @return end_timestamp
	 */
	public String getEndTimestamp() {
		return this.endTimestamp;
	}

	/**
	 * Set the fixed_term.
	 * 
	 * @param fixedTerm
	 *            fixed_term
	 */
	public void setFixedTerm(Integer fixedTerm) {
		this.fixedTerm = fixedTerm;
	}

	/**
	 * Get the fixed_term.
	 * 
	 * @return fixed_term
	 */
	public Integer getFixedTerm() {
		return this.fixedTerm;
	}

	/**
	 * Set the fixed_begin_term.
	 * 
	 * @param fixedBeginTerm
	 *            fixed_begin_term
	 */
	public void setFixedBeginTerm(Integer fixedBeginTerm) {
		this.fixedBeginTerm = fixedBeginTerm;
	}

	/**
	 * Get the fixed_begin_term.
	 * 
	 * @return fixed_begin_term
	 */
	public Integer getFixedBeginTerm() {
		return this.fixedBeginTerm;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cardId == null) ? 0 : cardId.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		WechatCard other = (WechatCard) obj;
		if (cardId == null) {
			if (other.cardId != null) {
				return false;
			}
		} else if (!cardId.equals(other.cardId)) {
			return false;
		}
		return true;
	}

}
