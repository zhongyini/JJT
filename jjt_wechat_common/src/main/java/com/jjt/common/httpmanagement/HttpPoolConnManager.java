package com.jjt.common.httpmanagement;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
/**
 * Http connect management
 * @author YUCJ
 *
 */
public class HttpPoolConnManager {
	private static PoolingHttpClientConnectionManager connManager;
	
	private static void newInstance(){
		if(connManager == null){
			connManager = new PoolingHttpClientConnectionManager();
			//increase max total connection to 200
			connManager.setMaxTotal(200);
			//increase default max connection per route to 20
			connManager.setDefaultMaxPerRoute(20);
		}
	}
	
	public static CloseableHttpClient httpClientInstance(){
		newInstance();
		return HttpClients.custom().setConnectionManager(connManager).build();
	}
}
