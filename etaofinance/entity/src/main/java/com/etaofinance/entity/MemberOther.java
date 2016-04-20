package com.etaofinance.entity;

public class MemberOther {
	 private Long id;

	    private Long memberid;

	    private Float balanceprice;

	    private Float allowwithdrawprice;

	    private Float haswithdrawprice;

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

	    public Float getBalanceprice() {
	        return balanceprice;
	    }

	    public void setBalanceprice(Float balanceprice) {
	        this.balanceprice = balanceprice;
	    }

	    public Float getAllowwithdrawprice() {
	        return allowwithdrawprice;
	    }

	    public void setAllowwithdrawprice(Float allowwithdrawprice) {
	        this.allowwithdrawprice = allowwithdrawprice;
	    }

	    public Float getHaswithdrawprice() {
	        return haswithdrawprice;
	    }

	    public void setHaswithdrawprice(Float haswithdrawprice) {
	        this.haswithdrawprice = haswithdrawprice;
	    }

	    public String getPaypassword() {
	        return paypassword;
	    }

	    public void setPaypassword(String paypassword) {
	        this.paypassword = paypassword == null ? null : paypassword.trim();
	    }
}