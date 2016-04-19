<%@page import="com.etaofinance.entity.domain.GlobalConfigModel"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.etaofinance.entity.common.PagedResponse"%>
<%@page import="com.etaofinance.core.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%
PagedResponse<GlobalConfigModel> responsePageList=	(PagedResponse<GlobalConfigModel>)request.getAttribute("listData");
List<GlobalConfigModel> data = responsePageList.getResultList();
if(data == null){
	data = new ArrayList<GlobalConfigModel>();
}

							%>
<table class="table table-striped table-bordered table-hover dataTables-example">
						<thead>
							<tr>
							<th>变量说明</th>
								<th>变量名称</th>
								<th>变量值</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<%for (int i = 0; i < data.size(); i++) { %>
							<tr>
								<td><%=data.get(i).getRemark()%></td>
								<td><%=data.get(i).getKeyName()%></td>
								<td><input type="text" class="form-control" id="show<%=data.get(i).getId()%>" value="<%=data.get(i).getValue()%>" disabled="true" /> 
									<inupt type="hidden" id="hid<%=data.get(i).getId()%>" value="<%=data.get(i).getValue()%>" /></td>
								<td>
								<a id="btne<%=data.get(i).getId()%>"  href="javascript:void(0)" onclick="EditConfig('<%=data.get(i).getId()%>')">编辑</a>
								<a id="btns<%=data.get(i).getId()%>"  name="save" href="javascript:void(0)" onclick="SaveConfig('<%=data.get(i).getId()%>','<%=data.get(i).getKeyName()%>')">保存</a>
								<a id="btnc<%=data.get(i).getId()%>"  name="cancle" href="javascript:void(0)" onclick="CancleConfig('<%=data.get(i).getId()%>')">取消</a>
						</td>
							</tr>

							<%
								}
							%>
						</tbody>
					</table>
<%=PageHelper.getPage(responsePageList.getPageSize(),
		responsePageList.getCurrentPage(), responsePageList.getTotalRecord(),
		responsePageList.getTotalPage())%>
		
<script>
$(function() {
	//隐藏保存 取消按钮
	$("[name='save']").each(function() {
		$(this).hide();
	});
	$("[name='cancle']").each(function() {
		$(this).hide();
	});
	$('#btnaddboxshow').click(function(){
		$('#addconfig').show();
		$('#mengban').show();
	});
	//添加框取消事件
	 $('#boxcancle').click(function(){
		ClaenBox();
	}); 
	//添加新的配置
	$('#btnaddconfig').click(function(){
		 Addconfig();
	});
});
</script>