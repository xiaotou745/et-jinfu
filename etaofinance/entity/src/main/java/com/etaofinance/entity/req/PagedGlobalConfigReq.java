package com.etaofinance.entity.req;

import com.etaofinance.entity.common.PagedRequestBase;




public class PagedGlobalConfigReq extends PagedRequestBase{
	private int groupId;

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
}