package com.xxx.wechat.core.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.xxx.wechat.core.BaseDao;
import com.xxx.wechat.core.dao.entity.WechatUser;
import com.xxx.wechat.core.dao.entity.extend.ShareTheViewExt;

@Mapper
public interface WechatUserDao extends BaseDao<WechatUser> {
	
	WechatUser selectByOpenid(String openid);

	List<ShareTheViewExt> sharTheView(ShareTheViewExt share);
	
	List<ShareTheViewExt> selectDownByUser(ShareTheViewExt userAdmin);
}
