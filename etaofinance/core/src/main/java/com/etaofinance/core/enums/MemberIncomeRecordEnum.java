package com.etaofinance.core.enums;

public enum MemberIncomeRecordEnum {
	TouZi(1,"投资"),
	
	ChongZhi(3,"充值"),
	
	TiXian(4,"提现"),
	
	TuiKuan(2,"退款");
	
	private int value = 0;
	private String desc;

	private MemberIncomeRecordEnum(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}

	public int value() {
		return this.value;
	}

	public String desc() {
		return this.desc;
	}

	public static MemberIncomeRecordEnum getEnum(int index) {
		for (MemberIncomeRecordEnum c : MemberIncomeRecordEnum.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
