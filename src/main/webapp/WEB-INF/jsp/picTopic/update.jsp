<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- 引入外部js文件 -->
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/admin/picTopicAdd.js"></script>
<div class="row">
	<div class="col-md-12">
		<h3 class="page-title">
			组图新闻管理
			<small>发布、添加、更新、删除组图 </small>
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
					<a href="admin/picTopic/audits" class="ajaxify">组图新闻管理</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="admin/picTopic/updateUI/${pictureTopicDto.id}" class="ajaxify">添加组图新闻</a>
				</li>
			</ul>
		</div>
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
					<span class="caption-subject bold uppercase"> 组图修改</span>
				</div>
				<div class="actions">
					<a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title="全屏浏览"></a>
				</div>
			</div>
			<div class="portlet-body form">
				<sf:form id="addForm" method="post" role="form" modelAttribute="pictureTopicDto" action="admin/picTopic/update/${pictureTopicDto.id }" class="form-horizontal ajaxiform" onkeydown="if(event.keyCode==13){return false;}" >	
					<div class="form-body">
						<div class="form-group form-md-line-input has-info">
							<label class="col-md-2 control-label" for="form_control_1">标题</label>
							<div class="col-md-6">
								<sf:input path="title" class="form-control"/>
								<div class="form-control-focus"></div>
							</div>
						</div>
						<div class="form-group form-md-line-input has-info">
							<label class="col-md-2 control-label" for="form_control_1">推送到</label>
							<div class="col-md-6">
								<select name="cid" id="cid" class="bs-select form-control">
									<c:forEach items="${cs }" var="c">
										<c:if test="${c.id eq cid}">
										<option value="${c.id }" selected="selected">${c.name }</option>
										</c:if>
										<c:if test="${c.id ne cid}">
										<option value="${c.id }">${c.name }</option>
										</c:if>
									</c:forEach>
								</select>	
								<div class="form-control-focus"></div>
							</div>
						</div>
						<c:choose>
						<c:when test="${isAudit||isAdmin }">
						<div class="form-group form-md-line-input has-info">
							<label class="col-md-2 control-label" for="form_control_1">状态</label>
							<div class="col-md-6">
								<div class="md-radio-inline">
									<c:if test="${pictureTopicDto.status == 0 }">
									<div class="md-radio has-success">
										<input type="radio" id="radio53" name="status" value="0" class="md-radiobtn" checked>
										<label for="radio53">
										<span></span>
										<span class="check"></span>
										<span class="box"></span>
										不发布 </label>
									</div>
									<div class="md-radio has-success">
										<input type="radio" id="radio54" name="status" value="1" class="md-radiobtn">
										<label for="radio54">
										<span></span>
										<span class="check"></span>
										<span class="box"></span>
										发布 </label>
									</div>
									</c:if>
									<c:if test="${pictureTopicDto.status == 1 }">
									<div class="md-radio has-success">
										<input type="radio" id="radio53" name="status" value="0" class="md-radiobtn" >
										<label for="radio53">
										<span></span>
										<span class="check"></span>
										<span class="box"></span>
										不发布 </label>
									</div>
									<div class="md-radio has-success">
										<input type="radio" id="radio54" name="status" value="1" class="md-radiobtn" checked>
										<label for="radio54">
										<span></span>
										<span class="check"></span>
										<span class="box"></span>
										发布 </label>
									</div>
									</c:if>
								</div>
							</div>
						</div>
						</c:when>
						<c:otherwise>
							<sf:hidden path="status" value="0" />
						</c:otherwise>
						</c:choose>
						<div class="form-group form-md-line-input has-info">
							<label class="col-md-2 control-label" for="form_control_1">是否推荐</label>
							<div class="col-md-6">
								<div class="md-radio-inline">
									<c:if test="${pictureTopicDto.recommend == 0 }">
									<div class="md-radio has-success">
										<input type="radio" id="radio55" name="recommend" value="0" class="md-radiobtn" checked>
										<label for="radio55">
										<span></span>
										<span class="check"></span>
										<span class="box"></span>
										不推荐 </label>
									</div>
									<div class="md-radio has-success">
										<input type="radio" id="radio56" name="recommend" value="1" class="md-radiobtn">
										<label for="radio56">
										<span></span>
										<span class="check"></span>
										<span class="box"></span>
										推荐 </label>
									</div>
									</c:if>
									<c:if test="${pictureTopicDto.recommend == 1 }">
									<div class="md-radio has-success">
										<input type="radio" id="radio55" name="recommend" value="0" class="md-radiobtn" >
										<label for="radio55">
										<span></span>
										<span class="check"></span>
										<span class="box"></span>
										不推荐 </label>
									</div>
									<div class="md-radio has-success">
										<input type="radio" id="radio56" name="recommend" value="1" class="md-radiobtn" checked>
										<label for="radio56">
										<span></span>
										<span class="check"></span>
										<span class="box"></span>
										推荐 </label>
									</div>
									</c:if>
								</div>
							</div>
						</div>
						<div class="form-group form-md-line-input has-info">
							<label class="col-md-2 control-label" for="form_control_1">检索关键字</label>
							<div class="col-md-6">
								<div class="input-icon right">
									<div id="keyword-exists">
										<c:forEach items="${keywords }" var="k">
											<span>${k }</span>
										</c:forEach>
									</div>
									<sf:input path="keyword" class="form-control" />
								</div>
							</div>
						</div>
						<div class="form-group form-md-line-input has-info">
							<label class="col-md-2 control-label" for="form_control_1">新闻图片说明</label>
							<div class="col-md-6">
								<sf:textarea path="explain" class="form-control" rows="2" cols="110"/>
								<div class="form-control-focus"></div>
							</div>
						</div>
						<div class="form-group form-md-line-input has-info">
							<label class="col-md-2 control-label" for="form_control_1">图片上传</label>
							<div class="col-md-6">
								<input type="file" id="attach" name="attach" class="form-control" />
								<a class="btn btn-info" id="uploadFile" >上传</a>
								<span style="color: red">${error }</span>
							</div>
						</div>
						<div class="form-group form-md-line-input has-info">
							<div class="col-md-12">
								<div class="note note-info">
									<p>已传图片<span class="badge badge-success" style="float: right;">3 </span></p>
								</div>
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
										<c:forEach items="${pics }" var="pic">
										<tr>
											<td>
												<img src="<%=request.getContextPath()%>/resources/picTopic/thumbnail/${pic.picName}"/>
												<input type="hidden" name="oldAks" value="${pic.id }">
											</td>
											<td>${pic.picNameOld }</td>
											<td>${pic.size/1024}K</td>
											<td>
												<input type="radio" value="${pic.id }" name='pictureId' 
													<c:if test="${pic.id eq pictureTopicDto.pictureId}"> checked="checked"</c:if>
												>
											</td>
											<td>
												&nbsp;<a abc="${pic.id }" class='btn btn-xs btn-danger deleteAttach delete'>删除附件</a>
											</td>
										</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="form-actions">
						<div class="row">
							<div class="col-md-offset-2 col-md-10">
								<button type="submit" class="btn green-meadow">保存</button>
								<a type="button" class="btn default ajaxify" href="admin/picTopic/audits">取消</a>
							</div>
						</div>
					</div>
				</sf:form>
			</div>
		</div>
		<!-- END SAMPLE FORM PORTLET-->
	</div>
</div>