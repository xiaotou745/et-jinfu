package com.etaofinance.wap.controllor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etaofinance.api.service.inter.IMessageService;
import com.etaofinance.api.service.inter.IProjectService;
import com.etaofinance.entity.Message;
import com.etaofinance.entity.ProjectFavorite;
import com.etaofinance.entity.common.HttpResultModel;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("msg")
public class MessageController {
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpServletResponse response;
	
	@Autowired
	IMessageService  messageService;
	
	
	/**
	 * 
	 * @param msg
	 * @return
	 */
	@RequestMapping("read")
	@ResponseBody
	@ApiOperation(value = "读消息", httpMethod = "POST", 
	consumes="application/json;charset=UFT-8",produces="application/json;charset=UFT-8",
	notes = "读消息")
	public HttpResultModel<Object> readMsg(@RequestBody Message msg)
	{
		return messageService.readMsg(msg);
	}
	
	/**
	 * 
	 * @param msg
	 * @return
	 */
	@RequestMapping("delete")
	@ResponseBody
	@ApiOperation(value = "删除消息", httpMethod = "POST", 
	consumes="application/json;charset=UFT-8",produces="application/json;charset=UFT-8",
	notes = "删除消息")
	public HttpResultModel<Object> deleteMsg(@RequestBody Message msg)
	{
		return messageService.delMsg(msg);
	}
	
	/**
	 * 
	 * @param msg
	 * @return
	 */
	@RequestMapping("send")
	@ResponseBody
	@ApiOperation(value = "发送消息", httpMethod = "POST", 
	consumes="application/json;charset=UFT-8",produces="application/json;charset=UFT-8",
	notes = "发送消息")
	public HttpResultModel<Object> sendMsg(@RequestBody Message msg)
	{
		return messageService.addMsgSelective(msg);
	}
}
