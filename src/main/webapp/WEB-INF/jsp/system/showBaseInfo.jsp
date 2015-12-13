<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
	$(function(){
		var success='<%=request.getAttribute("success")%>';
		if (success != 'null') {
			toastr.success(success);
		}
	});
</script>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			网站信息基本管理
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
					<a href="admin/system/baseinfo" class="ajaxify">网站信息基本管理</a>
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
					网站基本信息
				</div>
				<div class="tools">
					<a href="javascript:;" class="collapse"> </a>
				</div>
			</div>
			<div class="portlet-body">
				<table class="table table-striped table-hover table-bordered" id="sample_1">
					<tbody>
						<tr>
							<td width="150px" align="center">网站名称</td>
							<td>${baseInfo.name }</td>
						</tr>
						<tr>
							<td width="150px" align="center">网站所在地址</td>
							<td>${baseInfo.address }</td>
						</tr>
						<tr>
							<td width="150px" align="center">邮政编码</td>
							<td>${baseInfo.zipCode }</td>
						</tr>
						<tr>
							<td width="150px" align="center">联系电话</td>
							<td>${baseInfo.phone }</td>
						</tr>
						<tr>
							<td width="150px" align="center">网站联系邮箱</td>
							<td>${baseInfo.email }</td>
						</tr>
						<tr>
							<td width="150px" align="center">网站访问域名</td>
							<td>${baseInfo.domainName }</td>
						</tr>
						<tr>
							<td width="150px" align="center">网站备案号</td>
							<td>${baseInfo.recordCode }</td>
						</tr>
						<tr>
							<td width="150px" align="center">首页图片宽度</td>
							<td>${baseInfo.indexPicWidth }</td>
						</tr>
						<tr>
							<td width="150px" align="center">首页图片高度</td>
							<td>${baseInfo.indexPicHeight }</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2" align="center">
								<a href="<%=request.getContextPath() %>/admin/system/baseinfo/updateUI" class="btn green ajaxify">修改网站基本信息</a>
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
