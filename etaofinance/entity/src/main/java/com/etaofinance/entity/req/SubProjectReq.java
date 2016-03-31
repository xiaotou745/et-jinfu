package com.etaofinance.entity.req;
/**
 * 
 * 认购项目
 * @author ofmyi_000
 *
 */
public class SubProjectReq {

	private Long userId;
	private Integer quantity;
	private Integer isLead;
	private String payPwd;
	private Long projectId;
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public String getPayPwd() {
		return payPwd;
	}
	public void setPayPwd(String payPwd) {
		this.payPwd = payPwd;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getIsLead() {
		return isLead;
	}
	public void setIsLead(Integer isLead) {
		this.isLead = isLead;
	}
}
