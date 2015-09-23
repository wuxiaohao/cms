<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/admin/main.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/zTree/zTreeStyle.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/tree/jquery.ztree.core-3.5.min.js"></script>
<!-- 导入ztree可选框的js包 -->
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/tree/jquery.ztree.excheck-3.5.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/core/jquery.cms.core.js"></script>
 <!-- 必须引入dwr的engine.js -->
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<!-- 将java的类引入 -->
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/dwrService.js"></script>
<script type="text/javascript">
	$(function(){
		var t = $("#tree").mytree({
			url:$("#treePath").val(),
			mine:{listChild:0},
			callback:{
				onAsyncSuccess:initTree,
				beforeCheck:function(treeId,treeNode){ //点击之前
					if(!treeNode.checked){  //添加当前被选中节点和父节点（当节点被勾选前，treeNode.checked==false）
						var ps = getPs(treeNode,false);//获取被选中的节点的父节点
						ps.push(treeNode);//加入被选中的节点
						//alert(ps.length);
						//ps就是要添加的元素
						addGroupChannel(ps);
					} else {  //删除当前被选中节点和子节点
						var cs = new Array();
						getCs(treeNode,true,cs);//获取要删除的节点的子节点
						cs.push(treeNode); //加入被删除的节点
						//alert(cs.length);
						//cs就是要删除的元素
						deleteGroupChannel(cs);
					}
					
				},
				onCheck:function(event,treeId,treeNode){ //点击之后
					//如果取消之后父节点依然存在就不操作，父节点不存在就进行删除
					if(!treeNode.checked) { //删除当前被选中节点和父节点（当节点被取消勾选后，treeNode.checked==false）
						var ps = getPs(treeNode,false);
						//alert(ps.length);
						//ps就是删除的元素
						deleteGroupChannel(cs);
					}
				}
			},
			check:{
				enable:true,
				chkboxType: { "Y": "p", "N": "ps" }
			}
		});
		
		
		//获取被选中的节点的父节点
		function getPs(treeNode,checked) {
			var ps = new Array();
			var pn;
			while((pn=treeNode.getParentNode())) {
				//父节点是否被选中
				if(pn.checked == checked) {
					ps.push(pn);
				}
				treeNode = pn;
			}
			return ps;
		}
		
		//获取要删除的节点的子节点
		//cs参数用来存储所有子节点
		function getCs(treeNode,checked,cs) {
			var ccs;
			if((ccs = treeNode.children)) {
				for(var i = 0; i<ccs.length; i++){
					var c= ccs[i];
					if(c.checked == checked){
						cs.push(c);
					}
					getCs(c,checked,cs);//递归
				}
			}
		}
		
		//初始化树
		function initTree(){
			t.expandAll(true);//打开树节点
			//var n = t.getNodeByParam("id",7,null)
			//t.checkNode(n,true,true);
			var cids = $("input[name='cids']");
			for(var i=0;i<cids.length;i++){
				cid = cids[i].value;
				var n = t.getNodeByParam("id",cid,null);
				t.checkNode(n,true,true);
			}
		}
		
		function addGroupChannel(cs) {
			var gid = $("#gid").val();
			for(var i = 0; i < cs.length; i++) {
				var c = cs[i];
				if(c.id > 0){
					dwrService.addGroupChannel(gid,c.id);
				}
			}
		}
		
		function deleteGroupChannel(cs) {
			var gid = $("#gid").val();
			for(var i = 0; i < cs.length; i++) {
				var c = cs[i];
				if(c.id > 0){
					dwrService.deleteGroupChannel(gid,c.id);
				}
			}
		}
		
		//全局异常
		function handler(msg,exc){
			alert(msg);
		}
		dwr.engine.setErrorHandler(handler);
		
	});
</script>
</head>
<body>
<div id="content">
	<h3 class="admin_link_bar">
		<jsp:include page="inc.jsp"></jsp:include>
	</h3>
	<div>
		<c:forEach items="${cids }" var="cid">
			<input type="hidden" name="cids" value="${cid }">
		</c:forEach>
		<input type="hidden" id="gid" value="${group.id }"/>
		<!-- 获取整颗树的路径 -->
		<input type="hidden" id="treePath" value="<%=request.getContextPath()%>/admin/channel/treeAll"/>
		<div style="padding-left:10px;font-size:12px;">当前组名称:${group.name }</div>
		<div id="tree" class="ztree"></div>
	</div>
</div>
</body>
</html>