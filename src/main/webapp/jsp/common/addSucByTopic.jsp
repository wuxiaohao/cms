<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>添加记录成功</title>
<script type="text/javascript">
	//获取当前访问路径
	var href = window.location.href;
	//截取字符串
	href = href.substring(href.indexOf("h"),href.lastIndexOf("/j"));
	var href = href+"/redirectIndexByTopic";
	//刷新父页面
	window.opener.location.href=href;
	function closewindow(){
		if(window.opener){
			//关闭当前窗口
			window.close();
		}
	}
	function clock(){
		i = i -1;
		if(document.getElementById("info")){
			document.getElementById("info").innerHTML = "本窗口将在"+i+"秒后自动关闭";
		}
		if(i > 0)
			setTimeout("clock();",1000);
		else
			closewindow();
	}
	
	var i = 4;
	clock();

</script>
</head>
<body>
<input type="hidden" value="${mark }" id="mark"> 
<center>
	添加记录成功！<p>
	<div id="info">本窗口将在3秒后自动关闭</div>
	<input type="button" value="关闭窗口" onclick="closewindow();">
</center>
</body>
</html>