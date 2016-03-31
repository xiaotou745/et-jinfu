package com.etaofinance.wap.controllor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etaofinance.api.common.LoginHelper;
import com.etaofinance.api.service.inter.IProjectFavoriteService;
import com.etaofinance.api.service.inter.IProjectService;
import com.etaofinance.api.service.inter.IProjectSubscriptionService;
import com.etaofinance.entity.ProjectSubscription;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.ProjectFavoriteDM;
import com.etaofinance.entity.domain.ProjectModel;
import com.etaofinance.entity.domain.ProjectSubscriptionDM;
import com.etaofinance.entity.req.PagedProjectReq;
import com.etaofinance.entity.req.SubProjectReq;
import com.etaofinance.wap.common.LoginUtil;
import com.etaofinance.wap.common.RequireLogin;
import com.etaofinance.wap.common.UserContext;


@Controller
@RequestMapping("project")
public class ProjectController {
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	@Autowired
	IProjectService  projectService;
	
	@Autowired
	IProjectSubscriptionService  projectSubscriptionService;
	
	@Autowired
	IProjectFavoriteService  projectFavoriteService;
	
	/**
	 * 项目列表接口(WAP)
	 * @param req
	 * @return
	 */
	@RequestMapping("projectlist")
	@ResponseBody
	public  PagedResponse<ProjectModel> projectlist(@RequestBody PagedProjectReq req) {
		return projectService.getProjectList(req);
	}
	/**
	 * 认购项目
	 * @param req
	 * @return
	 */
	@RequestMapping("subproject")
	@ResponseBody
	@RequireLogin
	public   HttpResultModel<Object>  subproject(@RequestBody SubProjectReq req) {
		req.setUserId(UserContext.getCurrentContext(request).getUserInfo().getId());
		return projectService.subproject(req);
	}
	
	/**
	 * 我投资的项目
	 * @param 
	 * @author hulingbo
	 * @date 2016年3月29日19:59:34
	 * @return
	 */
	@RequestMapping("getinvestproject")
	@ResponseBody
	public List<ProjectSubscriptionDM> getInvestProject(@RequestBody ProjectSubscriptionDM record)
	{
		return projectSubscriptionService.getListMore(record);
	}
	
	/**
	 * 我关注的项目
	 * @param 
	 * @author hulingbo
	 * @date 2016年3月29日20:37:42
	 * @return
	 */
	@RequestMapping("getfavoriteproject")
	@ResponseBody
	public List<ProjectFavoriteDM> getFavoriteProject(@RequestBody ProjectFavoriteDM record)
	{
		return projectFavoriteService.getListMore(record);
	}
	
	
}
