package com.etaofinance.wap.controllor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etaofinance.api.service.inter.IProjectService;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.ProjectModel;
import com.etaofinance.entity.req.PagedProjectReq;


@Controller
@RequestMapping("project")
public class ProjectController {
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	@Autowired
	IProjectService  projectService;
	
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
}
