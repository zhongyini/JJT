package com.xxx.wechat.admin.service;

import java.util.List;

import com.xxx.wechat.core.dao.entity.AdminRoleAuthorityRel;
import com.xxx.wechat.core.exception.AppException;

public interface IAuthorityService {

	List<AdminRoleAuthorityRel> searchByRoleId(String roleId) throws AppException;

	List<String> searchAuthorityCodeByRoleId(String roleId) throws AppException;

	List<AdminRoleAuthorityRel> searchAll() throws AppException;
}
