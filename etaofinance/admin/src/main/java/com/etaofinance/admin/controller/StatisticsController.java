package com.etaofinance.admin.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.etaofinance.api.service.inter.IProjectService;
import com.etaofinance.entity.Comment;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.DataStatistics;
import com.etaofinance.entity.req.PagedCommentReq;

@Controller
@RequestMapping("statistics")
public class StatisticsController {
	
	@Autowired
	private IProjectService projectService;
	
	
	@RequestMapping("index")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "首页");
		model.addObject("currenttitle", "数据统计");
		model.addObject("viewPath", "statistics/index");
		List<DataStatistics> dt =projectService.getDataStatistics();
		model.addObject("listData",dt);
		return model;
	}
}
