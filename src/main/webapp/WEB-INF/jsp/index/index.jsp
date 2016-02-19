<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>欢迎访问${baseInfo.name }</title>
<!-- 设置关键字，方便被搜索引擎搜索到 -->
<meta http-equiv="keywords" content="城市学院,东莞理工学院城市学院">   
<meta http-equiv="description" content="城市学院网站,东莞理工学院城市学院网站">     
<meta charset="UTF-8">
<!-- 移动端访问 -->
<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
<title>城市学院</title>	
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/qianduan/css/bootstrap-responsive.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/qianduan/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/qianduan/css/docs.min.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/qianduan/css/main.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/qianduan/css/screen.css">
<script src="<%=request.getContextPath() %>/resources/qianduan/js/jquery.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/qianduan/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/qianduan/js/pic_hover.js"></script>
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
</head>
<body>
<jsp:include page="/jsp/template/top.jsp"/>	
<jsp:include page="/jsp/template/body.jsp"/>
<jsp:include page="/jsp/template/bottom.jsp"/>
<!-- 搜索弹层 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="display: none;">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<form action="" id="search-form" method="GET">
				<input type="hidden" name="app" value="search">
				<input type="hidden" name="controller" value="index">
				<input type="hidden" name="action" value="search">
				<input type="hidden" id="type" name="type" value="all">
				<input type="search" placeholder="输入关键字" name="wd" id="head_wd" value="">
			</form>
			<div class="close" data-dismiss="modal" aria-label="Close">×</div>
		</div>
	</div>
</div>
<a href="#" id="back-to-top"></a>
</body>
</html>