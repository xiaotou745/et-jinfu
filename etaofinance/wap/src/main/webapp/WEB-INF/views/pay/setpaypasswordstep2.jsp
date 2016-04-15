<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.entity.PublicProvinceCity" %>
<%@page import="java.util.List" %>
<%
	//网站跟地址
	String basePath = PropertyUtils.getProperty("java.wap.url");
	//静态资源跟地址
	String staticResPath = PropertyUtils.getProperty("staticResourceUrl");
	
	String checkKey= (String)request.getAttribute("checkKey");
%>

  <link rel="stylesheet" href="<%=staticResPath%>/etao-crowdfunding/css/p/pay/set-paypassword-step2.css">
  <div class="g-wrap">
        <div class="g-views">
            
    <div class="modify-paypassword-wrap">
        <div class="title">
            请输入新的支付密码
        </div>
        <div class="pay_pwd_wrap ">
            <div class="point"></div>
            <input type="tel" class="pay_pwd" value="" maxlength="6" />
        </div>
        <div class="err_tip">
            两次输入密码不一致
        </div>
        <div class="test"></div>
    </div>

        </div>
    </div>     
      <input type="hidden" id="checkKey" value="<%=checkKey%>"/>
    <!-- error: point:pagejs is not defined; modname islayout/normal-flexible -->
 <script src="<%=staticResPath%>/etao-crowdfunding/js/p/pay/set-paypassword-step2.js"></script>    
    