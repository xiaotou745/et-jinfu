<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.entity.PublicProvinceCity" %>
<%@page import="java.util.List" %>
<%
	//网站跟地址
	String basePath = PropertyUtils.getProperty("java.wap.url");
	//静态资源跟地址
	String staticResPath = PropertyUtils.getProperty("staticResourceUrl");
	String phoneString=(String)request.getAttribute("phoneString");
	String phoneNo=(String)request.getAttribute("phone");
	String checkKey= (String)request.getAttribute("checkKey");
%>
	<input type="hidden" id="checkKey" value="<%=checkKey%>"/>
<input type="hidden" id="phoneNo" value="<%=phoneNo%>"/>
<link rel="stylesheet" href="<%=staticResPath%>/etao-crowdfunding/css/p/me/retrieve-password-step2.css">
    <div class="g-wrap">
        <div class="g-views">
            
    <section class="container">
        <div class="title">
            <div class="des">
                验证码已发送到您的手机
            </div>
            <div class="phone">
                <%=phoneString%>
            </div>
        </div>
    </section>
    <section class="container">
    	<!-- <div class="nick-name">昵称：<span>我爱人民币</span></div> -->
        <div class="validation-message"></div>
    	<div class="form-input-vertify">
			<input type="text" placeholder="请输入短信验证码" data-role="vertifyCode"/>
			<button class="radius" data-role="getVertifyCode" disabled>重新获取(5s)</button>
		</div>
    </section>
    <section class="container top-gap">
    	<div class="btn-toggle">
    		<button disabled>下一步</button>
    	</div>
    </section>
    
        </div>
    </div>
    <!-- error: point:pagejs is not defined; modname islayout/normal-flexible -->
    <script src="<%=staticResPath%>/etao-crowdfunding/js/p/me/retrieve-password-step2.js"></script>



