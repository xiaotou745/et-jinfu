<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.core.enums.ProjectType"%>
<%@page import="com.etaofinance.core.util.EnumHelper"%>
<%@page import="java.util.List"%>
<%@page import="com.etaofinance.core.util.HtmlHelper"%>
<%
	String basePath = PropertyUtils.getProperty("java.admin.url");
%>
<div
	class="wrapper wrapper-content animated fadeInRight form-horizontal">
	<div class="row">
		<div class="col-lg-12">
			<div class="row">
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-5 control-label">项目ID:</label>
						<div class="col-sm-7">
							<input type="text" placeholder="请输入项目ID" class="form-control"
								id="ProjectId"
								onkeyup="this.value = parseInt(this.value); if (this.value=='NaN') { this.value = ''}"
								maxlength="10" />
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-5 control-label">项目名称:</label>
						<div class="col-sm-7">
							<input type="text" placeholder="请输入项目名称" class="form-control"
								id="ProjectName" />
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-5 control-label">审核状态:</label>
						<div class="col-sm-7">
							<select name="projectAuditStatus" class="form-control m-b"
								id="projectAuditStatus">
								<option value="-1">全部</option>
								<option value="1">待审核</option>
								<option value="3">审核拒绝</option>
							</select>
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-5 control-label">项目类型:</label>
						<div class="col-sm-7">
							<%=HtmlHelper.getSelect("projectType",
					EnumHelper.GetEnumItems(ProjectType.class), "desc",
					"value", null, "-1", "全部")%>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-4 control-label">提交时间:</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" value="" name="beginDate"
								id="beginDate"
								onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\',{d:-1})||\'2030-10-01\'}'})" />
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-4 control-label">结束日期:</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" value="" name="endDate"
								id="endDate"
								onFocus="WdatePicker({minDate:'#F{$dp.$D(\'beginDate\',{d:1})}',maxDate:'2030-10-01'})" />
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-3">
					<button type="button" class="btn btn-w-m btn-primary"
						id="btnSearch" style="margin-left: 3px;">查询</button>

				</div>
			</div>
		</div>
	</div>
</div>
<div id="content"></div>
<!-- 显示审核弹出框 -->
<div tabindex="-1" class="modal inmodal" id="showProjectAuditDiv"
	role="dialog" aria-hidden="true" style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title">项目审核</h4> 
			</div>
			<small class="font-bold">
				<div class="modal-body">
					<fieldset>
					 	<div class="control-group">
			                <label>审核操作：</label><input type="radio" id="rAuditPass" name="rAuditOpt" value="2" checked="checked"/>通过
			                <input type="radio" id="rAuditNotPass" name="rAuditOpt" value="3" />不通过<input type="hidden" value="" id="projectId" />
			            </div>
			            <div class="control-group" id="someDateDiv">
			                <label>上线预热时间：</label><input type="text" style="width:200px;" class="form-control" value="" name="onlinePreheatDate"
								id="onlinePreheatDate"
								onFocus="WdatePicker({minDate:'%y-%M-%d'})" />
								<label>开放融资时间：</label><input type="text" style="width:200px;" class="form-control" value=""
								name="openFinancingDate" id="openFinancingDate"
								onFocus="WdatePicker({minDate:'%y-%M-%d',maxDate:'#F{$dp.$D(\'endFinancingDate\',{d:-1})||\'2030-10-01\'}'})" />
								<label>融资结束时间：</label><input type="text" style="width:200px;" class="form-control" value=""
								name="endFinancingDate" id="endFinancingDate"
								onFocus="WdatePicker({minDate:'#F{$dp.$D(\'openFinancingDate\',{d:1})}',maxDate:'2030-10-01'})" />
			            </div>
			            <div class="control-group">
			            	<label>备注：</label><textarea name="txtRemark" id="txtRemark" style="width:300px;height:60px;max-width:300px;max-height:60px;"></textarea>
			            </div>
			        </fieldset>
				</div>
				<div class="modal-footer">
					<button class="btn btn-white" type="button" data-dismiss="modal">返回</button>
					<button id="btnConfirm" class="btn btn-primary" type="button" >确定</button>
				</div>
			</small>
		</div> 
	</div>
</div>
<script>
var jss={
		search:function(currentPage){
			var data={currentPage:currentPage,
					"id":$("#ProjectId").val().trim()==""?"0":$("#ProjectId").val().trim(),
					"projectStatus":$("#projectStatus").val(),
					"projectName":$("#ProjectName").val(),
					"auditStatus":$("#projectAuditStatus").val(),
					"startCreateTime":$("#beginDate").val(),
					"endCreateTime":$("#endDate").val(),
					"typeId":$("#projectType").val()
			};
			$.post("<%=basePath%>/project/waitlistdo", data, function(d) {
				$("#content").html(d);
			});
		}
	}
	jss.search(1);
	$("#btnSearch").click(function() {
		jss.search(1);
	});
	//项目审核 弹框
	function showProjectAudit(projectId){
		//ajax调用获取申请人资料信息
		$("#projectId").val(projectId);
		$("#showProjectAuditDiv").modal("show");		
	}
	/*审核 弹出框 确认操作 */
	$("#btnConfirm").click(function(){
		var url="<%=basePath%>/project/audit";
		var auditStaus= $('input[name="rAuditOpt"]:checked').val();
		var remark=$("#txtRemark").val();
		var projectId=$("#projectId").val();
		if(remark.trim().length == 0){
			alert("请输入备注信息！")
			return;
		}
		var paramaters = { 
			 	"projectId":projectId,
			 	"auditStatus":auditStaus,
			 	"remark":remark,
			 	"onlinePreheatDate":$("#onlinePreheatDate").val(),
			 	"openFinancingDate":$("#openFinancingDate").val(),
			 	"endFinancingDate":$("#endFinancingDate").val()
			 };  
		$.ajax({
			type:"POST",
			url:url,
			data:paramaters,
			success:function(result){
				alert("审核成功！");
				jss.search(1);
			},
			error:function(e){
				alert("审核失败！");
			}
		});
	});
	/* 单选框 切换 */
	$('input:radio[name="rAuditOpt"]').change(function(){
		if($(this).val() == 2){
			$("#someDateDiv").show();
		}else{
			$("#someDateDiv").hide();
		}
	});
</script>