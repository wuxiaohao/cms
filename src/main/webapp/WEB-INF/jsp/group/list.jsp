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
	/**
	 * 设置树
	 */
	$("#setTree").click(function(){
		var selectedIds = $('input[title=check]:checked'); //获取被选的checkbox
		if(selectedIds.length != 1){
			toastr.error("请选中其中一行!");
			return false;
		}
		
		var _cs = new Array();
		var t = $("#tree").mytree({
			url:$("#setTreePath").val(),
			mine:{listChild:0},
			callback:{
				onAsyncSuccess:initTree,
				beforeCheck:function(treeId,treeNode) { //点击之前
					if(!treeNode.checked) { //添加当前被选中节点和父节点（当节点被勾选前，treeNode.checked==false）
						//ps中的节点应该进行添加操作
						var ps = t.getCheckParentNodes(treeNode,false);//获取被选中的节点的父节点
						ps.push(treeNode);//加入被选中的节点
						//ps就是要添加的元素
						addGroupChannel(ps);
					} else {	//删除当前被选中节点和子节点
						var cs = new Array();
						t.getCheckChildNodes(treeNode,true,cs);//获取要删除的节点的子节点
						cs.push(treeNode);//加入被删除的节点
						//cs就是要删除的元素
						deleteGroupChannel(cs);
					}
				},
				onCheck:function(event,treeId,treeNode) {//点击之后
					//如果取消之后父节点依然存在就不操作，父节点不存在就进行删除
					if(!treeNode.checked) {
						var ps = t.getCheckParentNodes(treeNode,false);//删除当前被选中节点和父节点（当节点被取消勾选后，treeNode.checked==false）
						//ps就表示要删除的元素
						deleteGroupChannel(ps);
					}
				}
			
			},
			check:{
				enable:true,
				chkboxType: { "Y": "p", "N": "ps" }
			}
		});
		
		$("#addmodel").modal(); //弹框
		
		//全局异常
		function handler_(msg,exc) {
			alert(msg);
		}
		
		dwr.engine.setErrorHandler(handler_);
		
		function addGroupChannel(cs) {
			var gid = $('input[title=check]:checked').val();
			for(var i=0;i<cs.length;i++) {
				var c = cs[i];
				if(c.id>0) {
					dwrService.addGroupChannel(gid,c.id);
					toastr.success("添加成功!");
				}
			}
			
		}
		
		function deleteGroupChannel(cs) {
			var gid = $('input[title=check]:checked').val();
			for(var i=0;i<cs.length;i++) {
				var c = cs[i];
				if(c.id>0) {
					dwrService.deleteGroupChannel(gid,c.id);
					toastr.success("删除成功!");
				}
			}
			
		}
		//初始化树
		function initTree() {
			var cids = new Array();　//创建一个数组
			//根据组id获取该组的所有管理栏目的id
			$.ajax({
				type : "POST",
				url : $("#getChannelIdPath").val()+selectedIds.val(),
				success : function(data) {
					t.expandAll(true);//打开树节点
					for(var i=0;i<data.length;i++) {
						var cid = data[i];
						var n = t.getNodeByParam("id",cid,null);
						t.checkNode(n,true,true);
					}
				}
			});
		}
	});
});
	
</script>
</head>
<input type="hidden" id="treePath" value="<%=request.getContextPath()%>/admin/group/groupTree/"/>
<input type="hidden" id="getChannelIdPath" value="<%=request.getContextPath()%>/admin/group/groupChannelIds/"/>
<input type="hidden" id="setTreePath" value="<%=request.getContextPath()%>/admin/channel/treeAll"/>
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
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-cogs"></i>
					用户组列表
				</div>
				<div class="tools">
					<a href="javascript:;" class="collapse"> </a>
				</div>
			</div>
			<div class="portlet-body">
				<div class="table-toolbar">
					<div class="btn-group">
						<a class="btn green-meadow ajaxify" href="admin/group/addUI">添加用户组&nbsp;<i class="fa fa-plus"></i></a>
						<a href="javascript:void(0);" id="showTree" class="btn btn-danger">查询栏目&nbsp;<i class="fa fa-search"></i></a>
						<a href="javascript:void(0);" id="setTree" class="btn btn-info">设置栏目&nbsp;<i class="fa fa-edit"></i></a>
					</div>
				</div>
				<table class="table table-striped table-hover table-bordered" id="sample_1">
					<thead>
						<tr>
							<th class="table-checkbox"  style="text-align:center"><input type="checkbox"
								class="group-checkable" data-set="#sample_1 .checkboxes" />
							</th>
							<th>用户组名称</th>
							<th>用户组描述</th>
							<th>用户组操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${datas.datas }" var="group">
							<tr>
								<td style="text-align:center">
									<input type="checkbox" class="checkboxes" title="check" value="${group.id }" />
								</td>
								<td>
									<a href="admin/group/${group.id }" class="ajaxify">${group.name }</a>
								</td>
								<td>${group.descr }&nbsp;</td>
								<td width="400px;">
									<a href="admin/group/delete/${group.id }" title="${user.id }" class="btn btn-sm red delete ajaxify">删除</a>
									<a href="admin/group/updateUI/${group.id }" class="btn btn-sm green ajaxify">更新</a>								
									<a href="admin/group/clearUsers/${group.id }" class="btn btn-sm blue delete ajaxify">清空用户</a>								
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
								<jsp:param value="admin/group/groups" name="url"/>
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
</html>