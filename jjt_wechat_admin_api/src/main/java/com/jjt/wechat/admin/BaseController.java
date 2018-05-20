package com.jjt.wechat.admin;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.jjt.common.utils.CheckUtils;
import com.jjt.wechat.core.exception.AppException;
import com.jjt.wechat.admin.dto.LoginReq;
import com.jjt.wechat.constants.Constants;
import com.jjt.wechat.helper.MessageHelper;

public abstract class BaseController {
	
	protected Logger logger = LoggerFactory
			.getLogger(this.getClass());
	
	@Autowired
	protected HttpServletRequest request;

	@Autowired
	protected MessageHelper messageHelper;

	protected LoginReq getAdmin() {
		return (LoginReq) request.getSession().getAttribute(Constants.USER);
	}

	protected String getAdminId() {
		LoginReq admin = getAdmin();
		if (CheckUtils.isNull(admin)) {
			throw new AppException(messageHelper.mesg_error_0001);
		}
		return admin.getName();
	}

}