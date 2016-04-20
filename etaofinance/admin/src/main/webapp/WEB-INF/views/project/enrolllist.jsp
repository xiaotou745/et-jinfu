<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
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
						<label class="col-sm-5 control-label">项目标题:</label>
						<div class="col-sm-7">
							<input type="text" placeholder="请输入项目标题" class="form-control" id="title"  maxlength="10"/>
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-3 control-label">所在行业:</label>
						<div class="col-sm-7">
							<input type="text" placeholder="请输入所在行业" class="form-control" id="ownedIndustry"  maxlength="10"/>
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-4 control-label">提交时间:</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" value="" name="beginDate"
								id="beginDate"
								onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\',{d:-1})||\'2030-10-01\'}'})" />
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-1 control-label">~</label>
						<div class="col-sm-7">
									<input type="text" class="form-control" value="" name="endDate"
								id="endDate"
								onFocus="WdatePicker({minDate:'#F{$dp.$D(\'beginDate\',{d:1})}',maxDate:'2030-10-01'})" />
						</div>
					</div>
				</div>
				
				<div class="col-lg-3">
					<button type="button" class="btn btn-w-m btn-primary" id="btnSearch" style="margin-left: 60px;">查询</button>
				</div>
			</div>
		</div>
	</div>
</div>
 
 
<div id="content">
</div>



<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">报名项目审核</h4>
			</div>
			<div class="modal-body">
				<table>
					<tr>
						<td>审核：</td>
						<td><input type="radio" name="status" id="statusFir"
							value="1" checked> 通过 <input type="radio" name="status"
							id="statusSec" value="-1">未通过</td>
					</tr>
					<tr style="visibility: hidden;" id=rdtr>
						<td>拒绝理由：</td>
						<td><input type="text" style="length: 200px;" id="refuseRemark"/></td>
					</tr>
				</table>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" id="applyenroll">
					确定</button>
			</div>
		</div>
	</div>
</div>

<input type="hidden" name="" value=""  id="hidId"/>
<input type="hidden" name="" value="1" id="hidStatus"/>
<script>
	var jss = {
		search : function(currentPage) {
			var data = {
				currentPage : currentPage,
				"title" : $("#title").val(),
				"ownedIndustry" : $("#ownedIndustry").val(),
				"startTime" : $("#beginDate").val(),
				"endTime" : $("#endDate").val()

			};
			$.post("<%=basePath%>/project/enrolllistdo",data,function(d){
					$("#content").html(d);
				});
			}
		}
	
	jss.search(1);
	
	$("#btnSearch").click(function(){
		jss.search(1);
	});
	
	function setHidId(hidId){
		$('#hidId').val(hidId);
	}
	
	function setDefaultVal(){
		$('#refuseRemark').val('');
		$('#hidId').val('');
		$('#hidStatus').val(1);
		$('#statusSec').removeAttr('checked');
		$('#statusFir').attr('checked',true);
		$('.close').click();
		$('#rdtr').css('visibility','hidden');
	}
	$('input[name="status"]').click(function(){
		

	$('input[name="status"]').each(function() {
			var thisRdio = $(this);
			if (thisRdio.is(':checked')) {

				var ckrdioval = thisRdio.val();

				if (1 == ckrdioval) {

					$('#rdtr').css('visibility', 'hidden');
					$('#refuseRmark').val('');
				}

				if (-1 == ckrdioval) {
					$('#rdtr').css('visibility', 'visible');
				}
				$('#hidStatus').val(ckrdioval);
			}
		})
	});

	$('#applyenroll').click(function() {

		var data = {
			"id" : $('#hidId').val(),
			"status" : $('#hidStatus').val(),
			"refuseremark" : $('#refuseRemark').val()
		};

		$.post('<%=basePath%>/project/auditenroll',data,function(res){
			if(res>0){
				alert('审核成功');
				setDefaultVal();
				window.location.reload();
			//	jss.search(1);
			}else{
				alert('审核失败');
			}
		});
	});
	
	
</script>