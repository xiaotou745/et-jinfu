package com.etaofinance.wap.controllor;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.etaofinance.api.service.inter.IBalanceRecordService;
import com.etaofinance.api.service.inter.IBankCardService;
import com.etaofinance.api.service.inter.IBankService;
import com.etaofinance.api.service.inter.IPublicProviceCityService;
import com.etaofinance.api.service.inter.IPublicProvinceCityService;
import com.etaofinance.api.service.inter.ISuggestionService;
import com.etaofinance.api.service.inter.IWithdrawformService;
import com.etaofinance.core.util.SystemUtils;
import com.etaofinance.entity.BalanceRecord;
import com.etaofinance.entity.Bank;
import com.etaofinance.entity.BankCard;
import com.etaofinance.entity.MemberOther;
import com.etaofinance.entity.PublicProvinceCity;
import com.etaofinance.entity.Withdrawform;
import com.etaofinance.entity.ZcSuggestion;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.common.ResponseBase;
import com.etaofinance.entity.domain.BalanceRecordDM;
import com.etaofinance.entity.domain.WithdrawformDM;
import com.etaofinance.entity.req.PublicMemberReq;
import com.etaofinance.wap.common.RequireLogin;
import com.etaofinance.wap.common.UserContext;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("finance")
public class FinanceControllor {
	@Autowired
	private IBalanceRecordService balanceRecordService;	

	@Autowired
	private IWithdrawformService withdrawformService;	
	
	@Autowired
	HttpServletRequest request;
	
	/**
	 * 获取账户流水
	 * @param 
	 * @author hulingbo
	 * @date 2016年3月29日15:58:38
	 * @return
	 */
	@RequestMapping("/getbalancerecordlist")
	@ResponseBody
	@RequireLogin
	@ApiOperation(value = "获取账户流水", httpMethod = "POST", 
	consumes="application/json;charset=UFT-8",produces="application/json;charset=UFT-8",
	notes = "获取账户流水")
	public List<BalanceRecordDM> getBalanceRecordList(@RequestBody PublicMemberReq record)
	{
		Long memberid=UserContext.getCurrentContext(request).getUserInfo().getId();	
		record.setMemberId(memberid);	
		return balanceRecordService.getListMore(record);
	}
	
	/**
	 * 获取账户流水详情
	 * @param 
	 * @author hulingbo
	 * @date 2016年3月25日17:13:18
	 * @return
	 */
	@RequestMapping("/getbalancerecorddetail")
	@ResponseBody
	@RequireLogin
	@ApiOperation(value = "获取账户流水详情", httpMethod = "POST", 
	consumes="application/json;charset=UFT-8",produces="application/json;charset=UFT-8",
	notes = "获取账户流水详情")
	public HttpResultModel<BalanceRecordDM> getBalanceRecordDetail(@RequestBody PublicMemberReq record)
	{
		Long memberid=UserContext.getCurrentContext(request).getUserInfo().getId();	
		record.setMemberId(memberid);	
		return balanceRecordService.selectBRDetail(record);
	}
	
	/**
	 * 提现
	 * @param 
	 * @author hulingbo
	 * @date 2016年3月29日15:58:02
	 * @return
	 */
	@RequestMapping("/createwithdrawform")
	@ResponseBody
	@RequireLogin
	@ApiOperation(value = "提现", httpMethod = "POST", 
	consumes="application/json;charset=UFT-8",produces="application/json;charset=UFT-8",
	notes = "提现")
	public HttpResultModel<Object> createWithdrawform(@RequestBody Withdrawform record)
	{
		Long memberid=UserContext.getCurrentContext(request).getUserInfo().getId();	
		record.setMemberid(memberid);	
		return withdrawformService.create(record);
	}
	
	/**
	 * 获取可提现记录
	 * @param 
	 * @author hulingbo
	 * @date 2016年3月29日16:34:09
	 * @return
	 */
	@RequestMapping("/getwithdrawformlist")
	@ResponseBody
	@RequireLogin
	@ApiOperation(value = "获取可提现记录", httpMethod = "POST", 
	consumes="application/json;charset=UFT-8",produces="application/json;charset=UFT-8",
	notes = "获取可提现记录")
	public List<WithdrawformDM> getWithdrawformList(@RequestBody PublicMemberReq record)
	{		
		Long memberid=UserContext.getCurrentContext(request).getUserInfo().getId();	
		record.setMemberId(memberid);	
		return withdrawformService.getListMore(record);
	}
	
	/**
	 * 获取可提现记录详情
	 * @param 
	 * @author hulingbo
	 * @date 2016年3月29日16:34:20
	 * @return
	 */
	@RequestMapping("/getWithdrawformdetail")
	@ResponseBody
	@RequireLogin
	@ApiOperation(value = "获取可提现记录详情", httpMethod = "POST", 
	consumes="application/json;charset=UFT-8",produces="application/json;charset=UFT-8",
	notes = "获取可提现记录详情")
	public HttpResultModel<WithdrawformDM> getWithdrawformDetail(@RequestBody PublicMemberReq record)
	{
		Long memberid=UserContext.getCurrentContext(request).getUserInfo().getId();	
		record.setMemberId(memberid);	
		return withdrawformService.selectWFDetail(record);
	}
	
	

}
