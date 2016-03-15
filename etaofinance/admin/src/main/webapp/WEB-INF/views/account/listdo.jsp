<%@page import="com.etaofinance.core.util.ParseHelper"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@page import="com.etaofinance.entity.common.PagedResponse"%>
<%@page import="com.etaofinance.core.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
    <%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.entity.AccountInfo"%>
<%@page import="java.util.List"%>
<%
	String basePath =PropertyUtils.getProperty("java.admin.url");
%>
<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr>
			<th width="5%">编号</th>
			<th>真实姓名</th>
			<th>登录名称</th>
			<th>最后操作时间</th>
			<th>启用状态</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>

		<%
			PagedResponse<AccountInfo> data = (PagedResponse<AccountInfo>) request
					.getAttribute("listData");
			List<AccountInfo> list = data.getResultList();
			if (list == null) {
				list = new ArrayList<AccountInfo>();
			}
			for (int i = 0; i < list.size(); i++) {
		%>
		<tr>
			<td><%=(i+1)%></td>
			<td><%=list.get(i).getTruename()%></td>
			<td><%=list.get(i).getLoginname()%></td>
			<td><%=ParseHelper.ToDateString(list.get(i).getOpttime(), "") %></td>
			<td><%=list.get(i).getIslock() ? "×" : "√"%></td>
			<td><a href="javascript:void(0)" onclick="modify(<%=list.get(i).getId()%>)">编辑</a>
			<a href="javascript:void(0)"
				onclick="setauth(<%=list.get(i).getId()%>)">分配权限</a>
			</td>
		</tr>
		<%
			}
		%>
	</tbody>
</table>
<%=PageHelper.getPage(data.getPageSize(),
					data.getCurrentPage(), data.getTotalRecord(),
					data.getTotalPage())%>