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
		<div class="portlet box red">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-edit"></i>
					查询超链接详细信息
				</div>
				<div class="tools">
					<a href="javascript:;" class="collapse"> </a>
				</div>
			</div>
			<div class="portlet-body">
				<table class="table table-striped table-hover table-bordered" id="sample_1">
					<tbody>
						<tr>
							<td width="150px" align="center">超链接标题</td>
							<td>${cmsLink.title }</td>
						</tr>
						<tr>
							<td width="150px" align="center">超链接地址</td>
							<td><a href="${cmsLink.url }" target="_blank">${cmsLink.url }</a></td>
						</tr>
						<tr>
							<td width="150px" align="center">超链接类别</td>
							<td>${cmsLink.type }</td>
						</tr>
						<tr>
							<td width="150px" align="center">打开方式</td>
							<td>
								<c:if test="${cmsLink.newWin eq 0}">本窗口</c:if>
								<c:if test="${cmsLink.newWin eq 1}">新窗口</c:if>
							</td>
						</tr>
						<tr>
							<td width="150px" align="center">链接标签ID</td>
							<td>${cmsLink.urlId }</td>
						</tr>
						<tr>
							<td width="150px" align="center">链接标签类别</td>
							<td>${cmsLink.urlClass}</td>
						</tr>
						
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2" align="center">
								<a href="admin/cmsLink/updateUI/${cmsLink.id }" class="btn green ajaxify">修改用户</a>
								<a class="btn default ajaxify" href="admin/cmsLink/links">取消</a>
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
