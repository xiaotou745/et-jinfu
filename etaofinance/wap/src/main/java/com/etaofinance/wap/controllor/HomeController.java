package com.etaofinance.wap.controllor;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.etaofinance.api.service.inter.IADVertService;
import com.etaofinance.api.service.inter.IProjectService;
import com.etaofinance.entity.ADVert;
import com.etaofinance.entity.Project;
import com.etaofinance.entity.domain.ProjectModel;



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
	@Autowired
	IProjectService projectService;
	/**
	 * 首页
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index()
	{
		ModelAndView view= new ModelAndView("home/index");
		//1.获取轮播图.
		List<ADVert> list=adService.getListForWap();
		//2.获取新手专享
		List<ProjectModel> projectList=projectService.getNoviceProject();
		return view;
	}
}
