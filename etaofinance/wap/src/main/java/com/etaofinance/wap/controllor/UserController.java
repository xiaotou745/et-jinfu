package com.etaofinance.wap.controllor;



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
import com.etaofinance.api.service.inter.IMemberOtherService;
import com.etaofinance.api.service.inter.IMemberService;
import com.etaofinance.core.consts.RedissCacheKey;
import com.etaofinance.core.security.MD5Util;
import com.etaofinance.core.util.CookieUtils;
import com.etaofinance.core.util.JsonUtil;
import com.etaofinance.core.util.RegexHelper;
import com.etaofinance.entity.Member;
import com.etaofinance.entity.MemberOther;
import com.etaofinance.entity.req.ForgetPwdOneReq;
import com.etaofinance.entity.req.ForgetPwdThreeReq;
import com.etaofinance.entity.req.ForgetPwdTwoReq;
import com.etaofinance.entity.req.LoginReq;
import com.etaofinance.entity.req.ModifypwdReq;
import com.etaofinance.entity.req.RegistReq;
import com.etaofinance.entity.req.SendCodeReq;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.resp.ForgetPwdResp;
import com.etaofinance.entity.resp.MemberResp;
import com.etaofinance.entity.resp.SendCodeResp;
import com.etaofinance.wap.common.LoginUtil;
import com.etaofinance.wap.common.UserContext;

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
	private IMemberOtherService bankCardService;
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	@Autowired
	RedisService redisService;
	
	/**
	 * 获取验证码接口
	 * @param req
	 * @return
	 */
	@RequestMapping("sendcode")
	@ResponseBody
	public  HttpResultModel<Object> sendcode(@RequestBody SendCodeReq req) {
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
	 * 登录
	 * @param req
	 * @return
	 */
	@RequestMapping("login")
	@ResponseBody
	public  HttpResultModel<Member> login(@RequestBody LoginReq req) {
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
		result.setCode(1);
		result.setMsg("登录成功");
		result.setData(member);
		return result;				
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
	/**
	 * 获取图形验证码
	 * @param 
	 * @author ruhuaxiao
	 * @date 2016年3月25日16:53:16
	 * @return
	 */
	@RequestMapping("code")
	public ModelAndView code(int type) {
		ModelAndView mv = new ModelAndView("user/code");
		mv.addObject("CodeType", type);
		return mv;
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
	public HttpResultModel<Object> modifypwd(@RequestBody  ModifypwdReq req)
	{
		HttpResultModel<Object> resultModel=new HttpResultModel<Object>();
		Member m=UserContext.getCurrentContext(request).getUserInfo();
		if(m.equals(null))
		{
			resultModel.setCode(-1);
			resultModel.setMsg("用户未登录,请先登录用户!");
			return resultModel;
		}
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
	public HttpResultModel<Object> createPayPwd(@RequestBody  MemberOther record)
	{
		HttpResultModel<Object> resultModel=new HttpResultModel<Object>();
//		Member m=UserContext.getCurrentContext(request).getUserInfo();
//		if(m.equals(null))
//		{
//			resultModel.setCode(-1);
//			resultModel.setMsg("用户未登录,请先登录用户!");
//			return resultModel;
//		}
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
	public HttpResultModel<Object> verificationPayPwd(@RequestBody  MemberOther record)
	{
		HttpResultModel<Object> resultModel=new HttpResultModel<Object>();
//		Member m=UserContext.getCurrentContext(request).getUserInfo();
//		if(m.equals(null))
//		{
//			resultModel.setCode(-1);
//			resultModel.setMsg("用户未登录,请先登录用户!");
//			return resultModel;
//		}
		long tempUserid=(long)(1);
		record.setMemberid(tempUserid);
		return memberOtherService.verificationPayPwd(record);
	}
	
}
