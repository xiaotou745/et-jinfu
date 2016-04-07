<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.entity.PublicProvinceCity" %>
<%@page import="java.util.List" %>
<%
	//网站跟地址
	String basePath = PropertyUtils.getProperty("java.wap.url");
	//静态资源跟地址
	String staticResPath = PropertyUtils.getProperty("staticResourceUrl");
	String phone=(String)request.getAttribute("phone");
%>

<!DOCTYPE html>
<html>
<head>
    <title>找回密码</title>
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
    <link rel="stylesheet" href="<%=staticResPath%>/etao-crowdfunding/css/p/me/retrieve-password-step2.css">
    <script src="<%=staticResPath%>/etao-crowdfunding/js/mo.1.0.0.js"></script>
</head>

<body>
    <div class="g-wrap">
        <div class="g-views">
            
    <section class="container">
        <div class="title">
            <div class="des">
                验证码已发送到您的手机
            </div>
            <div class="phone">
                <%=phone%>
            </div>
        </div>
    </section>
    <section class="container">
    	<!-- <div class="nick-name">昵称：<span>我爱人民币</span></div> -->
        <div class="validation-message"></div>
    	<div class="form-input-vertify">
			<input type="text" placeholder="请输入短信验证码" data-role="vertifyCode"/>
			<button class="radius" data-role="getVertifyCode" disabled>重新获取(60s)</button>
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
    <script src="<%=staticResPath%>/etao-crowdfunding/js/p/me/retrieve-password-step2.js"></script>
</body>

</html>

