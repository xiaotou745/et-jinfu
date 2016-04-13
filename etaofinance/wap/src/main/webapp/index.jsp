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

<html>
<script src="<%=basePath%>/js/jquery-2.1.1.js"></script>
<body>
<body>
hello
<a href="javascript:void(0)" id="login">点击进入主页</a>
</body>
<script>
$('#login').click(function(){
	window.location.href=window.location.href+'home/index';
});
</script>
</html>