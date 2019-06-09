package com.xxx.wechat.admin.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxx.wechat.admin.service.IWechatUserAccountService;
import com.xxx.wechat.common.utils.CheckUtils;
import com.xxx.wechat.common.utils.DateUtils;
import com.xxx.wechat.core.dao.WechatRecommendDao;
import com.xxx.wechat.core.dao.WechatUserAccountDao;
import com.xxx.wechat.core.dao.WechatUserDao;
import com.xxx.wechat.core.dao.entity.WechatRecommend;
import com.xxx.wechat.core.dao.entity.WechatUser;
import com.xxx.wechat.core.dao.entity.WechatUserAccount;
import com.xxx.wechat.core.exception.AppException;
import com.xxx.wechat.helper.MessageHelper;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service("wechatUserAccountService")
public class WechatUserAccountServiceImpl implements IWechatUserAccountService{
	private static final Logger logger = LoggerFactory
			.getLogger(AdminServiceImpl.class);
	
	@Autowired
	private WechatUserAccountDao userAccountDao;
	@Autowired
	private WechatUserDao userdao;
	@Autowired
	WechatRecommendDao recommendDao;
	@Autowired
	private MessageHelper messageHelper;
	
	@Override
	public void selectUserAccount(String openId,String money) throws AppException {
		
		try {
			//查询该用户是否开通账户
			WechatUserAccount wechatUser = userAccountDao.selectByPrimaryKey(openId);
			//记录受影响的行数
			int changeStatus = 0;
			//如果没有开通则开通账户
			if (CheckUtils.isNull(wechatUser)) {
				//获取用户的详细信息
				WechatUser user = userdao.selectByPrimaryKey(openId);
				//添加账户
				WechatUserAccount record = new WechatUserAccount();
				record.setOpenid(openId);
				record.setUnionid(user.getUnionid());
				record.setBalance((long)0);
				record.setCreateDatetime(DateUtils.getNowTimestamp());
				record.setUpdateDatetime(DateUtils.getNowTimestamp());
				changeStatus = userAccountDao.insert(record);
				if (changeStatus != 1) {
					logger.error("开通账户失败");
					throw new AppException(messageHelper.mesg_info_1712);
				}
			}
			//查询该用户是否有推荐人
			List<WechatRecommend> wechatRecommendList = recommendDao.referrer(openId);
			//如果有则对推荐人进行操作
			if (!CheckUtils.isNull(wechatRecommendList) && wechatRecommendList.size() > 0) {
				for (WechatRecommend wechatRecommend : wechatRecommendList) {
					if (!CheckUtils.isNullOrEmpty(wechatRecommend.getRecOpenid())) {
						//查询推荐人是否开通账户
						wechatUser = null;
						wechatUser = userAccountDao.selectByPrimaryKey(wechatRecommend.getRecOpenid());
						//如果没有开通则开通账户
						if (CheckUtils.isNull(wechatUser)) {
							//获取用户的详细信息
							WechatUser user = userdao.selectByPrimaryKey(wechatRecommend.getRecOpenid());
							//添加账户
							changeStatus = 0;
							WechatUserAccount record = new WechatUserAccount();
							record.setOpenid(user.getOpenid());
							record.setUnionid(user.getUnionid());
							record.setBalance((long)0);
							record.setCreateDatetime(DateUtils.getNowTimestamp());
							record.setUpdateDatetime(DateUtils.getNowTimestamp());
							changeStatus = userAccountDao.insert(record);
							if (changeStatus != 1) {
								logger.error("开通账户失败");
								throw new AppException(messageHelper.mesg_info_1712);
							}
						}
						changeStatus = 0;
						//修改推荐人的余额
						changeStatus = updateUserAccount(wechatRecommend.getRecOpenid(),Integer.valueOf(money)*100);
						if (changeStatus != 1) {
							logger.error("修改推荐人余额失败");
							throw new AppException(messageHelper.mesg_info_1716);
						}
					}
				}
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new AppException(messageHelper.mesg_error_0001, e);
		}
		
	}

	@Override
	public int saveUserAccount(String openId, String unionId) throws AppException {
		if (CheckUtils.isNullOrEmpty(openId)) {
			logger.error("openId不能为空");
			throw new AppException(messageHelper.mesg_info_1711);
		}
		/*if (CheckUtils.isNullOrEmpty(unionId)) {
			logger.error("unionId不能为空");
			throw new AppException(messageHelper.mesg_info_1708);
		}*/
		WechatUserAccount record = new WechatUserAccount();
		record.setOpenid(openId);
		record.setUnionid(unionId);
		record.setBalance((long)0);
		record.setCreateDatetime(DateUtils.getNowTimestamp());
		record.setUpdateDatetime(DateUtils.getNowTimestamp());
		return userAccountDao.insert(record);
	}


	@Override
	public int updateUserAccount(String openId,Integer money) throws AppException {
		if (CheckUtils.isNullOrEmpty(openId)) {
			logger.error("openId不能为空");
			throw new AppException(messageHelper.mesg_info_1711);
		}
		if (CheckUtils.isNullOrEmpty(openId)) {
			logger.error("请设置返点金额");
			throw new AppException(messageHelper.mesg_info_1714);
		}
		try {
			WechatUserAccount userAccount = userAccountDao.selectByPrimaryKey(openId);
			if (CheckUtils.isNull(userAccount)) {
				logger.error("该账户不存在，修改失败");
				throw new AppException(messageHelper.mesg_info_1713);
			}
			long balance = userAccount.getBalance();
			userAccount.setBalance(balance + money);
			userAccount.setUpdateDatetime(DateUtils.getNowTimestamp());
			Example example = new Example(WechatUserAccount.class);
			Criteria criteria = example.createCriteria();
			criteria.andEqualTo("openid", userAccount.getOpenid());
			
			return userAccountDao.updateByExample(userAccount, example);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new AppException(messageHelper.mesg_error_0001);
		}
		
	}
	
	
}
