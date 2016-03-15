package com.etaofinance.entity;

import java.util.Date;

public class RoleInfo {
    private Integer id;

    private String rolename;

    private Date createtime;

    private String createname;

    private String optname;

    private Date opttime;

    private Boolean islock;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
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

    public Boolean getIslock() {
        return islock;
    }

    public void setIslock(Boolean islock) {
        this.islock = islock;
    }
}