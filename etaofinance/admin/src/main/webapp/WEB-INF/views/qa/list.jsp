
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>         
<%@page import="com.etaofinance.entity.QA"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.core.util.HtmlHelper"%>
<%@page import="com.etaofinance.core.util.EnumHelper"%>

<%
	String basePath =PropertyUtils.getProperty("java.admin.url");	 
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
</style>
<div class="wrapper wrapper-content animated fadeInRight">

	<div class="row">
		<div class="col-lg-12">			
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">问题:</label>
							<div class="col-sm-8">						
								<input type="text" class="form-control" value="" name="txtQuestion" id="txtQuestion" />
							</div>
						</div>
					</div>					
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label"></label>
							<div class="col-sm-8">							
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label"></label>
							<div class="col-sm-8">
								
							</div>
						</div>
					</div>
				</div>				
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label"></label>
							<div class="col-sm-8">							
   						</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label"></label>
							<div class="col-sm-8">
							  
							</div>
						</div>
					</div>
				</div>

			    <div class="row">
						<div class="col-lg-3">
						<button type="button" class="btn btn-w-m btn-primary" id=btnSearch
							style="margin-left: 3px;height:30px;">查询</button>
								<button type="button" class="btn btn-w-m btn-primary" id="add"  onclick="showAdd()"
					style="margin-left: 3px;height:30px;">新增		</button>	
					 
					</div>
			</div>	
			
			   </div>
	</div>	
</div>
<div id="content"></div>

<div tabindex="-1" class="modal inmodal" id="divadd"
	role="dialog" aria-hidden="true" style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title">添加问题</h4>
			</div>
			<small class="font-bold">
				<div class="modal-body">
					<fieldset>
            <br>
            <div class="control-group">  
                <label>问题：</label><input  name="txtAQuestion" id="txtAQuestion" type="text" >
            </div>
            <div class="control-group">  
                <label>答案：</label><input  name="txtAAnswer" id="txtAAnswer" type="text" >
            </div>     
            <div class="control-group">  
                <label>是否删除：</label>      
  			 <select name="radAIsDel"
				 class="form-control m-b" id="radAIsDel" >				
					<option value="1">删除</option>
					<option value="0">未删除</option>
			</select> 
            </div>
           <div class="control-group">  
                <label>排序：</label><input  name="txtASortNo" id="txtASortNo" type="text" >
            </div>           
        </fieldset>
				</div>
				<div class="modal-footer">
					<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
					<button class="btn btn-primary" type="button" id="btnAdd" onclick="saveAdd()">保存</button>
				</div>
			</small>
		</div> 
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
				<h4 class="modal-title">修改问题</h4>
			</div>
			<small class="font-bold">
				<div class="modal-body">
					<fieldset>
            <br>                
           <div class="control-group">  
                <label>问题：</label><input  name="txtEQuestion" id="txtEQuestion" type="text" >
            </div>
            <div class="control-group">  
                <label>答案：</label><input  name="txtEAnswer" id="txtEAnswer" type="text" >
            </div>     
             <div class="control-group">  
                <label>是否删除：</label>      
  			 <select name="radEIsDel"
				 class="form-control m-b" id="radEIsDel" >				
					<option value="1">删除</option>
					<option value="0">未删除</option>
			</select> 
            </div>
           <div class="control-group">  
                <label>排序：</label><input  name="txtESortNo" id="txtESortNo" type="text" >
            </div>            
            
            
        </fieldset>
				</div>
				<div class="modal-footer">
					<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
					<button class="btn btn-primary" type="button" id="btnEdit" onclick="saveModify()">保存</button>
				</div>
			</small>
		</div> 
	</div> 
</div>

	<script>		
	var jss={
			search:function(currentPage){	
			   var question = $("#txtQuestion").val();				   		
				 var paramaters = { 
						 "currentPage":currentPage,						
						 "question": question						 				
						 };        
			        var url = "<%=basePath%>/qa/listdo";
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

	function showAdd(){ 
        $('#txtAQuestion').val('');
        $('#txtAAnswer').val('');      
        $('#txtASortNo').val('0');         
        $('#divadd').modal('show');
}
	function saveAdd(){	
		var txtAQuestion= $('#txtAQuestion').val().trim();
		var txtAAnswer= $('#txtAAnswer').val().trim();	
		var radAIsDel= $('#radAIsDel').val().trim();		
		var txtASortNo= $('#txtASortNo').val().trim();		
	   
	    var paramaters = {
                "question": txtAQuestion.trim(),
                "answer": txtAAnswer.trim(),
                "isdel": radAIsDel.trim(),                
                    "sortno": txtASortNo.trim(),                
            };
	    
       var url = "<%=basePath%>/qa/add";
       var la= layer.confirm('是否确认创建问答？', {
		    btn: ['确认','取消'], //按钮
		    shade: false //显示遮罩
		},function(){
			layer.close(la);
			$.ajax({
		           type: 'POST',
		           url: url,
		           data: paramaters,
		           success: function (result) {		    
		        	   alert(result.msg);		        	
		               if (result.code == 1) {
		            	   window.location.href = "<%=basePath%>/qa/list";		
		               }
		                              
		        	  
		           }
		       });
		});  
	}
	
	function saveModify()
	{
		var txtEQuestion= $('#txtEQuestion').val().trim();
		var txtEAnswer= $('#txtEAnswer').val().trim();	
		var radEIsDel= $('#radEIsDel').val().trim();		
		var txtESortNo= $('#txtESortNo').val().trim();		
	   


	    var paramaters = {
                "question": txtEQuestion.trim(),
                "answer": txtEAnswer.trim(),
                "isdel": radEIsDel.trim(),                
                    "sortno": txtESortNo.trim(),                
            };
       var url = "<%=basePath%>/qa/modify";
	   var la= layer.confirm('是否确认修改问答？', {
		    btn: ['确认','取消'], //按钮
		    shade: false //显示遮罩
		},function(){
			layer.close(la);
			$.ajax({
		           type: 'POST',
		           url: url,
		           data: paramaters,
		           success: function (result) {		    
		        	   alert(result.msg);	        	
		               if (result.code == 1) {
		            	   window.location.href = "<%=basePath%>/qa/list";		
		               }
		                              
		        	  
		           }
		       });
		});       	    
	}	
	
	</script>		
	
