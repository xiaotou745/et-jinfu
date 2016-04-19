package com.etaofinance.wap.controllor;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.etaofinance.api.service.inter.IADVertService;
import com.etaofinance.api.service.inter.ICommentService;
import com.etaofinance.api.service.inter.IMemberService;
import com.etaofinance.api.service.inter.IProjectFavoriteService;
import com.etaofinance.api.service.inter.IProjectImageService;
import com.etaofinance.api.service.inter.IProjectService;
import com.etaofinance.api.service.inter.IProjectSubscriptionService;
import com.etaofinance.entity.ADVert;
import com.etaofinance.entity.Comment;
import com.etaofinance.entity.Member;
import com.etaofinance.entity.Project;
import com.etaofinance.entity.ProjectImage;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.ProjectComment;
import com.etaofinance.entity.domain.ProjectMember;
import com.etaofinance.entity.domain.ProjectModel;
import com.etaofinance.entity.req.PagedCommentReq;
import com.etaofinance.entity.req.PagedProjectCommentReq;
import com.etaofinance.entity.req.PagedProjectReq;
import com.etaofinance.wap.common.RequireLogin;
import com.etaofinance.wap.common.UserContext;



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
	@Autowired
	IProjectImageService projectImageService;
	@Autowired
	ICommentService commentService;
	@Autowired
	IMemberService memberService;
	@Autowired
	IProjectFavoriteService projectFavoriteService;
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
	public ModelAndView listdo(@RequestBody PagedProjectReq req)
	{
		ModelAndView view = new ModelAndView("home/listdo");
		PagedResponse<ProjectModel> result=projectService.getProjectList(req);
		view.addObject("result", result);
		return view;
	}
	/**
	 * 详情页
	 * @param req
	 * @return
	 */
	@RequestMapping("/detail")
	public ModelAndView detail(Long projectid)
	{
		ModelAndView view = new ModelAndView("wapView");
		view.addObject("currenttitle", "项目详情");
		view.addObject("viewPath", "home/detail");
		view.addObject("projectid",projectid);
		Member member=UserContext.getCurrentContext(request).getUserInfo();
		if(member!=null)
		{
			member=memberService.getById(member.getId());
		}
		view.addObject("member", member);
		//1.项目详情
		ProjectModel detaiModel=projectService.getProjectDetail(projectid);
		view.addObject("detaiModel",detaiModel);
		//2.投资人
		List<ProjectMember> subList=new ArrayList<ProjectMember>();
		if(member!=null&&member.getLevel()>1)
		{
			subList=projectSubscriptionService.getProjectLeadMember(projectid);
		}
		view.addObject("subList",subList);
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
		view.addObject("leadList",leadList);
		//3.项目概况,回报说明图片.
		List<ProjectImage> imgList=projectImageService.getByProjectId(projectid);
		view.addObject("imgList",imgList);
		//4.项目交流以及评论
		List<ProjectComment> commentList=new ArrayList<ProjectComment>();
		if(member!=null&&member.getLevel()>1)
		{	PagedProjectCommentReq  commentReq=new PagedProjectCommentReq();
			commentReq.setCurrentPage(1);
			commentReq.setProjectId(projectid);
			commentReq.setMemberId(member.getId());
			commentList=commentService.getProjectComment(commentReq).getResultList();
		}
		int  isFavorite=0;
		if(member!=null)
		{
			isFavorite=projectFavoriteService.isMyFavorite(member.getId(), projectid);
		}
		view.addObject("isFavorite",isFavorite);
		//5.是否关注该项目
		view.addObject("commentList",commentList);
		return view;
	}
	
	/**
	 * 项目认购页面
	 * @param req
	 * @return
	 */
	@RequestMapping("/subscribe")
	@RequireLogin
	public ModelAndView subscribe(Long projectid)
	{
		ModelAndView view = new ModelAndView("wapView");
		view.addObject("currenttitle", "项目详情");
		view.addObject("viewPath", "home/subscribe");
		view.addObject("projectid",projectid);
		//1.项目详情
		ProjectModel detaiModel=projectService.getProjectDetail(projectid);
		view.addObject("detaiModel",detaiModel);
		Member member=memberService.getById(UserContext.getCurrentContext(request).getUserInfo().getId());
		view.addObject("member", member);
		return view;
	}
	/**
	 * 评论分页列表
	 * @param req
	 * @return
	 */
	@RequestMapping("/commentlistdo")
	@RequireLogin
	public ModelAndView commentlistdo(@RequestBody PagedProjectCommentReq commentReq)
	{
		ModelAndView view = new ModelAndView("home/commentlistdo");
		Member member=memberService.getById(UserContext.getCurrentContext(request).getUserInfo().getId());
		commentReq.setMemberId(member.getId());
		List<ProjectComment> commentList=commentService.getProjectComment(commentReq).getResultList();
		view.addObject("commentList", commentList);
		view.addObject("member", member);
		return view;
	}
	/**
	 * 项目认购页面
	 * @param req
	 * @return
	 */
	@RequestMapping("/subscribeinputpaypassword")
	@RequireLogin
	public ModelAndView subscribeinputpaypassword(Long projectid,Integer quantity,Integer islead)
	{
		ModelAndView view = new ModelAndView("wapView");
		view.addObject("currenttitle", "支付密码");
		view.addObject("viewPath", "home/subscribeinputpaypassword");
		view.addObject("projectid",projectid);
		view.addObject("quantity",quantity);
		view.addObject("islead",islead);
		return view;
	}
	
}
