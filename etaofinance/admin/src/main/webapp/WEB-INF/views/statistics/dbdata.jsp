<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@page import="com.etaofinance.entity.common.PagedResponse"%>
<%@page import="java.util.ArrayList"%>
    <%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.entity.domain.ToDoDataStatistics"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%
	String basePath =PropertyUtils.getProperty("java.admin.url");
    SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	List<ToDoDataStatistics> list = (List<ToDoDataStatistics>) request.getAttribute("listData");
%>
<% if(list==null||list.size()==0) 
{%>
	=====暂无数据=====
<%}else{%>
<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<tbody>
		<tr>
		<td>待审核跟投人</td>
		<td><%=list.get(0).getGtuser() %> 位</td>
		</tr>
		<tr>
		<td>待审核项目</td>
		<td><%=list.get(0).getDshProject() %> 个</td>
		</tr>
		<tr>
		<td>待审核领投人</td>
		<td><%=list.get(0).getLtuser() %> 位</td>
		</tr>
		<tr>
		<td>待审核提现</td>
		<td><%=list.get(0).getDshtx()%> 个</td>
		</tr>
		<tr>
		<td>订单成功数</td>
		<td><%=list.get(0).getOrdercount()%> 个</td>
		</tr>
		<tr>
		<td>意见反馈数</td>
		<td><%=list.get(0).getQacount()%> 个</td>
		</tr>
	</tbody>
</table>
<%} %>