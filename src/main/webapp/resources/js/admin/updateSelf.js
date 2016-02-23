$(function(){
	$("#addForm").cmsvalidate();
	var indexPicWidth=0,indexPicHeight=0,imgWidth=0,imgHeight=0;
	var x,y,w,h;
	var newName,oldName;
	var ctx = $("#ctx").val();
	//multi:false表示只上传一个文件，auto默认为true，自动上传
	$("#ico").uploadify({
		swf:ctx+"/resources/uploadify/uploadify.swf",
		uploader:ctx+"/admin/user/uploadIcon",
		buttonText: '请选择图片',
		fileObjName:"ico",
		multi:false,
		formData:{"sid":$("#sid").val()},
		fileTypeExts:"*.jpg;*.png;",
		onUploadSuccess:function(file, data, response) {
			var ao = $.parseJSON(data);//把对象转为json数据
			if(ao.result) {
				newName = ao.obj.newName;
				oldName = ao.obj.oldName;
				$("#headImg").append("<img src='"+ctx+"/resources/userIcon/temp/"+newName+"'/>" +
						"<br/><input class='btn btn-sm btn-info' type='button' value='确定选择' id='confirmSelect'/>");
				iconWidth = ao.obj.iconWidth; //头像宽度
				iconHeight = ao.obj.iconHeight; //头像高度
				imgWidth = ao.obj.imgWidth; //上传的临时图片宽度
				imgHeight = ao.obj.imgHeight; //上传的临时图片高度
				$("#headImg").parent().after("<div class='col-md-2' id='pc'><div style='border:2px solid #AAAAAA;width:"+iconWidth+"px;height:"+iconHeight+"px;overflow:hidden;'><img id='preview' src='"+ctx+"/resources/userIcon/temp/"+newName+"'/></div></div>");
				$("#headImg img").Jcrop({
					aspectRatio:1,
					onChange: showPreview,
					onSelect: showPreview,
					setSelect: [0,0,iconWidth,iconHeight],
					dragEdges:true
				});$("#confirmSelect").click(confirmSelect);
			} else {
				alert(ao.msg);
			}
		}
	});
	
	function showPreview(coords)
	{
		if (parseInt(coords.w) > 0)
		{
			var rx = iconWidth / coords.w;
			var ry = iconHeight / coords.h;
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
		var path = ctx+"/admin/user/confirmPic";
		$.post(path,{w:w,h:h,x:x,y:y,newName:newName},function(data) {
			if($.ajaxCheck(data)) {
				$("#pc").remove();
				$("#hei").remove();
				$("#touxiangImg").html("<img src='"+ctx+"/resources/userIcon/"+newName+"'/>");
				$("#ico").hide();
				$("#icon").val(newName);
			}
		},"json")
	}
});