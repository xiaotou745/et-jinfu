<%@page import="com.etaofinance.core.util.ParseHelper"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@page import="com.etaofinance.entity.common.PagedResponse"%>
<%@page import="com.etaofinance.core.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
    <%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.entity.Project"%>
<%@page import="com.etaofinance.core.enums.ProjectType"%>
<%@page import="com.etaofinance.core.enums.ProjectAuditStatus"%>
<%@page import="java.util.List"%>
<%
	String basePath =PropertyUtils.getProperty("java.admin.url");
%>
<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr>
			<th width="5%">项目id</th>
			<th>项目名称</th>
			<th>融资额</th>
			<th>项目类型</th>
			<th>城市</th>
			<th>年化收益率</th>
			<th>提交时间</th>
			<th>审核状态</th>
			<th>拒绝理由</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>

		<%
			PagedResponse<Project> data = (PagedResponse<Project>) request.getAttribute("listData");
			Map<Long,String> strategyMap = (Map<Long,String>) request.getAttribute("strategyMap");
			Map<Long,String> cityMap = (Map<Long,String>) request.getAttribute("cityMap");
			
			List<Project> list = data.getResultList();
			if (list == null) {
				list = new ArrayList<Project>();
			}
			for (int i = 0; i < list.size(); i++) {
		%>
		<tr>
			<td><%=list.get(i).getId()%></td>
			<td>
			<a target="_blank" href="<%=basePath%>/project/previewproject?id=<%=list.get(i).getId()%>">
			<%=list.get(i).getProjectname()%></a></td>
			<td><%=list.get(i).getAmount()%></td>
			<td><%=ProjectType.getEnum(list.get(i).getTypeid()).desc()%></td>
			<td><%=cityMap.get(list.get(i).getCitycode())%></td>
			<td><%=strategyMap.get(list.get(i).getId())%></td>
			<td><%=ParseHelper.ToDateString(list.get(i).getCreatetime(), "") %></td>
			<td><%=ProjectAuditStatus.getEnum(list.get(i).getAuditstatus()).desc()%></td>
			<td><%=list.get(i).getRefusereasion()%></td>
			<td>
<%-- 			<a target="_blank" href="<%=basePath%>/project/previewproject?id=<%=list.get(i).getId()%>">预览</a> --%>
			<% if(list.get(i).getAuditstatus()!=ProjectAuditStatus.AuditPass.value()){ %>
				<a href="<%=basePath%>/project/projectmodify?id=<%=list.get(i).getId()%>">编辑</a>
			<% } %>			
			<% if(list.get(i).getAuditstatus()==ProjectAuditStatus.WaitAudit.value()){ %>
				<a href="<%=basePath%>/project/projectaudit?id=<%=list.get(i).getId()%>">审核</a>
			<% } else if(list.get(i).getAuditstatus()==ProjectAuditStatus.AuditRefuse.value()) {%>
				<a href="<%=basePath%>/project/projectaudit?id=<%=list.get(i).getId()%>">重审</a>
			<% } else {%>
			<% } %>
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