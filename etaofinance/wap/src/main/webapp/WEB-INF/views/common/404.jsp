<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page isErrorPage="true"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%
	String basePath = PropertyUtils.getProperty("java.wap.url");
    response.setStatus(HttpServletResponse.SC_OK);
  //静态资源跟地址
  	String staticResPath = PropertyUtils.getProperty("staticResourceUrl");
%>

<!DOCTYPE html>
<html>
<head>
    <title>404页面</title>
    <meta name="description" content="" />
    <meta name="keywords" content="" />
    <meta charset="UTF-8">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <meta name="format-detection" content="telephone=no, email=no" />
    <meta name="360-fullscreen" content="true" />
    <!-- QQ强制竖屏 -->
    <meta name="x5-orientation" content="portrait">
    <!-- QQ强制全屏 -->
    <meta name="x5-fullscreen" content="true">
    <!-- QQ应用模式 -->
    <meta name="x5-page-mode" content="app">
    <!-- UC强制全屏 -->
    <meta name="full-screen" content="yes">
    <!-- UC应用模式 -->
    <meta name="browsermode" content="application">
    <!-- uc强制竖屏 -->
    <meta name="screen-orientation" content="portrait">
    <script src="<%=staticResPath%>/etao-crowdfunding/js/flexible/flexible_css.debug.js"></script>
    <script src="<%=staticResPath%>/etao-crowdfunding/js/flexible/flexible.debug.js"></script>
    <link rel="stylesheet" href="<%=staticResPath%>/etao-crowdfunding/css/p/other/404.css">
    <script src="<%=staticResPath%>/etao-crowdfunding/js/mo.1.0.0.js"></script>
</head>

<body>
    <div class="g-wrap">
        <div class="g-views">
            
	<section class="container">
		<div class="error-image"></div>
		<div class="error-text"></div>
	</section>
	<seciton class="container">
		<div class="tips">很遗憾您访问的页面不存在！</div>
	</seciton>
	<section class="container">
		<div class="btn-toggle">
			<a href="<%=basePath%>/home/index"><button>返回首页</button></a>
		</div>
	</section>

        </div>
    </div>
    <!-- error: point:pagejs is not defined; modname islayout/normal-flexible -->
    <script src="<%=staticResPath%>/etao-crowdfunding/js/p/other/404.js"></script>
</body>

</html>