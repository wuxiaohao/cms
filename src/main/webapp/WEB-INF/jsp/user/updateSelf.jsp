<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/jcrop/css/jquery.Jcrop.css" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/jcrop/js/jquery.Jcrop.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/admin/updateSelf.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<input type="hidden" id="sid" value="<%=session.getId()%>"/>
<input type="hidden" id="ctx" value="<%=request.getContextPath()%>"/>
<div class="row">
	<div class="col-md-12">
		<h3 class="page-title">
			个人中心
			<small>修改个人信息 </small>
		</h3>
		<div class="page-bar">
			<ul class="page-breadcrumb">
				<li>
					<i class="fa fa-home"></i>
					<a class="ajaxify start" href="layout_ajax_content_1.html">首页</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a>个人中心</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="admin/user/showMySelf" class="ajaxify">查询个人信息</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="admin/user/updateSelfUI" class="ajaxify">修改个人信息</a>
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
					<span class="caption-subject bold uppercase"> 修改个人信息</span>
				</div>
				<div class="actions">
					<a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title="全屏浏览"></a>
				</div>
			</div>
			<div class="portlet-body form">
				<sf:form id="addForm" method="post" role="form" modelAttribute="userDto" action="admin/user/updateSelf" class="form-horizontal ajaxiform" onkeydown="if(event.keyCode==13){return false;}">
					<sf:hidden path="id"/>
					<sf:hidden path="username"/>
					<sf:hidden path="password"/>
					<sf:hidden path="icon" id="icon" />
					<div class="form-body">
						<div class="form-group form-md-line-input">
							<label class="col-md-2 control-label" for="form_control_1">头像</label>
							<div class="col-md-6">
								<c:if test="${!empty(userDto.icon)}">
									<div id="touxiangImg">
										<img style="border:3px solid #AAAAAA"; src="<%=request.getContextPath() %>/resources/userIcon/${userDto.icon}"/>
									</div>
								</c:if>
								<c:if test="${empty(userDto.icon) }">
									<div id="touxiangImg">
										<img style="border:3px solid #AAAAAA"; src="<%=request.getContextPath() %>/resources/userIcon/zanwu.png"/>
									</div>
								</c:if>
								<br/>
								<input type="file" id="ico" class="form-control" />
							</div>
						</div>
						<div class="form-group form-md-line-input" style="display: none;" id="hei">
							<label class="col-md-2"></label>
							<div class="col-md-2">
								<div id="headImg"></div>
							</div>
						</div>
						<div class="form-group form-md-line-input">
							<label class="col-md-2 control-label" for="form_control_1">显示昵称(可以是中文) </label>
							<div class="col-md-6">
								<sf:input path="nickname" class="form-control" placeholder="请输入你的昵称" />
								<div class="form-control-focus">
								</div>
							</div>
						</div>
						<div class="form-group form-md-line-input">
							<label class="col-md-2 control-label" for="form_control_1">联系电话  </label>
							<div class="col-md-6">
								<sf:input path="phone" class="form-control" placeholder="请输入你的联系电话" />
								<div class="form-control-focus">
								</div>
							</div>
						</div>
						<div class="form-group form-md-line-input">
							<label class="col-md-2 control-label" for="form_control_1">电子邮件  </label>
							<div class="col-md-6">
								<sf:input path="email" class="form-control" placeholder="请输入你的电子邮件" />
								<sf:errors path="email"/>
								<div class="form-control-focus">
								</div>
							</div>
						</div>
					</div>
					<div class="form-actions">
						<div class="row">
							<div class="col-md-offset-2 col-md-10">
								<button type="submit" class="btn green">修改</button>
								<a type="button" class="btn default ajaxify" href="admin/user/showMySelf">取消</a>
							</div>
						</div>
					</div>
				</sf:form>
			</div>
		</div>
		<!-- END SAMPLE FORM PORTLET-->
	</div>
</div>
