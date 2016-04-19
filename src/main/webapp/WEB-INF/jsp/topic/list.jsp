<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 局部刷新table表格 -->
<%-- <script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/admin/listTopic.js"></script> --%>
<script type="text/javascript">
	$(function(){
		$("a.delete").confirmOperator();
		var success='<%=request.getAttribute("success")%>';
		var error='<%=request.getAttribute("error")%>';
		showMessage(success,error);
		//条件检索
		$("#search").click(function(event){
			var con = $.trim($("#con").val()); 
			var cid = $("#cid").val();
			var href ="";
			var lianjie = "";
			if(${status} == 0){
				href = $(".unaudits").attr("href");
				lianjie = $(".unaudits");
			} else {
				href = $(".audits").attr("href");
				lianjie = $(".audits");
			}
			if(!((con == "" || con == null) && (cid ==0 || cid == null))){
				hrefNew = href+"?con="+con+"&cid="+cid;
				lianjie.attr("href",hrefNew);
			}
			lianjie.click();
			lianjie.attr("href",href);
		});
	});
</script>
</head>
<a style="display: none;" class="ajaxify audits" href="<%=request.getContextPath() %>/admin/topic/audits"></a>
<a style="display: none;" class="ajaxify unaudits" href="<%=request.getContextPath() %>/admin/topic/unaudits"></a>
<div class="row">
	<div class="col-md-12">
		<h3 class="page-title">
			文章新闻管理
			<small>发布、添加、更新、删除文章 </small>
		</h3>
		<div class="page-bar">
			<ul class="page-breadcrumb">
				<li>
					<i class="fa fa-home"></i>
					<a class="ajaxify start" href="layout_ajax_content_1.html">首页</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a>新闻管理</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="admin/topic/audits" class="ajaxify">文章新闻管理</a>
				</li>
			</ul>
		</div>
	</div>
