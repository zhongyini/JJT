package com.xxx.wechat.core.dao.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 管理员表.
 * 
 */
@Table(name = "xxx_admin_user")
public class AdminUser implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	public AdminUser(String adminId,String password){
		this.adminId = adminId;
		this.password = password;
	}
	
	/** admin_id. */
	@Id
	@Column(name = "admin_id")
	private String adminId;

	/** role_id. */
	@Column(name = "role_id")
	private String roleId;

	/** name. */
	@Column(name = "name")
	private String name;

	/** password. */
	@Column(name = "password")
	private String password;

	/** password_overdue_date. */
	@Column(name = "password_overdue_date")
	private String passwordOverdueDate;

	/** mail. */
	@Column(name = "mail")
	private String mail;

	/** headImgUrl. */
	@Column(name = "headImgUrl")
	private String headImgUrl;

	/** delete_flag. */
	@Column(name = "delete_flag")
	private String deleteFlag;

	/** create_user. */
	@Column(name = "create_user")
	private String createUser;

	/** create_date. */
	@Column(name = "create_date")
	private Date createDate;

	/** modify_user. */
	@Column(name = "modify_user")
	private String modifyUser;

	/** modify_date. */
	@Column(name = "modify_date")
	private Timestamp modifyDate;

	/**
	 * Constructor.
	 */
	public AdminUser() {
	}

	/**
	 * Set the admin_id.
	 * 
	 * @param adminId
	 *            admin_id
	 */
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	/**
	 * Get the admin_id.
	 * 
	 * @return admin_id
	 */
	public String getAdminId() {
		return this.adminId;
	}

	/**
	 * Set the role_id.
	 * 
	 * @param roleId
	 *            role_id
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * Get the role_id.
	 * 
	 * @return role_id
	 */
	public String getRoleId() {
		return this.roleId;
	}

	/**
	 * Set the name.
	 * 
	 * @param name
	 *            name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the name.
	 * 
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Set the password.
	 * 
	 * @param password
	 *            password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Get the password.
	 * 
	 * @return password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Set the password_overdue_date.
	 * 
	 * @param passwordOverdueDate
	 *            password_overdue_date
	 */
	public void setPasswordOverdueDate(String passwordOverdueDate) {
		this.passwordOverdueDate = passwordOverdueDate;
	}

	/**
	 * Get the password_overdue_date.
	 * 
	 * @return password_overdue_date
	 */
	public String getPasswordOverdueDate() {
		return this.passwordOverdueDate;
	}

	/**
	 * Set the mail.
	 * 
	 * @param mail
	 *            mail
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * Get the mail.
	 * 
	 * @return mail
	 */
	public String getMail() {
		return this.mail;
	}

	/**
	 * Set the portrait.
	 * 
	 * @param portrait
	 *            portrait
	 */
	public void setPortrait(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	/**
	 * Get the portrait.
	 * 
	 * @return portrait
	 */
	public String getPortrait() {
		return this.headImgUrl;
	}

	/**
	 * Set the delete_flag.
	 * 
	 * @param deleteFlag
	 *            delete_flag
	 */
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	/**
	 * Get the delete_flag.
	 * 
	 * @return delete_flag
	 */
	public String getDeleteFlag() {
		return this.deleteFlag;
	}

	/**
	 * Set the create_user.
	 * 
	 * @param createUser
	 *            create_user
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	/**
	 * Get the create_user.
	 * 
	 * @return create_user
	 */
	public String getCreateUser() {
		return this.createUser;
	}

	/**
	 * Set the create_date.
	 * 
	 * @param createDate
	 *            create_date
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * Get the create_date.
	 * 
	 * @return create_date
	 */
	public Date getCreateDate() {
		return this.createDate;
	}

	/**
	 * Set the modify_user.
	 * 
	 * @param modifyUser
	 *            modify_user
	 */
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	/**
	 * Get the modify_user.
	 * 
	 * @return modify_user
	 */
	public String getModifyUser() {
		return this.modifyUser;
	}

	/**
	 * Set the modify_date.
	 * 
	 * @param modifyDate
	 *            modify_date
	 */
	public void setModifyDate(Timestamp modifyDate) {
		this.modifyDate = modifyDate;
	}

	/**
	 * Get the modify_date.
	 * 
	 * @return modify_date
	 */
	public Timestamp getModifyDate() {
		return this.modifyDate;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adminId == null) ? 0 : adminId.hashCode());
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
		AdminUser other = (AdminUser) obj;
		if (adminId == null) {
			if (other.adminId != null) {
				return false;
			}
		} else if (!adminId.equals(other.adminId)) {
			return false;
		}
		return true;
	}

}
