<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.entity.PublicProvinceCity" %>
<%@page import="java.util.List" %>
<%
	//网站跟地址
	String basePath = PropertyUtils.getProperty("java.wap.url");
	//静态资源跟地址
	String staticResPath = PropertyUtils.getProperty("staticResourceUrl");
	String reUrl=(String)request.getAttribute("reUrl");
%>
<input type="hidden" id="reurl" value="<%=reUrl%>"/>
<link rel="stylesheet" href="<%=staticResPath%>/etao-crowdfunding/css/p/me/login.css">
    <div class="g-wrap">
        <div class="g-views"> 
	<header>
    <div class="title">
    </div>
</header>

	<section class="container">
		<div class="validation-message"></div>
		
		<div class="form-input">
			<input data-role="username" type="text" placeholder="请输入手机号/邮箱/用户名" />
			<i class="m-icon icon-close hide"></i>
		</div>
		<div class="form-input">
			<input data-role="password" type="password" placeholder="6~20位字符" maxlength="20"/>
			<input data-role="hidden-password" type="hidden"/>
			<i class="m-icon icon-eye"></i>
		</div>
	</section>
	<section class="container">
		<div class="tip">
			<i class="m-icon icon-approval"></i>
		<span>一个月内免登录</span>
		</div>
	</section>
	<section class="container">
		<div class="btn-toggle">
			<button disabled>登录</button>
		</div>
	</section>
	<section class="container">
		<div class="link">
			<a href="<%=basePath%>/me/register"><span class="txt-highlight">快速注册</span></a>
			<span class="separator"></span>
			<a href="<%=basePath%>/me/retrievepasswordstep1"><span class="txt-normal">忘记密码</span></a>
		</div>
	</section>

        </div>
    </div>
    <!-- error: point:pagejs is not defined; modname islayout/normal-flexible -->
    <script src="<%=staticResPath%>/etao-crowdfunding/js/p/me/login.js"></script>
