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
		$(this).after("<span>&nbsp;<input type='text'value='"+pos+"' size='3' readonly='true' />&nbsp;<input id='pos"+id+"' type='hidden' value='"+pos+"'/><a href='#' class='btn btn-sm blue confirmPos'>确定</a>&nbsp;<a href='' class='btn btn-sm red cancelPos'>取消</a></span>");
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
		var id = $(this).parent("span").prev("a").attr("objid");
		var op = $(this).parent("span").prev("a").attr("pos");
		var np = $(this).prev("input").val();
		if(op!=np) {
			//通过dwr更新节点
			dwrService.updateLinkPos(id,op,np,function(){
				//刷新页面
				$("#lianjie").click();
				showMessage("已重新排序!","");
			});
		}
		$(this).parent("span").prev("a.setPos").on("click",setPos);//重新绑定点击事件
		$(this).parent("span").remove();
	});
	
	//选择超链接类型事件
	$("#selectType").change(function(){
		var href = $("#lianjie").attr("href");
		var v = $(this).val();
		if(v != "-1"){
			hrefNew = href +"?type="+v;
			$("#lianjie").attr("href",hrefNew);
		} 
		$("#lianjie").click();
		$("#lianjie").attr("href",href);
	})
}) 
</script>
</head>
<a style="display: none;" id="lianjie" class="ajaxify" href="<%=request.getContextPath() %>/admin/cmsLink/links"></a>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			超级链接管理
			<small>增加删除修改超级链接 </small>
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
					<a href="admin/cmsLink/links" class="ajaxify">超级链接管理</a>
				</li>
			</ul>
		</div>
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
			<div class="portlet-title">
				<div class="caption">
					链接列表
				</div>
				<div class="caption" style="float: right;">
					<select id="selectType" class="bs-select form-control">
					<option value="-1">请选择类别筛选</option>
					<c:forEach items="${ types}" var="t">
						<option value="${t }" <c:if test="${type eq t}">selected</c:if>>${t }</option>
					</c:forEach>
					</select>
				</div>
			</div>
			<div class="portlet-body">
				<div class="table-toolbar">
					<div class="btn-group">
						<a class="btn green ajaxify" href="admin/cmsLink/addUI">添加超链接</a>
					</div>
				</div>
				<table class="table table-striped table-hover table-bordered" id="sample_1">
					<thead>
						<tr>
							<th width="15%">标题</th>
							<th width="25%">超链接</th>
							<th>类型</th>
							<th width="8%">打开方式</th>
							<th width="30%">位置</th>
							<th width="13%">用户操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${datas.datas }" var="cl">
							<tr>
								<td><a href="admin/cmsLink/${cl.id }" class="ajaxify">${cl.title }</a></td>
								<td><a href="${cl.url }" target="_blank">${cl.url }</a></td>
								<td>${cl.type }</td>
								<td>
									<c:if test="${cl.newWin eq 0}">本窗口</c:if>
									<c:if test="${cl.newWin eq 1}">新窗口</c:if>
								</td>
								<td class="posCon">
									${cl.pos }&nbsp;<a class="btn btn-sm green setPos" pos="${cl.pos }" objid="${cl.id }">排序</a>
								</td>
								
								<td><a href="admin/cmsLink/delete/${cl.id }?type=${type}" class="btn btn-sm red ajaxify delete"> 删除 </a>
									<a href="admin/cmsLink/updateUI/${cl.id }" class="btn btn-sm blue ajaxify"> 更新 </a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
					<tr>
						<td colspan="6" style="text-align:right;margin-right:10px;">
						<jsp:include page="/jsp/pager.jsp">
							<jsp:param value="${datas.total }" name="totalRecord"/>
							<jsp:param value="admin/cmsLink/links" name="url"/>
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