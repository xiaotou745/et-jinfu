package com.etaofinance.entity.req;
/**
 * 
 * 我投资的项目
 * @author hulingbo
 * @date 2016年4月7日16:37:29
 *
 */
public class ProFavoriteReq {
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
