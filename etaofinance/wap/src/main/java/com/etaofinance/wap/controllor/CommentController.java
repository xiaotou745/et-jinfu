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
	@Autowired
	HttpServletRequest request;
	
	/**
	 * 发布、回复评论
	 * post参数:{
     *          "projectid": "1",
  	 *		    "memberid":"2322323",
  	 *		    "content":"nihaodkjdljf",
  	 *		    "recommentid":"1232" //不带此参数或者参数为空为发布评论、此参数有值为恢复对应ID评论
	 *		   }
	 * LIN
	 */
	@RequestMapping("/add")
	@ResponseBody
	@ApiOperation(value = "评论", httpMethod = "POST", 
	consumes="application/json;charset=UFT-8",produces="application/json;charset=UFT-8",
	notes = "用户评论")
	@RequireLogin
	public HttpResultModel<Object> addComment(@RequestBody Comment req)
	{
		req.setMemberid(UserContext.getCurrentContext(request).getUserInfo().getId());
		return  commentService.insert(req);
	}
	
	
	/**
	 * 删除评论
	 * post参数:{
     *          "id": "7",
  	 *		    "memberid":"2322323"
	 *		   }
	 * LIN
	 */
	@RequestMapping("/del")
	@ResponseBody
	@ApiOperation(value = "删除评论", httpMethod = "POST", 
	consumes="application/json;charset=UFT-8",produces="application/json;charset=UFT-8",
	notes = "删除评论")
	@RequireLogin
	public HttpResultModel<Object> updateCommentIsDel(@RequestBody Comment req)
	{
		return  commentService.updateByPrimaryKeyAndMem(req);
	}
}
