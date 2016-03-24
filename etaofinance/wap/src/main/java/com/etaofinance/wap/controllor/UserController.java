package com.etaofinance.wap.controllor;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etaofinance.api.service.inter.IMemberService;
import com.etaofinance.wap.common.HttpResultModel;
import com.etaofinance.entity.req.SendCodeReq;
import com.etaofinance.entity.resp.SendCodeResp;

/**
 * 用户相关
 * @author ofmyi_000
 *
 */
@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	IMemberService memberService;
	@Autowired
	HttpServletRequest request;
	
	/**
	 * 获取验证码接口
	 * @param req
	 * @return
	 */
	@RequestMapping("sendcode")
	@ResponseBody
	public  SendCodeResp sendcode(@RequestBody SendCodeReq req) {
		return memberService.sendCode(req);
	}
	
}
