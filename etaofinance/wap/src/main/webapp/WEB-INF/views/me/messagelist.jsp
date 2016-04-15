<%@page import="com.etaofinance.entity.Message"%>
<%@page import="com.etaofinance.core.util.ParseHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.entity.PublicProvinceCity" %>
<%@page import="java.util.List" %>
<%
	//网站跟地址
	String basePath = PropertyUtils.getProperty("java.wap.url");
	//静态资源跟地址
	String staticResPath = PropertyUtils.getProperty("staticResourceUrl");
	List<Message> list=(ArrayList<Message>)request.getAttribute("list");
%>


 <link rel="stylesheet" href="<%=staticResPath%>/etao-crowdfunding/css/p/me/message-list.css">
    <div class="g-wrap">
        <div class="g-views">
        <%
        if(list!=null&&list.size()>0)
        {
        	for(int i=0;i<list.size();i++)
        	{
        		Message m=list.get(i);
        	%>
        		    <a href="<%=basePath%>/me/messagedetail?id=<%=m.getId()%>">  
					<section class="container bg">	
						<div class="<%=m.getIsread()?"message-title":"message-title new"%>">
							<div class="title-wrap">
								<span class="title"><%=m.getMsghead().length()>9?m.getMsghead().substring(0,6)+"...":m.getMsghead()%></span>
								<span class="mark"></span>
								<span class="separator"></span>
								<span class="type"><%=m.getMsgtype()==1?"系统消息":""%></span>
							</div>
							<span class="time"><%=ParseHelper.ToDateString(m.getCreatetime())%></span>
						</div>
						<div class="message-content">
							<%=m.getContent().length()>32?m.getContent().substring(0,29)+"...":m.getContent()%>
						</div>
					</section>
					</a>
        	<%}
         }
        else{
        %>暂无消息<%
        }%>
        </div>
    </div>
    <script src="<%=staticResPath%>/etao-crowdfunding/js/p/me/message-list.js"></script>

