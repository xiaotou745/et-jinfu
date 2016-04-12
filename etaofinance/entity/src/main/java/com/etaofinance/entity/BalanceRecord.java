package com.etaofinance.entity;

import java.util.Date;

import com.etaofinance.core.enums.RecordType;

public class BalanceRecord {
    private Long id;

    private Long memberid;

    private Float amount;

    private Float afteramount;

    private Short typeid;

    private Long projectid;

    private String relationno;

    private String remark;

    private String optname;

    private Date opttime;

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

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Float getAfteramount() {
        return afteramount;
    }

    public void setAfteramount(Float afteramount) {
        this.afteramount = afteramount;
    }

    public Short getTypeid() {
        return typeid;
    }
    
    public String getTypeidString(){
    	if(RecordType.getEnum(typeid)!=null)
		{
			return RecordType.getEnum(typeid).desc();
		}
		else {
			return "";
		}
    }

    public void setTypeid(Short typeid) {
        this.typeid = typeid;
    }

    public Long getProjectid() {
        return projectid;
    }

    public void setProjectid(Long projectid) {
        this.projectid = projectid;
    }

    public String getRelationno() {
        return relationno;
    }

    public void setRelationno(String relationno) {
        this.relationno = relationno == null ? null : relationno.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getOptname() {
        return optname;
    }

    public void setOptname(String optname) {
        this.optname = optname == null ? null : optname.trim();
    }

    public Date getOpttime() {
        return opttime;
    }

    public void setOpttime(Date opttime) {
        this.opttime = opttime;
    }
}