package com.jjt.wechat.core.exception;

public class AppException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String errorCode;

	public AppException(String message) {
		super(message);
	}

	public AppException(Throwable e) {
		super(e);
	}

	public AppException(String errorCode, String message) {
		super(message);
		this.setErrorCode(errorCode);
	}

	public AppException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppException(String errorCode, String message, Throwable cause) {
		super(message, cause);
		this.setErrorCode(errorCode);
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
