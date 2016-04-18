<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>${baseInfo.name } -- ${topic.title }</title>
<%@ include file="common.jsp" %> 
<body>
	<jsp:include page="/jsp/template/top.jsp"/>	
	<div class="container">
		<jsp:include page="navs.jsp"/>	
		<div class="row ">
			<main class="col-md-8 main-content ">
				<article id="57 " class="post tag-laravel tag-xin-ban-ben-fa-bu tag-laravel-5-2 ">
					<header class="post-head ">
						<h2 class="post-title ">${topic.title }</h2>
						<section class="post-meta clearfix">
							<span class="author pull-left"> 作者：${topic.author}&nbsp;;&nbsp;浏览次数：${topic.viewCount}&nbsp;•&nbsp;</span>
							<time class="post-date pull-left" datetime="" title="<fmt:formatDate value="${topic.publishDate }" pattern="yyyy-MM-dd HH:mm:ss"/> ">
								<fmt:formatDate value="${topic.publishDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
							</time>
							<span class="pull-right ">
								<!-- JiaThis Button BEGIN -->
								<div class="jiathis_style">
									<span class="jiathis_txt">分享到：</span>
									<a class="jiathis_button_weixin"></a>
									<a class="jiathis_button_cqq"></a>
									<a class="jiathis_button_tsina"></a>
									<a class="jiathis_button_douban"></a>
									<a class="jiathis_button_qzone"></a>
									<a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jtico jtico_jiathis" target="_blank"></a>
								</div>
								<script type="text/javascript" src="http://v3.jiathis.com/code/jia.js" charset="utf-8"></script>
								<!-- JiaThis Button END -->
							</span> 
						</section>
					</header>
					<section class="post-content ">
						<p>${topic.content }</p>
					</section>
					<div class="post-footer clearfix ">
						<div class="pull-left tag-list ">
							<i class="fa fa-folder-open-o "></i>
							<c:if test="${hasKey }">
								<c:forEach items="${kwst }" var="k">
									<a href="<%=request.getContextPath() %>/keyword/${k}">${k }</a>,
								</c:forEach>
							</c:if>
						</div>
					</div>
				</article>
				<div class="about-author clearfix">
					<div class="widget ">
						<h4 class="title ">文章附件</h4>
						<div class="content community ">
							<p>
								<c:if test="${!hasAtts}">该文章没有附件</c:if>
								<c:if test="${hasAtts }">
									<c:forEach items="${atts }" var="att">
										<a href="<%=request.getContextPath()%>/resources/upload/${att.newName}">
										<i class="fa fa-comments "></i>${att.oldName }
										</a>
									</c:forEach>
								</c:if>
							</p>
						</div>
					</div>
				</div>
				<div class="prev-next-wrap clearfix ">
					<c:if test="${!empty(topic.preId) }">
						<a class="btn btn-default " href="<%=request.getContextPath() %>/topic/${topic.preId}">上一篇文章<i class="fa fa-angle-right fa-fw "></i></a> 
					</c:if>
					&nbsp;
					<c:if test="${!empty(topic.nextId) }">
						<a class="btn btn-default " href="<%=request.getContextPath() %>/topic/${topic.nextId}">下一篇文章<i class="fa fa-angle-right fa-fw "></i></a>
					</c:if>
				</div>
			</main>
			<aside class="col-md-4 sidebar ">
				<div class="widget ">
					<h4 class="title ">标签云</h4>
					<div class="content tag-cloud">
						<c:forEach var="kw" items="${kws }">
							<a href="<%=request.getContextPath() %>/keyword/${kw.name}">${kw.name}</a>
						</c:forEach>
					</div>
				</div>
				<div class="widget ">
					<h4 class="title ">热门文章</h4>
					<c:forEach items="${hotTop}" var="hot" >
						<c:if test="${fn:length(hot.title) > '13' }">
							<a href="<%=request.getContextPath() %>/topic/${hot.id}" class="btn btn-default btn-block ">${fn:substring(hot.title,0,13)}...</a> 
						</c:if>
						<c:if test="${fn:length(hot.title) <= '13' }">
							<a href="<%=request.getContextPath() %>/topic/${hot.id}" class="btn btn-default btn-block ">${hot.title}</a>
						</c:if>
					</c:forEach>
				</div>
			</aside>
		</div>
	</div>
	<jsp:include page="/jsp/template/bottom.jsp"/>
</body>
</html>
