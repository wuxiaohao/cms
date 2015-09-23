<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8"/>
<title><spring:message code="title"/></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta content="" name="description"/>
<meta content="" name="author"/>
<!-- 获取总资源 -->
<%@ include file="/jsp/common.jsp"%>
</head>
<body class="page-404-full-page">


<div class="row">
	<div class="col-md-12 page-404">
		<div class="number">
			 404
		</div>
		<div class="details">
			<h3>不好意思！页面不存在</h3>
			<p>
				我们没有发现你所请求的路径.<br/>
				<a href="admin">返回主页</a> 或尝试搜索你所要的内容. 
			</p>
				<div class="input-group input-medium">
					<input type="text" class="form-control" name="keyword" id="keyword">
					<span class="input-group-btn">
					<button type="submit" class="btn blue" onclick="keyword();"><i class="fa fa-search"></i></button>
					</span>
				</div>
				<!-- /input-group -->
		</div>
	</div>
</div>
<script>
function keyword(){ 
var keyword=$("#keyword").val();
window.open("http://www.baidu.com/s?wd="+keyword);
}
</script>
</body>

<!-- END BODY -->
</html>
