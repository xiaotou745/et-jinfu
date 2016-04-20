<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@page import="com.etaofinance.entity.common.PagedResponse"%>
<%@page import="com.etaofinance.core.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
    <%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.entity.Comment"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%
	String basePath =PropertyUtils.getProperty("java.admin.url");
    SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	PagedResponse<Comment> data = (PagedResponse<Comment>) request.getAttribute("listData");
	List<Comment> list = data.getResultList();
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
			<th>评论人ID</th>
			<th>评论人名称</th>
			<th>评论内容</th>
			<th>评论时间</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<%
			for (int i = 0; i < list.size(); i++) {
		%>
		<tr>
			<td><%=list.get(i).getId() %></td>
			<td><%=list.get(i).getMemberid()%></td>
				<td><%=list.get(i).getUsername()%></td>
			<td><%=list.get(i).getContent().length()>8?list.get(i).getContent().substring(0,8):list.get(i).getContent()%></td>
			<td><%=dateFormater.format(list.get(i).getCreatetime()) %></td>	
				<td><a href="javascript:void(0);" onclick="delComment(<%=list.get(i).getId() %>)">删除</a></td>	
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