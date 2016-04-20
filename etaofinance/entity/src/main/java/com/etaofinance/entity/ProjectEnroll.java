package com.etaofinance.entity;

import java.util.Date;

public class ProjectEnroll {
    private Long id;

    private Long memberid;

    private String title;

    private String ownedindustry;

    private String businessplanname;

    private String businessplanurl;

    private String phoneno;

    private String email;

    private String contacts;

    private String createname;

    private Date createtime;

    private Integer status;

    private String refuseremark;

    private Date opttime;

    private String optname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberid() {
        return memberid;
    }

    public void setMemberid(Long memberid) {
        this.memberid = memberid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getOwnedindustry() {
        return ownedindustry;
    }

    public void setOwnedindustry(String ownedindustry) {
        this.ownedindustry = ownedindustry == null ? null : ownedindustry.trim();
    }

    public String getBusinessplanname() {
        return businessplanname;
    }

    public void setBusinessplanname(String businessplanname) {
        this.businessplanname = businessplanname == null ? null : businessplanname.trim();
    }

    public String getBusinessplanurl() {
        return businessplanurl;
    }

    public void setBusinessplanurl(String businessplanurl) {
        this.businessplanurl = businessplanurl == null ? null : businessplanurl.trim();
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

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts == null ? null : contacts.trim();
    }

    public String getCreatename() {
        return createname;
    }

    public void setCreatename(String createname) {
        this.createname = createname == null ? null : createname.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRefuseremark() {
        return refuseremark;
    }

    public void setRefuseremark(String refuseremark) {
        this.refuseremark = refuseremark == null ? null : refuseremark.trim();
    }

    public Date getOpttime() {
        return opttime;
    }

    public void setOpttime(Date opttime) {
        this.opttime = opttime;
    }

    public String getOptname() {
        return optname;
    }

    public void setOptname(String optname) {
        this.optname = optname == null ? null : optname.trim();
    }
}