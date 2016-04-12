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
	PagedResponse<ProjectModel> result=(PagedResponse<ProjectModel>)request.getAttribute("result");
	List<ProjectModel> list=result.getResultList();
	
%>
<%
if(list!=null&&list.size()>0)
{
	for(int i=0;i<list.size();i++)
	{
		%>		
<div class="container-one">
    <div class="one-list container">
        <h3><b><%=list.get(i).getProjectname()%></b><span><%=list.get(i).getProjectStatusStr()%></span></h3>
        <p><span><img src="<%=list.get(i).getProjectimage()%>"></span>
        <b><img src="<%=staticResPath%>/etao-crowdfunding/img/p/home/index/index_<%=list.get(i).getTypeid()==1?"6":"7"%>.png"></b>
            <div class="progress">
                <span class="bar" style="width:<%=list.get(i).getSchedule()%>%">
                    		<span class="triangle-bottom"></span>
                <span class="percentage"><%=list.get(i).getSchedule()%>%</span>
                </span>
            </div>
        </p>
        <ul class="list-ul">
            <li><span><%=list.get(i).getInComeStr()%></span><b>年化收益</b></li>
            <li><span><%=list.get(i).getAmountStr()%></span><b>目标金额</b></li>
            <li><span><%=list.get(i).getUnitpriceStr()%></span><b>起投金额</b></li>
        </ul>
    </div>
</div>
		<%
	}
}
%>


