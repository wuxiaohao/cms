$(function(){
	$("#setTree").click(function(){
		var selectedIds = $('input[title=check]:checked'); //获取被选的checkbox
		if(selectedIds.length != 1){
			toastr.error("请选中其中一行!");
			return false;
		}
		
		$.ajax({
			type : "POST",
			url : $("#getChannelIdPath").val()+selectedIds.val(),
			success : function(data) {
				$(".cids").text("");// 清空数据
				var _data = jQuery.parseJSON(jQuery.parseJSON);
				var panel ="";
				$.each(_data,function(id, item){
					panel += "<input type='hidden' name='cids' value="+item+">";
				});
			}
		});
		
		/**
		 * 设置树
		 */
		var _cs = new Array();
		var t = $("#tree").mytree({
			url:$("#setTreePath").val(),
			mine:{listChild:0},
			callback:{
				onAsyncSuccess:initTree,
				beforeCheck:function(treeId,treeNode) { //点击之前
					if(!treeNode.checked) { //添加当前被选中节点和父节点（当节点被勾选前，treeNode.checked==false）
						//ps中的节点应该进行添加操作
						var ps = t.getCheckParentNodes(treeNode,false);//获取被选中的节点的父节点
						ps.push(treeNode);//加入被选中的节点
						//ps就是要添加的元素
						addGroupChannel(ps);
					} else {	//删除当前被选中节点和子节点
						var cs = new Array();
						t.getCheckChildNodes(treeNode,true,cs);//获取要删除的节点的子节点
						cs.push(treeNode);//加入被删除的节点
						//cs就是要删除的元素
						deleteGroupChannel(cs);
					}
				},
				onCheck:function(event,treeId,treeNode) {//点击之后
					//如果取消之后父节点依然存在就不操作，父节点不存在就进行删除
					if(!treeNode.checked) {
						var ps = t.getCheckParentNodes(treeNode,false);//删除当前被选中节点和父节点（当节点被取消勾选后，treeNode.checked==false）
						//ps就表示要删除的元素
						deleteGroupChannel(ps);
					}
				}
			
			},
			check:{
				enable:true,
				chkboxType: { "Y": "p", "N": "ps" }
			}
		});
		
		$("#addmodel").modal();
	});
	
	function handler_(msg,exc) {
		alert(msg);
	}
	
	dwr.engine.setErrorHandler(handler_);
	
	function addGroupChannel(cs) {
		var gid = selectedIds.val();
		for(var i=0;i<cs.length;i++) {
			var c = cs[i];
			if(c.id>0) {
				dwrService.addGroupChannel(gid,c.id);
			}
		}
		
	}
	
	function deleteGroupChannel(cs) {
		var gid = selectedIds.val();
		for(var i=0;i<cs.length;i++) {
			var c = cs[i];
			if(c.id>0) {
				dwrService.deleteGroupChannel(gid,c.id);
			}
		}
		
	}

	function initTree() {
		t.expandAll(true);
		var cids = $("input[name='cids']");
		for(var i=0;i<cids.length;i++) {
			var cid = cids[i].value;
			var n = t.getNodeByParam("id",cid,null);
			t.checkNode(n,true,true);
		}
	}
});