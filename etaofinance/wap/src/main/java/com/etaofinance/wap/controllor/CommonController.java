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

import com.etaofinance.api.redis.RedisService;
import com.etaofinance.api.service.inter.IMemberOtherService;
import com.etaofinance.api.service.inter.IMemberService;
import com.etaofinance.core.consts.RedissCacheKey;
import com.etaofinance.core.security.MD5Util;
import com.etaofinance.core.util.CookieUtils;
import com.etaofinance.core.util.JsonUtil;
import com.etaofinance.core.util.PropertyUtils;
import com.etaofinance.core.util.RegexHelper;
import com.etaofinance.entity.Member;
import com.etaofinance.entity.MemberOther;
import com.etaofinance.entity.req.ForgetPwdOneReq;
import com.etaofinance.entity.req.ForgetPwdThreeReq;
import com.etaofinance.entity.req.ForgetPwdTwoReq;
import com.etaofinance.entity.req.LoginReq;
import com.etaofinance.entity.req.ModifypwdReq;
import com.etaofinance.entity.req.RegistReq;
import com.etaofinance.entity.req.SendCodeReq;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.resp.ForgetPwdResp;
import com.etaofinance.entity.resp.MemberResp;
import com.etaofinance.entity.resp.SendCodeResp;
import com.etaofinance.wap.common.LoginUtil;
import com.etaofinance.wap.common.NoRequireLogin;
import com.etaofinance.wap.common.RequireLogin;
import com.etaofinance.wap.common.UserContext;
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
	 * @date 2016年3月25日16:53:16
	 * @return
	 */
	@RequestMapping("code")
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
	
}
