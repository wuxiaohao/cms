<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
$(function(){
	$("#addForm").cmsvalidate();
	$("#urlType").change(function(){
		var v = $(this).val();
		if(v=="-1") {
			$(".type").removeAttr("readonly");
			$(".type").select();
			$(".type").focus();
		} else if(v!="0"){
			$(".type").val(v);
			$(".type").attr("readonly","true");
		}
	})
});
</script>
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
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="admin/cmsLink/updateUI" class="ajaxify">修改超级链接</a>
				</li>
			</ul>
		</div>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN SAMPLE FORM PORTLET-->
		<div class="portlet light bordered">
			<div class="portlet-title">
				<div class="caption font-green">
					<i class="icon-pin font-green"></i>
					<span class="caption-subject bold uppercase"> 修改超链接</span>
				</div>
				<div class="actions">
					<a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title="全屏浏览"></a>
				</div>
			</div>
			<div class="portlet-body form">
				<sf:form id="addForm" role="form" method="post" modelAttribute="cmsLink" action="admin/cmsLink/update/${cmsLink.id }" class="form-horizontal ajaxiform">	
					<div class="form-body">
						<div class="form-group form-md-line-input has-info">
							<label class="col-md-2 control-label" for="form_control_1">超链接标题</label>
							<div class="col-md-3">
								<sf:input path="title" class="form-control" />
								<sf:errors cssClass="errorContainer" path="title"/>
								<div class="form-control-focus"></div>
							</div>
						</div>
						<div class="form-group form-md-line-input has-info">
							<label class="col-md-2 control-label" for="form_control_1">超链接地址</label>
							<div class="col-md-3">
								<sf:input path="url" class="form-control" />
								<div class="form-control-focus"></div>
							</div>
						</div>
						<div class="form-group form-md-line-input has-info">
							<label class="col-md-2 control-label" for="form_control_1">超链接类别</label>
							<div class="col-md-3">
								<select id="urlType" class="bs-select form-control">
								<option value="0">请选择类型</option>
								<c:forEach items="${types }" var="t">
									<option value="${t }">${t }</option>
								</c:forEach>
									<option value="-1">选择其他</option>
								</select>
								<div class="form-control-focus"></div>
								<sf:input path="type" readonly="true" class="form-control type" />
							</div>
						</div>
						<div class="form-group form-md-line-input has-info">
							<label class="col-md-2 control-label" for="form_control_1">打开方式</label>
							<div class="col-md-6">
								<div class="md-radio-inline">
									<c:if test="${cmsLink.newWin == 0 }">
									<div class="md-radio has-success">
										<input type="radio" id="radio53" name="newWin" value="0" class="md-radiobtn" checked>
										<label for="radio53">
										<span></span>
										<span class="check"></span>
										<span class="box"></span>
										本窗口 </label>
									</div>
									<div class="md-radio has-success">
										<input type="radio" id="radio54" name="newWin" value="1" class="md-radiobtn">
										<label for="radio54">
										<span></span>
										<span class="check"></span>
										<span class="box"></span>
										新窗口 </label>
									</div>
									</c:if>
									<c:if test="${cmsLink.newWin == 1 }">
									<div class="md-radio has-success">
										<input type="radio" id="radio53" name="newWin" value="0" class="md-radiobtn">
										<label for="radio53">
										<span></span>
										<span class="check"></span>
										<span class="box"></span>
										本窗口 </label>
									</div>
									<div class="md-radio has-success">
										<input type="radio" id="radio54" name="newWin" value="1" class="md-radiobtn" checked>
										<label for="radio54">
										<span></span>
										<span class="check"></span>
										<span class="box"></span>
										新窗口 </label>
									</div>
									</c:if>
								</div>
							</div>
						</div>
						<div class="form-group form-md-line-input has-info">
							<label class="col-md-2 control-label" for="form_control_1">链接标签ID</label>
							<div class="col-md-3">
								<sf:input path="urlId" class="form-control" />
								<div class="form-control-focus"></div>
							</div>
						</div>
						<div class="form-group form-md-line-input has-info">
							<label class="col-md-2 control-label" for="form_control_1">>链接标签类别</label>
							<div class="col-md-3">
								<sf:input path="urlClass" class="form-control" />
								<div class="form-control-focus"></div>
							</div>
						</div>
					</div>
					<div class="form-actions">
						<div class="row">
							<div class="col-md-offset-2 col-md-10">
								<button type="submit" class="btn green-meadow">提交</button>
								<a class="btn default ajaxify" href="admin/cmsLink/links">取消</a>
							</div>
						</div>
					</div>
				</sf:form>
			</div>
		</div>
		<!-- END SAMPLE FORM PORTLET-->
	</div>
</div>
