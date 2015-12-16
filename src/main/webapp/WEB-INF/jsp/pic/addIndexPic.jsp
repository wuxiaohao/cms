<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/jcrop/css/jquery.Jcrop.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/jcrop/js/jquery.Jcrop.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/admin/indexPic.js"></script>
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
					<a href="admin/pic/addIndexPicUI" class="ajaxify">添加首页宣传图片</a>
				</li>
			</ul>
		</div>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<input type="hidden" id="sid" value="<%=session.getId()%>"/>
<input type="hidden" id="ctx" value="<%=request.getContextPath()%>"/>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN SAMPLE FORM PORTLET-->
		<div class="portlet light bordered">
			<div class="portlet-title">
				<div class="caption font-blue">
					<i class="icon-pin font-blue"></i>
					<span class="caption-subject bold uppercase"> 添加首页宣传图片</span>
				</div>
				<div class="actions">
					<a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title="全屏浏览"></a>
				</div>
			</div>
			<div class="portlet-body form">
				<sf:form id="addForm" method="post" role="form" modelAttribute="indexPic" action="admin/pic/addIndexPic" class="form-horizontal ajaxiform" onkeydown="if(event.keyCode==13){return false;}" >	
					<div class="form-body">
						<div class="form-group form-md-line-input has-info">
							<label class="col-md-2 control-label" for="form_control_1"></label>
							<div class="col-md-6">
								<input type="file" id="indexPic" name="indexPic" class="form-control" />
							</div>
						</div>
						<div class="form-group form-md-line-input has-info">
							<label class="col-md-2 control-label" for="form_control_1">图片名称</label>
							<div class="col-md-6">
								<input id="newName" name="newName" readonly class="form-control"/>
								<div class="form-control-focus"></div>
							</div>
						</div>
						<div class="form-group form-md-line-input has-info">
							<div class="col-md-6">
								<div id="indexPicView" style="margin-left: 145px;"></div>
							</div>
						</div>
						<div class="form-group form-md-line-input has-info">
							<label class="col-md-2 control-label" for="form_control_1">图片标题</label>
							<div class="col-md-6">
								<sf:input path="title" class="form-control"/>
								<div class="form-control-focus"></div>
							</div>
						</div>
						<div class="form-group form-md-line-input has-info">
							<label class="col-md-2 control-label" for="form_control_1">图片子标题</label>
							<div class="col-md-6">
								<sf:input path="subTitle" class="form-control"/>
								<div class="form-control-focus"></div>
							</div>
						</div>
						<div class="form-group form-md-line-input has-info">
							<label class="col-md-2 control-label" for="form_control_1">状态</label>
							<div class="col-md-6">
								<div class="md-radio-inline">
									<div class="md-radio has-success">
										<input type="radio" id="radio53" name="status" value="0" class="md-radiobtn" checked>
										<label for="radio53">
										<span></span>
										<span class="check"></span>
										<span class="box"></span>
										未发布 </label>
									</div>
									<div class="md-radio has-success">
										<input type="radio" id="radio54" name="status" value="1" class="md-radiobtn">
										<label for="radio54">
										<span></span>
										<span class="check"></span>
										<span class="box"></span>
										已发布 </label>
									</div>
								</div>
							</div>
						</div>
						<div class="form-group form-md-line-input has-info">
							<label class="col-md-2 control-label" for="form_control_1">链接类型</label>
							<div class="col-md-6">
								<div class="md-radio-inline">
									<div class="md-radio has-success">
										<input type="radio" id="radio55" name="linkType" value="0" class="md-radiobtn" checked>
										<label for="radio55">
										<span></span>
										<span class="check"></span>
										<span class="box"></span>
										站内链接 </label>
									</div>
									<div class="md-radio has-success">
										<input type="radio" id="radio56" name="linkType" value="1" class="md-radiobtn">
										<label for="radio56">
										<span></span>
										<span class="check"></span>
										<span class="box"></span>
										站外链接 </label>
									</div>
								</div>
							</div>
						</div>
						<div class="form-group form-md-line-input has-info">
							<label class="col-md-2 control-label" for="form_control_1">链接地址</label>
							<div class="col-md-6">
								<sf:input path="linkUrl" class="form-control"/>
								<div class="form-control-focus"></div>
							</div>
						</div>
					</div>
					<div class="form-actions">
						<div class="row">
							<div class="col-md-offset-2 col-md-10">
								<button type="submit" class="btn green-meadow">提交</button>
								<a type="button" class="btn default ajaxify" href="admin/pic/indexPics">取消</a>
							</div>
						</div>
					</div>
				</sf:form>
			</div>
		</div>
		<!-- END SAMPLE FORM PORTLET-->
	</div>
</div>