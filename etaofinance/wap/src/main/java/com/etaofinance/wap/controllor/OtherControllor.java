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

import com.etaofinance.api.service.inter.IBankService;
import com.etaofinance.api.service.inter.IFeedBackService;
import com.etaofinance.api.service.inter.IMessageService;
import com.etaofinance.api.service.inter.IPublicProviceCityService;
import com.etaofinance.api.service.inter.IPublicProvinceCityService;
import com.etaofinance.api.service.inter.IQAService;
import com.etaofinance.api.service.inter.ISuggestionService;
import com.etaofinance.core.util.SystemUtils;
import com.etaofinance.entity.Bank;
import com.etaofinance.entity.FeedBack;
import com.etaofinance.entity.Message;
import com.etaofinance.entity.PublicProvinceCity;
import com.etaofinance.entity.QA;
import com.etaofinance.entity.ZcSuggestion;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.req.PagedQAReq;
import com.etaofinance.entity.resp.FeedBackResp;

@Controller
@RequestMapping("other")
public class OtherControllor {
	@Autowired
	private IQAService qAService;
	
	@Autowired
	private IFeedBackService feedBackService;	

	@Autowired
	private IMessageService messageService;	

	/**
	 * 获取常见问题列表
	 * @param 
	 * @author hulingbo
	 * @date 2016年3月24日18:05:14
	 * @return
	 */
	@RequestMapping("/getqalist")
	@ResponseBody
	public  List<QA> getQAlist() {
		return qAService.getList();
	}
	
	/**
	 * 创建意见反馈
	 * @param 
	 * @author hulingbo
	 * @date 2016年3月24日18:05:14
	 * @return
	 */
	@RequestMapping("/createfeedback")
	@ResponseBody
	public HttpResultModel<FeedBackResp> createFeedBack(@RequestBody  FeedBack record) 
	{		
		return	feedBackService.create(record);
	}
	

	/**
	 * 获取我的消息列表
	 * @param 
	 * @author hulingbo
	 * @date 2016年3月29日17:25:51
	 * @return
	 */
	@RequestMapping("/getmessagelist")
	@ResponseBody
	public  List<Message> getMessagelist(@RequestBody  Message record) {
		return messageService.getList(record);
	}
	
}
