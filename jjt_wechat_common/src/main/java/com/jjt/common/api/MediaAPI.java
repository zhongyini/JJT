package com.jjt.common.api;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.jjt.common.api.enums.MediaType;
import com.jjt.common.api.response.BaseResponse;
import com.jjt.common.api.response.DownloadMediaResponse;
import com.jjt.common.api.response.UploadImageMaterialResponse;
import com.jjt.common.api.response.UploadImgResponse;
import com.jjt.common.api.response.UploadMediaResponse;
import com.jjt.common.entity.message.resp.Article;
import com.jjt.common.utils.BeanUtils;
import com.jjt.common.utils.JSONUtil;
import com.jjt.common.utils.NetWorkCenter;
import com.jjt.common.utils.StreamUtil;

/**
 * 多媒体资源API
 *
 */
public class MediaAPI extends BaseAPI {

	private static final Logger LOG = LoggerFactory.getLogger(MediaAPI.class);

	public MediaAPI(String accessToken) {
		super(accessToken);
	}

	/**
	 * 上传资源，会在微信服务器上保存3天，之后会被删除
	 *
	 * @param type
	 *            资源类型
	 * @param file
	 *            需要上传的文件
	 * @return 响应对象
	 */
	public UploadMediaResponse uploadMedia(MediaType type, File file) {
		UploadMediaResponse response;
		//修改http为https at 20171027 by yangk
		String url = "https://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=#&type="
				+ type.toString();
		BaseResponse r = executePost(url, null, file);
		response = JSONUtil.toBean(r.getErrmsg(), UploadMediaResponse.class);
		return response;
	}

	/**
	 * 上传群发文章素材。
	 *
	 * @param articles
	 *            上传的文章信息
	 * @return 响应对象
	 */
	public UploadMediaResponse uploadNews(List<Article> articles) {
		UploadMediaResponse response;
		String url = BASE_API_URL + "cgi-bin/media/uploadnews?access_token=#";
		final Map<String, Object> params = new HashMap<String, Object>();
		params.put("articles", articles);
		BaseResponse r = executePost(url, JSONUtil.toJson(params));
		response = JSONUtil.toBean(r.getErrmsg(), UploadMediaResponse.class);
		return response;
	}

	/**
	 * 上传群发消息图片素材
	 * 
	 * @param file
	 *            上传的图片
	 * @return 上传结果
	 */
	public UploadImgResponse uploadImg(File file) {
		UploadImgResponse response;
		String url = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=#";
		BaseResponse r = executePost(url, null, file);
		response = JSONUtil.toBean(r.getErrmsg(), UploadImgResponse.class);
		return response;
	}

