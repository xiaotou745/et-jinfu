package com.etaofinance.apihttp.service.inter;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/user")
@Consumes("application/json")
@Produces("application/json; charset=utf-8")
public interface IUserService {

//	/**
//	 * 获取验证码
//	 * @author ruhuaxiao	
//	 * @date 2016年3月23日11:07:48
//	 * @return 
//	 * */
//	@POST
//	@Path("/sendcode")
//	public HttpResultModel<Object> sendcode(SendCodeReq req);
}
