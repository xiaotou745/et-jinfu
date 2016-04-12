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
	boolean isReal=member.getLevel()>0;//是否实名认证
	boolean isHas=(boolean)request.getAttribute("isHas");//是否有已提交的申请
%>
<%
	//没有实名认证
	if(!isReal)
	{%>
		console.log('没有实名认证');
	<%}
	if(isHas)//已经有在认证的信息
	{%>
		console.log('已经有待审核的认证信息');
	<%}
%>

<link rel="stylesheet" href="<%=staticResPath%>/etao-crowdfunding/css/p/me/certification-investor.css">
    <div class="g-wrap">
        <div class="g-views">
            
        <section class="container bg">
            <div class="lead">
                <p>您是符合以下条件之一的自然人投资者</p>
                <ul>
                    <li>
                        <i class="select"></i>我的金融资产不低于300万人民币</li>
                    <li><i class="select active"></i>我最近三年年均收入不低于50万人民币</li>
                    <li>
                        <i class="select"></i>
                        </i>我是专业的风险投资人</li>
                </ul>
            </div>
        </section>
        <section class="container bg">
            <div class="label-input">
                <label>所在公司</label>
                <input type="text" placeholder="请填写您所在的公司" data-role="company"/>
            </div>
            <div class="label-input">
                <label>职位头衔</label>
                <input type="text" placeholder="请填写您的职位/头衔" data-role="position"/>
            </div>
        </section>
        <section class="container bg">
            <div class="pledge">
                <i></i>
                <p>我承诺以上登记的所有信息属实，并对虚假信息产生的一切后果负责。我同意签署<span>《用户服务协议》</span>和<span>《风险揭示书》</span></p>
            </div>
            <div class="btn-toggle">
                <button disabled data-role="submit">提交</button>
            </div>
        </section>
    
        </div>
    </div>
    <!-- error: point:pagejs is not defined; modname islayout/normal-flexible -->
    <script src="<%=staticResPath%>/etao-crowdfunding/js/p/me/certification-investor.js"></script>


