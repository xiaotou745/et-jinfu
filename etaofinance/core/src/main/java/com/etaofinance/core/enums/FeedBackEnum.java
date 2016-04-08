package com.etaofinance.core.enums;

public enum FeedBackEnum {
	 
	/**
	 * 成功
	 */
	Success(1, "成功！"),
	
	/**
	 * 错误
	 */
	Err(-1,"错误"),
	
	/**
	 * 用户不存在
	 */
	MemberIdIsNull(-2,"用户不存在"),
	
	/**
	 * 内容不能为空
	 */
	DescriptionIsNull(-3,"内容不能为空"),
	
	/**
	 * 内容不能超过500个字符
	 */
	DescriptionIsErr(-4,"内容不能超过500个字符"),

	/**
	 * 联系人不能超过10个字符
	 */
	ContactsIsErr(-5,"联系人不能超过10个字符"),	

	
	/**
	 * 电话不能超过12个字符
	 */
	PhoneNoIsErr(-6,"电话不能超过12个字符"),
	

	/**
	 * 邮箱不能超过30个字符	
	 */
	EMailIsErr(-7,"邮箱不能超过30个字符");

	private int value = 0;
	private String desc;
	private FeedBackEnum(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static QAEnum getEnum(int index) {
		for (QAEnum c : QAEnum.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
