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
  <link rel="stylesheet" href="<%=staticResPath%>/etao-crowdfunding/css/p/me/add-bankcard.css">
      
  <div class="g-wrap">
        <div class="g-views">
            
	<form data-role="form">
	<section class="container">
		<div class="tips">
			为保证账户资金安全，只能绑定本人银行卡
		</div>
	</section>
	<section class="container bg">
		<div class="label-input">
			<label>持卡人</label>
			<input type="text" value="宋瑞轩" disabled />
		</div>
		<div class="label-input">
			<label>银行</label>
			<input class="highlight" type="text" value="请选择银行 >" disabled/>
		</div>
		<div class="label-input">
			<label>卡号</label>
			<input type="tel" placeholder="请输入银行卡号" />
		</div>
	</section>
	<section class="container top-gap">
		<div class="btn-toggle">
			<button disabled data-role="submit">确认</button>
		</div>
	</section>
	</form>
	<div class="popup hide">
            <div class="popup-title">
                <div class="close"></div>
            </div>
            <div class="popup-content">
                <div class="content-title">选择银行</div>
                <div class="item active"><span>农业银行</span></div>
                <div class="item"><span>交通银行</span></div>
                <div class="item"><span>建设银行</span></div>
                <div class="item"><span>招商银行</span></div>
            </div>
        </div>


        </div>
    </div>
    <!-- error: point:pagejs is not defined; modname islayout/normal-flexible -->
    <script src="<%=staticResPath%>/etao-crowdfunding/js/p/me/add-bankcard.js"></script>
    