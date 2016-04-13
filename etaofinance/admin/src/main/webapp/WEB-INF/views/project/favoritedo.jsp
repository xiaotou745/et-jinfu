<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@page import="com.etaofinance.entity.common.PagedResponse"%>
<%@page import="com.etaofinance.core.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
    <%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.entity.domain.ProjectFavoriteInvestModel"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%
	String basePath =PropertyUtils.getProperty("java.admin.url");
    SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	PagedResponse<ProjectFavoriteInvestModel> data = (PagedResponse<ProjectFavoriteInvestModel>) request.getAttribute("listData");
	List<ProjectFavoriteInvestModel> list = data.getResultList();
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
			<th>投资项目数</th>
			<th>身份证</th>
			<th>用户名</th>
			<th>关注时间</th>
		</tr>
	</thead>
	<tbody>
		<%
			for (int i = 0; i < list.size(); i++) {
		%>
		<tr>
			<td><%=list.get(i).getId() %></td>
			<td><%=list.get(i).getTruename()==null?"--":list.get(i).getTruename()%></td>
			<td><%=list.get(i).getPhoneno()==null?"--":list.get(i).getPhoneno()%></td>
			<td><%=list.get(i).getEmail()==null?"--":list.get(i).getEmail()%></td>
			<td><%=list.get(i).GetProjectCount()==null?"--":list.get(i).GetProjectCount() %></td>
			<td><%=list.get(i).getIdcard()==null?"--":list.get(i).getIdcard() %></td>
			<td><%=list.get(i).getUsername()==null?"--":list.get(i).getUsername() %></td>
			<td><%=dateFormater.format(list.get(i).getCreatetime()) %></td>	
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