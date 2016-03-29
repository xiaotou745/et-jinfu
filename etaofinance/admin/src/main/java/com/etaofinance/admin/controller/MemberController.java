package com.etaofinance.admin.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.etaofinance.core.enums.MemberTypeEnum;
import com.etaofinance.core.util.ParseHelper;
import com.etaofinance.entity.Member;
import com.etaofinance.entity.MemberApply;
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
	/*
	 * 审核 重审 确认对话框 保存 wangchao
	 */
	@RequestMapping("auditconfirm")
	@ResponseBody
	public int auditConfirm(HttpServletRequest request, MemberApplyAuditReq req){
		UserContext uContext= UserContext.getCurrentContext(request);
		req.setAuditName(uContext.getLoginName());
		int i= memberApplyService.auditConfirm(req);
		return i;
	}
	
	/*
	 * 会员详情  wangchao
	 */
	@RequestMapping("memberdetail")
	public ModelAndView memberDetail(HttpServletRequest request,String id) {
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "会员管理");
		model.addObject("currenttitle", "会员详情");
		model.addObject("viewPath", "member/memberdetail");
		//根据会员id获取会员基本信息
		Member member=memberService.getById(ParseHelper.ToLong(id, 0));
		model.addObject("member", member); //会员基础信息
		//获取会员类型 跟投人 领投人 最新申请信息
		MemberApply followMemberApply=new MemberApply();
		MemberApply leadMemberApply = new MemberApply();
		if(member!=null){
			List<MemberApply> mList= memberApplyService.getMemberApplyInfoByMemberId(ParseHelper.ToLong(id, 0));
			if(mList!=null){
				for (int i = 0; i < mList.size(); i++) {
					if(mList.get(i).getTypeid().equals(MemberTypeEnum.FollowInvestUser.value())){ //跟投人
						followMemberApply=mList.get(i);   
					}
					if(mList.get(i).getTypeid().equals(MemberTypeEnum.LeadInvestUser.value())){ //跟投人
						leadMemberApply=mList.get(i);
					}					
				}
			}
			model.addObject("followMemberApply", followMemberApply);   
			model.addObject("leadMemberApply", leadMemberApply);
		}
		return model;
	}
}
