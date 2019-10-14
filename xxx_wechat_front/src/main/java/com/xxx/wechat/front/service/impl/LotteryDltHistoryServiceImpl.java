package com.xxx.wechat.front.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xxx.wechat.common.utils.CheckUtils;
import com.xxx.wechat.common.utils.DateUtils;
import com.xxx.wechat.core.dao.LotteryDltGuessDao;
import com.xxx.wechat.core.dao.LotteryDltHistoryDao;
import com.xxx.wechat.core.dao.entity.LotteryDltGuess;
import com.xxx.wechat.core.dao.entity.LotteryDltHistory;
import com.xxx.wechat.core.dao.entity.extend.LotteryDltHistoryExt;
import com.xxx.wechat.core.exception.AppException;
import com.xxx.wechat.front.service.ILotteryDltHistoryService;

import tk.mybatis.mapper.entity.Example;

@Service
public class LotteryDltHistoryServiceImpl implements ILotteryDltHistoryService {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	LotteryDltHistoryDao lotteryDltHistoryDao;

	@Autowired
	LotteryDltGuessDao lotteryDltGuessDao;

	@Override
	public int add(LotteryDltHistory lotteryDltHistory) throws AppException {
		try {
			return lotteryDltHistoryDao.insert(lotteryDltHistory);
		} catch (AppException e) {
			logger.error("错误数据：" + lotteryDltHistory.toString());
			logger.error("错误信息：" + e.getMessage());
			throw new AppException("插入大乐透数据失败");
		}
	}

	@Override
	public List<String> getTermList() throws AppException {
		return lotteryDltHistoryDao.selectTermList();
	}

