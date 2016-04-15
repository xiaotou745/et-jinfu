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
	MemberOther other=(MemberOther)request.getAttribute("other");
	int cardSize=(int)request.getAttribute("cardsize");
%>
<link rel="stylesheet" href="<%=staticResPath%>/etao-crowdfunding/css/p/me/account-balance.css">
    <div class="g-wrap">
        <div class="g-views">
            
    <section class="container bg">
        <div class="balance">
            <div class="amount">￥<span><%=other.getBalanceprice()%></span></div>
            <div class="instruction">现金余额</div>
            <div class="btn-toggle">
                <a href="<%=basePath%>/me/accountflow"><button>查看详情</button></a>
            </div>
        </div>
    </section>
    <section class="container bg">
    	<a href="<%=basePath%>/me/recharge">
    	<div class="text">
    		<div class="title">充值</div>
    		<div class="content">
    			<i class="m-icon icon-arrow-r"></i>
    		</div>
    	</div>
        </a>
       	<a href="<%=basePath%>/me/withdraw">
    	<div class="text top-line">
    		<div class="title">提现</div>
    		<div class="content">
    			<i class="m-icon icon-arrow-r"></i>
    		</div>
    	</div>
        </a>
    </section>
    <section class="container bg">
    	<a href="<%=basePath%>/me/bankcardlist">        
    	<div class="text">
    		<div class="title">我的银行卡</div>
    		<div class="content">
    			<span><%=cardSize%></span>张
    			<i class="m-icon icon-arrow-r"></i>
    		</div>
    	</div>
        </a>
       <a href="<%=basePath%>/me/paymanagement">   
    	<div class="text top-line">
    		<div class="title">支付管理</div>
    		<div class="content">
    			<i class="m-icon icon-arrow-r"></i>
    		</div>
    	</div>
        </a>
    </section>
    
        </div>
    </div>
    <!-- error: point:pagejs is not defined; modname islayout/normal-flexible -->
    <script src="<%=staticResPath%>/etao-crowdfunding/js/p/me/account-balance.js"></script>


