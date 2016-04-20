package com.etaofinance.core.enums;

public enum WithdrawformStatus {
	
	Status1(1,"待审核"),

	Status2(2,"审核通过"),

	Status3(-1,"打款完成"),
	
	Statusf1(2,"审核拒绝"),

	Statusf2(2,"打款失败");
	
	private int value = 0;
	
	private String desc;
	
	private WithdrawformStatus(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}
	
	public int value() {
		return this.value;
	}
	
	public String desc() {
		return this.desc;
	}

	public static WithdrawformStatus getEnum(int index) {
		for (WithdrawformStatus c : WithdrawformStatus.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
