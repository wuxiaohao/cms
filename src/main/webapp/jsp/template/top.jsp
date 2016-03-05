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
							<li><a href="mailto:11@111.com">联系我们</a></li>
						</ol>
						<div class="input-group clearfix">
							<div class="header_input">
							<input type="text" id="search_con" class="form-control" placeholder="请输入关键字"></div>
							<div class="input-group-btn pc_search">
								<button id="search_btn" class="btn btn-default">搜索</button>
							</div>
							<div class="input-group-btn phone_search">
								<button id="search_btn" class="btn btn-default" data-toggle="modal" data-target="#myModal">搜索</button>
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
					<li>
						<a href="<%=request.getContextPath()%>/channel/39" class="ti">高教视野</a>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/channel/55" class="ti">学科资源</a>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/channel/59" class="ti">图说天下</a>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/channel/57" class="ti">人文校园</a>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/channel/50" class="ti">党团工作</a>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/channel/40" class="ti">校园之窗</a>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/channel/41" class="ti">教育科研</a>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/channel/46" class="ti">校园文化</a>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/channel/53" class="ti">城院要闻</a>
					</li>
			</ul>
		</nav>
	</div>
</header>
<script type="text/javascript">
$("#search_btn").click(function(){
	var sc = $("#search_con").val();
	if(sc==""||sc=="请输入关键字") {
		alert("你需要输入相应的检索内容");
	} else {
		window.location.href=$("#ctx").val()+"/search/"+sc;
	}
});
</script>
<!-- 搜索弹层 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="display: none;">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<form action="" id="search-form" method="POST">
				<input type="search" placeholder="输入关键字" name="wd" id="head_wd" value="">
			</form>
			<div class="close" data-dismiss="modal" aria-label="Close">×</div>
		</div>
	</div>
</div>
<a href="#" id="back-to-top"></a>