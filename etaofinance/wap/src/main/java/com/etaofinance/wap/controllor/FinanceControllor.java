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

import com.etaofinance.api.service.inter.IBankCardService;
import com.etaofinance.api.service.inter.IBankService;
import com.etaofinance.api.service.inter.IPublicProviceCityService;
import com.etaofinance.api.service.inter.IPublicProvinceCityService;
import com.etaofinance.api.service.inter.ISuggestionService;
import com.etaofinance.core.util.SystemUtils;
import com.etaofinance.entity.Bank;
import com.etaofinance.entity.BankCard;
import com.etaofinance.entity.MemberOther;
import com.etaofinance.entity.PublicProvinceCity;
import com.etaofinance.entity.ZcSuggestion;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.common.ResponseBase;

@Controller
@RequestMapping("finance")
public class FinanceControllor {
	@Autowired
	private IBankService bankService;
	
	@Autowired
	private IBankCardService bankCardService;	


	/**
	 * 获取银行列表
	 * @param 
	 * @author hulingbo
	 * @date 2016年3月24日18:05:14
	 * @return
	 */
	@RequestMapping("/getbanklist")
	@ResponseBody
	public List<Bank> getBankList()
	{
		return bankService.getList();
	}	
	
	/**
	 * 绑定银行卡
	 * @param 
	 * @author hulingbo
	 * @date 2016年3月25日17:07:19
	 * @return
	 */
	@RequestMapping("/bindbankcard")
	@ResponseBody
	HttpResultModel<ResponseBase> BindBankCard(@RequestBody  BankCard record)
	{
		return bankCardService.create(record);
	}
	
	/**
	 * 解绑银行卡
	 * @param 
	 * @author hulingbo
	 * @date 2016年3月25日17:09:37
	 * @return
	 */
	@RequestMapping("/unbindbankcard")
	@ResponseBody
	int UnBindBankCard(@RequestBody  BankCard record)
	{
		return bankCardService.remove(record.getId());
	}
	
	/**
	 * 获取银行卡列表
	 * @param 
	 * @author hulingbo
	 * @date 2016年3月25日17:13:18
	 * @return
	 */
	@RequestMapping("/getbankcardlist")
	@ResponseBody
	public List<BankCard> getBankCardList(@RequestBody  BankCard record)
	{
		return bankCardService.getListByMemberId(record.getMemberid());
	}	
	
	

}
