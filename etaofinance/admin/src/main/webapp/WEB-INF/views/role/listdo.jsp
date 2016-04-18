<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.etaofinance.entity.common.PagedResponse"%>
<%@page import="com.etaofinance.core.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.etaofinance.entity.RoleInfo"%>
<%@page import="java.util.List"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%
	String basePath =PropertyUtils.getProperty("java.admin.url");
%>
<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr>
			<th width="5%">ID</th>
			<th>角色名称</th>
			<th>启用状态</th>
			<th>操作人</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>

		<%
		List<RoleInfo> list = (List<RoleInfo>) request.getAttribute("listData");
			for (int i = 0; i < list.size(); i++) {
		%>
		<tr>
			<td><%=list.get(i).getId()%></td>
			<td><%=list.get(i).getRolename()%></td>
			<td><%=list.get(i).getIslock()? "锁定" : "可用"%></td>
			<td><%=list.get(i).getOptname()%></td>
			<td>
			<a href="javascript:void(0)" onclick="modify(<%=list.get(i).getId()%>,<%=list.get(i).getIslock()?1:0%>,'<%=list.get(i).getRolename()%>')">编辑</a>
			<a href="javascript:void(0)"
				onclick="setauth(<%=list.get(i).getId()%>)">分配权限</a></td>
		</tr>
		<%
			}
		%>
	</tbody>
</table>