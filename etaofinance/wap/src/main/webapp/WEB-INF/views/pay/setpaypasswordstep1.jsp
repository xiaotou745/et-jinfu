<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.entity.PublicProvinceCity" %>
<%@page import="java.util.List" %>
<%
	//网站跟地址
	String basePath = PropertyUtils.getProperty("java.wap.url");
	//静态资源跟地址
	String staticResPath = PropertyUtils.getProperty("staticResourceUrl");
	String phoneNo=(String)request.getAttribute("phone");
%>

  <link rel="stylesheet" href="<%=staticResPath%>/etao-crowdfunding/css/p/pay/set-paypassword-step1.css">
    <div class="g-wrap">
        <div class="g-views">
            
    <section class="container">
        <div class="title">
            <div class="des">
                验证码已发送到您的手机
            </div>
            <div class="phone">            
                185****4920
            </div>
        </div>
    </section>
    <section class="container">
    	<div class="form-input-vertify">
			<input type="text" placeholder="请输入短信验证码" data-role="vertifyCode"/>
			<button class="radius" data-role="getVertifyCode" disabled>重新获取(60s)</button>
		</div>
    </section>
    <section class="container top-gap">
    	<div class="btn-toggle">
    		<button disabled>下一步</button>
    	</div>
    </section>
    
        </div>
    </div>
    <input type="hidden" id="phoneNo" value="<%=phoneNo%>"></input>
    <!-- error: point:pagejs is not defined; modname islayout/normal-flexible -->
    <script src="<%=staticResPath%>/etao-crowdfunding/js/p/pay/set-paypassword-step1.js"></script>
        
