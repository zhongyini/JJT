package com.xxx.wechat.core.dao;

import org.apache.ibatis.annotations.Delete;

import com.xxx.wechat.core.BaseDao;
import com.xxx.wechat.core.dao.entity.AdminRole;;

public interface AdminRoleDao extends BaseDao<AdminRole>{
	
	@Delete("DELETE FROM XXX_ADMIN_ROLE WHERE ROLE_ID =#{roleId,jdbcType=VARCHAR}")
	int deleteByRole(String roleId);
}
