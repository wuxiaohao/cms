<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>欢迎访问${baseInfo.name }</title>
<!-- 设置关键字，方便被搜索引擎搜索到 -->
<meta http-equiv="keywords" content="城市学院,东莞理工学院城市学院">   
<meta http-equiv="description" content="城市学院网站,东莞理工学院城市学院网站">     
<meta charset="UTF-8">
<!-- 移动端访问 -->
<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
<title>城市学院</title>	
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/qianduan/css/bootstrap-responsive.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/qianduan/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/qianduan/css/docs.min.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/qianduan/css/main.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/qianduan/css/screen.css">
<script src="<%=request.getContextPath() %>/resources/qianduan/js/jquery.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/qianduan/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/qianduan/js/pic_hover.js"></script>
<link href="<%=request.getContextPath() %>/resources/qianduan/css/news.css" rel="stylesheet" type="text/css" />
<SCRIPT src="<%=request.getContextPath() %>/resources/qianduan/js/ScrollPic.js" type="text/javascript"></SCRIPT>
<script type="text/javascript">
$("#search_btn").click(function(){
	var sc = $("#search_con").val();
	if(sc==""||sc=="请输入关键字") {
		alert("你需要输入相应的检索内容");
	} else {
		window.location.href=$("#ctx").val()+"/search/"+sc;
	}
});
</script>
<SCRIPT language="javascript" type="text/javascript">
		<!--//--><![CDATA[//><!--
		var scrollPic_02 = new ScrollPic();
		scrollPic_02.scrollContId   = "ISL_Cont_1"; //内容容器ID
		scrollPic_02.arrLeftId      = "LeftArr";//左箭头ID
		scrollPic_02.arrRightId     = "RightArr"; //右箭头ID

		scrollPic_02.frameWidth     = 1200;//显示框宽度
		scrollPic_02.pageWidth      = 244; //翻页宽度

		scrollPic_02.speed          = 2; //移动速度(单位毫秒，越小越快)
		scrollPic_02.space          = 16; //每次移动像素(单位px，越大越快)
		scrollPic_02.autoPlay       = true; //自动播放
		scrollPic_02.autoPlayTime   = 5; //自动播放间隔时间(秒)

		scrollPic_02.initialize(); //初始化
							
		//--><!]]>
</SCRIPT>
</head>
<body>
<jsp:include page="/jsp/template/top.jsp"/>	
<jsp:include page="/jsp/template/body.jsp"/>
<jsp:include page="/jsp/template/bottom.jsp"/>
</body>
</html>