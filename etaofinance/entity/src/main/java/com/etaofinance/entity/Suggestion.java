package com.etaofinance.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Suggestion {
    private Integer id;

    private String projectname;

    private String username;

    private String phoneno;

    private String email;

    private BigDecimal amount;

    private String repaymentdate;

    private Date createtime;

    private String clientip;

    private Short status;

    private Integer dkamount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname == null ? null : projectname.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getRepaymentdate() {
        return repaymentdate;
    }

    public void setRepaymentdate(String repaymentdate) {
        this.repaymentdate = repaymentdate == null ? null : repaymentdate.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getClientip() {
        return clientip;
    }

    public void setClientip(String clientip) {
        this.clientip = clientip == null ? null : clientip.trim();
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Integer getDkamount() {
        return dkamount;
    }

    public void setDkamount(Integer dkamount) {
        this.dkamount = dkamount;
    }
}