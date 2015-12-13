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
		<div class="portlet box red">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-edit"></i>
					查询用户组
				</div>
				<div class="tools">
					<a href="javascript:;" class="collapse"> </a>
				</div>
			</div>
			<div class="portlet-body">
				<table class="table table-striped table-hover table-bordered" id="sample_1">
					<tbody>
						<tr>
							<td width="150px" align="center">角色名称</td>
							<td>${role.name }&nbsp;</td>
						</tr>
						<tr>
							<td width="150px" align="center">角色类型</td>
							<td>${role.roleType }&nbsp;</td>
						</tr>
						<tr>
							<td width="150px" align="center">该角色所有用户</td>
							<td>
								<c:forEach items="${us }" var="u">
									<a href="<%=request.getContextPath()%>/admin/user/${u.id}" class="btn btn-xs red ajaxify">
										${u.nickname }
									</a>
								</c:forEach>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2" align="center">
								<a href="admin/role/updateUI/${role.id }" class="btn green ajaxify">修改角色</a>
							<a class="btn default ajaxify" href="admin/role/roles">取消</a>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
		<!-- END EXAMPLE TABLE PORTLET-->
	</div>
</div>
<!-- END PAGE CONTENT -->
