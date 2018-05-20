package com.jjt.common.httpexception;

public class InappropriateDataException extends Exception{

	private static final long serialVersionUID = -5784691930890639506L;

	public InappropriateDataException(String param) {
		super("Inappropriate data!" + param);
	}
}
