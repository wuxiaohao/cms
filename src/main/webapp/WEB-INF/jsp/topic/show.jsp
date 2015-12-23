<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="row">
	<div class="col-md-12">
		<h3 class="page-title">
			文章新闻管理
			<small>发布、添加、更新、删除文章 </small>
		</h3>
		<div class="page-bar">
			<ul class="page-breadcrumb">
				<li>
					<i class="fa fa-home"></i>
					<a class="ajaxify start" href="layout_ajax_content_1.html">首页</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a>新闻管理</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="admin/topic/audits" class="ajaxify">文章新闻管理</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="admin/topic/${topic.id } class="ajaxify">查询文章新闻</a>
				</li>
			</ul>
		</div>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet light bg-inverse">
			<div class="portlet-title">
				<div class="caption font-blue">
					<i class="icon-settings font-blue"></i>
					<span class="caption-subject bold"> 文章信息查看</span>
				</div>
				<div class="actions">
					<a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title="全屏浏览"></a>
				</div>
			</div>
			<div class="portlet-body form">
				<div class="form-horizontal">
				<div class="form-body">
					<h3 class="form-section">基本信息</h3>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-3">文章标题&nbsp;:</label>
								<div class="col-md-9">
									<p class="form-control-static">
										<c:if test="${empty topic.title}">
										 	暂无...
										</c:if>	
										<c:if test="${!empty topic.title}">
										 	${topic.title}
										</c:if>	
									</p>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-3">作者&nbsp;:</label>
								<div class="col-md-9">
									<p class="form-control-static">
										<c:if test="${empty topic.user.nickname}">
										 	暂无...
										</c:if>	
										<c:if test="${!empty topic.user.nickname}">
										 	${topic.user.nickname }
										</c:if>	
									</p>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-3">文章栏目&nbsp;:</label>
								<div class="col-md-9">
									<p class="form-control-static">
										${topic.channel.name }
									</p>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-3">文章状态&nbsp;:</label>
								<div class="col-md-9">
									<p class="form-control-static">
										<c:if test="${topic.status eq 0 }">未发布</c:if>
										<c:if test="${topic.status eq 1 }">已发布</c:if>
									</p>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-3">创建时间&nbsp;:</label>
								<div class="col-md-9">
									<p class="form-control-static">
										<fmt:formatDate value="${topic.createDate}" pattern="yyyy-MM-dd HH:mm"/>
									</p>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-3">发布时间&nbsp;:</label>
								<div class="col-md-9">
									<p class="form-control-static">
										<fmt:formatDate value="${topic.publishDate}" pattern="yyyy-MM-dd HH:mm"/>
									</p>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-3">是否推荐&nbsp;:</label>
								<div class="col-md-9">
									<p class="form-control-static">
										<c:if test="${topic.recommend eq 0 }">不推荐</c:if>
										<c:if test="${topic.recommend eq 1 }">推荐</c:if>
									</p>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-3">文章关键字&nbsp;:</label>
								<div class="col-md-9">
									<p class="form-control-static">
										<c:if test="${empty topic.keyword}">
											暂无...
										</c:if>
										<c:if test="${!empty topic.keyword}">
											${topic.keyword }
										</c:if>
									</p>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-3">文章附件&nbsp;:</label>
								<div class="col-md-9">
									<p class="form-control-static">
										<c:if test="${empty atts}">
											暂无...
										</c:if>
										<c:if test="${!empty atts}">
										<c:forEach items="${atts }" var="att">
										<a href="<%=request.getContextPath() %>/resources/upload/${att.newName}" class="list_op">${att.oldName }</a>&nbsp;
										</c:forEach>
										</c:if>
									</p>
								</div>
							</div>
						</div>
					</div>
					<h3 class="form-section">文章内容</h3>
					<div class="row">
						<div class="col-md-12">
							<div class="note note-success">
								<p>
									<c:if test="${empty topic.content}">
										暂无...
									</c:if>
									<c:if test="${!empty topic.content}">
										${topic.content }
									</c:if>
								</p>
							</div>
						</div>
					</div>
					<h3 class="form-section">文章摘要</h3>
					<div class="row">
						<div class="col-md-12">
							<div class="note note-info">
								<p>
									<c:if test="${empty topic.summary}">
										暂无...
									</c:if>
									<c:if test="${!empty topic.summary}">
										${topic.summary }
									</c:if>
								</p>
							</div>
						</div>
					</div>
				</div>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<div class="row">
								<div class="col-md-offset-3 col-md-9">
									<a class="btn default ajaxify" href="admin/topic/audits">返回</a>
								</div>
							</div>
						</div>
					</div>
				</div>
				</div>
			</div>
		</div>
		<!-- END EXAMPLE TABLE PORTLET-->
	</div>
</div>
<!-- END PAGE CONTENT -->
