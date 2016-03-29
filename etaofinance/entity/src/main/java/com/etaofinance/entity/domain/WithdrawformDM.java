package com.etaofinance.entity.domain;

import java.util.Date;

import com.etaofinance.entity.BalanceRecord;
import com.etaofinance.entity.MenuInfo;
import com.etaofinance.entity.Withdrawform;

public class WithdrawformDM extends Withdrawform{
	private String statusName;

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}	

	

}