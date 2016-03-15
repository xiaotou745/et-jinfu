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
import com.etaofinance.api.service.inter.IADVertService;
import com.etaofinance.entity.ADVert;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.req.PagedADVertReq;
import com.etaofinance.entity.resp.ADVertResp;



@Controller
@RequestMapping("advert")
public class ADVertController {
	 //广告
	 @Autowired
	 private IADVertService  aDVertService;	 

	/**
	 * 广告列表管理页面 
	 * @author hulignbo
	 * @Date 2016年3月15日11:41:26
	 * @param search 查询条件实体
	 * @return
	 */
	@RequestMapping("list")
	public ModelAndView list(HttpServletRequest request){		
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "运营管理");
		model.addObject("currenttitle", "广告列表");	
		model.addObject("viewPath", "advert/list");
		return model;
	}	
	
	/**
	 * 广告列表页面 
	 * @author hulignbo
	 * @Date 2016年3月15日11:41:26
	 * @param search 查询条件实体
	 * @return
	 * @throws ParseException 
	 */	
	@RequestMapping("listdo")
	public ModelAndView listdo(HttpServletRequest request,PagedADVertReq req) throws ParseException {		
		UserContext context = UserContext.getCurrentContext(request);
		PagedResponse<ADVert> resp = aDVertService.query(req);
		ModelAndView model = new ModelAndView("advert/listdo");
		model.addObject("listData", resp);
		return model;
	}		
	
	@RequestMapping("add")
	@ResponseBody
	public HttpResultModel<ADVertResp> add(ADVert record, HttpServletRequest request) {
		record.setCreatetime(new Date());
		record.setCreatename(UserContext.getCurrentContext(request).getLoginName());

		HttpResultModel<ADVertResp> resp= aDVertService.add(record);	
		return resp;
	}
//	
//	@RequestMapping("modify")
//	@ResponseBody
//	public HttpResultModel<TaskDistributionResp> modify(TaskDistribution  record, HttpServletRequest request) {
//		record.setUpdatename(UserContext.getCurrentContext(request).getLoginName());
//		record.setUpdatetime(new Date());
//		return taskDistributionService.modify(record);
//	}

}
