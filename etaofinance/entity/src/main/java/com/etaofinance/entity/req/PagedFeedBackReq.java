package com.etaofinance.entity.req;

import java.util.Date;

import com.etaofinance.entity.common.PagedRequestBase;

public class PagedFeedBackReq extends PagedRequestBase{
	private Integer id;

    private String description;

    private Long memberid;

    private String createname;

    private String phoneno;

    private String email;
    
    private String contacts;   

	private String beginCreatetime;
	
	private String endCreatetime;

    private Boolean isdel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Long getMemberid() {
        return memberid;
    }

    public void setMemberid(Long memberid) {
        this.memberid = memberid;
    }

    public String getCreatename() {
        return createname;
    }

    public void setCreatename(String createname) {
        this.createname = createname == null ? null : createname.trim();
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
    
    public Boolean getIsdel() {
        return isdel;
    }

    public void setIsdel(Boolean isdel) {
        this.isdel = isdel;
    }
    
    public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
}
