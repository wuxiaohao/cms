<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <input type="hidden" id="ctx" value="<%=request.getContextPath()%>"/>
<div class="container">
	<div class="modal-header">
		<div class="container">
			<div class="row-fluid">
				<div class="col-md-12">
					<div class="pull-left header_left">
						<a href="#"><img src="<%=request.getContextPath()%>/resources/qianduan/img/logo.jpg" class="img-responsive logo" alt="城院新闻网" title="城院新闻网"></a>
					</div>
					<div class="pull-right header_right">
						<ol class="breadcrumb header_nav">
							<li><a href="http://csxy.dgut.edu.cn">城院首页</a></li>
							<li><a href="<%=request.getContextPath()%>/login">后台管理</a></li>
							<li><a href="#">部门新闻</a></li>
							<li><a href="mailto:${baseInfo.email}">联系我们</a></li>
						</ol>
						<div class="input-group clearfix">
							<div class="header_input">
							<input type="text" id="search_con" class="form-control" placeholder="请输入关键字"></div>
							<div class="input-group-btn pc_search">
								<button class="btn btn-default" id="search_btn">搜索</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<header class="navbar navbar-static-top bs-docs-nav" id="top" role="banner">
	<div class="container">
		<div class="navbar-header">
			<button class="navbar-toggle collapsed" type="button" data-toggle="collapse" data-target="#bs-navbar" aria-controls="bs-navbar" aria-expanded="false">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a href="<%=request.getContextPath()%>/index" class="navbar-brand">网站首页</a>
		</div>
		<nav id="bs-navbar" class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<#list navs as nav>
					<li>
					<#if nav.customLink==0>
						<a href="<%=request.getContextPath()%>/channel/${nav.id}" class="ti">${nav.name}</a>
					<#else>
						<a href="${nav.customLinkUrl}" class="ti">${nav.name}</a>
					</#if>
					</li>
				</#list>
			</ul>
		</nav>
	</div>
</header>