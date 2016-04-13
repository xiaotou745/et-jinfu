<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="com.etaofinance.core.util.HtmlHelper"%>
<%@ page import="com.etaofinance.core.util.EnumHelper"%>
<%@ page import="com.etaofinance.core.enums.MemberTypeEnum"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<div class="wrapper wrapper-content animated fadeInRight form-horizontal">
	<div class="row">
		<div class="col-lg-12">
			<div class="row">
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-5 control-label">提交人:</label>
						<div class="col-sm-7">
							<input type="text" placeholder="提交人姓名" class="form-control" id="CreateName" maxlength="10"/>
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-5 control-label">关键字:</label>
						<div class="col-sm-7">
							<input type="text" placeholder="反馈内容" class="form-control" id="Description" />
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-4 control-label">反馈时间:</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" value=""
								name="applyStartDate" id="createStartDate"
								onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'createEndDate\',{d:-1})||\'2030-10-01\'}'})" />
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-2 control-label">到</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" value=""
								name="applyEndDate" id="createEndDate"
								onFocus="WdatePicker({minDate:'#F{$dp.$D(\'createStartDate\',{d:1})}',maxDate:'2030-10-01'})" />
						</div>
					</div>
				</div>
			</div>
			<div class="row">
			<div class="col-lg-2">
					<button type="button" class="btn btn-w-m btn-primary" id="btnSearch" style="margin-left: 925px;">查询</button>
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
<div tabindex="-1" class="modal inmodal" id="divmodify"
	role="dialog" aria-hidden="true" style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title">用户反馈</h4>
			</div>
			<small class="font-bold">
				<div class="modal-body">
					<fieldset>
            <br>                
           <div class="control-group">  
                <label>反馈内容：</label>
                <label id="des"></label>
            </div>        
            
            
        </fieldset>
				</div>
			</small>
		</div> 
	</div> 
</div>
<script>
	var jss={
			search:function(currentPage){	
                 var createname = $("#CreateName").val();          
                 var description=$("#Description").val();
                 var begindate=$("#createStartDate").val();
                 var enddate=$("#createEndDate").val();
				 var paramaters = { 
						 "createname":createname,
						 "description": description,
						 "beginCreatetime": begindate,
						 "endCreatetime":enddate,
						 "currentPage":currentPage
						 };        
			        var url = "../project/commentdo";
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
