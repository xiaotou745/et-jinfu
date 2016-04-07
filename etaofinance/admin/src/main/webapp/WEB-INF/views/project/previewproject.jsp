<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.etaofinance.entity.ProjectStrategy"%>
<%@page import="com.etaofinance.entity.ProjectImage"%>
<%@page import="com.etaofinance.entity.Project"%>
<%@page import="java.util.List"%>
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
			<td>项目名</td>
			<td colspan="5"></td>
		</tr>
		<tr>
			<td>融资金额</td>
			<td></td>
			<td>份数</td>
			<td></td>
			<td>每份金额</td>
			<td></td>
		</tr>
		<tr>
			<td>地区</td>
			<td colspan="5"></td>
		</tr>
		<tr>
			<td>详细地址</td>
			<td colspan="5"></td>
		</tr>
		<tr>
			<td>年化收益率</td>
			<td colspan="5"></td>
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
			<td colspan="5"></td>
		</tr>
		<tr>
			<td>项目概况</td>
			<td colspan="5"></td>
		</tr>
		<tr>
			<td>回报说明</td>
			<td colspan="5"></td>
		</tr>
	</tbody>
</table>
<%
	}
%>