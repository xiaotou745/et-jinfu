<%@page import="com.etaofinance.core.util.ParseHelper"%>
<%@page import="com.etaofinance.core.enums.ProjectStatus"%>
<%@page import="com.etaofinance.entity.domain.ProjectComment"%>
<%@page import="com.etaofinance.entity.ProjectImage"%>
<%@page import="com.etaofinance.entity.domain.ProjectMember"%>
<%@page import="com.etaofinance.entity.Member"%>
<%@page import="com.etaofinance.entity.domain.ProjectModel"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.etaofinance.core.util.PropertyUtils"%>
<%@page import="com.etaofinance.entity.PublicProvinceCity" %>
<%@page import="java.util.List" %>
<%@page import="com.etaofinance.entity.common.PagedResponse" %>
<%
	//网站跟地址
	String basePath = PropertyUtils.getProperty("java.wap.url");
	String imgurl=PropertyUtils.getProperty("ImageShowPath")+"/";
	//静态资源跟地址
	String staticResPath = PropertyUtils.getProperty("staticResourceUrl");
	//当前用户
	Member member=request.getAttribute("member")==null?null:(Member)request.getAttribute("member");
	//评论列表
	List<ProjectComment> commentList=(ArrayList<ProjectComment>)request.getAttribute("commentList");
	Long projectid=(Long)request.getAttribute("projectid");
	Long myid=member==null?0:member.getId();
%>
<%
                            	if(commentList!=null&&commentList.size()>0)
                            	{
                            	for(int i=0;i<commentList.size();i++)
                            	{
                            		byte isr=commentList.get(i).getIsreply();
                            		%>
                            	  <li data-commentid="<%=commentList.get(i).getId()%>" data-userid="<%=commentList.get(i).getMemberid()%>">
                                      <p><b>
                                      <img src="<%=imgurl+commentList.get(i).getHeadImage()%>">
                                      </b></p>
                                      <p><span><%=commentList.get(i).getCommontName()%></span>
                                      <span><%=ParseHelper.ToDateString(commentList.get(i).getCreatetime())%></span>
                                      <span>
                                      <%if(isr==1)
                                      {
                                    	  %>回复<a href="#"><%=commentList.get(i).getReplayName()%>:</a>
                                    	  <%
                                      } %>
                                      	<%=commentList.get(i).getContent()%>
                                      	</span>
                                      	<%
                                      	if(myid==commentList.get(i).getMemberid()&&commentList.get(i).getIsdel()!=1)
                                      	{
                                      		%>
                                      		<a class="ul-del" href="#">删除</a>
                                      		<%
                                      	}
                                      	%>
                                      </p>
                                         <p class="reply"><i></i></p>
                                  </li>
                            		
                            		<%
                            	}
                            	}else{
                            		%>
                            		 <li>暂无更多评论</li>
                            		<%
                            	}%>