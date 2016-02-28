<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>${baseInfo.name } -- ${channel.name}</title>
<%@ include file="common.jsp" %> 
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
					<c:forEach begin= "0" end="3" items="${datas.datas}" var="pic">
						<div class="span3">
							<a href="<%=request.getContextPath() %>/imgsNews/${pic.id}" class="thumbnail">
								<div class="fan">
									<img src="<%=request.getContextPath() %>/resources/picTopic/${pic.picName}" alt="${pic.title }">
								</div>
								<div class="imgtxt clearfix">
									<p class="title">
										<c:if test="${fn:length(pic.title) > '10' }">
											${fn:substring(pic.title,0,10) }...
										</c:if>
										<c:if test="${fn:length(pic.title) <= '10' }">
											${pic.title}
										</c:if>
									</p>
									<p>
										<span class="pull-left">
											${pic.publishDate}
										</span>
										<span class="pull-right">${pic.viewCount}次</span>
									</p>
								</div>
							</a>
						</div>
					</c:forEach>
				</div>
				<div class="row-fluid col-md-99 channel_right">
					<c:forEach begin= "4" end="7" items="${datas.datas}" var="pic">
						<div class="span3">
							<a href="<%=request.getContextPath() %>/imgsNews/${pic.id}" class="thumbnail">
								<div class="fan">
									<img src="<%=request.getContextPath() %>/resources/picTopic/${pic.picName}" alt="${pic.title }">
								</div>
								<div class="imgtxt clearfix">
									<p class="title">
										<c:if test="${fn:length(pic.title) > '10' }">
											${fn:substring(pic.title,0,10) }...
										</c:if>
										<c:if test="${fn:length(pic.title) <= '10' }">
											${pic.title}
										</c:if>
									</p>
									<p>
										<span class="pull-left">
											${pic.publishDate}
										</span>
										<span class="pull-right">${pic.viewCount}次</span>
									</p>
								</div>
							</a>
						</div>
					</c:forEach>
				</div>
			</c:if>
			<div class="t_c">
				<jsp:include page="/jsp/index_pager.jsp">
					<jsp:param value="${datas.total }" name="totalRecord"/>
					<jsp:param value="/cms/channel/${channel.id}" name="url"/>
				</jsp:include>
			</div>
		</div>

	</div>
	<jsp:include page="/jsp/template/bottom.jsp"/>
</body>
</html>
