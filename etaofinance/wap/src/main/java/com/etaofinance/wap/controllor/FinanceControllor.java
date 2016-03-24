package com.etaofinance.wap.controllor;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.etaofinance.api.service.inter.IBankService;
import com.etaofinance.api.service.inter.IPublicProviceCityService;
import com.etaofinance.api.service.inter.IPublicProvinceCityService;
import com.etaofinance.api.service.inter.ISuggestionService;
import com.etaofinance.core.util.SystemUtils;
import com.etaofinance.entity.Bank;
import com.etaofinance.entity.PublicProvinceCity;
import com.etaofinance.entity.ZcSuggestion;

@Controller
@RequestMapping("finance")
public class FinanceControllor {
	@Autowired
	private IBankService bankService;

	
	@RequestMapping("/getbanklist")
	@ResponseBody
	public List<Bank> getBankList()
	{
		return bankService.getBankList();
	}
}
