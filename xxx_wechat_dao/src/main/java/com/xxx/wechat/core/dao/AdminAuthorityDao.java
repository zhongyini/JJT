package com.xxx.wechat.core.dao;

import java.util.List;

import com.xxx.wechat.core.BaseDao;
import com.xxx.wechat.core.dao.entity.AdminAuthority;
import com.xxx.wechat.core.dao.entity.extend.AdminAuthorityExt;;

public interface AdminAuthorityDao extends BaseDao<AdminAuthority>{

	public List<AdminAuthorityExt> selectByRoleId(String roleId);
}
