package com.etaofinance.entity;

import java.util.Date;

public class ProjectEnroll {
    private Long id;

    private String title;

    private String ownedindustry;

    private String businessplanname;

    private String businessplanurl;

    private String phoneno;

    private String email;

    private String contacts;

    private String createname;

    private Date createtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}