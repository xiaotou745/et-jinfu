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
	//静态资源跟地址
	String staticResPath = PropertyUtils.getProperty("staticResourceUrl");
	//当前用户
	Member member=request.getAttribute("member")==null?null:(Member)request.getAttribute("member");
	//项目详情
	ProjectModel detaiModel=(ProjectModel)request.getAttribute("detaiModel");
	//认购人列表
	List<ProjectMember> subList=(ArrayList<ProjectMember>)request.getAttribute("subList");
	//领头人列表
	List<ProjectMember> leadList=(ArrayList<ProjectMember>)request.getAttribute("leadList");
	//项目图列表
	List<ProjectImage> imgList=(ArrayList<ProjectImage>)request.getAttribute("imgList");
	//评论列表
	List<ProjectComment> commentList=(ArrayList<ProjectComment>)request.getAttribute("commentList");
	Long projectid=(Long)request.getAttribute("projectid");
	int isLogin=member==null?0:1;//是否登录
	int isTzr=member==null?0:(member.getLevel()>1?1:0);//是否投资人
	Long myid=member==null?0:member.getId();
%>

 <link rel="stylesheet" href="<%=staticResPath%>/etao-crowdfunding/css/p/home/detail.css">
    <div class="g-wrap">
        <div class="g-views">
            
<ul class="list-nav hide" data-role="hide-tabs">
                <li class="nav-on" data-tabindex="0">项目概况</li>
                <li data-tabindex="1">回报说明</li>
                <li data-tabindex="2">项目交流</li>
                <li data-tabindex="3">认投情况</li>
            </ul>
   <section class="content">
   		 <div class="detail-banner">
        <p><span><img src="<%=detaiModel.getProjectimage()%>"></span>
        <b><img src="<%=staticResPath%>/etao-crowdfunding/img/p/home/detail/index_<%=detaiModel.getTypeid()==1?"6":"7"%>.png"></b></p>
    </div>
    <div class="detail-one">
        <div class="container">
            <div class="one-list">
                <h3><b><%=detaiModel.getProjectname()%></b><span><%=ProjectStatus.getEnum(detaiModel.getProjectstatus()).desc()%></span></h3>
                <p><b></b><span><%=detaiModel.getProvinceName()+detaiModel.getCityName()%></span></p>
                <dd><%=detaiModel.getDescription()%></dd>
