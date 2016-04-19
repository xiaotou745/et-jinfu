package com.etaofinance.wap.controllor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.etaofinance.api.redis.RedisService;
import com.etaofinance.api.service.impl.MemberService;
import com.etaofinance.api.service.inter.IMemberService;
import com.etaofinance.api.service.inter.IQAService;
import com.etaofinance.entity.Member;
import com.etaofinance.entity.QA;
import com.etaofinance.wap.common.UserContext;

@RequestMapping("more")
@Controller
public class MoreController {
	@Autowired
	HttpServletRequest request;
	@Autowired
    RedisService redisService;
	@Autowired
	HttpServletResponse response;
	@Autowired
	IQAService qaService;
	@Autowired
	IMemberService memberService;
	/**
	 * 常见问题
	 * @return
	 */
	@RequestMapping("index")
	public ModelAndView index()
	{
		ModelAndView view = new ModelAndView("wapView");
		view.addObject("currenttitle", "更多");
		view.addObject("viewPath", "more/index");
		return view;
	}
	/**
	 * 常见问题
	 * @return
	 */
	@RequestMapping("question")
	public ModelAndView question()
	{
		ModelAndView view = new ModelAndView("wapView");
		view.addObject("currenttitle", "常见问题");
		view.addObject("viewPath", "more/question");
		List<QA> list=qaService.getListForWap();
		view.addObject("qalist", list);
		return view;
	}
	/**
	 * 新手指引
	 * @return
	 */
	@RequestMapping("introduce")
	public ModelAndView introduce()
	{
		ModelAndView view = new ModelAndView("wapView");
		view.addObject("currenttitle", "新手指引");
		view.addObject("viewPath", "more/introduce");
		int flag=1;
		Member member=UserContext.getCurrentContext(request).getUserInfo();
		if(member!=null)
		{
			member=memberService.getById(member.getId());
			if(member.getLevel()>1)//2投资人 3领投人
			{
				flag=0;
			}
		}
		view.addObject("flag", flag);
		return view;
	}
	/**
	 * 项目报名
	 * @return
	 */
	@RequestMapping("projectapply")
	public ModelAndView projectapply()
	{
		ModelAndView view = new ModelAndView("wapView");
		view.addObject("currenttitle", "项目报名");
		view.addObject("viewPath", "more/projectapply");
		Member member=UserContext.getCurrentContext(request).getUserInfo();
		member=(member==null?new Member():member);
		view.addObject("member", member);
		return view;
	}
	/**
	 * 意见反馈
	 * @return
	 */
	@RequestMapping("feedback")
	public ModelAndView feedback()
	{
		ModelAndView view = new ModelAndView("wapView");
		view.addObject("currenttitle", "意见反馈");
		view.addObject("viewPath", "more/feedback");
		Member member=UserContext.getCurrentContext(request).getUserInfo();
		member=(member==null?new Member():member);
		view.addObject("member", member);
		return view;
	}
}
