<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.core.enums.ProjectStatus"%>
<%@page import="com.etaofinance.core.util.EnumHelper"%>
<%@page import="java.util.List"%>
<%@page import="com.etaofinance.core.util.HtmlHelper"%>
<%@page import="com.etaofinance.entity.AccountInfo"%>
<%
	String basePath = PropertyUtils.getProperty("java.admin.url");

AccountInfo curUser = (AccountInfo) request.getAttribute("currentUser");
%>
<ul class="list-group">
	<li class="list-group-item"><label  >用户名：</label><label ><%=curUser.getLoginname()%></label></li>
	<li class="list-group-item"><label  >旧密码：</label><input	type="password"  id="oldPwd"  value=""><label style="color: red;visibility:hidden;">不能为空</label></li>
	<li class="list-group-item"><label >新密码：</label><input  	type="password" id="newPwd" /><label style="color: red;visibility:hidden;">不能为空</label></li>
	<li class="list-group-item"><label  >重复新密码：</label><input  type="password" id="newPwdAgain" /><label style="color: red;visibility:hidden;">新旧密码不一致</label></li>
</ul>

<input type="hidden" value="" id="memberId">
<button class="btn btn-w-m btn-primary" id="modifyPwd">提交</button>

<script>

var modifypwd = {
		
		modifyuri:"<%=basePath%>/account/updateuser",

		data : {
			"oldPwd" : $('#oldPwd').val().trim(),

			"newPwd" : $('#newPwd').val().trim(),

			"newPwdAgain" : $('#newPwdAgain').val().trim()
		},
		
		setdata:function(){
			modifypwd.data.oldPwd = $('#oldPwd').val().trim();
			
			modifypwd.data.newPwd = $('#newPwd').val().trim();
			
			modifypwd.data.newPwdAgain = $('#newPwdAgain').val().trim();
			
		},
		
	inittips:function(){
		$('#oldPwd').next('label').css('visibility', 'hidden');
		$('#newPwd').next('label').css('visibility', 'hidden');
		$('#newPwdAgain').next('label').css('visibility', 'hidden');
	},
		validatepwd : function(oldpwd, newpwd,newpwdagain) {

			if (null == oldpwd || '' == oldpwd) {

				$('#oldPwd').next('label').css('visibility', 'visible');

				return false;
			}
			
			if (null == newpwd || '' == newpwd) {

				$('#newPwd').next('label').css('visibility', 'visible');

				return false;

			}
		
			if (newpwd != newpwdagain) {

				$('#newPwdAgain').next('label').css('visibility', 'visible');

				return false;
			}
		
			return true;
		},
		modify : function(uri, data) {

			$.post(uri, data, function(res) {

				if (res>0) {
					alert("操作成功");
					window.location.href = "<%=basePath %>/account/logoff";
				} else {
					alert("操作失败：旧密码不正确");
				}

			});
		}
	};

	$('#modifyPwd').click(
			function() {

				modifypwd.inittips();
	
				modifypwd.setdata();
				
				var validateres = modifypwd.validatepwd(modifypwd.data.oldPwd,modifypwd.data.newPwd,
						modifypwd.data.newPwdAgain);

				if (false == validateres) {
					return;
				}
				modifypwd.modify(modifypwd.modifyuri, modifypwd.data);

			});
</script>