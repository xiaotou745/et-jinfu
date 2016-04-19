package com.etaofinance.wap.controllor;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.print.DocFlavor.STRING;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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
import com.etaofinance.api.service.inter.IMessageService;
import com.etaofinance.api.service.inter.IProjectFavoriteService;
import com.etaofinance.api.service.inter.IProjectService;
import com.etaofinance.api.service.inter.IProjectSubscriptionService;
import com.etaofinance.api.service.inter.IWithdrawformService;
import com.etaofinance.core.consts.RedissCacheKey;
import com.etaofinance.core.util.CookieUtils;
import com.etaofinance.core.util.PropertyUtils;
import com.etaofinance.entity.BankCard;
import com.etaofinance.entity.Member;
import com.etaofinance.entity.MemberOther;
import com.etaofinance.entity.Message;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.Project;
import com.etaofinance.entity.domain.BalanceRecordDM;
import com.etaofinance.entity.domain.ProjectFavoriteDM;
import com.etaofinance.entity.domain.ProjectSubscriptionDM;
import com.etaofinance.entity.req.ProFavoriteReq;
import com.etaofinance.entity.req.ProLaunchReq;
import com.etaofinance.entity.req.ProSubInvestReq;
import com.etaofinance.entity.req.PublicMemberReq;
import com.etaofinance.wap.common.LoginUtil;
import com.etaofinance.wap.common.RequireLogin;
import com.etaofinance.wap.common.UserContext;
import com.wordnik.swagger.annotations.ApiOperation;

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
	@Autowired
	IProjectService projectService;

	@Autowired
	IMessageService  messageService;
	
	@Autowired
	IBankCardService   bankCardService;
	
	@Autowired
	IWithdrawformService    withdrawformService;
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
		if(UserContext.getCurrentContext(request).getUserInfo()!=null)
		{
			
			ModelAndView view2 = new ModelAndView(new RedirectView(PropertyUtils.getProperty("java.wap.url")+"/home/index"));
			return view2;
		}
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
		String levelStr="未认证",levelStr2="未认证";
		if(member.getLevel()==2)
		{
			levelStr="已认证";
		}
		if(member.getLevel()==3)
		{
			levelStr2="已认证";
		}				
		boolean bt1= memberApplyService.IsHasTZApply(member.getId());
		boolean bt2=memberApplyService.IsHasLTApply(member.getId());
		if(bt1)
		{
			levelStr="认证中";
		}
		if(bt2)
		{
			levelStr2="认证中";
		}
		view.addObject("levelStr",levelStr);
		view.addObject("levelStr2",levelStr2);
	
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
	}
	
	/**
	 * 领投人认证
	 * @param checkKey
	 * @param userId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("certificationleadinvestor")
	@RequireLogin
	public ModelAndView certificationleadinvestor() 
	{
		ModelAndView view= new ModelAndView("wapView");
		view.addObject("currenttitle", "领投人认证");
		view.addObject("viewPath", "me/certificationleadinvestor");
		Long memberId=UserContext.getCurrentContext(request).getUserInfo().getId();
		Member member=memberService.getById(memberId);
		
		//未设置实名认证 跳转实名认证
		if(member.getLevel().equals("0"))
		{
			String basePath = PropertyUtils.getProperty("java.wap.url");
			basePath+="/me/transfer";			
			ModelAndView view2= new ModelAndView(new RedirectView(basePath));
			view2.addObject("type", "1");
			return  view2;	
		}	
		//已是领投人或跟投 人 跳转 
		if(member.getLevel().equals("2") || member.getLevel().equals("3"))
		{
			String basePath = PropertyUtils.getProperty("java.wap.url");
			basePath+="/me/usercenter";			
			ModelAndView view2= new ModelAndView(new RedirectView(basePath));		
			return  view2;	
		}
		
		//存在未审核的领投人信息或跟投人信息 跳转
		boolean isHas=memberApplyService.IsHasApply(memberId);
		if(isHas)
		{
			String basePath = PropertyUtils.getProperty("java.wap.url");
			basePath+="/me/transfer";			
			ModelAndView view2= new ModelAndView(new RedirectView(basePath));
			view2.addObject("type", "2");
			return  view2;
		}
		return view;
	}
	
	
	/**
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
		case 3:{tip="您尚未设置支付密码,请先设置支付密码";url=basePath+"/pay/setpaypasswordstep1";button="去设置";}break;
		case 4:{tip="您当前未绑定银行卡，请先绑定";url=basePath+"/me/addbankcard";button="去绑定";}break;
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
	/**
	 * 发起的项目
	 * @return
	 */
	@RequestMapping("projectlanuch")
	@RequireLogin
	public  ModelAndView projectlanuch()
	{
		ModelAndView view= new ModelAndView("wapView");
		view.addObject("currenttitle", "发起的项目");
		view.addObject("viewPath", "me/projectlanuch");
		Long memberid=UserContext.getCurrentContext(request).getUserInfo().getId();	
		ProLaunchReq req=new ProLaunchReq();
		req.setMemberid(memberid);	
		List<Project> list= projectService.getListMore(req);
		view.addObject("list", list);
		return view;
	}
	
	/**
	 * 账户与安全
	 * @param checkKey
	 * @param userId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("accountsecurity")
	@RequireLogin
	public ModelAndView accountsecurity() 
	{
		ModelAndView view= new ModelAndView("wapView");
		view.addObject("currenttitle", "账户与安全");
		view.addObject("viewPath", "me/accountsecurity");
		Member member=UserContext.getCurrentContext(request).getUserInfo();
		member=memberService.getById(member.getId());
		view.addObject("member",member);
		return view;
	}
	
	/**
	 * 消息列表
	 * @param checkKey
	 * @param userId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("messagelist")
	@RequireLogin
	public ModelAndView messagelist() 
	{
		ModelAndView view= new ModelAndView("wapView");
		view.addObject("currenttitle", "消息列表");
		view.addObject("viewPath", "me/messagelist");
		Member member=UserContext.getCurrentContext(request).getUserInfo();
		List<Message> list=messageService.getList(member.getId());
		view.addObject("list",list);
		return view;
	}
	
	/**
	 * 消息列表
	 * @param checkKey
	 * @param userId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("messagedetail")
	@RequireLogin
	public ModelAndView messagedetail(Long id) 
	{
		ModelAndView view= new ModelAndView("wapView");
		view.addObject("currenttitle", "消息详情");
		view.addObject("viewPath", "me/messagedetail");
		Message m=messageService.getByid(id);
		messageService.readMsg(m);
		view.addObject("message",m);
		return view;
	}
	/**
	 * 修改手机号
	 * @param checkKey
	 * @param userId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("modifytelephone")
	@RequireLogin
	public ModelAndView modifytelephone() 
	{
		ModelAndView view= new ModelAndView("wapView");
		view.addObject("currenttitle", "修改手机号");
		view.addObject("viewPath", "me/modifytelephone");
		MemberOther member=memberOtherService.getByMemberId(UserContext.getCurrentContext(request).getUserInfo().getId());
		int flag=0;
		if(member.getPaypassword()!=null&&!member.getPaypassword().equals(""))
		{
			flag=1;
		}
		view.addObject("flag", flag);
		return view;
	}
	/**
	 * 修改手机号BY
	 * 短信验证码
	 * @param checkKey
	 * @param userId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("messagecertification")
	@RequireLogin
	public ModelAndView messagecertification() 
	{
		ModelAndView view= new ModelAndView("wapView");
		view.addObject("currenttitle", "短信验证");
		view.addObject("viewPath", "me/messagecertification");
		Member member=memberService.getById(UserContext.getCurrentContext(request).getUserInfo().getId());
		view.addObject("member", member);
		return view;
	}
	/**
	 * 修改手机号By支付密码
	 * @param checkKey
	 * @param userId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("inputpaypassword")
	@RequireLogin
	public ModelAndView inputpaypassword() 
	{
		ModelAndView view= new ModelAndView("wapView");
		view.addObject("currenttitle", "输入支付密码");
		view.addObject("viewPath", "me/inputpaypassword");
		return view;
	}
	/**
	 * 绑定新手机号
	 * @param checkKey
	 * @param userId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("bindtelephone")
	@RequireLogin
	public ModelAndView bindtelephone(String checkKey) 
	{
		String value=redisService.get(String.format(RedissCacheKey.JF_Member_ChangePhoneOne, checkKey), String.class);
		ModelAndView view= new ModelAndView("wapView");
		view.addObject("currenttitle", "绑定新手机号");
		view.addObject("viewPath", "me/bindtelephone");
		view.addObject("checkKey", checkKey);
		if(value==null||!value.equals(checkKey))
		{	//一次性校验码错误
		
			String basePath=PropertyUtils.getProperty("java.wap.url")+"/me/modifytelephone";		
			ModelAndView view2= new ModelAndView(new RedirectView(basePath));
			return  view2;
		}
		return view;
	}

	/******************************************资金账户begin*/
	
	/**
	 * 充值
	 * @param checkKey
	 * @param userId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("recharge")
	@RequireLogin
	public ModelAndView recharge() 
	{
		ModelAndView view= new ModelAndView("wapView");
		view.addObject("currenttitle", "充值");
		view.addObject("viewPath", "me/recharge");
		
		long memberId= UserContext.getCurrentContext(request).getUserInfo().getId();
		Member member=memberService.getById(memberId);
		MemberOther memberOther=memberOtherService.getByMemberId(memberId);
		if(memberOther.getPaypassword()==null || memberOther.getPaypassword().equals(""))
		{			
			String basePath = PropertyUtils.getProperty("java.wap.url");
			basePath+="/me/transfer";		
			ModelAndView view2= new ModelAndView(new RedirectView(basePath));
			view2.addObject("type", "3");
			return  view2;			
		}	
		return view;
	}	


	/**
	 * 提现
	 * @param checkKey
	 * @param userId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("withdraw")
	@RequireLogin
	public ModelAndView withdraw() 
	{
		ModelAndView view= new ModelAndView("wapView");
		view.addObject("currenttitle", "提现");
		view.addObject("viewPath", "me/withdraw");
		
		long memberId= UserContext.getCurrentContext(request).getUserInfo().getId();
		Member member=memberService.getById(memberId);
		MemberOther memberOther=memberOtherService.getByMemberId(memberId);
		if(memberOther.getPaypassword()==null || memberOther.getPaypassword().equals(""))
		{						
			String basePath = PropertyUtils.getProperty("java.wap.url");
			basePath+="/me/transfer";	
			ModelAndView view2= new ModelAndView(new RedirectView(basePath));
			view2.addObject("type", "3");
			return  view2;			
		}	
		double allowwithdrawprice=memberOther.getAllowwithdrawprice();		
		double  WithdrawPendingAmount= withdrawformService.GetWithdrawPendingAmountByMbId(memberId);
		view.addObject("allowwithdrawprice",allowwithdrawprice-WithdrawPendingAmount);
		return view;
	}
	
	
	/**我的银行卡
	 * 
	 * @param checkKey
	 * @param userId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("bankcardlist")
	@RequireLogin
	public ModelAndView bankcardlist() 
	{
		ModelAndView view= new ModelAndView("wapView");
		view.addObject("currenttitle", "我的银行卡");
		view.addObject("viewPath", "me/bankcardlist");
		
		long memberId= UserContext.getCurrentContext(request).getUserInfo().getId();
		Member member=memberService.getById(memberId);
		
		//未设置支付密码 跳转
		MemberOther memberOther=memberOtherService.getByMemberId(memberId);
		if(memberOther.getPaypassword()==null || memberOther.getPaypassword().equals(""))
		{						
			String basePath = PropertyUtils.getProperty("java.wap.url");
			basePath+="/me/transfer";			
			ModelAndView view2= new ModelAndView(new RedirectView(basePath));
			view2.addObject("type", "3");
			return  view2;		
		}	
		
		//未绑定银行卡 跳转
		List<BankCard> bandCardList=bankCardService.getListByMemberId(memberId);
		if(bandCardList==null || bandCardList.size()==0)
		{
			String basePath = PropertyUtils.getProperty("java.wap.url");
			basePath+="/me/transfer";		
			ModelAndView view2= new ModelAndView(new RedirectView(basePath));
			view2.addObject("type", "4");
			return  view2;		
		}		
	    String bankname=bandCardList.get(0).getBankname();
	    String cardno=bandCardList.get(0).getCardno();
	    int len=cardno.length()-4;
	    String cardnoStr="**** **** **** "+cardno.substring(len);  
		view.addObject("bankname", bankname);	
		view.addObject("cardnoStr", cardnoStr);

		return view;			
	}
	
	/**绑定银行卡
	 * 
	 * @param checkKey
	 * @param userId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("addbankcard")
	@RequireLogin
	public ModelAndView addbankcard() 
	{
		ModelAndView view= new ModelAndView("wapView");
		view.addObject("currenttitle", "绑定银行卡");
		view.addObject("viewPath", "me/addbankcard");
		
		long memberId= UserContext.getCurrentContext(request).getUserInfo().getId();
		Member member=memberService.getById(memberId);
		MemberOther memberOther=memberOtherService.getByMemberId(memberId);
		if(memberOther.getPaypassword()==null || memberOther.getPaypassword().equals(""))
		{						
			String basePath = "";
			basePath+="/pay/setpaypasswordstep1";		
			ModelAndView view2= new ModelAndView(new RedirectView(basePath));
			view2.addObject("phone", member.getPhoneno());
			return  view2;		
		}	

		view.addObject("truename", member.getTruename());	
		return view;
	}	
	
	/**
	 * 支付管理
	 * @param checkKey
	 * @param userId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("paymanagement")
	@RequireLogin
	public ModelAndView paymanagement() 
	{
		ModelAndView view= new ModelAndView("wapView");
		view.addObject("currenttitle", "提现");
		view.addObject("viewPath", "me/paymanagement");
		
		long memberId= UserContext.getCurrentContext(request).getUserInfo().getId();
		Member member=memberService.getById(memberId);
		MemberOther memberOther=memberOtherService.getByMemberId(memberId);
		if(memberOther.getPaypassword()==null || memberOther.getPaypassword().equals(""))
		{						
			String basePath = PropertyUtils.getProperty("java.wap.url");
			basePath+="/me/transfer";	
			ModelAndView view2= new ModelAndView(new RedirectView(basePath));
			view2.addObject("type", "3");
			return  view2;			
		}	

		return view;
	}
	
	/******************************************资金账户end*/
	/**修改邮箱
	 * 
	 * @param checkKey
	 * @param userId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("modifyemail")
	@RequireLogin
	public ModelAndView modifyemail() 
	{
		ModelAndView view= new ModelAndView("wapView");
		view.addObject("currenttitle", "修改邮箱");
		view.addObject("viewPath", "me/modifyemail");
		return view;
	}
	/**修改邮箱
	 * 
	 * @param checkKey
	 * @param userId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("modifypassword")
	@RequireLogin
	public ModelAndView modifypassword() 
	{
		ModelAndView view= new ModelAndView("wapView");
		view.addObject("currenttitle", "修改密码");
		view.addObject("viewPath", "me/modifypassword");
		return view;
	}	

}
