package com.etaofinance.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.etaofinance.admin.common.UserContext;
import com.etaofinance.api.service.inter.ICommentService;
import com.etaofinance.api.service.inter.IProjectFavoriteService;
import com.etaofinance.api.service.inter.IProjectImageService;
import com.etaofinance.api.service.inter.IProjectService;
import com.etaofinance.api.service.inter.IProjectStrategyService;
import com.etaofinance.api.service.inter.IProjectSubscriptionService;
import com.etaofinance.api.service.inter.IPublicProvinceCityService;
import com.etaofinance.core.enums.AreaLevel;
import com.etaofinance.core.enums.ProjectAuditStatus;
import com.etaofinance.core.enums.ProjectType;
import com.etaofinance.core.util.JsonUtil;
import com.etaofinance.core.util.ParseHelper;
import com.etaofinance.api.service.inter.IMemberService;
import com.etaofinance.entity.Comment;
import com.etaofinance.entity.FeedBack;
import com.etaofinance.entity.Project;
import com.etaofinance.entity.ProjectImage;
import com.etaofinance.entity.ProjectStrategy;
import com.etaofinance.entity.PublicProvinceCity;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.ProjectFavoriteInvestModel;
import com.etaofinance.entity.domain.PublishProjectReq;
import com.etaofinance.entity.req.PagedCommentReq;
import com.etaofinance.entity.req.PagedFeedBackReq;
import com.etaofinance.entity.req.PagedProjectFavReq;
import com.etaofinance.entity.req.PagedProjectReq;
import com.etaofinance.entity.req.ProjectAuditReq;
import com.etaofinance.entity.Member;
/**
 * 项目管理
 * 
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
	@Autowired
	private IProjectImageService projectImageService;
	@Autowired
	private IPublicProvinceCityService publicProvinceCityService;
	@Autowired
	private IProjectSubscriptionService projectSubscriptionService;
	@Autowired
	private IMemberService memberService; 
	@Autowired
	private IProjectFavoriteService projectFavoriteService;
	@Autowired
	private ICommentService commentService;
	/**
	 * 项目列表查询页
	 * 
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
		req.setId(ParseHelper.ToInt(req.getId(), 0));
		PagedResponse<Project> listData = projectService.queryProjectList(req);
		view.addObject("listData", listData);
		return view;
	}

	/**
	 * 待审核项目列表查询页
	 * 
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
		req.setId(ParseHelper.ToInt(req.getId(), 0));
		PagedResponse<Project> listData = projectService.queryProjectList(req);
		view.addObject("listData", listData);
		Map<Long, String> strategyMap = getStrategyMap(listData);
		view.addObject("strategyMap", strategyMap);
		Map<Integer, String> cityMap = publicProvinceCityService
				.getOpenCityMap();
		view.addObject("cityMap", cityMap);
		return view;
	}

	private Map<Long, String> getStrategyMap(PagedResponse<Project> listData) {
		Map<Long, String> resultMap = new HashMap<Long, String>();
		List<Long> projectIds = listData.getResultList().stream()
				.map(k -> k.getId()).collect(Collectors.toList());
		if (projectIds.size() == 0) {
			return resultMap;
		}
		List<ProjectStrategy> strategyList = projectStrategyService
				.getByProjectIds(projectIds);

		Map<Long, List<ProjectStrategy>> result = strategyList.stream()
				.collect(Collectors.groupingBy(ProjectStrategy::getProjectid));
		for (Long projectId : result.keySet()) {
			List<String> valueList = result.get(projectId).stream()
					.map(k -> k.getValue().toString())
					.collect(Collectors.toList());
			resultMap.put(projectId, String.join("-", valueList));
		}
		return resultMap;
	}

	/**
	 * 新建项目 wangchao modify
	 * 
	 * @return
	 */
	@RequestMapping("newproject")
	public ModelAndView newproject() {
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "项目管理");
		view.addObject("currenttitle", "发布项目");
		view.addObject("viewPath", "project/newproject");
		view.addObject("provincelist", publicProvinceCityService
				.getOpenCityByJiBie(AreaLevel.Province));
		view.addObject("pro_city", publicProvinceCityService
				.getCityStr(publicProvinceCityService
						.getOpenCityByJiBie(AreaLevel.City)));
		view.addObject("city_region", publicProvinceCityService
				.getCityStr(publicProvinceCityService
						.getOpenCityByJiBie(AreaLevel.District)));
		return view;
	}

	/**
	 * 保存项目 wangchao
	 * 
	 * @return
	 */
	@RequestMapping("saveproject")
	@ResponseBody
	public int saveproject(HttpServletRequest request, String data) {
		PublishProjectReq req = JsonUtil.str2obj(data, PublishProjectReq.class);
		UserContext context = UserContext.getCurrentContext(request);
		req.setPublishName(context.getUserName());
		return projectService.publishProject(req);
	}

	/*
	 * 项目审核 wangchao
	 */
	@RequestMapping("audit")
	@ResponseBody
	public int audit(HttpServletRequest request, ProjectAuditReq req) {
		UserContext context = UserContext.getCurrentContext(request);
		req.setAuditName(context.getUserName());
		int r = projectService.audit(req);
		return r;
	}

	/*
	 * 项目预览 wangchao 不是这个页面 ，，类似于wap中的 项目详情页面
	 */
	@RequestMapping("previewproject")
	public ModelAndView previewProject(long id) {
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "项目管理");
		view.addObject("currenttitle", "项目预览");
		view.addObject("viewPath", "project/previewproject");
		// 项目信息
		Project project = projectService.selectByPrimaryKey(id);
		// 项目 类型 信息
		List<ProjectStrategy> proStrList = projectStrategyService
				.getByProjectId(id);
		// 项目图片 信息
		List<ProjectImage> proImgList = projectImageService.getByProjectId(id);
		// 省市区
		Map<Integer, String> cityMap = publicProvinceCityService
				.getOpenCityMap();
		view.addObject("cityMap", cityMap);
		view.addObject("project", project);
		view.addObject("proStrList", proStrList);
		view.addObject("proImgList", proImgList);
		return view;
	}

	/*
	 * 项目审核 wangchao
	 */
	@RequestMapping("projectaudit")
	public ModelAndView projectAudit(long id) {
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "项目管理");
		view.addObject("currenttitle", "项目审核");
		view.addObject("viewPath", "project/projectaudit");
		// 项目信息
		Project project = projectService.selectByPrimaryKey(id);
		// 项目 类型 信息
		List<ProjectStrategy> proStrList = projectStrategyService
				.getByProjectId(id);
		// 项目图片 信息
		List<ProjectImage> proImgList = projectImageService.getByProjectId(id);
		// 省市区
		Map<Integer, String> cityMap = publicProvinceCityService
				.getOpenCityMap();
		view.addObject("cityMap", cityMap);
		view.addObject("project", project);
		view.addObject("proStrList", proStrList);
		view.addObject("proImgList", proImgList);
		view.addObject("projectId", id);
		return view;
	}

	/*
	 * 修改项目 wangchao
	 */
	@RequestMapping("projectmodify")
	public ModelAndView projectModify(long id) {
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "项目管理");
		view.addObject("currenttitle", "项目修改");
		view.addObject("viewPath", "project/projectmodify");
		// 项目信息
		Project project = projectService.selectByPrimaryKey(id);
		if (project != null) {
			Member member=memberService.getById(project.getMemberid());
			// 项目 类型 信息
			List<ProjectStrategy> tmpStrList = projectStrategyService.getByProjectId(id);
			List<ProjectStrategy> proStrList=new ArrayList<ProjectStrategy>();
			
			proStrList.add(0, tmpStrList.stream().filter(t->t.getKey().equals("SteadyA")).collect(Collectors.toList()).get(0));
			proStrList.add(1, tmpStrList.stream().filter(t->t.getKey().equals("SteadyB")).collect(Collectors.toList()).get(0));
			// 项目图片 信息
			List<ProjectImage> proImgList = projectImageService.getByProjectId(id);
			// 省市区
			Map<Integer, String> cityMap = publicProvinceCityService
					.getOpenCityMap();
			view.addObject("provincelist", publicProvinceCityService
					.getOpenCityByJiBie(AreaLevel.Province));
			view.addObject("pro_city", publicProvinceCityService
					.getCityStr(publicProvinceCityService
							.getOpenCityByJiBie(AreaLevel.City)));
			view.addObject("city_region", publicProvinceCityService
					.getCityStr(publicProvinceCityService
							.getOpenCityByJiBie(AreaLevel.District)));
			view.addObject("cityMap", cityMap);
			view.addObject("project", project);
			view.addObject("member",member);
			view.addObject("proStrList", proStrList);
			view.addObject("proImgList", proImgList);
			view.addObject("projectId", id);
		}
		return view;
	}
	/*
	 * 项目收藏 LIN
	 */
	@RequestMapping("favorite")
	public ModelAndView favoritelist() {
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "项目管理");
		model.addObject("currenttitle", "关注列表");
		model.addObject("viewPath", "project/favorite");
		return model;
	}

	@RequestMapping("favoritedo")
	public ModelAndView favoritelistdo(PagedProjectFavReq req) { 
		ModelAndView model = new ModelAndView("project/favoritedo");
		PagedResponse<ProjectFavoriteInvestModel> favoriteList = new PagedResponse<ProjectFavoriteInvestModel>();
		favoriteList=projectFavoriteService.getFavoritePageList(req);
		model.addObject("listData",favoriteList);
		return model;
	}
	
	/*
	 * 项目回复列表 LIN
	 */
	@RequestMapping("comment")
	public ModelAndView commentlist() {
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "项目管理");
		model.addObject("currenttitle", "项目交流");
		model.addObject("viewPath", "project/comment");
		return model;
	}

	@RequestMapping("commentdo")
	public ModelAndView commentlistdo(PagedCommentReq req) { 
		ModelAndView model = new ModelAndView("project/commentdo");
		PagedResponse<Comment> commentList = new PagedResponse<Comment>();
		commentList=commentService.getCommentPagingList(req);
		model.addObject("listData",commentList);
		return model;
	}
}
