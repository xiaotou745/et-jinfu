package com.etaofinance.wap.controllor;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.etaofinance.api.service.inter.IADVertService;
import com.etaofinance.api.service.inter.IProjectService;
import com.etaofinance.api.service.inter.IProjectSubscriptionService;
import com.etaofinance.entity.ADVert;
import com.etaofinance.entity.Member;
import com.etaofinance.entity.Project;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.ProjectMember;
import com.etaofinance.entity.domain.ProjectModel;
import com.etaofinance.entity.req.PagedProjectReq;



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
	@Autowired
	IProjectSubscriptionService projectSubscriptionService;
	/**
	 * 首页
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index()
	{
		ModelAndView view = new ModelAndView("wapView");
		view.addObject("currenttitle", "网站主页---");
		view.addObject("viewPath", "home/index");
		//1.获取轮播图.
		List<ADVert> list=adService.getListForWap();
		//2.获取新手专享
		List<ProjectModel> projectList=projectService.getNoviceProject();
		//3.首页15条项目数据
		PagedProjectReq req=new PagedProjectReq();
		req.setTypeId(0);
		req.setCurrentPage(1);
		req.setAuditStatus(0);
		List<ProjectModel> itemList=projectService.getProjectList(req).getResultList();
		view.addObject("ADLIST", list);
		view.addObject("proList", projectList);
		view.addObject("itemList", itemList);
		return view;
	}
	/**
	 * 项目列表异步分页
	 * @param req
	 * @return
	 */
	@RequestMapping("/listdo")
	public ModelAndView listdo(PagedProjectReq req)
	{
		ModelAndView view = new ModelAndView("home/listdo");
		PagedResponse<ProjectModel> result=projectService.getProjectList(req);
		view.addObject("result", result);
		return view;
	}
	/**
	 * 项目列表异步分页
	 * @param req
	 * @return
	 */
	@RequestMapping("/detail")
	public ModelAndView detail(Long projectid)
	{
		ModelAndView view = new ModelAndView("wapView");
		view.addObject("currenttitle", "项目详情");
		view.addObject("viewPath", "home/detail");
		//1.项目详情
		ProjectModel detaiModel=projectService.getProjectDetail(projectid);
		//2.投资人
		List<ProjectMember> subList=projectSubscriptionService.getProjectLeadMember(projectid);
		//3.领头人
		List<ProjectMember> leadList=new ArrayList<ProjectMember>();
		if(subList!=null)
		{
			for (int i = 0; i < subList.size(); i++) {
				if(subList.get(i).getIsLead()==1)
				{
					leadList.add(subList.get(i));
				}
			}
		}
		//3.项目概况,回报说明图片.
		//4.项目交流以及评论
		//5.认投人列表
		//PagedResponse<ProjectModel> result=projectService.getProjectList(req);
		//view.addObject("result", result);
		return view;
	}
}
