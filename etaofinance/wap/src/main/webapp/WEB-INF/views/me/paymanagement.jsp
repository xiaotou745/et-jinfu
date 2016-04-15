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
  <link rel="stylesheet" href="<%=staticResPath%>/etao-crowdfunding/css/p/me/pay-management.css">
      
  <div class="g-wrap">
        <div class="g-views">
            
	<section class="container">
		
		<div class="item">
			<div><a href="<%=basePath%>/pay/modifypaypasswordstep1">修改支付密码   </a></div>
			<i class="m-icon icon-arrow-r"></i>
		</div>

		<div class="item">
			<div><a href="<%=basePath%>/pay/retrievepaypasswordstep1">找回支付密码  </a></div>
			<i class="m-icon icon-arrow-r"></i>
		</div>
	 
	</section>

        </div>
    </div>
    <!-- error: point:pagejs is not defined; modname islayout/normal-flexible -->
    <script src="<%=staticResPath%>/etao-crowdfunding/js/p/me/pay-management.js"></script>
    