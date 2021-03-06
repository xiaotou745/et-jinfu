package com.etaofinance.admin.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.etaofinance.admin.common.LoginUtil;
import com.etaofinance.admin.common.UserContext;
import com.etaofinance.api.common.LoginHelper;
import com.etaofinance.api.redis.RedisService;
import com.etaofinance.api.service.inter.IAccountAuthService;
import com.etaofinance.api.service.inter.IAccountInfoService;
import com.etaofinance.api.service.inter.IAdminOptLogService;
import com.etaofinance.api.service.inter.IMenuInfoService;
import com.etaofinance.api.service.inter.IRoleInfoService;
import com.etaofinance.core.security.AES;
import com.etaofinance.core.security.MD5Util;
import com.etaofinance.core.util.CookieUtils;
import com.etaofinance.core.util.JsonUtil;
import com.etaofinance.core.util.PropertyUtils;
import com.etaofinance.entity.AccountInfo;
import com.etaofinance.entity.AdminOptLog;
import com.etaofinance.entity.RoleInfo;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.SimpleUserInfoModel;
import com.etaofinance.entity.req.PagedAccountInfoReq;
import com.etaofinance.entity.req.UpdatePwdReq;

@Controller
@RequestMapping("account")
public class AccountController {
	
	@Autowired
	private RedisService redisService;
	@Autowired
	private IAccountInfoService accountInfoService;
	@Autowired
	private IAccountAuthService accountAuthService;
	@Autowired
	private IMenuInfoService menuService;
	@Autowired
	private IAdminOptLogService adminOptLogService;
	
	@Autowired
	HttpServletRequest request;
	@Autowired
	private IRoleInfoService authorityRoleService;
	
	
	
	
	
