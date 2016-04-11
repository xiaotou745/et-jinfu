package com.etaofinance.core.enums;

// 提现状态(1待审核 2 审核通过 3打款完成 -1审核拒绝 -2 打款失败)

public enum WithdrawStatus {

	Cking(1,"待审核"),
	
	Pass(2,"审核通过"),
	
	Completed(3,"打款完成"),
	
	Refuse(-1,"审核拒绝"),
	
	Failured(-2,"打款失败");

	
	private int value = 0;
	private String desc;
	
	private WithdrawStatus(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	
	public int value() {
		return this.value;
	}
	
	public String desc() {
		return this.desc;
	}

	public static WithdrawStatus getEnum(int index) {
		for (WithdrawStatus c : WithdrawStatus.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
