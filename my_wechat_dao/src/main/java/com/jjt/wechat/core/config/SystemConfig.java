package com.jjt.wechat.core.config;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jjt.wechat.common.utils.CheckUtils;
import com.jjt.wechat.core.dao.ConfigurationDao;
import com.jjt.wechat.core.dao.entity.Configuration;
import com.jjt.wechat.core.service.IConfigurationService;
import com.jjt.wechat.core.util.AppContextUtils;

public class SystemConfig implements Serializable {
	
	private static final long serialVersionUID = -5521090852200151806L;

	//日志
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//刷新时间（毫秒）
	private int refreshSecond = 15000;
	
	//上一次时间（毫秒）
	private long lastTime = 0L;

	//存储系统配置集合
	private Map<String, String> propMap = null;

	private static SystemConfig systemConfig = null;;

	private SystemConfig() {
		//初始化系统配置map集合
		if(CheckUtils.isNull(propMap)){
			propMap = new Hashtable<String, String>();
		}
		
		initConfig(System.currentTimeMillis());
	}


	//初始化配置集合
	private void initConfig(long lastTime) {
		this.lastTime = lastTime;
		List<Configuration> configList = null;
		//获取系统配置的list
		try {
			configList = AppContextUtils.getBean(ConfigurationDao.class).selectAll();
			propMap.clear();
			for(Configuration config : configList) {
				propMap.put(config.getItem(), config.getProperty());
			}
			return;
		} catch (Exception e) {
			logger.error("System config init error:"+e.getMessage());
		}
	}


	/**
	 * 获取系统配置的对象
	 * @return
	 */
	public static SystemConfig getInstance() {
		//单利模式初始化系统配置
		if (CheckUtils.isNull(systemConfig)) {
			systemConfig = new SystemConfig();
		}
		return systemConfig;
	}
	
	/**
	 * 获取t_configuration表系统配置
	 * 
	 * key参数获取内容：com.jjt.wechat.common.constant.Constant.Configuration
	 * @param key
	 * @return
	 */
	public String getProperty(String key){
		long now = System.currentTimeMillis();
		long sectionTime = now - this.lastTime;
		
		//若系统设定刷新时间小于最近两次时间差，则重新加载系统配置
		if(refreshSecond < sectionTime){
			initConfig(System.currentTimeMillis());
		}
		
		return propMap.get(key);
	}
}
