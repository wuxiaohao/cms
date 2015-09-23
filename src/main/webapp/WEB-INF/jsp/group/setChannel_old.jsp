<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/admin/main.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/zTree/zTreeStyle.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/tree/jquery.ztree.core-3.5.min.js"></script>
<!-- 导入ztree可选框的js包 -->
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/tree/jquery.ztree.excheck-3.5.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/core/jquery.cms.core.js"></script>
<!-- 必须引入dwr的engine.js -->
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<!-- 将java的类引入 -->
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/dwrService.js"></script>
<!-- 导入设置ztree的js文件 -->
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/admin/setChannel.js"></script>
</head>
<body>
<div id="content">
	<h3 class="admin_link_bar">
		<jsp:include page="inc.jsp"></jsp:include>
	</h3>
	<div>
		<c:forEach items="${cids }" var="cid">
			<input type="hidden" name="cids" value="${cid }">
		</c:forEach>
		<input type="hidden" id="gid" value="${group.id }"/>
		<!-- 获取整颗树的路径 -->
		<input type="hidden" id="treePath" value="<%=request.getContextPath()%>/admin/channel/treeAll"/>
		<div style="padding-left:10px;font-size:12px;">当前组名称:${group.name }</div>
		<div id="tree" class="ztree"></div>
	</div>
</div>
</body>
</html>