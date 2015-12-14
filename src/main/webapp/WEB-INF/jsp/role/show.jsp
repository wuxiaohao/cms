<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			角色设置
			<small>增加删除修改角色 </small>
		</h3>
		<div class="page-bar">
			<ul class="page-breadcrumb">
				<li>
					<i class="fa fa-home"></i>
					<a class="ajaxify start" href="layout_ajax_content_1.html">首页</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a>组织机构管理</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="admin/role/roles" class="ajaxify">角色管理</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="admin/role/${role.id }" class="ajaxify">查询角色</a>
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
					<span class="caption-subject bold"> 查询角色</span>
				</div>
				<div class="actions">
					<a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title="全屏浏览"></a>
				</div>
			</div>
			<div class="portlet-body form">
				<div class="form-horizontal">
				<div class="form-body">
					<h3 class="form-section">${group.name }</h3>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-3">角色名称&nbsp;:</label>
								<div class="col-md-9">
									<p class="form-control-static">
										${role.name }
									</p>
								</div>
							</div>
						</div>
						<!--/span-->
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-3">角色类型&nbsp;:</label>
								<div class="col-md-9">
									<p class="form-control-static">
										 ${role.roleType }
									</p>
								</div>
							</div>
						</div>
						<!--/span-->
					</div>
					<!--/row-->
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-3">该角色所有用户&nbsp;:</label>
								<div class="col-md-9">
									<p class="form-control-static">
										<c:if test="${empty us}">
										 	暂无...
										</c:if>	
										<c:if test="${!empty us}">
										<c:forEach items="${us }" var="u">
											<a href="<%=request.getContextPath()%>/admin/user/${u.id}" class="btn btn-xs btn-danger ajaxify">${u.nickname }</a>&nbsp;
										</c:forEach>
										</c:if>
									</p>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<div class="row">
								<div class="col-md-offset-3 col-md-9">
									<a href="admin/role/updateUI/${role.id }" class="btn blue ajaxify">修改</a>
									<a class="btn default ajaxify" href="admin/role/roles">取消</a>
								</div>
							</div>
						</div>
						<div class="col-md-6">
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
