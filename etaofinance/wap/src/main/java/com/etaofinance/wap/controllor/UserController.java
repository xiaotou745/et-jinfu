package com.etaofinance.wap.controllor;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etaofinance.api.service.inter.IMemberService;
import com.etaofinance.entity.Member;
import com.etaofinance.entity.req.RegistReq;
import com.etaofinance.entity.req.SendCodeReq;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.resp.MemberResp;
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
	
	/**
	 * 注册
	 * @param req
	 * @return
	 */
	@RequestMapping("regist")
	@ResponseBody
	public  HttpResultModel<Member> regist(@RequestBody RegistReq req) {
		return  memberService.regist(req);			
	}
	
	/**
	 * 会员实名认证
	 * @param 
	 * @author hulingbo
	 * @date 2016年3月24日18:05:14
	 * @return
	 */
	@RequestMapping("certification")
	@ResponseBody
	public HttpResultModel<MemberResp> Certification(@RequestBody  Member record)
	{
		return  memberService.Certification(record);	
	}	
	
	/**
	 * 获取用户信息  
	 * @param 
	 * @author hulingbo
	 * @date 2016年3月25日10:50:53 
	 * @return
	 */
	@RequestMapping("getuserinfo")
	@ResponseBody
	public Member getUserInfo(@RequestBody  Member record)
	{
		return  memberService.getById(record.getId());	
	}
	
	/**
	 * 获取用户信息  
	 * @param 
	 * @author hulingbo
	 * @date 2016年3月25日10:50:53 
	 * @return
	 */
	@RequestMapping("modify")
	@ResponseBody
	public HttpResultModel<MemberResp> modify(@RequestBody Member record)
	{
		return  memberService.modify(record);	
	}
	
}
