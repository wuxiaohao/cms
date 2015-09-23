$(function(){
	/**
	 * 显示树
	 */
	$("#showTree").click(function(){
		var selectedIds = $('input[title=check]:checked'); //获取被选的checkbox
		if(selectedIds.length != 1){
			toastr.error("请选中其中一行!");
			return false;
		}
		
		var t = $("#tree").mytree({
			url:$("#treePath").val()+selectedIds.val(),
			mine:{listChild:0,expandAll:true}
		});			
		$("#addmodel").modal();
		
	});
});