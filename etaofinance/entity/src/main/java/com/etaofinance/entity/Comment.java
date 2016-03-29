package com.etaofinance.entity;

import java.util.Date;

public class Comment {
    private Long id;

    private Long projectid;

    private Long memberid;

    private String content;

    private Byte isreply;

    private Long recommentid;

    private Date createtime;

    private Byte isdel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectid() {
        return projectid;
    }

    public void setProjectid(Long projectid) {
        this.projectid = projectid;
    }

    public Long getMemberid() {
        return memberid;
    }

    public void setMemberid(Long memberid) {
        this.memberid = memberid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Byte getIsreply() {
        return isreply;
    }

    public void setIsreply(Byte isreply) {
        this.isreply = isreply;
    }

    public Long getRecommentid() {
        return recommentid;
    }

    public void setRecommentid(Long recommentid) {
        this.recommentid = recommentid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Byte getIsdel() {
        return isdel;
    }

    public void setIsdel(Byte isdel) {
        this.isdel = isdel;
    }
}