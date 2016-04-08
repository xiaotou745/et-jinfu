package com.etaofinance.entity;

import java.util.Date;

public class Message {
    private Integer id;

    private Long memberid;

    private String phone;

    private String pushway;

    private String content;
    private String msghead;
    private Integer msgtype;
    private String msgdes;

    private Short status;
    private Boolean isdel;
    private Boolean isread;

    private Date createtime;

    private String createname;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    } 
    
    public Integer getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(Integer msgtype) {
        this.msgtype = msgtype;
    } 
    
    public String getMsghead() {
        return msghead;
    }

    public void setMsghead(String msghead) {
        this.msghead = msghead;
    }
    public String getMsgdes() {
        return msgdes;
    }

    public void String(String msgdes) {
        this.msgdes = msgdes;
    }

    public Long getMemberid() {
        return memberid;
    }

    public void setMemberid(Long memberid) {
        this.memberid = memberid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPushway() {
        return pushway;
    }

    public void setPushway(String pushway) {
        this.pushway = pushway == null ? null : pushway.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCreatename() {
        return createname;
    }

    public void setCreatename(String createname) {
        this.createname = createname == null ? null : createname.trim();
    }
    public Boolean getIsdel() {
        return isdel;
    }

    public void setIsdel(Boolean isdel) {
        this.isdel = isdel;
    }
    
    public Boolean getIsread() {
        return isread;
    }

    public void setIsread(Boolean isread) {
        this.isread = isread;
    }
}