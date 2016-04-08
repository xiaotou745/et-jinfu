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
<!-- <script>window.location.href='http://izc.etao.cn/zt';</script> -->
hello
<a href="javascript:void(0)" id="login">点击登录测试</a>
</body>
<script>
$('#login').click(function(){
	var url="<%=basePath%>/user/login";
	var data={"loginName":"15652662526",
			"pwd":"123456",
			"remberMe":"1",
			"reUrl":""}
	 $.ajax({
         type: "POST",
         url: url,
         data: data,
         dataType: "json",
         success: function(dt){
                   console.log(dt);
                     },
         contentType:"application/json;charset=utf-8"
	 });
});
</script>
</html>