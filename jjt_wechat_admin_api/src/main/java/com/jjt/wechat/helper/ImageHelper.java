package com.jjt.wechat.helper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.jjt.common.api.MaterialAPI;
import com.jjt.common.utils.CheckUtils;
import com.jjt.wechat.config.AppConfig;
import com.jjt.wechat.core.config.ApiConfig;

@Component
public class ImageHelper {

	private static final Logger logger = LoggerFactory
			.getLogger(ImageHelper.class);
	
	@Autowired
	AppConfig appConfig;
	
	@Value("${material.news.image.save.path}")
	private String imageSavePath;
	
	/**
	 * 
	 * @param imageUrl :相对路径--例如：/var/jjt/data/material/news
	 * @param imageName 文件名不带文件后缀
	 * @param path 保存文件的文件夹名称
	 * @return
	 */
	public String saveImage(String imageUrl, String imageName, String path) {

		URL url = null;

		HttpURLConnection conn = null;
		byte[] data = null;
		InputStream inStream = null;
		File imageFile = null;
		File targetFile = null;
		FileOutputStream outStream = null;
		try {

			// new一个URL对象
			url = new URL(imageUrl);
			// 打开链接
			//***************************取消日志打印:影响管理后台图片显示加载速度  by liubo 2017-12-25****************//
			//logger.info("保存图片路径：" + imageUrl);
			conn = (HttpURLConnection) url.openConnection();
			// 设置请求方式为"GET"
			conn.setRequestMethod("GET");
			// 超时响应时间为10秒
			conn.setConnectTimeout(10 * 1000);
			// 通过输入流获取图片数据
			inStream = conn.getInputStream();
			// 得到图片的二进制数据，以二进制封装得到数据，具有通用性
			data = readInputStream(inStream);
			targetFile = new File(imageSavePath + File.separator + path);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			String prefix = imageUrl.substring(imageUrl.lastIndexOf("="))
					.replace("=", ".");
			if (CheckUtils.isNullOrEmpty(prefix)) {
				prefix = ".jpeg";
			}
			// new一个文件对象用来保存图片，默认保存当前工程根目录
			imageFile = new File(imageSavePath + File.separator + path
					+ File.separator + imageName + prefix);
			// 如果原图片存在，删除
			if (imageFile.exists()) {
				return path + File.separator + imageName + prefix;
			}
			// 创建输出流
			outStream = new FileOutputStream(imageFile);
			// 写入数据
			outStream.write(data);
			// 关闭输出流
			outStream.close();

			return path + File.separator + imageName + prefix;
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error("upload image file error", e);
			return imageUrl;
			// throw new AppException(e);
		} finally {
			if (outStream != null) {
				try {
					outStream.close();
				} catch (IOException e) {
					outStream = null;
				}
			}
			if (inStream != null) {
				try {
					inStream.close();
				} catch (IOException e) {
					inStream = null;
				}
			}
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}

			imageFile = null;
			data = null;
		}

	}

	public static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		// 创建一个Buffer字符串
		byte[] buffer = new byte[1024];
		// 每次读取的字符串长度，如果为-1，代表全部读取完毕
		int len = 0;
		// 使用一个输入流从buffer里把数据读取出来
		while ((len = inStream.read(buffer)) != -1) {
			// 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
			outStream.write(buffer, 0, len);
		}
		// 关闭输入流
		inStream.close();
		// 把outStream里的数据写入内存
		return outStream.toByteArray();
	}
	
	public MaterialAPI getMaterialAPI() {
		//获取次图文
		String accessToken = null;
		MaterialAPI materialAPI = null;
		accessToken = ApiConfig.getInstance().getAccessToken();
		if (CheckUtils.isNullOrEmpty(accessToken)) {
			logger.error("accessToken is null or empty.");
			//return new RestResult(messageHelper.mesg_error_0001);
		}
		// 初始化获取图文消息接口
		materialAPI = new MaterialAPI(accessToken);
		
		return materialAPI;
	}
}
