<%@page import="com.etaofinance.core.util.ParseHelper"%>
<%@page import="com.etaofinance.core.enums.ProjectStatus"%>
<%@page import="com.etaofinance.entity.domain.ProjectComment"%>
<%@page import="com.etaofinance.entity.ProjectImage"%>
<%@page import="com.etaofinance.entity.domain.ProjectMember"%>
<%@page import="com.etaofinance.entity.Member"%>
<%@page import="com.etaofinance.entity.domain.ProjectModel"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.entity.PublicProvinceCity" %>
<%@page import="java.util.List" %>
<%@page import="com.etaofinance.entity.common.PagedResponse" %>
<%
	//网站跟地址
	String basePath = PropertyUtils.getProperty("java.wap.url");
	//静态资源跟地址
	String staticResPath = PropertyUtils.getProperty("staticResourceUrl");

	Long projectid=(Long)request.getAttribute("projectid");
	Integer quantity=(Integer)request.getAttribute("quantity");
	Integer islead=(Integer)request.getAttribute("islead");
%>

<link rel="stylesheet" href="<%=staticResPath%>/etao-crowdfunding/css/p/pay/subscribe-input-paypassword.css">
    <div class="g-wrap">
        <div class="g-views">
            
    <div class="modify-paypassword-wrap">
        <div class="title">
            请输入6位支付密码
        </div>
        <div class="pay_pwd_wrap ">
            <div class="point"></div>
            <input type="tel" class="pay_pwd" value="" maxlength="6" />
        </div>
        <div class="err_tip">
            两次输入密码不一致
        </div>
        <div class="test"></div>
    </div>
		<input type="hidden"  value="<%=projectid %>" name="projectid" data-role="projectid" />
		<input type="hidden"  value="<%=quantity %>" name="quantity" data-role="quantity" />
		<input type="hidden"  value="<%=islead %>" name="islead" data-role="islead" />
        </div>
    </div>
    <script src="<%=staticResPath%>/etao-crowdfunding/js/p/pay/subscribe-input-paypassword.js"></script>
