<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
html { overflow-x:hidden; }
</style>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/js/base/jquery.ui.all.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-1.7.2.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/bootstrap-toastr/toastr.min.css"/>
<link href="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.servletContext.contextPath }/resources/assets/global/css/components.css" rel="stylesheet" type="text/css"/>
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/bootstrap-toastr/toastr.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/core/jquery.cms.core.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ui/jquery.ui.core.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ui/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ui/jquery.ui.sortable.js"></script>
<script type="text/javascript">
$(function(){
	$("a.delete").confirmOperator();
	var success='<%=request.getAttribute("success")%>';
	var error='<%=request.getAttribute("error")%>';
	if($("#refresh").val()=="1") {
		parent.refreshTree();  //调用父类的refreshTree
		parent.showMessage(success,error);  //调用父类回显信息的方法
	}
	$(".listTable").mysorttable(null,"${pageContext.servletContext.contextPath }/admin/channel/channels/updateSort");
});
</script>
</head>
<input type="hidden" id="refresh" value="${refresh}"/>
<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-edit"></i>
					当前栏目:${pc.name }
				</div>
				<div class="tools">
					<a href="javascript:;" class="collapse"> </a>
				</div>
			</div>
			<div class="portlet-body">
				<div class="table-toolbar">
					<div class="btn-group">
						<a class="btn green" href="<%=request.getContextPath() %>/admin/channel/add/${pc.id}" >添加子栏目</a>
					</div>
				</div>
				<table class="table table-striped table-hover table-bordered listTable">
					<thead>
						<tr>
							<th width="150px">栏目名称</th>
							<th width="110px">栏目类型</th>
							<th>是否推荐</th>
							<th>首页栏目</th>
							<th>顶部栏目</th>
							<th>栏目状态</th>
							<th>操作</th>
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
								<td>
									<c:if test="${c.status eq 0 }">启用</c:if>
									<c:if test="${c.status eq 1 }"><span class="emp">停用</span></c:if>
									&nbsp;
								</td>
								<td>
									<a href="<%=request.getContextPath() %>/admin/channel/delete/${pc.id}/${c.id}" class="btn btn-sm red delete"> 删除 </a>
									<a href="<%=request.getContextPath() %>/admin/channel/update/${c.id}" class="btn btn-sm blue"> 更新 </a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
					<c:if test="${fn:length(channels)>1 }">
					<tfoot>
						<tr>
						<td colspan="7" style="text-align:right;">
								<a id="beginOrder" href="#" class="btn default">开始排序</a>
								<a id="saveOrder" aa="/cms" href="#" class="btn default">存储排序</a>
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
</html>
<script>
	jQuery(document).ready(function() {     
		//初始化配置toastr
		toastr.options = {
		        "closeButton": true, //是否显示关闭按钮
		        "debug": false, //是否使用debug模式
		        "positionClass": "toast-top-right",//弹出窗的位置
		        "showDuration": "300",//显示的动画时间
		        "hideDuration": "1000",//消失的动画时间
		        "timeOut": "4000", //展现时间
		        "extendedTimeOut": "1000",//加长展示时间
		        "showEasing": "swing",//显示时的动画缓冲方式
		        "hideEasing": "linear",//消失时的动画缓冲方式
		        "showMethod": "fadeIn",//显示时的动画方式
		        "hideMethod": "fadeOut" //消失时的动画方式
		        };
		});
</script>