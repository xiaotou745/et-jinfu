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
import com.etaofinance.entity.Project;
import com.etaofinance.entity.ProjectFavorite;
import com.etaofinance.entity.ProjectSubscription;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.ProjectFavoriteDM;
import com.etaofinance.entity.domain.ProjectModel;
import com.etaofinance.entity.domain.ProjectSubscriptionDM;
import com.etaofinance.entity.req.ProFavoriteReq;
import com.etaofinance.entity.req.ProSubInvestReq;
import com.etaofinance.entity.req.PagedProjectReq;
import com.etaofinance.entity.req.SubProjectReq;
import com.etaofinance.wap.common.LoginUtil;
import com.etaofinance.wap.common.RequireLogin;
import com.etaofinance.wap.common.UserContext;
import com.wordnik.swagger.annotations.ApiOperation;


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
	@ApiOperation(value = "项目接口;列表", httpMethod = "POST", 
	consumes="application/json;charset=UFT-8",produces="application/json;charset=UFT-8",
	notes = "项目接口列表")
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
	@ApiOperation(value = "认购项目", httpMethod = "POST", 
	consumes="application/json;charset=UFT-8",produces="application/json;charset=UFT-8",
	notes = "认购项目")
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
	@RequestMapping("investproject")
	@ResponseBody
	//@RequireLogin
	@ApiOperation(value = "我投资的项目", httpMethod = "POST", 
	consumes="application/json;charset=UFT-8",produces="application/json;charset=UFT-8",
	notes = "我投资的项目")
	public List<ProjectSubscriptionDM> investProject(@RequestBody ProSubInvestReq record)
	{	
		//Long memberid=UserContext.getCurrentContext(request).getUserInfo().getId();	
		//record.setMemberid(memberid);	
		return projectSubscriptionService.getListMore(record);
	}
	
	/**
	 * 我关注的项目
	 * @param 
	 * @author hulingbo
	 * @date 2016年3月29日20:37:42
	 * @return
	 */
	@RequestMapping("favoriteproject")
	@ResponseBody
	//@RequireLogin
	@ApiOperation(value = "我关注的项目", httpMethod = "POST", 
	consumes="application/json;charset=UFT-8",produces="application/json;charset=UFT-8",
	notes = "我关注的项目")
	public List<ProjectFavoriteDM> favoriteProject(@RequestBody ProFavoriteReq record)
	{
		//Long memberid=UserContext.getCurrentContext(request).getUserInfo().getId();	
		//record.setMemberid(memberid);	
		return projectFavoriteService.getListMore(record);
	}	
	
	/**
	 * 我发起的项目
	 * @param 
	 * @author hulingbo
	 * @date 2016年3月31日11:53:25
	 * @return
	 */
	@RequestMapping("launchproject")
	@ResponseBody
	@ApiOperation(value = "我发起的项目", httpMethod = "POST", 
	consumes="application/json;charset=UFT-8",produces="application/json;charset=UFT-8",
	notes = "我发起的项目")
	public List<Project> launchProject(@RequestBody Project record)
	{
		return projectService.getList(record);
	}
	
	/**
	 * 关注或取消关注项目
	 * @param profavorite
	 * @return
	 */
	@RequestMapping("follow")
	@ResponseBody
	@ApiOperation(value = "关注或取消关注项目", httpMethod = "POST", 
	consumes="application/json;charset=UFT-8",produces="application/json;charset=UFT-8",
	notes = "关注或取消关注项目")
	public HttpResultModel<Object> follow(@RequestBody ProjectFavorite profavorite)
	{
		
		return projectFavoriteService.follow(profavorite);
	}
	
	
}
