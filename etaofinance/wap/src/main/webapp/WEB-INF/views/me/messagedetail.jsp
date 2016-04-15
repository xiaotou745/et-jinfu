<%@page import="com.etaofinance.entity.Message"%>
<%@page import="com.etaofinance.core.util.ParseHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.entity.PublicProvinceCity" %>
<%@page import="java.util.List" %>
<%
	//网站跟地址
	String basePath = PropertyUtils.getProperty("java.wap.url");
	//静态资源跟地址
	String staticResPath = PropertyUtils.getProperty("staticResourceUrl");
	Message m=(Message)request.getAttribute("message");
%>

<link rel="stylesheet" href="<%=staticResPath%>/etao-crowdfunding/css/p/me/message-detail.css">
    <div class="g-wrap">
        <div class="g-views">
            
	<section class="container">
		<div class="title-wrap">
			<div class="title">
				<%=m.getMsghead() %>
			</div>
			<div class="time">
				<%=ParseHelper.ToDateString(m.getCreatetime())%>
			</div>
		</div>
		<div class="content top-line">
			<%=m.getContent()%>
		</div>
	</section>

        </div>
    </div>
    <!-- error: point:pagejs is not defined; modname islayout/normal-flexible -->
    <script src="<%=staticResPath%>/etao-crowdfunding/js/p/me/message-detail.js"></script>