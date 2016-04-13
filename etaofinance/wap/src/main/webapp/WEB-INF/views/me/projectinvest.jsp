<%@page import="com.etaofinance.core.util.ParseHelper"%>
<%@page import="com.etaofinance.core.enums.ProjectStatus"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.entity.PublicProvinceCity" %>
<%@page import="com.etaofinance.entity.domain.ProjectSubscriptionDM" %>
<%@page import="java.util.List" %>
<%
	//网站跟地址
	String basePath = PropertyUtils.getProperty("java.wap.url");
	//静态资源跟地址
	String staticResPath = PropertyUtils.getProperty("staticResourceUrl");

	List<ProjectSubscriptionDM> list=(ArrayList<ProjectSubscriptionDM>)request.getAttribute("list");
%>
<link rel="stylesheet" href="<%=staticResPath%>/etao-crowdfunding/css/p/me/project-invest.css">
    <div class="g-wrap">
        <div class="g-views">
            
<%
if(list!=null&&list.size()>0)
{
for(int i=0;i<list.size();i++) 
{%>
<section class="container bg">
<div class="title">
<div>订单号：<%=list.get(i).getId()%></div>
<span><%=list.get(i).getRefundstatus()==1?"已退款":""%></span>
<i class="<%=list.get(i).getProjectStatus()==5?"fall":""%>"><%=ProjectStatus.getEnum(list.get(i).getProjectStatus()).desc()%></i>
</div>
<div class="item">
<div><img src="<%=list.get(i).getProjectImage()%>" alt=""></div>
<dl>
	<dt><%=list.get(i).getProjectName()%></dt>
	<dd><span>认购金额：</span><%=list.get(i).getAmount()%></dd>
	<dd><span>认购时间：</span><%=ParseHelper.ToDateString(list.get(i).getCreatetime())%></dd>
</dl>
</div>
<%
if(list.get(i).getRefundstatus()==1)
	%>
	<div><a href="#">查看退款详情></a></div>
	<% 
%>
</section>

<%}
}%>



        </div>
    </div>
    <!-- error: point:pagejs is not defined; modname islayout/normal-flexible -->
    <script src="<%=staticResPath%>/etao-crowdfunding/js/p/me/project-invest.js"></script>
