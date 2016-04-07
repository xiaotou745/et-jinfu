package com.etaofinance.entity;

import java.util.Date;

public class ProjectFavorite {
	// 逻辑主键
    private Long id;

    // 会员Id
    private Long memberid;

    // 项目Id
    private Long projectid;

    
    // 关注时间
    private String createtime;

    // 是否关注
    private Integer isdel;

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

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }
}