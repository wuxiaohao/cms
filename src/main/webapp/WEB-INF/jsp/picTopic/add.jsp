<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- 引入进度条 -->
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-1.7.2.min.js"></script>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath }/resources/assets/jindutiao/fakeLoader.css">
<script src="${pageContext.servletContext.contextPath }/resources/assets/jindutiao/fakeLoader.min.js"></script>
<script>
   $(document).ready(function(){
     $(".fakeloader").fakeLoader({
         timeToHide:1500,
         bgColor:"#e74c3c",
         spinner:"spinner2"
     });
   });
</script>
<!-- 引入外部js文件 -->
<%@ include file="/jsp/commonTopic.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/admin/picTopicAdd.js"></script>
</head>
<body>
<div class="fakeloader"></div>
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
					组图编辑
				</div>
			</div>
			<div class="portlet-body">
				<sf:form id="addForm" method="post" modelAttribute="pictureTopicDto" class="form-horizontal ajaxiform" onkeydown="if(event.keyCode==13){return false;}" >
				<table class="table table-striped table-hover table-bordered">
					<tbody>
						<tr>
							<td width="150px" align="right">标题：</td>
							<td>
								<div class="col-md-4">
									<div class="input-icon right">
										<sf:input path="title" class="form-control" />
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td width="150px" align="right">推送到：</td>
							<td>
								<div class="col-md-4">
									<div class="input-icon right">
										<!-- <input type="text" readonly="readonly" name="cname" id="cname" class="form-control" />
										<input type="hidden" readonly="readonly" id="cid" name="cid" value="0"/> -->
										<select name="cid" id="cid" class="bs-select form-control">
											<c:forEach items="${cs }" var="c">
												<option value="${c.id }">${c.name }</option>
											</c:forEach>
										</select>	
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<c:choose>
							<c:when test="${isAudit||isAdmin }">
							<td width="150px" align="right">状态：</td>
							<td>
								<div class="col-md-4">
									<div class="input-icon right">
										<sf:radiobutton path="status" value="0"/>不发布
										<sf:radiobutton path="status" value="1"/>发布
									</div>
								</div>
							</td>
							</c:when>
							<c:otherwise>
								<sf:hidden path="status" value="0" />
							</c:otherwise>
							</c:choose>
						</tr>
						<tr>
							<td width="150px" align="right">是否推荐：</td>
							<td>
								<div class="col-md-4">
									<div class="input-icon right">
										<sf:radiobutton path="recommend" value="0"/>不推荐
										<sf:radiobutton path="recommend" value="1"/>推荐		
									</div>
								</div>	
							</td>
						</tr>
						<tr>
							<td width="150px" align="right">检索关键字：</td>
							<td>
								<div class="col-md-4">
									<div class="input-icon right">
										<sf:input path="keyword" class="form-control" />
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td width="150px" align="right">图片上传：</td>
							<td>
								<div class="col-md-4">
									<div class="input-icon right">
										<div id="attachs"></div>
										<input type="file" id="attach" name="attach" class="form-control" />
										<a class="btn green-meadow" id="uploadFile" >上传</a>
										<span style="color: red">${error }</span>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">已传图片</td>
						</tr>
						<tr>
							<td colspan="2">
								<table id="ok_attach" width="890px" class="table table-striped table-hover table-bordered">
									<thead>
										<tr>
										<td width="280">缩略图</td>
										<td width="350">图片名称</td>
										<td>图片大小</td>
										<td>是否为封面</td>
										<td width="190">操作</td>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan="2">新闻图片说明：</td>
						</tr>
						<tr>
							<td colspan="2">
							<sf:textarea path="explain" class="form-control" rows="5" cols="110"/>
							</td>
						</tr>
						
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2" align="center">
								<a id="addBtn" class="btn green" >添加</a>
								<input type="button" onclick="window.close()"  class="btn default" value="关闭"/>
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