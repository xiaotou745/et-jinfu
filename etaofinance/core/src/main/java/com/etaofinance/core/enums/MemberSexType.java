package com.etaofinance.core.enums;

public enum MemberSexType {
	/**
	 * 保密
	 */
	Unknow(0,"保密"),
	
	/**
	 * nan
	 */
	Men(1,"男"),
	
	/**
	 * nv
	 */
	Women(2,"女");

	private int value = 0;
	private String desc;
	private MemberSexType(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static MemberSexType getEnum(int index) {
		for (MemberSexType c : MemberSexType.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
