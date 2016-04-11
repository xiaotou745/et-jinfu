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
	Long userID=(Long)request.getAttribute("userId");
%>
<input type="hidden" id="checkKey" value="<%=checkKey%>"/>
<input type="hidden" id="userID" value="<%=userID%>"/>
<link rel="stylesheet" href="<%=staticResPath%>/etao-crowdfunding/css/p/me/retrieve-password-step3.css">
    <div class="g-wrap">
        <div class="g-views">
            
	<section class="container">
		<div class="title">
			找回密码
		</div>
	</section>
	<section class="container">
		<div class="validation-message"></div>
		<div class="form-input">
			<input type="password" placeholder="请输入6~20位新密码" data-role="newPassword" maxlength="20" />
		</div>
		<div class="form-input">
			<input type="password" placeholder="确认新密码" data-role="confirmPassword" maxlength="20">
		</div>
	</section>
	<section class="container top-gap">
		<div class="btn-toggle">
			<button disabled>完成</button>
		</div>
	</section>

        </div>
    </div>
    <!-- error: point:pagejs is not defined; modname islayout/normal-flexible -->
    <script src="<%=staticResPath%>/etao-crowdfunding/js/p/me/retrieve-password-step3.js"></script>
