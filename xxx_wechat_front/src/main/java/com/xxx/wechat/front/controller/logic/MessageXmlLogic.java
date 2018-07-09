package com.xxx.wechat.front.controller.logic;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageXmlLogic extends BaseLogic {

	protected final static Logger logger = LoggerFactory.getLogger(MessageXmlLogic.class);

	private final static String SEND_PICS_INFO = "SendPicsInfo";
	private final static String SEND_LOCATION_INFO = "SendLocationInfo";
	private final static String SCAN_CODE_INFO = "ScanCodeInfo";
	private final static String XML = "xml";
	private final static String COUNT = "Count";
	private final static String PIC_LIST = "PicList";
	private final static String PIC_MD5_SUM = "PicMd5Sum";

	/**
	 * 解析从微信服务器来的请求，将消息或者事件返回出去
	 *
	 * @param request
	 *            http请求对象
	 * @param token
	 *            用户设置的taken
	 * @param appId
	 *            公众号的APPID
	 * @param aesKey
	 *            用户设置的加密密钥
	 * @return 微信消息或者事件Map
	 */
	public static Map<String, Object> parseXml(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();

		InputStream inputStream = null;
		try {
			inputStream = request.getInputStream();
			XMLInputFactory factory = XMLInputFactory.newInstance();
			XMLEventReader reader = factory.createXMLEventReader(inputStream);
			while (reader.hasNext()) {
				XMLEvent event = reader.nextEvent();
				if (event.isStartElement()) {
					String tagName = event.asStartElement().getName().toString();
					if (SEND_PICS_INFO.equals(tagName)) {
						map.put(tagName, eventSendPicsInfo(reader));
					} else if (SEND_LOCATION_INFO.equals(tagName)) {
						map.put(tagName, eventSendLocationInfo(reader));
					} else if (SCAN_CODE_INFO.equals(tagName)) {
						map.put(tagName, eventScanCodePush(reader));
					} else if (XML.equals(tagName)) {
					} else {
						map.put(tagName, reader.getElementText());
					}
				}
			}
		} catch (IOException e) {
			logger.error("IO出现异常", e);
		} catch (XMLStreamException e) {
			logger.error("XML解析出现异常", e);
		} catch (Exception e) {
			logger.error("其他异常", e);
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				logger.error("IO出现异常", e);
			}
		}
		return map;
	}

	/**
	 * Event为pic_sysphoto, pic_photo_or_album, pic_weixin时触发
	 * 
	 * @param reader
	 *            reader
	 * @return 读取结果
	 * @throws XMLStreamException
	 *             XML解析异常
	 */
	private static Map<String, Object> eventSendPicsInfo(XMLEventReader reader) throws XMLStreamException {
		Map<String, Object> sendPicsInfoMap = new HashMap<String, Object>();
		while (reader.hasNext()) {
			XMLEvent event = reader.nextEvent();
			if (event.isStartElement()) {
				String tagName = event.asStartElement().getName().toString();
				if (COUNT.equals(tagName)) {
					sendPicsInfoMap.put(tagName, reader.getElementText());
				} else if (PIC_LIST.equals(tagName)) {
					// StringBuilder sb = new StringBuilder();
					List<Map<String, String>> picList = new ArrayList<>();
					while (reader.hasNext()) {
						XMLEvent event1 = reader.nextEvent();
						if (event1.isStartElement()
								&& PIC_MD5_SUM.equals(event1.asStartElement().getName().toString())) {
							Map<String, String> picMap = new HashMap<String, String>();
							picMap.put(PIC_MD5_SUM, reader.getElementText());
							// sb.append(reader.getElementText());
							// sb.append(",");
							picList.add(picMap);
						} else if (event1.isEndElement()
								&& PIC_LIST.equals(event1.asEndElement().getName().toString())) {
							break;
						}
					}
					// sendPicsInfoMap.put(tagName, sb.substring(0,
					// sb.length()));
					sendPicsInfoMap.put(tagName, picList);
				}
			}
		}

		return sendPicsInfoMap;
	}

	/**
	 * Event为location_select时触发
	 * 
	 * @param reader
	 *            reader
	 * @return 读取结果
	 * @throws XMLStreamException
	 *             XML解析异常
	 */
	private static Map<String, Object> eventSendLocationInfo(XMLEventReader reader) throws XMLStreamException {
		Map<String, Object> sendLocationInfo = new HashMap<String, Object>();
		while (reader.hasNext()) {
			XMLEvent event = reader.nextEvent();
			if (event.isStartElement()) {
				String tagName = event.asStartElement().getName().toString();
				sendLocationInfo.put(tagName, reader.getElementText());
			}
		}
		return sendLocationInfo;
	}

	/**
	 * Event为scancode_push, scancode_waitmsg时触发
	 * 
	 * @param reader
	 *            reader
	 * @return 读取结果
	 * @throws XMLStreamException
	 *             XML解析异常
	 */
	private static Map<String, Object> eventScanCodePush(XMLEventReader reader) throws XMLStreamException {
		Map<String, Object> scanCodePush = new HashMap<String, Object>();
		while (reader.hasNext()) {
			XMLEvent event = reader.nextEvent();
			if (event.isStartElement()) {
				String tagName = event.asStartElement().getName().toString();
				scanCodePush.put(tagName, reader.getElementText());
			}
		}
		return scanCodePush;
	}

}
