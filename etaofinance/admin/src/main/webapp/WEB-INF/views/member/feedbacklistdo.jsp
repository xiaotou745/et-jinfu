<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@page import="com.etaofinance.entity.common.PagedResponse"%>
<%@page import="com.etaofinance.core.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
    <%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.entity.FeedBack"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%
	String basePath =PropertyUtils.getProperty("java.admin.url");
    SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	PagedResponse<FeedBack> data = (PagedResponse<FeedBack>) request.getAttribute("listData");
	List<FeedBack> list = data.getResultList();
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
			<th>反馈内容</th>
			<th>提交人</th>
			<th>电话</th>
			<th>邮箱</th>
			<th>操作时间</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<%
			for (int i = 0; i < list.size(); i++) {
		%>
		<tr>
			<td><%=list.get(i).getId() %></td>
			<td><%=list.get(i).getDescription().length()>8?list.get(i).getDescription().substring(0, 8)+"..":list.get(i).getDescription() %></td>
			<td><%=list.get(i).getCreatename()%></td>
			<td><%=list.get(i).getPhoneno() %></td>
			<td><%=list.get(i).getEmail() %></td>
			<td><%=dateFormater.format(list.get(i).getCreatetime()) %></td>	
			<td><a href="javascript:void(0)" onclick="detail('<%=list.get(i).getDescription() %>')">详情</a> <a href="javascript:void(0)" onclick="del(<%=list.get(i).getId() %>)">删除</a></td>
		</tr>
		<%
			}
		%>
	</tbody>
</table>
<%} %>
<%=PageHelper.getPage(data.getPageSize(),data.getCurrentPage(), data.getTotalRecord(),data.getTotalPage())%>

<script>	
var del = function(id){
	var paramaters = {
            "id": id
        };
	var url = "<%=basePath%>/member/feedbackdel";
	var la= layer.confirm('是否确认删除反馈？', {
		 btn: ['确认','取消'], //按钮
		 shade: false //显示遮罩
	     },function(){
			layer.close(la);
			$.ajax({
		           type: 'POST',
		           url: url,
		           data: paramaters,
		           success: function (result) {		    
		        	      alert("删除成功!");	      	
		             
		            	   window.location.href = "<%=basePath%>/member/feedbacklist";		
		                              
		           }
		       });
		}); 
}	
var detail = function (id)
{    	
	 $('#des').text(id);
	 $('#divmodify').modal('show');
}
</script>