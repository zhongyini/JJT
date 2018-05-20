package com.jjt.wechat.core.config;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.atomic.AtomicBoolean;

//import com.jjt.wechat.core.dao.UrlSettingDao;
//import com.jjt.wechat.core.entity.UrlSetting;
import com.jjt.wechat.core.util.AppContextUtils;

/**
 * URL配置类，项目中请保证其为单例
 * 实现观察者模式，用于监控token变化
 *
 */
public final class UrlConfig extends Observable implements Serializable {

	private static final long serialVersionUID = 1L;

    private final        AtomicBoolean propRefreshing = new AtomicBoolean(false);

    private       long    startTime;
    private       Map<String,String> propMap = new Hashtable<String, String>();

    private static UrlConfig apiConfig = null;
    public static UrlConfig getInstance() {
		if (apiConfig == null) {
			apiConfig = new UrlConfig();
		}
		return apiConfig;
	}
	
    public UrlConfig() {
        this(false);
    }
    
    public UrlConfig( boolean enableJsApi) {
        long now = System.currentTimeMillis();
        initMap(now);
    }

    public String getUrl(String key) {
        long now = System.currentTimeMillis();
        long time = now - this.startTime;
        try {
            /*
             * 判断优先顺序：
             * 1.官方给出的超时时间是7200秒，这里用7100秒来做，防止出现已经过期的情况
             * 2.刷新标识判断，如果已经在刷新了，则也直接跳过，避免多次重复刷新，如果没有在刷新，则开始刷新
             */
            if (time > 15000 && this.propRefreshing.compareAndSet(false, true)) {
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

    /**
     * 移除所有配置变化监听器
     */
    public void removeAllHandle() {
        super.deleteObservers();
    }

    private void initMap(final long refreshTime) {
        this.startTime = refreshTime;
        
//        List<UrlSetting> list = AppContextUtils.getBean(UrlSettingDao.class).selectAll();
//        propMap.clear();
//		Iterator<UrlSetting> iter = list.iterator();
//		UrlSetting dto = null;
//		while (iter.hasNext()) {
//			dto = iter.next();
//			propMap.put(dto.getUrlId(), dto.getUrlValue());
//		}
        //刷新工作做完，将标识设置回false
        this.propRefreshing.set(false);
    }

}
