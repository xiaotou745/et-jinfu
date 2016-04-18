<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.core.util.ParseHelper"%>
<%@page import="com.etaofinance.entity.MenuInfo"%>
<%@page import="com.etaofinance.core.util.EnumHelper"%>
<%@page import="com.etaofinance.core.util.HtmlHelper"%>
<%@page import="org.springframework.web.util.HtmlUtils"%>
<%@page import="org.apache.commons.codec.binary.Base64"%>
<%
	String basePath = PropertyUtils.getProperty("java.admin.url");

String menuJson = (String) request.getAttribute("menuJson");
menuJson=new String(Base64.encodeBase64(menuJson.getBytes("UTF-8")));  
%>
<script src="<%=basePath%>/js/util.js"></script>
<script src="<%=basePath%>/js/bootstrap-treeview.js"></script>
<table style="width:80%">
<tr>
<td style="width:400px;">
		<div class="control-group" style="width:400px;">
			<div class="controls">
				<button class="btn btn-success" id="btnExpanAll" type="button">展开/折叠</button>
				<button class="btn btn-success" type="button" id="addNewMenu">新增</button>
			</div>
			<div class="controls" style="height:600px; overflow:auto; ">
				<div class="test treeview" id="treeview11"></div>
			</div>
		</div>

</td>
<td >
<div style="margin-top:-45px;" id="detail">
	<div class="controls">
				<button class="btn btn-success" type="button" id="editbutton">编辑</button>
				<button class="btn btn-success" type="button" id="new2ji">新增二级菜单</button>
				<button class="btn btn-success" type="button" id="newbutton">新增按钮</button>
	</div>
	<div class="row">
		<div class="col-lg-6">
			<div class="form-group">
				<label class="col-sm-5 control-label">编号:</label>
				<div class="col-sm-7">
				   <input id="Id" class="form-control" type="text" name="Id" readonly="readonly" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-6">
			<div class="form-group">
				<label class="col-sm-5 control-label">父级编号:</label>
				<div class="col-sm-7">
				   <input id="ParId" class="form-control" type="text" name="ParId" readonly="readonly" />
				</div>
			</div>
		</div>
	</div>
		<div class="row">
		<div class="col-lg-6">
			<div class="form-group">
				<label class="col-sm-5 control-label">菜单名称:</label>
				<div class="col-sm-7">
				   <input id="MenuName" class="form-control" type="text" name="MenuName"  readonly="readonly"/>
				</div>
			</div>
		</div>
	</div>
		<div class="row">
		<div class="col-lg-6">
			<div class="form-group">
				<label class="col-sm-5 control-label">是否锁定:</label>
				<div class="col-sm-7">
				   <input id="islock" class="form-control" type="text" name="islock" readonly="readonly" />
				</div>
			</div>
		</div>
	</div>
		<div class="row">
		<div class="col-lg-6">
			<div class="form-group">
				<label class="col-sm-5 control-label">页面URL:</label>
				<div class="col-sm-7">
				   <input id="Url" class="form-control" type="text" name="Url"  readonly="readonly"/>
				</div>
			</div>
		</div>
	</div>
		<div class="row">
		<div class="col-lg-6">
			<div class="form-group">
				<label class="col-sm-5 control-label">是否按钮:</label>
				<div class="col-sm-7">
				   <input id="IsButton" class="form-control" type="text" name="IsButton"  readonly="readonly"/>
				</div>
			</div>
		</div>
	</div>
		<div class="row">
		<div class="col-lg-6">
			<div class="form-group">
				<label class="col-sm-5 control-label">authcode:</label>
				<div class="col-sm-7">
				   <input id="authcode" class="form-control" type="text" name="authcode"  readonly="readonly"/>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-6">
			<div class="form-group">
				<label class="col-sm-5 control-label">父级名称:</label>
				<div class="col-sm-7">
				   <input id="ParMenuName" class="form-control" type="text" name="ParMenuName"  readonly="readonly"/>
				</div>
			</div>
		</div>
	</div>
		

</div>
</td>
</tr>
</table>





<!-- 新增菜单 -->
<div tabindex="-1" class="modal inmodal" id="addNewMenubox" role="dialog" aria-hidden="true" style="display: none;">
	<div class="modal-dialog modal-sm">
		<div class="modal-content animated bounceInRight " >
		
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title">新增一级菜单</h4>
			</div>
				<div class="modal-body">
							<label>菜单名称：</label> <input  name="menuName" id="1jimenuName" type="text"/>
							<label>authcode：</label> <input name="menuName" id="1jiauthcode" type="text"/>
				</div>
				
				<div class="modal-footer">
					<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
					<button class="btn btn-primary" type="button"
						id="btnRechargeCommit" onclick="save1ji()">保存</button>
				</div>
		</div>
		<small class="font-bold"> </small>
	</div>
	<small class="font-bold"> </small>
