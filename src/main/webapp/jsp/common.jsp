<!-- css -->
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css"/>
<!-- BEGIN PAGE LEVEL PLUGIN STYLES -->
<link href="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/fullcalendar/fullcalendar.min.css" rel="stylesheet" type="text/css"/>
<!-- END PAGE LEVEL PLUGIN STYLES -->
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL STYLES -->
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/bootstrap-select/bootstrap-select.min.css"/>
<link href="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/select2/select2.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/jquery-multi-select/css/multi-select.css"/>
<link href="${pageContext.servletContext.contextPath }/resources/assets/admin/pages/css/login.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.servletContext.contextPath }/resources/assets/admin/pages/css/error.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/jstree/dist/themes/default/style.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/bootstrap-toastr/toastr.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"/>
<link href="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/fancybox/source/jquery.fancybox.css" rel="stylesheet" type="text/css"/>
<!-- END PAGE LEVEL SCRIPTS -->
<!-- BEGIN THEME STYLES -->
<link href="${pageContext.servletContext.contextPath }/resources/assets/global/css/components.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.servletContext.contextPath }/resources/assets/global/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.servletContext.contextPath }/resources/assets/admin/layout/css/layout.css" rel="stylesheet" type="text/css"/>
<link id="style_color" href="${pageContext.servletContext.contextPath }/resources/assets/admin/layout/css/themes/default.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.servletContext.contextPath }/resources/assets/admin/layout/css/themes/darkblue.css" rel="stylesheet" type="text/css" id="style_color"/>
<link href="${pageContext.servletContext.contextPath }/resources/assets/admin/layout/css/custom.css" rel="stylesheet" type="text/css"/>
<!-- END THEME STYLES -->
<link rel="shortcut icon" href="favicon.ico"/>
<!-- js -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<![endif]-->
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/jquery-migrate.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/jquery-ui/jquery-ui.min.js" type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->

<script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/flot/jquery.flot.min.js" type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/flot/jquery.flot.resize.min.js" type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/flot/jquery.flot.categories.min.js" type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/jquery.pulsate.min.js" type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/bootstrap-daterangepicker/moment.min.js" type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/bootstrap-daterangepicker/daterangepicker.js" type="text/javascript"></script>
<!-- IMPORTANT! fullcalendar depends on jquery-ui.min.js for drag & drop support -->
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/fullcalendar/fullcalendar.min.js" type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/jquery-easypiechart/jquery.easypiechart.min.js" type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/jquery.sparkline.min.js" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->

<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/bootstrap-select/bootstrap-select.min.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/select2/select2.min.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js"></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/bootstrap-toastr/toastr.min.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/datatables/media/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/bootstrap-maxlength/bootstrap-maxlength.min.js" type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/bootstrap-touchspin/bootstrap.touchspin.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/fancybox/source/jquery.fancybox.pack.js"></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/plupload/js/plupload.full.min.js" type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/myjs/table-managed.js" ></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/jstree/dist/jstree.min.js"></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/plugins/jstree/dist/jstree.js"></script>

 
<!-- END PAGE LEVEL SCRIPTS -->
<%-- <script src="${pageContext.servletContext.contextPath }/resources/assets/myjs/jquery.form.js" ></script> --%>
<script src="${pageContext.servletContext.contextPath }/resources/assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/admin/layout/scripts/quick-sidebar.js" type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/admin/layout/scripts/demo.js" type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/admin/pages/scripts/index.js" type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath }/resources/assets/admin/pages/scripts/tasks.js" type="text/javascript"></script>
<!-- 表单校验 -->
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.validate.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/validate/main.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/core/validate-methods.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/core/jquery.cms.validate.js"></script>
<!-- ztree -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/zTree/zTreeStyle.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/tree/jquery.ztree.core-3.5.min.js"></script>
<!-- 导入ztree可选框的js包 -->
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/tree/jquery.ztree.excheck-3.5.min.js"></script>

<!--  -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/js/core/jquery.cms.keywordinput.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ui/jquery-ui-1.10.0.custom.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/i18n/jquery.ui.datepicker-zh-CN.js"></script>
<!-- 引入文件上传插件uploadify -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/uploadify/uploadify.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/uploadify/jquery.uploadify.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/js/core/jquery.cms.keywordinput.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/core/jquery.cms.keywordinput.js"></script>

<!-- 新增 -->
<script src="${pageContext.servletContext.contextPath }/resources/assets/bootbox.js"></script>
<!-- jqueryUI -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/js/base/jquery.ui.all.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/js/base/jquery-ui.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ui/jquery.ui.core.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ui/jquery.ui.button.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ui/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ui/jquery.ui.sortable.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ui/jquery.ui.spinner.js"></script>

<!-- 自定义插件 -->
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/core/jquery.cms.core.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/admin/inc.js"></script>
<!-- 必须引入dwr的engine.js -->
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<!-- 将java的类引入 -->
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/dwrService.js"></script>

<!-- 阻止自动换行 -->
<style type="text/css">
.c{
display:inline;
}
html { overflow-x:hidden; }
</style>

<script>
	jQuery(document).ready(function() {     
		   Metronic.init(); // init metronic core componets
		   Layout.init(); // init layout
		   QuickSidebar.init(); // init quick sidebar
		   Demo.init(); // init demo features
		   Index.init();   
		   Index.initDashboardDaterange();
		   Index.initCalendar(); // init index page's custom scripts
		   Index.initCharts(); // init index page's custom scripts
		   Index.initChat();
		   Index.initMiniCharts();
		   Tasks.initDashboardWidget();
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
	
		function showMessage(success,error){
			if (success != 'null' && success != "") {
				toastr.success(success);
			}
			if (error != 'null' && error != "") {
				toastr.error(error);
			}
		}
		
		//设置对话框语言为中文（默认为英文）
	    bootbox.setDefaults({
	        locale: "zh_CN"
	    });
</script>
<!-- END JAVASCRIPTS -->
<!-- <script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
  ga('create', 'UA-37564768-1', 'keenthemes.com');
  ga('send', 'pageview');
</script> -->