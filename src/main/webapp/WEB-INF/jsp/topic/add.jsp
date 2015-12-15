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
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/admin/topicAdd.js"></script>
</head>
<body>
<div class="fakeloader"></div>
<input type="hidden" id="sid" value="<%=session.getId()%>"/>
<input type="hidden" id="ctx" value="<%=request.getContextPath()%>"/>
<div id="menuContent" class="menuContent" style="display:none; position: absolute;background:#eee;z-index: 999;border:1px solid #999">
	<ul id="mytree" class="ztree" style="margin-top:0;"></ul>
</div>
<!-- BEGIN PAGE CONTENT-->
<div style="margin-left: 210px">
	<div class="col-md-10">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet box red">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-edit"></i>
					文章编辑
				</div>
			</div>
			<div class="portlet-body">
				<sf:form id="addForm" method="post" modelAttribute="topicDto" class="form-horizontal ajaxiform" onkeydown="if(event.keyCode==13){return false;}" >
				<table class="table table-striped table-hover table-bordered">
					<tbody>
						<tr>
							<td width="150px" align="right">文章标题：</td>
							<td>
								<div class="col-md-4">
									<div class="input-icon right">
										<sf:input path="title" class="form-control" />
										<sf:errors cssClass="errorContainer" path="title"/></td>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td width="150px" align="right">文章栏目：</td>
							<td>
								<div class="col-md-4">
									<div class="input-icon right">
										<input type="text" readonly="readonly" name="cname" id="cname" class="form-control" />
										<input type="hidden" readonly="readonly" id="cid" name="cid" value="0"/>
										<sf:errors cssClass="errorContainer" path="cid"/>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<c:choose>
							<c:when test="${isAudit||isAdmin }">
							<td width="150px" align="right">文章状态：</td>
							<td>
								<div class="col-md-4">
									<div class="input-icon right">
										<sf:radiobutton path="status" value="0"/>未发布
										<sf:radiobutton path="status" value="1"/>已发布
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
							<td width="150px" align="right">是否推荐该文章：</td>
							<td>
								<div class="col-md-4">
									<div class="input-icon right">
										<sf:radiobutton path="recommend" value="0"/>不推荐
										<sf:radiobutton path="recommend" value="1"/>推荐		
									</div>
								</div>	
							</td>
						</tr>
						<%-- <tr>
							<td width="150px" align="right">作者：</td>
							<td>
								<div class="col-md-4">
									<div class="input-icon right">
										<sf:input path="author" class="form-control" />
									</div>
								</div>
							</td>
						</tr> --%>
						<tr>
							<td width="150px" align="right">文章关键字：</td>
							<td>
								<div class="col-md-4">
									<div class="input-icon right">
										<sf:input path="keyword" class="form-control" />
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td width="150px" align="right">文章附件：</td>
							<td>
								<div class="col-md-4">
									<div class="input-icon right">
										<div id="attachs"></div>
										<input type="file" id="attach" name="attach" class="form-control" />
										<a class="btn green-meadow" id="uploadFile" >上传</a>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">已传附件</td>
						</tr>
						<tr>
							<td colspan="2">
								<table id="ok_attach" width="890px" class="table table-striped table-hover table-bordered">
									<thead>
										<tr>
										<Td>文件名缩略图</Td>
										<td width="150">文件名</td>
										<td>文件大小</td>
										<td>主页图片</td>
										<td>栏目图片</td>
										<td>附件信息</td>
										<td width="190">操作</td>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan="2">文章内容：</td>
						</tr>
						<tr>
							<td colspan="2">
								<sf:textarea path="content" class="form-control" rows="25" cols="110"/>
							</td>
						</tr>
						<tr>
							<td colspan="2">文章摘要：</td>
						</tr>
						<tr>
							<td colspan="2">
							<sf:textarea path="summary" class="form-control" rows="5" cols="110"/>
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