</div>

<!-- 新增二级菜单 -->
<div tabindex="-1" class="modal inmodal" id="addNewMenubox2ji" role="dialog" aria-hidden="true" style="display: none;">
	<div class="modal-dialog modal-sm">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title">新增二级菜单</h4>
			</div>
				<div class="modal-body">
					
							<label>菜单名称：</label> <input name="menuName" id="2jimenuName" type="text"/>
				
							<label>authcode：</label> <input name="menuName" id="2jiauthcode" type="text"/>
						
							<label>Url：</label> <input name="menuName" id="2jiUrl" type="text"/>
				
				</div>
				<div class="modal-footer">
					<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
					<button class="btn btn-primary" type="button"
						id="btnRechargeCommit" onclick="save2ji()">保存</button>
				</div>
		</div>
		<small class="font-bold"> </small>
	</div>
	<small class="font-bold"> </small>
</div>
<!-- 新增按钮 -->
<div tabindex="-1" class="modal inmodal" id="addNewMenubox3ji" role="dialog" aria-hidden="true" style="display: none;">
	<div class="modal-dialog modal-sm">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title">新增按钮</h4>
			</div>
		
				<div class="modal-body">
					
							<label>按钮名称：</label> <input name="menuName" id="3jimenuName" type="text"/>
					
							<label>authcode：</label> <input name="menuName" id="3jiauthcode" type="text"/>
				
				</div>
				<div class="modal-footer">
					<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
					<button class="btn btn-primary" type="button"
						id="btnRechargeCommit" onclick="save3ji()">保存</button>
				</div>
		</div>
		<small class="font-bold"> </small>
	</div>
	<small class="font-bold"> </small>
</div>

<!-- 编辑 -->
<div tabindex="-1" class="modal inmodal" id="edit1ji" role="dialog" aria-hidden="true" style="display: none;">
	<div class="modal-dialog modal-sm">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					
				</button>
				<h4 class="modal-title">编辑一级菜单</h4>
			</div>
			
				<div class="modal-body">
				
						<input id="edit1hide" type="hidden"/>
					
							<label>菜单名称：</label> <input name="menuName" id="edit1jimenuName" type="text"  />
					
							<label>是否锁定：</label> 
							<input name="edit1radion" id="edit1radionY" type="radio" value="1" />是
							<input name="edit1radion" id="edit1radionN"  type="radio" value="0" />否
					
							<label>authcode：</label> <input name="menuName" id="edit1authcode" type="text"  />
					
				</div>
				<div class="modal-footer">
					<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
					<button class="btn btn-primary" type="button"
						id="btnRechargeCommit" onclick="edit1jiSave()">保存</button>
				</div>
		</div>
		<small class="font-bold"> </small>
	</div>
	<small class="font-bold"> </small>
</div>

<!-- 编辑2级菜单 -->
<div tabindex="-1" class="modal inmodal" id="edit2ji" role="dialog" aria-hidden="true" style="display: none;">
	<div class="modal-dialog modal-sm">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					
				</button>
				<h4 class="modal-title">编辑二级菜单</h4>
			</div>
		
				<div class="modal-body">
				
						<input id="edit2hide" type="hidden"/>
					
							<label>菜单名称：</label> <input name="menuName" id="edit2jimenuName" type="text"  />
					
					
							<label>URL：</label> <input name="menuName" id="edit2jiurl" type="text"  />
					
					
							<label>是否锁定：</label> 
							<input name="edit2radion" id="edit2radionY" type="radio" value="1" />是
							<input name="edit2radion" id="edit2radionN"  type="radio" value="0" />否
				
					
							<label>authcode：</label> <input name="menuName" id="edit2authcode" type="text"  />
					
				</div>
				<div class="modal-footer">
					<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
					<button class="btn btn-primary" type="button"
						id="btnRechargeCommit" onclick="edit2jiSave()">保存</button>
				</div>
		</div>
		<small class="font-bold"> </small>
	</div>
	<small class="font-bold"> </small>
</div>
<!-- 编辑3级菜单 -->
<div tabindex="-1" class="modal inmodal" id="edit3ji" role="dialog" aria-hidden="true" style="display: none;">
	<div class="modal-dialog modal-sm">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					
				</button>
				<h4 class="modal-title">编辑按钮</h4>
			</div>
			
				<div class="modal-body">
				
						<input id="edit3hide" type="hidden"/>
					
							<label>按钮名称：</label> <input name="menuName" id="edit3jimenuName" type="text"  />
				
					
							<label>是否锁定：</label> 
							<input name="edit3radion" id="edit3radionY" type="radio" value="1" />是
							<input name="edit3radion" id="edit3radionN"  type="radio" value="0" />否
				
				
							<label>authcode：</label> <input name="menuName" id="edit3authcode" type="text"  />
				
				
				</div>
				<div class="modal-footer">
					<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
					<button class="btn btn-primary" type="button"
						id="btnRechargeCommit" onclick="edit3jiSave()">保存</button>
				</div>
		
		</div>
		<small class="font-bold"> </small>
	</div>
	<small class="font-bold"> </small>
