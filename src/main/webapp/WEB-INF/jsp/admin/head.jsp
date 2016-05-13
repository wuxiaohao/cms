<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<!-- BEGIN HEADER -->
<script type="text/javascript">
	$(document).ready(function() {
		var initializationTime=new Date();
		showLeftTime();
	});
	function showLeftTime()
	{
	var now=new Date();
	var year=now.getFullYear();
	var month=now.getMonth()+1;
	var day=now.getDate();
	var hours=now.getHours();
	var minutes=now.getMinutes();
	var seconds=now.getSeconds();
	var days=now.getDay();
	var daysStr = "";
	switch(days) {
	case 1:	daysStr = "一"; break;
	case 2:	daysStr = "二"; break;
	case 3:	daysStr = "三"; break;
	case 4: daysStr = "四"; break;
	case 5: daysStr = "五"; break;
	case 6: daysStr = "六"; break;
	case 0: daysStr = "日"; break;
	}
	var hoursStr = "";
	if(hours < 10) {
		 hoursStr = "0" + hours;
	}
	if(minutes < 10) {
		minutes = "0" + minutes;
	}
	if(seconds < 10) {
		seconds = "0" + seconds;
	}
	document.getElementById('time').innerHTML="<font color='#438EB9'>"+year+"年"+month+"月"+day+"日"+"<b>星期"+daysStr+"</b>"+hours+":"+minutes+":"+seconds+"</font>";
	//一秒刷新一次显示时间
	var timeID=setTimeout(showLeftTime,1000);
	}
	
</script>
<div class="page-header navbar navbar-fixed-top">
	<!-- BEGIN HEADER INNER -->
	<div class="page-header-inner">
		<!-- BEGIN LOGO -->
		<div class="page-logo">
			<a href="main/index.oa">
			<img src="${pageContext.servletContext.contextPath }/resources/assets/admin/layout/img/aaa.jpg" alt="logo" class="logo-default"/>
			</a>
			<div class="menu-toggler sidebar-toggler hide">
				<!-- DOC: Remove the above "hide" to enable the sidebar toggler button on header -->
			</div>
		</div>
		<!-- END LOGO -->
		<!-- BEGIN RESPONSIVE MENU TOGGLER -->
		<a href="javascript:;" class="menu-toggler responsive-toggler" data-toggle="collapse" data-target=".navbar-collapse">
		</a>
		<!-- END RESPONSIVE MENU TOGGLER -->
		<!-- BEGIN TOP NAVIGATION MENU -->
		<div class="top-menu">
			<p class="nav navbar-nav" id="time" style="margin-top: 13px;width: 190px"></p>
			<ul class="nav navbar-nav pull-right">
				<li class="dropdown dropdown-user">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
					<c:if test="${!empty(loginUser.icon)}">
						<img alt="这是头像啊" class="img-circle" src="<%=request.getContextPath() %>/resources/userIcon/thumbnail/${loginUser.icon}"/>
					</c:if>
					<c:if test="${empty(loginUser.icon) }">
						<img alt="这是头像啊" class="img-circle" src="<%=request.getContextPath() %>/resources/userIcon/thumbnail/zanwu.png"/>
					</c:if>
					<%-- <img alt="" class="img-circle" src="${pageContext.servletContext.contextPath }/resources/assets/admin/layout/img/avatar3_small.jpg"/> --%>
					<span class="username username-hide-on-mobile">
					欢迎您，${loginUser.nickname }
					</span>
					<i class="fa fa-angle-down"></i>
					</a>
					<ul class="dropdown-menu dropdown-menu-default">
						<li>
							<a href="javascript:exitSystem()">
							<i class="icon-key"></i> 退出系统 </a>
						</li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
</div>
<!-- END HEADER -->