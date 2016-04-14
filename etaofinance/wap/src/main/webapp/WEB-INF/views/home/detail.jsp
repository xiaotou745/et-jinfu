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
        <p><span><img src="<%=staticResPath%>/etao-crowdfunding/img/p/home/detail/img_1.jpg"></span><b><img src="<%=staticResPath%>/etao-crowdfunding/img/p/home/detail/index_6.png"></b></p>
    </div>
    <div class="detail-one">
        <div class="container">
            <div class="one-list">
                <h3><b>易淘餐厅华腾世纪总部公园店</b><span>融资中</span></h3>
                <p><b></b><span>北京市朝阳区</span></p>
                <dd>以前所未有的就餐感受，开启互联网餐厅新时代。以前所未有的就餐感受。</dd>
                <h4>预计</h4>
                <ul class="detail-ul">
                    <li><span>12%+5%</span><b>年化收益</b></li>
                    <li><span>¥26万</span><b>目标金额</b></li>
                    <li><span>￥4500</span><b>起投金额</b></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="detail-one">
        <div class="container">
            <div class="two-list">
                <h3>领投方</h3>
                <ul>
                    <li>IDG资本</li>
                    <li>IDG资本</li>
                    <li class="list-head">IDG资本</li>
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
                    1
                </div>
                <div class="pane hide" data-panelindex="1">
                    2
                </div>
                <div class="pane hide" data-panelindex="2">
                    <div class="detail-box" style="display:none">
                        <p>认证合格投资人后才可查看，去<a href="###">登录</a></p>
                        <p>认证合格投资人后才可查看，去<a href="###">认证</a></p>
                    </div>
                    <div class="detail-box-list container">
                        <div class="box-list-input">
                            <textarea placeholder="在此处写上您的评论或问题" data-role="comment"></textarea>
                            <button disabled>发布</button>
                        </div>
                        <div class="box-list-ul">
                            <ul>
                                <li>
                                    <p><b></b></p>
                                    <p><span>IDG资本</span><span>2015.06.29</span><span>这个项目很靠谱，好项目不等人。</span><a class="ul-del" href="###">删除</a></p>
                                    <p><i></i></p>
                                </li>
                                <li>
                                    <p><b></b></p>
                                    <p><span>客服小美</span><span>2015.06.29</span><span>回复<a href="###">IDG资本:</a>感谢您对该项目的认可。</span><a class="ul-del" href="###">删除</a></p>
                                    <p><i></i></p>
                                </li>
                                <li>
                                    <p><b></b></p>
                                    <p><span>IDG资本</span><span>2015.06.29</span><span>这个项目很靠谱，好项目不等人。</span><a class="ul-del" href="###">删除</a></p>
                                    <p><i></i></p>
                                </li>
                                <li>
                                    <p><b></b></p>
                                    <p><span>IDG资本</span><span>2015.06.29</span><span>这个项目很靠谱，好项目不等人。</span><a class="ul-del" href="###">删除</a></p>
                                    <p><i></i></p>
                                </li>
                                <li>
                                    <p><b></b></p>
                                    <p><span>IDG资本</span><span>2015.06.29</span><span>这个项目很靠谱，好项目不等人。</span><a class="ul-del" href="###">删除</a></p>
                                    <p><i></i></p>
                                </li>
                                <li>
                                    <p><b></b></p>
                                    <p><span>IDG资本</span><span>2015.06.29</span><span>这个项目很靠谱，好项目不等人。</span><a class="ul-del" href="###">删除</a></p>
                                    <p><i></i></p>
                                </li>
                                <li>
                                    <p><b></b></p>
                                    <p><span>IDG资本</span><span>2015.06.29</span><span>这个项目很靠谱，好项目不等人。</span><a class="ul-del" href="###">删除</a></p>
                                    <p><i></i></p>
                                </li>
                            </ul>
                        </div>
                    </div>
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
    <!-- <section>
		<div class="detail-fixed">
			<div class="container">
				<h3>回复IDG资本</h3>
				<textarea placeholder="在此处写上您的评论或问题"></textarea>
				<button disabled>发布</button>
			</div>
		</div>
	</section> -->
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

