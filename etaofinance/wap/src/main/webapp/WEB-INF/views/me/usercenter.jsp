<%@page import="com.etaofinance.entity.Member"%>
<%@page import="com.etaofinance.entity.MemberOther"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.entity.PublicProvinceCity" %>
<%@page import="java.util.List" %>

<%
	//网站跟地址
	String basePath = PropertyUtils.getProperty("java.wap.url");
	//静态资源跟地址
	String staticResPath = PropertyUtils.getProperty("staticResourceUrl");
	MemberOther other=(MemberOther)request.getAttribute("other");
	Member member=(Member)request.getAttribute("member");
%>

<link rel="stylesheet" href="<%=staticResPath%>/etao-crowdfunding/css/p/me/user-center.css">
    <div class="g-wrap">
        <div class="g-views">
            
	<section class="content">
		<section class="container bgEm">
        <div class="name">
            <div class="name_left">
                <a href="<%=basePath%>/me/userinfo"><img src="<%=member.getHeadimage()%>" alt=""></a>
                <div>
                    <div><%=member.getUsername()%></div>
                    <i class="<%=member.getLevel()>1?"on":""%>"></i>
                </div>
            </div>
            <div class="name_right">
                <div>
                    <i></i>
                </div>
                <a href="#"><span>消息</span></a>
            </div>
        </div>
    </section>
    <section class="container bg">
        <a href="<%=basePath%>/me/accountblance">
        <div class="text">
            <div><i class="m-icon icon-money"></i>账户余额</div>
            <span>￥<%=other.getBalanceprice() %></span>
            <i class="m-icon icon-arrow-r"></i>
        </div>
        </a>
    </section>
    <section class="container bg">
        <a href="<%=basePath%>/me/projectinvest">
        <div class="text">
            <div><i class="m-icon icon-invest"></i>投资的项目</div>
            <i class="m-icon icon-arrow-r"></i>
        </div>
        </a>
        <a href="<%=basePath%>/me/projectconcern">
        <div class="text  top-line">
            <div><i class="m-icon icon-concern"></i>关注的项目</div>
            <i class="m-icon icon-arrow-r"></i>
        </div>
        </a>
        <a href="#">
        <div class="text  top-line">
            <div><i class="m-icon icon-lanuch"></i>发起的项目</div>
            <i class="m-icon icon-arrow-r"></i>
        </div>
        </a>
    </section>
    <section class="container bg">
        <a href="#">
        <div class="text">
            <div><i class="m-icon icon-security"></i>账号与安全</div>
            <i class="m-icon icon-arrow-r"></i>
        </div>
        </a>
    </section>
    <section class="container bg">
        <a href="<%=basePath%>/me/logout"><div class="exit">安全退出</div></a>
    </section>
	</section>
    
    <div class="footer top-line">
    <ul>
        <li><a href="<%=basePath%>/home/index"><i class="m-icon icon-home"></i><span>首页</span></a></li>
        <li><a href="<%=basePath%>/more/introduce"><i class="m-icon icon-instruction"></i><span>指南</span></a></li>
        <li class="on"><a href="#"><i class="m-icon icon-me"></i><span>我</span></a></li>
        <li><a href="<%=basePath%>/more/index""><i class="m-icon icon-more"></i><span>更多</span></a></li>
    </ul>
</div>
    
        </div>
    </div>
    <!-- error: point:pagejs is not defined; modname islayout/normal-flexible -->
<!--     <script src="http://172.18.11.112:3504/etao-crowdfunding/js/p/me/user-center.js"></script> -->


