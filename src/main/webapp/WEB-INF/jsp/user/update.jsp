<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
	$(function(){
		$("#addForm").cmsvalidate();
	});
	
</script>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			用户信息管理
			<small>增加删除修改用户信息 </small>
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
					<a href="admin/user/users" class="ajaxify">用户信息管理</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="admin/user/updateUI" class="ajaxify">修改用户</a>
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
					<i class="fa fa-gift"></i>修改用户  : ${userDto.username}
				</div>
				<div class="tools">
					<a href="javascript:;" class="collapse">
					</a>
				</div>
			</div>
			<div class="portlet-body">
				<!-- BEGIN FORM-->
				<sf:form id="addForm" method="post" modelAttribute="userDto" action="admin/user/update/${userDto.id }" class="form-horizontal ajaxiform">
					<sf:hidden path="id"/>
					<sf:hidden path="username"/>
					<sf:hidden path="password"/>
					<div class="form-body">
						<div class="form-group">
							<label class="control-label col-md-3">显示名称(可以是中文) <span class="required">
							* </span>
							</label>
							<div class="col-md-4">
								<div class="input-icon right">
									<sf:input path="nickname" class="form-control" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">联系电话 <span class="required">
							* </span>
							</label>
							<div class="col-md-4">
								<div class="input-icon right">
									<sf:input path="phone" class="form-control" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">电子邮件 <span class="required">
							* </span>
							</label>
							<div class="col-md-4">
								<div class="input-icon right">
									<sf:input path="email" class="form-control" />
									<sf:errors path="email"/>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">状态 <span class="required">
							* </span>
							</label>
							<div class="col-md-4">
								<div class="input-icon right">
									<sf:select path="status" class="bs-select form-control">
										<sf:option value="0">停用</sf:option>
										<sf:option value="1">启用</sf:option>
									</sf:select>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">角色<span class="required">
							* </span>
							</label>
							<div class="col-md-4">
								<div class="input-icon right">
									<sf:checkboxes  items="${roles}" itemLabel="name" itemValue="id" path="roleIds"/>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">用户组<span class="required">
							* </span>
							</label>
							<div class="col-md-4">
								<div class="input-icon right">
									<sf:checkboxes items="${groups }" path="groupIds" itemLabel="name" itemValue="id"/>
								</div>
							</div>
						</div>
					</div>
					<div class="form-actions">
						<div class="row">
							<div class="col-md-offset-3 col-md-9">
								<button type="submit" class="btn green">修改</button>
								<a type="button" class="btn default ajaxify" href="admin/user/users">取消</a>
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
