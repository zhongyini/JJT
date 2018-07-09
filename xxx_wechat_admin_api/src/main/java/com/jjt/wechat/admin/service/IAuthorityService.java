package com.xxx.wechat.admin.service;

import java.util.List;

import com.xxx.wechat.core.entity.Authority;
import com.xxx.wechat.core.exception.AppException;

public interface IAuthorityService {

	List<Authority> searchByRoleId(String roleId) throws AppException;

	List<String> searchAuthorityCodeByRoleId(String roleId) throws AppException;

	List<Authority> searchAll() throws AppException;
}
