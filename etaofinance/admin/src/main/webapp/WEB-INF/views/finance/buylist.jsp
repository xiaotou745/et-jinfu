<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.core.enums.ProjectStatus"%>
<%@page import="com.etaofinance.core.util.EnumHelper"%>
<%@page import="java.util.List"%>
<%@page import="com.etaofinance.core.util.HtmlHelper"%>
<%
	String basePath =PropertyUtils.getProperty("java.admin.url");
%>
<div class="wrapper wrapper-content animated fadeInRight form-horizontal">
	<div class="row">
		<div class="col-lg-12">
			<div class="row">
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-5 control-label">项目ID:</label>
						<div class="col-sm-7">
							<input type="text" placeholder="请输入项目ID" class="form-control" id="ProjectId" onkeyup="this.value = parseInt(this.value); if (this.value=='NaN') { this.value = ''}" maxlength="10"/>
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-5 control-label">项目名称:</label>
						<div class="col-sm-7">
							<input type="text" placeholder="请输入项目名称" class="form-control" id="ProjectName" />
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-5 control-label">状态:</label>
						<div class="col-sm-7">
							<%=HtmlHelper.getSelect("projectStatus", EnumHelper.GetEnumItems(ProjectStatus.class), "desc", "value",null,"-1","全部") %>
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<button type="button" class="btn btn-w-m btn-primary" id="btnSearch" style="margin-left: 3px;">查询</button>
				</div>
				<div class="col-lg-3">
					<button type="button" class="btn btn-w-m btn-primary" id="newProject" style="margin-left: 3px;">发布项目</button>
				</div>
			</div>
		</div>
	</div>
</div>
 
 
<div id="content">
</div>

<script>

	$(function(){
		$('#newProject').click(function(){
			window.open('<%=basePath%>/project/newproject');
		});
	});
	
	var jss={
			search:function(currentPage){
				var data={currentPage:currentPage,
						"id":$("#ProjectId").val().trim()==""?"0":$("#ProjectId").val().trim(),
						"projectStatus":$("#projectStatus").val(),
						"projectName":$("#ProjectName").val()
				};
				$.post("<%=basePath%>/project/listdo",data,function(d){
					$("#content").html(d);
				});
			}
		}
	
	jss.search(1);
	
	$("#btnSearch").click(function(){
		jss.search(1);
	});
	
	function setstatus(projectid,status){
		
	}
</script>