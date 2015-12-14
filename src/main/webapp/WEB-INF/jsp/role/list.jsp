<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	$(function(){
		$("a.delete").confirmOperator();
		var success='<%=request.getAttribute("success")%>';
		var error='<%=request.getAttribute("error")%>';
		showMessage(success,error);			
	});
	
</script>
</head>
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
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-cogs"></i>
					角色列表
				</div>
				<div class="tools">
					<a href="javascript:;" class="collapse"> </a>
				</div>
			</div>
			<div class="portlet-body">
				<div class="table-toolbar">
					<div class="btn-group">
						<a class="btn green-meadow ajaxify" href="admin/role/addUI">新增角色&nbsp;<i class="fa fa-plus"></i></a>
					</div>
				</div>
				<table class="table table-striped table-hover table-bordered" id="sample_1">
					<thead>
						<tr>
							<th style="text-align:center" class="table-checkbox"><input type="checkbox"
								class="group-checkable" data-set="#sample_1 .checkboxes" />
							</th>
							<th>角色名称</th>
							<th>角色类型</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${roles}" var="role">
							<tr>
								<td style="text-align:center"><input type="checkbox" class="checkboxes"
									name="checkid" value="${role.id }" /></td>
								<td><a href="admin/role/${role.id }" class="ajaxify">${role.name }</a></td>
								<td>${role.roleType }</td>
								<td><a href="admin/role/delete/${role.id }" class="btn btn-sm red ajaxify delete"> 删除 </a>
									<a href="admin/role/updateUI/${role.id }" class="btn btn-sm green ajaxify"> 更新 </a>
									<a href="admin/role/clearUsers/${role.id }" class="btn btn-sm blue ajaxify delete"> 清空用户 </a>
								</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<!-- END EXAMPLE TABLE PORTLET-->
	</div>
</div>
<!-- END PAGE CONTENT -->
</html>