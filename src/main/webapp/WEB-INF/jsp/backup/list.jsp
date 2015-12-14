<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	
	$("a.resumeDatabase").click(function(event) {
		if(!confirm("覆盖之后不可恢复，确定要覆盖吗？建议先进行备份")) {
			event.preventDefault();
			return false;
		} else {
			$("#info").text("恢复中,请稍等...");
		}
	})
})
</script>
</head>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			网站数据备份
			<small>备份和恢复数据、文件 </small>
		</h3>
		<div class="page-bar">
			<ul class="page-breadcrumb">
				<li>
					<i class="fa fa-home"></i>
					<a class="ajaxify start" href="layout_ajax_content_1.html">首页</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a>系统配置</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="admin/backups" class="ajaxify">网站数据备份</a>
				</li>
			</ul>
			<!-- END PAGE TITLE & BREADCRUMB-->
		</div>
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
					备份数据列表
				</div>
				<div class="tools">
					<a href="javascript:;" class="collapse"> </a>
				</div>
			</div>
			<div class="portlet-body">
				<div class="table-toolbar">
					<div class="btn-group">
						<a class="btn green-meadow ajaxify" href="admin/backup/addUI">数据备份&nbsp;<i class="fa fa-plus"></i></a>
					</div>
					<div style="float: right;color: red; font-size: 18px;" id="info"></div>
				</div>
				<table class="table table-striped table-hover table-bordered" id="sample_1">
					<thead>
						<tr>
							<th>备份文件名称</th>
							<th>文件大小</th>
							<th>备份时间</th>
							<th>文件类型</th>
							<th>用户操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${backups }" var="b">
							<tr>
								<td>${b.name }</td>
								<td>${b.size }K</td>
								<td><fmt:formatDate value="${b.time }" pattern="yyyy-MM-dd HH:mm"/></td>
								<td>
									${b.filetype }
								</td>
								<td>
									<a href="admin/delete/${b.name }?type=${b.filetype}"  class="btn btn-sm red ajaxify delete">删除</a>
									<a href="admin/resume/${b.name}?type=${b.filetype}" class="btn btn-sm blue ajaxify resumeDatabase">恢复数据库</a>
								&nbsp;
								</td>
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