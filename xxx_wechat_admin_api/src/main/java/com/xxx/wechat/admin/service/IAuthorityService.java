package com.xxx.wechat.admin.service;

import java.util.List;

import com.xxx.wechat.core.dao.entity.AdminAuthority;
import com.xxx.wechat.core.exception.AppException;

public interface IAuthorityService {

	List<AdminAuthority> searchByRoleId(String roleId) throws AppException;

	List<String> searchAuthorityCodeByRoleId(String roleId) throws AppException;

	List<AdminAuthority> searchAll() throws AppException;
}