	@Override
	public int generateLotteryDltGuess() throws AppException {
		String[] fieldNameArray = { "red_one", "red_two", "red_three", "red_four", "red_five", "blue_one", "blue_two" };
		Integer[] redBallArray = new Integer[35];
		Integer[] blueBallArray = new Integer[12];
		Integer[] numberArray = new Integer[7];
		LotteryDltGuess lotteryDltGuess = null;
		List<LotteryDltHistoryExt> lotteryDltHistoryExtList = null;
		List<LotteryDltGuess> lotteryDltGuessList = new ArrayList<LotteryDltGuess>();
		try {
			int nextTerm = lotteryDltHistoryDao.selectMaxTerm() + 1;
			for (int initListIndex = 0; initListIndex < 5; initListIndex++) {
				lotteryDltGuess = initLotteryDltGuess(new LotteryDltGuess());
				lotteryDltGuess.setTerm(nextTerm);
				lotteryDltGuessList.add(lotteryDltGuess);
			}
			for (int i = 0; i < fieldNameArray.length; i++) {
				lotteryDltHistoryExtList = lotteryDltHistoryDao.selectFieldAndFieldCountByField(fieldNameArray[i]);
				int lotteryDltHistoryExtListLength = lotteryDltHistoryExtList.size();
				if (lotteryDltHistoryExtListLength > redBallArray.length) {
					throw new AppException("获取的数据长度大于35");
				}
				for (int j = 0; j < lotteryDltHistoryExtListLength; j++) {
					if (j < 5) {
						switch (i) {
						case 0:
							lotteryDltGuessList.get(j).setRedOne(lotteryDltHistoryExtList.get(j).getFieldValue());
							break;
						case 1:
							lotteryDltGuessList.get(j).setRedTwo(lotteryDltHistoryExtList.get(j).getFieldValue());
							break;
						case 2:
							lotteryDltGuessList.get(j).setRedThree(lotteryDltHistoryExtList.get(j).getFieldValue());
							break;
						case 3:
							lotteryDltGuessList.get(j).setRedFour(lotteryDltHistoryExtList.get(j).getFieldValue());
							break;
						case 4:
							lotteryDltGuessList.get(j).setRedFive(lotteryDltHistoryExtList.get(j).getFieldValue());
							break;
						case 5:
							lotteryDltGuessList.get(j).setBlueOne(lotteryDltHistoryExtList.get(j).getFieldValue());
							break;
						case 6:
							lotteryDltGuessList.get(j).setBlueTwo(lotteryDltHistoryExtList.get(j).getFieldValue());
							break;
						default:
							break;
						}
					}
					if (i < 5) {
						if (CheckUtils.isNull(redBallArray[lotteryDltHistoryExtList.get(j).getFieldValue() - 1])) {
							redBallArray[lotteryDltHistoryExtList.get(j).getFieldValue() - 1] = lotteryDltHistoryExtList
									.get(j).getFieldCount();
						} else {
							redBallArray[lotteryDltHistoryExtList.get(j).getFieldValue()
									- 1] += lotteryDltHistoryExtList.get(j).getFieldCount();
						}
					} else {
						if (CheckUtils.isNull(blueBallArray[lotteryDltHistoryExtList.get(j).getFieldValue() - 1])) {
							blueBallArray[lotteryDltHistoryExtList.get(j).getFieldValue()
									- 1] = lotteryDltHistoryExtList.get(j).getFieldCount();
						} else {
							blueBallArray[lotteryDltHistoryExtList.get(j).getFieldValue()
									- 1] += lotteryDltHistoryExtList.get(j).getFieldCount();
						}
					}
				}
			}
			for (LotteryDltGuess dltGuess : lotteryDltGuessList) {
				String numSequence = dltGuess.getRedOne() + "," + dltGuess.getRedTwo() + "," + dltGuess.getRedThree()
						+ "," + dltGuess.getRedFour() + "," + dltGuess.getRedFive() + "-" + dltGuess.getBlueOne() + ","
						+ dltGuess.getBlueTwo();
				String redNumber = numSequence.split("-")[0];
				String[] redNumberStrArray = redNumber.split(",");
				Integer[] redNumberArray = new Integer[redNumberStrArray.length];
				for (int k = 0; k < redNumberStrArray.length; k++) {
					redNumberArray[k] = Integer.valueOf(redNumberStrArray[k]);
				}
				Arrays.parallelSort(redNumberArray);
				String blueNumber = numSequence.split("-")[1];
				String[] blueNumberStrArray = blueNumber.split(",");
				Integer[] blueNumberArray = new Integer[blueNumberStrArray.length];
				for (int k = 0; k < blueNumberStrArray.length; k++) {
					blueNumberArray[k] = Integer.valueOf(blueNumberStrArray[k]);
				}
				Arrays.parallelSort(blueNumberArray);
				String number = JSONObject.toJSONString(redNumberArray) + "," + JSONObject.toJSONString(blueNumberArray);
				dltGuess.setNumSequence(numSequence);
				dltGuess.setNumber(number.replace("[", "").replace("]", ""));
			}
			int counts = 0;
			for (int i = 0; i < redBallArray.length - 1; i++) {
				if (counts > 4) {
					break;
				}
				int number = 0;
				for (int j = i + 1; j < redBallArray.length; j++) {
					if (redBallArray[i] < redBallArray[j]) {
						int temp = redBallArray[j];
						redBallArray[j] = redBallArray[i];
						redBallArray[i] = temp;
						number = i;
					}
				}
				if (number == 0) {
					number = redBallArray.length;
				}
				numberArray[counts] = number;
				counts += 1;
			}

			for (int i = 0; i < blueBallArray.length - 1; i++) {
				if (counts > 6) {
					break;
				}
				int number = 0;
				for (int j = i + 1; j < blueBallArray.length; j++) {
					if (blueBallArray[i] < blueBallArray[j]) {
						int temp = blueBallArray[j];
						redBallArray[j] = blueBallArray[i];
						redBallArray[i] = temp;
						number = i;
					}
				}
				if (number == 0) {
					number = blueBallArray.length;
				}
				numberArray[counts] = number;
				counts += 1;
			}

			lotteryDltGuess = initLotteryDltGuess(new LotteryDltGuess());
			lotteryDltGuess.setRedOne(numberArray[0]);
			lotteryDltGuess.setRedTwo(numberArray[1]);
			lotteryDltGuess.setRedThree(numberArray[2]);
			lotteryDltGuess.setRedFour(numberArray[3]);
			lotteryDltGuess.setRedFive(numberArray[4]);
			lotteryDltGuess.setBlueOne(numberArray[5]);
			lotteryDltGuess.setBlueTwo(numberArray[6]);
			
			lotteryDltGuess.setNumSequence(JSONObject.toJSONString(numberArray).replace("[", "").replace("]", ""));
			Integer[] redNumberArray = new Integer[5];
			Integer[] blueNumberArray = new Integer[2];
			System.arraycopy(numberArray, 0, redNumberArray, 0, 5);
			System.arraycopy(numberArray, 5, blueNumberArray, 0, 2);
			Arrays.parallelSort(redNumberArray);
			Arrays.parallelSort(blueNumberArray);
			String number = JSONObject.toJSONString(redNumberArray) + "," + JSONObject.toJSONString(blueNumberArray);
			lotteryDltGuess.setNumber(number.replace("[", "").replace("]", ""));
			lotteryDltGuess.setRedOne(numberArray[0]);
			lotteryDltGuess.setTerm(nextTerm);
			lotteryDltGuessList.add(lotteryDltGuess);
			return lotteryDltGuessDao.insertLotteryDltGuessList(lotteryDltGuessList);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new AppException("大乐透预测失败");
		}
	}

	private LotteryDltGuess initLotteryDltGuess(LotteryDltGuess lotteryDltGuess) {
		lotteryDltGuess.setDeleteFlag(0);
		lotteryDltGuess.setSeveralAward(0);
		lotteryDltGuess.setStatus(0);
		lotteryDltGuess.setUpdateTime(DateUtils.getNowTimestamp());
		lotteryDltGuess.setUpdateUser("sys");
		return lotteryDltGuess;
	}

	@Override
	public String getLastTerm() throws AppException {
		Example example = new Example(LotteryDltHistory.class);
		example.createCriteria().andEqualTo("deleteFlag", "0").andCondition("ORDER BY term DESC LIMIT 1");
		List<LotteryDltHistory> lotteryDltHistoryList = lotteryDltHistoryDao.selectByExample(example);
		if (CheckUtils.isNull(lotteryDltHistoryList) || lotteryDltHistoryList.size() == 0) {
			return null;
		}
		return lotteryDltHistoryList.get(0).getTerm();
	}
}
