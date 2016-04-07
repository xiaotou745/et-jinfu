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
<!DOCTYPE html>
<html>
<head>
  	<script>
	var apiBasePath="<%=basePath%>";
	</script>
    <title>登录</title>
    <meta name="description" content="" />
    <meta name="keywords" content="" />
    <meta charset="UTF-8">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <meta name="format-detection" content="telephone=no, email=no" />
    <meta name="360-fullscreen" content="true" />
    <!-- QQ强制竖屏 -->
    <meta name="x5-orientation" content="portrait">
    <!-- QQ强制全屏 -->
    <meta name="x5-fullscreen" content="true">
    <!-- QQ应用模式 -->
    <meta name="x5-page-mode" content="app">
    <!-- UC强制全屏 -->
    <meta name="full-screen" content="yes">
    <!-- UC应用模式 -->
    <meta name="browsermode" content="application">
    <!-- uc强制竖屏 -->
    <meta name="screen-orientation" content="portrait">
    <script src="<%=staticResPath%>/etao-crowdfunding/js/flexible/flexible_css.debug.js"></script>
    <script src="<%=staticResPath%>/etao-crowdfunding/js/flexible/flexible.debug.js"></script>
    <link rel="stylesheet" href="<%=staticResPath%>/etao-crowdfunding/css/p/me/login.css">
    <script src="<%=staticResPath%>/etao-crowdfunding/js/mo.1.0.0.js"></script>
</head>

<body>
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
			<span class="txt-highlight">快速注册</span>
			<span class="separator"></span>
			<span class="txt-normal">忘记密码</span>
		</div>
	</section>

        </div>
    </div>
    <!-- error: point:pagejs is not defined; modname islayout/normal-flexible -->
    <script src="<%=staticResPath%>/etao-crowdfunding/js/p/me/login.js"></script>
</body>

</html>
