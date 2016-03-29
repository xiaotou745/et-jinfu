package com.etaofinance.entity;

import java.util.Date;

public class ProjectSubscription {
    private Long id;

    private Long memberid;

    private Long projectid;

    private Boolean islead;

    private Date createtime;

    private Float amount;

    private Integer fenshu;

    private String name;

    private String phoneno;

    private String email;

    private String idcard;

    private Short refundstatus;

    private String refundname;

    private Date refundtime;

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

    public Long getProjectid() {
        return projectid;
    }

    public void setProjectid(Long projectid) {
        this.projectid = projectid;
    }

    public Boolean getIslead() {
        return islead;
    }

    public void setIslead(Boolean islead) {
        this.islead = islead;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Integer getFenshu() {
        return fenshu;
    }

    public void setFenshu(Integer fenshu) {
        this.fenshu = fenshu;
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

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public Short getRefundstatus() {
        return refundstatus;
    }

    public void setRefundstatus(Short refundstatus) {
        this.refundstatus = refundstatus;
    }

    public String getRefundname() {
        return refundname;
    }

    public void setRefundname(String refundname) {
        this.refundname = refundname == null ? null : refundname.trim();
    }

    public Date getRefundtime() {
        return refundtime;
    }

    public void setRefundtime(Date refundtime) {
        this.refundtime = refundtime;
    }
}