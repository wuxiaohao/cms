<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
	var mt;
	$(function(){
		$("a.delete").confirmOperator();
		var success='<%=request.getAttribute("success")%>';
		var error='<%=request.getAttribute("error")%>';
		showMessage(success,error);
		mt = $("#tree").mytree({
			url:$("#setTreePath").val()
		});
	});
	function refreshTree() {
		mt.reAsyncChildNodes(null,"refresh");  //重新加载全部子节点
	}	
</script>
<input type="hidden" id="setTreePath" value="<%=request.getContextPath()%>/admin/channel/treeAll"/>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			栏目信息管理
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li>
				<i class="fa fa-home"></i>
				<a class="ajaxify start" href="layout_ajax_content_1.html">首页</a>
				>>
			</li>
			<li>
				<a>文章管理</a>
				>>
			</li>
			<li>
				<a>栏目信息管理</a>
			</li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>

<TABLE border=0 align=left>
	<TR>
		<TD width=190px align=left valign=top style="BORDER-RIGHT: #999999 1px dashed">
			<ul id="tree" class="ztree" style="width:190px; overflow:auto;"></ul>
		</TD>
		<TD width=100% align=left valign=top><IFRAME ID="cc" Name="testIframe" FRAMEBORDER=0 SCROLLING="auto" width=90%  height=390px style="margin-left: 30px" ></IFRAME></TD>
	</TR>
</TABLE>