package com.jjt.wechat.core.config;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.atomic.AtomicBoolean;

import com.jjt.wechat.core.util.AppContextUtils;

/**
 * sys配置类，项目中请保证其为单例
 * 实现观察者模式，用于监控token变化
 *
 */
public final class SysConfig extends Observable implements Serializable {

	private static final long serialVersionUID = 1L;

    private final        AtomicBoolean propRefreshing = new AtomicBoolean(false);

    private       long    startTime;
    private       Map<String,String> propMap = new Hashtable<String, String>();

    private static SysConfig apiConfig = null;
    public static SysConfig getInstance() {
		if (apiConfig == null) {
			apiConfig = new SysConfig();
		}
		return apiConfig;
	}
	
    public SysConfig() {
        this(false);
    }
    
    public SysConfig( boolean enableJsApi) {
        long now = System.currentTimeMillis();
        initMap(now);
    }
    
    public String getProperty(String key, boolean isInit) {
        long now = System.currentTimeMillis();
        long time = now - this.startTime;
        try {
            /*
             * 判断优先顺序：
             * 1.官方给出的超时时间是7200秒，这里用7100秒来做，防止出现已经过期的情况
             * 2.刷新标识判断，如果已经在刷新了，则也直接跳过，避免多次重复刷新，如果没有在刷新，则开始刷新
             */
            if (isInit || (time > 15000 && this.propRefreshing.compareAndSet(false, true))) {
//                LOG.debug("准备刷新token.............");
                initMap(now);
            }
        } catch (Exception e) {
//            LOG.warn("刷新Token出错.", e);
            //刷新工作出现有异常，将标识设置回false
            this.propRefreshing.set(false);
        }
        return propMap.get(key);
    
    }

    public String getProperty(String key) {
    	return getProperty(key, false);
    }

    /**
     * 移除所有配置变化监听器
     */
    public void removeAllHandle() {
        super.deleteObservers();
    }

    private void initMap(final long refreshTime) {
//        LOG.debug("开始初始化access_token........");
//        //记住原本的时间，用于出错回滚
//        final long oldTime = this.startTime;
        this.startTime = refreshTime;
        
//        List<SysCtrl> list = AppContextUtils.getBean(ConfigurationDao.class).selectAll();
//        propMap.clear();
//		Iterator<SysCtrl> iter = list.iterator();
//		SysCtrl dto = null;
//		while (iter.hasNext()) {
//			dto = iter.next();
//			propMap.put(dto.getItem(), dto.getProperty());
//		}
//        //刷新工作做完，将标识设置回false
        this.propRefreshing.set(false);
    }

}
