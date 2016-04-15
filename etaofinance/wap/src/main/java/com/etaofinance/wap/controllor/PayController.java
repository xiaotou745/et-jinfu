package com.etaofinance.wap.controllor;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.etaofinance.api.common.LoginHelper;
import com.etaofinance.api.dao.inter.IMemberOtherDao;
import com.etaofinance.api.redis.RedisService;
import com.etaofinance.api.service.impl.MemberOtherService;
import com.etaofinance.api.service.impl.MemberService;
import com.etaofinance.api.service.inter.IBalanceRecordService;
import com.etaofinance.api.service.inter.IBankCardService;
import com.etaofinance.api.service.inter.IMemberApplyService;
import com.etaofinance.api.service.inter.IMemberOtherService;
import com.etaofinance.api.service.inter.IMemberService;
import com.etaofinance.api.service.inter.IProjectFavoriteService;
import com.etaofinance.api.service.inter.IProjectSubscriptionService;
import com.etaofinance.core.consts.RedissCacheKey;
import com.etaofinance.core.util.CookieUtils;
import com.etaofinance.core.util.PropertyUtils;
import com.etaofinance.entity.BankCard;
import com.etaofinance.entity.Member;
import com.etaofinance.entity.MemberOther;
import com.etaofinance.entity.domain.BalanceRecordDM;
import com.etaofinance.entity.domain.ProjectFavoriteDM;
import com.etaofinance.entity.domain.ProjectSubscriptionDM;
import com.etaofinance.entity.req.ProFavoriteReq;
import com.etaofinance.entity.req.ProSubInvestReq;
import com.etaofinance.entity.req.PublicMemberReq;
import com.etaofinance.wap.common.LoginUtil;
import com.etaofinance.wap.common.RequireLogin;
import com.etaofinance.wap.common.UserContext;

/**
 * 支付
 * 所有的页面Controoler
 * @author ofmyi_000
 *
 */
@RequestMapping("pay")
@Controller
public class PayController {

	@Autowired
	HttpServletRequest request;
	@Autowired
    RedisService redisService;
	@Autowired
	HttpServletResponse response;
	
	@Autowired
	IMemberService memberService;
	
	
	/**
	 * 创建支付密码第1个页面
	 * @return
	 */
	@RequestMapping("setpaypasswordstep1")
	public ModelAndView setpaypasswordstep1()
	{
		ModelAndView view = new ModelAndView("wapView");
		view.addObject("currenttitle", "设置支付密码");
		view.addObject("viewPath", "pay/setpaypasswordstep1");		
		
		long memberId= UserContext.getCurrentContext(request).getUserInfo().getId();
		Member member=memberService.getById(memberId);
		String phone=member.getPhoneno();	
		view.addObject("phone", phone);	
		return view;
	}	
	
	/**
	 * 创建支付密码第2个页面
	 * @return
	 */
	@RequestMapping("setpaypasswordstep2")
	public ModelAndView retrievepasswordstep1(String checkKey) throws IOException
	{
		ModelAndView view = new ModelAndView("wapView");
		view.addObject("currenttitle", "设置支付密码");
		view.addObject("viewPath", "pay/setpaypasswordstep2");	
		view.addObject("checkKey", checkKey);	
		return view;
	}	
	
	/**
	 * 修改支付密码第1个页面
	 * @return
	 */
	@RequestMapping("modifypaypasswordstep1")
	public ModelAndView modifypaypasswordstep1()
	{
		ModelAndView view = new ModelAndView("wapView");
		view.addObject("currenttitle", "修改支付密码");
		view.addObject("viewPath", "pay/modifypaypasswordstep1");		
		return view;
	}	
	
	/**
	 * 修改支付密码第2个页面
	 * @return
	 */
	@RequestMapping("modifypaypasswordstep2")
	public ModelAndView modifypaypasswordstep2(String checkKey) throws IOException
	{
		ModelAndView view = new ModelAndView("wapView");
		view.addObject("currenttitle", "修改支付密码");
		view.addObject("viewPath", "pay/modifypaypasswordstep2");	
		view.addObject("checkKey", checkKey);	
		return view;
	}
	
	/**
	 * 找回支付密码第1个页面
	 * @return
	 */
	@RequestMapping("retrievepaypasswordstep1")
	public ModelAndView retrievepaypasswordstep1()
	{
		ModelAndView view = new ModelAndView("wapView");
		view.addObject("currenttitle", "找回支付密码");
		view.addObject("viewPath", "pay/retrievepaypasswordstep1");
		
		long memberId= UserContext.getCurrentContext(request).getUserInfo().getId();
		Member member=memberService.getById(memberId);
		String phone=member.getPhoneno();	
		view.addObject("phone", phone);	
		return view;
	}	
}
