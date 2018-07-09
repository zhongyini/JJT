package com.xxx.wechat.helper;

import java.util.List;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xxx.wechat.admin.service.IAuthorityService;
import com.xxx.common.utils.CheckUtils;
import com.xxx.wechat.core.entity.Authority;
import com.xxx.wechat.core.exception.AppException;

@Component
public class AuthorityHelper {

	private static final Logger logger = LoggerFactory
			.getLogger(AuthorityHelper.class);
	@Autowired
	private IAuthorityService authorityService;

	private Vector<Authority> authorities = new Vector<Authority>();

	public boolean hasPermission(String roleId, String authorityCode) {
		if (CheckUtils.isNullOrEmpty(roleId)
				||CheckUtils.isNullOrEmpty(authorityCode)) {
			logger.warn("参数异常");
			return false;
		}
		for (Authority authority : authorities) {
			if (roleId.equals(authority.getRoleId().toString())
					&&authorityCode.equals(authority.getAuthorityCode())) {
				return true;
			}
		}
		return false;
	}

	public boolean hasPermission(String key,List<Authority> authoritiess) {
		for (Authority authority : authoritiess) {
			if (authority.getAuthorityCode().contains(key)) {
				return true;
			}
		}
		return false;
	}
	public void load() {
		List<Authority> list = null;
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
