package com.etaofinance.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.etaofinance.api.service.inter.IBalanceRecordService;
import com.etaofinance.api.service.inter.IProjectService;
import com.etaofinance.api.service.inter.IWithdrawformService;
import com.etaofinance.core.enums.ProjectAuditStatus;
import com.etaofinance.core.util.ParseHelper;
import com.etaofinance.entity.BalanceRecord;
import com.etaofinance.entity.Project;
import com.etaofinance.entity.Withdrawform;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.req.PagedBalancerecordReq;
import com.etaofinance.entity.req.PagedProjectReq;
import com.etaofinance.entity.req.PagedWithdrawReq;


@Controller
@RequestMapping("finance")
public class FinanceController {

	
	//@Autowired
	//private IRechargeService rechargeService;
	
	@Autowired
	private  IWithdrawformService withdrawformService;
	
	@Autowired
	private IBalanceRecordService balanceRecordService;
	
	@RequestMapping("buylist")
	public ModelAndView buyList() {
		
		ModelAndView view = new ModelAndView("adminView");
		
		view.addObject("subtitle", "资金管理");
		
		view.addObject("currenttitle", "付款列表");
		
		view.addObject("viewPath", "balance/buylist");
		
		return view;
	}
	
	@RequestMapping("buylistdo")
	public ModelAndView payListDo(PagedBalancerecordReq req) {
		
		ModelAndView view = new ModelAndView("balance/buylistdo");
		
		req.setAuditStatus(ProjectAuditStatus.AuditPass.value());
		
		req.setId(ParseHelper.ToInt(req.getId(),0));
		
		PagedResponse<BalanceRecord> listData=balanceRecordService.getBalanceRecordList(req);
		
		view.addObject("listData", listData);
		return view;
	}
	@RequestMapping("withdrawlist")
	public ModelAndView withdrawList() {
		
		ModelAndView view = new ModelAndView("adminView");
		
		view.addObject("subtitle", "资金管理");
		
		view.addObject("currenttitle", "提现列表");
		
		view.addObject("viewPath", "withdraw/withdrawlist");
		
		return view;
	}
	
	@RequestMapping("withdrawlistdo")
	public ModelAndView withdrawListDo(PagedWithdrawReq req) {
		
		ModelAndView view = new ModelAndView("withdraw/withdrawlistdo");
		
		req.setAuditStatus(ProjectAuditStatus.AuditPass.value());
		
		req.setId(ParseHelper.ToInt(req.getId(),0));
		
		PagedResponse<Withdrawform> listData=withdrawformService.getWithdrawList(req);
		
		view.addObject("listData", listData);
		return view;
	}
	
	@RequestMapping("rechargelist")
	public ModelAndView rechargeList() {
		
		ModelAndView view = new ModelAndView("adminView");
		
		view.addObject("subtitle", "资金管理");
		
		view.addObject("currenttitle", "充值列表");
		
		view.addObject("viewPath", "recharge/rechargelist");
		
		return view;
	}
	
	@RequestMapping("rechargelistdo")
	public ModelAndView rechargeListDo(PagedProjectReq req) {
		
		ModelAndView view = new ModelAndView("recharge/rechargelistdo");
		
		req.setAuditStatus(ProjectAuditStatus.AuditPass.value());
		
		req.setId(ParseHelper.ToInt(req.getId(),0));
		
	//	PagedResponse<Project> listData=rechargeService.getChargeList(req);
		
	//	view.addObject("listData", listData);
		
		return view;
	}
}
