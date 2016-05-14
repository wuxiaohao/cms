<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发生错误</title>
</head>
<body>
<div id="container">
	<div id = "error">
		<img src="${pageContext.servletContext.contextPath }/resources/img/ErrorLogo.jpg">
		<div style="text-align: center;color: red;font-size: 18px">
			<span>${exception.message }</span>
			<a class="ajaxify" href="<%=request.getContextPath()%>/admin/user/showMySelf">返回首页</a>
		</div>
	</div>
</div>
</body>
</html>