package com.xxx.wechat.core.config;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xxx.wechat.common.constant.ConfigurationEnum;
import com.xxx.wechat.common.utils.CheckUtils;
import com.xxx.wechat.core.dao.ConfigurationDao;
import com.xxx.wechat.core.dao.entity.Configuration;
import com.xxx.wechat.core.util.AppContextUtils;

public class ConfigurationConfig implements Serializable {
	
	private static final long serialVersionUID = 559623999036263612L;

	//日志
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//存储系统配置集合
	private Map<String, String> propMap = null;

	private static ConfigurationConfig systemConfig = null;

	private ConfigurationConfig() {
		//初始化系统配置map集合
		if(CheckUtils.isNull(propMap)){
			propMap = new Hashtable<String, String>();
		}
		
		initConfig();
	}


	//初始化配置集合
	private void initConfig() {
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
	public static ConfigurationConfig getInstance() {
		//单例模式初始化系统配置
		//if (CheckUtils.isNull(systemConfig)) {
			systemConfig = new ConfigurationConfig();
		//}
		return systemConfig;
	}
	
	/**
	 * 获取t_configuration表系统配置
	 * 
	 * key参数获取内容：com.xxx.wechat.common.constant.ConfigurationEnum
	 * @param key
	 * @return
	 */
	public String getProperty(ConfigurationEnum configurationEnum){
		return propMap.get(configurationEnum.toString());
	}
}
