$(function(){
	//更改文章状态（由于拼接html后，只能用事件委派方式促发事件）
	$("#data_table").on("click",".changeStatus",function(event){
		if (!confirm("该操作不可逆，是否确定操作？")) {
			event.preventDefault();
			return false;
		}
		var id = $(this).attr("abc");
		dwrService.changeStatus(id);  //后台更新数据
		$(this).parent("td").parent("tr").remove(); //删除该行的显示
	});
	//拼接字符串后的删除（由于拼接html后，只能用事件委派方式促发事件）
	$("#data_table").on("click",".deleteTr",function(event){
		if (!confirm("该操作不可逆，是否确定操作？")) {
			event.preventDefault();
			return false;
		}
		var id = $(this).attr("abc");
		dwrService.deleteTopic(id);//后台删除数据
		$(this).parent("td").parent("tr").remove(); //删除该行的显示
	});
	//检索文章，拼接字符串
	$("#search").click(function(){
		var _con = $.trim($("#con").val());
		var _cid = $("#cid").val();
		var _status = ${status};
		$.ajax({
			type : "post",
			url : "${pageContext.servletContext.contextPath }/admin/topic/queryTopic",
			data: {
				con:_con,
				cid:_cid,
				status:_status
				},
			error: function(request){
				alert("Connection error");
			},
			success : function(data){
				var ao = $.parseJSON(data);  //转换json对象
				var success = $.ajaxCheck(ao);  //判断操作是否成功
				if(success) {
					//构造显示页面
					var tbody = $("#data_table tbody").empty();
					var node = "";
					$.each(ao.obj,function(i,value){
						node += "<tr><td><a href=\"javascript:openWin('${pageContext.servletContext.contextPath }/admin/topic/"+value.id+"','showTopic')\">"+value.title+"</a></td><td>"+value.author+"</td>";						
						if(value.recommend == 0){
							node += "<td>不推荐</td><td>"+value.cname+"</td>";
						} else {
							node += "<td>推荐</td><td>"+value.cname+"</td>";
						}
						if(value.status == 0){
							node += "<td><span style=\"color: red\">未发布&nbsp;</span><a abc='"+value.id+"' class=\"btn btn-sm blue ajaxify changeStatus \">发布</a></td>";
						} else {
							node += "<td>已发布&nbsp;<a abc='"+value.id+"' class=\"btn btn-sm blue ajaxify changeStatus \">取消发布</a></td>";
						}
						node += "<td><a abc='"+value.id+"' class=\"btn btn-sm red deleteTr \">删除</a><a href=\"javascript:openWin('${pageContext.servletContext.contextPath }/admin/topic/update/"+value.id+"','updateTopic')\" class=\"btn btn-sm blue\">更新</a>&nbsp;</td>";
						node += "</tr>";
					});
					tbody.html(node);
					//是否显示分页
					if(_con == "" && _cid ==0) {
						$("#tfoot").show();
					} else {
						$("#tfoot").hide();
					}
				}
			}
		});
	});
});