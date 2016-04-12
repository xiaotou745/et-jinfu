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
	boolean isReal=member.getLevel()>0;
%>
 <link rel="stylesheet" href="<%=staticResPath%>/etao-crowdfunding/css/p/me/certification.css">
    <div class="g-wrap">
        <div class="g-views">
            
<section class="container bg">
	<div class="label-input">
		<label>真实姓名</label>
		<%
		if(isReal)
		{%>
			<input readonly="readonly" type="text" placeholder="请填写真实姓名" name="truename" data-role="name" value="<%=member.getTruename()%>"/>
		<%}else
		{%>
			<input  type="text" placeholder="请填写真实姓名" name="truename" data-role="name" />
		<%}
		%>
		
	</div>
	<div class="label-input">
		<label>身份证号</label>
		<%
		if(isReal)
		{%>
			<input readonly="readonly" type="text" placeholder="请填写真实身份证号" name="idcard" data-role="card" value="<%=member.getIdcard()%>" />
		<%}else
		{%>
			<input type="text" placeholder="请填写真实身份证号" name="idcard" data-role="card"/>
		<%}
		%>
	</div>
</section>
<section class="container bg">
	<div class="pledge">
		<i class="m-icon icon-approval"></i>
		<span>我承诺以上登记的所有信息属实，并对虚假信息产生的一切后果负责。</span>
	</div>
	<div class="btn-toggle">
		<button disabled data-role="submit">验证</button>
	</div>	
</section>

        </div>
    </div>
    <!-- error: point:pagejs is not defined; modname islayout/normal-flexible -->
    <script src="<%=staticResPath%>/etao-crowdfunding/js/p/me/certification.js"></script>