	@RequestMapping("list")
	public ModelAndView list() {
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "用户设置");
		view.addObject("currenttitle", "用户管理");
		view.addObject("viewPath", "account/list");
		List<RoleInfo> datalist=authorityRoleService.selectList();
		view.addObject("roleData", datalist);
		return view;
	}

	
	@RequestMapping("listdo")
	public ModelAndView list(PagedAccountInfoReq req) {
		PagedResponse<AccountInfo> resp = accountInfoService.queryAccount(req);
		ModelAndView view = new ModelAndView();
		view.addObject("viewPath", "account/listdo");
		view.addObject("listData", resp);
		return view;
	}
	
	@RequestMapping("code")
	public ModelAndView code(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("account/code");
		return mv;
	}
	
	
	@RequestMapping("adduser")
	@ResponseBody
	public int addUser(HttpServletRequest request,AccountInfo account) {
		if (account==null||
				account.getLoginname()==null||
				account.getLoginname().isEmpty()||
				account.getTruename()==null||
				account.getTruename().isEmpty()
				) {
			return -1;
		}
		UserContext context = UserContext.getCurrentContext(request);
		account.setCreatename(context.getUserName());
		account.setOptname(context.getUserName());
		account.setRoleid(0);
		String password = MD5Util.MD5(account.getPassword());
		account.setPassword(password);
		return accountInfoService.insert(account);
	}

	
	@RequestMapping("updateuser")
	@ResponseBody
	public int updateUser(HttpServletRequest request,AccountInfo account) {
		if (account.getPassword()!=null&&!account.getPassword().isEmpty()) {
			String password = MD5Util.MD5(account.getPassword());
			account.setPassword(password);
		}
		UserContext context = UserContext.getCurrentContext(request);
		account.setCreatename(context.getUserName());
		account.setOptname(context.getUserName());
		return accountInfoService.update(account);
	}
	
	@RequestMapping("getuserinfo")
	@ResponseBody
	public AccountInfo getUserInfo(HttpServletRequest request,int userId) {
		return accountInfoService.getByID(userId);
	}
	
	
	
	@RequestMapping(value = "login", method = { RequestMethod.POST })
	public void login(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String username, @RequestParam String password, @RequestParam String code,
			 Integer rememberMe) throws ServletException, IOException {
		String basePath = PropertyUtils.getProperty("java.admin.url");

		String sessionCode = LoginHelper.getAuthCode(request,LoginUtil.ADMIN_JSESSIONID);
		//一次性验证码,防止暴力破解
		LoginHelper.removeAuthCodeCookie(request, response,LoginUtil.ADMIN_JSESSIONID);
		// 如果已登录,直接返回
		boolean isLogin = LoginUtil.checkIsLogin(request,response);
		// 如果已登录,直接返回已登录
		if (isLogin) {
			response.sendRedirect(basePath+"/account/list");
			return;
		}

		String error = "";
		AccountInfo account = null;
		// 验证码不正确
		if (sessionCode == null || !sessionCode.toString().toLowerCase().equals(code.toLowerCase())) {
			error = "验证码不正确";
		}else{
			account = accountInfoService.login(username, password);
			if (account == null) {
				error = "用户名或密码错误";
			}
			else if(account.getIslock()){
				error = "您的账号已经被禁用,请联系管理员";
			}
		}
		if(!error.equals("")){
			request.setAttribute("error", error);
			request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		// 登录成功,写cookie
		int cookieMaxAge = -1;
		// 选择记住我,默认cookie24小时,否则随浏览器的关闭而失效
		boolean isRemeberMe = rememberMe!= null && rememberMe.equals(1);
		if (isRemeberMe) {
			cookieMaxAge = 7 * 60 * 60 * 24;
		}
		account.setPassword(password);//这个是为了给cookie准备的参数
		error = "成功";

		SimpleUserInfoModel loginUser = new SimpleUserInfoModel();
		loginUser.setId(account.getId());
		loginUser.setLoginName(account.getLoginname());
		loginUser.setPassword("");
		loginUser.setRoleId(account.getRoleid());
		loginUser.setUserName(account.getTruename());
		String encyCookie=AES.aesEncrypt(JsonUtil.obj2string(loginUser));
		CookieUtils.setCookie(request,response,LoginUtil.LOGIN_COOKIE_NAME, encyCookie, cookieMaxAge,
				true);
		
		
		
		
		// 记录 登录日志
		
		AdminOptLog adminOptLog = new AdminOptLog();
		
		
		adminOptLog.setRemark("登陆成功");
		adminOptLog.setCreatename(account.getTruename());
		adminOptLog.setAccountid(account.getId());
	
		adminOptLogService.insertSelective(adminOptLog);
		
		response.sendRedirect(basePath+"/account/list");
	}
	
	/**
	 * 注销
	 * 
	 * @author pengyi
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "logoff")
	public void logoff(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 删除登录cookie
		CookieUtils.deleteCookie(request, response,LoginUtil.LOGIN_COOKIE_NAME);
		response.sendRedirect(PropertyUtils.getProperty("java.admin.url") + "/");

	}
	
	
	@RequestMapping(value = "changepwd")
	public ModelAndView changePwd(){
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "管理员");
		view.addObject("currenttitle", "修改密码");
		view.addObject("viewPath", "account/changepwd");
		return view;
	}
	
	
	@RequestMapping(value = "updatepwd")
	@ResponseBody
	public int updatePwd(HttpServletRequest request, UpdatePwdReq req){
		UserContext context = UserContext.getCurrentContext(request);
		req.setUserId(context.getId());
		return accountInfoService.updatePwd(req);
	}
	
	
	@RequestMapping("modifypwdpg")
	public ModelAndView modifyPwdpg() {
		
		ModelAndView view = new ModelAndView("adminView");
		
		view.addObject("subtitle", "设置");
		
		view.addObject("currenttitle", "修改密码");
		
		view.addObject("viewPath", "member/modifypwd");
		
		view.addObject("currentUser",accountInfoService.getByID(UserContext.getCurrentContext(request).getId()));
		
		return view;
	}
}
