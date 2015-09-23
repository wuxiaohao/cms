<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%-- <script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/admin/showChannel.js"></script> --%>
<script type="text/javascript">
	$(function(){
		$("a.delete").confirmOperator();
		var success='<%=request.getAttribute("success")%>';
		var error='<%=request.getAttribute("error")%>';
		if (success != 'null') {
			toastr.success(success);
		}
		if (error != 'null') {
			toastr.error(error);
		}
		/**
		 * 显示树
		 */
		$("#showTree").click(function(){
			var selectedIds = $('input[title=check]:checked'); //获取被选的checkbox
			if(selectedIds.length != 1){
				toastr.error("请选中其中一行!");
				return false;
			}
			
			var t = $("#tree").mytree({
				url:$("#treePath").val()+selectedIds.val(),
				mine:{listChild:0,expandAll:true}
			});			
			$("#addmodel").modal();
			
		});
	});
</script>
</head>
<input type="hidden" id="treePath" value="<%=request.getContextPath()%>/admin/user/userTree/"/>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			用户信息管理
			<small> <i class="fa fa-shopping-cart"></i> 增加删除修改用户信息 </small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li>
				<i class="fa fa-home"></i>
				<a class="ajaxify start" href="layout_ajax_content_1.html">首页</a>
				>>
			</li>
			<li>
				<a>组织机构管理</a>
				>>
			</li>
			<li>
				<a href="admin/user/users" class="ajaxify">用户信息管理</a>
			</li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>

<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-edit"></i>
					用户列表
				</div>
				<div class="tools">
					<a href="javascript:;" class="collapse"> </a>
				</div>
			</div>
			<div class="portlet-body">
				<div class="table-toolbar">
					<div class="btn-group">
						<a class="btn green ajaxify" href="admin/user/addUI">新增用户</a>
						<a href="javascript:void(0);" id="showTree" class="btn red">管理栏目</a>
					</div>
				</div>
				<table class="table table-striped table-hover table-bordered" id="sample_1">
					<thead>
						<tr>
							<th style="text-align:center" class="table-checkbox"><input type="checkbox"
								class="group-checkable" data-set="#sample_1 .checkboxes" />
							</th>
							<th>用户名称</th>
							<th>用户昵称</th>
							<th>用户状态</th>
							<th>用户邮箱</th>
							<th>用户操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${datas.datas }" var="user">
							<tr>
								<td style="text-align:center">
									<input type="checkbox" class="checkboxes" title="check" value="${user.id }" />
								</td>
								<td>
									<a href="admin/user/${user.id }" class="ajaxify">${user.username }</a>
								</td>
								<td>${user.nickname }&nbsp;</td>
								<td>
									<c:if test="${user.status eq 0 }">
										<span style="color: red">停用</span>
										<a href="admin/user/updateStatus/${user.id }" class="btn btn-sm btn-info ajaxify">启用</a>
									</c:if>
									<c:if test="${user.status eq 1 }">
										<span>启用</span>
										<a href="admin/user/updateStatus/${user.id }" class="btn btn-sm btn-danger ajaxify">停用</a>
									</c:if>
									&nbsp;
								</td>
								<td>
									<a href="mailto:${user.email }">${user.email }</a>
									&nbsp;
								</td>
								<td>
									<a href="admin/user/delete/${user.id }" title="${user.id }" class="btn btn-sm red delete ajaxify">删除</a>
									<a href="admin/user/updateUI/${user.id }" class="btn btn-sm blue ajaxify">更新</a>								
								&nbsp;
								</td>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="6" style="text-align:right;margin-right:10px;">
							<jsp:include page="/jsp/pager.jsp">
								<jsp:param value="${datas.total }" name="totalRecord"/>
								<jsp:param value="admin/user/users" name="url"/>
							</jsp:include>
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

<!-- 弹框  -->
<div id="addmodel" class="modal fade" role="dialog" aria-labelledby="myModalLabel10" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title">管理栏目</h4>
			</div>
			<div class="modal-body">
				<div id="tree" class="ztree"></div>
			</div>
			<div class="modal-footer">
				<a class="btn default" data-dismiss="modal" aria-hidden="true">取消</a>
			</div>
		</div>
	</div>
</div>