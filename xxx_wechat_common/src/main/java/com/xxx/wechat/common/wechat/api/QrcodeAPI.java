package com.xxx.wechat.common.wechat.api;

import java.util.HashMap;
import java.util.Map;

import com.xxx.wechat.common.constant.Constant;
import com.xxx.wechat.common.utils.BeanUtils;
import com.xxx.wechat.common.utils.CheckUtils;
import com.xxx.wechat.common.utils.JsonUtils;
import com.xxx.wechat.common.wechat.api.entity.QrCode;
import com.xxx.wechat.common.wechat.api.response.BaseResponse;
import com.xxx.wechat.common.wechat.api.response.QrcodeResponse;


/**
 * 二维码相关API
 *
 * @since 1.2
 */
public class QrcodeAPI extends BaseApi {

    public QrcodeAPI(String accessToken){
    	super(accessToken);
    }

    /**
     * 创建二维码
     *
     * @param actionName    二维码类型，QR_SCENE为临时,QR_LIMIT_SCENE为永久
     * @param sceneId       场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
     * @param sceneStr      场景值ID（字符串形式的ID），字符串类型，长度限制为1到64，仅永久二维码支持此字段
     * @param expireSeconds 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒
     * @return 二维码对象
     */
    public QrcodeResponse createQrcode(QrCode qrcode) throws Exception {
        BeanUtils.requireNonNull(qrcode.getActionName(), "actionName is null");
        //BeanUtils.requireNonNull(qrcode.getActionInfo(), "actionInfo is null");

        logger.debug("创建二维码信息.....");

        QrcodeResponse response = null;
        String url = String.format(Constant.WechatUrl.GET_QRCODE_TICKET, accessToken);

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("action_name", qrcode.getActionName());
        Map<String, Object> actionInfo = new HashMap<String, Object>();
        Map<String, Object> scene = new HashMap<String, Object>();
        if (!CheckUtils.isNull(qrcode.getSceneId()))
            scene.put("scene_id", qrcode.getSceneId());
        if (!CheckUtils.isNullOrEmpty(qrcode.getSceneStr()))
            scene.put("scene_str", qrcode.getSceneStr());
        actionInfo.put("scene", scene);
        param.put("action_info", actionInfo);
        if (!CheckUtils.isNull(qrcode.getExpireSeconds())) {
            param.put("expire_seconds", qrcode.getExpireSeconds());
        }
        BaseResponse r = executePost(url, JsonUtils.toJson(param));
        String resultJson = isSuccess(r.getErrcode()) ? r.getErrmsg() : r.toJsonString();
        response = JsonUtils.toBean(resultJson, QrcodeResponse.class);
        return response;
    }
}
