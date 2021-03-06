package com.etaofinance.apihttp.common;
/**
 * api-http 返回值公用 enum
 * @author CaoHeYang
 * @date 2015-0909
 */
public enum HttpReturnRnums {
	/**
	 * 成功
	 */
	Success(200,"success"),
	/**
	 * 系统错误
	 */
	SystemError(-1,"系统错误"),
	/**
	 * 参数错误
	 */
	ParaError(-2,"参数错误");
	private int value = 0;
	private String desc;
	private HttpReturnRnums(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static HttpReturnRnums getEnum(int index) {
		for (HttpReturnRnums c : HttpReturnRnums.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}

