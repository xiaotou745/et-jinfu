package com.etaofinance.entity.req;

import com.etaofinance.entity.common.PagedRequestBase;

/**
 * 角色
 * */
public class PagedRoleInfoReq extends PagedRequestBase{

private String roleName;

public String getRoleName() {
	return roleName;
}

public void setRoleName(String roleName) {
	this.roleName = roleName;
}

}
