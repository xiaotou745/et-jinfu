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

    private String certifyMaterialName;
    
    private String certifyMaterialUrl;
    
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
    public String getStatusString(){
    	if(MemberApplyInvestStatusEnum.getEnum(status)!=null)
		{
			return MemberApplyInvestStatusEnum.getEnum(status).desc();
		}
		else {
			return "";
		}
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
        this.auditname = auditname == null ? null : auditname.trim();
    }

    public String getRefusereasion() {
        return refusereasion;
    }

    public void setRefusereasion(String refusereasion) {
        this.refusereasion = refusereasion == null ? null : refusereasion.trim();
    }

    public String getApplyinfo() {
        return applyinfo;
    }

    public void setApplyinfo(String applyinfo) {
        this.applyinfo = applyinfo == null ? null : applyinfo.trim();
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname == null ? null : companyname.trim();
    }

    public String getCompanytitle() {
        return companytitle;
    }

    public void setCompanytitle(String companytitle) {
        this.companytitle = companytitle == null ? null : companytitle.trim();
    }

	public String getCertifyMaterialName() {
		return certifyMaterialName;
	}

	public void setCertifyMaterialName(String certifyMaterialName) {
		this.certifyMaterialName = certifyMaterialName;
	}

	public String getCertifyMaterialUrl() {
		return certifyMaterialUrl;
	}

	public void setCertifyMaterialUrl(String certifyMaterialUrl) {
		this.certifyMaterialUrl = certifyMaterialUrl;
	}
}