<%@page import="com.etaofinance.core.enums.ProjectStatus"%>
<%@page import="com.etaofinance.core.util.ParseHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.etaofinance.entity.Project"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.entity.PublicProvinceCity" %>
<%@page import="java.util.List" %>

<%
	//网站跟地址
	String basePath = PropertyUtils.getProperty("java.wap.url");
	//静态资源跟地址
	String staticResPath = PropertyUtils.getProperty("staticResourceUrl");
	List<Project> list=(ArrayList<Project>)request.getAttribute("list");
%>


 <link rel="stylesheet" href="<%=staticResPath%>/etao-crowdfunding/css/p/me/project-lanuch.css">
    <div class="g-wrap">
        <div class="g-views">
            <%
            if(list!=null&&list.size()>0)
            {
            	for(int i=0;i<list.size();i++)
            	{
            		%>
            		<section class="container bg">
						<div class="title">
							<div>发起时间：<%=ParseHelper.ToDateString(list.get(i).getCreatetime())%></div>
							<span></span>
							<i class="<%=list.get(i).getProjectstatus()==3?"fall":"" %>"><%=ProjectStatus.getEnum(list.get(i).getProjectstatus()).desc()%></i>
						</div>
						<div class="item">
							<div><img src="<%=list.get(i).getProjectimage()%>" alt=""></div>
							<dl>
								<dt><%=list.get(i).getProjectname()%></dt>
								<dd><span>融资进度：</span>已完成<%=list.get(i).getSchedule()%>%</dd>
							</dl>
						</div>
					</section>
            		<%
            	}
            }
            %>
        </div>
    </div>
    <!-- error: point:pagejs is not defined; modname islayout/normal-flexible -->
    <script src="<%=staticResPath%>/etao-crowdfunding/js/p/me/project-lanuch.js"></script>


