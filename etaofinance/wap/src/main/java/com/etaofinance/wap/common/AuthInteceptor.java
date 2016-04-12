package com.etaofinance.wap.common;

import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.etaofinance.core.util.JsonUtil;
import com.etaofinance.core.util.PropertyUtils;
import com.etaofinance.entity.common.HttpResultModel;
/**
 * 权限拦截器
 * @author ofmyi_000
 *
 */
public class AuthInteceptor extends HandlerInterceptorAdapter {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String basePath =PropertyUtils.getProperty("java.wap.url");
		String IsOpenSwagger=PropertyUtils.getProperty("IsOpenSwagger");
		if(request.getServletPath().equals("/common/swagger")&&IsOpenSwagger.equals("0"))
		{//验证是否开启Swagger  1 开启 0未开启 不允许访问
			response.sendRedirect(basePath);
			return false;
		}
		if (handler instanceof HandlerMethod) {
			HandlerMethod myHandlerMethod = (HandlerMethod) handler;
	        Object bean = myHandlerMethod.getBean();
	        Method method= myHandlerMethod.getMethod();
	        Annotation classAnnotation = bean.getClass().getAnnotation(RequireLogin.class);//类上有该标记
	        Annotation methodAnnotation=method.getAnnotation(RequireLogin.class);//方法上有该标记
	        Annotation methodNologinAnnotation=method.getAnnotation(NoRequireLogin.class);//
	        if((classAnnotation!=null&&methodNologinAnnotation==null)
	        		||(classAnnotation==null&&methodAnnotation!=null))
	        {
	        	boolean isLogin = LoginUtil.checkIsLogin(request,response);
	        	if(isLogin)
	        		return true;
	        	else{//未登录
	        		if(isAjax(request)){
	        			//Ajax请求返回JSON
	        			HttpResultModel<Object> rep=new HttpResultModel<Object>();
	        			rep.setCode(-1);
	        			rep.setMsg("请登录后操作!");
	        			String data = JsonUtil.obj2string(rep);
	        	        response.setHeader("content-type", "text/html;charset=UTF-8");
	        	        OutputStream out = response.getOutputStream();
	        	        out.write(data.getBytes("UTF-8"));
	        	        return false;
	        		}
	        		response.sendRedirect(basePath+"/me/login");
	        		return false;
	        		
	        	}//IF LOGIN END
	        }//if Annotation end
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
}
