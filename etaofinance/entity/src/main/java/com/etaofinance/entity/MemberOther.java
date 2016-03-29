package com.etaofinance.entity;

public class MemberOther {
	 private Long id;

	    private Long memberid;

	    private Double balanceprice;

	    private Double allowwithdrawprice;

	    private Double haswithdrawprice;

	    private String paypassword;

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

	    public Double getBalanceprice() {
	        return balanceprice;
	    }

	    public void setBalanceprice(Double balanceprice) {
	        this.balanceprice = balanceprice;
	    }

	    public Double getAllowwithdrawprice() {
	        return allowwithdrawprice;
	    }

	    public void setAllowwithdrawprice(Double allowwithdrawprice) {
	        this.allowwithdrawprice = allowwithdrawprice;
	    }

	    public Double getHaswithdrawprice() {
	        return haswithdrawprice;
	    }

	    public void setHaswithdrawprice(Double haswithdrawprice) {
	        this.haswithdrawprice = haswithdrawprice;
	    }

	    public String getPaypassword() {
	        return paypassword;
	    }

	    public void setPaypassword(String paypassword) {
	        this.paypassword = paypassword == null ? null : paypassword.trim();
	    }
}