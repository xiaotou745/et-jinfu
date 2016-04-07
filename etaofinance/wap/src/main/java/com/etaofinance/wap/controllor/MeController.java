package com.etaofinance.wap.controllor;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	/**
	 * 登录页面
	 * @return
	 */
	@RequestMapping("login")
	public ModelAndView login()
	{
		ModelAndView view= new ModelAndView("me/login");
		return view;
	}
	/**
	 * 注册页面
	 * @return
	 */
	@RequestMapping("register")
	public ModelAndView register()
	{
		ModelAndView view= new ModelAndView("me/register");
		return view;
	}
}
