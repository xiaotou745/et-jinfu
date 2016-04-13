<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.entity.PublicProvinceCity" %>
<%@page import="java.util.List" %>
<%
	//网站跟地址
	String basePath = PropertyUtils.getProperty("java.wap.url");
	//静态资源跟地址
	String staticResPath = PropertyUtils.getProperty("staticResourceUrl");
	String tip= (String)request.getAttribute("tip");
	String url=(String)request.getAttribute("url");
	String button=(String)request.getAttribute("button");
%>

<link rel="stylesheet" href="<%=staticResPath%>/etao-crowdfunding/css/p/other/transfer.css">
    <div class="g-wrap">
        <div class="g-views">
            
	<section class="container error">
		<div class="error-image"></div>
		<div class="error-text"></div>
	</section>
	<seciton class="container error">
		<div class="tips"><%=tip%></div>
		<div class="link"><a href="<%=url%>"><%=button%></a></div>
	</seciton>

        </div>
    </div>
    <script src="<%=staticResPath%>/etao-crowdfunding/js/p/other/transfer.js"></script>
