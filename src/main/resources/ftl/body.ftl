<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="container">
	<#-- 滚动新闻图片 -->
	<div class="content_top row-fluid clearfix">
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
									<h5 class="carousel-title">${newsPic.topic.title[0..32]}...</h5>
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
									<h5 class="carousel-title">${newsPic.topic.title[0..32]}...</h5>
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
	
	<div id="news_content" class="row-fluid clearfix">
		<#-- 文章列表1 -->
		<div class="span8 article pull-left">
			<div class="panel panel-info">
				<@indexTopicList indexTopic=ts["1"] tupian="news_top1.jpg" />
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
							<a href="<%=request.getContextPath() %>/videoNews/${video.id}" class="thumbnail">
								<img src="<%=request.getContextPath()%>/resources/video/thumbnail/${video.picName}" alt="${video.title}">
								<div class="imgtxt clearfix">
									<#if video.title?length gt 8>
										<p class="title">${video.title[0..8]}...</p>
									<#else>
										<p class="title">${video.title}</p>
									</#if>
									<p><span class="pull-left">${(video.publishDate)?string("yyy-MM-dd")}</span>
									<span class="pull-right">${video.viewCount}次</span></p>
								</div>
							</a>
						</div>
						</#list>
					</div>
				</div>
			</div>
		</div>
	</div>
	<#-- 滚动宣传图片 -->
	<div class="row-fluid clearfix">
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
								<#--如果是链接到内部-->
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
					<@indexTopicList indexTopic=ts["2"] tupian="news_top2.jpg" />
				</div>
			</div>
			<#--文章列表3-->
			<div class="span6 article">
				<div class="panel panel-info">
					<@indexTopicList indexTopic=ts["3"] tupian="news_top3.jpg" />
				</div>
			</div>
		</div>
	</div>
	<#--友情链接-->
	<div class="indexlink">
		<h4 class="title">友情链接</h4>
		<ul class="dis" style="display: block;">
			<li class="yqlj">
				<iframe width="100%" height="100px" src="<%=request.getContextPath() %>/jsp/template/link.jsp" scrolling="no" frameborder="0" allowtransparency="true"></iframe>
			</li>
		</ul>
	</div>
</div>


<#macro indexTopicList indexTopic tupian="">
	<div class="panel-heading clearfix">
		<h3 class="panel-title fl">${indexTopic.cname}</h3>
		<a href="channel/${indexTopic.cid}" class="fr">更多 >></a>
	</div>
	<div class="panel-body">
		<div>
			<img src="<%=request.getContextPath()%>/resources/qianduan/img/${tupian}" class="topNews_img" />
			<#list indexTopic.topics as topic>
				<#if topic_index ==0>
					<a href='topic/${topic.id}' class="topNews_title">
						<#if topic.title?length gt 20>
							${topic.title[0..20]}...
						<#else>
							${topic.title}
						</#if>
					</a>
					<p class="topNews_content">
						<#if topic.summary??>
							<#if topic.summary?length gt 60>
								${topic.summary[0..60]}...
							<#else>
								${topic.summary}
							</#if>
							<a href="topic/${topic.id}">[全文]</a></p>
						</#if>
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