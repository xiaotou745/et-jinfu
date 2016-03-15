package com.etaofinance.wap.controllor;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.etaofinance.api.service.inter.IPublicProviceCityService;
import com.etaofinance.api.service.inter.ISuggestionService;
import com.etaofinance.core.util.ParseHelper;
import com.etaofinance.core.util.SystemUtils;
import com.etaofinance.entity.PublicProvinceCity;
import com.etaofinance.entity.Suggestion;
import com.etaofinance.entity.ZcSuggestion;

@Controller
//@RequestMapping("suggestion")
public class SuggestionControllor {
	@Autowired
	private ISuggestionService suggestionService;

	@Autowired
	private IPublicProviceCityService publicProviceCityService;

	@RequestMapping("/zt")
	public ModelAndView suggAdd() {

		// 这里初始化城市
		List<PublicProvinceCity> list = publicProviceCityService
				.getAllOpenCity();
		ModelAndView model = new ModelAndView("suggestion/sugg");

		// StringBuffer sb = new StringBuffer();
		// 现有店铺
		String city = "";

		List<PublicProvinceCity> oneList = list.stream()
				.filter(k -> k.getJiBie() == 2).collect(Collectors.toList());
//		int tmp_i = 0;
		for (PublicProvinceCity item : oneList) {
//			tmp_i++;
			 List<PublicProvinceCity> twoList = list
			 .stream()
			 .filter(k_t ->k_t.getParentCode().equals( item.getCode()) )
			 .collect(Collectors.toList());
//			String isOn = tmp_i == 1 ? "on" : "";

			city += "<div class=\"popup_con\"><p>"
					+ item.getName() + "<i></i></p>" + "<ul>";

			 for (PublicProvinceCity item_two : twoList) {
			 city += "<li>" + item_two.getName() + "</li>";
			 }
			city += " </ul> " + "</div>";

		}
		model.addObject("openCity", city);
		return model;
	}

	@RequestMapping("addsuggestion")
	@ResponseBody
	public int add(HttpServletRequest request, ZcSuggestion record) {
		String clientIp = SystemUtils.getClientIp(request);
		record.setClientip(clientIp);
		return suggestionService.insertZcSuggestion(record);
	}
}
