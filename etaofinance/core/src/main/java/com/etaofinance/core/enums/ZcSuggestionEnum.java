package com.etaofinance.core.enums;

public enum ZcSuggestionEnum {

	/**
	 * 成功
	 */
	Success(1, "成功！"),
	
	/**
	 * 错误
	 */
	Err(-1,"错误");

	private int value = 0;
	private String desc;
	private ZcSuggestionEnum(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static ZcSuggestionEnum getEnum(int index) {
		for (ZcSuggestionEnum c : ZcSuggestionEnum.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
