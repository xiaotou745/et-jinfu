<%@page import="com.etaofinance.core.util.ParseHelper"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.etaofinance.entity.common.PagedResponse"%>
<%@page import="com.etaofinance.core.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.entity.BalanceRecord"%>
<%@page import="com.etaofinance.core.enums.ProjectStatus"%>
<%@page import="java.util.List"%>
<%
	String basePath = PropertyUtils.getProperty("java.admin.url");
%>
<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr>
			<th width="5%">付款ID</th>
			<th>订单号</th>
			<th>姓名</th>
			<th>金额</th>
			<th>创建时间</th>
			<th>所属项目</th>
			<th>是否支付</th>
		</tr>
	</thead>
	<tbody>

		<%
			PagedResponse<BalanceRecord> data = (PagedResponse<BalanceRecord>) request
					.getAttribute("listData");
			List<BalanceRecord> list = data.getResultList();
			if (list == null) {
				list = new ArrayList<BalanceRecord>();
			}
			for (int i = 0; i < list.size(); i++) {
		%>

		<tr>
			<td><%=list.get(i).getId()%></td>
			<td><%=list.get(i).getRelationno()%></td>
			<td><%=list.get(i).getOptname()%></td>
			<td><%=list.get(i).getAmount()%></td>
			<td><%=ParseHelper
						.ToDateString(list.get(i).getOpttime(), "")%></td>
			<td><%=list.get(i).getRemark()%></td>
			<td><%="是"%></td>
		</tr>

		<%
			}
		%>
	</tbody>
</table>

<%=PageHelper.getPage(data.getPageSize(),
					data.getCurrentPage(), data.getTotalRecord(),
					data.getTotalPage())%>
