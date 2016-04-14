package com.etaofinance.entity.req;

import com.etaofinance.entity.common.PagedRequestBase;

public class PagedProjectSubReq extends PagedRequestBase{
	private Long memberid;

    private Long projectid;
    
    private String name;
    
    private String phoneno;
    
    private String email;
    
    private Short refundstatus;
    
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
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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
    
    public Short getRefundstatus() {
        return refundstatus;
    }

    public void setRefundstatus(Short refundstatus) {
        this.refundstatus = refundstatus;
    }
}
