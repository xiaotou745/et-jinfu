<%@page import="com.etaofinance.entity.Member"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.entity.PublicProvinceCity" %>
<%@page import="java.util.List" %>
<%
	//网站跟地址
	String basePath = PropertyUtils.getProperty("java.wap.url");
	//静态资源跟地址
	String staticResPath = PropertyUtils.getProperty("staticResourceUrl");
	String checkKey=(String)request.getAttribute("checkKey");
%>
   <link rel="stylesheet" href="<%=staticResPath%>/etao-crowdfunding/css/p/me/bind-telephone.css">
    <div class="g-wrap">
        <div class="g-views">
            
    <form action="" data-role="form">
    	<section class="container top-gap">
    		<div class="form-input-vertify">
    			<input type="text" data-role="telephone" name="phoneNo" placeholder="请输入新的手机号"  />
    			<button data-role="getCode" class="radius">获取验证码</button>
    		</div>
    	</section>
    	<section class="container">
    		<div class="form-input">
    			<input type="text" placeholder="请输入短信验证码" name="veriCode" data-role="code" />
    		</div>
    	</section>
    	<section class="container top-gap">
    		<div class="btn-toggle">
    			<button data-role="submit" disabled>绑定</button>
    		</div>
    	</section>
        <input type="hidden" name="checkKey" data-role="checkKey" value="<%=checkKey%>"/>
    </form>

        </div>
    </div>
    <!-- error: point:pagejs is not defined; modname islayout/normal-flexible -->
    <script src="<%=staticResPath%>/etao-crowdfunding/js/p/me/bind-telephone.js"></script>