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
					<a href="admin/user/${user.id }" class="ajaxify">查询用户</a>
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
					查询用户
				</div>
				<div class="tools">
					<a href="javascript:;" class="collapse"> </a>
				</div>
			</div>
			<div class="portlet-body">
				<table class="table table-striped table-hover table-bordered" id="sample_1">
					<tbody>
						<tr>
							<td width="150px" align="center">用户名</td>
							<td>${user.username }</td>
						</tr>
						<tr>
							<td width="150px" align="center">显示名称</td>
							<td>${user.nickname }</td>
						</tr>
						<tr>
							<td width="150px" align="center">联系电话</td>
							<td>${user.phone}</td>
						</tr>
						<tr>
							<td width="150px" align="center">电子邮件</td>
							<td>${user.email }</td>
						</tr>
						<tr>
							<td width="150px" align="center">状态</td>
							<td>
								<c:if test="${user.status eq 0 }">
									<span class="emp">停用</span>
								</c:if>
								<c:if test="${user.status eq 1 }">
									<span>启用</span>
								</c:if>
							</td>
						</tr>
						<tr>
							<td width="150px" align="center">创建时间</td>
							<td><fmt:formatDate value="${user.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						</tr>
						<tr>
							<td width="150px" align="center">拥有角色</td>
							<td>
								<c:forEach items="${rs }" var="r">
									<a href="<%=request.getContextPath()%>/admin/role/${r.id}" class="btn btn-xs red ajaxify">
									${r.name }
									</a>&nbsp;
								</c:forEach>
							</td>
						</tr>
						<tr>
							<td width="150px" align="center">所在用户组</td>
							<td>
								<c:forEach items="${gs }" var="g">
									<a href="<%=request.getContextPath()%>/admin/group/${g.id}" class="btn btn-xs blue ajaxify">${g.name }</a>&nbsp;
								</c:forEach>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2" align="center">
								<a href="admin/user/updateUI/${user.id }" class="btn green ajaxify">修改用户</a>
								<a class="btn default ajaxify" href="admin/user/users">取消</a>
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
