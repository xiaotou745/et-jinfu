<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@page import="com.etaofinance.entity.common.PagedResponse"%>
<%@page import="com.etaofinance.core.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
    <%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.entity.ProjectSubscription"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%
	String basePath =PropertyUtils.getProperty("java.admin.url");
    SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	PagedResponse<ProjectSubscription> data = (PagedResponse<ProjectSubscription>) request.getAttribute("listData");
	List<ProjectSubscription> list = data.getResultList();
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
			<th>姓名</th>
			<th>手机号</th>
			<th>邮箱</th>
			<th>投资金额</th>
			<th>身份证</th>
			<th>投资时间</th>
			<th>状态</th>
		</tr>
	</thead>
	<tbody>
		<%
			for (int i = 0; i < list.size(); i++) {
		%>
		<tr>
			<td><%=list.get(i).getId() %></td>
			<td><%=list.get(i).getName()%></td>
			<td><%=list.get(i).getPhoneno()%></td>
			<td><%=list.get(i).getEmail()%></td>
			<td><%=list.get(i).getAmount() %></td>
			<td><%=list.get(i).getIdcard() %></td>
			<td><%=dateFormater.format(list.get(i).getCreatetime()) %></td>
			<td><%=list.get(i).getRefundstatus() ==0?"未退款":"已退款" %></td>	
		</tr>
		<%
			}
		%>
	</tbody>
</table>
<%} %>
<%=PageHelper.getPage(data.getPageSize(),data.getCurrentPage(), data.getTotalRecord(),data.getTotalPage())%>
<script>
	var isEmNull = function(v){
		if(!v){
			return '--'
		}
		return v;
	}
</script>