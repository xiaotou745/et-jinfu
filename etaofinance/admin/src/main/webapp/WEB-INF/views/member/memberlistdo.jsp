<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@page import="com.etaofinance.entity.common.PagedResponse"%>
<%@page import="com.etaofinance.core.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
    <%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.entity.domain.MemberModel"%>
<%@page import="java.util.List"%>
<%
	String basePath =PropertyUtils.getProperty("java.admin.url");
	PagedResponse<MemberModel> data = (PagedResponse<MemberModel>) request.getAttribute("listData");
	List<MemberModel> list = data.getResultList();
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
			<th>账户余额</th>
			<th>最后登录时间</th>
			<th>用户类型</th>
			<th>项目数</th>
			<th>投资数</th>
			<th>关注数</th>
			<th>状态</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<%
			for (int i = 0; i < list.size(); i++) {
		%>
		<tr>
			<td><%=list.get(i).getId() %></td>
			<td><%=list.get(i).getMemberName() %></td>
			<td><%=list.get(i).getPhoneNo()%></td>
			<td><%=list.get(i).getMail() %></td>
			<td><%=list.get(i).getBalance() %></td>
			<td><%=list.get(i).getLastLoginTime() %></td>
			<td><%=list.get(i).getMemberTypeString() %></td>
			<td><%=list.get(i).getProjectCount() %></td>
			<td><%=list.get(i).getInvestCount() %></td>
			<td><%=list.get(i).getAttentionCount() %></td>
			<td><%=list.get(i).getMemberStatusString() %></td>		
			<td><a href="<%=basePath%>/member/memberdetail?id=<%=list.get(i).getId()%>" target="_Blank">详情</a></td>
		</tr>
		<%
			}
		%>
	</tbody>
</table>
<%} %>
<%=PageHelper.getPage(data.getPageSize(),data.getCurrentPage(), data.getTotalRecord(),data.getTotalPage())%>