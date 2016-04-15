package com.etaofinance.entity;

import java.util.Date;

import com.etaofinance.core.enums.MemberApplyInvestStatusEnum;
import com.etaofinance.core.enums.MemberStatusEnum;

public class MemberApply {
	private Long id;

	private Long memberid;

	private Short status;

	private Integer typeid;

	private Date createtime;

	private Date audittime;

	private String auditname;

	private String refusereasion;

	private String applyinfo;

	private String companyname;

	private String companytitle;

	private String certifymaterialname;

	private String certifymaterialurl;

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

	public Short getStatus() {
		return status;
	}

	public String getStatusString() {
		if (status != null) {
			if (MemberApplyInvestStatusEnum.getEnum(status) != null) {
				return MemberApplyInvestStatusEnum.getEnum(status).desc();
			}
		}
		return "";
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Integer getTypeid() {
		return typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getAudittime() {
		return audittime;
	}

	public void setAudittime(Date audittime) {
		this.audittime = audittime;
	}

	public String getAuditname() {
		return auditname;
	}

	public void setAuditname(String auditname) {
		this.auditname = auditname == null ? "" : auditname.trim();
	}

	public String getRefusereasion() {
		return refusereasion;
	}

	public void setRefusereasion(String refusereasion) {
		this.refusereasion = refusereasion == null ? "" : refusereasion
				.trim();
	}

	public String getApplyinfo() {
		return applyinfo;
	}

	public void setApplyinfo(String applyinfo) {
		this.applyinfo = applyinfo == null ? "" : applyinfo.trim();
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname == null ? "" : companyname.trim();
	}

	public String getCompanytitle() {
		return companytitle;
	}

	public void setCompanytitle(String companytitle) {
		this.companytitle = companytitle == null ? "" : companytitle.trim();
	}

	public String getCertifymaterialname() {
		return certifymaterialname;
	}

	public void setCertifymaterialname(String certifymaterialname) {
		this.certifymaterialname = certifymaterialname;
	}

	public String getCertifymaterialurl() {
		return certifymaterialurl;
	}

	public void setCertifymaterialurl(String certifymaterialurl) {
		this.certifymaterialurl = certifymaterialurl;
	}
}