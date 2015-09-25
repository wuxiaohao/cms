<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 阻止自动换行 -->
<style>
.c{
display:inline;
}
</style>
<script type="text/javascript">
	$(function(){
		$("a.delete").confirmOperator();
		var success='<%=request.getAttribute("success")%>';
		var error='<%=request.getAttribute("error")%>';
		showMessage(success,error);
		/* $("#search").click(function(event){
			var con = $("#con").val(); var cid = $("#cid").val();
			var href = window.location.href+"/topic/audits";
			var len = href.lastIndexOf("?");
			if(len>0) {
				href = href.substr(0,len);
			}
			window.location.href=href+"?con="+con+"&cid="+cid;
		}); */
	});
</script>
</head>

<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			文章内容管理
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li>
				<i class="fa fa-home"></i>
				<a class="ajaxify start" href="layout_ajax_content_1.html">首页</a>
				>>
			</li>
			<li>
				<a>文章管理</a>
				>>
			</li>
			<li>
				<a href="admin/topic/audits" class="ajaxify">文章内容管理</a>
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
					<c:if test="${status eq 0 }">
						<a href="admin/topic/audits" class="btn btn-default red-stripe ajaxify">已发布文章列表</a>
						<a href="admin/topic/unaudits" class="btn red ajaxify">未发布文章列表</a>
					</c:if>
					<c:if test="${status eq 1 }">
						<a href="admin/topic/audits" class="btn red ajaxify">已发布文章列表</a>
						<a href="admin/topic/unaudits" class="btn btn-default red-stripe ajaxify">未发布文章列表</a>
					</c:if>
					<a class="btn btn-default yellow-stripe" href="javascript:openWin('<%=request.getContextPath() %>/admin/topic/add','addTopic')">添加文章</a>
				</div>
			</div>
			<div class="portlet-body">
				<div class="note note-success" style="height: 60px">
					<!-- <p>Please click &nbsp;&nbsp;icon on the header's right top to toggle the quick sidebar.
					</p> -->
					<p style="float: left;"><font size="3">文章关键字</font></p>
					<div class="col-md-4 c">
						<input type="text" name="con" id="con" value="${con}" class="form-control" />
					</div>
					<div class="col-md-4 c">
						<select name="cid" id="cid" class="bs-select form-control">
							<option value="0">选择栏目</option>
							<c:forEach items="${cs }" var="c">
								<c:if test="${c.id  eq cid}">
								<option value="${c.id }" selected="selected">${c.name }</option>
								</c:if>
								<c:if test="${c.id  ne cid}">
								<option value="${c.id }">${c.name }</option>
								</c:if>
							</c:forEach>
						</select>	
					</div>
					<dir class="c">
						<button class="btn green" id="search">搜索文章</button>
					</dir>
				</div>
				<table class="table table-striped table-hover table-bordered" id="sample_1">
					<thead>
						<tr>
							<td width="600" align="center">文章标题</td>
							<td>文章作者</td>
							<td>是否推荐</td>
							<td>所属频道</td>
							<td width="150">文章的状态</td>
							<td width="150">操作</td>
						</tr>
					</thead>
					<c:forEach items="${datas.datas }" var="t">
						<tbody>
							<tr>
								<td>
									<a href="javascript:openWin('<%=request.getContextPath() %>/admin/topic/${t.id }','showTopic')">${t.title }</a>
								</td>
								<td>${t.author}</td>
								<td>
									<c:if test="${t.recommend eq 0 }">不推荐</c:if>
									<c:if test="${t.recommend eq 1 }">推荐</c:if>
								</td>
								<td>${t.cname }</td>
								<td>
									<c:if test="${t.status eq 0 }">
										<span style="color: red">未发布&nbsp;</span>
										<a href="admin/topic/changeStatus/${t.id }?status=${t.status}" class="btn btn-sm blue ajaxify delete">发布</a>
									</c:if>
									<c:if test="${t.status eq 1 }">
										<span>已发布&nbsp;</span>
										<a href="admin/topic/changeStatus/${t.id }?status=${t.status}" class="btn btn-sm blue ajaxify delete">取消发布</a>
									</c:if>
									<%-- <div class="bootstrap-switch-container">
										<c:if test="${t.status eq 0 }">
											<input type="checkbox" class="make-switch" data-on-text="发布" data-off-text="未发布">
										</c:if>
										<c:if test="${t.status eq 1 }">
											<input type="checkbox" class="make-switch" checked data-on-text="已发布" data-off-text="未发布">
										</c:if>
									</div> --%>
								</td>
								<td>
									<a href="admin/topic/delete/${t.id }?status=${t.status}" title="${user.id }" class="btn btn-sm red delete ajaxify">删除</a>
									<a href="javascript:openWin('<%=request.getContextPath() %>/admin/topic/update/${t.id}','updateTopic')" class="btn btn-sm blue">更新</a>								
									&nbsp;
								</td>
							</tr>
						</tbody>
					</c:forEach>
					<tfoot>
						<tr>
							<td colspan="6" style="text-align:right;margin-right:10px;">
							<c:if test="${status eq 0 }">
							<jsp:include page="/jsp/pager.jsp">
								<jsp:param value="${datas.total }" name="totalRecord"/>
								<jsp:param value="admin/topic/unaudits" name="url"/>
							</jsp:include>
							</c:if>
							<c:if test="${status eq 1 }">
							<jsp:include page="/jsp/pager.jsp">
								<jsp:param value="${datas.total }" name="totalRecord"/>
								<jsp:param value="admin/topic/audits" name="url"/>
							</jsp:include>
							</c:if>
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
</html>