<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.entity.PublicProvinceCity" %>
<%@page import="java.util.List" %>
<%@page import="com.etaofinance.entity.Member"%>
<%
	//网站跟地址
	String basePath = PropertyUtils.getProperty("java.wap.url");
	//静态资源跟地址
	String staticResPath = PropertyUtils.getProperty("staticResourceUrl");
	Member member=(Member)request.getAttribute("member");
%>
  <link rel="stylesheet" href="<%=staticResPath%>/etao-crowdfunding/css/p/me/account-security.css">
    <div class="g-wrap">
        <div class="g-views">
            
	<section class="container bg">
		<a href="<%=basePath%>/me/modifytelephone"><div class="text">
			<div class="title">手机号</div>
			<div class="content">
				<span><%=member.getPhoneno()%></span>
				<i class="m-icon icon-arrow-r"></i>
			</div>
		</div>
		</a>
		<a href="<%=basePath%>/me/modifyemail">
		<div class="text">
			<div class="title">邮箱</div>
			<div class="content">
			<%
			if(member.getEmail()!=null&&!member.getEmail().equals(""))
			{			
			%>
			<span><%=member.getEmail()%></span>
			<%			
			} else {			
			%>
		  <span>未绑定</span>
		  	<%			
			} 	
			%>
				<i class="m-icon icon-arrow-r"></i>
			</div>
		</div>
		</a>
		<a href="#">
		<div class="text">
			<div class="title">密码</div>
			<div class="content">
				<span>修改密码</span>
				<i class="m-icon icon-arrow-r"></i>
			</div>
		</div>
		</a>
	</section>

        </div>
    </div>
    <!-- error: point:pagejs is not defined; modname islayout/normal-flexible -->  
      <script src="<%=staticResPath%>/etao-crowdfunding/js/p/me/account-security.js"></script>
