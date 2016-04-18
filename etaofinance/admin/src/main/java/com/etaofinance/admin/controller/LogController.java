package com.etaofinance.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.etaofinance.api.service.inter.IAdminOptLogService;
import com.etaofinance.api.service.inter.IMemberService;
import com.etaofinance.entity.AdminOptLog;
import com.etaofinance.entity.FeedBack;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.req.PagedAdminoptLogReq;
import com.etaofinance.entity.req.PagedFeedBackReq;

@Controller
@RequestMapping("log")
public class LogController {
	
	@Autowired
	private IAdminOptLogService adminOptLogService;	
	
	
	@RequestMapping("list")
	public ModelAndView list() {
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "首页");
		model.addObject("currenttitle", "操作日志");
		model.addObject("viewPath", "log/list");
		return model;
	}

	@RequestMapping("listdo")
	public ModelAndView listdo(PagedAdminoptLogReq req) { 
		ModelAndView model = new ModelAndView("log/listdo");
		PagedResponse<AdminOptLog> List = new PagedResponse<AdminOptLog>();
		List=adminOptLogService.getAdminOptLogList(req);
		model.addObject("listData",List);
		return model;
	}
}
