
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.entity.PublicProvinceCity" %>
<%@page import="java.util.List" %>
<%
	//网站跟地址
	String basePath = PropertyUtils.getProperty("java.wap.url");
	//静态资源跟地址
	String staticResPath = PropertyUtils.getProperty("staticResourceUrl");
	int flag=(int)request.getAttribute("flag");
%>



 <link rel="stylesheet" href="<%=staticResPath%>/etao-crowdfunding/css/p/instruction/introduce.css">
    <div class="g-wrap">
        <div class="g-views">
            
	<section>
		<div class="introduce-banner">
			<p><img src="<%=staticResPath%>/etao-crowdfunding/img/p/instruction/introduce/introduce_1.jpg"></p>
		</div>
	</section>
	<section class="introduce-size">
		<div class="container">
			<h3>什么是易淘众筹？</h3>
			<p>在易淘众筹平台上，投资者通过购买实体店铺项目收益权，获得分红收益。</p>
		</div>	
	</section>
	<section class="introduce-profit">
		<div class="container">
			<h3>如何投资获得收益？</h3>
			<p><img src="<%=staticResPath%>/etao-crowdfunding/img/p/instruction/introduce/introduce_2.png"></p>
			<%
			if(flag==1)
			{
				%>
				<a href="<%=basePath%>/me/certificationinvestor"><button >认证跟投人</button></a>
				<% 
			}
			%>
		</div>
	</section>
	<section class="introduce-size">
		<div class="container">
			<h3>投资风险如何？</h3>
			<p>目前平台上的项目有稳健型和风险共担型两种类型，稳健型的项目风险较低 ，有较稳定的收益；风险共担型的项目有一定的风险，需要与经营者共同承担经营风险，不过预期收益也较高。</p>
		</div>	
	</section>
	<section class="introduce-plone">
		<div class="plone">
			<h3>如何融资？</h3>
			<p><img src="<%=staticResPath%>/etao-crowdfunding/img/p/instruction/introduce/introduce_3.png"></p>
		</div>
	</section>
	<div class="footer top-line">
    <ul>
        <li ><a href="<%=basePath%>/home/index"><i class="m-icon icon-home"></i><span>首页</span></a></li>
        <li class="on"><a href="#"><i class="m-icon icon-instruction"></i><span>指南</span></a></li>
        <li><a href="<%=basePath%>/me/usercenter"><i class="m-icon icon-me"></i><span>我</span></a></li>
        <li><a href="<%=basePath%>/more/index"><i class="m-icon icon-more"></i><span>更多</span></a></li>
    </ul>
</div>
        </div>
    </div>
    <!-- error: point:pagejs is not defined; modname islayout/normal-flexible -->
    <script src="<%=staticResPath%>/etao-crowdfunding/js/p/instruction/introduce.js"></script>