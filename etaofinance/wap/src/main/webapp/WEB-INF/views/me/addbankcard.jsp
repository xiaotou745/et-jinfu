<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.entity.PublicProvinceCity" %>
<%@page import="java.util.List" %>
<%
	//网站跟地址
	String basePath = PropertyUtils.getProperty("java.wap.url");
	//静态资源跟地址
	String staticResPath = PropertyUtils.getProperty("staticResourceUrl");
	
	String truename= (String)request.getAttribute("truename");	
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
			<input type="text" value="<%=truename %>" disabled />
		</div>
		<div class="label-input" data-role="bank">
			<label>银行</label>
			<input class="highlight" type="text" value="农业银行 >" disabled/>
		</div>
		<div class="label-input">
			<label>卡号</label>
			<input type="tel" name="cardno" data-role="cardno" placeholder="请输入银行卡号" />
		</div>
	</section>
	<section class="container top-gap">
		<div class="btn-toggle">
			<button disabled data-role="submit">确认</button>
		</div>
	</section>
	<input type="hidden" name="bankid" data-role="bankid" value="1"/>
	<input type="hidden" name="bankname" data-role="bankname" value="农业银行"/>
	</form>
	<div class="popup hide">
            <div class="popup-title">
                <div class="close"></div>
            </div>
            <div class="popup-content">
                <div class="content-title">选择银行</div>
                <div class="item active" data-id="1" data-name="农业银行"><span>农业银行</span></div>
                <div class="item" data-id="2" data-name="交通银行"><span>交通银行</span></div>
                <div class="item" data-id="3" data-name="建设银行"><span>建设银行</span></div>
                <div class="item" data-id="4" data-name="招商银行"><span>招商银行</span></div>
            </div>
        </div>


        </div>
    </div>
    <!-- error: point:pagejs is not defined; modname islayout/normal-flexible -->
    <script src="<%=staticResPath%>/etao-crowdfunding/js/p/me/add-bankcard.js"></script>
    