	/**
	 * 上传图片素材
	 * 
	 * @param file
	 *            上传的图片
	 * @return 上传结果
	 */
	public UploadImageMaterialResponse uploadImageMaterial(MediaType type,
			File file) {
		UploadImageMaterialResponse response = null;
		String uploadMediaUrl = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=#&type="
				+ type.toString();
		OutputStream out = null;
		DataInputStream in = null;
		try {
			// 拼装请求地址
			URL url = new URL(uploadMediaUrl.replace("#", accessToken));
			String result = null;
			if (!file.exists() || !file.isFile()) {
				BeanUtils.requireNonNull(file, "file is null");
			}
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setUseCaches(false); // post方式不能使用缓存
			// 设置请求头信息
			con.setRequestProperty("Connection", "Keep-Alive");
			con.setRequestProperty("Charset", "UTF-8");
			// 设置边界
			String BOUNDARY = "----------" + System.currentTimeMillis();
			con.setRequestProperty("Content-Type",
					"multipart/form-data; boundary=" + BOUNDARY);
			// 请求正文信息
			// 第一部分：
			StringBuilder sb = new StringBuilder();
			sb.append("--"); // 必须多两道线
			sb.append(BOUNDARY);
			sb.append("\r\n");
			sb.append("Content-Disposition: form-data;name=\"media\";filename=\""
					+ file.getName() + "\" \r\n");
			sb.append("Content-Type:application/octet-stream\r\n\r\n");
			byte[] head = sb.toString().getBytes("utf-8");
			// 获得输出流
			out = new DataOutputStream(con.getOutputStream());
			// 输出表头
			out.write(head);
			// 文件正文部分
			// 把文件已流文件的方式 推入到url中
			in = new DataInputStream(new FileInputStream(file));
			int bytes = 0;
			byte[] bufferOut = new byte[1024];
			while ((bytes = in.read(bufferOut)) != -1) {
				out.write(bufferOut, 0, bytes);
			}
			in.close();
			// 结尾部分
			byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
			out.write(foot);
			out.flush();
			out.close();
			StringBuffer buffer = new StringBuffer();
			BufferedReader reader = null;
			// 定义BufferedReader输入流来读取URL的响应
			reader = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			if (result == null) {
				result = buffer.toString();
			}
			// 使用JSON-lib解析返回结果
			response = (UploadImageMaterialResponse) JSONObject.parseObject(
					result, UploadImageMaterialResponse.class);

		} catch (IOException e) {
			System.out.println("发送POST请求出现异常！" + e);
			e.printStackTrace();

		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (Exception e2) {
					out = null;
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (Exception e2) {
					in = null;
				}
			}
		}
		return response;
	}

//	public static void main(String[] args) {
//		MediaAPI api = new MediaAPI(
//				"Ukjrb-On9ydTvyfAd8xR5Bot-67nQyNrbMnJloFDfqgSD04aagf19nQNudPQjzw9t0X1o9fHbVlaGMXlvF573sBE-QTGEkTa2y6L-tszG64KuXHZBTp55vGu6ghazy7dOKKeADAAWW");
//
//		File file = new File(
//				"E:\\source\\04_服务号\\040_Source\\qiaohu_wechat_front\\src\\main\\resources\\static\\img\\qiaohu.png");
//		api.uploadImageMaterial(MediaType.IMAGE, file);
//
//	}

	/**
	 * 下载资源，实现的很不好，反正能用了。搞的晕了，之后会优化
	 *
	 * @param mediaId
	 *            微信提供的资源唯一标识
	 * @return 响应对象
	 */
	public DownloadMediaResponse downloadMedia(String mediaId) {
		DownloadMediaResponse response = new DownloadMediaResponse();
		//修改http为https at 20171027 by yangk
		String url = "https://file.api.weixin.qq.com/cgi-bin/media/get?access_token="
				+ accessToken + "&media_id=" + mediaId;
		RequestConfig config = RequestConfig.custom()
				.setConnectionRequestTimeout(NetWorkCenter.CONNECT_TIMEOUT)
				.setConnectTimeout(NetWorkCenter.CONNECT_TIMEOUT)
				.setSocketTimeout(NetWorkCenter.CONNECT_TIMEOUT).build();
		CloseableHttpClient client = HttpClientBuilder.create()
				.setDefaultRequestConfig(config).build();
		HttpGet get = new HttpGet(url);
		try {
			CloseableHttpResponse r = client.execute(get);
			if (HttpStatus.SC_OK == r.getStatusLine().getStatusCode()) {
				InputStream inputStream = r.getEntity().getContent();
				Header[] headers = r.getHeaders("Content-disposition");
				if (null != headers && 0 != headers.length) {
					Header length = r.getHeaders("Content-Length")[0];
					response.setContent(inputStream,
							Integer.valueOf(length.getValue()));
					response.setFileName(headers[0].getElements()[0]
							.getParameterByName("filename").getValue());
				} else {
					ByteArrayOutputStream out = new ByteArrayOutputStream();
					StreamUtil.copy(inputStream, out);
					String json = out.toString();
					response = JSONUtil.toBean(json,
							DownloadMediaResponse.class);
				}
			}
		} catch (IOException e) {
			LOG.error("IO处理异常", e);
		} finally {
			try {
				client.close();
			} catch (IOException e) {
				LOG.error("异常", e);
			}
		}
		return response;
	}

}
