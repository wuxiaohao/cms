<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<style>
html { overflow-x:hidden; }
</style>
<link href="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.servletContext.contextPath }/resources/assets/global/css/components.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-1.7.2.min.js"></script>
<!-- 表单校验 -->
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.validate.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/validate/main.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/core/validate-methods.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/core/jquery.cms.validate.js"></script>
<script type="text/javascript">
	$(function(){
		$("#addForm").cmsvalidate();
	});
</script>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN VALIDATION STATES-->
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-gift"></i>添加[${pc.name}]子栏目功能
				</div>
				<div class="tools">
					<a href="javascript:;" class="collapse">
					</a>
				</div>
			</div>
			<div class="portlet-body">
				<!-- BEGIN FORM-->
				<sf:form id="addForm" method="post" modelAttribute="channel" class="form-horizontal">
					<table class="table table-striped table-hover table-bordered">
						<tbody>
							<tr>
								<td width="150px" align="right">
									栏目名称：<span style="color: red;">* </span>
								</td>
								<td>
									<div class="col-md-4">
										<div class="input-icon right">
											<sf:input path="name" class="form-control" />
											<sf:errors path="name" />
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td width="150px" align="right">
									是否指定链接：<span style="color: red;">* </span>
								</td>
								<td>
									<div class="col-md-4">
										<div class="input-icon right">
											<sf:radiobutton path="customLink" value="0" class="make-switch switch-radio1"/>不指定
											<sf:radiobutton path="customLink" value="1" class="make-switch switch-radio1"/>指定
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td width="150px" align="right">
									链接地址：<span >&nbsp; </span>
								</td>
								<td>
									<div class="col-md-4">
										<div class="input-icon right">
											<sf:input path="customLinkUrl" class="form-control"/>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td width="150px" align="right">
									栏目类型：<span style="color: red;">* </span>
								</td>
								<td>
									<div class="col-md-4">
										<div class="input-icon right">
											<sf:select path="type" class="bs-select form-control">
											<sf:options items="${types}"/>
											</sf:select>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td width="150px" align="right">
									是否在首页显示：<span style="color: red;">* </span>
								</td>
								<td>
									<div class="col-md-4">
										<div class="input-icon right">
											<sf:radiobutton path="isIndex" value="0" class="make-switch switch-radio1"/>不显示
											<sf:radiobutton path="isIndex" value="1" class="make-switch switch-radio1"/>显示
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td width="150px" align="right">
									导航栏目：<span style="color: red;">* </span>
								</td>
								<td>
									<div class="col-md-4">
										<div class="input-icon right">
											<sf:radiobutton path="isTopNav" value="0" class="make-switch switch-radio1"/>不是
											<sf:radiobutton path="isTopNav" value="1" class="make-switch switch-radio1"/>是
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td width="150px" align="right">
									是否是推荐栏目：<span style="color: red;">* </span>
								</td>
								<td>
									<div class="col-md-4">
										<div class="input-icon right">
											<sf:radiobutton path="recommend" value="0" class="make-switch switch-radio1" />不是
											<sf:radiobutton path="recommend" value="1" class="make-switch switch-radio1" />是
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td width="150px" align="right">
									状态：<span style="color: red;">* </span>
								</td>
								<td>
									<div class="col-md-4">
										<div class="input-icon right">
											<sf:radiobutton path="status" value="0" class="make-switch switch-radio1" />启用
											<sf:radiobutton path="status" value="1" class="make-switch switch-radio1" />停用
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td width="150px" align="right">
									导航序号：<span style="color: red;">* </span>
								</td>
								<td>
									<div class="col-md-4">
										<div class="input-icon right">
											<sf:input path="navOrder" class="form-control" />
										</div>
									</div>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2" align="center">
									<button type="submit" class="btn green">添加</button>
								</td>
							</tr>
						</tfoot>
					</table>
				</sf:form>
				<!-- END FORM-->
			</div>
		</div>
		<!-- END VALIDATION STATES-->
	</div>
</div>
