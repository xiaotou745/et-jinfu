
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
 <link rel="stylesheet" href="<%=staticResPath%>/etao-crowdfunding/css/p/more/index.css">
    <div class="g-wrap">
        <div class="g-views">
            
	<section class="container bg">
		<a href="<%=basePath%>/more/projectapply">
		<div class="text">
			<div>项目报名</div>
			<i class="m-icon icon-arrow-r"></i>
		</div>
		</a>
	</section>
	<section class="container bg">
		<a href="<%=basePath%>/more/question">
		<div class="text">
			<div>常见问题</div>
			<i class="m-icon icon-arrow-r"></i>
		</div>
		</a>
		<a href="<%=basePath%>/more/feedback">
		<div class="text top-line">
			<div>意见反馈</div>
			<i class="m-icon icon-arrow-r"></i>
		</div>
		</a>
	</section>
	<section class="container bg info-telephone">
		<div class="text">
				客服电话:
				&nbsp;&nbsp;<span>400-070-5286</span>
				(工作日&nbsp;&nbsp;<span>09：00-21：30</span>)
		</div>
	</section>
	<div class="footer top-line">
    <ul>
        <li><a href="<%=basePath%>/home/index"><i class="m-icon icon-home"></i><span>首页</span></a></li>
        <li><a href="<%=basePath%>/more/introduce"><i class="m-icon icon-instruction"></i><span>指南</span></a></li>
        <li><a href="<%=basePath%>/me/usercenter"><i class="m-icon icon-me"></i><span>我</span></a></li>
        <li class="on"><a href="#"><i class="m-icon icon-more"></i><span>更多</span></a></li>
    </ul>
</div>

        </div>
    </div>
    <!-- error: point:pagejs is not defined; modname islayout/normal-flexible -->
    <script src="<%=staticResPath%>/etao-crowdfunding/js/p/more/index.js"></script>
