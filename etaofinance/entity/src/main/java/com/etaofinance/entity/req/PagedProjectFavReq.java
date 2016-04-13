package com.etaofinance.entity.req;

import com.etaofinance.entity.common.PagedRequestBase;

public class PagedProjectFavReq extends PagedRequestBase{
	private Long memberid;
	
	private Long projectid;
	
	private String truename;
	
	private String phoneno;
	
	private String email;
	
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
    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename == null ? null : truename.trim();
    }
    
    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno == null ? null : phoneno.trim();
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }
}
