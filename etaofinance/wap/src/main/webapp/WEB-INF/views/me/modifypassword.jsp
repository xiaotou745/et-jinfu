<%@page import="com.etaofinance.core.util.ParseHelper"%>
<%@page import="java.util.ArrayList"%>
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
<link rel="stylesheet" href="<%=staticResPath%>/etao-crowdfunding/css/p/me/modify-password.css">
    <div class="g-wrap">
        <div class="g-views">
            
    <form action="" data-role="form">
    	<section class="container bg top-gap">
    		<div class="label-input">
    			<label>原密码</label>
    			<input type="password" name="oldPwd" data-role="oldpassword" placeholder="请输入原密码">
    		</div>
    	</section>
    	<section class="container bg top-gap">
    		<div class="label-input">
    			<label>新密码</label>
    			<input type="password" placeholder="请输入新密码" name="newPwd" data-role="password">
    		</div>
    		<div class="label-input top-line">
    			<label>确认密码</label>
    			<input type="password" placeholder="请再次输入原密码" name="password" data-role="repassword">
    		</div>
    	</section>
    	<section class="container top-gap">
    		<div class="btn-toggle">
    			<button data-role="submit" disabled>确定修改</button>
    		</div>
    	</section>
    </form>

        </div>
    </div>
    <!-- error: point:pagejs is not defined; modname islayout/normal-flexible -->
    <script src="<%=staticResPath%>/etao-crowdfunding/js/p/me/modify-password.js"></script>
