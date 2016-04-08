package com.etaofinance.wap.controllor;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.etaofinance.api.service.inter.IADVertService;



/**
 * 首页
 * @author ofmyi_000
 *
 */
@Controller
@RequestMapping("home")
public class HomeController {

	@Autowired
	HttpServletRequest request;
	@Autowired
	IADVertService adService;
	/**
	 * 首页
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index()
	{
		ModelAndView view= new ModelAndView("home/index");
		//1.获取轮播图.
		adService.getListForWap();
		//2.获取新手专享
		return view;
	}
}
