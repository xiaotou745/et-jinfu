package com.etaofinance.entity.req;

import java.sql.Date;

import com.etaofinance.entity.common.PagedRequestBase;

public class PagedBalancerecordReq extends PagedRequestBase{
	
	private int proId;
	
	private String createName;
	
	private String startTime;
	
	private String endTime;
	
	
	public int getProId() {
		return proId;
	}
	public void setProId(int proId) {
		this.proId = proId;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
