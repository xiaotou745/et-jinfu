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
	BalanceRecord item=(BalanceRecord)request.getAttribute("item");
	Project project=(Project)request.getAttribute("project");
	
%>
<link rel="stylesheet" href="<%=staticResPath%>/etao-crowdfunding/css/p/me/balance-detail.css">
    <div class="g-wrap">
        <div class="g-views">
            
	<section class="container bg">
		<div class="text">
			<div class="title">
				类型：
			</div>
			<div class="content">
				<%=BalanceRecordType.getEnum(item.getTypeid()).desc()%>
			</div>
		</div>
	</section>
	<section class="container bg">
		<div class="text">
			<div class="title">
				支出：
			</div>
			<div class="content">
				￥<%=ParseHelper.digitsNum(item.getAmount(), 2)%>
			</div>
		</div>
		<div class="text top-line">
			<div class="title">
				支出时间：
			</div>
			<div class="content">
				<%=ParseHelper.ToDateString(item.getOpttime())%>
			</div>
		</div>
		<div class="text top-line">
			<div class="title">
				项目名称：
			</div>
			<div class="content">
				<div><%=project.getProjectname()%></div>
<!-- 				<span>订单号：<b>110344858</b></span> -->
			</div>
		</div>
	</section>
		<section class="container bg">
		<div class="text">
			<div class="title">
				账户余额：
			</div>
			<div class="content">
				￥<%=ParseHelper.digitsNum(item.getAfteramount(), 2)%>
			</div>
		</div>
	</section>

        </div>
    </div>
    <!-- error: point:pagejs is not defined; modname islayout/normal-flexible -->
    <script src="<%=staticResPath%>/etao-crowdfunding/js/p/me/balance-detail.js"></script>

