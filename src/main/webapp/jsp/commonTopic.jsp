<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<style type="text/css">
*.errorContainer {
	color:#f00;
	font-weight:bolder;
	padding:0px 4px;
}
html { overflow-x:hidden; }
.c{
display:inline;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.servletContext.contextPath }/resources/assets/global/css/components.css" rel="stylesheet" type="text/css"/>
<%-- <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/bootstrap-toastr/toastr.min.css"/> --%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/js/base/jquery.ui.all.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/admin/article.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/validate/main.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/zTree/zTreeStyle.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/js/core/jquery.cms.keywordinput.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-1.7.2.min.js"></script>
<%-- <script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/bootstrap-toastr/toastr.min.js"></script> --%>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/core/jquery.cms.keywordinput.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/tree/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/core/jquery.cms.core.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ui/jquery-ui-1.10.0.custom.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/i18n/jquery.ui.datepicker-zh-CN.js"></script>
<!-- 文章内容插件 -->
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/xheditor/xheditor-1.1.14-zh-cn.min.js"></script>
<!-- 引入文件上传插件uploadify -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/uploadify/uploadify.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/uploadify/jquery.uploadify.min.js"></script>
<!-- 引入jq校验 -->
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/core/jquery.cms.validate.js"></script>
<!-- 引入dwr -->
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/dwrService.js"></script>
<!-- 引入外部js文件 -->
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/admin/topicAdd.js"></script>
<script type="text/javascript">
	$(function(){		
		//初始化配置toastr
		toastr.options = {
		        "closeButton": true, //是否显示关闭按钮
		        "debug": false, //是否使用debug模式
		        "positionClass": "toast-bottom-right",//弹出窗的位置
		        "showDuration": "300",//显示的动画时间
		        "hideDuration": "1000",//消失的动画时间
		        "timeOut": "4000", //展现时间
		        "extendedTimeOut": "1000",//加长展示时间
		        "showEasing": "swing",//显示时的动画缓冲方式
		        "hideEasing": "linear",//消失时的动画缓冲方式
		        "showMethod": "fadeIn",//显示时的动画方式
		        "hideMethod": "fadeOut" //消失时的动画方式
		        };
		});
	});
function showMessage(success,error){
	if (success != 'null' && success != "") {
		toastr.success(success);
	}
	if (error != 'null' && error != "") {
		toastr.error(error);
	}
}
</script>