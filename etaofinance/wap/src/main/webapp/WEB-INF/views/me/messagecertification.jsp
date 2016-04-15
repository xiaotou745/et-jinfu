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
	String phone=member.getPhoneno();
	String sString=phone.substring(0,phone.length()-(phone.substring(3)).length())+"****"+phone.substring(7);
%>

<link rel="stylesheet" href="<%=staticResPath%>/etao-crowdfunding/css/p/me/message-certification.css">
    <div class="g-wrap">
        <div class="g-views">
            
    <form data-role="form">
        <section class="container top-gap">
            <div class="form-input-vertify">
                <input type="tel" name="telephone" data-role="telephone" value="<%=sString%>"  readonly maxlength="11" />
                <input type="hidden" id="hidephone"  value="<%=phone%>"  readonly maxlength="11" />
                <button data-role="getCode" class="radius">获取验证码</button>
            </div>
        </section>
        <section class="container">
            <div class="form-input">
                <input type="text" placeholder="请输入短信验证码" data-role="code" />
            </div>
        </section>
        <section class="container top-gap">
            <div class="btn-toggle">
                <button data-role="submit" disabled>绑定</button>
            </div>
        </section>
    </form>

        </div>
    </div>
    <!-- error: point:pagejs is not defined; modname islayout/normal-flexible -->
    <script src="<%=staticResPath%>/etao-crowdfunding/js/p/me/message-certification.js"></script>