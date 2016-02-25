<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${baseInfo.name } -- 视频新闻列表</title>
<meta http-equiv="keywords" content="城市学院,城市学院新闻,东莞理工学院城市学院">
<meta http-equiv="description" content="城市学院网站,东莞理工学院城市学院网站">
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/qianduan/css/bootstrap-responsive.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/qianduan/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/qianduan/css/docs.min.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/qianduan/css/main.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/qianduan/css/screen.css">
<script src="<%=request.getContextPath() %>/resources/qianduan/js/jquery.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/qianduan/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/qianduan/js/pic_hover.js"></script>
<body>
	<jsp:include page="/jsp/template/top.jsp"/>	
	<div class="container">
		<jsp:include page="navs.jsp"/>	
		<div class="channel col-md-3">
			<!-- 栏目树 -->
			<div class="widget">
				<h4 class="title ">${cname}</h4>
				<c:forEach var="c" items="${cs}">
					<c:choose>
						<c:when test="${c.id == channel.id }">
							<a href="<%=request.getContextPath() %>/channel/${c.id }" class="btn btn-default btn-block active">${c.name }</a>
						</c:when>
						<c:otherwise>
							<a href="<%=request.getContextPath() %>/channel/${c.id }" class="btn btn-default btn-block">${c.name }</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</div>
			<!-- 标签云 -->
			<div class="widget relative">
				<h4 class="title_line">标签云</h4>
				<div class="content tag-cloud">
					<c:forEach var="kw" items="${kws }">
						<a href="<%=request.getContextPath() %>/keyword/${kw.name}">${kw.name}</a>
					</c:forEach>
				</div>
			</div>
		</div>
		<div class="col-md-9">
			<div class="channel_title col-md-99">
				<h4 class="title_line">${channel.name}</h4>
			</div>
			<c:if test="${datas.total le 0 }">
				<span>该栏目还没有任何图片新闻....</span>
			</c:if>
			<c:if test="${datas.total gt 0 }">
				<div class="row-fluid col-md-99 channel_right">
					<c:forEach begin= "0" end="3" items="${datas.datas}" var="video">
						<div class="span3">
							<a href="title" class="thumbnail">
								<div class="fan">
									<img src="<%=request.getContextPath() %>/resources/video/thumbnail/${video.picName}" alt="${video.videoName }">
								</div>
								<div class="imgtxt clearfix">
									<p class="title">${video.title}</p>
									<p>
										<span class="pull-left">
											<fmt:formatDate value="${video.publishDate }" pattern="yyyy-MM-dd" />
										</span>
										<span class="pull-right">${video.viewCount}</span></p>
								</div>
							</a>
						</div>
					</c:forEach>
				</div>
				<div class="row-fluid col-md-99 channel_right">
					<c:forEach begin= "4" end="7" items="${datas.datas}" var="video">
						<div class="span3">
							<a href="title" class="thumbnail">
								<div class="fan">
									<img src="<%=request.getContextPath() %>/resources/video/thumbnail/${video.picName}" alt="${video.videoName }">
								</div>
								<div class="imgtxt clearfix">
									<p class="title">${video.title}</p>
									<p>
										<span class="pull-left">
											<fmt:formatDate value="${video.publishDate }" pattern="yyyy-MM-dd" />
										</span>
										<span class="pull-right">${video.viewCount}</span></p>
								</div>
							</a>
						</div>
					</c:forEach>
				</div>
			</c:if>
			<%-- <div class="t_c">
				<jsp:include page="/jsp/index_pager.jsp">
					<jsp:param value="${datas.total }" name="totalRecord"/>
					<jsp:param value="channel/${channel.id}" name="url"/>
				</jsp:include>
			</div> --%>
		</div>

	</div>
	<jsp:include page="/jsp/template/bottom.jsp"/>
</body>
</html>
