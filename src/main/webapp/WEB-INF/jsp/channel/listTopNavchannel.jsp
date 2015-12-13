<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
		$(".listTable").mysorttable(null,"${pageContext.servletContext.contextPath }/admin/channel/updateTopNavSort");
	});
	
</script>
</head>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			导航栏目排序
		</h3>
		<div class="page-bar">
			<ul class="page-breadcrumb">
				<li>
					<i class="fa fa-home"></i>
					<a class="ajaxify start" href="layout_ajax_content_1.html">首页</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a>栏目管理</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="admin/role/roles" class="ajaxify">角色管理</a>
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
		<div class="portlet gren">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-edit"></i>
					顶部栏目列表
				</div>
			</div>
			<div class="portlet-body">
				<table class="table table-striped table-hover table-bordered listTable">
					<thead>
						<tr>
							<th>栏目名称</th>
							<th>栏目类型</th>
							<th>是否推荐</th>
							<th>首页栏目</th>
							<th>顶部栏目</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${channels }" var="c">
							<tr id="id_${c.id }">
								<td>${c.name }&nbsp;</td>
								<td>${c.type.name }&nbsp;</td>
								<td>
									<c:if test="${c.recommend eq 0 }"><span class="emp">不推荐</span></c:if>
									<c:if test="${c.recommend eq 1 }">推荐</c:if>
									&nbsp;
								</td>
								<td>
									<c:if test="${c.isIndex eq 0 }"><span class="emp">否</span></c:if>
									<c:if test="${c.isIndex eq 1 }">是</c:if>
									&nbsp;
								</td>
								<td>
									<c:if test="${c.isTopNav eq 0 }"><span class="emp">否</span></c:if>
									<c:if test="${c.isTopNav eq 1 }">是</c:if>
									&nbsp;
								</td>
							</tr>
						</c:forEach>
					</tbody>
					<c:if test="${fn:length(channels)>1 }">
					<tfoot>
						<tr>
						<td colspan="5" style="text-align:right;">
								<button id="beginOrder" class="btn default">开始排序</button>
								<button id="saveOrder" aa="/cms" class="btn default">存储排序</button>
								&nbsp;
						</td>
						</tr>
					</tfoot>
					</c:if>
				</table>
			</div>
		</div>
		<!-- END EXAMPLE TABLE PORTLET-->
	</div>
</div>
<!-- END PAGE CONTENT -->
</html>