package com.etaofinance.licaiwap.common;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.etaofinance.core.util.PropertyUtils;

public class AuthInteceptor extends HandlerInterceptorAdapter {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String basePath =PropertyUtils.getProperty("java.wap.url");
		if (handler instanceof HandlerMethod) {
			// 判断是否登录
//			boolean isLogin = LoginUtil.checkIsLogin(request,response);
//			if (!isLogin&& 
//				!request.getServletPath().equals("/account/login") &&
//				!request.getServletPath().equals("/account/code"))
//				{
//				if(isAjax(request)){
//					throw new AjaxNotLoginRunTimeException("请重新登录");
//				}else {
//					response.sendRedirect(basePath + "/");
//					return false;
//				}
//			}
//			if (isLogin) {//用户登录后，检查用户是否有当前页面的权限，没有则跳转到订单列表页
//				//用户登录后，将当前用户id和名称保存起来，用于记录操作日志
//				request.setAttribute("userID", UserContext.getCurrentContext(request).getId());
//				request.setAttribute("userName", UserContext.getCurrentContext(request).getLoginName());
//
//				if (needCheckPageAuth(request)) {
//					response.sendRedirect(basePath+"/ordermanage/auditorder");
//					return false;
//				}
//			}
		}
		
		return true;
	}
	private boolean isAjax(HttpServletRequest request){
		String requestType = request.getHeader("X-Requested-With");
		if (requestType != null && requestType.equals("XMLHttpRequest")) {
			return true;
		}
		return false;
	}
	private boolean needCheckPageAuth(HttpServletRequest request){
		//如果当前请求是从ajax中来的，或当前请求的页面为订单列表或验证码页面，则不进行权限判断
		if (!isAjax(request)&&
			!request.getServletPath().equals("/account/code")&&
			!request.getServletPath().equals("/account/logoff")) {
			return false;
			
		}
		return false;
	}
}
