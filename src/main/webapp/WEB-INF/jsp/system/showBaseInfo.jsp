<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
			网站信息基本管理
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
					<a href="admin/system/baseinfo" class="ajaxify">网站信息基本管理</a>
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
					<span class="caption-subject bold"> 网站基本信息</span>
				</div>
				<div class="actions">
					<a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title="全屏浏览"></a>
				</div>
			</div>
			<div class="portlet-body form">
				<div class="form-horizontal">
				<div class="form-body">
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-3">网站名称&nbsp;:</label>
								<div class="col-md-9">
									<p class="form-control-static">
										${baseInfo.name }
									</p>
								</div>
							</div>
						</div>
						<!--/span-->
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-3">网站所在地址&nbsp;:</label>
								<div class="col-md-9">
									<p class="form-control-static">
										 ${baseInfo.address }
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
								<label class="control-label col-md-3">邮政编码&nbsp;:</label>
								<div class="col-md-9">
									<p class="form-control-static">
										${baseInfo.zipCode }
									</p>
								</div>
							</div>
						</div>
						<!--/span-->
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-3">联系电话&nbsp;:</label>
								<div class="col-md-9">
									<p class="form-control-static">
										 ${baseInfo.phone }
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
								<label class="control-label col-md-3">网站联系邮箱&nbsp;:</label>
								<div class="col-md-9">
									<p class="form-control-static">
										${baseInfo.email }
									</p>
								</div>
							</div>
						</div>
						<!--/span-->
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-3">网站访问域名&nbsp;:</label>
								<div class="col-md-9">
									<p class="form-control-static">
										 ${baseInfo.domainName }
									</p>
								</div>
							</div>
						</div>
						<!--/span-->
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-3">首页图片宽度&nbsp;:</label>
								<div class="col-md-9">
									<p class="form-control-static">
										 ${baseInfo.indexPicWidth }
									</p>
								</div>
							</div>
						</div>
						<!--/span-->
						<!--/span-->
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-3">首页图片高度&nbsp;:</label>
								<div class="col-md-9">
									<p class="form-control-static">
										 ${baseInfo.indexPicHeight }
									</p>
								</div>
							</div>
						</div>
						<!--/span-->
						<!--/span-->
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-3">网站备案号&nbsp;:</label>
								<div class="col-md-9">
									<p class="form-control-static">
										 ${baseInfo.recordCode }
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
									<a href="<%=request.getContextPath() %>/admin/system/baseinfo/updateUI" class="btn blue ajaxify">修改网站基本信息</a>
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
