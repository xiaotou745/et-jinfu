package com.etaofinance.entity.domain;

import com.etaofinance.entity.MenuInfo;



public class MenuDetail extends MenuInfo {

	private String parMenuName;
	private int jibie; 

	public int getJibie() {
		return jibie;
	}

	public void setJibie(int jibie) {
		this.jibie = jibie;
	}

	public String getParMenuName() {
		return parMenuName;
	}

	public void setParMenuName(String parMenuName) {
		this.parMenuName = parMenuName;
	}
}
