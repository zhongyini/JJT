package com.xxx.wechat.admin.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.xxx.wechat.admin.service.IAdminService;
import com.xxx.wechat.core.dao.entity.AdminUser;
import com.xxx.wechat.core.exception.AppException;

@Service("adminService")
public class AdminServiceImpl implements IAdminService {

	private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

	@Override
	public AdminUser login(AdminUser user) throws AppException {
		// TODO Auto-generated method stub
		logger.info("login");
		return null;
	}

	@Override
	public int save(AdminUser user) throws AppException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(AdminUser user) throws AppException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(AdminUser req) throws AppException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AdminUser detail(String id) throws AppException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int restPwd(AdminUser admin) throws AppException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateStatue(AdminUser admin) throws AppException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String uploadImage(MultipartFile file, String id) throws AppException {
		// TODO Auto-generated method stub
		return null;
	}

}
