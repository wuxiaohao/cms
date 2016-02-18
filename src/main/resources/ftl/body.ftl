<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="container">
	<#-- 滚动新闻图片 -->
	<div class="content_top row-fluid">
		<div class='span12' id="myCarousel">
			<div class="carousel slide" id="carousel-405390">
				<ol class="carousel-indicators">
				<#list newsPics as newsPic>
					<#if newsPic_index == 0>
						<li class="active" data-slide-to="${newsPic_index}" data-target="#carousel-405390"></li>
					<#else>
						<li data-slide-to="${newsPic_index}" data-target="#carousel-405390"></li>
					</#if>
				</#list>
				</ol>
				<div class="carousel-inner">
					<#list newsPics as newsPic>
						<#if newsPic_index == 0>
							<div class="item active">
								<a href="topic/${newsPic.topic.id}" title="${newsPic.topic.title}">
									<img alt="${newsPic.newName}" src="<%=request.getContextPath()%>/resources/upload/${newsPic.newName}" />
								</a>
								<#if newsPic.topic.title?length gt 37>
									<h5 class="carousel-title">${newsPic.topic.title[0..37]}...</h5>
								<#else>
									<h5 class="carousel-title">${newsPic.topic.title}</h5>
								</#if>
							</div>
						<#else>
							<div class="item">
								<a href="topic/${newsPic.topic.id}" title="${newsPic.topic.title}">
									<img alt="${newsPic.oldName}" src="<%=request.getContextPath()%>/resources/upload/${newsPic.newName}" />
								</a>
								<#if newsPic.topic.title?length gt 37>
									<h5 class="carousel-title">${newsPic.topic.title[0..37]}...</h5>
								<#else>
									<h5 class="carousel-title">${newsPic.topic.title}</h5>
								</#if>
							</div>
						</#if>
					</#list>
				</div> <a data-slide="prev" href="#carousel-405390" class="left carousel-control">‹</a> <a data-slide="next" href="#carousel-405390" class="right carousel-control">›</a>
			</div>
		</div>
	</div>
	
	<div id="news_content" class="row-fluid">
		<#-- 文章列表1 -->
		<div class="span8 article pull-left">
			<div class="panel panel-info">
				<div class="panel-heading clearfix">
					<h3 class="panel-title fl">校务公开</h3>
					<a href="" class="fr">更多 >></a>
				</div>
				<div class="panel-body">
					<div>
						<img src="../img/news_top1.jpg" class="topNews_img" />
						<a class="topNews_title">2015年度高中学业水平考试获奖教师</a>
						<p class="topNews_content">2015年度高中学业水平考试获奖教师2015年度高中学业水平考试获奖教师2015年度高中学业水平考试获奖教师 2015年度高中学师2015年度高中学业水平师2015年度高中学业水平...
							<a>[全文]</a></p>
						<hr>
					</div>
					<dl class="article_list">
						<dd class="clearfix"><a href='#' class="article_link">2015年度高中学业水平考试获奖教师<span class="date">2015-12-18</span></a></dd>
						<dd class="clearfix"><a href='#' class="article_link">昭通学院附属中学岗位公开选调报名<span class="date">2015-12-18</span></a></dd>
						<dd class="clearfix"><a href='#' class="article_link">公开选调报名登记及资格审查情况<span class="date">2015-12-18</span></a></dd>
						<dd class="clearfix"><a href='#' class="article_link">昭通学院附中9月1日按期开学<span class="date">2015-12-18</span></a></dd>
						<dd class="clearfix"><a href='#' class="article_link">关于转发昭市编的批复的通知<span class="date">2015-12-18</span></a></dd>
						<dd class="clearfix"><a href='#' class="article_link">师专附中学生对班主任满意度测评表<span class="date">2015-12-18</span></a></dd>
						<dd class="clearfix"><a href='#' class="article_link">师专附中学生对班主任满意度测评表<span class="date">2015-12-18</span></a></dd>						
					</dl>
				</div>
			</div>
		</div>
		<#-- 视频列表 -->
		<div class="span4 article pull-left">
			<div class="panel panel-info">
				<div class="panel-heading clearfix">
					<h3 class="panel-title fl">${vids.cname}</h3>
					<a href="<%=request.getContextPath()%>/channel/${vids.cid}" class="fr">更多 >></a>
				</div>
				<div class="panel-body">
					<div class="row">
						<#list vids.videos as video>
						<div class="col-md-6 col-sm-4 col-xs-6">
							<a class="thumbnail">
								<img src="<%=request.getContextPath()%>/resources/video/thumbnail/${video.picName}" alt="${video.title}">
								<p class="imgtxt">
									<#if video.title?length gt 8>
										${video.title[0..8]}...
									<#else>
										${video.title}
									</#if>
									<br/>
									${(video.publishDate)?string("yyy-MM-dd")}
								</p>
							</a>
						</div>
						</#list>
					</div>
				</div>
			</div>
		</div>
	</div>
	<#-- 滚动宣传图片 -->
	<div class="row-fluid">
		<div class='span12' id="myCarousel">
			<div class="carousel slide" id="carousel-405391">
				<ol class="carousel-indicators">
					<#list pics as pic>
						<#if pic_index == 0>
							<li class="active" data-slide-to="${pic_index}" data-target="#carousel-405391"></li>
						<#else>
							<li data-slide-to="${pic_index}" data-target="#carousel-405391"></li>
						</#if>
					</#list>
				</ol>
				<div class="carousel-inner">
					<#list pics as pic>
						<#if pic_index == 0 >
							<div class="item active relative">
								<#--如果是链接到外部-->
								<#if pic.linkType == 1>
								<a target="_blank" href="<%=request.getContextPath()%>/${pic.linkUrl}">
									<img alt="${pic.oldName}" src="<%=request.getContextPath()%>/resources/indexPic/${pic.newName}"/>
						  	 	</a>
						  	 	<#else>
						  	 	<a href="${pic.linkUrl}">
									<img alt="${pic.oldName}" src="<%=request.getContextPath()%>/resources/indexPic/${pic.newName}"/>
						  	 	</a>
						  	 	</#if>
							</div>
						<#else>
							<div class="item">
								<#--如果是链接到外部-->
								<#if pic.linkType == 1>
								<a target="_blank" href="<%=request.getContextPath()%>/${pic.linkUrl}">
									<img alt="${pic.oldName}" src="<%=request.getContextPath()%>/resources/indexPic/${pic.newName}"/>
						  	 	</a>
						  	 	<#else>
						  	 	<a href="${pic.linkUrl}">
									<img alt="${pic.oldName}" src="<%=request.getContextPath()%>/resources/indexPic/${pic.newName}"/>
						  	 	</a>
						  	 	</#if>	
							</div>
						</#if>
					</#list>
				</div>
			</div>
		</div>
	</div>
	
	<div id="data_content">
		<div id="data_content_right" class="row-fluid">
			<#--文章列表2-->
			<div class="span6 article">
				<div class="panel panel-info">
					<div class="panel-heading clearfix">
						<h3 class="panel-title fl">最近更新</h3>
						<a href="" class="fr">更多  >></a>
					</div>
					<div class="panel-body">
						<div>
							<img src="../img/news_top2.jpg" class="topNews_img" />
							<a class="topNews_title">2015年度高中学业水平考试获奖教师</a>
							<p class="topNews_content">2015年度高中学业水平考试获奖教师2015年度高中学业水平考试获奖教师2015年度高中学业水平考试获奖教师 2015年度高中学...
								<a>[全文]</a></p>
							<hr>
						</div>
						<dl class="article_list">
							<dd class="clearfix"><a href='#' class="article_link">2015年度高中学业水平考试获奖教师<span class="date">2015-12-18</span></a></dd>
							<dd class="clearfix"><a href='#' class="article_link">昭通学院附属中学岗位公开选调报名<span class="date">2015-12-18</span></a></dd>
							<dd class="clearfix"><a href='#' class="article_link">公开选调报名登记及资格审查情况<span class="date">2015-12-18</span></a></dd>
							<dd class="clearfix"><a href='#' class="article_link">昭通学院附中9月1日按期开学<span class="date">2015-12-18</span></a></dd>
							<dd class="clearfix"><a href='#' class="article_link">关于转发昭市编的批复的通知<span class="date">2015-12-18</span></a></dd>
							<dd class="clearfix"><a href='#' class="article_link">师专附中学生对班主任满意度测评表<span class="date">2015-12-18</span></a></dd>
							<dd class="clearfix"><a href='#' class="article_link">师专附中学生对班主任满意度测评表<span class="date">2015-12-18</span></a></dd>
							<dd class="clearfix"><a href='#' class="article_link">云南招考频道信息<span class="date">2015-12-18</span></a></dd>
							<dd class="clearfix"><a href='#' class="article_link">云南招考频道信息<span class="date">2015-12-18</span></a></dd>
							<dd class="clearfix"><a href='#' class="article_link">云南招考频道信息<span class="date">2015-12-18</span></a></dd>
							<dd class="clearfix"><a href='#' class="article_link">师专附中学生对班主任满意度测评表<span class="date">2015-12-18</span></a></dd>
						</dl>
					</div>
				</div>
			</div>
			<#--文章列表3-->
			<div class="span6 article">
				<div class="panel panel-info">
					<div class="panel-heading clearfix">
						<h3 class="panel-title fl">最近更新</h3>
						<a href="" class="fr">更多  >></a>
					</div>
					<div class="panel-body">
						<div>
							<img src="../img/news_top3.jpg" class="topNews_img" />
							<a class="topNews_title">2015年度高中学业水平考试获奖教师</a>
							<p class="topNews_content">2015年度高中学业水平考试获奖教师2015年度高中学业水平考试获奖教师2015年度高中学业水平考试获奖教师 2015年度高中学...
								<a>[全文]</a></p>
							<hr>
						</div>
						<dl class="article_list">
							<dd class="clearfix"><a href='#' class="article_link">2015年度高中学业水平考试获奖教师<span class="date">2015-12-18</span></a></dd>
							<dd class="clearfix"><a href='#' class="article_link">昭通学院附属中学岗位公开选调报名<span class="date">2015-12-18</span></a></dd>
							<dd class="clearfix"><a href='#' class="article_link">公开选调报名登记及资格审查情况<span class="date">2015-12-18</span></a></dd>
							<dd class="clearfix"><a href='#' class="article_link">昭通学院附中9月1日按期开学<span class="date">2015-12-18</span></a></dd>
							<dd class="clearfix"><a href='#' class="article_link">关于转发昭市编的批复的通知<span class="date">2015-12-18</span></a></dd>
							<dd class="clearfix"><a href='#' class="article_link">师专附中学生对班主任满意度测评表<span class="date">2015-12-18</span></a></dd>
							<dd class="clearfix"><a href='#' class="article_link">师专附中学生对班主任满意度测评表<span class="date">2015-12-18</span></a></dd>
							<dd class="clearfix"><a href='#' class="article_link">云南招考频道信息<span class="date">2015-12-18</span></a></dd>
							<dd class="clearfix"><a href='#' class="article_link">云南招考频道信息<span class="date">2015-12-18</span></a></dd>
							<dd class="clearfix"><a href='#' class="article_link">云南招考频道信息<span class="date">2015-12-18</span></a></dd>
							<dd class="clearfix"><a href='#' class="article_link">师专附中学生对班主任满意度测评表<span class="date">2015-12-18</span></a></dd>
						</dl>
					</div>
				</div>
			</div>
		</div>
	</div>
	<#--友情链接-->
	<div class="indexlink">
		<h4 class="title">友情链接</h4>
		<ul class="dis" style="display: block;">
			<li class="yqlj">
				<iframe width="100%" height="100px" src="link.jsp" scrolling="no" frameborder="0" allowtransparency="true"></iframe>
			</li>
		</ul>
	</div>
