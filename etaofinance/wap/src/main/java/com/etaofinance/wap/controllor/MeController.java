package com.etaofinance.wap.controllor;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.etaofinance.api.common.LoginHelper;
import com.etaofinance.api.dao.inter.IMemberOtherDao;
import com.etaofinance.api.redis.RedisService;
import com.etaofinance.api.service.impl.MemberOtherService;
import com.etaofinance.api.service.impl.MemberService;
import com.etaofinance.api.service.inter.IBalanceRecordService;
import com.etaofinance.api.service.inter.IBankCardService;
import com.etaofinance.api.service.inter.IMemberOtherService;
import com.etaofinance.api.service.inter.IMemberService;
import com.etaofinance.core.consts.RedissCacheKey;
import com.etaofinance.core.util.CookieUtils;
import com.etaofinance.core.util.PropertyUtils;
import com.etaofinance.entity.BankCard;
import com.etaofinance.entity.Member;
import com.etaofinance.entity.MemberOther;
import com.etaofinance.entity.domain.BalanceRecordDM;
import com.etaofinance.entity.req.PublicMemberReq;
import com.etaofinance.wap.common.LoginUtil;
import com.etaofinance.wap.common.RequireLogin;
import com.etaofinance.wap.common.UserContext;

/**
 * 登录页面
 * 所有的页面Controoler
 * @author ofmyi_000
 *
 */
@RequestMapping("me")
@Controller
public class MeController {

	@Autowired
	HttpServletRequest request;
	@Autowired
    RedisService redisService;
	@Autowired
	HttpServletResponse response;
	@Autowired
	IMemberService memberService;
	@Autowired
	IMemberOtherService memberOtherService;
	@Autowired
	IBankCardService bankService;
	@Autowired
	private IBalanceRecordService balanceRecordService;	
	/**
	 * 登录页面
	 * @return
	 */
	@RequestMapping("login")
	public ModelAndView login(String reUrl)
	{
		ModelAndView view = new ModelAndView("wapView");
		view.addObject("currenttitle", "登录");
		view.addObject("viewPath", "me/login");
		view.addObject("reUrl",reUrl==null?"":reUrl);
		return view;
	}
	/**
	 * 登录页面
	 * @return
	 */
	@RequestMapping("logout")
	public ModelAndView logout()
	{
		ModelAndView view = new ModelAndView("me/logout");
		//删除Cookie
		CookieUtils.deleteCookie(request, response, LoginUtil.LOGIN_COOKIE_NAME);
		return view;
	}
	/**
	 * 注册页面
	 * @return
	 */
	@RequestMapping("register")
	public ModelAndView register(String reUrl)
	{
		ModelAndView view = new ModelAndView("wapView");
		view.addObject("currenttitle", "注册");
		view.addObject("viewPath", "me/register");
		view.addObject("reUrl",reUrl==null?"":reUrl);
		return view;
	}
	/**
	 * 忘记密码第一个页面
	 * @return
	 */
	@RequestMapping("retrievepasswordstep1")
	public ModelAndView retrievepasswordstep1()
	{
		ModelAndView view = new ModelAndView("wapView");
		view.addObject("currenttitle", "找回密码");
		view.addObject("viewPath", "me/retrievepasswordstep1");
		return view;
	}
	/**
	 * 忘记密码第二个页面
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("retrievepasswordstep2")
	public ModelAndView retrievepasswordstep2(Long userId,String checkKey) throws IOException
	{
		String key=String.format(RedissCacheKey.JF_Member_FindPassWordSetpOne,checkKey);
		String value=redisService.get(key, String.class);
		ModelAndView view = new ModelAndView("wapView");
		view.addObject("currenttitle", "找回密码");
		view.addObject("viewPath", "me/retrievepasswordstep2");
		if(value==null||value.equals("")||!value.equals(checkKey))
		{
			ModelAndView view2 = new ModelAndView("wapView");
			view2.addObject("currenttitle", "找回密码");
			view2.addObject("viewPath", "me/retrievepasswordstep1");
			return view2;
		}
		Member member=memberService.getById(userId);
		String phone=member.getPhoneno();
		
		String sString=phone.substring(0,phone.length()-(phone.substring(3)).length())+"****"+phone.substring(7);
		view.addObject("phoneString", sString);
		view.addObject("phone", phone);
		view.addObject("checkKey", checkKey);
		return view;
	}
	/**
	 * 忘记密码第三个页面
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("retrievepasswordstep3")
	public ModelAndView retrievepasswordstep3(String checkKey,Long userId) throws IOException
	{
		String key=String.format(RedissCacheKey.JF_Member_FindPassWordSetpTwo,checkKey);
		String value=redisService.get(key, String.class);
		ModelAndView view= new ModelAndView("wapView");
		view.addObject("currenttitle", "找回密码");
		view.addObject("viewPath", "me/retrievepasswordstep3");
		if(value==null||value.equals("")||!value.equals(checkKey))
		{
			ModelAndView view2= new ModelAndView("wapView");
			view2.addObject("currenttitle", "找回密码");
			view2.addObject("viewPath", "me/retrievepasswordstep1");
			return view2;
		}
		view.addObject("checkKey", checkKey);
		view.addObject("userId", userId);
		return view;
	}
	/**
	 * 用户中心
	 * @param checkKey
	 * @param userId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("usercenter")
	@RequireLogin
	public ModelAndView usercenter() 
	{
		ModelAndView view= new ModelAndView("wapView");
		view.addObject("currenttitle", "个人中心");
		view.addObject("viewPath", "me/usercenter");
		Member member=UserContext.getCurrentContext(request).getUserInfo();
		member=memberService.getById(member.getId());
		MemberOther other=memberOtherService.getByMemberId(member.getId());
		view.addObject("member", member);
		view.addObject("other", other);
		return view;
	}
	/**
	 * 账户信息
	 * @param checkKey
	 * @param userId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("accountblance")
	@RequireLogin
	public ModelAndView accountblance() 
	{
		ModelAndView view= new ModelAndView("wapView");
		view.addObject("currenttitle", "账户信息");
		view.addObject("viewPath", "me/accountblance");
		Member member=UserContext.getCurrentContext(request).getUserInfo();
		MemberOther other=memberOtherService.getByMemberId(member.getId());
		List<BankCard> list=bankService.getListByMemberId(member.getId());
		if(list==null||list.size()==0)
		{
			view.addObject("cardsize", 0);
		}
		else
		{
			view.addObject("cardsize", list.size());
		}
		view.addObject("other", other);
		return view;
	}
	/**
	 * 账户流水
	 * @param checkKey
	 * @param userId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("accountflow")
	@RequireLogin
	public ModelAndView accountflow() 
	{
		ModelAndView view= new ModelAndView("wapView");
		view.addObject("currenttitle", "账户流水");
		view.addObject("viewPath", "me/accountflow");
		Member member=UserContext.getCurrentContext(request).getUserInfo();
		PublicMemberReq record =new PublicMemberReq();
		record.setMemberId(member.getId());
		List<BalanceRecordDM> list=balanceRecordService.getListMore(record);
		view.addObject("list", list);
		return view;
	}
}
