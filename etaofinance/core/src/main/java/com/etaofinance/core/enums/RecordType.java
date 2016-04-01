package com.etaofinance.core.enums;

public enum RecordType {
	//操作类型：1投资，2退款至余额，3充值，4提现
	/**
	 * 投资
	 */
	Invest(1, "投资"),
	/**
	 * 投资
	 */
	Refund(2, "退款"),
	/**
	 * 充值
	 */
	Recharge(3, "充值"),
	
	/**
	 * 提现
	 */
	Withdrew (4,"提现");

	private int value = 0;
	private String desc;
	private RecordType(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static RecordType getEnum(int index) {
		for (RecordType c : RecordType.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
