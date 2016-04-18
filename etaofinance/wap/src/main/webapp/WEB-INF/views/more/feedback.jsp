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
	Member member=(Member)request.getAttribute("member");
	String name=member.getTruename()==null?"":member.getTruename();
	String email=member.getEmail()==null?"":member.getEmail();
	String phone=member.getPhoneno()==null?"":member.getPhoneno();
	
%>

 <link rel="stylesheet" href="<%=staticResPath%>/etao-crowdfunding/css/p/more/feedback.css">
    <div class="g-wrap">
        <div class="g-views">
            
	<form action="" data-role="form">
		<section class="container top-gap">
			<textarea class="feedback" placeholder="尽情吐槽~" name="description" data-role="description"></textarea>
		</section>
		<section class="container">
			<div class="tips">您还可以输入<span data-role="text-count">500</span>字</div>
		</section>
		<section class="container ">
			<div class="label-input">
				<label>联系人</label>
				<input type="text" name="contacts" data-role="contacts" value="<%=name%>"/>
			</div>
		</section>
		<section class="container ">
			<div class="label-input">
				<label>联系电话</label>
				<input type="text" name="phoneno" data-role="phoneno" value="<%=phone%>"/>
			</div>
		</section>
		<section class="container ">
			<div class="label-input">
				<label>联系邮箱</label>
				<input type="text" name="email" data-role="email" value="<%=email%>"/>
			</div>
		</section>
		<section class="container top-gap">
			<div class="btn-toggle">
				<button data-role="submit" disabled>提交</button>
			</div>
		</section>
	</form>

        </div>
    </div>
    <!-- error: point:pagejs is not defined; modname islayout/normal-flexible -->
    <script src="<%=staticResPath%>/etao-crowdfunding/js/p/more/feedback.js"></script>
