<%@page import="com.etaofinance.entity.Member"%>
<%@page import="com.etaofinance.entity.MemberOther"%>
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

<link rel="stylesheet" href="<%=staticResPath%>/etao-crowdfunding/css/p/me/settings-username.css">
    <div class="g-wrap">
        <div class="g-views">
            
	<form data-role="form">
	<section class="container top-gap">
		<p>新用户名</p>
		<div class="form-input">
			<input type="text" placeholder="请输入6~20个字符" name="username" data-role="newUserName"/>
		</div>
	</section>
	<section class="container top-gap">
		<div class="btn-toggle">
			<button disabled data-role="submit">确定</button>
		</div>
	</section>
	</form>

        </div>
    </div>
    <!-- error: point:pagejs is not defined; modname islayout/normal-flexible -->
    <script src="<%=staticResPath%>/etao-crowdfunding/js/p/me/settings-username.js"></script>