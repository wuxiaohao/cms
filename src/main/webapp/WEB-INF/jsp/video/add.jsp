<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- 引入外部js文件 -->
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/admin/videoTopicAdd.js"></script>
<div class="row">
	<div class="col-md-12">
		<h3 class="page-title">
			视频新闻管理
			<small>发布、添加、更新、视频新闻 </small>
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
					<a href="admin/video/audits" class="ajaxify">视频新闻管理</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="admin/video/addUI" class="ajaxify">添加视频新闻</a>
				</li>
			</ul>
		</div>
	</div>
</div>
<input type="hidden" id="sid" value="<%=session.getId()%>"/>
<input type="hidden" id="ctx" value="<%=request.getContextPath()%>"/>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN SAMPLE FORM PORTLET-->
		<div class="portlet light bordered">
			<div class="portlet-title">
				<div class="caption font-blue">
					<i class="icon-pin font-blue"></i>
					<span class="caption-subject bold uppercase"> 视频编辑</span>
				</div>
				<div class="actions">
					<a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title="全屏浏览"></a>
				</div>
			</div>
			<div class="portlet-body form">
				<sf:form id="addForm" method="post" role="form" modelAttribute="video" action="admin/video/add" class="form-horizontal ajaxiform" onkeydown="if(event.keyCode==13){return false;}" >	
					<div class="form-body">
						<div class="form-group form-md-line-input has-info">
							<label class="col-md-2 control-label" for="form_control_1">标题</label>
							<div class="col-md-6">
								<sf:input path="title" class="form-control"/>
								<div class="form-control-focus"></div>
							</div>
						</div>
						<div class="form-group form-md-line-input has-info">
							<label class="col-md-2 control-label" for="form_control_1">推送到</label>
							<div class="col-md-6">
								<select name="cid" id="cid" class="bs-select form-control">
									<c:forEach items="${cs }" var="c">
										<option value="${c.id }">${c.name }</option>
									</c:forEach>
								</select>
								<div class="form-control-focus"></div>
							</div>
						</div>
						<c:choose>
						<c:when test="${isAudit||isAdmin }">
						<div class="form-group form-md-line-input has-info">
							<label class="col-md-2 control-label" for="form_control_1">状态</label>
							<div class="col-md-6">
								<div class="md-radio-inline">
									<div class="md-radio has-success">
										<input type="radio" id="radio53" name="status" value="0" class="md-radiobtn" checked>
										<label for="radio53">
										<span></span>
										<span class="check"></span>
										<span class="box"></span>
										不发布 </label>
									</div>
									<div class="md-radio has-success">
										<input type="radio" id="radio54" name="status" value="1" class="md-radiobtn">
										<label for="radio54">
										<span></span>
										<span class="check"></span>
										<span class="box"></span>
										发布 </label>
									</div>
								</div>
							</div>
						</div>
						</c:when>
						<c:otherwise>
							<sf:hidden path="status" value="0" />
						</c:otherwise>
						</c:choose>
						<div class="form-group form-md-line-input has-info">
							<label class="col-md-2 control-label" for="form_control_1">作者名称</label>
							<div class="col-md-6">
								<sf:input path="author" class="form-control"/>
								<div class="form-control-focus"></div>
							</div>
						</div>
						<div class="form-group form-md-line-input has-info">
							<label class="col-md-2 control-label" for="form_control_1">视频上传</label>
							<div class="col-md-6">
								<input type="file" id="attach" class="form-control" />
								<input id="vsize" type="hidden" name="size"/>
							</div>
						</div>
						<div class="form-group form-md-line-input has-info vname" style="display: none;">
							<label class="col-md-2 control-label" for="form_control_1">视频名称</label>
							<div class="col-md-6">
								<input id="vname" type="text" name="videoName" class="form-control" readonly/>
								<div class="form-control-focus"></div>
							</div>
						</div>
						<div class="form-group form-md-line-input has-info">
							<label class="col-md-2 control-label" for="form_control_1">缩略图上传</label>
							<div class="col-md-6">
								<input type="file" id="attachPic" class="form-control" />
							</div>
						</div>
						<div class="form-group form-md-line-input has-info vpicName" style="display: none;">
							<label class="col-md-2 control-label" for="form_control_1">图片名称</label>
							<div class="col-md-6">
								<input id="vpicName" type="text" name="picName" class="form-control" readonly/>
								<div class="form-control-focus"></div>
							</div>
						</div>
					</div>
					<div class="form-actions">
						<div class="row">
							<div class="col-md-offset-2 col-md-10">
								<button type="button" id="addBtn" class="btn green-meadow">添加</button>
								<a type="button" class="btn default ajaxify" href="admin/video/audits">取消</a>
							</div>
						</div>
					</div>
				</sf:form>
			</div>
		</div>
		<!-- END SAMPLE FORM PORTLET-->
	</div>
</div>