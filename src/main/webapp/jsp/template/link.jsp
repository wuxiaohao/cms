<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="<%=request.getContextPath() %>/resources/qianduan/css/news.css" rel="stylesheet" type="text/css" />
<SCRIPT src="<%=request.getContextPath() %>/resources/qianduan/js/ScrollPic.js" type="text/javascript"></SCRIPT>
</head>

<body class="transparent">
<div class="blk_29">
  <div class="LeftBotton" id="LeftArr"></div>
  <div class="Cont" id="ISL_Cont_1">
    <div class="box">
    	<div class="imgstyle">
	    		<a target="_blank" href="http://jxx.ccdgut.edu.cn/" title="计信系官网">
	    			<img border="0" src="<%=request.getContextPath()%>/resources/linkPic/1456415626407.jpg">
	    		</a>
    	</div>
    </div>
    <div class="box">
    	<div class="imgstyle">
	    		<a target="_blank" href="http://csxy.dgut.edu.cn/" title="城市学院">
	    			<img border="0" src="<%=request.getContextPath()%>/resources/linkPic/1456415617344.jpg">
	    		</a>
    	</div>
    </div>
    <div class="box">
    	<div class="imgstyle">
	    		<a target="_blank" href="http://www.oschina.net/" title="开源中国">
	    			<img border="0" src="<%=request.getContextPath()%>/resources/linkPic/1456415592887.jpg">
	    		</a>
    	</div>
    </div>
    <div class="box">
    	<div class="imgstyle">
	    		<a target="_blank" href="https://www.ppmoney.com/?utm_source=bdpz&utm_medium=brand&utm_term=PPmoney&utm_content=title&utm_campaign=title" title="PPmoney">
	    			<img border="0" src="<%=request.getContextPath()%>/resources/linkPic/1456415646319.jpg">
	    		</a>
    	</div>
    </div>
    <div class="box">
    	<div class="imgstyle">
	    		<a target="_blank" href="http://ice.ccdgut.edu.cn/" title="国际交流">
	    			<img border="0" src="<%=request.getContextPath()%>/resources/linkPic/1463228740528.jpg">
	    		</a>
    	</div>
    </div>
    <div class="box">
    	<div class="imgstyle">
	    		<a target="_blank" href="http://map.ccdgut.edu.cn/" title="虚拟校园">
	    			<img border="0" src="<%=request.getContextPath()%>/resources/linkPic/1456415566524.jpg">
	    		</a>
    	</div>
    </div>
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