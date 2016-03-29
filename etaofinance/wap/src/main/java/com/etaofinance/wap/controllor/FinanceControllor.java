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

@Controller
@RequestMapping("finance")
public class FinanceControllor {
	@Autowired
	private IBalanceRecordService balanceRecordService;	

	@Autowired
	private IWithdrawformService withdrawformService;	
	
	/**
	 * 获取账户流水
	 * @param 
	 * @author hulingbo
	 * @date 2016年3月29日15:58:38
	 * @return
	 */
	@RequestMapping("/getbalancerecordlist")
	@ResponseBody
	public List<BalanceRecordDM> getBalanceRecordList(@RequestBody BalanceRecord record)
	{
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
	public BalanceRecordDM getBalanceRecordDetail(@RequestBody BalanceRecord record)
	{
		return balanceRecordService.selectDMByPrimaryKey(record.getId());
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
	public HttpResultModel<ResponseBase> createWithdrawform(@RequestBody Withdrawform record)
	{
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
	public List<WithdrawformDM> getWithdrawformList(@RequestBody Withdrawform record)
	{
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
	public WithdrawformDM getWithdrawformDetail(@RequestBody Withdrawform record)
	{
		return withdrawformService.selectDMByPrimaryKey(record.getId());
	}
	
	

}
