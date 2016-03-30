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
