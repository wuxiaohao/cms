<body class="transparent">
<!--滚动图片 start-->
<div class="blk_29">
  <div class="LeftBotton" id="LeftArr"></div>
  <div class="Cont" id="ISL_Cont_1">
    <!-- 图片列表 begin -->
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

    <!-- 图片列表 begin -->
  </div>
  <div class="RightBotton" id="RightArr"></div>
</div>