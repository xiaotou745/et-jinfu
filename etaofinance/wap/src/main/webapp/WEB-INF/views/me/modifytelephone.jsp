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
	
	int flag=(int)request.getAttribute("flag");
%>

 <link rel="stylesheet" href="<%=staticResPath%>/etao-crowdfunding/css/p/me/modify-telephone.css">
    <div class="g-wrap">
        <div class="g-views">
            
	<section class="container">
		<div class="tips">
			您需要先进行安全验证
		</div>
		<a href="#">
		<div class="text top-line">
			<div class="title">短信验证</div>
			<i class="m-icon icon-arrow-r"></i>
		</div>
		</a>
		<%
		if(flag==1)
		{
			%>
			<a href="#">
			<div class="text">
			<div class="title">支付密码验证</div>
			<i class="m-icon icon-arrow-r"></i>
			</div>
			</a>
			<%
		}
		%>
	</section>

        </div>
    </div>
    <!-- error: point:pagejs is not defined; modname islayout/normal-flexible -->
    <script src="<%=staticResPath%>/etao-crowdfunding/js/p/me/modify-telephone.js"></script>
