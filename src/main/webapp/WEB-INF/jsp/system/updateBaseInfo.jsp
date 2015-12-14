<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
$(function(){
	$("#updateForm").cmsvalidate();
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
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="admin/system/updateUI" class="ajaxify">更新网站基本信息</a>
				</li>
			</ul>
		</div>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN SAMPLE FORM PORTLET-->
		<div class="portlet light bordered">
			<div class="portlet-title">
				<div class="caption font-green">
					<i class="icon-pin font-green"></i>
					<span class="caption-subject bold uppercase"> 更新网站基本信息</span>
				</div>
				<div class="actions">
					<a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title="全屏浏览"></a>
				</div>
			</div>
			<div class="portlet-body form">
				<sf:form id="updateForm" method="post" role="form" modelAttribute="baseInfo" action="admin/system/baseinfo/update" class="form-horizontal ajaxiform">	
					<div class="form-body">
						<div class="form-group form-md-line-input">
							<label class="col-md-2 control-label" for="form_control_1">网站名称</label>
							<div class="col-md-6">
								<sf:input path="name" class="form-control" />
								<div class="form-control-focus"></div>
							</div>
						</div>
						<div class="form-group form-md-line-input">
							<label class="col-md-2 control-label" for="form_control_1">网站所在地址</label>
							<div class="col-md-6">
								<sf:input path="address" class="form-control"/>
								<div class="form-control-focus"></div>
							</div>
						</div>
						<div class="form-group form-md-line-input">
							<label class="col-md-2 control-label" for="form_control_1">邮政编码</label>
							<div class="col-md-6">
								<sf:input path="zipCode" class="form-control" />
								<div class="form-control-focus"></div>
							</div>
						</div>
						<div class="form-group form-md-line-input">
							<label class="col-md-2 control-label" for="form_control_1">联系电话 </label>
							<div class="col-md-6">
								<sf:input path="phone" class="form-control" />
								<div class="form-control-focus"></div>
							</div>
						</div>
						<div class="form-group form-md-line-input">
							<label class="col-md-2 control-label" for="form_control_1">网站联系邮箱</label>
							<div class="col-md-6">
								<sf:input path="email" class="form-control" />
								<div class="form-control-focus"></div>
							</div>
						</div>
						<div class="form-group form-md-line-input">
							<label class="col-md-2 control-label" for="form_control_1">网站访问域名 </label>
							<div class="col-md-6">
								<div class="input-icon right">
									<sf:input path="domainName" class="form-control" />
									<div class="form-control-focus"></div>
								</div>
							</div>
						</div>
						<div class="form-group form-md-line-input">
							<label class="col-md-2 control-label" for="form_control_1">网站备案号 </label>
							<div class="col-md-6">
								<div class="input-icon right">
									<sf:input path="recordCode" class="form-control" />
									<div class="form-control-focus"></div>
								</div>
							</div>
						</div>
						<div class="form-group form-md-line-input">
							<label class="col-md-2 control-label" for="form_control_1">首页图片宽度 </label>
							<div class="col-md-6">
								<div class="input-icon right">
									<sf:input path="indexPicWidth" class="form-control" />
									<div class="form-control-focus"></div>
								</div>
							</div>
						</div>
						<div class="form-group form-md-line-input">
							<label class="col-md-2 control-label" for="form_control_1">首页图片高度 </label>
							<div class="col-md-6">
								<div class="input-icon right">
									<sf:input path="indexPicHeight" class="form-control" />
									<div class="form-control-focus"></div>
								</div>
							</div>
						</div>
					</div>
					<div class="form-actions">
						<div class="row">
							<div class="col-md-offset-2 col-md-10">
								<button type="submit" class="btn green">修改</button>
								<a type="button" class="btn default ajaxify" href="admin/system/baseinfo">取消</a>
							</div>
						</div>
					</div>
				</sf:form>
			</div>
		</div>
		<!-- END SAMPLE FORM PORTLET-->
	</div>
</div>
