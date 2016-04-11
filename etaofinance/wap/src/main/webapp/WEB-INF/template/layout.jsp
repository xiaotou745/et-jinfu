<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%
	String staticResPath = PropertyUtils.getProperty("staticResourceUrl");
%>
<!DOCTYPE html>
<html>
<head>
    <title>${currenttitle}</title>
    <!-- 全站通用顶部HEader KAISHI -->
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
    <script src="<%=staticResPath%>/etao-crowdfunding/js/mo.1.0.0.js"></script>
    <!-- 全站通用顶部HEader end -->
</head>
<body>
<tiles:insertAttribute name="body"></tiles:insertAttribute>
</body>
</html>