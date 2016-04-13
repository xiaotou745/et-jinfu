package com.etaofinance.entity.req;

import com.etaofinance.entity.common.PagedRequestBase;

public class PagedCommentReq extends PagedRequestBase{

	private Long memberid;
	
	private Long projectid;
	
	private String beginCreatetime;
	
	private String endCreatetime;
	
	
	public Long getMemberid() {
        return memberid;
    }

    public void setMemberid(Long memberid) {
        this.memberid = memberid;
    }
    
    public Long getProjectid() {
        return projectid;
    }

    public void setProjectid(Long projectid) {
        this.projectid = projectid;
    }
    
	public String getBeginCreatetime() {
        return beginCreatetime;
    }

    public void setBeginCreatetime(String begincreatetime) {
        this.beginCreatetime = begincreatetime;
    }

    public String getEndCreatetime() {
        return endCreatetime;
    }

    public void setEndCreatetime(String endcreatetime) {
        this.endCreatetime = endcreatetime;
    }
}
