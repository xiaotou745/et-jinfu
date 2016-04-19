<%@page import="com.etaofinance.entity.Project"%>
<%@page import="com.etaofinance.core.util.ParseHelper"%>
<%@page import="com.etaofinance.core.enums.BalanceRecordType"%>
<%@page import="com.etaofinance.core.enums.BalanceRecordEnum"%>
<%@page import="com.etaofinance.entity.BalanceRecord"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.entity.PublicProvinceCity" %>
<%@page import="java.util.List" %>
<%
	//网站跟地址
	String basePath = PropertyUtils.getProperty("java.wap.url");
	//静态资源跟地址
	String staticResPath = PropertyUtils.getProperty("staticResourceUrl");	
// 	BalanceRecord item=(BalanceRecord)request.getAttribute("item");
// 	Project project=(Project)request.getAttribute("project");
	
%>

<link rel="stylesheet" href="<%=staticResPath%>/etao-crowdfunding/css/p/me/withdraw-detail.css">
    <div class="g-wrap">
        <div class="g-views">
            
	<section class="container bg">
		<div class="balance">
            <div class="amount">￥<span>50.00</span></div>
            <div class="instruction">提现金额</div>
        </div>
	</section>
	<section class="container bg">
		<div class="step">
			<div class="time">2015-06-29 12:00</div>
			<div class="step-description doing">提现申请</div>
		</div>
		<div class="step">
			<div class="time">2015-06-29 12:00</div>
			<div class="step-description doing">通过审核，银行处理中！</div>
		</div>
		<div class="step">
			<div class="time">2015-06-29 12:00</div>
			<div class="step-description">到账成功！</div>
		</div>
	</section>

        </div>
    </div>
    <!-- error: point:pagejs is not defined; modname islayout/normal-flexible -->
    <script src="<%=staticResPath%>/etao-crowdfunding/js/p/me/withdraw-detail.js"></script>
