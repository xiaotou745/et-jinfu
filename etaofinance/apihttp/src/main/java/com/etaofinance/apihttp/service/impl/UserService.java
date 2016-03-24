package com.etaofinance.apihttp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etaofinance.api.service.inter.IMemberService;
import com.etaofinance.apihttp.service.inter.IUserService;
/**
 * 用户相关Service
 * @author ofmyi_000
 *
 */
@Service
public class UserService implements IUserService {
	@Autowired
	private IMemberService memberService;
	
	
//	/**
//	 * 发送验证码
//	 */
//	@Override
//	public HttpResultModel<Object> sendcode(SendCodeReq req) {
//		HttpResultModel<Object> resultModel = new HttpResultModel<Object>();
//		return null;
//	}

}
