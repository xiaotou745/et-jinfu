package com.etaofinance.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.etaofinance.api.service.inter.ISuggestionService;
import com.etaofinance.core.enums.ProjectAuditStatus;
import com.etaofinance.entity.Project;
import com.etaofinance.entity.ZcSuggestion;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.req.PagedZcSuggestionReq;
import com.etaofinance.entity.resp.QAResp;

@Controller
@RequestMapping("suggestion")
public class ZcSuggestionController {

	@Autowired
	ISuggestionService suggestionService;

	@RequestMapping("list")
	public ModelAndView list() {
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "问卷调整");
		view.addObject("currenttitle", "问卷调整列表");
		view.addObject("viewPath", "suggestion/list");
		return view;
	}

	@RequestMapping("listdo")
	public ModelAndView listdo(PagedZcSuggestionReq req) {
		// req.setPageSize(1);
		ModelAndView view = new ModelAndView("suggestion/listdo");
		PagedResponse<ZcSuggestion> listData = suggestionService
				.queryZcSuggestionList(req);
		view.addObject("listData", listData);
		return view;
	}

	@ResponseBody
	@RequestMapping("showview")
	public HttpResultModel<ZcSuggestion> showview(int id) {
		return suggestionService.showview(id);
	}
}
