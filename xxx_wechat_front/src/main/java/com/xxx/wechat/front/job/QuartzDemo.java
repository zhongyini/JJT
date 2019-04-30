package com.xxx.wechat.front.job;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xxx.wechat.common.constant.ConfigurationEnum;
import com.xxx.wechat.common.lottery.api.LotteryDltHistoryApi;
import com.xxx.wechat.common.lottery.api.response.LotteryDltHistoryResponse;
import com.xxx.wechat.common.lottery.api.response.LotteryDltHistoryTermResponse;
import com.xxx.wechat.common.utils.CheckUtils;
import com.xxx.wechat.common.utils.DateUtils;
import com.xxx.wechat.core.config.ConfigurationConfig;
import com.xxx.wechat.core.config.WechatTokenConfig;
import com.xxx.wechat.core.dao.entity.LotteryDltHistory;
import com.xxx.wechat.core.dao.entity.LotteryDltHistoryDetail;
import com.xxx.wechat.core.dao.entity.WechatToken;
import com.xxx.wechat.core.exception.AppException;
import com.xxx.wechat.core.service.IBaiduApiAccessTokenService;
import com.xxx.wechat.core.util.AppContextUtils;
import com.xxx.wechat.front.service.ILotteryDltHistoryDetailService;
import com.xxx.wechat.front.service.ILotteryDltHistoryService;

@Component
public class QuartzDemo {
	
	//日志
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Scheduled(cron = "0 0 0/1 * * ? ") // 每一小时执行一次
	public void goWork() throws Exception {
		logger.info("每一小时执行一次的定时任务：GET accesstoken begin" + new Date());
		for (int i = 0; i < 3; i++) {
			try {
				WechatToken wechatToken = WechatTokenConfig.getInstance().getWechatTokenOnline();
				if (!CheckUtils.isNull(wechatToken)) {
					logger.info("GET accesstoken over");
					return;
				}
			} catch (Exception e) {
				logger.error("Get WechatToken Error.次数：" + i + ",错误信息："+e.getMessage());
			}
		}
	}
	
	@Scheduled(cron = "0 0 0 */15 * ?") // 每天执行一次
	public void goBaiduAccessToken() {
		logger.info("执行定时任务，获取百度access token： accesstoken begin" + new Date());
		String appkey = ConfigurationConfig.getInstance().getProperty(ConfigurationEnum.BAIDU_APPKEY);
		String appsecret = ConfigurationConfig.getInstance().getProperty(ConfigurationEnum.BAIDU_APPSECRET);
		if (CheckUtils.isNullOrEmpty(appkey)) {
			appkey = "1BlRbyM9ZcQuvy7FbjazsGQw";
		}
		if (CheckUtils.isNullOrEmpty(appsecret)) {
			appsecret = "tG1f474lKkVoIBESkWGMXZwX22awRfpA";
		}
		for (int i = 0; i < 3; i++) {
			try {
				IBaiduApiAccessTokenService wechatTokenService = AppContextUtils.getBean(IBaiduApiAccessTokenService.class);
				boolean success = wechatTokenService.getAccessToken(appkey, appsecret);
				if (success) {
					logger.info("获取百度： access token success" + new Date());
				}
			} catch (AppException e) {
				logger.error("Get WechatToken Error.次数：" + i + ",错误信息："+e.getMessage());
			}
		}
	}
	
