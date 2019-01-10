package com.xxx.wechat.admin.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.xxx.wechat.admin.service.IRoleService;
import com.xxx.wechat.core.dao.entity.AdminAuthority;
import com.xxx.wechat.core.dao.entity.AdminRole;
import com.xxx.wechat.core.dao.entity.extend.AdminRoleExt;
import com.xxx.wechat.core.exception.AppException;

@Service("roleService")
public class RoleServiceImpl implements IRoleService {

	private static Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

	@Override
	public Page<AdminRole> searchAll(AdminRoleExt roleExt) throws AppException {
		// TODO Auto-generated method stub
		logger.info("searchAll");
		return null;
	}

	@Override
	public int create(AdminRole role, List<AdminAuthority> permissions) throws AppException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(AdminRole role, List<AdminAuthority> permissions) throws AppException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(AdminRole role) throws AppException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AdminRole detail(String roleId) throws AppException {
		// TODO Auto-generated method stub
		return null;
	}

}
