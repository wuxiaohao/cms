<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/jsp/commonTopic.jsp"%>
<!-- 引入外部js文件 -->
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/admin/topicAdd.js"></script>
</head>
<body>
<input type="hidden" id="sid" value="<%=session.getId()%>"/>
<input type="hidden" id="ctx" value="<%=request.getContextPath()%>"/>
<div id="menuContent" class="menuContent" style="display:none; position: absolute;background:#eee;z-index: 999;border:1px solid #999">
	<ul id="mytree" class="ztree" style="margin-top:0;"></ul>
</div>
<!-- BEGIN PAGE CONTENT-->
<div style="margin-left: 210px;margin-top: 10px;">
	<div class="col-md-10">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet box red">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-edit"></i>
					文章查看
				</div>
			</div>
			<div class="portlet-body">
				<table class="table table-striped table-hover table-bordered">
					<tbody>
						<tr>
							<td width="150px" align="right">文章标题：</td>
							<td>${topic.title}</td>
						</tr>
						<tr>
							<td width="150px" align="right">作者：</td>
							<td>${topic.user.nickname }</td>
						</tr>
						<tr>
							<td width="150px" align="right">文章栏目：</td>
							<td>${topic.channel.name }</td>
						</tr>
						<tr>
							<td width="150px" align="right">文章状态：</td>
							<td>
								<c:if test="${topic.status eq 0 }">未发布</c:if>
								<c:if test="${topic.status eq 1 }">已发布</c:if>
							</td>
						</tr>
						<tr>
							<td width="150px" align="right">是否推荐该文章：</td>
							<td>
								<c:if test="${topic.recommend eq 0 }">不推荐</c:if>
								<c:if test="${topic.recommend eq 1 }">推荐</c:if>
							</td>
						</tr>
						<tr>
							<td width="150px" align="right">创建时间：</td>
							<td>
								<fmt:formatDate value="${topic.createDate}" pattern="yyyy-MM-dd HH:mm"/>
							</td>
						</tr>
						<tr>
							<td width="150px" align="right">发布时间：</td>
							<td>
								<fmt:formatDate value="${topic.publishDate}" pattern="yyyy-MM-dd HH:mm"/>
							</td>
						</tr>
						<tr>
							<td width="150px" align="right">文章关键字：</td>
							<td>${topic.keyword }</td>
						</tr>
						<tr>
							<td width="150px" align="right">文章附件：</td>
							<td>
								<c:forEach items="${atts }" var="att">
								<a href="<%=request.getContextPath() %>/resources/upload/${att.newName}" class="list_op">${att.oldName }</a>&nbsp;
								</c:forEach>
							</td>
						</tr>
						<tr><td colspan="2">文章内容：</td></tr>
						<tr>
							<td colspan="2">${topic.content }</td>
						</tr>
						<tr>
							<td colspan="2">文章摘要：</td>
						</tr>
						<tr><td colspan="2">${topic.summary }</td></tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2" align="center">
								<input type="button" onclick="window.close()"  class="btn default" value="关闭"/>
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
</body>
</html>