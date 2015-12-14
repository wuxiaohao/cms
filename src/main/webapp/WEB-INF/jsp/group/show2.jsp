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
					<a href="admin/group/updateUI/${group.id }" class="ajaxify">查询用户组信息</a>
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
					<i class="fa fa-comments"></i>
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
							<td width="150px" align="center">用户组名称</td>
							<td>${group.name }&nbsp;</td>
						</tr>
						<tr>
							<td width="150px" align="center">用户组描述</td>
							<td>${group.descr }&nbsp;</td>
						</tr>
						<tr>
							<td width="150px" align="center">用户组所有用户</td>
							<td>
								<c:forEach items="${us }" var="u">
									<a href="<%=request.getContextPath()%>/admin/user/${u.id}" class="btn btn-xs red ajaxify">${u.nickname }</a>&nbsp;
								</c:forEach>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2" align="center">
								<a href="admin/group/updateUI/${group.id }" class="btn green ajaxify">修改用户组</a>
								<a class="btn default ajaxify" href="admin/group/groups">取消</a>
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
