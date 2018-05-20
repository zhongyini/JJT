package com.jjt.wechat.admin.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jjt.wechat.admin.service.IAdminService;
import com.jjt.wechat.core.entity.UserAdmin;
import com.jjt.wechat.core.exception.AppException;

@Service("adminService")
public class AdminServiceImpl implements IAdminService {

	private static final Logger logger = LoggerFactory
			.getLogger(AdminServiceImpl.class);

	@Override
	public UserAdmin login(UserAdmin user) throws AppException {
		// TODO Auto-generated method stub
		logger.info("login");
		return null;
	}

	@Override
	public int save(UserAdmin user) throws AppException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(UserAdmin user) throws AppException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(UserAdmin req) throws AppException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UserAdmin detail(String id) throws AppException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int restPwd(UserAdmin admin) throws AppException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateStatue(UserAdmin admin) throws AppException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String uploadImage(MultipartFile file, String id) throws AppException {
		// TODO Auto-generated method stub
		return null;
	}

}
