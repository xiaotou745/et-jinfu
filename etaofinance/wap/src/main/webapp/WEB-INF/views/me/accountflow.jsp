<%@page import="java.util.ArrayList"%>
<%@page import="com.etaofinance.entity.domain.BalanceRecordDM"%>
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
	List<BalanceRecordDM> list=(ArrayList<BalanceRecordDM>)request.getAttribute("list");
%>


<link rel="stylesheet" href="<%=staticResPath%>/etao-crowdfunding/css/p/me/account-flow.css">
    <div class="g-wrap">
        <div class="g-views">
            
	<section class="container bg">
		<div class="text">
			<div class="summary">
				<div>提现</div>
				<span>2015-06-29</span>
			</div>
			<div class="detail">
				<div>余额：<b>0</b></div>
				<span><b>-</b>50</span>
			</div>			
		</div>
		<div class="text">
			<div class="summary">
				<div>充值</div>
				<span>2015-06-29</span>
			</div>
			<div class="detail">
				<div>余额：<b>0</b></div>
				<span><b>+</b>50</span>
			</div>			
		</div>
		<div class="text">
			<div class="summary">
				<div>投资</div>
				<span>2015-06-29</span>
			</div>
			<div class="detail">
				<div>余额：<b>0</b></div>
				<span><b>+</b>50</span>
			</div>			
		</div>
		<div class="text">
			<div class="summary">
				<div>退款至余额</div>
				<span>2015-06-29</span>
			</div>
			<div class="detail">
				<div>余额：<b>0</b></div>
				<span><b>+</b>50</span>
			</div>			
		</div>
	</section>

        </div>
    </div>
    <script src="<%=staticResPath%>/etao-crowdfunding/js/p/me/account-flow.js"></script>

