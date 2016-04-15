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
  <link rel="stylesheet" href="<%=staticResPath%>/etao-crowdfunding/css/p/me/bankcard-list.css">
      
     <div class="g-wrap">
        <div class="g-views">
            
	<section class="container top-gap">
		<div class="bankcard-wrap">
			<div class="bank-name">农业银行</div>
			<div class="card-number">****  **** **** 3210</div>
		</div>
	</section>

        </div>
    </div>
    <!-- error: point:pagejs is not defined; modname islayout/normal-flexible -->
    <script src="<%=staticResPath%>/etao-crowdfunding/js/p/me/bankcard-list.js"></script>
    