</div>
<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet gren">
			<div class="portlet-title">
				<div class="caption" style="float: right;">
					<c:if test="${status eq 0 }">
						<a href="admin/topic/audits" class="btn btn-default red-stripe ajaxify">已发布文章列表</a>
						<a href="admin/topic/unaudits" class="btn red ajaxify">未发布文章列表</a>
					</c:if>
					<c:if test="${status eq 1 }">
						<a href="admin/topic/audits" class="btn red ajaxify">已发布文章列表</a>
						<a href="admin/topic/unaudits" class="btn btn-default red-stripe ajaxify">未发布文章列表</a>
					</c:if>
					<a class="btn btn-default green-stripe" href="javascript:openWin('<%=request.getContextPath() %>/admin/topic/add','addTopic')">添加文章</a>
				</div>
			</div>
			<div class="portlet-body">
				<div class="note note-success" style="height: 60px">
					<p style="float: left;"><font size="3">标题</font></p>
					<div class="col-md-2 c">
						<input type="text" name="con" id="con" value="${con}" class="form-control" />
					</div>
					<div class="col-md-2 c">
						<select name="cid" id="cid" class="bs-select form-control">
							<option value="0">请选择栏目</option>
							<c:forEach items="${cs }" var="c">
								<c:if test="${c.id  eq cid}">
								<option value="${c.id }" selected="selected">${c.name }</option>
								</c:if>
								<c:if test="${c.id  ne cid}">
								<option value="${c.id }">${c.name }</option>
								</c:if>
							</c:forEach>
						</select>	
					</div>
					<dir class="c">
						<button class="btn btn-circle green-meadow" id="search"><i class="fa fa-search"></i>检索</button>
					</dir>
				</div>
				<table class="table table-striped table-hover table-bordered" id="sample_1">
					<thead>
						<tr>
							<td width="25%" align="center">文章标题</td>
							<c:if test="${status eq 0}">
							<td>作者</td>
							</c:if>
							<c:if test="${status eq 1}">
							<td>发布人</td>
							</c:if>
							<td>是否推荐</td>
							<td>所属频道</td>
							<c:if test="${status eq 0}">
							<td>创建时间</td>
							</c:if>
							<c:if test="${status eq 1}">
							<td>发布时间</td>
							</c:if>
							<td width="140">状态</td>
							<td width="150">操作</td>
						</tr>
					</thead>
					<c:forEach items="${datas.datas }" var="t">
						<tbody>
							<tr>
								<td>
									<a href="admin/topic/${t.id }" class="ajaxify" >
									<c:choose>
										<c:when test="${fn:length(t.title)> 13 }">
											<c:out value="${fn:substring(t.title, 0, 13)}..." />  
										</c:when>
										<c:otherwise>
											<c:out value="${t.title }"></c:out>
										</c:otherwise>
									</c:choose>
									</a>
								</td>
								<c:if test="${status eq 0}">
								<td>${t.author}</td>
								</c:if>
								<c:if test="${status eq 1}">
								<td>${t.auditor}</td>
								</c:if>
								<td>
									<c:if test="${t.recommend eq 0 }">不推荐</c:if>
									<c:if test="${t.recommend eq 1 }">推荐</c:if>
								</td>
								<td>${t.cname }</td>
								<c:if test="${status eq 0}">
								<td><c:out value="${fn:substring(t.createDate,0,19) }" /></td>
								</c:if>
								<c:if test="${status eq 1}">
								<td><c:out value="${fn:substring(t.publishDate,0,19) }" /></td>
								</c:if>
								<td>
									<c:if test="${t.status eq 0 }">
										<span style="color: red">未发布&nbsp;</span>
										<a href="admin/topic/changeStatus/${t.id }?status=${t.status}&con=${con}&cid=${cid }" class="btn btn-circle btn-sm purple ajaxify delete">发布</a>
									</c:if>
									<c:if test="${t.status eq 1 }">
										<span>已发布&nbsp;</span>
										<a href="admin/topic/changeStatus/${t.id }?status=${t.status}&con=${con}&cid=${cid }" class="btn btn-circle btn-sm purple ajaxify delete">取消发布</a>
									</c:if>
								</td>
								<td>
									<%-- <a href="javascript:openWin('<%=request.getContextPath() %>/admin/topic/update/${t.id}','updateTopic')" class="btn btn-sm blue"><i class="fa fa-edit"></i>&nbsp;更新</a>			
									<a href="admin/topic/delete/${t.id }?status=${t.status}&con=${con}&cid=${cid }" title="${user.id }" class="btn btn-sm red delete ajaxify"><i class="fa fa-trash-o"></i>&nbsp;删除</a> --%>
									<div class="btn-group">
										<button class="btn btn-circle blue btn-sm dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="true">
										选项管理 <i class="fa fa-angle-down"></i>
										</button>
										<ul class="dropdown-menu" role="menu">
											<li>
												<a href="javascript:openWin('<%=request.getContextPath() %>/admin/topic/update/${t.id}','updateTopic')"><i class="fa fa-edit"></i>&nbsp;更新</a>
											</li>
											<li>
												<a href="admin/topic/delete/${t.id }?status=${t.status}&con=${con}&cid=${cid }" class="delete ajaxify"><i class="fa fa-trash-o"></i>&nbsp;删除</a>
											</li>
											<li>
												<a href="topic/${t.id }" target="_blank"><i class="fa fa-eye"></i>&nbsp;预览</a>
											</li>
										</ul>
									</div>
								</td>
							</tr>
						</tbody>
					</c:forEach>
					<tfoot>
						<tr>
							<td colspan="7" style="text-align:right;margin-right:10px;">
							<c:if test="${status eq 0 }">
							<jsp:include page="/jsp/pager.jsp">
								<jsp:param value="${datas.total }" name="totalRecord"/>
								<jsp:param value="admin/topic/unaudits" name="url"/>
							</jsp:include>
							</c:if>
							<c:if test="${status eq 1 }">
							<jsp:include page="/jsp/pager.jsp">
								<jsp:param value="${datas.total }" name="totalRecord"/>
								<jsp:param value="admin/topic/audits" name="url"/>
							</jsp:include>
							</c:if>
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