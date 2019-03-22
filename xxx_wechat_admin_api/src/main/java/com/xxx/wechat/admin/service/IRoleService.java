package com.xxx.wechat.admin.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.xxx.wechat.core.dao.entity.AdminRoleAuthorityRel;
import com.xxx.wechat.core.dao.entity.AdminRole;
import com.xxx.wechat.core.dao.entity.extend.AdminRoleExt;
import com.xxx.wechat.core.exception.AppException;

public interface IRoleService {

	public Page<AdminRole> searchAll(AdminRoleExt roleExt) throws AppException;

	int create(AdminRole role, List<AdminRoleAuthorityRel> permissions) throws AppException;

	int update(AdminRole role, List<AdminRoleAuthorityRel> permissions) throws AppException;

	int delete(AdminRole role) throws AppException;

	AdminRole detail(String roleId) throws AppException;
}
