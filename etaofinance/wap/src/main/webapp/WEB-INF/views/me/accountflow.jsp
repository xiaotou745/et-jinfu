<%@page import="java.util.ArrayList"%>
<%@page import="com.etaofinance.entity.domain.BalanceRecordDM"%>
<%@page import="com.etaofinance.entity.MemberOther"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.core.util.ParseHelper"%>
<%@page import="com.etaofinance.entity.PublicProvinceCity" %>
<%@page import="java.util.List" %>
<%
	//网站跟地址
	String basePath = PropertyUtils.getProperty("java.wap.url");
	//静态资源跟地址
	String staticResPath = PropertyUtils.getProperty("staticResourceUrl");
	List<BalanceRecordDM> list=(ArrayList<BalanceRecordDM>)request.getAttribute("list");
%>


<link rel="stylesheet" href="<%=staticResPath%>/etao-crowdfunding/css/p/me/account-flow.css">
    <div class="g-wrap">
        <div class="g-views">
            
	<section class="container bg">
	<%if(list!=null&&list.size()>0)
		{
		for(int i=0;i<list.size();i++)
		{
			%>
			<div class="text">
			<div class="summary">
				<div><%=list.get(i).getTypeName() %></div>
				<span><%=ParseHelper.ToDateString(list.get(i).getOpttime())%></span>
			</div>
			<div class="detail">
				<div>余额：<b><%=ParseHelper.digitsNum(list.get(i).getAfteramount(), 2)%> </b></div>
				<span><%=ParseHelper.digitsNum(list.get(i).getAmount(), 2)%> </span>
			</div>			
			</div>
			<%
		}
		}
		else
		{
			%>
			
			暂无明细
			<%
		}%>
	</section>

        </div>
    </div>
    <script src="<%=staticResPath%>/etao-crowdfunding/js/p/me/account-flow.js"></script>

