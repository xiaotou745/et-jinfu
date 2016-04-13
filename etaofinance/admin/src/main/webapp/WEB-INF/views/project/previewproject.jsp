<%@page import="com.etaofinance.core.util.Config"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.etaofinance.entity.ProjectStrategy"%>
<%@page import="com.etaofinance.entity.ProjectImage"%>
<%@page import="com.etaofinance.entity.Project"%>
<%@page import="com.etaofinance.core.enums.ProjectImageTypeEnum"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%
	String basePath = PropertyUtils.getProperty("java.admin.url");
	Project project = (Project) request.getAttribute("project");
%>
<%
	if (project == null) {
%>
======暂无数据=====
<%
	} else {
		List<ProjectStrategy> proStrList = (List<ProjectStrategy>) request.getAttribute("proStrList");
		List<ProjectImage> proImgList = (List<ProjectImage>) request.getAttribute("proImgList");
		Map<Long,String> cityMap = (Map<Long,String>) request.getAttribute("cityMap");
%>
<table class="table table-condensed table-bordered">
	<tbody>
		<tr>
			<td>项目名</td>
			<td colspan="2"><%=project.getProjectname()%></td>
			<td>项目类型</td>
			<td colspan="2"><%=project.getTypeIdString()%></td>
		</tr>
		<tr>
			<td>一句话简介</td>
			<td colspan="5"><%=project.getDescription()%></td>
		</tr>
		<tr>
			<td>融资金额</td>
			<td><%=project.getAmount()%></td>
			<td>份数</td>
			<td><%=project.getFenshu()%></td>
			<td>每份金额</td>
			<td><%=project.getUnitprice()%></td>
		</tr>
		<tr>
			<td>地区</td>
			<td colspan="5"><%=cityMap.get(project.getProvincecode())%><%=cityMap.get(project.getCitycode())%><%=cityMap.get(project.getAreacode())%></td>
		</tr>
		<tr>
			<td>详细地址</td>
			<td colspan="5"><%=project.getAddress()%></td>
		</tr>
		<tr>
			<td>年化收益率</td>
			
			<%if(proStrList.get(1).getValue()>0){ %>
				<td colspan="5"><%=proStrList.get(0).getValue()+"-"+proStrList.get(1).getValue() %></td>			
			<%} else{%>
				<td colspan="5"><%=proStrList.get(0).getValue()%></td>
			<%} %>
		</tr>
		<tr>
			<td>领投人</td>
			<td colspan="2"></td>
			<td>领投金额</td>
			<td colspan="2"></td>
		</tr>
		<tr>
			<td>领投人头像</td>
			<td colspan="5"></td>
		</tr>
		<tr>
			<td>项目图</td>
			<td colspan="5"><img src='<%=Config.ImgShowUrl+"/"+project.getProjectimage() %>' alt="项目图"></td>
		</tr>
		<tr>
			<td>项目概况</td>
			<td colspan="5">
			<% for(int i=0;i<proImgList.size();i++){ if(proImgList.get(i).getTypeid()==ProjectImageTypeEnum.ProjectBasicDesPC.value()){ %>
				<img src='<%=Config.ImgShowUrl+"/"+proImgList.get(i).getUrl() %>' alt="项目概况PC图">
			<%} }%>
			</td>
		</tr>
		<tr>
			<td>回报说明</td>
			<td colspan="5">
			<% for(int i=0;i<proImgList.size();i++){ if(proImgList.get(i).getTypeid()==ProjectImageTypeEnum.ProjectRtnDesPC.value()){ %>
				<img src='<%=Config.ImgShowUrl+"/"+proImgList.get(i).getUrl() %>' alt="项目回报说明PC图">
			<%} }%>
			</td>
		</tr>
	</tbody>
</table>
<%
	}
%>