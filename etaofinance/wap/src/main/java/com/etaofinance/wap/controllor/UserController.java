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
import com.etaofinance.core.enums.MemberEnum;
import com.etaofinance.core.enums.MemberOtherCreatePayPwdEnum;
import com.etaofinance.core.security.MD5Util;
import com.etaofinance.core.util.CookieUtils;
import com.etaofinance.core.util.JsonUtil;
import com.etaofinance.core.util.PropertyUtils;
import com.etaofinance.core.util.RegexHelper;
import com.etaofinance.entity.Member;
import com.etaofinance.entity.MemberApply;
import com.etaofinance.entity.MemberOther;
import com.etaofinance.entity.req.CreatePayPwdReq;
import com.etaofinance.entity.req.ForgetPayPwdReq;
import com.etaofinance.entity.req.ForgetPwdOneReq;
import com.etaofinance.entity.req.ForgetPwdThreeReq;
import com.etaofinance.entity.req.ForgetPwdTwoReq;
import com.etaofinance.entity.req.LoginReq;
import com.etaofinance.entity.req.ModifyPayPwdReq;
import com.etaofinance.entity.req.ModifyPhoneByMessageReq;
import com.etaofinance.entity.req.ModifyPhoneByPayReq;
import com.etaofinance.entity.req.ModifypwdReq;
import com.etaofinance.entity.req.RegistReq;
import com.etaofinance.entity.req.SendCodeReq;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.domain.MemberDM;
import com.etaofinance.entity.resp.CreatePayPwdResp;
import com.etaofinance.entity.resp.ForgetPayPwdResp;
import com.etaofinance.entity.resp.ForgetPwdResp;
import com.etaofinance.entity.resp.ModifyPayPwdResp;
import com.etaofinance.entity.resp.ModifyPhoneByPayResp;
import com.etaofinance.entity.resp.ModifyPhoneResp;
import com.etaofinance.wap.common.LoginUtil;
import com.etaofinance.wap.common.RequireLogin;
import com.etaofinance.wap.common.UserContext;
import com.wordnik.swagger.annotations.ApiOperation;


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
	 * @throws IOException 
	 */
	@RequestMapping("regist")
	@ResponseBody
	@ApiOperation(value = "注册", httpMethod = "POST", 
	consumes="application/json;charset=UFT-8",produces="application/json;charset=UFT-8",
	notes = "用户注册")
	public  HttpResultModel<Member> regist(@RequestBody RegistReq req) throws IOException {
		HttpResultModel<Member> resultModel=  memberService.regist(req);
		if(resultModel.getCode()>0)//注册成功,进行登录
		{
			//登录成功 设置缓存
			String uuid=UUID.randomUUID().toString();//生成该次登录的UUID
			String rediskey=String.format(RedissCacheKey.LOGIN_COOKIE, uuid);
			String redisValue=JsonUtil.obj2string(resultModel.getData());
			redisService.set(rediskey, redisValue,60*60*24,TimeUnit.SECONDS);
			//设置COOKIE
			CookieUtils.setCookie(request,response,LoginUtil.LOGIN_COOKIE_NAME, uuid, 60*60*24,true);
			if(req.getReUrl()!=null&&!req.getReUrl().equals(""))
			{
				resultModel.setUrl(req.getReUrl());
			}
			else {
				String basePath = PropertyUtils.getProperty("java.wap.url")+"/home/index";
				resultModel.setUrl(basePath);
			}
		}
		return resultModel;
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
			result.setCode(1);
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
		if(req.getReUrl()!=null&&!req.getReUrl().equals(""))
		{
			result.setUrl(req.getReUrl());	
		}
		else {
			String basePath = PropertyUtils.getProperty("java.wap.url")+"/home/index";
			result.setUrl(basePath);
		}
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
	@RequireLogin
	@ApiOperation(value = "获取用户信息  ", httpMethod = "POST", 
	consumes="application/json;charset=UFT-8",produces="application/json;charset=UFT-8",
	notes = "获取用户信息  ")
	public HttpResultModel<MemberDM> getUserInfo()
	{
		Long memberid=UserContext.getCurrentContext(request).getUserInfo().getId();	
		HttpResultModel<MemberDM> resp = new HttpResultModel<MemberDM>();		
		MemberDM memberDM=memberService.getUserInfo(memberid);	 		
		if(memberDM==null)
		{
			resp.setCode(MemberEnum.GetUserErr.value());
			resp.setMsg(MemberEnum.GetUserErr.desc());
			return resp;	
		}		
	
		resp.setData(memberDM);
		resp.setCode(MemberEnum.Success.value());
		resp.setMsg(MemberEnum.Success.desc());	
		return resp;
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
	@RequireLogin
	public HttpResultModel<Object> modify(@RequestBody Member record)
	{
		Long memberid=UserContext.getCurrentContext(request).getUserInfo().getId();	
		record.setId(memberid);
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
	 * 通过发送短信修改手机号码 第1步
	 * @param 
	 * @author hulingbo
	 * @date 2016年4月13日20:07:05
	 * @return
	 */
	@RequestMapping("modifyphonebymessageone")
	@ResponseBody
	@RequireLogin
	public HttpResultModel<ModifyPhoneResp> modifyPhoneByMessageOne(@RequestBody  ModifyPhoneByMessageReq req)
	{
		req.setUserId(UserContext.getCurrentContext(request).getUserInfo().getId());
		return memberService.modifyPhoneByMessageOne(req);
	}
	
	/**
	 * 修改手机号码第2步
	 * @param 
	 * @author hulingbo
	 * @date 2016年4月13日20:07:12
	 * @return
	 */
	@RequestMapping("modifyphonetwo")
	@ResponseBody
	@RequireLogin
	public HttpResultModel<ModifyPhoneResp> modifyPhonewo(@RequestBody  ModifyPhoneByMessageReq req)
	{
		req.setUserId(UserContext.getCurrentContext(request).getUserInfo().getId());
		return memberService.modifyPhoneTwo(req);
	}
	
	
	/**
	 * 通过支付密码修改手机号码 第1步
	 * @param 
	 * @author hulingbo
	 * @date 2016年4月13日20:07:05
	 * @return
	 */
	@RequestMapping("modifyphonebypayone")
	@ResponseBody
	@RequireLogin
	public HttpResultModel<ModifyPhoneByPayResp> modifyPhoneByPayOne(@RequestBody  ModifyPhoneByPayReq req)
	{
		req.setMemberId(UserContext.getCurrentContext(request).getUserInfo().getId());
		return memberService.modifyPhoneByPayOne(req);
	}
	
	/**
	 * 创建支付密码第1步
	 * @param 
	 * @author hulingbo
	 * @date 2016年4月13日15:36:56
	 * @return
	 */
	@RequestMapping("createpaypwdone")
	@ResponseBody
	@RequireLogin
	public HttpResultModel<CreatePayPwdResp> createPayPwdOne(@RequestBody  CreatePayPwdReq req)
	{	
		Member m=UserContext.getCurrentContext(request).getUserInfo();
		req.setUserId(m.getId());
		return memberOtherService.createPayPwdOne(req);
	}
	
	/**
	 * 创建支付密码第2步
	 * @param 
	 * @author hulingbo
	 * @date 2016年4月13日15:36:56
	 * @return
	 */
	@RequestMapping("createpaypwdtwo")
	@ResponseBody
	@RequireLogin
	public HttpResultModel<CreatePayPwdResp> createPayPwdTwo(@RequestBody  CreatePayPwdReq req)
	{	
		Member m=UserContext.getCurrentContext(request).getUserInfo();
		req.setUserId(m.getId());
		return memberOtherService.createPayPwdTwo(req);
	}
	
	
	/**
	 * 修改支付密码第1步
	 * @param 
	 * @author hulingbo
	 * @date 2016年4月13日15:36:56
	 * @return
	 */
	@RequestMapping("modifypaypwdone")
	@ResponseBody
	@RequireLogin
	public HttpResultModel<ModifyPayPwdResp> modifyPayPwdOne(@RequestBody  ModifyPayPwdReq req)
	{	
		Member m=UserContext.getCurrentContext(request).getUserInfo();
		req.setUserId(m.getId());
		return memberOtherService.modifyPayPwdOne(req);
	}

	/**
	 * 修改支付密码第2步
	 * @param 
	 * @author hulingbo
	 * @date 2016年4月13日15:36:56
	 * @return
	 */
	@RequestMapping("modifypaypwdtwo")
	@ResponseBody
	@RequireLogin
	public HttpResultModel<ModifyPayPwdResp> modifyPayPwdTwo(@RequestBody  ModifyPayPwdReq req)
	{	
		Member m=UserContext.getCurrentContext(request).getUserInfo();
		req.setUserId(m.getId());
		return memberOtherService.modifyPayPwdTwo(req);
	}
	
	
	/**
	 * 找回支付密码第1步
	 * @param 
	 * @author hulingbo
	 * @date 2016年4月13日15:36:56
	 * @return
	 */
	@RequestMapping("forgetpaypwdone")
	@ResponseBody
	public HttpResultModel<ForgetPayPwdResp> forgetPayPwdOne(@RequestBody  ForgetPayPwdReq req)
	{	
		return memberOtherService.forgetPayPwdOne(req);
	}

//	/**
//	 * 找回支付密码第2步
//	 * @param 
//	 * @author hulingbo
//	 * @date 2016年4月13日15:36:56
//	 * @return
//	 */
//	@RequestMapping("forgetpaypwdtwo")
//	@ResponseBody
//	public HttpResultModel<ForgetPayPwdResp> modifyPayPwdTwo(@RequestBody  ForgetPayPwdReq req)
//	{	
//		return memberOtherService.forgetPayPwdTwo(req);
//	}
//	
//	/**
//	 * 创建支付密码
//	 * 修改，找回
//	 * @param 
//	 * @author hulingbo
//	 * @date 2016年3月28日16:31:15
//	 * @return
//	 */
//	@RequestMapping("createpaypwd")
//	@ResponseBody
//	@RequireLogin
//	@ApiOperation(value = "创建支付密码", httpMethod = "POST", 
//	consumes="application/json;charset=UFT-8",produces="application/json;charset=UFT-8",
//	notes = "创建支付密码")
//	public HttpResultModel<Object> createPayPwd(@RequestBody  MemberOther record)
//	{
//		Member member=UserContext.getCurrentContext(request).getUserInfo();		
//		record.setMemberid(member.getId());			
//
//		return memberOtherService.createPayPwd(record);
//	}
//	
	
//	/**
//	 * 验证支付密码
//	 * @param 
//	 * @author hulingbo
//	 * @date 2016年3月28日17:13:36
//	 * @return
//	 */
//	@RequestMapping("verificationpaypwd")
//	@ResponseBody
//	@RequireLogin
//	@ApiOperation(value = "验证支付密码", httpMethod = "POST", 
//	consumes="application/json;charset=UFT-8",produces="application/json;charset=UFT-8",
//	notes = "验证支付密码")
//	public HttpResultModel<Object> verificationPayPwd(@RequestBody  MemberOther record)
//	{
//		Long memberid=UserContext.getCurrentContext(request).getUserInfo().getId();
//		record.setMemberid(memberid);
//		return memberOtherService.verificationPayPwd(record);
//	}
	
	/**
	 * 会员实名认证
	 * @param 
	 * @author hulingbo
	 * @date 2016年3月24日18:05:14
	 * @return
	 */
	@RequestMapping("certification")
	@ResponseBody
	@RequireLogin
	@ApiOperation(value = "会员实名认证", httpMethod = "POST", 
	consumes="application/json;charset=UFT-8",produces="application/json;charset=UFT-8",
	notes = "会员实名认证")
	public HttpResultModel<Object> certification(@RequestBody  Member record)
	{
		Long memberid=UserContext.getCurrentContext(request).getUserInfo().getId();
		record.setId(memberid);
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
	@RequireLogin
	@ApiOperation(value = "领头人,投资人认证", httpMethod = "POST", 
	consumes="application/json;charset=UFT-8",produces="application/json;charset=UFT-8",
	notes = "领头人,投资人认证")
	public HttpResultModel<Object> certificationInvestor(@RequestBody  MemberApply record)
	{
		Long memberid=UserContext.getCurrentContext(request).getUserInfo().getId();
		record.setMemberid(memberid);
		return  memberApplyService.create(record);	
	}	
	
	
	/**
	 * 发起邮箱绑定请求
	 * @param member  {"id":"1","phoneno":"110","email":"110@qq.com"}
	 * @return
	 */
	@RequestMapping("bindemail")
	@ResponseBody
	@ApiOperation(value = "发送邮箱绑定验证", httpMethod = "POST", 
	consumes="application/json;charset=UFT-8",produces="application/json;charset=UFT-8",
	notes = "发送邮箱绑定验证")
	public HttpResultModel<Object> bindEmail(@RequestBody Member member)
	{
		HttpResultModel<Object> res = null;
		
		res = memberService.bindEmail(member);
		
		return res;
	}
	
	/**
	 * 邮箱绑定回调
	 * 
	 * @param idAndEmail 
	 * @return
	 */
	@RequestMapping("emailbindcallback")
	@ResponseBody
	@ApiOperation(value = "邮箱绑定回调", httpMethod = "GET", 
	consumes="application/json;charset=UFT-8",produces="application/json;charset=UFT-8",
	notes = "邮箱绑定回调")
	public HttpResultModel<Object> bindEmailcallback(String idAndEmail)
	{
		HttpResultModel<Object> res = null;
		
		res = memberService.bindEmailCallBk(idAndEmail);
		//todo: 此处 最好返回一个友好的验证成功的页面
		return res;
	}
	
}
