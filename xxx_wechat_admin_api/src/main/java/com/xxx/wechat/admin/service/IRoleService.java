package com.xxx.wechat.admin.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.xxx.wechat.core.entity.Authority;
import com.xxx.wechat.core.entity.Role;
import com.xxx.wechat.core.entity.extend.RoleExt;
import com.xxx.wechat.core.exception.AppException;

public interface IRoleService {

	public Page<Role> searchAll(RoleExt roleExt) throws AppException;

	int create(Role role, List<Authority> permissions) throws AppException;

	int update(Role role, List<Authority> permissions) throws AppException;

	int delete(Role role) throws AppException;

	Role detail(String roleId) throws AppException;
}
