package com.etaofinance.entity.domain;

public class ToDoDataStatistics {

	//待审核跟投人
	private int gtuser;
	//待审核项目数
	private int ltuser;
	//待审核领投人
	private int dshProject;
	//待审核提现
	private int dshtx;
	//成功订单数
	private int ordercount;
	//意见反馈
	private int qacount;
	
	
	public int getLtuser() {
		return ltuser;
	}
	public void setLtuser(int ltuser) {
		this.ltuser = ltuser;
	}
	public int getDshProject() {
		return dshProject;
	}
	public void setDshProject(int dshProject) {
		this.dshProject = dshProject;
	}
	public int getDshtx() {
		return dshtx;
	}
	public void setDshtx(int dshtx) {
		this.dshtx = dshtx;
	}
	public int getOrdercount() {
		return ordercount;
	}
	public void setOrdercount(int ordercount) {
		this.ordercount = ordercount;
	}
	public int getQacount() {
		return qacount;
	}
	public void setQacount(int qacount) {
		this.qacount = qacount;
	}
	public int getGtuser() {
		return gtuser;
	}
	public void setGtuser(int gtuser) {
		this.gtuser = gtuser;
	}
}
