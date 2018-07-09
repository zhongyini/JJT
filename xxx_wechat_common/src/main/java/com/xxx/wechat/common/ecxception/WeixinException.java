package com.xxx.wechat.common.ecxception;

/**
 * 微信API处理异常
 *
 */
public class WeixinException extends RuntimeException {

	private static final long serialVersionUID = -8061334851224964500L;

	public WeixinException() {
        super();
    }

    public WeixinException(String message) {
        super(message);
    }

    public WeixinException(String message, Throwable cause) {
        super(message, cause);
    }

    public WeixinException(Throwable cause) {
        super(cause);
    }
}
