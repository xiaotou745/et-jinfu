<%@page import="com.etaofinance.core.util.ParseHelper"%>
<%@page import="com.etaofinance.core.enums.ProjectStatus"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.entity.PublicProvinceCity" %>
<%@page import="com.etaofinance.entity.domain.ProjectSubscriptionDM" %>
<%@page import="java.util.List" %>
<%
	//网站跟地址
	String basePath = PropertyUtils.getProperty("java.wap.url");
	//静态资源跟地址
	String staticResPath = PropertyUtils.getProperty("staticResourceUrl");
%>

<link rel="stylesheet" href="<%=staticResPath%>/etao-crowdfunding/css/p/me/project-concern.css">
    <div class="g-wrap">
        <div class="g-views">
            
	
<section class="container bg">
	<div class="title">
		<div>关注时间：2015.06.29</div>
		<i class="fall">融资中</i>
	</div>
	<div class="item">
		<div><img src="<%=staticResPath%>/etao-crowdfunding/img/p/me/project-concern/img.png" alt=""></div>
		<dl>
			<dt>易淘餐厅华腾世纪总部公园店</dt>
			<dd><span>上线时间：</span>2015.06.29</dd>
		</dl>
	</div>
	<div class="offer">
		<ul>
			<li>直接认购</li>
			<li><i></i>取消关注</li>
		</ul>
	</div>
</section>
<section class="container bg">
	<div class="title">
		<div>关注时间：2015.06.29</div>
		<i class="win">已成功</i>
	</div>
	<div class="item">
		<div><img src="<%=staticResPath%>/etao-crowdfunding/img/p/me/project-concern/img.png" alt=""></div>
		<dl>
			<dt>易淘餐厅华腾世纪总部公园店</dt>
			<dd><span>上线时间：</span>2015.06.29</dd>
		</dl>
	</div>
	<div class="offer">
		<ul>
			<li>直接认购</li>
			<li><i></i>取消关注</li>
		</ul>
	</div>
</section>

        </div>
    </div>
    <script src="<%=staticResPath%>/etao-crowdfunding/js/p/me/project-concern.js"></script>