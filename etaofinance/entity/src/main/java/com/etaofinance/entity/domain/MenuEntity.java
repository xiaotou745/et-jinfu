package com.etaofinance.entity.domain;

import com.etaofinance.entity.MenuInfo;

public class MenuEntity extends MenuInfo{
	private Integer accoutId;

    private Integer menuId;

	public Integer getAccoutId() {
		return accoutId;
	}

	public void setAccoutId(Integer accoutId) {
		this.accoutId = accoutId;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
}
