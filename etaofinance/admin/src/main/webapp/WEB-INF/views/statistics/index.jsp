<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@page import="com.etaofinance.entity.common.PagedResponse"%>
<%@page import="java.util.ArrayList"%>
    <%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.entity.domain.DataStatistics"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%
	String basePath =PropertyUtils.getProperty("java.admin.url");
    SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	List<DataStatistics> list = (List<DataStatistics>) request.getAttribute("listData");
%>
<% if(list==null||list.size()==0) 
{%>
	=====暂无数据=====
<%}else{%>
<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<tbody>
		<tr>
		<td rowspan=3  align="center">会员统计</td>
		</tr>
		<tr>
		<td>注册用户</td>
		<td><%=list.get(0).getUsercount() %> 位</td>
		</tr>
		<tr>
		<td>合格投资人</td>
		<td><%=list.get(0).getInvestor() %> 位</td>
		</tr>
		<tr>
		<td rowspan=3  align="center">网站资金统计</td>
		</tr>
		<tr>
		<td>会员充值</td>
		<td><%=list.get(0).getRecharge()%> 元</td>
		</tr>
		<tr>
		<td>会员提现</td>
		<td><%=list.get(0).getCash() %> 元</td>
		</tr>
		<tr>
		<td rowspan=5  align="center">项目统计</td>
		</tr>
		<tr>
		<td>预热项目</td>
		<td><%=list.get(0).getYr() %> 个</td>
		</tr>
		<tr>
		<td>融资中项目</td>
		<td><%=list.get(0).getRzz() %> 个</td>
		</tr>
		<tr>
		<td>融资成功项目</td>
		<td><%=list.get(0).getRzsuccess() %> 个</td>
		</tr>
		<tr>
		<td>融资失败项目</td>
		<td><%=list.get(0).getRzfail() %> 个</td>
		</tr>
		<tr>
		<td align="center" valign="middle">融资统计</td>
		<td>成功融资</td>
		<td><%=list.get(0).getRzsucamount() %> 元</td>
		</tr>
	</tbody>
</table>
<%} %>