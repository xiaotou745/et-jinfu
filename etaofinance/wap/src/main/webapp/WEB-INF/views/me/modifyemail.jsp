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


<link rel="stylesheet" href="<%=staticResPath%>/etao-crowdfunding/css/p/me/modify-email.css">
    <div class="g-wrap">
        <div class="g-views">
            
    <form action="" data-role="form">
    	<section class="container top-gap">
    		<div class="form-input">
    			<input type="text" placeholder="请填写邮箱地址" name="email" data-role="email" />
    		</div>
    	</section>
    	<section class="container top-gap">
    		<div class="btn-toggle">
    			<button data-role="submit" disabled>确定</button>
    		</div>
    	</section>
    </form>

        </div>
    </div>
    <!-- error: point:pagejs is not defined; modname islayout/normal-flexible -->
    <script src="<%=staticResPath%>/etao-crowdfunding/js/p/me/modify-email.js"></script>