</div>


<#macro indexTopicList indexTopic tupian="">
	<div class="panel-heading clearfix">
		<h3 class="panel-title fl">${indexTopic.cname}</h3>
		<a href="channel/${indexTopic.id}" class="fr">更多 >></a>
	</div>
	<div class="panel-body">
		<div>
			<img src="<%=request.getContextPath()%>/resources/qianduan/img/${tupian}" class="topNews_img" />
			<#list indexTopic.topics as topic>
				<#if topic_index ==0>
					<a class="topNews_title">
						<#if topic.title?length gt 30>
							${topic.title[0..30]}...
						<#else>
							${topic.title}
						</#if>
					</a>
					<p class="topNews_content">
						<#if topic.summary?length gt 40>
							${topic.summary[0..40]}...
						<#else>
							${topic.summary}
						</#if>
						<a href="topic/${topic.id}">[全文]</a></p>
					<hr>
				</#if>
			</#list>
		</div>
		<dl class="article_list">
			<#list indexTopic.topics as topic>
				<#if topic_index !=0>
					<dd class="clearfix">
						<a href='topic/${topic.id}' class="article_link">
							<#if topic.title?length gt 20>
								${topic.title[0..20]}...
							<#else>
								${topic.title}
							</#if>
							<span class="date">${(topic.publishDate)?string("yyy-MM-dd")}</span>
						</a>
					</dd>
				</#if>
			</#list>
		</dl>
	</div>
</#macro>