<%@page import="com.etaofinance.core.util.ParseHelper"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.etaofinance.entity.common.PagedResponse"%>
<%@page import="com.etaofinance.core.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.entity.Withdrawform"%>
<%@page import="com.etaofinance.core.enums.WithdrawStatus"%>
<%@page import="java.util.List"%>
<%
	String basePath =PropertyUtils.getProperty("java.admin.url");
%>

<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr>
			<th width="5%">序号</th>
			<th>姓名</th>
			<th>提现金额</th>
			<th>提现银行</th>
			<th>银行卡号</th>
			<th>申请时间</th>
			<th>操作时间</th>
			<th>到账时间</th>
			<th>拒绝理由</th>
			<th>状态</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>

		<%
			PagedResponse<Withdrawform> data = (PagedResponse<Withdrawform>) request.getAttribute("listData");
			List<Withdrawform> list = data.getResultList();
			if (list == null) {
				list = new ArrayList<Withdrawform>();
			}
			for (int i = 0; i < list.size(); i++) {
		%>
		<tr>
			<td><%=(i+1)%></td>
			<td><%=list.get(i).getCreatename()%></td>
			<td>¥<%=list.get(i).getAmount()%></td>
			<td><%=list.get(i).getBankname()%></td>
			<td><%=list.get(i).getAccountno()%></td>
			<td><%=ParseHelper.ToDateString(list.get(i).getCreatetime(), "")%></td>
			<td><%=ParseHelper.ToDateString(list.get(i).getOpttime(), "")%></td>
			<td><%=ParseHelper.ToDateString(list.get(i).getSuccesstime(), "")%></td>
			<td><%=list.get(i).getRemark()%></td>
			<td><%=WithdrawStatus.getEnum(list.get(i).getStatus()).desc()%></td>
			<%
				if(WithdrawStatus.Cking.value()==list.get(i).getStatus()){
			%>
			<td><a href="javascript:void(0)"
				onclick="setstatus(<%=list.get(i).getId()%>,2)">通过</a> <a
				href="javascript:void(0)"
				onclick="setstatus(<%=list.get(i).getId()%>,-1)">拒绝</a></td>
			<%
				}else{
			%>
			<td>--</td>
			<%
				}
			%>
		</tr>
		<%
			}
		%>
	</tbody>
</table>


<%=PageHelper.getPage(data.getPageSize(),
					data.getCurrentPage(), data.getTotalRecord(),
					data.getTotalPage())%>


<script>			
					function setstatus(id,status){		
	    var paramaters = {
                "id": id
            };
       var url ="<%=basePath%>/finance/agreewithdraw",ct="是否确认通过审核？";
       if(status!=2){
    	   url ="<%=basePath%>/finance/refusewithdraw";
    	   ct="是否确认拒绝审核？";
    	   
       }
	   var la= layer.confirm(ct, {
		    btn: ['确认','取消'], //按钮
		    shade: false //显示遮罩
		},function(){
			layer.close(la);
			$.ajax({
		           type: 'POST',
		           url: url,
		           data: paramaters,
		           success: function (result) {		    
		        	      alert("成功!");	  
		            	   window.location.href = "<%=basePath%>/finance/withdrawlist";		
		           }
		       });
		});       	    
	}
</script>