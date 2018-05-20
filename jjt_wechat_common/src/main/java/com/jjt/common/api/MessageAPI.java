package com.jjt.common.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jjt.common.api.enums.ResultType;
import com.jjt.common.api.response.BaseResponse;
import com.jjt.common.api.response.GetSendMessageResponse;
import com.jjt.common.entity.message.resp.Article;
import com.jjt.common.entity.message.resp.BaseMsg;
import com.jjt.common.entity.message.resp.ImageMsg;
import com.jjt.common.entity.message.resp.MpNewsMsg;
import com.jjt.common.entity.message.resp.MusicMsg;
import com.jjt.common.entity.message.resp.NewsMsg;
import com.jjt.common.entity.message.resp.TextMsg;
import com.jjt.common.entity.message.resp.VideoMsg;
import com.jjt.common.entity.message.resp.VoiceMsg;
import com.jjt.common.utils.BeanUtils;
import com.jjt.common.utils.JSONUtil;

/**
 * 消息相关API
 *
 */
public class MessageAPI extends BaseAPI {

	private static final Logger LOG = LoggerFactory.getLogger(MessageAPI.class);

	// public MessageAPI(ApiConfig config) {
	// super(config);
	// }
	public MessageAPI(String accessToken) {
		super(accessToken);
	}

	public GetSendMessageResponse getMessageIdResponse(String messgeId){
		BeanUtils.requireNonNull(messgeId, "messgeId is null");
		String url = BASE_API_URL + "cgi-bin/message/mass/get?access_token=#";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("msg_id", messgeId);
		BaseResponse response = executePost(url, JSONUtil.toJson(params));
		String resultJson = isSuccess(response.getErrcode()) ? response.getErrmsg() : response.toJsonString();
		return JSONUtil.toBean(resultJson, GetSendMessageResponse.class);
	}
	/**
	 * 根据openid发送模版消息
	 * @param message
	 * @param openIds
	 * @param SendIgnoreReprint
	 * @return
	 */
	public GetSendMessageResponse massMessageResponse(BaseMsg message, List<String> openIdList, Integer SendIgnoreReprint) {
		String[] openIds = openIdList.toArray(new String[openIdList.size()]);
		LOG.info("要推送的用户OPEN_ID"+openIds.length);
		BeanUtils.requireNonNull(message, "message is null");
		String url = BASE_API_URL + "cgi-bin/message/mass/send?access_token=#";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("touser", openIds);
		if (message instanceof MpNewsMsg) {
			MpNewsMsg msg = (MpNewsMsg) message;
			//media_id
			Map<String, Object> mpNews = new HashMap<String, Object>();
			mpNews.put("media_id", msg.getMediaId());
			params.put("mpnews", mpNews);
			LOG.info("图文发送");
			params.put("msgtype", "mpnews");
			params.put("send_ignore_reprint", SendIgnoreReprint);
		}else if (message instanceof TextMsg) {
			params.put("msgtype", "text");
			TextMsg msg = (TextMsg) message;
			Map<String, Object> text = new HashMap<String, Object>();
			text.put("content", msg.getContent());
			params.put("text", text);
		}
		BaseResponse response = executePost(url, JSONUtil.toJson(params));
		String resultJson = isSuccess(response.getErrcode()) ? response.getErrmsg() : response.toJsonString();
		return JSONUtil.toBean(resultJson, GetSendMessageResponse.class);
	}

	/**
	 * 群发消息给用户。 本方法调用需要账户为微信已认证账户
	 * 
	 * @param message
	 *            消息主体
	 * @param isToAll
	 *            是否发送给全部用户。false时需要填写groupId，true时可忽略groupId树形
	 * @param groupId
	 *            群组ID
	 * @param openIds
	 *            群发用户
	 * @return 群发结果
	 * @deprecated 微信不再建议使用群组概念,用标签代替
	 */
	@Deprecated
	public GetSendMessageResponse sendMessageToUser(BaseMsg message, boolean isToAll, String groupId,
			String[] openIds) {
		BeanUtils.requireNonNull(message, "message is null");
		LOG.debug("群发消息......");
		String url = BASE_API_URL + "cgi-bin/message/mass/sendall?access_token=#";
		final Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("is_to_all", isToAll);
		if (!isToAll) {
			BeanUtils.requireNonNull(groupId, "groupId is null");
			filterMap.put("group_id", groupId);
		}
		params.put("filter", filterMap);
		if (message instanceof MpNewsMsg) {
			params.put("msgtype", "mpnews");
			MpNewsMsg msg = (MpNewsMsg) message;
			Map<String, Object> mpNews = new HashMap<String, Object>();
			mpNews.put("media_id", msg.getMediaId());
			params.put("mpnews", mpNews);
		} else if (message instanceof TextMsg) {
			params.put("msgtype", "text");
			TextMsg msg = (TextMsg) message;
			Map<String, Object> text = new HashMap<String, Object>();
			text.put("content", msg.getContent());
			params.put("text", text);
		} else if (message instanceof VoiceMsg) {
			params.put("msgtype", "voice");
			VoiceMsg msg = (VoiceMsg) message;
			Map<String, Object> voice = new HashMap<String, Object>();
			voice.put("media_id", msg.getMediaId());
			params.put("voice", voice);
		} else if (message instanceof ImageMsg) {
			params.put("msgtype", "image");
			ImageMsg msg = (ImageMsg) message;
			Map<String, Object> image = new HashMap<String, Object>();
			image.put("media_id", msg.getMediaId());
			params.put("image", image);
		} else if (message instanceof VideoMsg) {
			// TODO 此处方法特别
		}
		BaseResponse response = executePost(url, JSONUtil.toJson(params));
		String resultJson = isSuccess(response.getErrcode()) ? response.getErrmsg() : response.toJsonString();
		return JSONUtil.toBean(resultJson, GetSendMessageResponse.class);
	}

