
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>         
<%@page import="com.etaofinance.entity.ADVert"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.core.util.HtmlHelper"%>
<%@page import="com.etaofinance.core.util.EnumHelper"%>
<%@page import="com.etaofinance.core.enums.ADVertType"%>

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
							<label class="col-sm-4 control-label">广告标题:</label>
							<div class="col-sm-8">						
								<input type="text" class="form-control" value="" name="txtTitle" id="txtTitle" />
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
				<h4 class="modal-title">添加广告</h4>
			</div>
			<small class="font-bold">
				<div class="modal-body">
					<fieldset>
            <br>
            <div class="control-group">  
                <label>广告标题：</label><input  name="txtATitle" id="txtATitle" type="text" >
            </div>
            <div class="control-group">  
                <label>链接地址：</label><input  name="txtAUrl" id="txtAUrl" type="text" >
            </div>
            <div class="control-group">  
                <label>所属广告位：</label>
                <%=HtmlHelper.getSelect("sltAType", EnumHelper.GetEnumItems(ADVertType.class), "desc", "value",null,"-1","全部") %>
             </div>            
              <div class="control-group">  
                <label>图片地址：</label><input  name="txtAImageUrl" id="txtAImageUrl" type="text" >
            </div>
            <div class="control-group">  
                <label>是否启用：</label>      
  			 <select name="radAstatus"
				 class="form-control m-b" id="commissionType" >				
					<option value="1">启用</option>
					<option value="0">不启用</option>
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
				<h4 class="modal-title">修改广告<</h4>
			</div>
			<small class="font-bold">
				<div class="modal-body">
					<fieldset>
            <br>                
            <div class="control-group">  
                <label>广告标题：</label><input  name="txtETitle" id="txtETitle" type="text" >
                <input  name="txtEId" id="txtEId" type="hidden">
            </div>
            <div class="control-group">  
                <label>链接地址：</label><input  name="txtEUrl" id="txtEUrl" type="text" >
            </div>
            <div class="control-group">  
                <label>所属广告位：</label><input  name="txtETypeId" id="txtETypeId" type="text" >
            </div>            
              <div class="control-group">  
                <label>图片地址：</label><input  name="txtEImageUrl" id="txtEImageUrl" type="text" >
            </div>
            <div class="control-group">  
                <label>是否启用：</label><input  name="txtEIsShelve" id="txtEIsShelve" type="text" >
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
			   var title = $("#txtTitle").val();				   		
				 var paramaters = { 
						 "currentPage":currentPage,						
						 "title": title						 				
						 };        
			        var url = "<%=basePath%>/advert/listdo";
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
        $('#txtATitle').val('');
        $('#txtAUrl').val('');
        //$("#sltAType").val();
         $('#txtAImageUrl').val('');
         $('#txtASortNo').val('0');         
        $('#divadd').modal('show');
}
	function saveAdd(){	
		var txtATitle= $('#txtATitle').val().trim();
		var txtAUrl= $('#txtAUrl').val().trim();	
		var sltAType= $('#sltAType').val().trim();
		var txtAImageUrl= $('#txtAImageUrl').val().trim();
		var radAstatus= $('#radAstatus').val();
		var txtASortNo= $('#txtASortNo').val().trim();		
	   
	    var paramaters = {
                "title": txtATitle.trim(),
                "url": txtAUrl.trim(),
                "typeid": sltAType.trim(),
                "imageurl": txtAImageUrl.trim(),
                "isshelve": radAstatus,
                "sortno": txtASortNo.trim(),                
            };
	    
       var url = "<%=basePath%>/advert/add";
       var la= layer.confirm('是否确认创建广告？', {
		    btn: ['确认','取消'], //按钮
		    shade: false //显示遮罩
		},function(){
			layer.close(la);
			$.ajax({
		           type: 'POST',
		           url: url,
		           data: paramaters,
		           success: function (result) {		    
		        	   alert(result.message);		        	
		               if (result.status == 1) {
		            	   window.location.href = "<%=basePath%>/advert/list";		
		               }
		                              
		        	  
		           }
		       });
		});  
	}
	
	function saveModify()
	{
// 		var txtId= $('#txtEId').val().trim();
// 		var txtKM= $('#txtEKM').val().trim();
// 		var txtKG= $('#txtEKG').val().trim();
// 		var txtESteps= $('#txtESteps').val().trim();
// 		var txtERemark= $('#txtERemark').val().trim();	
// 		var txtDistributionPrice= $('#txtEDistributionPrice').val().trim();		
// 		var txtEIsMaster= $('#txtEIsMaster').val().trim();		

// 		 if(txtKM == "")
// 		 {
// 			 alert("距离不能为空");
// 		    return;
// 		 }
// 		 if(txtKG == "")
// 		 {
// 			 alert("重量不能为空");
// 		    return;
// 		 }
// 		 if (txtEIsMaster==0)
// 			 {
// 				 if(txtKM>0 && txtKG>0)
// 		    	 {
// 					 alert("距离， 重量只能配置1个值");
// 					return;		 
// 				 }
// 				 if(txtKM<=0 && txtKG<=0)
// 		    	 {
// 					 alert("距离， 重量必须有1个值不能为0");
// 					return;		 
// 				 }
// 				 if(txtDistributionPrice == "")
// 				 {
// 					 alert("配送费");
// 				    return;
// 				 }
// 				if(txtDistributionPrice<=0){
// 				    	alert("配送费必须大于零");
// 				    	return;
// 				   }	
// 				 if(txtESteps == "")
// 				 {
// 					 alert("计价阶梯不能为空");
// 				    return;
// 				 }
// 				 if(txtESteps == "0")
// 				 {
// 					 alert("计价阶梯不能为0");
// 				    return;
// 				 }
// 			 }
// 		 else
// 			 {
// 				 if(txtERemark == "")
// 				 {
// 					 alert("备注不能为空");
// 				    return;
// 				 }
// 			 }
// 	    var paramaters = {	    		
// 	    		"Id": txtId.trim(),
//                 "KM": txtKM.trim(),
//                 "KG": txtKG.trim(),
//                 "Steps": txtESteps.trim(),                
//                 "DistributionPrice": txtDistributionPrice.trim(),
//                 "IsMaster": txtEIsMaster.trim(),
//                 "Remark": txtERemark.trim(),
//             };
<%--        var url = "<%=basePath%>/taskdistributionconfig/modify"; --%>
// 	   var la= layer.confirm('是否确认修改配置费？', {
// 		    btn: ['确认','取消'], //按钮
// 		    shade: false //显示遮罩
// 		},function(){
// 			layer.close(la);
// 			$.ajax({
// 		           type: 'POST',
// 		           url: url,
// 		           data: paramaters,
// 		           success: function (result) {		    
// 		        	   alert(result.message);		        	
// 		               if (result.status == 1) {
<%-- 		            	   window.location.href = "<%=basePath%>/taskdistributionconfig/list";		 --%>
// 		               }
		                              
		        	  
// 		           }
// 		       });
// 		});       	    
	}	
	
	</script>		
	
