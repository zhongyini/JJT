package com.xxx.wechat.front.job;

import java.util.ArrayList;
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
	
	@Scheduled(cron = "0 0 0 */1 * ?") // 每一小时执行一次
	public void goBaiduAccessToken() {
		logger.info("执行定时任务，获取百度accesstoken：GET accesstoken begin" + new Date());
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
					return;
				}
			} catch (AppException e) {
				logger.error("Get WechatToken Error.次数：" + i + ",错误信息："+e.getMessage());
			}
		}
	}
	
	@Transactional
	@Scheduled(cron = "0 0/1 * * * ? ") // 五分钟执行一次
	public void getLotteryDltHistory() throws Exception {
		logger.info("五分钟执行一次的定时任务：GET getLotteryDltHistory begin" + new Date());
		String lotteryDltUrl = "http://www.lottery.gov.cn/api/lottery_kj_detail_new.jspx?_ltype=4";
		try {
			LotteryDltHistoryApi lotteryDltHistoryApi = new LotteryDltHistoryApi();
			LotteryDltHistoryResponse response = lotteryDltHistoryApi.getLotteryDltHistory();
			logger.info("请求路径：" + lotteryDltUrl);
			logger.info("返回结果：" + response);
			
			LotteryDltHistory lotteryDltHistory = convertLotteryDltHistory(response);
			
			ILotteryDltHistoryService lotteryDltHistoryService = AppContextUtils.getBean(ILotteryDltHistoryService.class);
			
			int result = lotteryDltHistoryService.add(lotteryDltHistory);
			if (result == 1) {
				logger.info("插入大乐透数据成功");
				List<LotteryDltHistoryDetail> lotteryDltHistoryDetailist = convertLotteryDltHistoryDetail(response);
				ILotteryDltHistoryDetailService lotteryDltHistoryDetailService = AppContextUtils.getBean(ILotteryDltHistoryDetailService.class);
				result = lotteryDltHistoryDetailService.add(lotteryDltHistoryDetailist);
				if (result >= 1) {
					logger.info(String.valueOf(result));
					logger.info("插入大乐透详细数据成功");
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	private LotteryDltHistory convertLotteryDltHistory(LotteryDltHistoryResponse response) {
		LotteryDltHistory lotteryDltHistory = new LotteryDltHistory();
		String[] ballArray = response.getLottery().getNumSequence().substring(0, 20).split("-");
		String[] redBallArray = ballArray[0].split(" ");
		String[] buleBallArray = ballArray[1].split(" ");
		lotteryDltHistory.setBlueOne(Integer.valueOf(buleBallArray[0]));
		lotteryDltHistory.setBlueTwo(Integer.valueOf(buleBallArray[1]));
		lotteryDltHistory.setRedOne(Integer.valueOf(redBallArray[0]));
		lotteryDltHistory.setRedTwo(Integer.valueOf(redBallArray[1]));
		lotteryDltHistory.setRedThree(Integer.valueOf(redBallArray[2]));
		lotteryDltHistory.setRedFour(Integer.valueOf(redBallArray[3]));
		lotteryDltHistory.setRedFive(Integer.valueOf(redBallArray[4]));
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
			lotteryDltHistoryDetail.setLotteryTerm(response.getLottery().getTerm());
			lotteryDltHistoryDetail.setMoney(detaild.getMoney());
			lotteryDltHistoryDetail.setNum(Integer.valueOf(detaild.getNum()));
			lotteryDltHistoryDetail.setPiece(detaild.getPiece());
			lotteryDltHistoryDetail.setSendPrize(detaild.getSendPrize());
			lotteryDltHistoryDetailList.add(lotteryDltHistoryDetail);
		}
		return lotteryDltHistoryDetailList;
	}

}
