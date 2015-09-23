<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript">
$(function(){
	var t = $("#tree").mytree({
		url:$("#treePath").val(),
		mine:{listChild:0,expandAll:true}
	});
});
</script>
</head>
<body>
<div id="content">
	<h3 class="admin_link_bar">
		<jsp:include page="inc.jsp"></jsp:include>
	</h3>
	<div>
		<input type="hidden" id="treePath" value="<%=request.getContextPath()%>/admin/user/userTree/${user.id}?isAdmin=${uAdmin}"/>
		<div style="padding-left:10px;font-size:12px;">当前用户名称:${user.nickname }-->${uAdmin }</div>
		<div id="tree" class="ztree"></div>
	</div>
</div>
</body>
</html>