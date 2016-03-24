
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>

<%
	String basePath = PropertyUtils.getProperty("java.admin.url");
%>

<style type="text/css">
#map_contain {
	height: 90%;
	max-width: none;
}

label {
	max-width: none;
}

#control {
	width: 100%;
}
.control-group{
	padding-top: 10px
}
</style>
<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
		<div class="col-lg-12">
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">项目名称:</label>
							<div class="col-sm-8">
							  		<input type="text" placeholder="请输入项目名称" class="form-control" id="txtProjectName" name="key" value="">
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<button type="button" class="btn btn-w-m btn-primary" id="btnSearch" style="margin-left:3px;">查询</button>
						</div>
					</div>
				</div>
			    <div class="row">
						<div class="col-lg-3">
				</div>
			</div>
		</div>
	</div>
</div>

<div id="content"></div>


<div class="modal inmodal fade" id="divshowview" tabindex="-1" role="dialog"
	aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">查看</h4>
			</div>

			<div class="modal-body"
				style="height: 500px; overflow: auto; margin-top: 10px; border-bottom: solid 1px #dcdcdc;">
				<fieldset>
					<div class="control-group">编号：<span id="spanId"></span></div>
					<div class="control-group">项目名称：<span id="spanProjectName"></span></div>
					<div class="control-group">姓名：<span id="spanUserName"></span></div>
					<div class="control-group">手机号：<span id="spanPhoneNo"></span></div>
					<div class="control-group">Email：<span id="spanEmail"></span></div>
					<div class="control-group">现店铺数量：<span id="spanCount"></span></div>
					<div class="control-group">现店铺所在地：<span id="spanCity"></span></div>
					<div class="control-group">预期店铺位置：<span id="spanPreCity"></span></div>
					<div class="control-group">预期众筹金额：<span id="spanAmount"></span></div>
					<div class="control-group">申请时间：<span id="spanInsertTime"></span></div>
					<div class="control-group">申请IP：<span id="spanIp"></span></div>
				</fieldset>
				<div class="control-group"></div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
</div>


<script>		
	var jss={
			search:function(currentPage){	
			   var projectName = $("#txtProjectName").val();				   		
				 var paramaters = { 
						 "currentPage":currentPage,						
						 "projectName": projectName		 				
						 };        
			        var url = "<%=basePath%>/suggestion/listdo";
			        $.ajax({
			            type: 'POST',
			            url: url,
			            data: paramaters,
			            success: function (result) {   			            
			            	$("#content").html(result);               
			            }
			        });
			}
		}	
		
	jss.search(1);
	$("#btnSearch").click(function(){
		jss.search(1);
	});	
	
	 function show(id)
	    {
	    	 var paramaters = {
	                   "id": id
	               };
	    	 var url = "<%=basePath%>/suggestion/showview";
		$.ajax({
			type : 'POST',
			url : url,
			data : paramaters,
			success : function(result) {
				$("#divshowview").modal("show");
				var model=result.data;
				$("#spanId").html(model.id);
				$("#spanProjectName").html(model.projectname);
				$("#spanUserName").html(model.username);
				$("#spanPhoneNo").html(model.phoneno);
				$("#spanEmail").html(model.email);
				$("#spanCount").html(model.count);
				$("#spanCity").html(model.city);
				$("#spanPreCity").html(model.precity);
				$("#spanAmount").html(model.amount);
				$("#spanInsertTime").html(model.createtime);
				$("#spanIp").html(model.clientip);
			}
		});

	}
</script>

