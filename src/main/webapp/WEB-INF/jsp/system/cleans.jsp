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
				>>
			</li>
			<li>
				<a href="admin/system/cleans" class="ajaxify">系统清理管理</a>
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
							<th>名称</th>
							<th>数量</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>未引用的垃圾附件</td>
							<td>${attNums }</td>
							<td>
								<a href="admin/system/cleanList/atts" class="btn btn-sm blue ajaxify"> 查询 </a>
								<a href="admin/system/clean/atts" class="btn btn-sm red ajaxify delete"> 清理</a>
							</td>
						</tr>
						<tr>
							<td>未引用的首页图片</td>
							<td>${indexPics }</td>
							<td>
								<a href="admin/system/cleanList/pics" class="btn btn-sm blue ajaxify"> 查询 </a>
								<a href="admin/system/clean/pics" class="btn btn-sm red ajaxify delete"> 清理</a>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<!-- END EXAMPLE TABLE PORTLET-->
	</div>
</div>
<!-- END PAGE CONTENT -->
</html>