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
import com.etaofinance.core.util.SystemUtils;
import com.etaofinance.entity.BalanceRecord;
import com.etaofinance.entity.Bank;
import com.etaofinance.entity.BankCard;
import com.etaofinance.entity.MemberOther;
import com.etaofinance.entity.PublicProvinceCity;
import com.etaofinance.entity.ZcSuggestion;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.common.ResponseBase;
import com.etaofinance.entity.domain.BalanceRecordDM;

@Controller
@RequestMapping("finance")
public class FinanceControllor {
	@Autowired
	private IBalanceRecordService balanceRecordService;	

	
	/**
	 * 获取账户流水
	 * @param 
	 * @author hulingbo
	 * @date 2016年3月25日17:13:18
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
	@RequestMapping("/getbalancerecordDetail")
	@ResponseBody
	public BalanceRecordDM getBalanceRecordDetail(@RequestBody BalanceRecord record)
	{
		return balanceRecordService.selectDMByPrimaryKey(record.getId());
	}
	
	

}
