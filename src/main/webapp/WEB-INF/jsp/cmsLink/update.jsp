<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
$(function(){
	$("#addForm").cmsvalidate();
	$("#urlType").change(function(){
		var v = $(this).val();
		if(v=="-1") {
			$(".type").removeAttr("readonly");
			$(".type").select();
			$(".type").focus();
		} else if(v!="0"){
			$(".type").val(v);
			$(".type").attr("readonly","true");
		}
	})
});
</script>
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
					<a href="admin/cmsLink/updateUI" class="ajaxify">修改超级链接</a>
				</li>
			</ul>
		</div>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN VALIDATION STATES-->
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-edit"></i>修改超链接
				</div>
				<div class="tools">
					<a href="javascript:;" class="collapse">
					</a>
				</div>
			</div>
			<div class="portlet-body">
				<!-- BEGIN FORM-->
				<sf:form id="addForm" method="post" modelAttribute="cmsLink" action="admin/cmsLink/update/${cmsLink.id }" class="form-horizontal ajaxiform">
					<div class="form-body">
						<div class="form-group">
							<label class="control-label col-md-3">超链接标题 <span class="required">
							* </span>
							</label>
							<div class="col-md-4">
								<div class="input-icon right">
									<sf:input path="title" class="form-control" />
									<sf:errors cssClass="errorContainer" path="title"/>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">超链接地址 <span class="required">
							* </span>
							</label>
							<div class="col-md-4">
								<div class="input-icon right">
									<sf:input path="url" class="form-control" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">超链接类别 <span class="required">
							* </span>
							</label>
							<div class="col-md-4">
								<div class="input-icon right">
									<select id="urlType" class="bs-select form-control">
									<option value="0">请选择类型</option>
									<c:forEach items="${types }" var="t">
										<option value="${t }">${t }</option>
									</c:forEach>
									<option value="-1">选择其他</option>
									</select>
									<sf:input path="type" readonly="true" class="form-control type" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">打开方式 <span class="required">
							* </span>
							</label>
							<div class="col-md-4">
								<div class="input-icon right ">
									<div class="radio-list" data-error-container="#form_2_membership_error">
									<sf:radiobutton path="newWin" value="0"/>本窗口
									<sf:radiobutton path="newWin" value="1"/>新窗口
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">链接标签ID <span class="required">
							* </span>
							</label>
							<div class="col-md-4">
								<div class="input-icon right">
									<sf:input path="urlId" class="form-control" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">链接标签类别 <span class="required">
							* </span>
							</label>
							<div class="col-md-4">
								<div class="input-icon right">
									<sf:input path="urlClass" class="form-control" />
								</div>
							</div>
						</div>
					</div>
					<div class="form-actions">
						<div class="row">
							<div class="col-md-offset-3 col-md-9">
								<button type="submit" class="btn green">提交</button>
								<a class="btn default ajaxify" href="admin/cmsLink/links">取消</a>
							</div>
						</div>
					</div>
				</sf:form>
				<!-- END FORM-->
			</div>
		</div>
		<!-- END VALIDATION STATES-->
	</div>
</div>
