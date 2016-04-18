<%@page import="com.etaofinance.core.util.ParseHelper"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.etaofinance.entity.common.PagedResponse"%>
<%@page import="com.etaofinance.core.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.entity.ProjectEnroll"%>
<%@page import="java.util.List"%>
<%
	String basePath = PropertyUtils.getProperty("java.admin.url");
%>

<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr>
			<th width="5%">ID</th>
			<th>项目名称</th>
			<th>联系人</th>
			<th>电话</th>
			<th>邮箱</th>
			<th>行业</th>
			<th>提交时间</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<%
			PagedResponse<ProjectEnroll> data = (PagedResponse<ProjectEnroll>) request
					.getAttribute("listData");
		
			List<ProjectEnroll> list = data.getResultList();
			
			if (list == null) {
				list = new ArrayList<ProjectEnroll>();
			}
			
			for (int i = 0; i < list.size(); i++) {
		%>
		<tr>
			<td><%=(i + 1)%></td>
			<td><%=list.get(i).getTitle()%></td>
			<td><%=list.get(i).getContacts()%></td>
			<td><%=list.get(i).getPhoneno()%></td>
			<td><%=list.get(i).getEmail()%></td>
			<td><%=list.get(i).getOwnedindustry()%></td>
			<td><%=ParseHelper.ToDateString(list.get(i).getCreatetime(),
						"")%></td>
			<td><a href="<%=list.get(i).getBusinessplanurl()%>" target="_blank">下载</a></td>
		</tr>
		<%
			}
		%>
	</tbody>
</table>

<%=PageHelper.getPage(data.getPageSize(),
					data.getCurrentPage(), data.getTotalRecord(),
					data.getTotalPage())%>