</div>
<input type="hidden" value="<%=menuJson%>" id="menuJson"/>
<script>
//构建空间树

$('#detail').hide();
var detail="";
var $checkableTree;
init();
function init(){
	
	var result=base64decode($("#menuJson").val());
	if (result!=""){
  		 $checkableTree=$('#treeview11').treeview({
  	        data: result,
  	        levels: 1,
  	        showIcon: false,
  	        showCheckbox: true,
  	        onNodeChecked: function(event, node) {
  	        	$checkableTree.treeview('uncheckAll', { silent: true });
  	        	$checkableTree.treeview('checkNode', [ node.nodeId, { silent: true } ]);
  	        	ClickItem();
  	        },
  	        onNodeUnchecked: function (event, node) {
  	        	$('#detail').hide();
  	        	detail="";
  	        	$checkableTree.treeview('uncheckNode', [ node.nodeId, { silent: true } ]);
  	        }
  	      });
          $('#myModal').modal('show');
      } 
}

	var expandstatus=0;//展开状态
	// 展开/折叠
	$('#btnExpanAll').on('click', function (e) {
		if(expandstatus==0){
			expandstatus=1;
			 $checkableTree.treeview('expandAll', { levels: 10, silent: true });
	      }else{
	    	  expandstatus=0;
	    	  $checkableTree.treeview('collapseAll', { silent: true });
	      }
	  
	});
	//对勾选中事件
	function ClickItem(){
		$('#detail').show();
		var currId=$checkableTree.treeview('getChecked')[0].id;
		var IsButton=$checkableTree.treeview('getChecked')[0].isbutton;
		var par={
				
				"parId":currId
		};
		$.post("<%=basePath%>/admintools/menudetail",par,function(da){
			//console.log(da);
			var d=jQuery.parseJSON(da);
			detail=d;
			if(d.jibie==1)
			{
				$('#newbutton').hide();
				$('#new2ji').show();
			}
			if(d.jibie==2)
			{
				$('#newbutton').show();
				$('#new2ji').hide();
			}
			if(d.jibie==3)
			{
				$('#newbutton').hide();
				$('#new2ji').hide();
			}
			$('#Id').val(d.id);
			$('#ParId').val(d.parid);
			$('#MenuName').val(d.menuname);
			$('#islock').val(d.islock);
			$('#Url').val(d.url);
			$('#IsButton').val(d.isbutton);
			$('#authcode').val(d.authcode);
			$('#ParMenuName').val(d.parMenuName);
		});
	}
	//编辑菜单
	$('#editbutton').click(function(){
		if(detail.jibie==1){
			$('#edit1hide').val(detail.id);
			$('#edit1jimenuName').val(detail.menuname);
			$('#edit1authcode').val(detail.authcode);
			if(detail.islock==true)
			{
				$('#edit1radionY').prop("checked",true);
			}
			else
			{
				$('#edit1radionN').prop("checked",true);
			}
			$('#edit1ji').modal('show');
		}
		else if(detail.jibie==2){
			$('#edit2hide').val(detail.id);
			$('#edit2jimenuName').val(detail.menuname);
			$('#edit2authcode').val(detail.authcode);
			$('#edit2jiurl').val(detail.url);
			if(detail.islock==true)
			{
				$('#edit2radionY').prop("checked",true);
			}
			else
			{
				$('#edit2radionN').prop("checked",true);
			}
			$('#edit2ji').modal('show');
		}
		else{
			$('#edit3hide').val(detail.id);
			$('#edit3jimenuName').val(detail.menuname);
			$('#edit3authcode').val(detail.authcode);
			if(detail.islock==true)
			{
				$('#edit3radionY').prop("checked",true);
			}
			else
			{
				$('#edit3radionN').prop("checked",true);
			}
			$('#edit3ji').modal('show')
		}
	});
	//1级菜单编辑保存
	function edit1jiSave(){
		var menuname=$('#edit1jimenuName').val();
		var authcode=$('#edit1authcode').val();
		var chec=$("input[name='edit1radion']:checked").val();
		var islock=(chec==1||chec=='1')?1:0;
		if(menuname == ''){
			alert("请输入菜单名称!");
			return;
		}
		if(authcode == ''){
			alert("请输入authcode!");
			return;
		}
		var par={
				"id":$('#edit1hide').val(),
				"menuname":menuname,
				"authcode":authcode,
				"islock":islock,
				"url":""}
		postedit(par);
	}
	//2级菜单编辑保存
	function edit2jiSave(){
		var menuname=$('#edit2jimenuName').val();
		var authcode=$('#edit2authcode').val();
		var chec=$("input[name='edit2radion']:checked").val();
		var url=$('#edit2jiurl').val();
		var islock=(chec==1||chec=='1')?1:0;
		if(menuname == ''){
			alert("请输入菜单名称!");
			return;
		}
		if(authcode == ''){
			alert("请输入authcode!");
			return;
		}
		if(url == ''){
			alert("请输入URL!");
			return;
		}
		var par={
				"id":$('#edit2hide').val(),
				"menuname":menuname,
				"authcode":authcode,
				"url":url,
				"islock":islock};
			postedit(par);
		
	}
	
	//3级菜单编辑保存
	function edit3jiSave(){
		var menuname=$('#edit3jimenuName').val();
		var authcode=$('#edit3authcode').val();
		var chec=$("input[name='edit3radion']:checked").val();
		var islock=(chec==1||chec=='1')?1:0;
		if(menuname == ''){
			alert("请输入菜单名称!");
			return;
		}
		if(authcode == ''){
			alert("请输入authcode!");
			return;
		}
		var par={
				"id":$('#edit3hide').val(),
				"menuname":menuname,
				"authcode":authcode,
				"islock":islock,
				"url":""};
		postedit(par);
	}
	//新增菜单
	$('#addNewMenu').click(function(){
		//清除数据
		$("#1jimenuName").val('');
		$("#1jiauthcode").val('');
		$('#addNewMenubox').modal('show');
	});
	//新增2级菜单
	$('#new2ji').click(function(){
		$("#2jimenuName").val('');
		$("#2jiauthcode").val('');
		$("#2jiUrl").val('');
		$('#addNewMenubox2ji').modal('show');
	});
	//新增按钮
	$('#newbutton').click(function(){
		$("#3jimenuName").val('');
		$("#3jiauthcode").val('');
		$('#addNewMenubox3ji').modal('show');
	});
	//保存1级菜单
	function save1ji(){
		var menuName = $("#1jimenuName").val();
		var authcode=$("#1jiauthcode").val();
		
		if(menuName == ''){
			alert("请输入菜单名称!");
			return;
		}
		if(authcode == ''){
			alert("请输入authcode!");
			return;
		}
		var par={"parid":0,
				"menuname":menuName,
				"authcode":authcode,
				"islock":0,
				"isbutton":0,
				"url":""};
		postsave(par);
	}
	//保存2级菜单
	function save2ji(){
		var menuName = $("#2jimenuName").val();
		var authcode=$("#2jiauthcode").val();
		var url=$("#2jiUrl").val();
		if(menuName == ''){
			alert("请输入菜单名称!");
			return;
		}
		if(url==''){
			alert("请输入Url!");
			return;
		}
		if(authcode == ''){
			alert("请输入authcode!");
			return;
		}
		var par={"parid":detail.id,
				"menuname":menuName,
				"authcode":authcode,
				"url":url,
				"islock":0,
				"isbutton":0};
			postsave(par);
	}
	//保存3级菜单
	function save3ji(){
		var menuName = $("#3jimenuName").val();
		var authcode=$("#3jiauthcode").val();
		if(menuName == ''){
			alert("请输入按钮名称!");
			return;
		}
		if(authcode == ''){
			alert("请输入authcode!");
			return;
		}
		var par={"parid":detail.id,
				"menuname":menuName,
				"authcode":authcode,
				"isbutton":1,
				"islock":0,
				"url":""};
		
		postsave(par);
	}
	//保存菜单
	function postsave(par){
		$.post("<%=basePath%>/admintools/addnewmenu",par,function(d){
			if(d>0){
				alert('添加菜单成功!');
				$('#addNewMenubox').modal('hide');
				$('#addNewMenubox2ji').modal('hide');
				$('#addNewMenubox3ji').modal('hide');
				detail="";
				$('#detail').hide();
				window.location.href = window.location.href;
				}
			else{
				alert('添加菜单失败!');
				}
		});
	}
	//编辑保存菜单
	function postedit(par){
		$.post("<%=basePath%>/admintools/editmenu",par,function(d){
			if(d>0){
				alert('编辑菜单成功!');
				$('#edit1ji').modal('hide');
				$('#edit2ji').modal('hide');
				$('#edit3ji').modal('hide');
				detail="";
				$('#detail').hide();
				window.location.href = window.location.href;
				}
			else{
				alert('编辑菜单失败!');
				}
		});
	}
</script>

