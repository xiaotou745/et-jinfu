package com.etaofinance.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.etaofinance.api.service.inter.IBalanceRecordService;
import com.etaofinance.api.service.inter.IProjectService;
import com.etaofinance.api.service.inter.IRechargeService;
import com.etaofinance.api.service.inter.IWithdrawformService;
import com.etaofinance.core.enums.ProjectAuditStatus;
import com.etaofinance.core.enums.WithdrawStatus;
import com.etaofinance.core.util.ParseHelper;
import com.etaofinance.entity.BalanceRecord;
import com.etaofinance.entity.Project;
import com.etaofinance.entity.Recharge;
import com.etaofinance.entity.Withdrawform;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.req.PagedBalancerecordReq;
import com.etaofinance.entity.req.PagedProjectReq;
import com.etaofinance.entity.req.PagedRechargeReq;
import com.etaofinance.entity.req.PagedWithdrawReq;


@Controller
@RequestMapping("finance")
public class FinanceController {

	
	@Autowired
	private IRechargeService rechargeService;
	
	@Autowired
	private  IWithdrawformService withdrawformService;
	
	@Autowired
	private IBalanceRecordService balanceRecordService;
	
	/**
	 * 付款
	 * @return
	 */
	@RequestMapping("buylist")
	public ModelAndView buyList() {
		
		ModelAndView view = new ModelAndView("adminView");
		
		view.addObject("subtitle", "资金管理");
		
		view.addObject("currenttitle", "付款列表");
		
		view.addObject("viewPath", "finance/buylist");
		
		return view;
	}
	
	@RequestMapping("buylistdo")
	public ModelAndView payListDo(PagedBalancerecordReq req) {
		
		ModelAndView view = new ModelAndView("finance/buylistdo");
		
		PagedResponse<BalanceRecord> listData=balanceRecordService.getBalanceRecordList(req);
		
		view.addObject("listData", listData);
		return view;
	}
	
	
	/**
	 *提现
	 * @return
	 */
	@RequestMapping("withdrawlist")
	public ModelAndView withdrawList() {
		
		ModelAndView view = new ModelAndView("adminView");
		
		view.addObject("subtitle", "资金管理");
		
		view.addObject("currenttitle", "提现列表");
		
		view.addObject("viewPath", "finance/withdrawlist");
		
		return view;
	}
	
	
	@RequestMapping("withdrawlistdo")
	public ModelAndView withdrawListDo(PagedWithdrawReq req) {
		
		ModelAndView view = new ModelAndView("finance/withdrawlistdo");
		
		PagedResponse<Withdrawform> listData=withdrawformService.getWithdrawList(req);
		
		view.addObject("listData", listData);
		return view;
	}
	
	
	/**
	 * 充值
	 * @return
	 */
	@RequestMapping("rechargelist")
	public ModelAndView rechargeList() {
		
		ModelAndView view = new ModelAndView("adminView");
		
		view.addObject("subtitle", "资金管理");
		
		view.addObject("currenttitle", "充值列表");
		
		view.addObject("viewPath", "finance/rechargelist");
		
		return view;
	}
	
	
	@RequestMapping("rechargelistdo")
	public ModelAndView rechargeListDo(PagedRechargeReq req) {
		
		ModelAndView view = new ModelAndView("finance/rechargelistdo");
		
		PagedResponse<Recharge> listData=rechargeService.getRechargeList(req);
		
		view.addObject("listData", listData);
		
		return view;
	}
	
	
	
	
	@RequestMapping("refusewithdraw")
	@ResponseBody
	public int refuse(Long id) {
		
		
		int ires= withdrawformService.Audit(id, (short)WithdrawStatus.Refuse.value());
		
		return ires;
		
	}
	
	
	@RequestMapping("agreewithdraw")
	@ResponseBody
	public int agree(Long id) {
		

		int ires = withdrawformService.Audit(id,	(short) WithdrawStatus.Pass.value());

		return ires;
	}
}
