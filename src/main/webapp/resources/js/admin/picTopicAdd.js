$(function(){
	//表单校验
	$("#addForm").cmsvalidate();
	/*var validate = $("#addForm").cmsvalidate();
	$("#addBtn").click(function(){
		if(validate.valid()) {
			$("#addForm").submit();
			$(this).attr("disabled");
		}
	});*/
	
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
		uploader:$("#ctx").val()+"/admin/picTopic/upload",
		//按钮显示的文字
		buttonText: '选择图片',
		//文件上传对象的名称
		fileObjName:"attach",
		//是否自动上传
		auto:false,
		formData:{"sid":$("#sid").val()},
		//允许上传的文件后缀，限制弹出文件选择框里能选择的文件
		fileTypeExts:"*.jpg;*.png",
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
	var uploadPath = $(ctx).val()+"/resources/picTopic/";
	function createAttachNode(attach) {
		var node = "<tr>";
		node+="<td><img src='"+uploadPath+"thumbnail/"+attach.picName+"'/>" +
				"<input type='hidden' name='pics' value='"+attach.id+"'/></td>";
		node+="<td>"+attach.picNameOld+"</td>";
		node+="<td>"+Math.round(attach.size/1024)+"K</td>";
		node+="<td><div class='md-radio-inline'><div class='md-radio has-success'><input type='radio' id='"+attach.id+"' value='"+attach.id+"' name='pictureId' class='md-radiobtn'><label for='"+attach.id+"'><span></span><span class='check'></span><span class='box'></span></label></div></div></td>";
		node+="<td><a abc='"+attach.id+"' class='btn btn-xs btn-danger deleteAttach'>删除附件</a></td>";
		node+="</tr>";
		return node;
	}
	
	//点击删除附件的事件委派对象
	$("#ok_attach").on("click",".deleteAttach",function(){
		if (confirm("确认要删除？")) {
			var ad = this;
			var id = $(this).attr("abc");
			dwrService.deletePicture(id,function(data) {
				$(ad).parent("td").parent("tr").remove();
			});	
		}
	});
	$("#uploadFile").click(function() {
		$("#attach").uploadify("upload","*");
	})
})
