package com.xxx.wechat.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.xxx.wechat.admin.dto.LoginResp;
import com.xxx.wechat.admin.dto.RestResult;
import com.xxx.wechat.admin.enums.AdminStatus;
import com.xxx.wechat.admin.service.IAdminService;
import com.xxx.wechat.admin.util.DateUtil;
import com.xxx.wechat.common.utils.CheckUtils;
import com.xxx.wechat.constants.Constants;
import com.xxx.wechat.core.dao.AdminUserDao;
import com.xxx.wechat.core.dao.AuthorityDao;
import com.xxx.wechat.core.dao.entity.AdminAuthority;
import com.xxx.wechat.core.dao.entity.AdminUser;
import com.xxx.wechat.core.exception.AppException;
import com.xxx.wechat.helper.MessageHelper;

@Service("adminService")
public class AdminServiceImpl implements IAdminService {

	private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

	@Autowired
	private AdminUserDao adminUserDao;
	
	@Autowired
	private AuthorityDao authorityDao;
	
	@Autowired
	protected MessageHelper messageHelper;
	
	@Override
	public RestResult login(AdminUser user) throws AppException {
		RestResult restResult = new RestResult();
		AdminUser adminUser = adminUserDao.login(user);
		if (CheckUtils.isNull(adminUser)) {
			restResult.setErrorResult("账号或密码错误");
			return restResult;
		}
		// 过期
		if (DateUtil.daysBetweenStr(adminUser.getPasswordOverdueDate(), DateUtil.formatNow(DateUtil.YYYYMMDD1), DateUtil.YYYYMMDD1) >= 90) {
			restResult.setErrorResult("账号过期");
			return restResult;
		}
		List<AdminAuthority> authorityList = authorityDao.selectByRoleId(adminUser.getRoleId());
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("adminUser", adminUser);
		result.put("authorityList", authorityList);
		restResult.setResult(result);
		restResult.setMessage(messageHelper.mesg_info_0109);
		return restResult;
	}

	@Override
	public int save(AdminUser user) throws AppException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(AdminUser user) throws AppException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(AdminUser req) throws AppException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AdminUser detail(String id) throws AppException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int restPwd(AdminUser admin) throws AppException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateStatue(AdminUser admin) throws AppException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String uploadImage(MultipartFile file, String id) throws AppException {
		// TODO Auto-generated method stub
		return null;
	}

}
