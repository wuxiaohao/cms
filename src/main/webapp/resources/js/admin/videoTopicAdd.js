$(function(){
	//表单校验
	var validate = $("#addForm").cmsvalidate();
	$("#addBtn").click(function(){
		if(validate.valid()) {
			if($("#vname").val() == null || $("#vname").val() == ""){
				var error="请上传视频";
				showMessage("",error);
				return false;
			}
			$("#addForm").submit();
			$(this).attr("disabled");
		}
		
	});
	
	//视频上传uploadify
	$("#attach").uploadify({
		//指定swf文件
		swf:$("#ctx").val()+"/resources/uploadify/uploadify.swf",
		//后台处理的页面
		uploader:$("#ctx").val()+"/admin/video/upload",
		//文件队列上传完成1秒后删除
		removeTimeout : 1,
		//按钮显示的文字
		buttonText: '选择视频',
		//不允许同时上传多个视频
		multi: false,
		//文件上传对象的名称
		fileObjName:"attach",
		//是否自动上传
		auto:true,
		formData:{"sid":$("#sid").val()},
		//允许上传的文件后缀，限制弹出文件选择框里能选择的文件
		fileTypeExts:"*.avi;*.wmv;*.mpeg;*.mp4;*.mov;*.mkv;*.flv;*.f4v;*.m4v;*.rmvb;*.rm;*.3gp;*.dat;*.ts;*.mts;*.vob",
		//允许上传的文件类型的描述，在弹出的文件选择框里会显示
		fileTypeDesc: '支持上传的文件类型',
		//上传成功后执行
		onUploadSuccess:function(file, data, response) {
			var ao = $.parseJSON(data);
			var suc = $.ajaxCheck(ao);  //判断操作是否成功
			if(suc) {
				$('#' + file.id).find('.data').html(' 上传完毕');
				$("#vname").val(ao.obj.videoName);
				$("#vsize").val(ao.obj.size);
				$("#vpicName").val(ao.obj.picName)
				$(".vname").show(1000);
			}
		}
	});
	
	//缩略图上传uploadify
	$("#attachPic").uploadify({
		//指定swf文件
		swf:$("#ctx").val()+"/resources/uploadify/uploadify.swf",
		//后台处理的页面
		uploader:$("#ctx").val()+"/admin/video/uploadPic",
		//文件队列上传完成1秒后删除
		removeTimeout : 1,
		//按钮显示的文字
		buttonText: '选择图片',
		//不允许同时上传多张图片
		multi: false,
		//文件上传对象的名称
		fileObjName:"attachPic",
		//是否自动上传
		auto:true,
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
				$('#' + file.id).find('.data').html(' 上传完毕');
				$("#vpicName").val(ao.obj);
				$(".vpicName").show(1000);
			}
		}
	});
})
