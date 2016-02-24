$(function(){
	var linkPicWidth=0,linkPicHeight=0,imgWidth=0,imgHeight=0;
	var x,y,w,h;
	var newName,oldName;
	var ctx = $("#ctx").val();
	$("#addForm").cmsvalidate();
	$("#urlType").change(function(){
		var v = $(this).val();
		if(v=="-1") {
			$(".type").removeAttr("readonly");
			$(".type").select();
			$(".type").focus();
		} else if(v!="0"){
			$(".type").val(v);
			$(".type").attr("readonly","true");
		}
	});
	//multi:false表示只上传一个文件，auto默认为true，自动上传
	$("#linkPic").uploadify({
		swf:ctx+"/resources/uploadify/uploadify.swf",
		uploader:ctx+"/admin/cmsLink/uploadPicLink",
		buttonText: '请选择图片',
		fileObjName:"pic",
		multi:false,
		removeTimeout:0.5,
		formData:{"sid":$("#sid").val()},
		fileTypeExts:"*.jpg;*.png;",
		onUploadSuccess:function(file, data, response) {
			$("#" + file.id).find(".data").html("上传成功");
			var ao = $.parseJSON(data);//把对象转为json数据
			if(ao.result) {
				newName = ao.obj.newName;
				oldName = ao.obj,newName;
				$("#linkImg").append("<img src='"+ctx+"/resources/linkPic/temp/"+newName+"'/>" +
								"<br/><input class='btn btn-sm btn-info' type='button' value='确定选择' id='confirmSelect'/>");
				linkPicWidth = ao.obj.linkPicWidth; //头像宽度
				linkPicHeight = ao.obj.linkPicHeight; //头像高度
				imgWidth = ao.obj.imgWidth; //上传的临时图片宽度
				imgHeight = ao.obj.imgHeight; //上传的临时图片高度
				$("#linkImg").before("<div id='pc' style='border:3px solid #AAAAAA;width:"+linkPicWidth+"px;height:"+linkPicHeight+"px;overflow:hidden;'><img id='preview' src='"+ctx+"/resources/linkPic/temp/"+newName+"'/></div><br/>");
				$("#hei").show();
				$("#linkImg img").Jcrop({
					aspectRatio:222/60,
					onChange: showPreview,
					onSelect: showPreview,
					setSelect: [0,0,linkPicWidth,linkPicHeight],
					dragEdges:true
				});
				$("#confirmSelect").click(confirmSelect);
			} else {
				alert(ao.msg);
			}
		}
	});
	function showPreview(coords)
	{
		if (parseInt(coords.w) > 0)
		{
			var rx = linkPicWidth / coords.w;
			var ry = linkPicHeight / coords.h;
			x = coords.x;
			y = coords.y;
			h = coords.h;
			w = coords.w;
			jQuery('#preview').css({
				width: Math.round(rx * imgWidth) + 'px',
				height: Math.round(ry * imgHeight) + 'px',
				marginLeft: '-' + Math.round(rx * coords.x) + 'px',
				marginTop: '-' + Math.round(ry * coords.y) + 'px'
			});
		}
	}
	/**
	 * 上传剪切后的图片的坐标和名称到后台处理
	 */
	function confirmSelect() {
		//var tn = newName.replace("\.","#");
		//alert(tn);
		var path = ctx+"/admin/cmsLink/confirmPic";
		$.post(path,{w:w,h:h,x:x,y:y,newName:newName},function(data) {
			if($.ajaxCheck(data)) {
				$("#pc").remove();  //移除截图预览框框
				$("#hei").remove();//移除截图框框
				$("#linkPic").hide(); //隐藏上传的按钮
				$("#youqingImg img").remove();//删除原来显示的头像
				$("#youqingImg").html("<img style='border:3px solid #AAAAAA'; src='"+ctx+"/resources/linkPic/"+newName+"'/>");
				$("#picName").val(newName);  //赋值图片名称
			}
		},"json")
	}
});