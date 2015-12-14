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
			超级链接管理
			<small>增加删除修改超级链接 </small>
		</h3>
		<div class="page-bar">
			<ul class="page-breadcrumb">
				<li>
					<i class="fa fa-home"></i>
					<a class="ajaxify start" href="layout_ajax_content_1.html">首页</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a>首页管理</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="admin/cmsLink/links" class="ajaxify">超级链接管理</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="admin/cmsLink/${cmsLink.id}" class="ajaxify">查看超级链接</a>
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
					<span class="caption-subject bold"> 查看超链接</span>
				</div>
				<div class="actions">
					<a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title="全屏浏览"></a>
				</div>
			</div>
			<div class="portlet-body form">
				<div class="form-horizontal">
				<div class="form-body">
					<h3 class="form-section">${cmsLink.title }</h3>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-3">超链接标题&nbsp;:</label>
								<div class="col-md-9">
									<p class="form-control-static">
										${cmsLink.title }
									</p>
								</div>
							</div>
						</div>
						<!--/span-->
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-3">超链接地址&nbsp;:</label>
								<div class="col-md-9">
									<p class="form-control-static">
										 <a href="${cmsLink.url }" target="_blank">${cmsLink.url }</a>
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
								<label class="control-label col-md-3">超链接类别&nbsp;:</label>
								<div class="col-md-9">
									<p class="form-control-static">
										${cmsLink.type }
									</p>
								</div>
							</div>
						</div>
						<!--/span-->
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-3">打开方式&nbsp;:</label>
								<div class="col-md-9">
									<p class="form-control-static">
										 <c:if test="${cmsLink.newWin eq 0}">本窗口</c:if>
										 <c:if test="${cmsLink.newWin eq 1}">新窗口</c:if>
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
								<label class="control-label col-md-3">链接标签ID&nbsp;:</label>
								<div class="col-md-9">
									<p class="form-control-static">
										${cmsLink.urlId }
									</p>
								</div>
							</div>
						</div>
						<!--/span-->
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-3">链接标签类别&nbsp;:</label>
								<div class="col-md-9">
									<p class="form-control-static">
										 ${cmsLink.urlClass}
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
									<a href="admin/cmsLink/updateUI/${cmsLink.id }" class="btn blue ajaxify">修改</a>
								<a class="btn default ajaxify" href="admin/cmsLink/links">取消</a>
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
