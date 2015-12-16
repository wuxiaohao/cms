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
			首页宣传图片管理
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
					<a href="admin/pic/indexPics" class="ajaxify">首页宣传图片管理</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="admin/pic/indexPic/${indexPic.id }" class="ajaxify">查询首页宣传图片</a>
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
					${indexPic.title }
				</div>
				<div class="tools">
					<a href="javascript:;" class="collapse"> </a>
				</div>
			</div>
			<div class="portlet-body">
				<table class="table table-striped table-hover table-bordered" id="sample_1">
					<tbody>
						<tr>
							<td colspan="2">
								<img src="<%=request.getContextPath() %>/resources/indexPic/${indexPic.newName}"/>
							</td>
						</tr>
						<tr>
							<td width="150px" align="center">首页图片标题</td>
							<td>${indexPic.title }</td>
						</tr>
						<tr>
							<td width="150px" align="center">首页图片子标题</td>
							<td>${indexPic.subTitle }</td>
						</tr>
						<tr>
							<td width="150px" align="center">状态</td>
							<td>
								<c:if test="${indexPic.status eq 0 }">未发布</c:if>
								<c:if test="${indexPic.status ne 0 }">已发布</c:if>
							</td>
						</tr>
						<tr>
							<td width="150px" align="center">链接类型</td>
							<td>
								<c:if test="${indexPic.linkType eq 0 }">站内链接</c:if>
								<c:if test="${indexPic.linkType ne 0 }">站外链接</c:if>
							</td>
						</tr>
						<tr>
							<td width="150px" align="center">链接地址</td>
							<td>${indexPic.linkUrl }</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2" align="center">
								<a class="btn default ajaxify" href="admin/pic/indexPics">取消</a>
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
