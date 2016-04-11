package com.etaofinance.wap.controllor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.etaofinance.api.redis.RedisService;
import com.etaofinance.api.service.impl.MemberService;
import com.etaofinance.api.service.inter.IMemberService;
import com.etaofinance.core.consts.RedissCacheKey;
import com.etaofinance.core.util.PropertyUtils;
import com.etaofinance.entity.Member;

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
	/**
	 * 登录页面
	 * @return
	 */
	@RequestMapping("login")
	public ModelAndView login(String reUrl)
	{
		ModelAndView view = new ModelAndView("wapView");
		view.addObject("currenttitle", "注册");
		view.addObject("viewPath", "me/login");
		view.addObject("reUrl",reUrl==null?"":reUrl);
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
}
