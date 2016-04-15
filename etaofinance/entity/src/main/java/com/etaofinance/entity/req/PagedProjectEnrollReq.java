package com.etaofinance.entity.req;

import java.sql.Date;

import com.etaofinance.entity.common.PagedRequestBase;

public class PagedProjectEnrollReq extends PagedRequestBase{

	
	private String title;
	private String ownedIndustry;
	private String startTime;
	private String endTime;

	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}	
	
	public String getOwnedIndustry() {
		return ownedIndustry;
	}
	public void setOwnedIndustry(String ownedIndustry) {
		this.ownedIndustry = ownedIndustry;
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
