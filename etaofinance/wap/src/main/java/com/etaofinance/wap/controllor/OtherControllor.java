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
import com.etaofinance.api.service.inter.IQAService;
import com.etaofinance.api.service.inter.ISuggestionService;
import com.etaofinance.core.util.SystemUtils;
import com.etaofinance.entity.Bank;
import com.etaofinance.entity.PublicProvinceCity;
import com.etaofinance.entity.QA;
import com.etaofinance.entity.ZcSuggestion;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.req.PagedQAReq;

@Controller
@RequestMapping("other")
public class OtherControllor {
	@Autowired
	private IQAService qAService;
	

	@RequestMapping("/getqalist")
	@ResponseBody
	public  List<QA> getQAlist() {
		return qAService.getList();
	}
}
