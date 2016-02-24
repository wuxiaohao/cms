<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 导航栏目（待抽取） -->
<div class="localAddress">
	<div class="row-fluid">
		<div class="span12">
			<ul class="breadcrumb">
				<li>
					<span>你的位置：</span>
					<a href="<%=request.getContextPath()%>/index">主页</a> 
					<span class="divider"></span>
				</li>
				<c:if test="${empty(navs) }">
					<li>全站检索</li>
				</c:if>
				<c:if test="${!empty(navs) }">
				<c:forEach var="nav" items="${navs }" >
					<li>
					<c:if test="${nav.customLink == 0 }">
						<a href="<%=request.getContextPath() %>/channel/${nav.id}">${nav.name}</a> 
					</c:if>
					<c:if test="${nav.customLink == 1 }">
						<a href="${nav.customLinkUrl}">${nav.name}</a> 
					</c:if>
					<span class="divider"></span>
					</li>
				</c:forEach>
				</c:if>
			</ul>
		</div>
	</div>
</div>