<!--                 <h4>预计</h4> -->
                <ul class="detail-ul">
                    <li><span><%=detaiModel.getInComeStr()%></span><b>年化收益</b></li>
                    <li><span><%=detaiModel.getAmountStr()%></span><b>目标金额</b></li>
                    <li><span><%=detaiModel.getUnitpriceStr()%></span><b>起投金额</b></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="detail-one">
        <div class="container">
            <div class="two-list">
                <h3>领投方</h3>
                <ul>
                <%if(leadList.size()>0)
                {
	                for(int i=0;i<leadList.size();i++)
	                {%>
	                 <li><%=leadList.get(i).getMemberName()%></li>
	                <%
	                }
                }else{
                %>
                 	<li>暂无领投人</li>
                <%}%>
                </ul>
            </div>
        </div>
    </div>
    <div class="detail-one">
        <div class="three-list">
            <ul class="list-nav" data-role="tabs">
                <li class="nav-on" data-tabindex="0">项目概况</li>
                <li data-tabindex="1">回报说明</li>
                <li data-tabindex="2">项目交流</li>
                <li data-tabindex="3">认投情况</li>
            </ul>
            <div class="panes con">
                <div class="pane" data-panelindex="0">
                    <%
                    for(int i=0;i<imgList.size();i++)
                    {
                    	//项目概况Wap图
                    	if(imgList.get(i).getTypeid()==12)
                    	{%>
                    	<img src="<%=imgList.get(i).getUrl()%>">
                    	<%}
                    }
                    %>
                </div>
                <div class="pane hide" data-panelindex="1">
                     <%
                    for(int i=0;i<imgList.size();i++)
                    {
                    	//项目概况Wap图
                    	if(imgList.get(i).getTypeid()==22)
                    	{%>
                    	<img src="<%=imgList.get(i).getUrl()%>">
                    	<%}
                    }
                    %>
                </div>
                <div class="pane hide" data-panelindex="2">
                	<%
                	if(isLogin==0)
                	{//未登录
                		%>
                		<div class="detail-box">
                        <p>认证合格投资人后才可查看，去<a href="<%=basePath%>/me/login?reUrl=<%=basePath%>/home/detail?projectid=<%=projectid%>">登录</a></p>
                    	</div>
                		<% 
                	}
                	else if(isLogin==1&&isTzr==0)
                	{//非投资人
                		%>
                		<div class="detail-box">
                        <p>认证合格投资人后才可查看，去<a href="<%=basePath%>/me/login?reUrl=<%=basePath%>/me/certificationinvestor">认证</a></p>
                    	</div>
                		<% 
                	}else
                	{//显示评论信息
                	%>
                		 
                    <div class="detail-box-list container">
                        <div class="box-list-input">
                            <textarea placeholder="在此处写上您的评论或问题" data-role="comment"></textarea>
                            <button disabled>发布</button>
                        </div>
                        <div class="box-list-ul">
                            <ul>
                            	<%
                            	if(commentList.size()>0)
                            	{
                            	for(int i=0;i<commentList.size();i++)
                            	{
                            		byte isr=commentList.get(i).getIsreply();
                            		%>
                            	  <li>
                                      <p><b></b></p>
                                      <p><span><%=commentList.get(i).getCommontName()%></span>
                                      <span><%=commentList.get(i).getCreatetime()%></span>
                                      <span>
                                      <%if(isr==1)
                                      {
                                    	  %>回复<a href="###"><%=commentList.get(i).getReplayName()%>:</a>
                                    	  <%
                                      } %>
                                      	<%=commentList.get(i).getContent()%>
                                      	</span>
                                      	<%
                                      	if(myid==commentList.get(i).getMemberid())
                                      	{
                                      		%>
                                      		<a class="ul-del" href="###">删除</a>
                                      		<%
                                      	}
                                      	%>
                                      </p>
                                      <p><i></i></p>
                                  </li>
                            		
                            		<%
                            	}
                            	}else{
                            		%>
                            		 <li>暂无评论</li>
                            		<%
                            	}%>
                            </ul>
                        </div>
                    </div>
                		
                	<%}
                	%>
                   
                </div>
                <div class="pane hide" data-panelindex="3">
                    <div class="detail-head-list container">
                        <ul>
                            <li>
                                <p>马化腾</p>
                                <p class="head-list-one">领投人</p>
                                <p>认投金额:<b>￥50000.00</b><span>2015.06.29</span></p>
                            </li>
                            <li>
                                <p>马**</p>
                                <p></p>
                                <p>认投金额:<b>￥50000.00</b><span>2015.06.29</span></p>
                            </li>
                            <li>
                                <p>马**</p>
                                <p></p>
                                <p>认投金额:<b>￥50000.00</b><span>2015.06.29</span></p>
                            </li>
                            <li>
                                <p>马**</p>
                                <p></p>
                                <p>认投金额:<b>￥50000.00</b><span>2015.06.29</span></p>
                            </li>
                            <li>
                                <p>马**</p>
                                <p></p>
                                <p>认投金额:<b>￥50000.00</b><span>2015.06.29</span></p>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

   </section>
    <footer class="foot-list">
        <div class="foot-one">
            <a href="###"></a><b>225</b></div>
        <!-- 按钮以及样式 按钮认证和我要领头是默认样式、成功按钮样式名为‘two-me’、预热按钮样式名为'two-you'-->
        <div class="foot-two">
            <button disabled>认证</button>
        </div>
    </footer>
    
        </div>
    </div>
    <!-- error: point:pagejs is not defined; modname islayout/normal-flexible -->
    <script src="<%=staticResPath%>/etao-crowdfunding/js/p/home/detail.js"></script>

