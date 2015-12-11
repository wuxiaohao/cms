$(function(){
	//定义ztree
	var t = $("#mytree").mytree({mine:{listChild:0},
		callback:{
			onAsyncSuccess:function(){
				t.expandAll(true);
			},
			beforeClick:beforeChoice,
			onClick:choice
		},
		url:$("#ctx").val()+"/admin/topic/treeAll"
	});
	
	$("#cname").click(showMenu);
	
	//日期插件
	$( "#publishDate" ).datepicker({
		dateFormat:"yy-mm-dd",//设置日期的格式
		maxDate:0,//最大日期为1年后（2013-2-2具体日期）
		changeMonth:true,//可以选择一个月份
		changeYear:true//可以选择一个年份
	});
	
	//文章内容的插件
	var editor = $('#content').xheditor({tools:'full'});
	//表单校验
	var validate = $("#addForm").cmsvalidate();
	$("#addBtn").click(function(){
		if(validate.valid()) {
			$("#addForm").submit();
			$(this).attr("disabled");
		}
	});
	
	//引用关键字的自定义插件jquery.cms.keywordinput.js
	$("#keyword").keywordinput({
		autocomplete:{
			enable:true,
			url:$("#ctx").val()+"/admin/topic/searchKeyword",
			minLength:2
		}
	});
	
	//文件上传uploadify
	$("#attach").uploadify({
		//指定swf文件
		swf:$("#ctx").val()+"/resources/uploadify/uploadify.swf",
		//后台处理的页面
		uploader:$("#ctx").val()+"/admin/topic/upload",
		//按钮显示的文字
		buttonText: '选择文件',
		//文件上传对象的名称
		fileObjName:"attach",
		//是否自动上传
		auto:false,
		formData:{"sid":$("#sid").val()},
		//允许上传的文件后缀，限制弹出文件选择框里能选择的文件
		fileTypeExts:"*.jpg;*.gif;*.png;*.doc;*.docx;*.txt;*.xls;*.xlsx;*.rar;*.zip;*.pdf;*.flv;*.swf",
		//允许上传的文件类型的描述，在弹出的文件选择框里会显示
		fileTypeDesc: '支持上传的文件类型',
		//上传成功后执行
		onUploadSuccess:function(file, data, response) {
			var ao = $.parseJSON(data);
			var suc = $.ajaxCheck(ao);  //判断操作是否成功
			if(suc) {
				var node = createAttachNode(ao.obj);
				$("#ok_attach").find("tbody").append(node);
				$('#' + file.id).find('.data').html(' 上传完毕');
			}
		}
	});
	var uploadPath = $(ctx).val()+"/resources/upload/";
	function createAttachNode(attach) {
		var node = "<tr>";
		if(attach.isImg) {
			node+="<td><img src='"+uploadPath+"thumbnail/"+attach.newName+"'/></td>";
		} else {
			node+="<td>普通类型附件</td>";
		}
		node+="<td>"+attach.oldName+"</td>";
		node+="<td>"+Math.round(attach.size/1024)+"K</td>";
		if(attach.isImg) {
			node+="<td><input type='checkbox' value='"+attach.id+"' name='indexPic' class='indexPic'></td>";
			node+="<td><input type='radio' value='"+attach.id+"' name='channelPicId'></td>";
		} else {
			node+="<td>&nbsp;</td><td>&nbsp;</td>";
		}
		node+="<td><input type='checkbox' value='"+attach.id+"' name='isAttach' class='isAttach'>" +
				"<input type='hidden' name='aids' value='"+attach.id+"'/></td>";
		node+="<td><a href='#' class='btn btn-xs btn-info insertAttach' title='"+attach.id+"' isImg='"+attach.isImg+"' name='"+attach.newName+"' oldName='"+attach.oldName+"'>插入附件</a>" +
				"&nbsp;<a href='#' title='"+attach.id+"' class='btn btn-xs btn-danger deleteAttach'>删除附件</a></td>";
		node+="</tr>";
		return node;
	}
	
	//点击主页图片的事件委派对象
	$("#ok_attach").on("click",".indexPic",function(){
		//alert($(this).val());
		dwrService.updateIndexPic($(this).val()); //更新是否是主页信息
	});
	//点击附件信息的事件委派对象
	$("#ok_attach").on("click",".isAttach",function(){
		dwrService.updateAttachInfo($(this).val()); //更新是否是附件信息
	});
	//点击删除附件的事件委派对象
	$("#ok_attach").on("click",".deleteAttach",function(){
		if (confirm("确认要删除？")) {
			var ad = this;
			var id = $(this).attr("title");
			dwrService.deleteAttach(id,function(data) {
				$(ad).parent("td").parent("tr").remove();
				//alert($("#xhe0_iframe").contents().find("#attach_"+id).html());
				$("#xhe0_iframe").contents().find("#attach_"+id).remove();
			});	
		}
	});
	//点击插入附件，插入到文章内容中
	$("#ok_attach").on("click",".insertAttach",function(){
		var node = "";
		var isImg = $(this).attr("isimg");
		if(isImg==1) {
			node = "<img height='400' src='"+uploadPath+$(this).attr("name")+"' id='attach_"+$(this).attr("title")+"'/>"
		} else {
			node = "<a href='"+uploadPath+$(this).attr("name")+"' id='attach_"+$(this).attr("title")+"'>"+$(this).attr("oldName")+"</a>"
		}
		editor.pasteHTML(node);
	});
	
	$("#uploadFile").click(function() {
		$("#attach").uploadify("upload","*");
	})
})
function choice(event,treeId,treeNode) {
	$("#cname").val(treeNode.name);
	$("#cid").val(treeNode.id);
	hideMenu();
}
function beforeChoice(treeId,treeNode) {
	var check = (treeNode && !treeNode.isParent);
	if (!check) return check;
}
function showMenu() {
	$("#mytree").width($(this).width()-9);
	var cObj = $("#cname");
	var cOffset = $("#cname").offset();
	$("#menuContent").css({left:cOffset.left + "px", top:cOffset.top + cObj.outerHeight() + "px"}).slideDown("fast");

	$("body").bind("mousedown", onBodyDown);
}
function onBodyDown(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
		hideMenu();
	}
}
function hideMenu() {
	$("#menuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
