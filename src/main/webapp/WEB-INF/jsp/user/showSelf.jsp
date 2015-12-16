<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
	$(function(){
		var success='<%=request.getAttribute("success")%>';
		if (success != 'null') {
			toastr.success(success);
		}
	});
</script>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			个人中心
			<small>查看个人信息 </small>
		</h3>
		<div class="page-bar">
			<ul class="page-breadcrumb">
				<li>
					<i class="fa fa-home"></i>
					<a class="ajaxify start" href="layout_ajax_content_1.html">首页</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a>个人中心</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="admin/user/showMySelf" class="ajaxify">查询个人信息</a>
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
				<div class="caption font-green">
					<i class="icon-settings font-green"></i>
					<span class="caption-subject bold"> ${user.username }的个人信息</span>
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
								<label class="control-label col-md-3">用户名&nbsp;:</label>
								<div class="col-md-9">
									<p class="form-control-static">
										${user.username }
									</p>
								</div>
							</div>
						</div>
						<!--/span-->
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-3">显示名称&nbsp;:</label>
								<div class="col-md-9">
									<p class="form-control-static">
										 ${user.nickname }
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
								<label class="control-label col-md-3">联系电话&nbsp;:</label>
								<div class="col-md-9">
									<p class="form-control-static">
										<c:if test="${empty user.phone}">
										 	暂无...
										</c:if>	
										<c:if test="${!empty user.phone}">
										 	 ${user.phone}
										</c:if>	
									</p>
								</div>
							</div>
						</div>
						<!--/span-->
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-3">电子邮件&nbsp;:</label>
								<div class="col-md-9">
									<p class="form-control-static">
										 ${user.email }
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
								<label class="control-label col-md-3">当前状态&nbsp;:</label>
								<div class="col-md-9">
									<p class="form-control-static">
										 <c:if test="${user.status eq 0 }">
											<span class="emp">停用</span>
										 </c:if>
										 <c:if test="${user.status eq 1 }">
											<span>启用</span>
										 </c:if>
									</p>
								</div>
							</div>
						</div>
						<!--/span-->
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-3">创建时间&nbsp;:</label>
								<div class="col-md-9">
									<p class="form-control-static">
										 <fmt:formatDate value="${user.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
									</p>
								</div>
							</div>
						</div>
						<!--/span-->
					</div>
					<!--/row-->
					<h3 class="form-section">所属角色和组</h3>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-3">角色&nbsp;:</label>
								<div class="col-md-9">
									<p class="form-control-static">
										<c:if test="${empty rs}">
										 	暂无...
										</c:if>	
										<c:if test="${!empty rs}">
										 	<c:forEach items="${rs }" var="r">
											<a class="btn btn-xs btn-danger">${r.name }</a>
										</c:forEach>
										</c:if>	
									</p>
								</div>
							</div>
						</div>
						<!--/span-->
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-3">用户组&nbsp;:</label>
								<div class="col-md-9">
									<p class="form-control-static">
										 <c:if test="${empty gs}">
										 	暂无...
										 </c:if>	
										 <c:if test="${!empty gs}">
										 	<c:forEach items="${gs }" var="g">
												<a class="btn btn-xs blue">${g.name }</a>
											 </c:forEach>
										 </c:if>	
									</p>
								</div>
							</div>
						</div>
						<!--/span-->
					</div>
				</div>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<div class="row">
								<div class="col-md-offset-3 col-md-9">
									<a href="admin/user/updateSelfUI" class="btn green-meadow ajaxify"><i class="fa fa-pencil"></i> 修改个人信息</a>
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
