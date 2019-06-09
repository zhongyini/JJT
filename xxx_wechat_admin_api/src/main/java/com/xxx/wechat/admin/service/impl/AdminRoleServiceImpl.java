package com.xxx.wechat.admin.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.xxx.wechat.admin.service.IAdminRoleService;
import com.xxx.wechat.core.dao.entity.AdminRole;
import com.xxx.wechat.core.dao.entity.AdminRoleAuthorityRel;
import com.xxx.wechat.core.dao.AdminRoleAuthorityRelDao;
import com.xxx.wechat.core.dao.AdminRoleDao;
import com.xxx.wechat.core.dao.entity.AdminAuthority;
import com.xxx.wechat.core.dao.entity.extend.AdminRoleExt;
import com.xxx.wechat.core.exception.AppException;
import com.xxx.wechat.helper.MessageHelper;

@Service("roleService")
public class AdminRoleServiceImpl implements IAdminRoleService {

	private static Logger logger = LoggerFactory.getLogger(AdminRoleServiceImpl.class);

	@Autowired
	private AdminRoleDao adminRoleDao;
	@Autowired
	private AdminRoleAuthorityRelDao adminRoleAuthorityRelDao;
	@Autowired
	private MessageHelper messageHelper;
	
	@Override
	public AdminRole detail(String roleId) throws AppException {
		// TODO Auto-generated method stub
		logger.info("detail");
		return null;
	}

	@Override
	public Page<AdminRole> searchAll(AdminRoleExt roleExt) throws AppException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int create(AdminRole role, List<AdminRoleAuthorityRel> permissions) throws AppException {
		// 新增角色
		try {
			// 插入权限信息
			for (AdminRoleAuthorityRel authority : permissions) {
				adminRoleAuthorityRelDao.insert(authority);
			}
			//authorityDao.insertList(permissions);
			// 插入角色信息
			return adminRoleDao.insert(role);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new AppException(messageHelper.mesg_error_0001, e);
		}
	}

	@Override
	public int update(AdminRole role, List<AdminRoleAuthorityRel> permissions) throws AppException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(AdminRole role) throws AppException {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
