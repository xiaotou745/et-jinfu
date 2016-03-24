<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@page import="com.etaofinance.entity.common.PagedResponse"%>
<%@page import="com.etaofinance.core.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
    <%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.entity.domain.MemberApplyInvestModel"%>
<%@page import="java.util.List"%>
<%
	String basePath =PropertyUtils.getProperty("java.admin.url");
	PagedResponse<MemberApplyInvestModel> data = (PagedResponse<MemberApplyInvestModel>) request.getAttribute("listData");
	List<MemberApplyInvestModel> list = data.getResultList();
%>
<% if(data.getResultList()==null||data.getResultList().size()==0) 
{%>
	=====暂无数据=====
<%} else{%>
<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr>
			<th width="5%">ID</th>
			<th>用户名</th>
			<th>手机号</th>
			<th>邮箱</th>
			<th>申请时间</th>
			<th>审核状态</th>
			<th>拒绝理由</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>

		<%
			for (int i = 0; i < list.size(); i++) {
		%>
		<tr>
			<td><%=list.get(i).getId() %></td>
			<td><%=list.get(i).getMemberName()%></td>
			<td><%=list.get(i).geteMail() %></td>
			<td><%=list.get(i).getApplyDate() %></td>
			<td><%=list.get(i).getAuditStatusString() %></td>
			<td><%=list.get(i).getRefuseReasion() %></td>
			<td>操作</td>
		</tr>
		<%
			}
		%>
	</tbody>
</table>
<%} %>
<%=PageHelper.getPage(data.getPageSize(),
					data.getCurrentPage(), data.getTotalRecord(),
					data.getTotalPage())%>