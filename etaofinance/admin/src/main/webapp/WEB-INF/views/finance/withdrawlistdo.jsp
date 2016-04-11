<%@page import="com.etaofinance.core.util.ParseHelper"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@page import="com.etaofinance.entity.common.PagedResponse"%>
<%@page import="com.etaofinance.core.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
    <%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.entity.Project"%>
<%@page import="com.etaofinance.core.enums.ProjectStatus"%>
<%@page import="java.util.List"%>
<%
	String basePath =PropertyUtils.getProperty("java.admin.url");
%>

<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr>
			<th width="5%">编号</th>
			<th>项目名称</th>
			<th>融资进度</th>
			<th>投资人数</th>
			<th>关注人数</th>
			<th>状态</th>
			<th>融资开始时间</th>
			<th>融资结束时间</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>

		<%
			PagedResponse<Project> data = (PagedResponse<Project>) request.getAttribute("listData");
			List<Project> list = data.getResultList();
			if (list == null) {
				list = new ArrayList<Project>();
			}
			for (int i = 0; i < list.size(); i++) {
		%>
		<tr>
			<td><%=(i+1)%></td>
			<td><%=list.get(i).getProjectname()%></td>
			<td><%=list.get(i).getSchedule()%>%</td>
			<td><%=list.get(i).getInvestmentnumber()%></td>
			<td><%=list.get(i).getFollownumber()%></td>
			<td><%=ProjectStatus.getEnum(list.get(i).getProjectstatus()).desc()%></td>
			<td><%=ParseHelper.ToDateString(list.get(i).getStarttime(), "") %></td>
			<td><%=ParseHelper.ToDateString(list.get(i).getEndtime(), "") %></td>
			<td><a href="<%=basePath%>/?projectid=<%=list.get(i).getId()%>">详情</a>
			<a href="javascript:void(0)" onclick="setstatus(<%=list.get(i).getId()%>,1)">修改状态</a>
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