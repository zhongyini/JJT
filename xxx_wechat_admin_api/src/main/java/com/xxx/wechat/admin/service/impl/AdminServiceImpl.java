package com.xxx.wechat.admin.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.xxx.wechat.admin.dto.RestResult;
import com.xxx.wechat.admin.service.IAdminService;
import com.xxx.wechat.admin.util.DateUtil;
import com.xxx.wechat.common.utils.CheckUtils;
import com.xxx.wechat.common.utils.DateUtils;
import com.xxx.wechat.core.dao.AdminUserDao;
import com.xxx.wechat.core.dao.AdminAuthorityDao;
import com.xxx.wechat.core.dao.entity.AdminAuthority;
import com.xxx.wechat.core.dao.entity.AdminRole;
import com.xxx.wechat.core.dao.entity.AdminUser;
import com.xxx.wechat.core.dao.entity.extend.AdminAuthorityExt;
import com.xxx.wechat.core.dao.entity.extend.AdminUserExt;
import com.xxx.wechat.core.exception.AppException;
import com.xxx.wechat.helper.MessageHelper;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service("adminService")
public class AdminServiceImpl implements IAdminService {

	private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

	@Autowired
	private AdminUserDao adminUserDao;
	
	@Autowired
	private AdminAuthorityDao adminAuthorityDao;
	
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
		List<AdminAuthorityExt> authorityList = adminAuthorityDao.selectByRoleId(adminUser.getRoleId());
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
	
	@Override
	public AdminUser select(AdminUserExt AdminUserExt) throws AppException {
		try {
			AdminUser adminUser = adminUserDao.selectByPrimaryKey(AdminUserExt.getAdminId());
			if (adminUser != null) {
				return adminUser;
			}
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new AppException(messageHelper.mesg_error_0001, e);
		}
	}

	@Override
	@Transactional
	public int updatePassword(AdminUserExt req) throws AppException {
		AdminUser admin = null;
		try {

			// 查寻用户是否存在
			admin = adminUserDao.selectByPrimaryKey(req.getAdminId());
			if (CheckUtils.isNull(admin)) {
				throw new AppException(messageHelper.mesg_info_0005);
			}
			String newPwd = req.getPassword();
			if (admin.getPassword().equals(newPwd)) {
				throw new AppException(messageHelper.mesg_info_0206);
			}
			Timestamp tt = admin.getModifyDate();
			Example example = new Example(AdminUser.class);
			admin.setPassword(newPwd);
			admin.setPasswordOverdueDate(DateUtils.getStringDate(DateUtils
					.getNowTimestamp()));
			//修改密码时deleteFlag为：0
			admin.setDeleteFlag("0");

			Criteria criteria = example.createCriteria();
			criteria.andEqualTo("adminId", admin.getAdminId());
			criteria.andEqualTo("modifyDate", tt);

			return adminUserDao.updateByExample(admin, example);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new AppException(messageHelper.mesg_error_0001, e);
		}
	}

}
