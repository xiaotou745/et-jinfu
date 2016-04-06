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
import com.etaofinance.entity.BalanceRecord;
import com.etaofinance.entity.Bank;
import com.etaofinance.entity.BankCard;
import com.etaofinance.entity.MemberOther;
import com.etaofinance.entity.PublicProvinceCity;
import com.etaofinance.entity.ZcSuggestion;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.common.ResponseBase;
import com.etaofinance.entity.domain.BalanceRecordDM;
import com.etaofinance.entity.req.MemberIdReq;
import com.etaofinance.entity.resp.FeedBackResp;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("bank")
public class BankControllor {
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
	@ApiOperation(value = "获取银行列表", httpMethod = "POST", 
	consumes="application/json;charset=UFT-8",produces="application/json;charset=UFT-8",
	notes = "获取银行列表")
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
	@ApiOperation(value = "绑定银行卡", httpMethod = "POST", 
	consumes="application/json;charset=UFT-8",produces="application/json;charset=UFT-8",
	notes = "绑定银行卡")
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
	@ApiOperation(value = "解绑银行卡", httpMethod = "POST", 
	consumes="application/json;charset=UFT-8",produces="application/json;charset=UFT-8",
	notes = "解绑银行卡")
	public int UnBindBankCard(@RequestBody  BankCard record)
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
	@ApiOperation(value = "获取银行卡列表", httpMethod = "POST", 
	consumes="application/json;charset=UFT-8",produces="application/json;charset=UFT-8",
	notes = "获取银行卡列表")
	public List<BankCard> getBankCardList(@RequestBody  MemberIdReq record)
	{
		return bankCardService.getListByMemberId(record.getMemberId());
	}		



}
