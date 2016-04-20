<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.etaofinance.core.util.HtmlHelper"%>
<%@page import="com.etaofinance.core.util.EnumHelper"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@ page import="com.etaofinance.core.enums.MemberApplyInvestStatusEnum"%>
<%
	String basePath =PropertyUtils.getProperty("java.admin.url");
%>
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-lg-12">
			<div class="row">
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-5 control-label">领投人ID:</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" name="txtId" id="txtId" onkeyup="this.value = parseInt(this.value); if (this.value=='NaN') { this.value = ''}" maxlength="10" />
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-5 control-label">用户名:</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" name="txtMemberName"
								id="txtMemberName" />
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-5 control-label">手机号:</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" name="txtPhoneNo"
								id="txtPhoneNo" />
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-5 control-label">邮箱:</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" name="txtMail"
								id="txtMail" />
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-5 control-label">申请时间:</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" value=""
								name="applyStartDate" id="applyStartDate"
								onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'applyEndDate\',{d:-1})||\'2030-10-01\'}'})" />
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-5 control-label">至</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" value=""
								name="applyEndDate" id="applyEndDate"
								onFocus="WdatePicker({minDate:'#F{$dp.$D(\'applyStartDate\',{d:1})}',maxDate:'2030-10-01'})" />
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-5 control-label">审核状态:</label>
						<div class="col-sm-7">
							<%=HtmlHelper.getSelect("auditStatus",
					EnumHelper.GetEnumItems(MemberApplyInvestStatusEnum.class),
					"desc", "value", null, "-1", "全部")%>
						</div>
					</div>
				</div>
				
			</div>
		</div>
	</div>
	<div class="row">
	<div class="col-lg-3">
					<div class="form-group">
						<div class="col-sm-8">
							<button type="button" class="btn btn-w-m btn-primary"
								id="btnSearch" style="margin-left: 3px; height: 30px;">查询</button>
						</div>
					</div>
				</div>
</div></div>
<div class="row">
	<div class="col-lg-12">
		<div class="ibox-content" id="content"></div>
	</div>
</div>
<div tabindex="-1" class="modal inmodal" id="showLeadAuditMemberDiv"
	role="dialog" aria-hidden="true" style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title">领投人申请审核</h4> 
			</div>
			<small class="font-bold">
				<div class="modal-body">
					<fieldset>
			            <div class="control-group">
			            	<input type="hidden" value="" id="memberApplyId" />
			                <label>身份证姓名：</label><label id="lblIdCardName"></label>
			            </div>  
			            <div class="control-group">
			                <label>身份证号码：</label><label id="lblIdCardNo"></label>
			            </div>
			            <div class="control-group">
			                <label>符合以下条件之一：</label><label id="lblFitCondition"></label>
			            </div>
			            <div class="control-group">
			                <label>证明材料：</label><label id="lblCertifyMaterial"></label>
			                <a href="" id="btnDownloadMaterial">下载</a>
			            </div>
			            <div class="control-group">
			                <label>审核操作：</label><input type="radio" id="rAuditPass" name="rAuditOpt" value="1" checked="checked"/>通过<input type="radio" id="rAuditNotPass" name="rAuditOpt" value="2" />不通过
			            </div>
			            <div class="control-group">
			            	<label>拒绝原因：</label><textarea name="txtRefuseReason" id="txtRefuseReason" style="width:300px;height:60px;max-width:300px;max-height:60px;display:none;"></textarea>
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
                 var id = $("#txtId").val().trim()==""?"0":$("#txtId").val().trim();
                 var memberName = $("#txtMemberName").val();          
                 var mail=$("#txtMail").val();
                 var auditStatus=$("#auditStatus").val();
                 var applyStartDate=$("#applyStartDate").val();
                 var applyEndDate=$("#applyEndDate").val();
                 var phoneNo=$("#txtPhoneNo").val();
				 var paramaters = { 
						 "applyId":id,
						 "memberName": memberName,
						 "mail": mail,
						 "auditStatus": auditStatus,
						 "applyStartDate":applyStartDate,
						 "applyEndDate":applyEndDate,
						 "currentPage":currentPage,
						 "phoneNo":phoneNo
						 };        
			        var url = "<%=basePath%>/member/leadinvestlistdo";
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
	function showLeadAuditMember(id){
		//ajax调用获取申请人资料信息
		$("#memberApplyId").val(id);
		var url="<%=basePath%>/member/getmemberinfo";
		$.ajax({
			type:"POST",
			url:url,
			data:{"memberApplyId":$("#memberApplyId").val().trim()==""?"0":$("#memberApplyId").val().trim()},
			success:function(result){
				$("#lblIdCardName").html(result.trueName);
				$("#lblIdCardNo").html(result.idCard);
				$("#lblFitCondition").html(result.applyInfo);
				$("#lblCertifyMaterial").html(result.certifyMaterialName);
				$("#btnDownloadMaterial").attr("href",result.certifyMaterialUrl);
				$("#showLeadAuditMemberDiv").modal("show");
			}
		});		
	}
	$('input:radio[name="rAuditOpt"]').change(function(){
		if($(this).val() == 2){
			$("#txtRefuseReason").show();
		}else{
			$("#txtRefuseReason").hide();
		}
	});
	$("#btnConfirm").click(function(){
		var auditStaus= $('input[name="rAuditOpt"]:checked').val();
		var refuseReason=$("#txtRefuseReason").val();
		if(auditStaus==0){
			if(refuseReason.trim().length == 2){
				alert("请输入拒绝原因！")
			}
		}else{
			var url="<%=basePath%>/member/auditconfirm";
			var paramaters = { 
					 	"memberApplyId":$("#memberApplyId").val(),
					 	"auditStatus":auditStaus,
					 	"refuseReason":refuseReason
					 };  
			$.ajax({
				type:"POST",
				url:url,
				data:paramaters,
				success:function(result){
					alert("成功！");
					window.location.href = "<%=basePath%>/member/leadinvestlist";	
				},
				error:function(e){
					alert("失败！");
				}
			});
		}
	});
</script>