	//http://www.lottery.gov.cn/api/lottery_kj_detail_new.jspx?_ltype=4&_term=18153
	@Transactional
	@Scheduled(cron = "0 0 0 * * *") // 每天晚上零点执行一次
	public void getLotteryDltHistory() throws Exception {
		logger.info("获取大乐透数据定时任务：GET getLotteryDltHistory begin" + new Date());
		String lotteryDltTermUrl = "http://www.lottery.gov.cn/api/get_typeBytermAndnews.jspx?_ltype=4";
		String lotteryDltUrl = "http://www.lottery.gov.cn/api/lottery_kj_detail_new.jspx?_ltype=4";
		try {
			LotteryDltHistoryApi lotteryDltHistoryApi = new LotteryDltHistoryApi();
			LotteryDltHistoryTermResponse lotteryDltHistoryTermResponse = lotteryDltHistoryApi.getLotteryDltTerm(lotteryDltTermUrl);
			logger.info("请求路径：" + lotteryDltUrl);
			logger.info("返回结果：" + lotteryDltHistoryTermResponse);
			
			ILotteryDltHistoryService lotteryDltHistoryService = AppContextUtils.getBean(ILotteryDltHistoryService.class);
			// 历史期号
			List<LotteryDltHistoryTermResponse.TremList> termList = lotteryDltHistoryTermResponse.getTremList();
			if (CheckUtils.isNull(termList) || termList.size() == 0) {
				return;
			}
			List<String> newTermList = new ArrayList<String>();
			List<String> addTermList = new ArrayList<String>();
			for (LotteryDltHistoryTermResponse.TremList trem : termList) {
				newTermList.add(trem.getTerm());
			}
			if (!CheckUtils.isNull(newTermList) && newTermList.size() > 0) {
				Collections.sort(newTermList);
				// 数据库已保存的历史期号
				List<String> dbTermList = lotteryDltHistoryService.getTermList();
				if (CheckUtils.isNull(dbTermList)) {
					dbTermList = new ArrayList<String>();
				}
				int eventNameLength = newTermList.size();
				int dbTermListLength = dbTermList.size();
				if (eventNameLength > dbTermListLength) {
					for (int i = 0; i < eventNameLength; i++) {
						if (dbTermListLength == 0) {
							addTermList.add(newTermList.get(i));
						} else {
							for (int j = 0; j < dbTermListLength; j++) {
								if (dbTermList.get(j).equals(newTermList.get(i))) {
									dbTermList.remove(j);
									dbTermListLength--;
									j--;
									break;
								} else {
									addTermList.add(newTermList.get(i));
								}
							}	
						}
					}
				}
				
				String newLotteryDltUrl = null;
				LotteryDltHistoryResponse lotteryDltHistoryResponse = null;
				for (int k = 0; k < addTermList.size(); k++) {
					newLotteryDltUrl = lotteryDltUrl + "&_term=" + addTermList.get(k);
					lotteryDltHistoryResponse = lotteryDltHistoryApi.getLotteryDltHistory(newLotteryDltUrl);
					LotteryDltHistory lotteryDltHistory = convertLotteryDltHistory(lotteryDltHistoryResponse);
					int result = lotteryDltHistoryService.add(lotteryDltHistory);
					if (result == 1) {
						List<LotteryDltHistoryDetail> lotteryDltHistoryDetailist = convertLotteryDltHistoryDetail(lotteryDltHistoryResponse);
						ILotteryDltHistoryDetailService lotteryDltHistoryDetailService = AppContextUtils.getBean(ILotteryDltHistoryDetailService.class);
						result = lotteryDltHistoryDetailService.add(lotteryDltHistoryDetailist);
					}
				}
				logger.info("成功插入大乐透数据" + addTermList.size() + "条");
				
				int count = lotteryDltHistoryService.generateLotteryDltGuess();
				logger.info("成功生成了" + count + "条预测数据");
			}
			
		} catch (Exception e) {
			logger.error(e.getStackTrace().toString());
		}
	}
	
