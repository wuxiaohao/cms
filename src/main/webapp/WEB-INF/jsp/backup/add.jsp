<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
$(function(){
	$("#beginBackup").click(function(){
		var v = $("#backupFilename").val();
		if(v=="") {
			var c = confirm("备份文件名没有输入，默认会使用“未命名”作为文件名，确定吗？");
			if(!c) return; 
			else {
				$("#backupFilename").val("未命名");	
			}
		}
		$("#backupCon").html("正在备份中,请稍等...");
		$("#addForm").submit();
	});
});
</script>
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
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="admin/backup/addUI" class="ajaxify">添加备份</a>
				</li>
			</ul>
		</div>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN SAMPLE FORM PORTLET-->
		<div class="portlet light bordered">
			<div class="portlet-title">
				<div class="caption font-green">
					<i class="icon-pin font-green"></i>
					<span class="caption-subject bold uppercase"> 一键备份</span>
				</div>
				<div class="actions">
					<a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title="全屏浏览"></a>
				</div>
			</div>
			<div class="portlet-body form">
				<sf:form role="form" id="addForm" method="post" action="admin/backup/add" class="form-horizontal ajaxiform">
					<div class="form-body">
						<div class="form-group form-md-line-input">
							<label class="col-md-2 control-label" for="form_control_1">请输入备份的文件名</label>
							<div class="col-md-6">
								<input type="text" name="backupFilename" id="backupFilename" class="form-control">
								<div class="form-control-focus"></div>
							</div>
						</div>
					</div>
					<div class="form-actions">
						<div class="row">
							<div class="col-md-offset-2 col-md-10">
								<button type="button" class="btn blue" id="beginBackup">备份</button>
								<a type="button" class="btn default ajaxify" href="admin/backups">取消</a>
								<span id="backupCon"></span>
							</div>
						</div>
					</div>
				</sf:form>
			</div>
		</div>
		<!-- END SAMPLE FORM PORTLET-->
	</div>
</div>
