<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<div class="page-sidebar-wrapper">	
		<div id="MyMenu" class="page-sidebar navbar-collapse collapse">
			<!-- BEGIN SIDEBAR MENU1 -->
			<ul class="page-sidebar-menu" data-slide-speed="200" data-auto-scroll="true" data-auto-scroll="true" data-slide-speed="200">
				<!-- DOC: To remove the search box from the sidebar you just need to completely remove the below "sidebar-search-wrapper" LI element -->
				<li class="sidebar-toggler-wrapper">
					<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
					<div class="sidebar-toggler">
					</div>
					<!-- END SIDEBAR TOGGLER BUTTON -->
				</li>
				<li class="start">
					<a class="ajaxify start" href="layout_ajax_content_1.html">
					<i class="icon-home"></i>
					<span class="title">首页</span>
					</a>
				</li>
				<li>
					<a href="javascript:;">
					<i class="icon-folder"></i>
					<span class="title">个人中心</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a class="ajaxify" href="<%=request.getContextPath()%>/admin/user/showMySelf">
							<i class="icon-wallet"></i>
							个人信息</a>
						</li>
						<li>
							<a class="ajaxify" href="<%=request.getContextPath()%>/admin/user/updatePwdUI">
							<i class="icon-wallet"></i>
							修改密码</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="javascript:;">
					<i class="icon-folder"></i>
					<span class="title">组织机构管理</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a class="ajaxify" href="<%=request.getContextPath() %>/admin/user/users">
							<i class="icon-wallet"></i>
							用户信息管理</a>
						</li>
						<li>
							<a class="ajaxify" href="<%=request.getContextPath() %>/admin/group/groups">
							<i class="icon-wallet"></i>
							用户组管理</a>
						</li>
						<li>
							<a class="ajaxify" href="<%=request.getContextPath() %>/admin/role/roles">
							<i class="icon-wallet"></i>
							角色管理</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="javascript:;">
					<i class="icon-folder"></i>
					<span class="title">新闻管理</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a id="wenzhang" class="ajaxify" href="<%=request.getContextPath() %>/admin/topic/audits">
							<i class="icon-wallet"></i>
							文章新闻管理</a>
						</li>
						<li>
							<a class="ajaxify" href="<%=request.getContextPath() %>/admin/picTopic/audits">
							<i class="icon-wallet"></i>
							组图新闻管理</a>
						</li>
						<li>
							<a class="ajaxify" href="<%=request.getContextPath() %>/admin/video/audits">
							<i class="icon-wallet"></i>
							视频新闻管理</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="javascript:;">
					<i class="icon-folder"></i>
					<span class="title">栏目管理</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a class="ajaxify" href="<%=request.getContextPath() %>/admin/channel/channels">
							<i class="icon-wallet"></i>
							栏目信息管理</a>
						</li>
						<li>
							<a class="ajaxify" href="<%=request.getContextPath() %>/admin/channel/TopNavchannels">
							<i class="icon-wallet"></i>
							顶部栏目设置</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="javascript:;">
					<i class="icon-folder"></i>
					<span class="title">首页管理</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a class="ajaxify" href="<%=request.getContextPath() %>/admin/cmsLink/links">
							<i class="icon-wallet"></i>
							友情链接管理</a>
						</li>
						<li>
							<a id="HeadImg" class="ajaxify" href="<%=request.getContextPath() %>/admin/pic/indexPics">
							<i class="icon-wallet"></i>
							首页宣传图片管理</a>
						</li>
						<li>
							<a class="ajaxify" href="<%=request.getContextPath() %>/admin/pic/newPics">
							<i class="icon-wallet"></i>
							首页新闻图片管理</a>
						</li>
						<li>
							<a class="ajaxify" href="<%=request.getContextPath() %>/admin/system/baseinfo">
							<i class="icon-wallet"></i>
							网站信息管理</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="javascript:;">
					<i class="icon-folder"></i>
					<span class="title">系统配置</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a class="ajaxify" href="<%=request.getContextPath() %>/admin/backups">
							<i class="icon-wallet"></i>
							网站数据备份</a>
						</li>
						<li>
							<a class="ajaxify" href="<%=request.getContextPath() %>/admin/system/cleans">
							<i class="icon-wallet"></i>
							垃圾文件清理</a>
						</li>
					</ul>
				</li>
			</ul>
	</div>
		</div>