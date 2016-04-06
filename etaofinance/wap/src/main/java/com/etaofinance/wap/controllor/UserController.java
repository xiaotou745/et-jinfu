package com.etaofinance.wap.controllor;



import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;















import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.etaofinance.api.redis.RedisService;
import com.etaofinance.api.service.inter.IMemberApplyService;
import com.etaofinance.api.service.inter.IMemberOtherService;
import com.etaofinance.api.service.inter.IMemberService;
import com.etaofinance.core.consts.RedissCacheKey;
import com.etaofinance.core.security.MD5Util;
import com.etaofinance.core.util.CookieUtils;
import com.etaofinance.core.util.JsonUtil;
import com.etaofinance.core.util.PropertyUtils;
import com.etaofinance.core.util.RegexHelper;
import com.etaofinance.entity.Member;
import com.etaofinance.entity.MemberApply;
import com.etaofinance.entity.MemberOther;
import com.etaofinance.entity.req.ForgetPwdOneReq;
import com.etaofinance.entity.req.ForgetPwdThreeReq;
import com.etaofinance.entity.req.ForgetPwdTwoReq;
import com.etaofinance.entity.req.LoginReq;
import com.etaofinance.entity.req.ModifypwdReq;
import com.etaofinance.entity.req.RegistReq;
import com.etaofinance.entity.req.SendCodeReq;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.common.ResponseBase;
import com.etaofinance.entity.resp.ForgetPwdResp;
import com.etaofinance.entity.resp.MemberResp;
import com.etaofinance.entity.resp.SendCodeResp;
import com.etaofinance.wap.common.LoginUtil;
import com.etaofinance.wap.common.NoRequireLogin;
import com.etaofinance.wap.common.RequireLogin;
import com.etaofinance.wap.common.UserContext;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

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
	IMemberOtherService memberOtherService;	
	
	@Autowired
	IMemberApplyService memberApplyService;	
	
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	@Autowired
	RedisService redisService;
	
	
	/**
	 * 注册
	 * @param req
	 * @return
	 */
	@RequestMapping("regist")
	@ResponseBody
	@ApiOperation(value = "注册", httpMethod = "POST", 
	consumes="application/json;charset=UFT-8",produces="application/json;charset=UFT-8",
	notes = "用户注册")
	public  HttpResultModel<Member> regist(@RequestBody RegistReq req) {
		return  memberService.regist(req);			
	}
	
	/**
	 * 登录
	 * @param req
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("login")
	@ResponseBody
	@ApiOperation(value = "登录", httpMethod = "POST", 
	consumes="application/json;charset=UFT-8",produces="application/json;charset=UFT-8",
	notes = "登录")
	public  HttpResultModel<Member> login(@RequestBody LoginReq req) throws IOException {
		HttpResultModel<Member> result=new HttpResultModel<Member>(); 
		int cookieMaxAge = 60*60*24;//cookie时间 1天
		if(req.getRemberMe().endsWith("1"))//记住我
		{
			cookieMaxAge=60*60*24*30;
		}
		if(LoginUtil.checkIsLogin(request, response))//已经登录
		{
			Member member=UserContext.getCurrentContext(request).getUserInfo();
			result.setCode(-1);
			result.setData(member);
			return result;	
		}
		Member member=null;
		//进行登录流程 1.手机号 2 邮箱 3用户名 
		if(RegexHelper.IsPhone(req.getLoginName()))
		{
			member=memberService.selectByPhoneNo(req.getLoginName());
		}
		//邮箱
		if(member==null&&RegexHelper.IsEmail(req.getLoginName()))
		{
			member=memberService.selectByEmail(req.getLoginName());
		}
		//用户名
		if(member==null)
		{
			member=memberService.selectByUserName(req.getLoginName());
		}
		//用户不存在 或者密码不匹配
		if(member==null||!member.getLoginpwd().equals(MD5Util.MD5(req.getPwd())))
		{
			result.setCode(-1);
			result.setMsg("用户名或密码错误,请重试");
			return result;	
		}
		//登录成功 设置缓存
		String uuid=UUID.randomUUID().toString();//生成该次登录的UUID
		String rediskey=String.format(RedissCacheKey.LOGIN_COOKIE, uuid);
		String redisValue=JsonUtil.obj2string(member);
		redisService.set(rediskey, redisValue,cookieMaxAge,TimeUnit.SECONDS);
		//设置COOKIE
		CookieUtils.setCookie(request,response,LoginUtil.LOGIN_COOKIE_NAME, uuid, cookieMaxAge,true);
		if(req.getReUrl()==null||req.getReUrl().equals(""))
		{
			String basePath =PropertyUtils.getProperty("java.wap.url");
			response.sendRedirect(basePath + "/"+req.getReUrl());
		}
		result.setCode(1);
		result.setMsg("登录成功");
		result.setData(member);
		return result;				
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
	@ApiOperation(value = "获取用户信息  ", httpMethod = "POST", 
	consumes="application/json;charset=UFT-8",produces="application/json;charset=UFT-8",
	notes = "获取用户信息  ")
	public Member getUserInfo(@RequestBody  Member record)
	{
		return  memberService.getById(record.getId());	
	}
	/**
	 * 修改用户信息  
	 * @param 
	 * @author hulingbo
	 * @date 2016年3月25日10:50:53 
	 * @return
	 */
	@ApiOperation(value = "修改用户信息  ", httpMethod = "POST", 
	consumes="application/json;charset=UFT-8",produces="application/json;charset=UFT-8",
	notes = "修改用户信息  ")
	@RequestMapping("modify")
	@ResponseBody
	public HttpResultModel<MemberResp> modify(@RequestBody Member record)
	{
		return  memberService.modify(record);	
	}
	/**
	 * 忘记密码第一步
	 * @param 
	 * @author hulingbo
	 * @date 2016年3月24日18:05:14
	 * @return
	 */
	@RequestMapping("forgetpwdsetpone")
	@ResponseBody
	@ApiOperation(value = "忘记密码第一步", httpMethod = "POST", 
	consumes="application/json;charset=UFT-8",produces="application/json;charset=UFT-8",
	notes = "忘记密码第一步")
	public HttpResultModel<ForgetPwdResp> forgetpwdsetpone(@RequestBody  ForgetPwdOneReq req)
	{
		HttpResultModel<ForgetPwdResp> res=new HttpResultModel<ForgetPwdResp>();
		String cookieKey=CookieUtils.getCookie(request,LoginUtil.ADMIN_JSESSIONID);
		//没有获取到验证码的UUID
		if(cookieKey==null&&cookieKey.equals(""))
		{
			res.setCode(-1);
			res.setMsg("请输入正确的验证码");
			return res;
		}
		req.setCookieKey(cookieKey);
		return memberService.forgetpwdsetpone(req);
	}
	/**
	 * 忘记密码第二步
	 * @param 
	 * @author hulingbo
	 * @date 2016年3月24日18:05:14
	 * @return
	 */
	@RequestMapping("forgetpwdsetptwo")
	@ResponseBody
	@ApiOperation(value = "忘记密码第二步", httpMethod = "POST", 
	consumes="application/json;charset=UFT-8",produces="application/json;charset=UFT-8",
	notes = "忘记密码第二步")
	public HttpResultModel<ForgetPwdResp> forgetpwdsetptwo(@RequestBody  ForgetPwdTwoReq req)
	{
		return memberService.forgetpwdsetptwo(req);
	}
	/**
	 * 忘记密码第三步
	 * @param 
	 * @author hulingbo
	 * @date 2016年3月24日18:05:14
	 * @return
	 */
	@RequestMapping("forgetpwdsetpthree")
	@ResponseBody
	@ApiOperation(value = "忘记密码第三步", httpMethod = "POST", 
	consumes="application/json;charset=UFT-8",produces="application/json;charset=UFT-8",
	notes = "忘记密码第三步")
	public HttpResultModel<ForgetPwdResp> forgetpwdsetpthree(@RequestBody  ForgetPwdThreeReq req)
	{
		return memberService.forgetpwdsetpthree(req);
	}
	/**
	 * 修改密码
	 * @param 
	 * @author hulingbo
	 * @date 2016年3月24日18:05:14
	 * @return
	 */
	@RequestMapping("modifypwd")
	@ResponseBody
	@RequireLogin
	@ApiOperation(value = "修改密码", httpMethod = "POST", 
	consumes="application/json;charset=UFT-8",produces="application/json;charset=UFT-8",
	notes = "修改密码")
	public HttpResultModel<Object> modifypwd(@RequestBody  ModifypwdReq req)
	{
		Member m=UserContext.getCurrentContext(request).getUserInfo();
		req.setUserId(m.getId());
		return memberService.modifypwd(req);
	}
	
	/**
	 * 创建支付密码
	 * @param 
	 * @author hulingbo
	 * @date 2016年3月28日16:31:15
	 * @return
	 */
	@RequestMapping("createpaypwd")
	@ResponseBody
	@RequireLogin
	@ApiOperation(value = "创建支付密码", httpMethod = "POST", 
	consumes="application/json;charset=UFT-8",produces="application/json;charset=UFT-8",
	notes = "创建支付密码")
	public HttpResultModel<Object> createPayPwd(@RequestBody  MemberOther record)
	{
		HttpResultModel<Object> resultModel=new HttpResultModel<Object>();
		long tempUserid=(long)(1);
		record.setMemberid(tempUserid);
		return memberOtherService.createPayPwd(record);
	}
	
	
	/**
	 * 验证支付密码
	 * @param 
	 * @author hulingbo
	 * @date 2016年3月28日17:13:36
	 * @return
	 */
	@RequestMapping("verificationpaypwd")
	@ResponseBody
	@RequireLogin
	@ApiOperation(value = "验证支付密码", httpMethod = "POST", 
	consumes="application/json;charset=UFT-8",produces="application/json;charset=UFT-8",
	notes = "验证支付密码")
	public HttpResultModel<Object> verificationPayPwd(@RequestBody  MemberOther record)
	{
		HttpResultModel<Object> resultModel=new HttpResultModel<Object>();
		long tempUserid=(long)(1);
		record.setMemberid(tempUserid);
		return memberOtherService.verificationPayPwd(record);
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
	@ApiOperation(value = "会员实名认证", httpMethod = "POST", 
	consumes="application/json;charset=UFT-8",produces="application/json;charset=UFT-8",
	notes = "会员实名认证")
	public HttpResultModel<MemberResp> certification(@RequestBody  Member record)
	{
		return  memberService.Certification(record);	
	}	
	
	/**
	 * 领头人,投资人认证
	 * @param 
	 * @author hulingbo
	 * @date 2016年3月24日18:05:14
	 * @return
	 */
	@RequestMapping("certificationinvestor")
	@ResponseBody
	@ApiOperation(value = "领头人,投资人认证", httpMethod = "POST", 
	consumes="application/json;charset=UFT-8",produces="application/json;charset=UFT-8",
	notes = "领头人,投资人认证")
	public HttpResultModel<ResponseBase> certificationInvestor(@RequestBody  MemberApply record)
	{
		return  memberApplyService.create(record);	
	}	
}
