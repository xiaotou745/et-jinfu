package com.etaofinance.entity.req;

import java.sql.Date;

import com.etaofinance.entity.common.PagedRequestBase;

public class PagedBalancerecordReq extends PagedRequestBase{
	private int proId;
	private String createName;
	private Date startTime;
	private Date endTime;
	
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
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
