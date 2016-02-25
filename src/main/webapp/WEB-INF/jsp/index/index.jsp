<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>欢迎访问${baseInfo.name }</title>
<%@ include file="common.jsp" %> 
</head>
<body>
<jsp:include page="/jsp/template/top.jsp"/>	
<jsp:include page="/jsp/template/body.jsp"/>
<jsp:include page="/jsp/template/bottom.jsp"/>
</body>
</html>