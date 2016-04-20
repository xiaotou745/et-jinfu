package com.etaofinance.core.enums;

// 提现状态(1待审核 2 审核通过 3打款完成 -1审核拒绝 -2 打款失败)

public enum BalanceRecordType {

	Invest(1,"投资"),
	
	Refund(2,"退款"),
	
	Recharge(3,"充值"),
	
	Apply(4,"提现"),
	
	ApplyErr(5,"提现审核拒绝退款");
	

	
	private int value = 0;
	private String desc;
	
	private BalanceRecordType(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	
	public int value() {
		return this.value;
	}
	
	public String desc() {
		return this.desc;
	}

	public static BalanceRecordType getEnum(int index) {
		for (BalanceRecordType c : BalanceRecordType.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
