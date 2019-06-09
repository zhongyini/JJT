package com.xxx.wechat.admin.service;

import org.springframework.web.multipart.MultipartFile;

import com.xxx.wechat.admin.dto.RestResult;
import com.xxx.wechat.core.dao.entity.AdminUser;
import com.xxx.wechat.core.dao.entity.extend.AdminUserExt;
import com.xxx.wechat.core.exception.AppException;

public interface IAdminService {

	/**
	 * 用户登录
	 * 
	 * @param user
	 * @return
	 * @throws AppException
	 */
	RestResult login(AdminUser user) throws AppException;

	/**
	 * 保存新用户
	 * 
	 * @param user
	 * @return
	 * @throws AppException
	 */
	int save(AdminUser user) throws AppException;

	/**
	 * 更新用户信息
	 * 
	 * @param user
	 * @return
	 * @throws AppException
	 */
	int update(AdminUser user) throws AppException;

	/**
	 * 删除用户信息
	 * 
	 * @param req
	 * @return
	 * @throws AppException
	 */
	int delete(AdminUser req) throws AppException;

	/**
	 * 获取用户详细信息
	 * 
	 * @param id
	 * @return
	 * @throws AppException
	 */
	AdminUser detail(String id) throws AppException;

	/**
	 * 重置用户密码
	 * 
	 * @param admin
	 * @return
	 * @throws AppException
	 */
	int restPwd(AdminUser admin) throws AppException;

	/**
	 * 更新用户状态
	 * 
	 * @param admin
	 * @return
	 * @throws AppException
	 */
	int updateStatue(AdminUser admin) throws AppException;

	/**
	 * 上传头像
	 * 
	 * @param admin
	 * @return
	 * @throws AppException
	 */
	String uploadImage(MultipartFile file, String id) throws AppException;
	
	/**
	 * 获取用户所有信息
	 * 
	 * @param id
	 * @return
	 * @throws AppException
	 */
	AdminUser select(AdminUserExt AdminUserExt) throws AppException;
	
	/**
	 * 更新用户密码
	 * 
	 * @param req
	 * @return
	 * @throws AppException
	 */
	int updatePassword(AdminUserExt req) throws AppException;
}