	/**
	 * 群发消息给用户。 本方法调用需要账户为微信已认证账户
	 * 
	 * @param message
	 *            消息主体
	 * @param isToAll
	 *            是否发送给全部用户。false时需要填写tagId，true时可忽略tagId树形
	 * @param tagId
	 *            标签ID
	 * @return 群发结果
	 */
	public GetSendMessageResponse sendMessageToUser(BaseMsg message, boolean isToAll, Integer tagId) {
		BeanUtils.requireNonNull(message, "message is null");
		LOG.debug("群发消息......");
		String url = BASE_API_URL + "cgi-bin/message/mass/sendall?access_token=#";
		final Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("is_to_all", isToAll);
		if (!isToAll) {
			BeanUtils.requireNonNull(tagId, "tagId is null");
			filterMap.put("tag_id", tagId);
		}
		params.put("filter", filterMap);
		if (message instanceof MpNewsMsg) {
			params.put("msgtype", "mpnews");
			MpNewsMsg msg = (MpNewsMsg) message;
			Map<String, Object> mpNews = new HashMap<String, Object>();
			mpNews.put("media_id", msg.getMediaId());
			params.put("mpnews", mpNews);
		} else if (message instanceof TextMsg) {
			params.put("msgtype", "text");
			TextMsg msg = (TextMsg) message;
			Map<String, Object> text = new HashMap<String, Object>();
			text.put("content", msg.getContent());
			params.put("text", text);
		} else if (message instanceof VoiceMsg) {
			params.put("msgtype", "voice");
			VoiceMsg msg = (VoiceMsg) message;
			Map<String, Object> voice = new HashMap<String, Object>();
			voice.put("media_id", msg.getMediaId());
			params.put("voice", voice);
		} else if (message instanceof ImageMsg) {
			params.put("msgtype", "image");
			ImageMsg msg = (ImageMsg) message;
			Map<String, Object> image = new HashMap<String, Object>();
			image.put("media_id", msg.getMediaId());
			params.put("image", image);
		} else if (message instanceof VideoMsg) {
			// TODO 此处方法特别
		}
		BaseResponse response = executePost(url, JSONUtil.toJson(params));
		String resultJson = isSuccess(response.getErrcode()) ? response.getErrmsg() : response.toJsonString();
		return JSONUtil.toBean(resultJson, GetSendMessageResponse.class);
	}

	/**
	 * 发布客服消息
	 *
	 * @param openid
	 *            关注者ID
	 * @param message
	 *            消息对象，支持各种消息类型
	 * @return 调用结果
	 * @deprecated 此方法已经不再建议使用，使用CustomAPI中方法代替
	 */
	@Deprecated
	public ResultType sendCustomMessage(String openid, BaseMsg message) {
		BeanUtils.requireNonNull(openid, "openid is null");
		BeanUtils.requireNonNull(message, "message is null");
		LOG.debug("发布客服消息......");
		String url = BASE_API_URL + "cgi-bin/message/custom/send?access_token=#";
		final Map<String, Object> params = new HashMap<String, Object>();
		params.put("touser", openid);
		if (message instanceof TextMsg) {
			TextMsg msg = (TextMsg) message;
			params.put("msgtype", "text");
			Map<String, String> text = new HashMap<String, String>();
			text.put("content", msg.getContent());
			params.put("text", text);
		} else if (message instanceof ImageMsg) {
			ImageMsg msg = (ImageMsg) message;
			params.put("msgtype", "image");
			Map<String, String> image = new HashMap<String, String>();
			image.put("media_id", msg.getMediaId());
			params.put("image", image);
		} else if (message instanceof VoiceMsg) {
			VoiceMsg msg = (VoiceMsg) message;
			params.put("msgtype", "voice");
			Map<String, String> voice = new HashMap<String, String>();
			voice.put("media_id", msg.getMediaId());
			params.put("voice", voice);
		} else if (message instanceof VideoMsg) {
			VideoMsg msg = (VideoMsg) message;
			params.put("msgtype", "video");
			Map<String, String> video = new HashMap<String, String>();
			video.put("media_id", msg.getMediaId());
			video.put("thumb_media_id", msg.getMediaId());
			video.put("title", msg.getTitle());
			video.put("description", msg.getDescription());
			params.put("video", video);
		} else if (message instanceof MusicMsg) {
			MusicMsg msg = (MusicMsg) message;
			params.put("msgtype", "music");
			Map<String, String> music = new HashMap<String, String>();
			music.put("thumb_media_id", msg.getThumbMediaId());
			music.put("title", msg.getTitle());
			music.put("description", msg.getDescription());
			music.put("musicurl", msg.getMusicUrl());
			music.put("hqmusicurl", msg.getHqMusicUrl());
			params.put("music", music);
		} else if (message instanceof NewsMsg) {
			NewsMsg msg = (NewsMsg) message;
			params.put("msgtype", "news");
			Map<String, Object> news = new HashMap<String, Object>();
			List<Object> articles = new ArrayList<Object>();
			List<Article> list = msg.getArticles();
			for (Article article : list) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("title", article.getTitle());
				map.put("description", article.getDescription());
				map.put("url", article.getUrl());
				map.put("picurl", article.getPicUrl());
				articles.add(map);
			}
			news.put("articles", articles);
			params.put("news", news);
		}
		BaseResponse response = executePost(url, JSONUtil.toJson(params));
		return ResultType.get(response.getErrcode());
	}
}
