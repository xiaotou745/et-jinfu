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
	<link rel="stylesheet" href="<%=staticResPath%>/etao-crowdfunding/css/p/me/register.css">
    <div class="g-wrap">
        <div class="g-views">
            
	<header>
    <div class="title">
    </div>
</header>

	<section class="container">
	<div class="validation-message"></div>
		<div class="form-input">
			<input type="tel" placeholder="请输入手机号" maxlength="11" data-role="telephone"/>
		</div>
		<div class="form-input-vertify">
			<input type="text" placeholder="请输入短信验证码" data-role="code"/>
			<button class="radius" data-role="vertify-code" disabled>获取验证码</button>
		</div>
		<div class="form-input">
			<input type="password" placeholder="请设置6~20位密码" maxlength="20" data-role="password"/>
		</div>
	</section>
	<section class="container">
		<div class="authorize">
			<i class="m-icon icon-approval"></i>
		<span class="agree">阅读并同意</span><span class="highlight">《易淘金服网站服务协议》</span>
		</div>
	</section>
	<section class="container">
		<div class="btn-toggle">
			<button disabled>注册</button>
		</div>
	</section>
	<section class="container">
		<div class="link">
			<span class="txt-normal">已有账号，</span>
			<a href="<%=basePath%>/me/login"><span class="txt-highlight">点此登录</span></a>
		</div>
	</section>
		

        </div>
    </div>
    <!-- error: point:pagejs is not defined; modname islayout/normal-flexible -->
    <script src="<%=staticResPath%>/etao-crowdfunding/js/p/me/register.js"></script>

