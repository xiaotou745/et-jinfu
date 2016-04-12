<%@page import="com.etaofinance.entity.Member"%>
<%@page import="com.etaofinance.entity.MemberOther"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.entity.PublicProvinceCity" %>
<%@page import="java.util.List" %>
<%@page import="com.etaofinance.core.enums.MemberSexType" %>

<%
	//网站跟地址
	String basePath = PropertyUtils.getProperty("java.wap.url");
	//静态资源跟地址
	String staticResPath = PropertyUtils.getProperty("staticResourceUrl");
	Member member=(Member)request.getAttribute("member");
%>

   <link rel="stylesheet" href="<%=staticResPath%>/etao-crowdfunding/css/p/me/user-info.css">
    <div class="g-wrap">
        <div class="g-views">
            
    <section class="container bg">
        <div class="info">
            <a href=""><img src="<%=member.getHeadimage()%>" alt=""></a>
        </div>
    </section>
    <section class="container bg">
        <a href="#">
            <div class="text">
                <div>用户名</div>
                <span><%=member.getUsername()%></span>
                <i class="m-icon icon-arrow-r"></i>
            </div>
        </a>
        <a href="javascript:" data-role="gender">
            <div class="text  top-line">
                <div>性别</div>
                <span><%=MemberSexType.getEnum(member.getSex()).desc()%></span>
                <i class="m-icon icon-arrow-r"></i>
            </div>
        </a>
    </section>
    <section class="container bg">
        <a href="#">
            <div class="text">
                <div>实名认证</div>
                <span><%=member.getLevel()==0?"未认证":"已认证"%></span>
                <i class="m-icon icon-arrow-r"></i>
            </div>
        </a>
        <a href="#">
            <div class="text  top-line">
                <div>跟投人认证</div>
                <span><%=member.getLevel()>1?"已认证":"未认证"%></span>
                <i class="m-icon icon-arrow-r"></i>
            </div>
        </a>
        <a href="#">
            <div class="text  top-line">
                <div>领头人认证</div>
                <span><%=member.getLevel()>2?"已认证":"未认证"%></span>
                <i class="m-icon icon-arrow-r"></i>
            </div>
        </a>
    </section>
    <div class="sex-popup hide">
    	<div class="popup-title">
    		<div class="close"></div>
    	</div>
    	<div class="popup-content">
    		<div>性别</div>
    		<div class="modify-gender" data-gender="1" ><span>男 </span><i class="select <%=member.getSex()==1?"active":""%>"></i></div>
    		<div class="modify-gender" data-gender="2" ><span>女</span><i class="select <%=member.getSex()==2?"active":""%>"></i></div>
    		<div class="modify-gender" data-gender="0" ><span>保密</span><i class="select <%=member.getSex()==0?"active":""%>"></i></div>
    	</div>
    </div>

        </div>
    </div>
    <!-- error: point:pagejs is not defined; modname islayout/normal-flexible -->
    <script src="<%=staticResPath%>/etao-crowdfunding/js/p/me/user-info.js"></script>



