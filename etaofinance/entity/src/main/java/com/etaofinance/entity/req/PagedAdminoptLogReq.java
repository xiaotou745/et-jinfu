package com.etaofinance.entity.req;

import com.etaofinance.entity.common.PagedRequestBase;

public class PagedAdminoptLogReq extends PagedRequestBase{

	private String beginCreatetime;
	
	private String endCreatetime;

	private String remark;
	
	public String getBeginCreatetime() {
		return beginCreatetime;
	}

	public void setBeginCreatetime(String beginCreatetime) {
		this.beginCreatetime = beginCreatetime;
	}

	public String getEndCreatetime() {
		return endCreatetime;
	}

	public void setEndCreatetime(String endCreatetime) {
		this.endCreatetime = endCreatetime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
