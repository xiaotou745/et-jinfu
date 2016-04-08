package com.etaofinance.wap.controllor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.etaofinance.api.redis.RedisService;
import com.etaofinance.api.service.inter.IQAService;
import com.etaofinance.entity.QA;

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
	/**
	 * 登录页面
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
}
