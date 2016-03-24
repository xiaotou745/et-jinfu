package com.etaofinance.entity.resp;
/**
 * 验证码返回
 * @author ofmyi_000
 *
 */
public class SendCodeResp {

	private String message;
	private int code;
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
