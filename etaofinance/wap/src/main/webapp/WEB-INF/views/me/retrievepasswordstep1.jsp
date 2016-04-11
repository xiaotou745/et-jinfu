<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.entity.PublicProvinceCity" %>
<%@page import="java.util.List" %>
<%
	//网站跟地址
	String basePath = PropertyUtils.getProperty("java.wap.url");
	//静态资源跟地址
	String staticResPath = PropertyUtils.getProperty("staticResourceUrl");
%>
  <link rel="stylesheet" href="<%=staticResPath%>/etao-crowdfunding/css/p/me/retrieve-password-step1.css">
    <div class="g-wrap">
        <div class="g-views">
            
	<section class="container">
		<div class="title">找回密码</div>
	</section>
	<section class="container">
	<div class="validation-message"></div>
		<div class="form-input">
			<input type="tel" placeholder="请输入手机号" data-role="telephone" maxlength="11" />
		</div>
		<div class="form-input-vertify">
			<input type="text" placeholder="请输入验证码" data-role="vertifyCode"/>
			<button class="vertify-code"><img src="<%=basePath%>/common/code?type=1" /></button>
		</div>
	</section>
	<section class="container top-gap">
		<div class="btn-toggle">
			<button disabled>下一步</button>
		</div>
	</section>

        </div>
    </div>
    <!-- error: point:pagejs is not defined; modname islayout/normal-flexible -->
    <script src="<%=staticResPath%>/etao-crowdfunding/js/p/me/retrieve-password-step1.js"></script>
