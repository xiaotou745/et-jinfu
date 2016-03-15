package com.etaofinance.entity;

import java.util.Date;

public class Project {
    private Long id;

    private String projectname;

    private Short typeid;

    private String description;

    private Float amount;

    private Integer fenshu;

    private Integer leadminfenshu;

    private Integer preheatmaxfenshu;

    private Float unitprice;

    private Integer provincecode;

    private Integer citycode;

    private Integer areacode;

    private String address;

    private String projectimage;

    private Short auditstatus;

    private Short projectstatus;

    private Boolean isshelve;

    private Long memberid;

    private Date preheattime;

    private Date starttime;

    private Date endtime;

    private Integer schedule;

    private Integer investmentnumber;

    private Integer follownumber;

    private String auditname;

    private Date audittime;

    private String createname;

    private Date createtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname == null ? null : projectname.trim();
    }

    public Short getTypeid() {
        return typeid;
    }

    public void setTypeid(Short typeid) {
        this.typeid = typeid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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

    public Integer getLeadminfenshu() {
        return leadminfenshu;
    }

    public void setLeadminfenshu(Integer leadminfenshu) {
        this.leadminfenshu = leadminfenshu;
    }

    public Integer getPreheatmaxfenshu() {
        return preheatmaxfenshu;
    }

    public void setPreheatmaxfenshu(Integer preheatmaxfenshu) {
        this.preheatmaxfenshu = preheatmaxfenshu;
    }

    public Float getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(Float unitprice) {
        this.unitprice = unitprice;
    }

    public Integer getProvincecode() {
        return provincecode;
    }

    public void setProvincecode(Integer provincecode) {
        this.provincecode = provincecode;
    }

    public Integer getCitycode() {
        return citycode;
    }

    public void setCitycode(Integer citycode) {
        this.citycode = citycode;
    }

    public Integer getAreacode() {
        return areacode;
    }

    public void setAreacode(Integer areacode) {
        this.areacode = areacode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getProjectimage() {
        return projectimage;
    }

    public void setProjectimage(String projectimage) {
        this.projectimage = projectimage == null ? null : projectimage.trim();
    }

    public Short getAuditstatus() {
        return auditstatus;
    }

    public void setAuditstatus(Short auditstatus) {
        this.auditstatus = auditstatus;
    }

    public Short getProjectstatus() {
        return projectstatus;
    }

    public void setProjectstatus(Short projectstatus) {
        this.projectstatus = projectstatus;
    }

    public Boolean getIsshelve() {
        return isshelve;
    }

    public void setIsshelve(Boolean isshelve) {
        this.isshelve = isshelve;
    }

    public Long getMemberid() {
        return memberid;
    }

    public void setMemberid(Long memberid) {
        this.memberid = memberid;
    }

    public Date getPreheattime() {
        return preheattime;
    }

    public void setPreheattime(Date preheattime) {
        this.preheattime = preheattime;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Integer getSchedule() {
        return schedule;
    }

    public void setSchedule(Integer schedule) {
        this.schedule = schedule;
    }

    public Integer getInvestmentnumber() {
        return investmentnumber;
    }

    public void setInvestmentnumber(Integer investmentnumber) {
        this.investmentnumber = investmentnumber;
    }

    public Integer getFollownumber() {
        return follownumber;
    }

    public void setFollownumber(Integer follownumber) {
        this.follownumber = follownumber;
    }

    public String getAuditname() {
        return auditname;
    }

    public void setAuditname(String auditname) {
        this.auditname = auditname == null ? null : auditname.trim();
    }

    public Date getAudittime() {
        return audittime;
    }

    public void setAudittime(Date audittime) {
        this.audittime = audittime;
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