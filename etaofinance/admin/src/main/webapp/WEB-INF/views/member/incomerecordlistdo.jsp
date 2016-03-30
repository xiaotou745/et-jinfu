<%@page import="com.etaofinance.core.util.ParseHelper"%>
<%@page import="com.etaofinance.entity.BalanceRecord"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.etaofinance.entity.common.PagedResponse"%>
<%@page import="com.etaofinance.core.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="java.util.List"%>
<%
	String basePath = PropertyUtils.getProperty("java.admin.url");
	PagedResponse<BalanceRecord> data = (PagedResponse<BalanceRecord>) request.getAttribute("listData");
%>
<%
	if (data.getResultList() == null
			|| data.getResultList().size() == 0) {
%>
=====暂无数据=====
<%
	} else {
		List<BalanceRecord> list = data.getResultList();
%>
<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr>
			<th width="5%">ID</th>
			<th>变化后金额（元）</th>
			<th>时间</th>
			<th>类型</th>
			<th>余额（元）</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>

		<%
			for (int i = 0; i < list.size(); i++) {
		%>
		<tr>
			<td><%=list.get(i).getId()%></td>
			<td><%=list.get(i).getAfteramount()- list.get(i).getAmount()%></td>
			<td><%=ParseHelper.ToDateString(list.get(i).getOpttime())%></td>
			<td><%=list.get(i).getTypeidString()%></td>
			<td><%=list.get(i).getAmount()%></td>
			<td><a href="#">更多</a></td>
		</tr>
		<%
			}
		%>
	</tbody>
</table>
<%
	}
%>
<%=PageHelper.getPage(data.getPageSize(),
					data.getCurrentPage(), data.getTotalRecord(),
					data.getTotalPage())%>