package com.etaofinance.entity.req;

import com.etaofinance.entity.common.PagedRequestBase;

public class PagedMemberBalanceRecordReq extends PagedRequestBase{
	private int id;
	
	private String createStartTime;
	
	private String createEndTime;
	
	private int typeId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCreateStartTime() {
		return createStartTime;
	}

	public void setCreateStartTime(String createStartTime) {
		this.createStartTime = createStartTime;
	}

	public String getCreateEndTime() {
		return createEndTime;
	}

	public void setCreateEndTime(String createEndTime) {
		this.createEndTime = createEndTime;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
}
