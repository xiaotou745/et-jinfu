<%@page import="com.etaofinance.core.util.PageHelper"%>
<%@page import="com.etaofinance.entity.ZcSuggestion"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.etaofinance.entity.common.PagedResponse"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="java.util.List"%>

<%
	String basePath =PropertyUtils.getProperty("java.admin.url");	 
PagedResponse<ZcSuggestion> data=(PagedResponse<ZcSuggestion>)request.getAttribute("listData");
List<ZcSuggestion> list = data.getResultList();
if(list == null){
	list = new ArrayList<ZcSuggestion>();
}
%>
<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr>
			<th>ID</th>
			<th>项目名称</th>
			<th>姓名</th>
			<th>电话</th>
			<th>邮箱</th>
		</tr>
	</thead>
	<tbody>
		<%
			for (int i = 0; i < list.size(); i++) {
				ZcSuggestion model=list.get(i);
		%>
		<tr>
			<td><%=model.getId()%></td>
			<td><a href="javascript:void(0)" onclick="show(<%=model.getId()%>)"><%=model.getProjectname()%></a></td>
			<td><%=model.getUsername()%></td>
			<td><%=model.getPhoneno()%></td>
			<td><%=model.getEmail()%></td>
		</tr>
		<%
			}
		%>
	</tbody>
</table>
<%=PageHelper.getPage(data.getPageSize(),
					data.getCurrentPage(), data.getTotalRecord(),
					data.getTotalPage())%>