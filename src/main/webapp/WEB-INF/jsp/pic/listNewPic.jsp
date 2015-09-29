<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	$(function(){
		$(".isNewPic").click(function(){
			dwrService.updateIndexPic($(this).val());
			showMessage("设置成功!","");
		})
	});
</script>
</head>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			首页新闻图片管理
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li>
				<i class="fa fa-home"></i>
				<a class="ajaxify start" href="layout_ajax_content_1.html">首页</a>
				>>
			</li>
			<li>
				<a>首页管理</a>
				>>
			</li>
			<li>
				<a href="admin/pic/newPics" class="ajaxify">首页新闻图片管理</a>
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
			<div class="portlet-title">
				<div class="caption">
					首页新闻图片列表
				</div>
			</div>
			<div class="portlet-body">
				<table class="table table-striped table-hover table-bordered" id="sample_1">
					<thead>
						<tr>
							<th width="20%">缩略图</th>
							<th>文章标题</th>
							<th width="15%">首页新闻</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${datas.datas }" var="pic">
							<tr>
								<td><img src='<%=request.getContextPath()%>/resources/upload/thumbnail/${pic.newName}'/></td>
								<td>${pic.topic.title }</td>
								<td>
									<input class="form-control isNewPic" type="checkbox" value="${pic.id }" <c:if test="${pic.isIndexPic eq 1 }">checked="checked"</c:if>/>
								</td>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
					<tr>
						<td colspan="6" style="text-align:right;margin-right:10px;">
						<jsp:include page="/jsp/pager.jsp">
							<jsp:param value="${datas.total }" name="totalRecord"/>
							<jsp:param value="admin/pic/newPics" name="url"/>
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
</html>