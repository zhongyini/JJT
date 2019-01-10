package com.xxx.wechat.front.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.xxx.wechat.core.dao.WechatPayApiLogDao;
import com.xxx.wechat.core.dao.WechatPaymentDao;
import com.xxx.wechat.core.dao.WechatUserAccountDao;
import com.xxx.wechat.core.dao.entity.WechatPayApiLog;
import com.xxx.wechat.core.dao.entity.WechatPayment;
import com.xxx.wechat.core.dao.entity.WechatUserAccount;
import com.xxx.wechat.front.dto.PaymentResultDto;
import com.xxx.wechat.front.service.IWechatPaymentService;
import com.xxx.wechat.front.vo.ResultVo;
import com.xxx.wechat.helper.PaymentHelper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;
import java.util.Map;

import static com.xxx.wechat.common.constant.Constant.*;
import static com.xxx.wechat.common.utils.HttpClientUtil.ResponseResult;
import static com.xxx.wechat.common.utils.HttpClientUtil.sendJsonPost;

@Service
public class WechatPaymentServiceImpl implements IWechatPaymentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WechatPaymentServiceImpl.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private DataSourceTransactionManager transactionManager;
    @Autowired
    private PaymentHelper paymentHelper;
    @Autowired
    private WechatUserAccountDao wechatUserAccountDao;
    @Autowired
    private WechatPaymentDao wechatPaymentDao;
    @Autowired
    private WechatPayApiLogDao wechatPayApiLogDao;

    @Override
    @Transactional
    public ResultVo payment(String openId) {
        ResultVo resultVo = new ResultVo();

        WechatUserAccount wechatUserAccount = wechatUserAccountDao.get(openId);
        if (null == wechatUserAccount) {
            resultVo.setCode(PaymentResultCode.CODE2);
            return resultVo;
        }

        if (wechatUserAccount.getBalance() <= 0L || wechatUserAccount.getBalance() < 30L) {
            resultVo.setCode(PaymentResultCode.CODE3);
            return resultVo;
        }

        // 是否大于单笔支付上限
        int retryCount = 10;
        Long balance = wechatUserAccount.getBalance();
        if (balance > 49900L) {
            for (; balance > 49900L && retryCount > 0; ) {
                resultVo = singlePayment(openId);
                if (PaymentResultCode.CODE0.equals(resultVo.getCode())) {
                    resultVo.setCode(PaymentResultCode.CODE0);
                    return resultVo;
                }
                WechatUserAccount wechatUserAccount2 = getWechatUserAccountByNewTransaction(openId);
                if (null == wechatUserAccount2) {
                    resultVo.setCode(PaymentResultCode.CODE1);
                    return resultVo;
                }
                balance = wechatUserAccount2.getBalance();
                retryCount--;
            }

            if (balance >= 30L) {
                resultVo = singlePayment(openId);
            } else {
                resultVo.setCode(PaymentResultCode.CODE2);
                return resultVo;
            }
        } else {
            resultVo = singlePayment(openId);
        }

        return resultVo;
    }

    @Override
    @Transactional
    public ResultVo listPayment(String openId) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(PaymentResultCode.CODE1);

        List<WechatPayment> wechatPayments = wechatPaymentDao.listRedPack(openId);
        Long totalAmount = 0L;
        for (WechatPayment wechatPayment : wechatPayments) {
            totalAmount += wechatPayment.getAmount();
        }

        Map<String, Object> results = Maps.newHashMap();
        results.put("total", wechatPayments.size());
        results.put("totalAmount", totalAmount);
        results.put("data", wechatPayments);

        resultVo.setData(results);
        return resultVo;
    }

    /**
     * 开启新事物 获取微信用户账户信息
     *
     * @param openId 微信用户openId
     * @return WechatUserAccount
     */
    private WechatUserAccount getWechatUserAccountByNewTransaction(String openId) {
        TransactionStatus transactionStatus = null;
        DefaultTransactionDefinition transactionDefinition;
        WechatUserAccount wechatUserAccount = null;
        try {
            transactionDefinition = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
            transactionDefinition.setReadOnly(true);
            transactionStatus = transactionManager.getTransaction(transactionDefinition);

            wechatUserAccount = wechatUserAccountDao.get(openId);

            transactionManager.commit(transactionStatus);
        } catch (Exception e) {
            LOGGER.error(String.format("查询账号: %s 新数据失败", openId), e);
            if (transactionStatus != null) {
                transactionManager.rollback(transactionStatus);
            }
        }
        return wechatUserAccount;
    }

    /**
     * 单笔支付
     *
     * @param openId 微信用户openId
     * @return ResultVo
     */
    private ResultVo singlePayment(String openId) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(PaymentResultCode.CODE1);

        WechatUserAccount wechatUserAccount = getWechatUserAccountByNewTransaction(openId);

        if (wechatUserAccount.getBalance() <= 0L || wechatUserAccount.getBalance() < 30L) {
            resultVo.setCode(PaymentResultCode.CODE3);
            return resultVo;
        }

        Long money;
        if (wechatUserAccount.getBalance() >= 49900L) {
            money = 49900L;
        } else {
            money = wechatUserAccount.getBalance();
        }

        TransactionStatus transactionStatus = null;
        DefaultTransactionDefinition transactionDefinition;
        WechatPayment wechatPayment = new WechatPayment();
        try {
            transactionDefinition = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
            transactionStatus = transactionManager.getTransaction(transactionDefinition);

            // 添加支付记录
            wechatPayment.setOpenid(openId);
            wechatPayment.setAmount(money);
            wechatPayment.setStatus(PaymentStatus.ISSUING);
            wechatPaymentDao.insert(wechatPayment);

            transactionManager.commit(transactionStatus);
        } catch (Exception e) {
            LOGGER.error("添加支付记录失败", e);
            if (transactionStatus != null) {
                transactionManager.rollback(transactionStatus);
            }

            resultVo.setCode(PaymentResultCode.CODE0);
            return resultVo;
        }

        Boolean isUpdateBalance = Boolean.FALSE;
        Long requestStart, requestEnd = 0L;
        // returnCode 0成功 1失败
        Integer cnts, returnCode = PaymentApiStatus.SUCCESS;
        Map<String, Object> params = Maps.newHashMap();
        PaymentResultDto paymentResultDto = new PaymentResultDto();
        try {
            transactionDefinition = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
            transactionStatus = transactionManager.getTransaction(transactionDefinition);

            // 支付API invoke
            params.put("appkey", paymentHelper.paymentAppkey);
            params.put("method", paymentHelper.paymentMethod);
            params.put("openid", openId);
            // 红包金额 ( 单位：分 ）（必须大于等于30小于49900）
            params.put("money", money);

            requestStart = System.currentTimeMillis();
            ResponseResult result = sendJsonPost(paymentHelper.paymentUrl, params);
            requestEnd = System.currentTimeMillis() - requestStart;
            paymentResultDto = PaymentResultDto.jsonToBean(result);

            // 获取支付API返回状态
            if (StringUtils.isBlank(paymentResultDto.getCode()) || !"0".equals(paymentResultDto.getCode())) {
                returnCode = PaymentApiStatus.FAIL;
            }

            // 更新余额
            updateBlance(resultVo, wechatUserAccount, money, paymentResultDto);
            isUpdateBalance = Boolean.TRUE;

            // 更新支付记录
            WechatPayment wechatPaymentTemp = new WechatPayment();
            wechatPaymentTemp.setPayId(wechatPayment.getPayId());
            if (PaymentApiStatus.FAIL.equals(returnCode)) {
                wechatPaymentTemp.setStatus(PaymentStatus.FAIL);
            } else {
                wechatPaymentTemp.setTradeNo(paymentResultDto.getRedpackSn());
                wechatPaymentTemp.setRedpackUrl(paymentResultDto.getRedpackUrl());
                wechatPaymentTemp.setStatus(PaymentStatus.SUCCESS);
            }
            cnts = wechatPaymentDao.update(wechatPaymentTemp);
            if (cnts == 0) {
                throw new RuntimeException("支付履历更新失败");
            }

            // 新增API调用记录
            WechatPayApiLog wechatPayApiLog = new WechatPayApiLog();
            wechatPayApiLog.setPayId(wechatPayment.getPayId());
            wechatPayApiLog.setApiUrl(paymentHelper.paymentUrl);
            wechatPayApiLog.setParameters(new JSONObject(params).toString());
            wechatPayApiLog.setReturnCode(returnCode);
            wechatPayApiLog.setReturnJson(objToJson(paymentResultDto));
            wechatPayApiLog.setElapsed(requestEnd);
            wechatPayApiLogDao.insert(wechatPayApiLog);

            transactionManager.commit(transactionStatus);
        } catch (Exception e) {
            LOGGER.error("支付失败", e);
            if (transactionStatus != null) {
                transactionManager.rollback(transactionStatus);
            }

            if (!isUpdateBalance) {
                // 更新余额
                updateBlance(resultVo, wechatUserAccount, money, paymentResultDto);
            }

            // 更新支付记录
            WechatPayment wechatPaymentTemp = new WechatPayment();
            wechatPaymentTemp.setPayId(wechatPayment.getPayId());
            wechatPaymentTemp.setTradeNo(paymentResultDto.getRedpackSn());
            wechatPaymentTemp.setRedpackUrl(paymentResultDto.getRedpackUrl());
            if (StringUtils.isNotBlank(paymentResultDto.getCode()) && "0".equals(paymentResultDto.getCode())) {
                wechatPaymentTemp.setStatus(PaymentStatus.SUCCESS);
            } else {
                wechatPaymentTemp.setStatus(PaymentStatus.STATUS0);
            }
            cnts = wechatPaymentDao.update(wechatPaymentTemp);
            if (cnts == 0) {
                throw new RuntimeException("支付履历更新失败[异常]");
            }

            // 新增API调用记录
            WechatPayApiLog wechatPayApiLog = new WechatPayApiLog();
            wechatPayApiLog.setPayId(wechatPayment.getPayId());
            wechatPayApiLog.setApiUrl(paymentHelper.paymentUrl);
            wechatPayApiLog.setParameters(new JSONObject(params).toString());
            if (StringUtils.isNotBlank(paymentResultDto.getCode())) {
                if ("0".equals(paymentResultDto.getCode())) {
                    wechatPayApiLog.setReturnCode(PaymentApiStatus.SUCCESS);
                } else {
                    wechatPayApiLog.setReturnCode(PaymentApiStatus.FAIL);
                }
            } else {
                wechatPayApiLog.setReturnCode(PaymentApiStatus.ERROR);
            }
            wechatPayApiLog.setReturnJson(objToJson(paymentResultDto));
            wechatPayApiLog.setElapsed(requestEnd);
            wechatPayApiLogDao.insert(wechatPayApiLog);

            return resultVo;
        }

        resultVo.setData(paymentResultDto.getRedpackUrl());
        return resultVo;
    }

    /**
     * 更新余额
     */
    private void updateBlance(ResultVo resultVo, WechatUserAccount wechatUserAccount, Long money, PaymentResultDto paymentResultDto) {
        TransactionStatus transactionStatus = null;
        DefaultTransactionDefinition transactionDefinition;
        Integer cnts;
        try {
            transactionDefinition = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
            transactionStatus = transactionManager.getTransaction(transactionDefinition);

            // 更新微信用户账户余额
            if (StringUtils.isNotBlank(paymentResultDto.getCode()) && "0".equals(paymentResultDto.getCode())) {
                wechatUserAccount.setBalance(wechatUserAccount.getBalance() - money);
                cnts = wechatUserAccountDao.updateAccountBalance(wechatUserAccount);
                if (cnts == 0) {
                    WechatUserAccount wechatUserAccountTemp = getWechatUserAccountByNewTransaction(wechatUserAccount.getOpenid());
                    wechatUserAccountTemp.setBalance(wechatUserAccountTemp.getBalance() - money);
                    cnts = wechatUserAccountDao.updateAccountBalance(wechatUserAccountTemp);
                    if (cnts == 0) {
                        throw new RuntimeException("当前账户金额已变化");
                    }
                }
            }

            transactionManager.commit(transactionStatus);
        } catch (Exception e) {
            LOGGER.error("更新当前账户余额失败", e);
            if (transactionStatus != null) {
                transactionManager.rollback(transactionStatus);
            }

            resultVo.setCode(PaymentResultCode.CODE0);
        }
    }

    /**
     * 查询支付信息
     *
     * @param tradeNo 贸易号
     * @return PaymentResultDto
     */
    /*private PaymentResultDto getPayInfo(String tradeNo) throws Exception {
        Map<String, Object> params = Maps.newHashMap();
        params.put("appkey", paymentHelper.paymentAppkey);
        params.put("method", paymentHelper.paymentGetMethod);
        params.put("redpack_sn", tradeNo);

        ResponseResult result = sendJsonPost(paymentHelper.paymentGetUrl, params);
        return PaymentResultDto.jsonToBean(result);
    }*/

    /**
     * obj to json
     */
    private String objToJson(Object obj) {
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            LOGGER.error("obj to json失败", e);
            return StringUtils.EMPTY;
        }
    }

}
