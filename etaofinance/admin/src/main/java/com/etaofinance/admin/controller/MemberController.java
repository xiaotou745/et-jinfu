package com.etaofinance.admin.controller;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.etaofinance.core.util.ParseHelper;
import com.etaofinance.entity.Member;
import com.etaofinance.admin.common.UserContext;
import com.etaofinance.api.service.inter.IMemberApplyService;
import com.etaofinance.api.service.inter.IMemberService;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.MemberApplyAuditModel;
import com.etaofinance.entity.domain.MemberApplyInvestModel;
import com.etaofinance.entity.domain.LeadInvestModel;
import com.etaofinance.entity.domain.MemberModel;
import com.etaofinance.entity.req.MemberApplyAuditReq;
import com.etaofinance.entity.req.PagedMemberReq;

@Controller
@RequestMapping("member")
public class MemberController {
	@Autowired
	private IMemberService memberService;
	
	@Autowired
	private IMemberApplyService memberApplyService;
	/*
	 * 会员列表 wangchao
	 */
	@RequestMapping("memberlist")
	public ModelAndView memberlist() {
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "会员管理");
		model.addObject("currenttitle", "会员列表");
		model.addObject("viewPath", "member/memberlist");
		return model;
	}

	@RequestMapping("memberlistdo")
	public ModelAndView memberlistdo(PagedMemberReq req) { 
		ModelAndView model = new ModelAndView("member/memberlistdo");
		PagedResponse<MemberModel> memberModelList = new PagedResponse<MemberModel>();
		memberModelList=memberService.getMemberList(req);
		model.addObject("listData",memberModelList);
		return model;
	}
	
	/*
	 * 跟投人列表 wangchao
	 */
	@RequestMapping("followinvestlist")
	public ModelAndView followinvestlist() {
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "会员管理");
		model.addObject("currenttitle", "跟投人列表");
		model.addObject("viewPath", "member/followinvestlist");
		return model;
	} 
	
	@RequestMapping("followinvestlistdo")
	public ModelAndView followinvestlistdo(PagedMemberReq req) { 
		
		ModelAndView model = new ModelAndView("member/followinvestlistdo");
		PagedResponse<MemberApplyInvestModel> memberApplyInvestModel = new PagedResponse<MemberApplyInvestModel>();
		req.setMemberType(1); //跟投人
		memberApplyInvestModel=memberApplyService.getMemberApplyList(req);
		model.addObject("listData",memberApplyInvestModel);
		return model;
	}
	
	/*
	 * 领投人列表 wangchao
	 */
	@RequestMapping("leadinvestlist")
	public ModelAndView leadinvestlist() {
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "会员管理");
		model.addObject("currenttitle", "领投人列表");
		model.addObject("viewPath", "member/leadinvestlist");
		return model;
	} 
	@RequestMapping("leadinvestlistdo")
	public ModelAndView leadinvestlistdo(PagedMemberReq req) { 
		ModelAndView model = new ModelAndView("member/leadinvestlistdo");
		req.setMemberType(2);  //领投人
		PagedResponse<MemberApplyInvestModel> memberApplyInvestModel = new PagedResponse<MemberApplyInvestModel>();
		memberApplyInvestModel=memberApplyService.getMemberApplyList(req);
		model.addObject("listData",memberApplyInvestModel);
		return model;
	}
	/**
	 * 通过手机号获取会员ID,0表示不存在
	 * @param phoneno
	 * @return
	 */
	@RequestMapping("getmemberid")
	@ResponseBody
	public Long getmemberid(String phoneno) { 
		Member m=memberService.selectByPhoneNo(phoneno);
		if(m!=null)
		{
			return m.getId();
		}
		return 0l;
	}
	/*
	 * 根据申请Id获取申请投资人信息 wangchao
	 */
	@RequestMapping("getmemberinfo")
	@ResponseBody
	public MemberApplyAuditModel getmemberInfo(String memberApplyId){
		MemberApplyAuditModel member= memberApplyService.getMemberApplyInfo(ParseHelper.ToLong(memberApplyId,0));
		return member;
	}
	@RequestMapping("auditconfirm")
	@ResponseBody
	public int auditConfirm(HttpServletRequest request, MemberApplyAuditReq req){
		UserContext uContext= UserContext.getCurrentContext(request);
		req.setAuditName(uContext.getLoginName());
		int i= memberApplyService.auditConfirm(req);
		return i;
	}
}
