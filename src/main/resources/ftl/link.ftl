<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="<%=request.getContextPath() %>/resources/qianduan/css/news.css" rel="stylesheet" type="text/css" />
<SCRIPT src="<%=request.getContextPath() %>/resources/qianduan/js/ScrollPic.js" type="text/javascript"></SCRIPT>
</head>

<body class="transparent">
<#--滚动图片 start-->
<div class="blk_29">
  <div class="LeftBotton" id="LeftArr"></div>
  <div class="Cont" id="ISL_Cont_1">
    <#-- 图片列表 begin -->
    <#list pics as pic>
    <div class="box">
    	<div class="imgstyle">
    		<#if pic.newWin==1>
	    		<a target="_blank" href="${pic.url}">
	    			<img border="0" src="<%=request.getContextPath()%>/resources/LinkPic/${pic.picName}">
	    		</a>
    		<#else>
	    		<a href="${pic.url}">
	    			<img border="0" src="<%=request.getContextPath()%>/resources/LinkPic/${pic.picName}">
	    		</a>
    		</#if>
    	</div>
    </div>
    </#list>
    <#-- 图片列表 end -->
  </div>
  <div class="RightBotton" id="RightArr"></div>
</div>
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
</body>
</html>