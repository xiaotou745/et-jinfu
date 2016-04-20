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
  <link rel="stylesheet" href="<%=staticResPath%>/etao-crowdfunding/css/p/me/recharge.css">
      
 <div class="g-wrap">
        <div class="g-views">
            
	<form data-role="form">
	<section class="container bg">
		<div class="label-input">
			<label>金额（元）</label>
			<input type="tel" placeholder="请输入充值金额" name="amount" data-role="amount"/>
		</div>
	</section>
	<section class="container bg">
		<div class="item">
			<div class="title">选择支付方式</div>
			<div class="content">
				<div class="des-wrap">
					<i class="pay"></i>
					<div class="des">
						<div>银联支付</div>
						<div>推荐有银联卡的用户使用</div>
					</div>
				</div>
				<span class="select active"></span>
<!-- 				<i class="select"></i> -->
			</div>
<!-- 			<div class="content top-line"> -->
<!-- 				<div class="des-wrap"> -->
<!-- 					<i class="wallet"></i> -->
<!-- 					<div class="des"> -->
<!-- 						<div>易淘钱包</div> -->
<!-- 						<div>推荐有易淘钱包账号的用户使用</div> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				<span class="select active"></span> -->
<!-- 			</div> -->
		</div>
	</section>
	<section class="container top-gap">
		<div class="btn-toggle" >
			<button disabled data-role="submit">确认</button>
		</div>
	</section>
	<input type="hidden" name="accounttype" value="1" />
	</form>

        </div>
    </div>
    <!-- error: point:pagejs is not defined; modname islayout/normal-flexible -->
    <script src="<%=staticResPath%>/etao-crowdfunding/js/p/me/recharge.js"></script>
    