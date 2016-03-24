package com.etaofinance.admin.controller;


import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.etaofinance.admin.common.UserContext;
import com.etaofinance.api.service.inter.IQAService;
import com.etaofinance.api.service.inter.IQAService;
import com.etaofinance.entity.QA;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.req.PagedQAReq;
import com.etaofinance.entity.req.PagedQAReq;
import com.etaofinance.entity.resp.QAResp;



@Controller
@RequestMapping("qa")
public class QAController {
	 //广告
	 @Autowired
	 private IQAService  qAService;	 

	/**
	 * 问答列表管理页面 
	 * @author hulignbo
	 * @Date 2016年3月15日11:41:26
	 * @param search 查询条件实体
	 * @return
	 */
	@RequestMapping("list")
	public ModelAndView list(HttpServletRequest request){		
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "运营管理");
		model.addObject("currenttitle", "问答列表");	
		model.addObject("viewPath", "qa/list");
		return model;
	}	
	
	/**
	 * 问答列表页面 
	 * @author hulignbo
	 * @Date 2016年3月15日11:41:26
	 * @param search 查询条件实体
	 * @return
	 * @throws ParseException 
	 */	
	@RequestMapping("listdo")
	public ModelAndView listdo(HttpServletRequest request,PagedQAReq req) throws ParseException {		
		UserContext context = UserContext.getCurrentContext(request);
		PagedResponse<QA> resp = qAService.query(req);
		ModelAndView model = new ModelAndView("qa/listdo");
		model.addObject("listData", resp);
		return model;
	}		
	
	@RequestMapping("selectbyprimarykey")
	@ResponseBody
	public QA selectByPrimaryKey(int id)
	{
		QA model=qAService.getById(id);
		return model;
	}
	
	@RequestMapping("add")
	@ResponseBody
	public HttpResultModel<QAResp> add(QA record, HttpServletRequest request) {
		record.setCreatetime(new Date());
		record.setCreatename(UserContext.getCurrentContext(request).getLoginName());

		HttpResultModel<QAResp> resp= qAService.create(record);
		return resp;
	}
	
	@RequestMapping("modify")
	@ResponseBody
	public HttpResultModel<QAResp> modify(QA  record, HttpServletRequest request) {
		HttpResultModel<QAResp> resp= qAService.modify(record);	
		return resp;
	}

	@RequestMapping("del")
	@ResponseBody
	public int del(Integer id) {
		return qAService.remove(id);
	}
}
