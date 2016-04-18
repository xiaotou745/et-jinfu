<%@page import="com.etaofinance.entity.domain.GlobalConfigModel"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.etaofinance.entity.common.PagedResponse"%>
<%@page import="com.etaofinance.core.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="java.util.List"%>
<%@page import="com.etaofinance.entity.QA"%>
<%@page import="com.etaofinance.core.util.ParseHelper"%>

<%
String basePath =PropertyUtils.getProperty("java.admin.url");	 
PagedResponse<QA> data=	(PagedResponse<QA>)request.getAttribute("listData");
List<QA> list = data.getResultList();
if(list == null){
	list = new ArrayList<QA>();
}

							%>
<table class="table table-striped table-bordered table-hover dataTables-example">
						<thead>
							<tr>					
			<th>排序</th>
			<th>问题</th>
			<th>内容</th>		
			<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<%for (int i = 0; i < list.size(); i++) { %>
				<tr>		
			<td><%=list.get(i).getSortno()%></td>
			<td><%=list.get(i).getQuestion()%></td>
			<td><%=list.get(i).getAnswer()%></td>		
			<td>			
				<a href="javascript:void(0)" onclick="modify(<%=list.get(i).getId() %>)">编辑</a>
				<a href="javascript:void(0)" onclick="del('<%=list.get(i).getId() %>')">删除</a>
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
					
<script>				
    function modify(id)
    {    	
    	 var paramaters = {
                   "id": id
               };
    	 var url = "<%=basePath%>/qa/selectbyprimarykey";
    	 $.ajax({
	           type: 'POST',
	           url: url,
	           data: paramaters,
	           success: function (result) {		    
	        	             	
	        	 	$('#txtEId').val(result.id);
	        	 	$('#txtEQuestion').val(result.question);
	        	 	$('#txtEAnswer').val(result.answer);   	
	        	    if(result.isdel)
	                	$("#radEIsDel").val(1);
	                else
	                	$("#radEIsDel").val(0);	           
	                                    
	                $("#txtESortNo").val(result.sortno); 	            		
	                $('#divmodify').modal('show');
	           }
	       });   	
		
    }
function del(id){		
	    
	    var paramaters = {
                "id": id
            };
       var url = "<%=basePath%>/qa/del";
	   var la= layer.confirm('是否确认删除问答？', {
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
		             
		            	   window.location.href = "<%=basePath%>/qa/list";		
		                              
		        	  
		           }
		       });
		});       	    
	}
	
    </script>
