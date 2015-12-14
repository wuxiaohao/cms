<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- 获取总资源 -->
<%@ include file="/jsp/common.jsp"%>
<script type="text/javascript">
	$(function(){
		$("#addForm").cmsvalidate();
	});
</script>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN SAMPLE FORM PORTLET-->
		<div class="portlet light bordered">
			<div class="portlet-title">
				<div class="caption font-blue">
					<i class="icon-pin font-blue"></i>
					<span class="caption-subject bold uppercase"> 添加[${pc.name}]子栏目</span>
				</div>
			</div>
			<div class="portlet-body form">
				<sf:form role="form" id="addForm" method="post" modelAttribute="channel" class="form-horizontal">	
					<div class="form-body">
						<div class="form-group form-md-line-input">
							<label class="col-md-2 control-label" for="form_control_1">栏目名称</label>
							<div class="col-md-6">
								<sf:input path="name" class="form-control" />
								<sf:errors path="name" />
								<div class="form-control-focus"></div>
							</div>
						</div>
					</div>
					<div class="form-body">
						<div class="form-group form-md-line-input">
							<label class="col-md-2 control-label" for="form_control_1">链接地址</label>
							<div class="col-md-6">
								<sf:input path="customLinkUrl" class="form-control" />
								<div class="form-control-focus"></div>
							</div>
						</div>
					</div>
					<div class="form-group form-md-line-input">
						<label class="col-md-2 control-label" for="form_control_1">栏目类型</label>
						<div class="col-md-6">
							<sf:select path="type" class="bs-select form-control">
								<sf:options items="${types }"/>
							</sf:select>
							<div class="form-control-focus"></div>
						</div>
					</div>
					<div class="form-group form-md-line-input">
						<label class="col-md-2 control-label" for="form_control_1">是否指定链接</label>
						<div class="col-md-6">
							<div class="md-radio-inline">
								<div class="md-radio has-error">
									<input type="radio" id="radio53" name="customLink" value="0" class="md-radiobtn" checked>
									<label for="radio53">
									<span></span>
									<span class="check"></span>
									<span class="box"></span>
									不指定 </label>
								</div>
								<div class="md-radio has-error">
									<input type="radio" id="radio54" name="customLink" value="1" class="md-radiobtn">
									<label for="radio54">
									<span></span>
									<span class="check"></span>
									<span class="box"></span>
									指定 </label>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group form-md-line-input">
						<label class="col-md-2 control-label" for="form_control_1">是否在首页显示</label>
						<div class="col-md-6">
							<div class="md-radio-inline">
								<div class="md-radio has-info">
									<input type="radio" id="radio55" name="isIndex" value="0" class="md-radiobtn" checked>
									<label for="radio55">
									<span></span>
									<span class="check"></span>
									<span class="box"></span>
									不显示 </label>
								</div>
								<div class="md-radio has-info">
									<input type="radio" id="radio56" name="isIndex" value="1" class="md-radiobtn">
									<label for="radio56">
									<span></span>
									<span class="check"></span>
									<span class="box"></span>
									显示 </label>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group form-md-line-input">
						<label class="col-md-2 control-label" for="form_control_1">是否是推荐栏目</label>
						<div class="col-md-6">
							<div class="md-radio-inline">
								<div class="md-radio has-success">
									<input type="radio" id="radio59" name="recommend" value="0" class="md-radiobtn" checked>
									<label for="radio59">
									<span></span>
									<span class="check"></span>
									<span class="box"></span>
									不推荐 </label>
								</div>
								<div class="md-radio has-success">
									<input type="radio" id="radio60" name="recommend" value="1" class="md-radiobtn">
									<label for="radio60">
									<span></span>
									<span class="check"></span>
									<span class="box"></span>
									推荐 </label>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group form-md-line-input">
						<label class="col-md-2 control-label" for="form_control_1">顶部栏目</label>
						<div class="col-md-6">
							<div class="md-radio-inline">
								<div class="md-radio has-warning">
									<input type="radio" id="radio57" name="isTopNav" value="0" class="md-radiobtn" checked>
									<label for="radio57">
									<span></span>
									<span class="check"></span>
									<span class="box"></span>
									不是 </label>
								</div>&nbsp;&nbsp;&nbsp;&nbsp;
								<div class="md-radio has-warning">
									<input type="radio" id="radio58" name="isTopNav" value="1" class="md-radiobtn">
									<label for="radio58">
									<span></span>
									<span class="check"></span>
									<span class="box"></span>
									是 </label>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group form-md-line-input">
						<label class="col-md-2 control-label" for="form_control_1">状态</label>
						<div class="col-md-6">
							<div class="md-radio-inline">
								<div class="md-radio has-success">
									<input type="radio" id="radio61" name="status" value="0" class="md-radiobtn" checked>
									<label for="radio61">
									<span></span>
									<span class="check"></span>
									<span class="box"></span>
									启用 </label>
								</div>&nbsp;&nbsp;&nbsp;&nbsp;
								<div class="md-radio has-success">
									<input type="radio" id="radio62" name="status" value="1" class="md-radiobtn">
									<label for="radio62">
									<span></span>
									<span class="check"></span>
									<span class="box"></span>
									停用</label>
								</div>
							</div>
						</div>
					</div>
					<div class="form-actions">
						<div class="row">
							<div class="col-md-offset-2 col-md-10">
								<button type="submit" class="btn blue">添加</button>
							</div>
						</div>
					</div>
				</sf:form>
			</div>
		</div>
		<!-- END SAMPLE FORM PORTLET-->
	</div>
</div>
