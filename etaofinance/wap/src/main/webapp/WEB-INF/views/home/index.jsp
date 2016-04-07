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
<!DOCTYPE html>
<html>
<head>
    <title>首页</title>
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
    <link rel="stylesheet" href="<%=staticResPath%>/etao-crowdfunding/css/p/home/index.css">
    <script src="<%=staticResPath%>/etao-crowdfunding/js/mo.1.0.0.js"></script>
</head>

<body>
    <div class="g-wrap">
        <div class="g-views">
            
	<section>
		<div class="index-banner">
			<div class="banner-list">
				<p><img src="<%=staticResPath%>/etao-crowdfunding/img/p/home/index/index_banner.jpg"></p>
				<div class="list-new">
					<div class="new-left"><img src="<%=staticResPath%>/etao-crowdfunding/img/p/home/index/index_5.png"></div>
					<div class="new-right">
						<ul>
							<li><span>12%</span><b>年化收益</b></li>
							<li><span>¥100</span><b>起投金额</b></li>
							<li><span>7天</span><b>项目期限</b></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="banner-list" style="display:none;">
				<p><img src="<%=staticResPath%>/etao-crowdfunding/img/p/home/index/index_banner.jpg"></p>
				<div class="list-new">
					<div class="new-left"><img src="<%=staticResPath%>/etao-crowdfunding/img/p/home/index/index_5.png"></div>
					<div class="new-right">
						<ul>
							<li><span>12%</span><b>年化收益</b></li>
							<li><span>¥100</span><b>起投金额</b></li>
							<li><span>7天</span><b>项目期限</b></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="banner-li">
				<a href="###"></a><a href="###"></a>
			</div>
		</div>
	</section>
	<section class="index-container">
		<div class="container-one">
			<div class="one-list container">
				<h3><b>易淘餐厅华腾世纪总部公园店</b><span>融资中</span></h3>
				<p><span><img src="<%=staticResPath%>/etao-crowdfunding/img/p/home/index/img_1.jpg"></span><b><img src="<%=staticResPath%>/etao-crowdfunding/img/p/home/index/index_6.png"></b></p>
				<ul class="list-ul">
					<li><span>12%+5%</span><b>年化收益</b></li>
					<li><span>¥26万</span><b>目标金额</b></li>
					<li><span>￥4500</span><b>起投金额</b></li>
				</ul>				
			</div>
		</div>
		<div class="container-one">
			<div class="one-list container">
				<h3><b>易淘餐厅华腾世纪总部公园店</b><span class="one-acess">已成功</span></h3>
				<p><span><img src="<%=staticResPath%>/etao-crowdfunding/img/p/home/index/img_1.jpg"></span><b><img src="<%=staticResPath%>/etao-crowdfunding/img/p/home/index/index_7.png"></b></p>
				<ul class="list-ul">
					<li><span>12%+5%</span><b>年化收益</b></li>
					<li><span>¥26万</span><b>目标金额</b></li>
					<li><span>￥4500</span><b>起投金额</b></li>
				</ul>				
			</div>
		</div>
		<div class="container-one">
			<div class="one-list container">
				<h3><b>易淘餐厅华腾世纪总部公园店</b><span class="one-txle">预热中</span></h3>
				<p><span><img src="<%=staticResPath%>/etao-crowdfunding/img/p/home/index/img_1.jpg"></span><b><img src="<%=staticResPath%>/etao-crowdfunding/img/p/home/index/index_6.png"></b></p>
				<ul class="list-ul">
					<li><span>12%+5%</span><b>年化收益</b></li>
					<li><span>¥26万</span><b>目标金额</b></li>
					<li><span>￥4500</span><b>起投金额</b></li>
				</ul>				
			</div>
		</div>
		<div class="container-one">
			<div class="one-list container">
				<h3><b>易淘餐厅华腾世纪总部公园店</b><span>融资中</span></h3>
				<p><span><img src="<%=staticResPath%>/etao-crowdfunding/img/p/home/index/img_1.jpg"></span><b><img src="<%=staticResPath%>/etao-crowdfunding/img/p/home/index/index_7.png"></b></p>
				<ul class="list-ul">
					<li><span>12%+5%</span><b>年化收益</b></li>
					<li><span>¥26万</span><b>目标金额</b></li>
					<li><span>￥4500</span><b>起投金额</b></li>
				</ul>				
			</div>
		</div>
	</section>
	<section>
		
	</section>

        </div>
    </div>
    <!-- error: point:pagejs is not defined; modname islayout/normal-flexible -->
    <script src="<%=staticResPath%>/etao-crowdfunding/js/p/home/index.js"></script>
</body>

</html>
