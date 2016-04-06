package com.etaofinance.wap.controllor;



import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;













import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.etaofinance.api.service.inter.IMemberService;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.req.SendCodeReq;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiOperation;


/**
 * 公共模块
 * @author hulingbo
 * @date 2016年4月5日16:15:06
 *
 */
@Controller
@RequestMapping("common")
public class CommonController {
	@Autowired
	IMemberService memberService;	
	
@RequestMapping("/swagger")
	public ModelAndView suggAdd() {
		ModelAndView model = new ModelAndView("common/swagger");
		return model;
	}

	/**
	 * 获取图片验证
	 * @param 
	 * @author ruhuaxiao 
	 * @date 2016??3??25??16:53:16
	 * @return
	 */
	@RequestMapping("code")
	@ApiOperation(value = "图片验证码", httpMethod = "POST", 
	consumes="application/json;charset=UFT-8",produces="application/json;charset=UFT-8",
	notes = "图片验证码")
	public ModelAndView code(int type) {
		ModelAndView mv = new ModelAndView("user/code");
		mv.addObject("CodeType", type);
		return mv;
	}
	/**
	 * 获取验证码接口
	 * @param req
	 * @return
	 */
	@RequestMapping("sendcode")
	@ResponseBody
	@ApiOperation(value = "发送验证码", httpMethod = "POST", 
	consumes="application/json;charset=UFT-8",produces="application/json;charset=UFT-8",
	notes = "获取验证码")
	public  HttpResultModel<Object> sendcode(@RequestBody SendCodeReq req) {
		return memberService.sendCode(req);
	}
	
	/**
	 * 获取验证码接口
	 * @param req
	 * @return
	 */
	@RequestMapping("sendcode")
	@ResponseBody
	@ApiOperation(value = "发送验证码", httpMethod = "POST", 
	consumes="application/json;charset=UFT-8",produces="application/json;charset=UFT-8",
	notes = "获取验证码")
	public  HttpResultModel<Object> sendcode(@RequestBody SendCodeReq req) {
		return memberService.sendCode(req);
	}
	
}
