package com.etaofinance.apihttp.service.inter;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.etaofinance.entity.RoleInfo;
import com.etaofinance.entity.req.*;
import com.etaofinance.entity.common.HttpResultModel;
/*
 * 用户账号相关
 * 胡灵波
 * 2016年3月9日10:35:01
 * */
@Path("/account")
@Consumes("application/json")
@Produces("application/json; charset=utf-8")
public interface IAccountService {
	/**
	 * 获取角色信息
	 * @author 胡灵波
	 * @date 2016年3月9日10:40:00
	 * @return
	 */
	@POST
	@Path("/selectbyprimarykey")	
	public RoleInfo selectByPrimaryKey(RoleInfo req);
	
	
	

}
