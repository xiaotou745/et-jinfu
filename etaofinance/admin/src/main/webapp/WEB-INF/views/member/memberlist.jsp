<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="com.etaofinance.core.util.HtmlHelper"%>
<%@ page import="com.etaofinance.core.util.EnumHelper"%>
<%@ page import="com.etaofinance.core.enums.MemberTypeEnum"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%
	String basePath =PropertyUtils.getProperty("java.admin.url");
%>
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-lg-12">
			<div class="row">
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-4 control-label">ID:</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" name="txtId" id="txtId" onkeyup="this.value = parseInt(this.value); if (this.value=='NaN') { this.value = ''}" maxlength="10" />
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-4 control-label">用户名:</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" name="txtMemberName"
								id="txtMemberName" />
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-4 control-label">手机号:</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" name="txtPhoneNo"
								id="txtPhoneNo" />
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-4 control-label">邮箱:</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" name="txtMail"
								id="txtMail" />
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-4 control-label">注册时间:</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" value=""
								name="applyStartDate" id="registerStartDate"
								onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'registerEndDate\',{d:-1})||\'2030-10-01\'}'})" />
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-4 control-label">到</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" value=""
								name="applyEndDate" id="registerEndDate"
								onFocus="WdatePicker({minDate:'#F{$dp.$D(\'registerStartDate\',{d:1})}',maxDate:'2030-10-01'})" />
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-4 control-label">用户类型:</label>
						<div class="col-sm-8">
							<%=HtmlHelper.getSelect("memberType",
					EnumHelper.GetEnumItems(MemberTypeEnum.class),
					"desc", "value", null, "-1", "全部")%>
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="form-group">
						<div class="col-sm-8">
							<button type="button" class="btn btn-w-m btn-primary"
								id="btnSearch" style="margin-left: 3px; height: 30px;">搜索</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="ibox-content" id="content"></div>
	</div>
</div>
<script>
	var jss={
			search:function(currentPage){	
                 var id = $("#txtId").val().trim()==""?"0":$("#txtId").val().trim();
                 var memberName = $("#txtMemberName").val();          
                 var mail=$("#txtMail").val();
                 var memberType=$("#memberType").val();
                 var registerStartDate=$("#registerStartDate").val();
                 var registerEndDate=$("#registerEndDate").val();
				 var paramaters = { 
						 "applyId":id,
						 "memberName": memberName,
						 "mail": mail,
						 "memberType":memberType,
						 "registerStartDate":registerStartDate,
						 "registerEndDate":registerEndDate,
						 "currentPage":currentPage
						 };        
			        var url = "<%=basePath%>/member/memberlistdo";
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
</script>
