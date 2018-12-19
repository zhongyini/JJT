package com.xxx.wechat.helper;

import java.util.List;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xxx.wechat.admin.service.IAuthorityService;
import com.xxx.wechat.common.utils.CheckUtils;
import com.xxx.wechat.core.dao.entity.AdminAuthority;
import com.xxx.wechat.core.exception.AppException;

@Component
public class AuthorityHelper {

	private static final Logger logger = LoggerFactory
			.getLogger(AuthorityHelper.class);
	@Autowired
	private IAuthorityService authorityService;

	private Vector<AdminAuthority> authorities = new Vector<AdminAuthority>();

	public boolean hasPermission(String roleId, String authorityCode) {
		if (CheckUtils.isNullOrEmpty(roleId)
				||CheckUtils.isNullOrEmpty(authorityCode)) {
			logger.warn("参数异常");
			return false;
		}
		for (AdminAuthority authority : authorities) {
			if (roleId.equals(authority.getRoleId().toString())
					&&authorityCode.equals(authority.getAuthorityCode())) {
				return true;
			}
		}
		return false;
	}

	public boolean hasPermission(String key,List<AdminAuthority> authoritiess) {
		for (AdminAuthority authority : authoritiess) {
			if (authority.getAuthorityCode().contains(key)) {
				return true;
			}
		}
		return false;
	}
	public void load() {
		List<AdminAuthority> list = null;
		try {
			list = authorityService.searchAll();
			if (!CheckUtils.isNullOrEmpty(list)) {
				authorities.addAll(list);
			}
		} catch (AppException e) {
			logger.error(e.getMessage(), e.getCause());
		}
	}

	public void reLoad() {

		authorities.clear();
		load();

	}

}