	private LotteryDltHistory convertLotteryDltHistory(LotteryDltHistoryResponse response) {
		LotteryDltHistory lotteryDltHistory = new LotteryDltHistory();
		String numSequence = response.getLottery().getNumSequence();
		// 早些年代的数据没有按照顺序排列
		if (!CheckUtils.isEmpty(numSequence) && numSequence.length() >= 20) {
			String[] codeNumber = new String[7];
			String[] ballArray = response.getLottery().getNumSequence().substring(0, 20).split("-");
			if (ballArray.length == 1) {
				codeNumber = ballArray[0].split(" ");
			} else if (ballArray.length == 2) {
				String[] redBallArray = ballArray[0].split(" ");
				String[] buleBallArray = ballArray[1].split(" ");
				codeNumber[0] = redBallArray[0];
				codeNumber[1] = redBallArray[1];
				codeNumber[2] = redBallArray[2];
				codeNumber[3] = redBallArray[3];
				codeNumber[4] = redBallArray[4];
				codeNumber[5] = buleBallArray[0];
				codeNumber[6] = buleBallArray[1];
				
			} else if (ballArray.length == 3) {
				String[] redBallArray = ballArray[0].split(" ");
				codeNumber[0] = redBallArray[0];
				codeNumber[1] = redBallArray[1];
				codeNumber[2] = redBallArray[2];
				codeNumber[3] = redBallArray[3];
				codeNumber[4] = redBallArray[4];
				codeNumber[5] = ballArray[1];
				codeNumber[6] = ballArray[2];
			}
			lotteryDltHistory.setRedOne(Integer.valueOf(codeNumber[0]));
			lotteryDltHistory.setRedTwo(Integer.valueOf(codeNumber[1]));
			lotteryDltHistory.setRedThree(Integer.valueOf(codeNumber[2]));
			lotteryDltHistory.setRedFour(Integer.valueOf(codeNumber[3]));
			lotteryDltHistory.setRedFive(Integer.valueOf(codeNumber[4]));
			lotteryDltHistory.setBlueOne(Integer.valueOf(codeNumber[5]));
			lotteryDltHistory.setBlueTwo(Integer.valueOf(codeNumber[6]));
		} else {
			lotteryDltHistory.setRedOne(Integer.valueOf(response.getCodeNumber()[0]));
			lotteryDltHistory.setRedTwo(Integer.valueOf(response.getCodeNumber()[1]));
			lotteryDltHistory.setRedThree(Integer.valueOf(response.getCodeNumber()[2]));
			lotteryDltHistory.setRedFour(Integer.valueOf(response.getCodeNumber()[3]));
			lotteryDltHistory.setRedFive(Integer.valueOf(response.getCodeNumber()[4]));
			lotteryDltHistory.setBlueOne(Integer.valueOf(response.getCodeNumber()[5]));
			lotteryDltHistory.setBlueTwo(Integer.valueOf(response.getCodeNumber()[6]));
		}
		
		lotteryDltHistory.setDeleteFlag(0);
		lotteryDltHistory.setDrawNews(response.getLottery().getDrawNews());
		lotteryDltHistory.setLotteryDate(DateUtils.getTimestamp(DateUtils.parse(response.getLottery().getfTime(), DateUtils.YYYYMMDDHHMMSS)));
		lotteryDltHistory.setNumber(response.getLottery().getNumber());
		lotteryDltHistory.setNumSequence(response.getLottery().getNumSequence());
		lotteryDltHistory.setTerm(Integer.valueOf(response.getLottery().getTerm()));
		lotteryDltHistory.setUpdateTime(DateUtils.getNowTimestamp());
		lotteryDltHistory.setUpdateUser("job");
		return lotteryDltHistory;
	}
	
	private List<LotteryDltHistoryDetail> convertLotteryDltHistoryDetail(LotteryDltHistoryResponse response) {
		List<LotteryDltHistoryDetail> lotteryDltHistoryDetailList = new ArrayList<LotteryDltHistoryDetail>();
		List<LotteryDltHistoryResponse.Details> detials = response.getDetails();
		LotteryDltHistoryDetail lotteryDltHistoryDetail = null;
		for (LotteryDltHistoryResponse.Details detaild : detials) {
			lotteryDltHistoryDetail = new LotteryDltHistoryDetail();
			lotteryDltHistoryDetail.setAllmoney(detaild.getAllmoney());
			lotteryDltHistoryDetail.setDeleteFlag(0);
			lotteryDltHistoryDetail.setLevel(detaild.getLevel());
			lotteryDltHistoryDetail.setTerm(response.getLottery().getTerm());
			lotteryDltHistoryDetail.setMoney(detaild.getMoney());
			lotteryDltHistoryDetail.setNum(Integer.valueOf(detaild.getNum()));
			lotteryDltHistoryDetail.setPiece(detaild.getPiece());
			lotteryDltHistoryDetail.setSendPrize(detaild.getSendPrize());
			lotteryDltHistoryDetailList.add(lotteryDltHistoryDetail);
		}
		return lotteryDltHistoryDetailList;
	}

}
