<%@page import="com.etaofinance.core.util.ParseHelper"%>
<%@page import="com.etaofinance.core.enums.ProjectStatus"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.entity.PublicProvinceCity" %>
<%@page import="com.etaofinance.entity.domain.ProjectFavoriteDM" %>
<%@page import="java.util.List" %>
<%
	//网站跟地址
	String basePath = PropertyUtils.getProperty("java.wap.url");
	//静态资源跟地址
	String staticResPath = PropertyUtils.getProperty("staticResourceUrl");
	List<ProjectFavoriteDM> list=(ArrayList<ProjectFavoriteDM>)request.getAttribute("list");
	String imgurl=PropertyUtils.getProperty("ImageShowPath")+"/";
%>

<link rel="stylesheet" href="<%=staticResPath%>/etao-crowdfunding/css/p/me/project-concern.css">
    <div class="g-wrap">
        <div class="g-views">
          <%if(list!=null&&list.size()>0)
        	{
        	  for(int i=0;i<list.size();i++)
        	  {
        		  %>
				<section class="container bg">
				<div class="title">
					<div>关注时间：<%=list.get(i).getCreatetime()%></div>
					<i class="<%=list.get(i).getProjectStatus()==3?"fall":"win"%>"><%=ProjectStatus.getEnum(list.get(i).getProjectStatus()).desc()%></i>
				</div>
				<div class="item">
					<div><img src="<%=imgurl+list.get(i).getProjectImage()%>" alt=""></div>
						<dl>
							<dt><%=list.get(i).getProjectName()%></dt>
							<dd><span>上线时间：</span><%=ParseHelper.ToDateString(list.get(i).getProjectBeginDate())%></dd>
						</dl>
					</div>
					<div class="offer">
						<ul>
							<li>直接认购</li>
							<li><i></i>取消关注</li>
						</ul>
				</div>
				</section>
        		  <%
        		}
        	}%>  
        </div>
    </div>
    <script src="<%=staticResPath%>/etao-crowdfunding/js/p/me/project-concern.js"></script>