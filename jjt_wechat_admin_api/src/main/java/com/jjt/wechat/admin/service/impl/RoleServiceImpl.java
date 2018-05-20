package com.jjt.wechat.admin.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.jjt.wechat.admin.service.IRoleService;
import com.jjt.wechat.core.entity.Authority;
import com.jjt.wechat.core.entity.Role;
import com.jjt.wechat.core.entity.extend.RoleExt;
import com.jjt.wechat.core.exception.AppException;

@Service("roleService")
public class RoleServiceImpl implements IRoleService {

	private static Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

	@Override
	public Page<Role> searchAll(RoleExt roleExt) throws AppException {
		// TODO Auto-generated method stub
		logger.info("searchAll");
		return null;
	}

	@Override
	public int create(Role role, List<Authority> permissions) throws AppException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Role role, List<Authority> permissions) throws AppException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Role role) throws AppException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Role detail(String roleId) throws AppException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
