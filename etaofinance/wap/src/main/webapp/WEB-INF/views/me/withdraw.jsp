<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.entity.PublicProvinceCity" %>
<%@page import="java.util.List" %>
<%
	//网站跟地址
	String basePath = PropertyUtils.getProperty("java.wap.url");
	//静态资源跟地址
	String staticResPath = PropertyUtils.getProperty("staticResourceUrl");
	String allowwithdrawprice=(String)request.getAttribute("allowwithdrawprice");
	
%>
  <link rel="stylesheet" href="<%=staticResPath%>/etao-crowdfunding/css/p/me/withdraw.css">
      
  <div class="g-wrap">
        <div class="g-views">
            
	<form data-role="form">
	<section class="container bg top-gap">
		<div class="label-input">
			<label>金额（元）</label>
			<input type="text" placeholder="请输入提现金额" name="amount" data-role="amount"/>
		</div>
		<div class="label-input">
			<label>当前可提现金额</label>
			<span>￥<b><%=allowwithdrawprice%></b></span>
			<span>全部提现</span>
		</div>
	</section>
	<section class="container">
		<div class="tips">
			提现至：尾号为<b>****3210 </b>的农业银行储蓄卡
		</div>
	</section>
	<section class="container top-gap">
		<div class="btn-toggle">
			<button disabled data-role="submit">提现</button>
		</div>
	</section>
	<section class="container">
		<div class="link">提现记录</div>
	</section>
	</form>

        </div>
    </div>
    <!-- error: point:pagejs is not defined; modname islayout/normal-flexible -->
    <script src="<%=staticResPath%>/etao-crowdfunding/js/p/me/withdraw.js"></script>
    