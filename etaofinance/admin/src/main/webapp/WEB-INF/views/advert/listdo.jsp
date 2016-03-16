<%@page import="com.etaofinance.entity.domain.GlobalConfigModel"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.etaofinance.entity.common.PagedResponse"%>
<%@page import="com.etaofinance.core.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="java.util.List"%>
<%@page import="com.etaofinance.entity.ADVert"%>
<%@page import="com.etaofinance.core.util.ParseHelper"%>

<%
String basePath =PropertyUtils.getProperty("java.admin.url");	 
PagedResponse<ADVert> data=	(PagedResponse<ADVert>)request.getAttribute("listData");
List<ADVert> list = data.getResultList();
if(list == null){
	list = new ArrayList<ADVert>();
}

							%>
<table class="table table-striped table-bordered table-hover dataTables-example">
						<thead>
							<tr>
						<th width="5%">ID</th>
			<th>排序</th>
			<th>广告标题</th>
			<th>所属广告位</th>
			<th>状态</th>
			<th>点击数</th>
			<th>添加时间</th>
			<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<%for (int i = 0; i < list.size(); i++) { %>
				<tr>
			<td><%=list.get(i).getId()%></td>
			<td><%=list.get(i).getSortno()%></td>
			<td><%=list.get(i).getTitle()%></td>
			<td><%=list.get(i).getTypeid()%></td>
			<td><%=list.get(i).getIsshelve()?"启用":"禁用"%></td>
			<td><%=list.get(i).getClicknum()%></td>			
			<td><%=ParseHelper.ToDateString(list.get(i).getCreatetime(), "") %></td>		
			<td>			
				<a href="javascript:void(0)" onclick="modify(<%=list.get(i).getId() %>)">修改</a>
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
    	 var url = "<%=basePath%>/advert/selectbyprimarykey";
    	 $.ajax({
	           type: 'POST',
	           url: url,
	           data: paramaters,
	           success: function (result) {		    
	        	             	
	        	 	$('#txtEId').val(result.id);
	        	 	$('#txtETitle').val(result.title);    	        	
	                $('#txtEUrl').val(result.url);
	                $('#sltEType').val(result.typeid);
	                $('#txtEImageUrl').val(result.imageurl);
	                if(result.isshelve)
	                	$("#radEstatus").val(1);
	                else
	                	$("#radEstatus").val(0);	                     
	                $("#txtESortNo").val(result.sortno); 	            		
	                $('#divmodify').modal('show');
	           }
	       });   	
		
    }
function del(id){		
	    
	    var paramaters = {
                "id": id
            };
       var url = "<%=basePath%>/advert/del";
	   var la= layer.confirm('是否确认删除广告？', {
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
		             
		            	   window.location.href = "<%=basePath%>/advert/list";		
		                              
		        	  
		           }
		       });
		});       	    
	}
	
    </script>
