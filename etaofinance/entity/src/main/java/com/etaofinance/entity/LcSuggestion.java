package com.etaofinance.entity;

import java.math.BigDecimal;
import java.util.Date;

public class LcSuggestion {
    private Integer id;

    private String username;

    private String phoneno;

    private BigDecimal amount;

    private String resistance;

    private Date createtime;

    private String clientip;

    private BigDecimal yearamount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getResistance() {
        return resistance;
    }

    public void setResistance(String resistance) {
        this.resistance = resistance == null ? null : resistance.trim();
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

    public BigDecimal getYearamount() {
        return yearamount;
    }

    public void setYearamount(BigDecimal yearamount) {
        this.yearamount = yearamount;
    }
}