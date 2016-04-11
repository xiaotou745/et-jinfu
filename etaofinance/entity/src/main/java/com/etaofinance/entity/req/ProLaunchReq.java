package com.etaofinance.entity.req;
/**
 * 
 * 我发起的项目
 * @author hulingbo
 * @date 2016年4月8日09:51:58
 *
 */
public class ProLaunchReq {
	private Long memberid;
    private Integer projectStatus;	
    
	public Long getMemberid() {
		return memberid;
	}
	public void setMemberid(Long memberid) {
		this.memberid = memberid;
	}   
  
	public Integer getProjectStatus() {
		return projectStatus;
	}
	public void setProjectStatus(Integer projectStatus) {
		this.projectStatus = projectStatus;
	}
	
}
