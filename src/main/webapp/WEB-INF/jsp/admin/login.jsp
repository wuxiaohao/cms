<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/jsp/commonLogin.jsp"%>
<script type="text/javascript">
	$(function(){
		$("#myForm").cmsvalidate();
	});
	
	function reCheckcode(img) {
		img.src="drawCheckCode?"+Math.random();
	}
	
	if (window != top){
		top.location.href = location.href; 
	}
	
</script>
<title>后台管理登录</title>
</head>
<body class="login">
<!-- BEGIN LOGO -->
<div class="logo">
	<a href="index.html">
	<img src="${pageContext.servletContext.contextPath }/resources/assets/admin/layout/img/logo-big.png" alt=""/>
	</a>
</div>
<!-- END LOGO -->
<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
<div class="menu-toggler sidebar-toggler">
</div>
<!-- END SIDEBAR TOGGLER BUTTON -->
<!-- BEGIN LOGIN -->
<div class="content">
	<!-- BEGIN LOGIN FORM -->
	<form class="login-form" id="myForm" action="<%=request.getContextPath()%>/login" method="post">
		<h3 class="form-title">请输入你的账号</h3>
		<div style="color: red">${error }</div>
		<div class="form-group">
			<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
			<label class="control-label visible-ie8 visible-ie9">用户名</label>
			<div class="input-icon">
				<i class="fa fa-user"></i>
				<input class="form-control placeholder-no-fix" type="text"
					 autocomplete="off" placeholder="用户名" name="username" <c:if test="${cms_cookie_username!=''}">value="${cms_cookie_username}"</c:if>/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label visible-ie8 visible-ie9">密码</label>
			<div class="input-icon">
				<i class="fa fa-lock"></i>
				<input class="form-control placeholder-no-fix" type="password" autocomplete="off" placeholder="密码" name="password"/>
			</div>
		</div>
		<div class="form-group">
			<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
			<label class="control-label visible-ie8 visible-ie9">验证码</label>
			<div class="input-icon">
				<input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="验证码" name="checkcode" id="validateCode"/>			
			</div>
			<div style="color: red">${errorCode }</div>
		</div>
		<div class="form-actions">
			<label class="checkbox">
			<input type="checkbox" value="1" onclick="javascript:document.getElementById('hid').value=this.checked;" /> 记住用户名 </label>
			<input type="hidden" id="hid" name="remember" value="false"/>
			<button type="submit" class="btn green-haze pull-right">
			登录 <i class="m-icon-swapright m-icon-white"></i>
			</button>
		</div>
		<div class="login-options">
			<h4>验证码</h4>
			<div class="control-group" style="text-align:center;cursor:pointer">
				<img src="drawCheckCode" onclick="reCheckcode(this)"/></span>
			</div>
		</div>
		<div class="forget-password">
			<h4>忘记你的密码?</h4>
			<p>
				 别担心, 请点击 <a href="javascript:;" id="forget-password">
				这里</a>
				申请重置你的密码.
			</p>
		</div>
	</form>
	<!-- END LOGIN FORM -->
	<!-- BEGIN FORGOT PASSWORD FORM -->
	<form class="forget-form" action="index.html" method="post">
		<h3>Forget Password ?</h3>
		<p>
			 Enter your e-mail address below to reset your password.
		</p>
		<div class="form-group">
			<div class="input-icon">
				<i class="fa fa-envelope"></i>
				<input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="Email" name="email"/>
			</div>
		</div>
		<div class="form-actions">
			<button type="button" id="back-btn" class="btn">
			<i class="m-icon-swapleft"></i> Back </button>
			<button type="submit" class="btn green-haze pull-right">
			Submit <i class="m-icon-swapright m-icon-white"></i>
			</button>
		</div>
	</form>
	<!-- END FORGOT PASSWORD FORM -->
</div>
<!-- END LOGIN -->
<!-- BEGIN COPYRIGHT -->
<div class="copyright">
	 2015 &copy; 城市学院. 吴晓豪 制作.
</div>
<script>
</script>
</body>
</html>