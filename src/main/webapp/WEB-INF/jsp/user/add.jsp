<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
	$(function(){
		$("#addForm").cmsvalidate();
	});
	
</script>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			用户信息管理
			<small>增加删除修改用户信息 </small>
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
					<a href="admin/user/users" class="ajaxify">用户信息管理</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="admin/user/addUI" class="ajaxify">新增用户</a>
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
					<span class="caption-subject bold uppercase"> 添加用户</span>
				</div>
				<div class="actions">
					<a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title="全屏浏览"></a>
				</div>
			</div>
			<div class="portlet-body form">
				<sf:form id="addForm" method="post" role="form" modelAttribute="userDto" action="admin/user/add" class="form-horizontal ajaxiform">	
					<div class="form-body">
						<div class="form-group form-md-line-input">
							<label class="col-md-2 control-label" for="form_control_1">用户名</label>
							<div class="col-md-6">
								<sf:input path="username" class="form-control" />
								<div class="form-control-focus"></div>
							</div>
						</div>
						<div class="form-group form-md-line-input">
							<label class="col-md-2 control-label" for="form_control_1">昵称(可以是中文)</label>
							<div class="col-md-6">
								<sf:input path="nickname" class="form-control"/>
								<div class="form-control-focus"></div>
							</div>
						</div>
						<div class="form-group form-md-line-input">
							<label class="col-md-2 control-label" for="form_control_1">密码</label>
							<div class="col-md-6">
								<input type="password" name="password" id="password" class="form-control" />
								<sf:errors cssClass="errorContainer" path="password"/>
								<div class="form-control-focus"></div>
							</div>
						</div>
						<div class="form-group form-md-line-input">
							<label class="col-md-2 control-label" for="form_control_1">确认密码</label>
							<div class="col-md-6">
								<input type="password" id="confirmPwd" name="confirmPwd" class="form-control" />
								<div class="form-control-focus"></div>
							</div>
						</div>
						<div class="form-group form-md-line-input">
							<label class="col-md-2 control-label" for="form_control_1">联系电话</label>
							<div class="col-md-6">
								<sf:input path="phone" class="form-control" />
								<div class="form-control-focus"></div>
							</div>
						</div>
						<div class="form-group form-md-line-input">
							<label class="col-md-2 control-label" for="form_control_1">电子邮件</label>
							<div class="col-md-6">
								<sf:input path="email" class="form-control" />
								<sf:errors path="email"/>
								<div class="form-control-focus"></div>
							</div>
						</div>
						<div class="form-group form-md-line-input">
							<label class="col-md-2 control-label" for="form_control_1">状态</label>
							<div class="col-md-6">
								<div class="md-radio-inline">
									<div class="md-radio has-success">
										<input type="radio" id="radio53" name="status" value="0" class="md-radiobtn" checked>
										<label for="radio53">
										<span></span>
										<span class="check"></span>
										<span class="box"></span>
										停用 </label>
									</div>
									<div class="md-radio has-success">
										<input type="radio" id="radio54" name="status" value="1" class="md-radiobtn">
										<label for="radio54">
										<span></span>
										<span class="check"></span>
										<span class="box"></span>
										启用 </label>
									</div>
								</div>
							</div>
						</div>
						<div class="form-group form-md-line-input">
							<label class="col-md-2 control-label" for="form_control_1">角色</label>
							<div class="col-md-6">
								<sf:checkboxes  items="${roles}" itemLabel="name" itemValue="id" path="roleIds"/>
							</div>
						</div>
						<div class="form-group form-md-line-input">
							<label class="col-md-2 control-label" for="form_control_1">用户组</label>
							<div class="col-md-6">
								<sf:checkboxes items="${groups }" path="groupIds" itemLabel="name" itemValue="id"/>
							</div>
						</div>
					</div>
					<div class="form-actions">
						<div class="row">
							<div class="col-md-offset-2 col-md-10">
								<button type="submit" class="btn green">提交</button>
								<a type="button" class="btn default ajaxify" href="admin/user/users">取消</a>
							</div>
						</div>
					</div>
				</sf:form>
			</div>
		</div>
		<!-- END SAMPLE FORM PORTLET-->
	</div>
</div>
