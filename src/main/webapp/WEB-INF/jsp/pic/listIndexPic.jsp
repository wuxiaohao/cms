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
	
	$(".setPos").click(setPos);
	function setPos(event) {
		event.preventDefault();//设置<a></a>标签不执行超链接的操作
		var pos = $(this).attr("pos");
		var id = $(this).attr("objid");
		$(this).after("<span>&nbsp;<input type='text' value='"+pos+"' size='3' readonly='true' />&nbsp;<input id='pos"+id+"' type='hidden' value='"+pos+"'/><a href='#' class='btn btn-sm blue confirmPos'>确定</a>&nbsp;<a href='' class='btn btn-sm red cancelPos'>取消</a></span>");
		$(this).next("span").children("input:text").spinner({
			min:$("#minPos").val(),
			max:$("#maxPos").val(),
			spin:function(event,ui){
				$("#pos"+id).val(ui.value);
			} 
		});
		//取消点击事件
		$(this).off("click");
	}
	
	//取消事件
	//新加入的元素，不能直接$(".posCon").click(function{});只能用事件委派的方式
	$(".posCon").on("click",".cancelPos",function(e){
		e.preventDefault();
		$(this).parent("span").prev("a.setPos").on("click",setPos);//重新绑定点击事件
		$(this).parent("span").remove();
	});
	//确定事件
	$(".posCon").on("click",".confirmPos",function(e){
		e.preventDefault();
		var id = $(this).parent("span").prev("a").attr("objid"); //当前首页图片的id
		var op = $(this).parent("span").prev("a").attr("pos"); //原位置
		var np = $(this).prev("input").val(); //新位置
		if(op!=np) {
			//通过dwr更新节点
			dwrService.updatePicPos(id,op,np,function(){
				//刷新页面
				$("#tp").click();
				showMessage("已重新排序!","");
			});
		}
		$(this).parent("span").prev("a.setPos").on("click",setPos);//重新绑定点击事件
		$(this).parent("span").remove();
	});
	
}) 
</script>
</head>
<a style="display: none;" id="tp" class="ajaxify" href="<%=request.getContextPath() %>/admin/pic/indexPics"></a>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			首页宣传图片管理
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
				<a href="admin/pic/indexPics" class="ajaxify">首页宣传图片管理</a>
			</li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>

<input type="hidden" id="maxPos" value="${max }"/>
<input type="hidden" id="minPos" value="${min }"/>
<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet gren">
			<div class="portlet-body">
				<div class="table-toolbar">
					<div class="btn-group">
						<a class="btn green" href="javascript:openWin('<%=request.getContextPath() %>/admin/pic/addIndexPic','addPic')">添加宣传图片</a>
					</div>
				</div>
				<table class="table table-striped table-hover table-bordered" id="sample_1">
					<thead>
						<tr>
							<th width="15%">缩略图</th>
							<th width="25%">图片标题</th>
							<th>状态</th>
							<th width="8%">链接类型</th>
							<th width="30%">位置</th>
							<th width="12%">用户操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${datas.datas }" var="pic">
							<tr>
								<td><img src='<%=request.getContextPath()%>/resources/indexPic/thumbnail/${pic.newName}'/></td>
								<td><a href="admin/pic/indexPic/${pic.id }" class="ajaxify">${pic.title }</a></td>
								<td>
									<c:if test="${pic.status eq 0}">
										<span style="color: red">停用</span>
										<a href="admin/pic/updateIndexPicStatus/${pic.id }"  class="btn btn-sm btn-info ajaxify">启用</a>
									</c:if>
									<c:if test="${pic.status eq 1}">
										<span>启用</span>
										<a href="admin/pic/updateIndexPicStatus/${pic.id }"  class="btn btn-sm btn-danger ajaxify">停用</a>
									</c:if>
								</td>
								<td>
									<c:if test="${pic.linkType eq 0}">站内链接</c:if>
									<c:if test="${pic.linkType eq 1}"><a href='${pic.linkUrl }' target="_blank">站外链接</a></c:if>
								</td>
								<td class="posCon">
									${pic.pos }&nbsp;<a class="btn btn-sm green setPos" pos="${pic.pos }" objid="${pic.id }">排序</a>
								</td>
								
								<td><a href="admin/pic/deleteIndexPic/${pic.id }" class="btn btn-sm red ajaxify delete"> 删除 </a>
									<a href="javascript:openWin('<%=request.getContextPath() %>/admin/pic/updateIndexPic/${pic.id }','updatePic')" class="btn btn-sm blue"> 更新 </a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
					<tr>
						<td colspan="6" style="text-align:right;margin-right:10px;">
						<jsp:include page="/jsp/pager.jsp">
							<jsp:param value="${datas.total }" name="totalRecord"/>
							<jsp:param value="admin/pic/indexPics" name="url"/>
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