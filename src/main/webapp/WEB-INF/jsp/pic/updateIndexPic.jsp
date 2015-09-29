<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/jcrop/css/jquery.Jcrop.css"/>
<link href="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.servletContext.contextPath }/resources/assets/global/css/components.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/js/base/jquery.ui.all.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/admin/article.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/validate/main.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/core/jquery.cms.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/core/jquery.cms.core.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/uploadify/uploadify.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/uploadify/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/jcrop/js/jquery.Jcrop.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/admin/indexPic.js"></script>
</head>
<body>
<input type="hidden" id="sid" value="<%=session.getId()%>"/>
<input type="hidden" id="ctx" value="<%=request.getContextPath()%>"/>
<!-- BEGIN PAGE CONTENT-->
<div style="margin-left: 210px">
	<div class="col-md-10">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet box red">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-edit"></i>
					添加首页图片
				</div>
			</div>
			<div class="portlet-body">
				<sf:form id="addForm" method="post" modelAttribute="indexPic" class="form-horizontal ajaxiform" onkeydown="if(event.keyCode==13){return false;}" >
				<table class="table table-striped table-hover table-bordered">
					<tbody>
						<tr>
							<td colspan="2">
								<div class="col-md-4">
									<div class="input-icon right">
										<input type="file" id="indexPic" name="indexPic"/>
										<input id="newName" name="newName" class="form-control" value="${indexPic.newName }" readonly />
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div id="indexPicView"></div>
							</td>
						</tr>
						<tr>
							<td width="150px" align="right">当前图片：</td>
							<td>
								<div class="col-md-4">
									<div class="input-icon right">
										<img src="<%=request.getContextPath() %>/resources/indexPic/${indexPic.newName}"/>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td width="150px" align="right">首页图片标题：</td>
							<td>
								<div class="col-md-4">
									<div class="input-icon right">
										<sf:input path="title" class="form-control" />
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td width="150px" align="right">首页图片子标题：</td>
							<td>
								<div class="col-md-4">
									<div class="input-icon right">
										<sf:input path="subTitle" class="form-control" />
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td width="150px" align="right">状态：</td>
							<td>
								<div class="col-md-4">
									<div class="input-icon right">
										<sf:radiobutton path="status" value="0"/>未发布
										<sf:radiobutton path="status" value="1"/>已发布
									</div>
								</div>	
							</td>
						</tr>
						<tr>
							<td width="150px" align="right">链接类型：</td>
							<td>
								<div class="col-md-4">
									<div class="input-icon right">
										<sf:radiobutton path="linkType" value="0"/>站内链接
										<sf:radiobutton path="linkType" value="1"/>站外链接
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td width="150px" align="right">链接地址：</td>
							<td>
								<div class="col-md-4">
									<div class="input-icon right">
										<sf:input path="linkUrl" class="form-control" />
									</div>
								</div>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2" align="center">
								<button type="submit" class="btn green" >更新首页图片</button>
							</td>
						</tr>
					</tfoot>
				</table>
				</sf:form>
			</div>
		</div>
		<!-- END EXAMPLE TABLE PORTLET-->
	</div>
</div>
<!-- END PAGE CONTENT -->
</body>
</html>