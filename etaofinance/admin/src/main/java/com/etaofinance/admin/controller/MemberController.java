package com.etaofinance.admin.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.etaofinance.core.enums.AreaLevel;
import com.etaofinance.core.enums.MemberTypeEnum;
import com.etaofinance.core.util.ParseHelper;
import com.etaofinance.entity.BalanceRecord;
import com.etaofinance.entity.BankCard;
import com.etaofinance.entity.FeedBack;
import com.etaofinance.entity.Member;
import com.etaofinance.entity.MemberApply;
import com.etaofinance.admin.common.UserContext;
import com.etaofinance.api.service.impl.BalanceRecordService;
import com.etaofinance.api.service.inter.IBalanceRecordService;
import com.etaofinance.api.service.inter.IBankCardService;
import com.etaofinance.api.service.inter.IFeedBackService;
import com.etaofinance.api.service.inter.IMemberApplyService;
import com.etaofinance.api.service.inter.IMemberService;
import com.etaofinance.api.service.inter.IPublicProvinceCityService;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.MemberApplyAuditModel;
import com.etaofinance.entity.domain.MemberApplyInvestModel;
import com.etaofinance.entity.domain.MemberModel;
import com.etaofinance.entity.req.MemberApplyAuditReq;
import com.etaofinance.entity.req.PagedFeedBackReq;
import com.etaofinance.entity.req.PagedMemberBalanceRecordReq;
import com.etaofinance.entity.req.ModifyMemberReq;
import com.etaofinance.entity.req.PagedMemberReq;

@Controller
@RequestMapping("member")
public class MemberController {
	@Autowired
	private IMemberService memberService;	
	@Autowired
	private IMemberApplyService memberApplyService;	
	@Autowired
	private IBankCardService bankCardService;
	@Autowired
	private IPublicProvinceCityService publicProvinceCityService;
	@Autowired
	private IBalanceRecordService balanceRecordService;
	@Autowired
	private IFeedBackService feedBackService;
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
	 * 用户反馈 LIN
	 */
	@RequestMapping("feedbacklist")
	public ModelAndView feedbacklist() {
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "会员管理");
		model.addObject("currenttitle", "用户反馈");
		model.addObject("viewPath", "member/feedbacklist");
		return model;
	}

	@RequestMapping("feedbacklistdo")
	public ModelAndView feedbacklistdo(PagedFeedBackReq req) { 
		ModelAndView model = new ModelAndView("member/feedbacklistdo");
		PagedResponse<FeedBack> feedList = new PagedResponse<FeedBack>();
		feedList=feedBackService.getFeedBackList(req);
		model.addObject("listData",feedList);
		return model;
	}
	@RequestMapping("feedbackdel")
	@ResponseBody
	public int feedbackdel(Integer id) {
		return feedBackService.remove(id);
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
		Long memberId =ParseHelper.ToLong(id, 0);
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "会员管理");
		model.addObject("currenttitle", "会员详情");
		model.addObject("viewPath", "member/memberdetail");
		model.addObject("provincelist", publicProvinceCityService.getOpenCityByJiBie(AreaLevel.Province));
		model.addObject("pro_city", publicProvinceCityService.getCityStr(publicProvinceCityService.getOpenCityByJiBie(AreaLevel.City)));
		model.addObject("city_region",publicProvinceCityService.getCityStr(publicProvinceCityService.getOpenCityByJiBie(AreaLevel.District)));
		//根据会员id获取会员基本信息
		Member member=memberService.getById(memberId);
		model.addObject("member", member); //会员基础信息
		//获取会员类型 跟投人 领投人 最新申请信息
		MemberApply followMemberApply=new MemberApply();
		MemberApply leadMemberApply = new MemberApply();
		if(member!=null){
			List<MemberApply> mList= memberApplyService.getMemberApplyInfoByMemberId(memberId);
			if(mList!=null){
				for (int i = 0; i < mList.size(); i++) {
					if(mList.get(i).getTypeid().equals(MemberTypeEnum.FollowInvestUser.value())){ //跟投人
						followMemberApply=mList.get(i);   
					}
					if(mList.get(i).getTypeid().equals(MemberTypeEnum.LeadInvestUser.value())){ //领投人
						leadMemberApply=mList.get(i);
					}					
				}
			}
			model.addObject("followMemberApply", followMemberApply);   
			model.addObject("leadMemberApply", leadMemberApply);
			//获取会员银行卡信息记录
			List<BankCard> bankCards= bankCardService.getListByMemberId(memberId);
			model.addObject("bankCards", bankCards);
		}
		return model;
	}
	/*
	 * 修改会员部分基础信息 wangchao 还需要加修改日志
	 */
	@RequestMapping("modifymember")
	@ResponseBody
	public int modifyMember(HttpServletRequest request,ModifyMemberReq req){
		int i= memberService.modifyMember(req);
		return i;
	}
	/*
	 * 查询会员收支记录 
	 */
	@RequestMapping("incomerecordlistdo")	 
	public ModelAndView incomeRecordListdo(PagedMemberBalanceRecordReq req){
		ModelAndView model = new ModelAndView("member/incomerecordlistdo");
		PagedResponse<BalanceRecord> memberBalanceRecord = new PagedResponse<BalanceRecord>();
		memberBalanceRecord=balanceRecordService.getPageList(req);
		model.addObject("listData",memberBalanceRecord);
		return model;
	}
	
}
