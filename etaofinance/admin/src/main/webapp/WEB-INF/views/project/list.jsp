<%@page import="com.etaofinance.core.enums.ProjectAuditStatus"%>
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
<!-- 不通过模态提示框 -->
<div tabindex="-1" class="modal inmodal" id="refuseDiv" role="dialog" aria-hidden="true" style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
			<input type="hidden" value="" id="curProjectId"/>
				<button class="close" type="button" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title">审核拒绝</h4> 
			</div>
			<small class="font-bold">
				<div class="modal-body">
					<fieldset>
			            <div class="form-group">
			            	<label>拒绝原因：</label>
			            </div>
			            <div class="form-group">
			            <textarea name="txtRefuseReason" id="txtRefuseReason" style="width:300px;height:100px;max-width:300px;max-height:100px;"></textarea>
			            </div>
			        </fieldset>
				</div>
				<div class="modal-footer">
					<button class="btn btn-white" type="button" data-dismiss="modal">取消</button>
					<button id="btnRefuseConfirm" class="btn btn-primary" type="button" >确定</button>
				</div>
			</small>
		</div> 
	</div>
</div>
<!-- 融资失败提示框 -->
<div tabindex="-1" class="modal inmodal" id="investFailDiv"
	role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="investFailDivLabel">再次确认融资失败</h4>
			</div>
			<div class="modal-body">该操作不可恢复，确定要操作融资失败吗？</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">取消
				</button>
				<button type="button" class="btn btn-primary" id="investFailConfirm">确定
				</button>
			</div>
		</div>
	</div>
</div>
<!-- 项目隐藏提示框 -->
<div tabindex="-1" class="modal inmodal" id="hideProjectDiv"
	role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" >项目隐藏</h4>
			</div>
			<div class="modal-body">项目隐藏后，将不再在前台列表展示，确定要隐藏吗？</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">取消
				</button>
				<button type="button" class="btn btn-primary" id="hideProjectConfirm">确定
				</button>
			</div>
		</div>
	</div>
</div>
<!-- 项目显示提示框 -->
<div tabindex="-1" class="modal inmodal" id="showProjectDiv"
	role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" >项目显示</h4>
			</div>
			<div class="modal-body">项目显示后，将在前台列表展示，确定要显示吗？</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">取消
				</button>
				<button type="button" class="btn btn-primary" id="showProjectConfirm">确定
				</button>
			</div>
		</div>
	</div>
</div>
<script>
$(function(){
	//点击发布项目按钮
	$('#newProject').click(function(){
		window.open('<%=basePath%>/project/newproject');
		});
	});
	var jss = {
		search : function(currentPage) {
			var data = {
				currentPage : currentPage,
				"id" : $("#ProjectId").val().trim() == "" ? "0" : $(
						"#ProjectId").val().trim(),
				"projectStatus" : $("#projectStatus").val(),
				"projectName" : $("#ProjectName").val()
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
//不通过时，弹出窗口
function showRefuseDiv(projectid){
	$("#curProjectId").val(projectid);
	$("#txtRefuseReason").val("");
	$("#refuseDiv").modal("show");
}
//拒绝确认按钮操作
$("#btnRefuseConfirm").click(function(){
	var url="<%=basePath%>/project/audit";
	var remark = $("#txtRefuseReason").val();
	var projectId = $("#projectId").val();
	if (remark.trim().length == 0) {
		alert("请输入备注信息！")
		return;
	} 
	var paramaters = {
		"projectId" : $("#curProjectId").val(),
		"remark" : remark,
		"onlinePreheatDate" : "",
		"openFinancingDate" : "",
		"endFinancingDate" : "",
		"auditStatus" : <%=ProjectAuditStatus.AuditRefuse.value()%>,
		"logRemark":"审核状态修改为不通过,"+remark
	};
	$.ajax({
		type : "POST",
		url : url,
		data : paramaters,
		success : function(result) {
			if(result==1){
				alert("操作成功！");
				$("#refuseDiv").modal("hide");
				jss.search(1);
			}else{
				alert("操作失败！");
			}
		},
		error : function(e) {
			alert("操作失败！");
		}
	});
});
//融资失败操作
function investFail(projectid){
	$('#investFailDiv').on('show.bs.modal',function(){
		$(this).attr('data-projectid',projectid);
	}).modal('show');
}
$("#investFailConfirm").click(function(){
	var url="<%=basePath%>/project/modifyprojectstatus";	  
	var paramaters = {
		"projectId" : $('#investFailDiv').attr("data-projectid"),
		"aftProjectStatus":<%=ProjectStatus.Failure.value()%>,
		"logRemark":"融资成功修改为融资失败"
	};
	$.ajax({
		type : "POST",
		url : url,
		data : paramaters,
		success : function(result) {
			if(result==1){
				alert("操作成功！");
				$('#investFailDiv').modal('hide');
				jss.search(1);
			}else{
				alert("操作失败！");
			}
		},
		error : function(e) {
			alert("操作失败！");
		}
	});
});
//隐藏项目
function hideProject(projectid){
	$('#hideProjectDiv').on('show.bs.modal',function(){
		$(this).attr('data-projectid',projectid);
	}).modal('show');
}
$("#hideProjectConfirm").click(function(){
	var url="<%=basePath%>/project/isshelf";	  
	var paramaters = {
		"projectId" : $('#hideProjectDiv').attr("data-projectid"),
		"logRemark":"修改项目为下架",
		"isShelve":0
	};
	$.ajax({
		type : "POST",
		url : url,
		data : paramaters,
		success : function(result) {
			if(result==1){
				alert("操作成功！");
				$('#hideProjectDiv').modal('hide');
				jss.search(1);
			}else{
				alert("操作失败！");
			}
		},
		error : function(e) {
			alert("操作失败！");
		}
	});
});
//显示项目
function showProject(projectid){
	$('#showProjectDiv').on('show.bs.modal',function(){
		$(this).attr('data-projectid',projectid);
	}).modal('show'); 
}
$("#showProjectConfirm").click(function(){
	var url="<%=basePath%>/project/isshelf";	  
	var paramaters = {
		"projectId" : $('#showProjectDiv').attr("data-projectid"),
		"logRemark":"修改项目为上架",
		"isShelve":0
	};
	$.ajax({
		type : "POST",
		url : url,
		data : paramaters,
		success : function(result) {
			if(result==1){
				alert("操作成功！");
				$('#showProjectDiv').modal('hide');
				jss.search(1);
			}else{
				alert("操作失败！");
			}
		},
		error : function(e) {
			alert("操作失败！");
		}
	});
});
</script>