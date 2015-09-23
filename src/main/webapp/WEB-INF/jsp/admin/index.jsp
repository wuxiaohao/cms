<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<meta charset="utf-8" />

<title>网站后台管理系统</title>

<meta content="width=device-width, initial-scale=1.0" name="viewport" />

<meta content="" name="description" />

<meta content="" name="author" />

<!-- 获取总资源 -->
<%@ include file="/jsp/common.jsp"%>
<script type="text/javascript"> 
    
/* if(navigator.userAgent.indexOf("MSIE")!=-1){
        window.location.href="${pageContext.servletContext.contextPath }/brower.oa";//这是直接在当前页跳转
} */
if (window != top){
top.location.href = location.href; 
}

function updatecode() {
		window.location.reload();
	}
	
function exitSystem() {
	//alert($("#contextPath").val()+"/admin/logout");
	window.parent.location.href = $("#contextPath").val()+"/admin/logout";
}
	
</script>
</head>


<!-- BEGIN BODY -->

<body class="page-header-fixed page-quick-sidebar-push-content">
<input type="hidden" id="contextPath" value="<%=request.getContextPath()%>"/>

<!-- 获取头 -->
<%@ include file="head.jsp"%>
 <div class="clearfix">
</div>
<!-- BEGIN CONTAINER -->
<div class="page-container">

	<!-- BEGIN SIDEBAR1 -->
	<%@ include file="menu.jsp"%>
	<!-- END SIDEBAR1 -->
	<!-- BEGIN CONTENT -->
	<div class="page-content-wrapper">
		<div class="page-content">
			<!-- BEGIN STYLE CUSTOMIZER -->
			
			<!-- END STYLE CUSTOMIZER -->
			
			<div class="page-content-body">
				<!-- HERE WILL BE LOADED AN AJAX CONTENT -->
			</div>
		</div>
		<!-- BEGIN CONTENT -->
	</div>
</div>
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
	<!-- END CONTAINER -->

	<%@ include file="foot.jsp"%> 

</body>

</html>
