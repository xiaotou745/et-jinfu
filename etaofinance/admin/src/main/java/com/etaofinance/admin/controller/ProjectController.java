package com.etaofinance.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.etaofinance.api.service.inter.IProjectService;
import com.etaofinance.api.service.inter.IProjectStrategyService;
import com.etaofinance.core.enums.ProjectAuditStatus;
import com.etaofinance.entity.Project;
import com.etaofinance.entity.ProjectStrategy;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.req.PagedProjectReq;
/**
 * 项目管理
 * @author ofmyi_000
 *
 */
@Controller
@RequestMapping("project")
public class ProjectController {
	@Autowired
	private IProjectService projectService;
	@Autowired
	private IProjectStrategyService projectStrategyService;
	/**
	 * 项目列表查询页
	 * @return
	 */
	@RequestMapping("list")
	public ModelAndView list() {
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "项目管理");
		view.addObject("currenttitle", "项目列表");
		view.addObject("viewPath", "project/list");
		return view;
	}
	@RequestMapping("listdo")
	public ModelAndView listDo(PagedProjectReq req) {
		ModelAndView view = new ModelAndView("project/listdo");
		req.setAuditStatus(ProjectAuditStatus.AuditPass.value());
		PagedResponse<Project> listData=projectService.queryProjectList(req);
		view.addObject("listData", listData);
		return view;
	}
	/**
	 * 待审核项目列表查询页
	 * @return
	 */
	@RequestMapping("waitlist")
	public ModelAndView waitList() {
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "项目管理");
		view.addObject("currenttitle", "待审项目列表");
		view.addObject("viewPath", "project/waitlist");
		return view;
	}
	@RequestMapping("waitlistdo")
	public ModelAndView waitListDo(PagedProjectReq req) {
		ModelAndView view = new ModelAndView("project/waitlistdo");
		PagedResponse<Project> listData=projectService.queryProjectList(req);
		view.addObject("listData", listData);
		Map<Long,String> strategyMap=getStrategyMap(listData);
		view.addObject("strategyMap", strategyMap);
		return view;
	}
	private Map<Long,String> getStrategyMap(PagedResponse<Project> listData){
		List<Long> projectIds=listData.getResultList().stream().map(k->k.getId()).collect(Collectors.toList());
		List<ProjectStrategy> strategyList=projectStrategyService.getByProjectIds(projectIds);
		Map<Long,String> resultMap=new HashMap<Long, String>();

		Map<Long, List<ProjectStrategy>> result =strategyList.stream().collect(Collectors.groupingBy(ProjectStrategy::getProjectid));
		for (Long projectId : result.keySet()) {
			List<String> valueList=result.get(projectId).stream().map(k->k.getValue().toString()).collect(Collectors.toList());
			resultMap.put(projectId, String.join("-", valueList));
		}
		return resultMap;
		}
	/**
	 * 项目列表查询页
	 * @return
	 */
	@RequestMapping("newproject")
	public ModelAndView newproject() {
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "项目管理");
		view.addObject("currenttitle", "发布项目");
		view.addObject("viewPath", "project/newproject");
		return view;
	}

}
