<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			用户组管理
			<small>增加删除修改用户组信息 </small>
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
					<a href="admin/group/groups" class="ajaxify">用户组管理</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="admin/group/updateUI/${group.id }" class="ajaxify">修改用户组</a>
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
					<i class="fa fa-edit"></i>修改用户组
				</div>
				<div class="tools">
					<a href="javascript:;" class="collapse">
					</a>
				</div>
			</div>
			<div class="portlet-body">
				<!-- BEGIN FORM-->
				<sf:form id="updateForm" method="post" modelAttribute="group" action="admin/group/update/${group.id }" class="form-horizontal ajaxiform">
					<div class="form-body">
						<div class="form-group">
							<label class="control-label col-md-3">用户组名称 <span class="required">
							* </span>
							</label>
							<div class="col-md-4">
								<div class="input-icon right">
									<sf:input path="name" class="form-control" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">用户组描述 <span class="required">
							* </span>
							</label>
							<div class="col-md-4">
								<div class="input-icon right">
									<sf:textarea path="descr" class="form-control" rows="3" />
								</div>
							</div>
						</div>
					</div>
					<div class="form-actions">
						<div class="row">
							<div class="col-md-offset-3 col-md-9">
								<button type="submit" class="btn green">提交</button>
								<a class="btn default ajaxify" href="admin/group/groups">取消</a>
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
