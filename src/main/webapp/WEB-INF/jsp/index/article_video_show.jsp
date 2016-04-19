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
				<!-- <span class="pull-right ">分享</span>  -->
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
		<div class="clearfix">
			<!-- 多说评论框 start -->
				<div class="ds-thread" data-thread-key="${vid.id }" data-title="${vid.title }" 
						data-url="<%=request.getContextPath() %>/videoNews/${vid.id}"></div>
			<!-- 多说评论框 end -->
		</div>
	</div>
	<jsp:include page="/jsp/template/bottom.jsp"/>
</body>
<!-- JiaThis Button BEGIN -->
<script type="text/javascript" src="http://v3.jiathis.com/code/jiathis_r.js?move=0" charset="utf-8"></script>
<!-- JiaThis Button END -->
<!-- 多说公共JS代码 start (一个网页只需插入一次) -->
<script type="text/javascript">
var duoshuoQuery = {short_name:"testwxh"};
	(function() {
		var ds = document.createElement('script');
		ds.type = 'text/javascript';ds.async = true;
		ds.src = (document.location.protocol == 'https:' ? 'https:' : 'http:') + '//static.duoshuo.com/embed.js';
		ds.charset = 'UTF-8';
		(document.getElementsByTagName('head')[0] 
		 || document.getElementsByTagName('body')[0]).appendChild(ds);
	})();
	</script>
<!-- 多说公共JS代码 end -->
</html>
