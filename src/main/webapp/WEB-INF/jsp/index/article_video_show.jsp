<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<script src="http://html5media.googlecode.com/svn/trunk/src/html5media.min.js"></script>
<title>${baseInfo.name } -- ${video.title }</title>
<%@ include file="common.jsp" %> 
<body>
	<jsp:include page="/jsp/template/top.jsp"/>	
	<div class="container">
		<jsp:include page="navs.jsp"/>	
		<header class="post-head ">
			<h2 class="post-title ">${video.title }</h2>
			<section class="post-meta clearfix">
				<span class="author pull-left"> 作者：${video.author}&nbsp;;&nbsp;浏览次数：${video.viewCount}&nbsp;•&nbsp;</span>
				<time class="post-date pull-left" datetime="" title="<fmt:formatDate value="${video.publishDate }" pattern="yyyy-MM-dd HH:mm:ss"/> ">
					<fmt:formatDate value="${video.publishDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</time>
				<!-- <span class="pull-right ">分享</span> -->
			</section>
		</header>
	</div>
	<div class="vContainer clearfix">
		<div class="videoScreen">				
			<video src="<%=request.getContextPath() %>/resources/video/${video.videoName}" width="100%" controls="controls"></video>
		</div>
		<div class="videolist">
			<ul>
				<c:forEach items="${vids}" var="vid">
				<li>
					<a href="<%=request.getContextPath() %>/videoNews/${vid.id}" class="clearfix">
						<img src="<%=request.getContextPath() %>/resources/video/thumbnail/${vid.picName}" alt="${vid.videoName }" class="fl" />
						<p class="fr">
							<c:if test="${fn:length(vid.title) > '5' }">
								${fn:substring(vid.title,0,5)}...
							</c:if>
							<c:if test="${fn:length(vid.title) <= '5' }">
								${vid.title }
							</c:if><br/>
							<span>播放次数：${vid.viewCount }</span>
						</p>
					</a>
				</li>
				</c:forEach>
			</ul>
		</div>	
	</div>
	<jsp:include page="/jsp/template/bottom.jsp"/>
</body>
</html>
