package com.sns.ajaxresult;

public enum ResultStatusCode {
	OK(200),
	FAIL(500),
	FORBIDDEN(403),
	NONMEMBER(300);
	
	private final int code;	
	ResultStatusCode(int code) {
		this.code = code;
	}
	
	public int code() {
		return this.code;
	}
}
