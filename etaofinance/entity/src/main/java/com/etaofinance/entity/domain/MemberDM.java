package com.etaofinance.entity.domain;

import java.util.Date;

import com.etaofinance.entity.BalanceRecord;
import com.etaofinance.entity.Member;
import com.etaofinance.entity.MenuInfo;
import com.etaofinance.entity.Withdrawform;

public class MemberDM extends Member{	
	
	//是否存在待审
	private Boolean isExistPending;
	
	//是否设置支付密码
	private Boolean isSetPayPassWord;	


	public Boolean getIsExistPending() {
		return isExistPending;
	}

	public void setIsExistPending(Boolean isExistPending) {
		this.isExistPending = isExistPending;
	}	
	
	public Boolean getIsSetPayPassWord() {
		return isSetPayPassWord;
	}

	public void setIsSetPayPassWord(Boolean isSetPayPassWord) {
		this.isSetPayPassWord = isSetPayPassWord;
	}

	

}