package com.etaofinance.licaiwap.controllor;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.etaofinance.api.service.inter.ISuggestionService;
import com.etaofinance.core.util.SystemUtils;
import com.etaofinance.entity.Suggestion;

@Controller
//@RequestMapping("suggestion")
public class SuggestionControllor {
	@Autowired
	private ISuggestionService suggestionService;
	
	@RequestMapping("/zt")
	public ModelAndView suggAdd() {
		ModelAndView model=new ModelAndView("suggestion/sugg");
		return model;
	}
	
	@RequestMapping("addsuggestion")
	@ResponseBody
	public int add(HttpServletRequest request,Suggestion record) {
		String clientIp = SystemUtils.getClientIp(request);
		record.setClientip(clientIp);
		return suggestionService.insert(record);
	}
}
