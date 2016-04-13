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
 * 登录页面
 * 所有的页面Controoler
 * @author ofmyi_000
 *
 */
@RequestMapping("me")
@Controller
public class MeController {

	@Autowired
	HttpServletRequest request;
	@Autowired
    RedisService redisService;
	@Autowired
	HttpServletResponse response;
	@Autowired
	IMemberService memberService;
	@Autowired
	IMemberOtherService memberOtherService;
	@Autowired
	IBankCardService bankService;
	@Autowired
	private IBalanceRecordService balanceRecordService;
	@Autowired
	IMemberApplyService memberApplyService;
	@Autowired
	IProjectSubscriptionService  projectSubscriptionService;
	@Autowired
	IProjectFavoriteService  projectFavoriteService;
	/**
	 * 登录页面
	 * @return
	 */
	@RequestMapping("login")
	public ModelAndView login(String reUrl)
	{
		ModelAndView view = new ModelAndView("wapView");
		view.addObject("currenttitle", "登录");
		view.addObject("viewPath", "me/login");
		view.addObject("reUrl",reUrl==null?"":reUrl);
		return view;
	}
	/**
	 * 登录页面
	 * @return
	 */
	@RequestMapping("logout")
	public ModelAndView logout()
	{
		ModelAndView view = new ModelAndView("me/logout");
		//删除Cookie
		CookieUtils.deleteCookie(request, response, LoginUtil.LOGIN_COOKIE_NAME);
		return view;
	}
	/**
	 * 注册页面
	 * @return
	 */
	@RequestMapping("register")
	public ModelAndView register(String reUrl)
	{
		ModelAndView view = new ModelAndView("wapView");
		view.addObject("currenttitle", "注册");
		view.addObject("viewPath", "me/register");
		view.addObject("reUrl",reUrl==null?"":reUrl);
		return view;
	}
	/**
	 * 忘记密码第一个页面
	 * @return
	 */
	@RequestMapping("retrievepasswordstep1")
	public ModelAndView retrievepasswordstep1()
	{
		ModelAndView view = new ModelAndView("wapView");
		view.addObject("currenttitle", "找回密码");
		view.addObject("viewPath", "me/retrievepasswordstep1");
		return view;
	}
	/**
	 * 忘记密码第二个页面
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("retrievepasswordstep2")
	public ModelAndView retrievepasswordstep2(Long userId,String checkKey) throws IOException
	{
		String key=String.format(RedissCacheKey.JF_Member_FindPassWordSetpOne,checkKey);
		String value=redisService.get(key, String.class);
		ModelAndView view = new ModelAndView("wapView");
		view.addObject("currenttitle", "找回密码");
		view.addObject("viewPath", "me/retrievepasswordstep2");
		if(value==null||value.equals("")||!value.equals(checkKey))
		{
			ModelAndView view2 = new ModelAndView("wapView");
			view2.addObject("currenttitle", "找回密码");
			view2.addObject("viewPath", "me/retrievepasswordstep1");
			return view2;
		}
		Member member=memberService.getById(userId);
		String phone=member.getPhoneno();
		
		String sString=phone.substring(0,phone.length()-(phone.substring(3)).length())+"****"+phone.substring(7);
		view.addObject("phoneString", sString);
		view.addObject("phone", phone);
		view.addObject("checkKey", checkKey);
		return view;
	}
	/**
	 * 忘记密码第三个页面
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("retrievepasswordstep3")
	public ModelAndView retrievepasswordstep3(String checkKey,Long userId) throws IOException
	{
		String key=String.format(RedissCacheKey.JF_Member_FindPassWordSetpTwo,checkKey);
		String value=redisService.get(key, String.class);
		ModelAndView view= new ModelAndView("wapView");
		view.addObject("currenttitle", "找回密码");
		view.addObject("viewPath", "me/retrievepasswordstep3");
		if(value==null||value.equals("")||!value.equals(checkKey))
		{
			ModelAndView view2= new ModelAndView("wapView");
			view2.addObject("currenttitle", "找回密码");
			view2.addObject("viewPath", "me/retrievepasswordstep1");
			return view2;
		}
		view.addObject("checkKey", checkKey);
		view.addObject("userId", userId);
		return view;
	}
	/**
	 * 用户中心
	 * @param checkKey
	 * @param userId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("usercenter")
	@RequireLogin
	public ModelAndView usercenter() 
	{
		ModelAndView view= new ModelAndView("wapView");
		view.addObject("currenttitle", "个人中心");
		view.addObject("viewPath", "me/usercenter");
		Member member=UserContext.getCurrentContext(request).getUserInfo();
		member=memberService.getById(member.getId());
		MemberOther other=memberOtherService.getByMemberId(member.getId());
		view.addObject("member", member);
		view.addObject("other", other);
		return view;
	}
	/**
	 * 账户信息
	 * @param checkKey
	 * @param userId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("accountblance")
	@RequireLogin
	public ModelAndView accountblance() 
	{
		ModelAndView view= new ModelAndView("wapView");
		view.addObject("currenttitle", "账户信息");
		view.addObject("viewPath", "me/accountblance");
		Member member=UserContext.getCurrentContext(request).getUserInfo();
		MemberOther other=memberOtherService.getByMemberId(member.getId());
		List<BankCard> list=bankService.getListByMemberId(member.getId());
		if(list==null||list.size()==0)
		{
			view.addObject("cardsize", 0);
		}
		else
		{
			view.addObject("cardsize", list.size());
		}
		view.addObject("other", other);
		return view;
	}
	/**
	 * 账户流水
	 * @param checkKey
	 * @param userId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("accountflow")
	@RequireLogin
	public ModelAndView accountflow() 
	{
		ModelAndView view= new ModelAndView("wapView");
		view.addObject("currenttitle", "账户流水");
		view.addObject("viewPath", "me/accountflow");
		Member member=UserContext.getCurrentContext(request).getUserInfo();
		PublicMemberReq record =new PublicMemberReq();
		record.setMemberId(member.getId());
		List<BalanceRecordDM> list=balanceRecordService.getListMore(record);
		view.addObject("list", list);
		return view;
	}
	/**
	 * 用户信息
	 * @param checkKey
	 * @param userId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("userinfo")
	@RequireLogin
	public ModelAndView userinfo() 
	{
		ModelAndView view= new ModelAndView("wapView");
		view.addObject("currenttitle", "用户信息");
		view.addObject("viewPath", "me/userinfo");
		Member member=UserContext.getCurrentContext(request).getUserInfo();
		member=memberService.getById(member.getId());
		view.addObject("member",member);
		return view;
	}
	/**
	 * 修改用户名
	 * @param checkKey
	 * @param userId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("setusername")
	@RequireLogin
	public ModelAndView setusername() 
	{
		ModelAndView view= new ModelAndView("wapView");
		view.addObject("currenttitle", "修改用户名");
		view.addObject("viewPath", "me/setusername");
		return view;
	}
	/**
	 * 实名认证
	 * @param checkKey
	 * @param userId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("certification")
	@RequireLogin
	public ModelAndView certification() 
	{
		ModelAndView view= new ModelAndView("wapView");
		view.addObject("currenttitle", "实名认证");
		view.addObject("viewPath", "me/certification");
		Member member=memberService.getById(UserContext.getCurrentContext(request).getUserInfo().getId());
		view.addObject("member", member);
		return view;
	}
	/**
	 * 跟投人认证
	 * @param checkKey
	 * @param userId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("certificationinvestor")
	@RequireLogin
	public ModelAndView certificationinvestor() 
	{
		ModelAndView view= new ModelAndView("wapView");
		view.addObject("currenttitle", "跟投人认证");
		view.addObject("viewPath", "me/certificationinvestor");
		Long idLong=UserContext.getCurrentContext(request).getUserInfo().getId();
		Member member=memberService.getById(idLong);
		boolean isHas=memberApplyService.IsHasApply(idLong);
		view.addObject("member", member);
		view.addObject("isHas", isHas);
		return view;
	}	/**
	 * 提示跳转页
	 * @param checkKey
	 * @param userId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("transfer")
	@RequireLogin
	public ModelAndView transfer(int type) 
	{
		ModelAndView view= new ModelAndView("wapView");
		view.addObject("currenttitle", "提示");
		view.addObject("viewPath", "me/transfer");
		String tip= "";
		String url="";
		String button="";
		String basePath=PropertyUtils.getProperty("java.wap.url");
		switch (type) {
		case 1:{tip="您尚未实名认证,请先进行实名认证";url=basePath+"/me/certification";button="去认证";}break;//实名认证
		case 2:{tip="您当前有未审核通过的认证申请,请等待审核";url=basePath+"/me/userinfo";button="返回";}break;
//		case 3:{tip="";url=basePath+"/aa/aa";button="去认证";}break;
//		case 4:{tip="";url=basePath+"/aa/aa";button="去认证";}break;
//		case 5:{tip="";url=basePath+"/aa/aa";button="去认证";}break;
//		case 6:{tip="";url=basePath+"/aa/aa";button="去认证";}break;
//		case 7:{tip="";url=basePath+"/aa/aa";button="去认证";}break;
//		case 8:{tip="";url=basePath+"/aa/aa";button="去认证";}break;
//		case 9:{tip="";url=basePath+"/aa/aa";button="去认证";}break;
//		case 10:{tip="";url=basePath+"/aa/aa";button="去认证";}break;
		default:
			break;
		}
		view.addObject("tip", tip);
		view.addObject("url", url);
		view.addObject("button", button);
		return view;
	}
	/**
	 * 投资的项目
	 * @return
	 */
	@RequestMapping("projectinvest")
	@RequireLogin
	public  ModelAndView projectinvest()
	{
		ModelAndView view= new ModelAndView("wapView");
		view.addObject("currenttitle", "投资的项目");
		view.addObject("viewPath", "me/projectinvest");
		ProSubInvestReq req=new ProSubInvestReq();
		req.setMemberid(UserContext.getCurrentContext(request).getUserInfo().getId());
		List<ProjectSubscriptionDM> list=projectSubscriptionService.getListMore(req);
		view.addObject("list", list);
		return view;
	}
	/**
	 * 关注的项目
	 * @return
	 */
	@RequestMapping("projectconcern")
	@RequireLogin
	public  ModelAndView projectconcern()
	{
		ModelAndView view= new ModelAndView("wapView");
		view.addObject("currenttitle", "关注的项目");
		view.addObject("viewPath", "me/projectconcern");
		ProFavoriteReq req=new ProFavoriteReq();
		req.setMemberid(UserContext.getCurrentContext(request).getUserInfo().getId());
		List<ProjectFavoriteDM> list=projectFavoriteService.getListMore(req);
		view.addObject("list", list);
		return view;
	}
}
