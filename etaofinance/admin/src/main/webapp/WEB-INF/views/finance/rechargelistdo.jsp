<%@page import="com.etaofinance.core.util.ParseHelper"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.etaofinance.entity.common.PagedResponse"%>
<%@page import="com.etaofinance.core.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.entity.Recharge"%>
<%@page import="com.etaofinance.core.enums.RechargeStatus"%>
<%@page import="com.etaofinance.core.enums.PayType"%>
<%@page import="java.util.List"%>
<%
	String basePath = PropertyUtils.getProperty("java.admin.url");
%>

<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr>
			<th width="5%">序号</th>
			<th>订单号</th>
			<th>姓名</th>
			<th>金额</th>
			<th>支付方式</th>
			<th>创建时间</th>
			<th>是否支付</th>
		</tr>
	</thead>
	<tbody>
		<%
			PagedResponse<Recharge> data = (PagedResponse<Recharge>) request
					.getAttribute("listData");
			List<Recharge> list = data.getResultList();
			if (list == null) {
				list = new ArrayList<Recharge>();
			}
			for (int i = 0; i < list.size(); i++) {
		%>
		<tr>
			<td><%=(i + 1)%></td>
			<td><%=list.get(i).getNo()%></td>
			<td><%=list.get(i).getCreatename()%></td>
			<td><%=list.get(i).getAmount()%></td>
			<td><%=PayType.getEnum(list.get(i).getAccounttype()).desc()%></td>
			<td><%=ParseHelper.ToDateString(list.get(i).getCreatetime(),
						"")%></td>
			<td><%=RechargeStatus.getEnum(list.get(i).getStatus())
						.desc()%></td>
		</tr>
		<%
			}
		%>
	</tbody>
</table>

<%=PageHelper.getPage(data.getPageSize(),
					data.getCurrentPage(), data.getTotalRecord(),
					data.getTotalPage())%>