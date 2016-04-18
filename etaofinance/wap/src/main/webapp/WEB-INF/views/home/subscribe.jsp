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
	//当前用户
	Member member=request.getAttribute("member")==null?null:(Member)request.getAttribute("member");
	//项目详情
	ProjectModel detaiModel=(ProjectModel)request.getAttribute("detaiModel");
	Long projectid=(Long)request.getAttribute("projectid");

%>

<link rel="stylesheet" href="<%=staticResPath%>/etao-crowdfunding/css/p/home/subscribe.css">
    <div class="g-wrap">
        <div class="g-views">
            
	<section class="container bg">
		<div class="title">
			<%=detaiModel.getProjectname()%>
		</div>
		<div class="address">
			<i class="m-icon icon-locate"></i>
			<span><%=detaiModel.getProvinceName()+detaiModel.getCityName()%></span>
		</div>
	</section>
	<section class="container bg">
		<div class="content">
			<div class="amount">
				认购金额:<b>￥</b><span>5000</span>
			</div>
			<div class="count">
				<span class="minus">
					<i class="m-icon icon-minus"></i>
				</span>
				<span class="number">5</span>
				<span class="plus">
					<i class="m-icon icon-plus"></i>
				</span>
			</div>
			<div class="remain">
				<span><%=detaiModel.getUnitpriceStr()%>/份</span>
				<span>剩余<b><%=detaiModel.getFenshu()-detaiModel.getRedidueFenshu()%></b>份</span>
			</div>
			<div class="protocol">
				<i class="m-icon icon-approval"></i>
				<span>《风险揭示书》</span>和<span>《有限合伙协议》</span>
			</div>
		</div>
	</section>
	<section class="container bg">
		<div class="info">认购人</div>
		<div class="label-input">
			<label>真实姓名</label>
			<input type="text" value="<%=member.getTruename()%>" readonly />
		</div>
		<div class="label-input">
			<label>身份证号</label>
			<input type="text" value="<%=member.getIdcard()%>" readonly/>
		</div>
		<div class="label-input">
			<label>手机号码</label>
			<input type="text" value="<%=member.getPhoneno()%>" readonly/>
		</div>
	</section>
	<section class="container top-gap">
		<div class="btn-toggle">
			<button>确认支付</button>
		</div>
	</section>

        </div>
    </div>
    <script src="<%=staticResPath%>/etao-crowdfunding/js/p/home/subscribe.js"></script>