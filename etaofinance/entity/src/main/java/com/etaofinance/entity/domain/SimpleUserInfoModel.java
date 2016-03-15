package com.etaofinance.entity.domain;

import java.io.Serializable;



/**
 * 登录后存储用户登录信息实体类
 * 
 *
 */
public class SimpleUserInfoModel implements Serializable {
	private int id;
	
	private String password;
	
	private String userName;
	
	private String loginName;
	
	private int roleId;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
}
