<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<script src="http://html5media.googlecode.com/svn/trunk/src/html5media.min.js"></script>
<title>${baseInfo.name } -- ${channel.name}</title>
<%@ include file="common.jsp" %> 
<body>
	<jsp:include page="/jsp/template/top.jsp"/>	
	<div class="container">
		<jsp:include page="navs.jsp"/>	
		<header class="post-head ">
			<h2 class="t_c">${video.title }</h2>
			<section class="post-meta clearfix">
				<span class="author pull-left"> 作者：${video.author}&nbsp;;&nbsp;浏览次数：${video.viewCount}&nbsp;•&nbsp;</span>
				<time class="post-date pull-left" datetime="" title="<fmt:formatDate value="${video.publishDate }" pattern="yyyy-MM-dd HH:mm:ss"/> ">
					<fmt:formatDate value="${video.publishDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</time>
				<!-- <span class="pull-right ">分享</span> -->
			</section>
		 </header>
		 <video width="100%" controls="controls">
			<source src="<%=request.getContextPath() %>/resources/video/${video.videoName}" type="video/mp4">
		 </video>
		 <br><br>
	</div>
	<jsp:include page="/jsp/template/bottom.jsp"/>
</body>
</html>
