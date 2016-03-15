package com.etaofinance.core.enums;

/**
 * 所属广告位
 * @author hulingbo
 * @2016年3月15日14:20:24
 *
 */
public enum ADVertType {
	/**
	 * Wap
	 */
	Wap(1,"wap"),
	/**
	 * 已支付
	 */
	Web(2,"Web");
	private int value = 0;
	private String desc;
	private ADVertType(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static ADVertType getEnum(int index) {
		for (ADVertType c : ADVertType.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
