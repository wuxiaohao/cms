<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			系统清理
			<small> <i class="fa fa-shopping-cart"></i> 清理无用的数据、图片、文件 </small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li>
				<i class="fa fa-home"></i>
				<a class="ajaxify start" href="layout_ajax_content_1.html">首页</a>
				>>
			</li>
			<li>
				<a>系统配置</a>
			</li>
			<li>
				<a href="admin/system/cleans" class="ajaxify">系统清理管理</a>
				>>
			</li>
			<li>
				<a href="admin/system/cleanList/pics" class="ajaxify">未引用的首页图片</a>
			</li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>

<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet gren">
			<div class="portlet-body">
				<table class="table table-striped table-hover table-bordered" id="sample_1">
					<thead>
						<tr>
							<th>缩略图</th>
							<th>名称</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${datas }" var="pic">
							<tr>
								<td><img src="<%=request.getContextPath()%>/resources/indexPic/thumbnail/${pic}"/></td>
								<td>${pic }</td>
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