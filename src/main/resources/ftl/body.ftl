<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="container">
	<div class="content_top row-fluid">
		<div class='span12' id="myCarousel">
			<div class="carousel slide" id="carousel-405390">
				<ol class="carousel-indicators">
				<#list newsPics as newsPic>
					<#if newsPic_index == 0>
						<li class="active" data-slide-to="0" data-target="#carousel-405390"></li>
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
								<img alt="${newsPic.newName}" src="<%=request.getContextPath()%>/resources/upload/${newsPic.newName}" />
							</a>
							<#if newsPic.topic.title?length gt 37>
								<h5 class="carousel-title">${newsPic.topic.title[0..37]}...</h5>
							<#else>
								<h5 class="carousel-title">${newsPic.topic.title}</h5>
							</#if>
						</#if>
					</div>
					</#list>
				</div> <a data-slide="prev" href="#carousel-405390" class="left carousel-control">‹</a> <a data-slide="next" href="#carousel-405390" class="right carousel-control">›</a>
			</div>
		</div>
	</div>
	<div id="news_content" class="row-fluid">
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
		<div class="span4 article pull-left">
			<div class="panel panel-info">
				<div class="panel-heading clearfix">
					<h3 class="panel-title fl">视频新闻</h3>
					<a href="" class="fr">更多 >></a>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-md-6 col-sm-4 col-xs-6">
							<a class="thumbnail">
								<img src="../img/pic.png" alt="">
								<p class="imgtxt">关于这张图片的详情<br/>2015-12-24</p>
							</a>
						</div>
						<div class="col-md-6 col-sm-4 col-xs-6">
							<a class="thumbnail">
								<img src="../img/pic.png" alt="">
								<p class="imgtxt">关于这张图片的详情<br/>2015-12-24</p>
							</a>
						</div>
						<div class="col-md-6 col-sm-4 col-xs-6">
							<a class="thumbnail">
								<img src="../img/pic.png" alt="">
								<p class="imgtxt">关于这张图片的详情<br/>2015-12-24</p>
							</a>
						</div>
						<div class="col-md-6 col-sm-4 col-xs-6">
							<a class="thumbnail">
								<img src="../img/pic.png" alt="">
								<p class="imgtxt">关于这张图片的详情<br/>2015-12-24</p>
							</a>
						</div>
						<div class="col-md-6 col-sm-4 col-xs-6">
							<a class="thumbnail">
								<img src="../img/pic.png" alt="">
								<p class="imgtxt">关于这张图片的详情<br/>2015-12-24</p>
							</a>
						</div>
						<div class="col-md-6 col-sm-4 col-xs-6">
							<a class="thumbnail">
								<img src="../img/pic.png" alt="">
								<p class="imgtxt">关于这张图片的详情！<br/>2015-12-24</p>										
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row-fluid">
		<div class='span12' id="myCarousel">
			<div class="carousel slide" id="carousel-405391">
				<ol class="carousel-indicators">
					<li class="active" data-slide-to="0" data-target="#carousel-405391">
					</li>
					<li data-slide-to="1" data-target="#carousel-405391">
					</li>
					<li data-slide-to="2" data-target="#carousel-405391">
					</li>
				</ol>
				<div class="carousel-inner">
					<div class="item active relative">
						<a href="">
							<img alt="" src="../img/711.jpg"/>
					    </a>
					</div>
					<div class="item">
						<a href="">
							<img alt="" src="../img/b1.jpg"/>
						</a>	
					</div>
					<div class="item">
						<a href="">
							<img alt="" src="../img/c1.jpg"/>
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="data_content">
		<div id="data_content_right" class="row-fluid">
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
	<div class="indexlink">
		<h4 class="title">友情链接</h4>
		<ul class="dis" style="display: block;">
			<li class="yqlj">
				<iframe width="100%" height="100px" src="news.html" scrolling="no" frameborder="0" allowtransparency="true"></iframe>
			</li>
		</ul>

	</div>
</div>