package com.xxx.wechat.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xxx.wechat.admin.BaseController;
import com.xxx.wechat.admin.RestResult;
import com.xxx.wechat.admin.service.IAdminService;
import com.xxx.wechat.common.utils.CheckUtils;
import com.xxx.wechat.constants.Constants;
import com.xxx.wechat.core.dao.entity.AdminUser;
import com.xxx.wechat.core.dao.entity.extend.AdminUserExt;
import com.xxx.wechat.core.exception.AppException;

@RestController
@RequestMapping("/admin")
public class AdminController extends BaseController {
	@Autowired
	private IAdminService adminService;
	
	@RequestMapping(value = "/updateOldDatePassword", method = RequestMethod.POST)
	public RestResult updateOldDatePassword(@RequestBody AdminUserExt admin) {
		
		// 查寻用户是否存在
		AdminUser nowUser = adminService.select(admin);
		if (CheckUtils.isNull(nowUser)) {
			return new RestResult(messageHelper.mesg_info_0005);
		}
			String oldPwd = nowUser.getPassword();
			String inputPwd = admin.getOldPassword();
		if (!inputPwd.equals(oldPwd)) {
			return new RestResult(messageHelper.mesg_info_0208);
		}
		// 新密码和旧密码比较
		if (admin.getPassword().equals(admin.getOldPassword())) {
			return new RestResult(messageHelper.mesg_info_0206);
		}
		try {
			int ret = adminService.updatePassword(admin);
			if (ret > Constants.SUCCESS) {
				return new RestResult(Constants.SUCCESS,
						messageHelper.mesg_info_0008);
			} else {
				return new RestResult(messageHelper.mesg_info_0010);
			}

		} catch (AppException e) {
			return new RestResult(e.getMessage());
		}

	}
	
}
