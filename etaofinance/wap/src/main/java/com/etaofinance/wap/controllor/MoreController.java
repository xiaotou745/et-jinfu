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
	@RequestMapping("question")
	public ModelAndView question()
	{
		ModelAndView view= new ModelAndView("more/question");
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
		ModelAndView view= new ModelAndView("more/introduce");
		int flag=1;
		Member member=UserContext.getCurrentContext(request).getUserInfo();
		if(member!=null)
		{
			member=memberService.getById(member.getId());
			if(member.getLevel()>1)//2投资人 3领头人
			{
				flag=0;
			}
		}
		view.addObject("flag", flag);
		return view;
	}
}
