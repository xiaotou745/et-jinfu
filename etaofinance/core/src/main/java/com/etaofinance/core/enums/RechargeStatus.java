package com.etaofinance.core.enums;

public enum RechargeStatus {
	
    Recharging(0,"充值中"),

    Success(1,"充值成功"),

    Failure(-1,"充值失败");
    
	private int value = 0;
	
	private String desc;
	
	private RechargeStatus(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}
	
	public int value() {
		return this.value;
	}
	
	public String desc() {
		return this.desc;
	}

	public static RechargeStatus getEnum(int index) {
		for (RechargeStatus c : RechargeStatus.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
