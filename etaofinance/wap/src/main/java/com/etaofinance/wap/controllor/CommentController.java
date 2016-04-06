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
import com.etaofinance.api.service.inter.ICommentService;
import com.etaofinance.api.service.inter.IProjectFavoriteService;
import com.etaofinance.api.service.inter.IProjectService;
import com.etaofinance.api.service.inter.IProjectSubscriptionService;
import com.etaofinance.entity.Comment;
import com.etaofinance.entity.Member;
import com.etaofinance.entity.Project;
import com.etaofinance.entity.ProjectSubscription;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.common.ResponseBase;
import com.etaofinance.entity.domain.ProjectFavoriteDM;
import com.etaofinance.entity.domain.ProjectModel;
import com.etaofinance.entity.domain.ProjectSubscriptionDM;
import com.etaofinance.entity.req.PagedProjectReq;
import com.etaofinance.entity.req.RegistReq;
import com.etaofinance.entity.req.SubProjectReq;
import com.etaofinance.wap.common.LoginUtil;
import com.etaofinance.wap.common.RequireLogin;
import com.etaofinance.wap.common.UserContext;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("comment")
public class CommentController {
	@Autowired
	private ICommentService commentService;
	
	@RequestMapping("/add")
	@ResponseBody
	@ApiOperation(value = "评论", httpMethod = "POST", 
	consumes="application/json;charset=UFT-8",produces="application/json;charset=UFT-8",
	notes = "用户评论")
	public HttpResultModel<ResponseBase> addComment(@RequestBody Comment req)
	{
		return  commentService.insert(req);
	}
}
