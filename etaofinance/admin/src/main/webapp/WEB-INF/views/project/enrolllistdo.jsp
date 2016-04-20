<%@page import="com.etaofinance.core.util.ParseHelper"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.etaofinance.entity.common.PagedResponse"%>
<%@page import="com.etaofinance.core.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.entity.ProjectEnroll"%>
<%@page import="java.util.List"%>
<%@page import="com.etaofinance.core.util.Config"%>
<%
	String basePath = PropertyUtils.getProperty("java.admin.url");
%>

<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr>
			<th width="5%">项目报名ID</th>
			<th>项目名称</th>
			<th>联系人</th>
			<th>电话</th>
			<th>邮箱</th>
			<th>行业</th>
			<th>提交时间</th>
			<th>审核状态</th>
			<th>拒绝理由</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<%
			PagedResponse<ProjectEnroll> data = (PagedResponse<ProjectEnroll>) request
					.getAttribute("listData");
		
			List<ProjectEnroll> list = data.getResultList();
			
			if (list == null) {
				list = new ArrayList<ProjectEnroll>();
			}
			
			for (int i = 0; i < list.size(); i++) {
		
		int status = list.get(i).getStatus();
		%>
		<tr>
			<td><%=list.get(i).getId()%></td>
			<td><%=list.get(i).getTitle()%></td>
			<td><%=list.get(i).getContacts()%></td>
			<td><%=list.get(i).getPhoneno()%></td>
			<td><%=list.get(i).getEmail()%></td>
			<td><%=list.get(i).getOwnedindustry()%></td>
			<td><%=ParseHelper.ToDateString(list.get(i).getCreatetime(),"")%></td>
			<%
				if (0 == status) {
			%>
			<td>未审核</td>
			<%
				} else if (1 == status) {
			%><td>审核通过</td>
			<%
				} else {
			%>
			<td>未审核通过</td>
			<%
				}
			%>
			<td><%=list.get(i).getRefuseremark()%></td>
			<td><a href="<%=Config.ImgShowUrl+"/"+list.get(i).getBusinessplanurl()%>" target="_blank">下载</a>
			
			
			<%
			if(0==status){
			%>
			<a data-toggle="modal"  data-target="#myModal" id="apply" onclick="setHidId(<%=list.get(i).getId()%>)">审核</a>
			<%} %>
			
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