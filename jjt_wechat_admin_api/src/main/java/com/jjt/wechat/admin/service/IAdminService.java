package com.jjt.wechat.admin.service;

import org.springframework.web.multipart.MultipartFile;

import com.jjt.wechat.core.entity.UserAdmin;
import com.jjt.wechat.core.exception.AppException;

public interface IAdminService {

	/**
	 * 用户登录
	 * 
	 * @param user
	 * @return
	 * @throws AppException
	 */
	UserAdmin login(UserAdmin user) throws AppException;

	/**
	 * 保存新用户
	 * 
	 * @param user
	 * @return
	 * @throws AppException
	 */
	int save(UserAdmin user) throws AppException;

	/**
	 * 更新用户信息
	 * 
	 * @param user
	 * @return
	 * @throws AppException
	 */
	int update(UserAdmin user) throws AppException;

	/**
	 * 删除用户信息
	 * 
	 * @param req
	 * @return
	 * @throws AppException
	 */
	int delete(UserAdmin req) throws AppException;

	/**
	 * 获取用户详细信息
	 * 
	 * @param id
	 * @return
	 * @throws AppException
	 */
	UserAdmin detail(String id) throws AppException;

	/**
	 * 重置用户密码
	 * 
	 * @param admin
	 * @return
	 * @throws AppException
	 */
	int restPwd(UserAdmin admin) throws AppException;

	/**
	 * 更新用户状态
	 * 
	 * @param admin
	 * @return
	 * @throws AppException
	 */
	int updateStatue(UserAdmin admin) throws AppException;

	/**
	 * 上传头像
	 * 
	 * @param admin
	 * @return
	 * @throws AppException
	 */
	String uploadImage(MultipartFile file, String id) throws AppException;
}
